package com.kamalova.amphora.utils;

import com.kamalova.amphora.dao.model.FamilyNode;
import com.kamalova.amphora.dao.FamilyTree;

import java.util.*;
import java.util.Comparator;
import java.util.stream.Collectors;

public class SortFamilyTree {
    /*
    Possible approaches:
    create the TreeMap and add every age as key
    then collect it all to the collection
    Complexity of TreeMap is O(logN) for insertion and lookup,
    so iterating through all elements by O(N), the final complexity will be O(N)
    Props: you can change comparator
    Cons: extra space for the TreeMap
     */

    public static List<FamilyNode> sort(FamilyTree familyTree,
                                        Comparator<Integer> comparator) {
        TreeMap<Integer, List<FamilyNode>> sortedMap = new TreeMap<>(comparator);
        // BFS to iterate through the family tree
        Set<FamilyNode> seen = new HashSet<>();
        Queue<FamilyNode> queue = new LinkedList<>(familyTree.getRoots());

        while (!queue.isEmpty()) {
            FamilyNode node = queue.poll();
            sortedMap.computeIfAbsent(node.getAge(), k -> new ArrayList<>());
            sortedMap.get(node.getAge()).add(node);
            List<FamilyNode> kids = node.getKids();
            kids.forEach(kid -> {
                if (!seen.contains(kid)) {
                    queue.add(kid);
                    seen.add(kid);
                }
            });
            seen.add(node);
        }

        return sortedMap.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

}
