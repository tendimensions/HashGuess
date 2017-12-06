package com.tendimensions;

import javafx.util.Pair;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Main myApp = new Main();
        myApp.run(args);
    }

    private void run(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: HashGuessing [filename with permutation components] [filename with hash to find]");
            System.exit(0);
        }

        Components components = loadFiles(args);
        components.initialize();

        Pair<String, String> result = components.findHash();
        if (result == null) {
            System.out.println("Didn't find it...");
        } else {
            System.out.println(String.format("Holy shit, we found a match! String: %s produced our hash using %s", result.getKey(), result.getValue()));
        }
    }

    private Components loadFiles(String[] args) {
        Components result = new Components();

        List<String> lines = readLinesFile(args[0]);
        result.setValues(lines);

        result.setHashToFind(readHashFile(args[1]));

        System.out.println("Starting program using these strings to hash: ");
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println();

        System.out.println("Looking for this hash: ");
        System.out.println(result.getHashToFind());
        System.out.println();

        return result;
    }

    private String readHashFile(String fileName) {
        String line = null;

        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    private List<String> readLinesFile(String fileName) {
        List<String> results = new ArrayList<>();

        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                results.add(line);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }
}
