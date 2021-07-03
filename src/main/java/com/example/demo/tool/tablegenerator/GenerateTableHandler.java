package com.example.demo.tool.tablegenerator;

import com.example.demo.DemoApplication;
import com.example.demo.tool.tablegenerator.annotation.*;
import com.example.demo.tool.tablegenerator.type.DataType;
import com.example.demo.tool.tablegenerator.type.StorageType;
import com.example.demo.tool.tablegenerator.type.TypeWrap;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class AnnotationClasses {

    /**
     * 注解类集合
     */
    public static final ArrayList<Class<? extends Annotation>> ANNOTATION_CLASSES = new ArrayList<>(Arrays.asList(
            OutColumn.class,
            OutColumnPYID.class,
            OutColumnAIID.class,
            OutColumnTimestamp.class
    ));
}

/**
 * @author 10338
 */
@Getter
public class GenerateTableHandler {


    private final JdbcTemplate jdbcTemplate;
    private final ConfigurableApplicationContext context;
    private final Pattern humpPattern = Pattern.compile("[A-Z]");
    @Setter
    boolean isDropAllIfExist = false;
    @Setter
    private String packageName;

    public GenerateTableHandler(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        this.context = springApplication.run(args);
        this.jdbcTemplate = new JdbcTemplate(context.getBean(DataSource.class));
    }

