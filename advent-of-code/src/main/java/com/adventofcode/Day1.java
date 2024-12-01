package com.adventofcode;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day1 {
    public static void solve() {
        Map<String, int[]> input = ReadTSVDay1.readDay1Columns();
        int[] sortedFirst = input.get("first");
        Arrays.sort(sortedFirst);
        int[] sortedSecond = input.get("second");
        Arrays.sort(sortedSecond);
        System.out.println(Arrays.toString(sortedFirst));
        System.out.println(Arrays.toString(sortedSecond));
        int distance = 0;
        for (int i = 0; i < sortedFirst.length; i++) {
            System.out.println(Math.abs(sortedFirst[i] - sortedSecond[i]));
            distance = distance + Math.abs(sortedFirst[i] - sortedSecond[i]);
        }
        System.out.println(distance);
        int similiarity = scoreSimiliar(sortedFirst, sortedSecond);
        System.out.println(similiarity);
    }

    static void fillOccurences(int[] array, HashMap<Integer, Integer> occurences) {
        for (int val: array) {
            if (occurences.get(val) == null) {
                occurences.put(val, 0); 
            }
            int currentCount = occurences.get(val);
            occurences.put(val, currentCount + 1);
        }
    }

    static int scoreSimiliar(int[] arrayOne, int[] arrayTwo) {
        HashMap<Integer, Integer> occurences = new HashMap<Integer, Integer>();
        fillOccurences(arrayTwo, occurences);
        int similiarScore = 0;
        for (int val: arrayOne) {
            similiarScore += val * occurences.getOrDefault(val, 0);
        }
        return similiarScore;
    }
}
