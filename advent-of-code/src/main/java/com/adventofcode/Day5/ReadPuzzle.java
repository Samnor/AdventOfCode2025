package com.adventofcode.Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;



public class ReadPuzzle {
    static String filePath = "src/main/java/com/adventofcode/Day5/input5.txt"; 
    public static List<List<Integer>> readUpdate() {
        // Lists to store each column separately
        List<List<Integer>> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read each line of the TSV
            int ruleLength = 5;
            line = br.readLine();
            while (line.length() == ruleLength) {
                line = br.readLine();
            }
            line = br.readLine();
            while (true) {
                result.add(
                    Arrays.stream(line.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
                );
                line = br.readLine();
                if (line == null) {
                    break;
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static List<int[]> readRules() {
        // Lists to store each column separately
        List<int[]> result = new ArrayList<int[]>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read each line of the TSV
            int ruleLength = 5;
            line = br.readLine();
            while (line.length() == ruleLength) {
                String[] components = line.split("\\|");
                result.add(
                    new int[]{
                        Integer.parseInt(components[0]),
                        Integer.parseInt(components[1])
                    }
                );
                line = br.readLine();
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
