package com.example.demo.tool.TableGenerator;

public class GenerateTableRun {
    public static void main(String[] args) {
        GenerateTableScanner generateTableScanner = new GenerateTableScanner(args);
        generateTableScanner.setPackageName("com.example.demo.entity");
        generateTableScanner.setDropAllIfExist(true);
        generateTableScanner.run();
    }
}