    public void run() {
        System.out.println("====================================================================================");
        System.out.println("====================================================================================");
        System.out.println("====================================================================================");
        try {
            if (isDropAllIfExist) {
                System.out.println("正在删除全部表...");

                String dbUrl = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection().getMetaData().getURL();
                String dbName = dbUrl.split("/")[3];
                List<Map<String, Object>> tableNames = jdbcTemplate.queryForList("select table_name from information_schema.tables where table_schema='" + dbName + "'");
                for (Map<String, Object> tableName : tableNames
                ) {
                    jdbcTemplate.execute("DROP TABLE IF EXISTS " + tableName.get("TABLE_NAME") + ";");
                    System.out.println(tableName.get("TABLE_NAME") + "已删除。");
                }

                System.out.println("删除完成。");
            }

            System.out.println("正在创建表...");

            //spring工具类，可以获取指定路径下的全部类
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

            String resourcePattern = "/**/*.class";
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(packageName) + resourcePattern;
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            //MetadataReader 的工厂类
            MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            // 扫描全部类
            for (Resource resource : resources) {
                // 用于读取类信息
                MetadataReader reader = metadataReaderFactory.getMetadataReader(resource);
                // 扫描到的 class
                String classname = reader.getClassMetadata().getClassName();
                Class<?> clazz = Class.forName(classname);

                // 判断类中是否有指定注解。
                if (clazz.isAnnotationPresent(OutTable.class)) {

                    // 获取实体类映射的表名。
                    String tableName = toLineCase(clazz.getSimpleName());

                    // 实体映射的全部 字段—column。
                    StringBuilder fieldContent = new StringBuilder();

                    // 每个类的每个字段(含当前类私有和全部继承类私有)。
                    List<Field[]> fieldsGroup = new ArrayList<>();
                    Class<?> whileClass = clazz;
                    while (whileClass != Object.class) {
                        fieldsGroup.add(whileClass.getDeclaredFields());
                        whileClass = whileClass.getSuperclass();
                    }
                    // 组合成最终 fields。
                    ArrayList<Field> fields = new ArrayList<>();
                    for (int i = fieldsGroup.size() - 1; i >= 0; i--) {
                        Field[] fs = fieldsGroup.get(i);
                        fields.addAll(Arrays.asList(fs));
                    }

                    // 遍历每个类的每个字段(含当前类私有和全部继承类私有)。
                    for (Field field : fields) {

                        // 获取每个字段的每个符合要求的注解，若不存在注解或不符合要求，则返回 Optional.empty()。
                        Optional<TypeWrap> typeWrapOptional = getAnnotationProperty(field);

                        if (typeWrapOptional.isPresent()) {
                            TypeWrap typeWrap = typeWrapOptional.get();

                            // 检查 [数据库字段类型] 是否与 [java 字段类型] 对应。
                            checkType(field, typeWrap);

                            // 获取 字段—column 名。
                            fieldContent.append(toLineCase(field.getName())).append(" ");

                            // 必须调用 getTypeName, 因为不调用会有下划线 CHAR_20。
                            fieldContent.append(typeWrap.getDataType().getDatabaseName()).append(" ");
                            for (StorageType storageTypeName : typeWrap.getStorageTypes()) {
                                // 必须调用 getStorageTypeName, 因为不调用会有下划线 AUTO_INCREMENT。
                                fieldContent.append(storageTypeName.getStorageTypeName()).append(" ");
                            }

                            fieldContent.append(",\n");
                        }
                    }
                    // 删掉最后一个逗号。
                    fieldContent.deleteCharAt(fieldContent.length() - 2);

                    // 创建单表的完整 sql 语句。
                    String sql = generateSql(tableName, fieldContent);

                    // 执行 sql 语句
                    jdbcTemplate.execute(sql);
                }
            }

            System.out.println("创建表完成。");
            System.out.println("====================================================================================");
            System.out.println("====================================================================================");
            System.out.println("====================================================================================");
            // 关闭 context。
            context.close();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private String toLineCase(String camelCase) {
        Matcher matcher = humpPattern.matcher(camelCase);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        // 如果开头含下划线，则删掉
        String underLine = "_";
        if (sb.charAt(0) == underLine.charAt(0)) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    private String generateSql(String tableName, StringBuilder fieldContent) {
        String table = "CREATE TABLE " + tableName +
                "(\n" +
                fieldContent +
                ");";
        System.out.println(table);
        return table;
    }


    /**
     * 获取每个字段的每个符合要求的注解，若不存在注解或不符合要求，则返回 Optional.empty()。
     */
    private Optional<TypeWrap> getAnnotationProperty(Field field) throws Throwable {
        Optional<TypeWrap> typeWrap = Optional.empty();

        for (Class<? extends Annotation> annotationClass : AnnotationClasses.ANNOTATION_CLASSES) {
            // 获取每个字段的对应注解类，若不存在，则为 null。
            Annotation annotation = field.getAnnotation(annotationClass);
            if (annotation != null) {
                if (typeWrap.isPresent()) {
                    throw new Throwable("该字段存在多个相同类型注解");
                }

                // 注解类的成员基本全是方法。
                // 这里可能会异常：未找到对应方法
                Method dataTypeMethod = annotationClass.getMethod("dataType");
                Method storageTypesMethod = annotationClass.getMethod("storageTypes");
                typeWrap = Optional.of(
                        new TypeWrap(
                                (DataType) dataTypeMethod.invoke(annotation),
                                (StorageType[]) storageTypesMethod.invoke(annotation)
                        ));
            }
        }
        return typeWrap;
    }

    /**
     * 检查 [数据库字段类型] 是否与 [java 字段类型] 对应。
     */
    private void checkType(Field field, TypeWrap typeWrap) throws Throwable {
        String enumName = typeWrap.getDataType().name();
        String databaseTypeName = typeWrap.getDataType().getDatabaseName();
        String javaTypeName = typeWrap.getDataType().getJavaClass().getTypeName();
        String fieldTypeName = field.getType().getTypeName();

        if (!fieldTypeName.equals(javaTypeName)) {
            throw new Throwable(
                    "[数据库字段类型] 与 [java 字段类型] 不一致!" + "\n" +
                            "应该为: " +
                            "[java 字段类型名: " + javaTypeName + "], " +
                            "[数据库字段类型名: " + databaseTypeName + "], " +
                            "[枚举名: " + enumName + "] " + "\n" +
                            "而当前: [java 字段类型名: " + fieldTypeName + "]" + "\n" +
                            "实体类: " + field.getDeclaringClass().getTypeName() + "\n" +
                            "字段名: " + field.getName()
            );
        }
    }

}
