package com.adventofcode.Day5;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class NumberNode {
    private static final AtomicInteger COUNTER = new AtomicInteger(0);
    private final int id;
    public Set<NumberNode> before;
    public Set<NumberNode> after;

    NumberNode(int id){
        this.id = id;
        this.before = new HashSet<NumberNode>();
        this.after = new HashSet<NumberNode>();
    }

    @Override
    public String toString() {
        String beforeIds = before.stream()
            .map(n -> String.valueOf(n.id))
            .collect(Collectors.joining(", "));
        String afterIds = after.stream()
            .map(n -> String.valueOf(n.id))
            .collect(Collectors.joining(", "));

        return String.format("NumberNode #%d { before: [%s], after: [%s] }", id, beforeIds, afterIds);
    }
}
