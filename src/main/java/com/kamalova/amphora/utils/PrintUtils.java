package com.kamalova.amphora.utils;


import com.kamalova.amphora.model.FamilyNode;
import com.kamalova.amphora.service.FamilyTree;

import java.util.*;

public class PrintUtils {

    // Q4: Print algorithm
    public static void printTree(FamilyTree familyTree) {
        // BFS to print the tree
        Set<FamilyNode> seen = new HashSet<>();
        Queue<FamilyNode> queue = new LinkedList<>(familyTree.getRoots());

        while (!queue.isEmpty()) {
            FamilyNode node = queue.poll();

            List<FamilyNode> kids = node.getKids();
            kids.forEach(kid -> {
                if (!seen.contains(kid)) {
                    queue.add(kid);
                    seen.add(kid);
                }
            });
            seen.add(node);
            System.out.println(node);
        }
    }

    // Q5: Print algorithm
    public static void printUpwardsTreeFrom(FamilyNode kid) {
        // BFS to print the tree
        Set<FamilyNode> seen = new HashSet<>();
        Queue<FamilyNode> queue = new LinkedList<>();
        queue.add(kid);
        while (!queue.isEmpty()) {
            FamilyNode node = queue.poll();
            FamilyNode[] parents = node.getParents();
            for (FamilyNode parent : parents) {
                if (parent != null && !seen.contains(parent)) {
                    queue.add(parent);
                    seen.add(parent);
                }
            }
            seen.add(node);
            System.out.println(node);
        }

    }
}