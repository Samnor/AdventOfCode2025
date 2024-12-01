package com.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;



public class ReadTSVDay1 {
    public static Map<String, int[]> readDay1Columns() {
        String filePath = "src/main/java/com/adventofcode/puzzle_input.tsv";
        
        // Lists to store each column separately
        List<String> column1 = new ArrayList<>();
        List<String> column2 = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read each line of the TSV
            while ((line = br.readLine()) != null) {
                // Split the line by tab delimiter
                String[] values = line.split("\t");

                // Add each column value to the respective list
                if (values.length > 0) column1.add(values[0]);
                if (values.length > 1) column2.add(values[1]);
                // Add more columns as needed based on your TSV structure
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert lists to arrays if needed
        int[] column1Array = column1.stream().mapToInt(Integer::parseInt).toArray();
        int[] column2Array = column2.stream().mapToInt(Integer::parseInt).toArray();
        HashMap<String, int[]> dictionary = new HashMap<String, int[]>();
        dictionary.put("first", column1Array);
        dictionary.put("second", column2Array);
        System.out.println(Arrays.toString(column1Array));
        System.out.println(Arrays.toString(column2Array));
        return dictionary;
    }
}
