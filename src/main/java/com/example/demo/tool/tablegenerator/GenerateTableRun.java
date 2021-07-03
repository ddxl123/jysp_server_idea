package com.example.demo.tool.tablegenerator;

/**
 * @author 10338
 */
public class GenerateTableRun {
    public static void main(String[] args) {
        GenerateTableHandler generateTableHandler = new GenerateTableHandler(args);
        generateTableHandler.setPackageName("com.example.demo.entity");
        generateTableHandler.setDropAllIfExist(true);
        generateTableHandler.run();
    }
}
