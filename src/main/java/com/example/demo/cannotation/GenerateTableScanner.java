package com.example.demo.cannotation;

import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;

@Data
public class GenerateTableScanner {

    private String packageName;

    public void generate() {
        try {
            System.out.println("---------------------111");

            //spring工具类，可以获取指定路径下的全部类
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

            String RESOURCE_PATTERN = "/**/*.class";
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(packageName) + RESOURCE_PATTERN;
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            //MetadataReader 的工厂类
            MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                //用于读取类信息
                MetadataReader reader = metadataReaderFactory.getMetadataReader(resource);
                //扫描到的class
                String classname = reader.getClassMetadata().getClassName();
                Class<?> clazz = Class.forName(classname);
                //判断字段中是否有指定注解
                for (Field f : clazz.getDeclaredFields()) {
                    MapField generateTable = f.getAnnotation(MapField.class);
                    System.out.println(generateTable);
                    if (generateTable != null) {
                        System.out.println(generateTable.storageTypes());
                    }
                }
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
