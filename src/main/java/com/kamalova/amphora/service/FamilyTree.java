package com.kamalova.amphora.service;

import com.kamalova.amphora.model.FamilyNode;
import lombok.Getter;

import java.util.*;

public class FamilyTree {
    @Getter
    private final List<FamilyNode> roots = new ArrayList<>();

    public FamilyTree() {
    }

    public FamilyNode add(String name, int age,
                          String father, String mother) {
        FamilyNode node;
        if (father == null && mother == null) {
            node = new FamilyNode(0, name, age);
            roots.add(node);
            return node;
        } else if (father != null && mother != null) {
            FamilyNode fn = findByName(father);
            FamilyNode mn = findByName(mother);
            if (fn.getGeneration() != mn.getGeneration()) {
                throw new IllegalArgumentException("Incorrect parents");
            }
            node = new FamilyNode(fn.getGeneration() + 1, name, age);
            fn.addKid(node);
            mn.addKid(node);
            FamilyNode[] parents = node.getParents();
            parents[0] = fn;
            parents[1] = mn;
            return node;
        } else if (mother != null) {
            return addToParent(name, age, mother);
        } else {
            return addToParent(name, age, father);
        }
    }

    private FamilyNode addToParent(String name, int age, String parent) {
        if (parent == null) {
            throw new IllegalArgumentException("Error with " + name);
        }
        FamilyNode parentNode = findByName(parent);
        FamilyNode node = new FamilyNode(parentNode.getGeneration() + 1, name, age);
        FamilyNode[] parents = node.getParents();
        parents[0] = parentNode;
        parentNode.addKid(node);
        return node;
    }

    public FamilyNode findByName(String name) {
        // BFS to search node - O(N) complexity
        Set<FamilyNode> seen = new HashSet<>();
        Queue<FamilyNode> queue = new LinkedList<>(roots);

        while (!queue.isEmpty()) {
            FamilyNode node = queue.poll();
            if (node.getName().equals(name)) {
                return node;
            }
            List<FamilyNode> kids = node.getKids();
            kids.forEach(kid -> {
                if (!seen.contains(kid)) {
                    queue.add(kid);
                    seen.add(kid);
                }
            });
            seen.add(node);
        }
        throw new IllegalArgumentException("Parent " + name + " not founded");
    }

}


/*

Q1: Implement a test class to inject and create the family structure one node at a time. The structure should at least
have five levels ( great grant parents, grand parent, parent, and kids, grand-kids).
- Make the tree unbalanced. Try to have a good distribution of the number of kids for a pair of parents (i.e. some
with no kids, some with 2 kids, some with 3, or 4....).
The injection time complexity should be O(n). i.e. one logical search to find the place to enter a family node.
Q2: Implement an algorithm with possibly O(n) complexity to sort the whole family tree in age descending order.
Q3: Implement a similar algorithm to sort the whole family tree in age ascending order.
Q4: Pretty print algorithm to print the family tree.
Q5: Print the reverse family tree ( upwards) from a node including both parents for each level(i.e. for a kid print its
parents, parents’ parents, great grand-parents.
Q6: Can you think of an algorithm that can insert a new family member ( assume we found a long missing member!) in
to the correct place of a sorted (ascending) list based on age, where the algorithm’s time complexity is better than
O(n)?. pseudo code or actual implementation is fine.
Q7: Candidate can use Mockito like mocking tool to showcase how the data persistence layer ( assume these records
can be persisted in a DB but for the test we don’t need a working database) can be mocked.

 */