package com.amithgc.billgenerator.enricher.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EnricherFinder {

    public static List<Class<?>> getAllEnrichers() {
        List<Class<?>> enrichers = new ArrayList<>();

        // Get all classes in the current classpath
        String classPath = System.getProperty("java.class.path");
        String[] classPathEntries = classPath.split(System.getProperty("path.separator"));

        try {
            // Loop through all classpath entries to find classes with @Enricher annotation
            for (String entry : classPathEntries) {
                findEnrichersInPackage("", entry, enrichers);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return enrichers;
    }

    private static void findEnrichersInPackage(String packageName, String path, List<Class<?>> enrichers) throws ClassNotFoundException {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }

        if (file.isDirectory()) {
            String[] files = file.list();
            if (files != null) {
                for (String fileName : files) {
                    findEnrichersInPackage(packageName + "." + fileName, path + File.separator + fileName, enrichers);
                }
            }
        } else {
            if (path.endsWith(".class")) {
                String className = packageName.substring(1).replace(".class", "");
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Enricher.class)) {
                    enrichers.add(clazz);
                }
            }
        }
    }
}
