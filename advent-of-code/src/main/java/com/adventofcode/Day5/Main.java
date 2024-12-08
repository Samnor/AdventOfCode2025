package com.adventofcode.Day5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void solve(){
        List<int[]> rules = ReadPuzzle.readRules();
        for( int[] rule: rules) {
            System.out.println(rule[0]);
        }
        List<List<Integer>> updates = ReadPuzzle.readUpdate();
        System.out.println(updates.get(0).get(0));
        HashMap<Integer, NumberNode> ruleSystem = buildRules(rules);
        int correctSum = 0;
        int incorrectSum = 0;
        for(List<Integer> update: updates) {
            if (evaluateUpdate(update, ruleSystem)){
                System.out.println("YES");
                correctSum += getMiddleValue(update);
                System.out.println(update);
            } else {
                System.out.println("NO");
                incorrectSum += getSumFromIncorrect(update, ruleSystem);
            }
        }
        System.out.println(correctSum);
        System.out.println(incorrectSum);
    }

    public static Boolean evaluateUpdate(List<Integer> update, HashMap<Integer, NumberNode> ruleSystem) {
        for (int i = 0; i < (update.size() - 1); i++) {
            int first = update.get(i);
            int second = update.get(i + 1);
            NumberNode firstNode = ruleSystem.getOrDefault(first, new NumberNode(first));
            NumberNode secondNode = ruleSystem.getOrDefault(second, new NumberNode(second));
            if (secondNode.before.contains(firstNode) == false) {
                return false;
            }
        }
        return true;
    }

    public static int getMiddleValue(List<Integer> update) {
        return update.get(update.size()/2);
    }

    public static HashMap<Integer, NumberNode> buildRules(List<int[]> rules) {
        HashMap<Integer, NumberNode> result = new HashMap<>();
        for(int[] rule: rules) {
            int first = rule[0];
            int second = rule[1];
            NumberNode firstNode = result.getOrDefault(first, new NumberNode(first));
            NumberNode secondNode = result.getOrDefault(second, new NumberNode(second));
            firstNode.after.add(secondNode);
            secondNode.before.add(firstNode);
            System.out.println(firstNode);
            System.out.println(secondNode);
            result.putIfAbsent(first, firstNode);
            result.putIfAbsent(second, secondNode);
        }
        return result;
    }

    public static int getSumFromIncorrect(List<Integer> update, HashMap<Integer, NumberNode> ruleSystem) {
        List<Integer> correctUpdate = new ArrayList<>();
        List<Integer> updateToAdd = new ArrayList<>(update);
        while (correctUpdate.size() < update.size()){
            for (int i = 0; i < updateToAdd.size(); i++) {
                HashSet<NumberNode> fullAfter = new HashSet<>();
                int baseNumber = updateToAdd.get(i);
                Boolean badBase = false;
                for (int j = 0; j < updateToAdd.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    int current = updateToAdd.get(j);
                    fullAfter.addAll(ruleSystem.get(current).after);
                    if (fullAfter.contains(ruleSystem.get(baseNumber))) {
                        badBase = true;
                        break;
                    }
                }
                if (badBase) {

                } else {
                    correctUpdate.add(baseNumber);
                    updateToAdd.remove((Integer) baseNumber);
                    break;
                }
            }
        }
        return getMiddleValue(correctUpdate);
    }
}
