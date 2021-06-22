package com.example.demo.TableGenerator;

import com.example.demo.DemoApplication;
import com.example.demo.TableGenerator.Annotations.MappingColumn;
import com.example.demo.TableGenerator.Annotations.MappingColumnPYID;
import com.example.demo.TableGenerator.Annotations.MappingTable;
import com.example.demo.TableGenerator.type.DataType;
import com.example.demo.TableGenerator.type.StorageType;
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
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class GenerateTableScanner {

    private final JdbcTemplate jdbcTemplate;
    private final ConfigurableApplicationContext context;
    @Setter
    boolean isDropAllIfExist = false;
    @Setter
    private String packageName;

    public GenerateTableScanner(String[] args) {
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

                String dbURL = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection().getMetaData().getURL();
                String dbName = dbURL.split("/")[3];
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

            String RESOURCE_PATTERN = "/**/*.class";
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(packageName) + RESOURCE_PATTERN;
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
                // 判断类中是否有指定注解
                if (clazz.isAnnotationPresent(MappingTable.class)) {
                    // 获取实体映射的表名
                    String tableName = toLineCase(clazz.getSimpleName());
                    // 全部实体映射的字段
                    StringBuilder fieldContent = new StringBuilder();
                    for (Field field : clazz.getDeclaredFields()) {
                        // 判断字段中是否有指定注解
                        DataType dataType = null;
                        StorageType[] storageTypes = null;
                        MappingColumnPYID mappingColumnPYID = field.getAnnotation(MappingColumnPYID.class);
                        MappingColumn mappingColumn = field.getAnnotation(MappingColumn.class);
                        if (mappingColumnPYID != null) {
                            dataType = mappingColumnPYID.dataType();
                            storageTypes = mappingColumnPYID.storageTypes();
                        } else {
                            if (mappingColumn != null) {
                                dataType = mappingColumn.dataType();
                                storageTypes = mappingColumn.storageTypes();
                            }
                        }
                        if (mappingColumn != null || mappingColumnPYID != null) {
                            fieldContent.append(field.getName()).append(" ");
                            // 必须调用 getTypeName, 因为不调用会有下划线 CHAR_20
                            fieldContent.append(dataType.getTypeName()).append(" ");
                            for (StorageType storageTypeName : storageTypes) {
                                // 必须调用 getStorageTypeName, 因为不调用会有下划线 AUTO_INCREMENT
                                fieldContent.append(storageTypeName.getStorageTypeName()).append(" ");
                            }
                            fieldContent.append(",\n");
                        }
                    }
                    // 删掉最后一个逗号
                    fieldContent.deleteCharAt(fieldContent.length() - 2);
                    // 创建单表的完整 sql 语句
                    String sql = generateSql(tableName, fieldContent);
                    // 执行 sql 语句
                    jdbcTemplate.execute(sql);
                }
            }

            System.out.println("创建表完成。");
            System.out.println("====================================================================================");
            System.out.println("====================================================================================");
            System.out.println("====================================================================================");
            // 关闭 context
            context.close();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private String toLineCase(String camelCase) {
        Pattern humpPattern = Pattern.compile("[A-Z]");
        Matcher matcher = humpPattern.matcher(camelCase);
        StringBuilder sb = new StringBuilder();
        if (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(0).toLowerCase());
        }
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
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

}
