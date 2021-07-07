package com.example.demo.util.tablegenerator;

/**
 * @author 10338
 */
public class GenerateTableRun {
    public static void main(String[] args) {
        GenerateTableHandler generateTableHandler = new GenerateTableHandler(args);
        generateTableHandler.setPackageName("com.example.demo.entity");
        generateTableHandler.setIsDropAllIfExist(1);
        generateTableHandler.run();
    }
}
