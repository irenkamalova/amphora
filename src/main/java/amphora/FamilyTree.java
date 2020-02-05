package com.kamalova.amphora;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class FamilyTree {
    private final FamilyNode root;
    private Map<Double, FamilyNode> map = new HashMap<>();
    int relation; // <- TODO Enum
    // 0 -> kid, 1 -> father, 2 -> mother

    public FamilyTree(FamilyNode root) {
        this.root = root;
        map.put(root.getId(), root);
    }

    // id
    public FamilyNode add(String name, int age, double idOfRelationNode, int relation) {
        // find parent of node?
        FamilyNode to = map.get(idOfRelationNode);

        switch (relation) {
            case 0 : {
                // getId getFirst
                double id;
                if (to.hasKids()) {
                 id = to.getFirstKid().getId() + 1;
                } else {
                    id = to.getId() * 0.1;
                }
                FamilyNode node = new FamilyNode(id, name, age);
                to.addKid(node);
                break;
            }
            case 1: {
                double id = to.getId() - to.getId() / 2;
                FamilyNode node = new FamilyNode(id, name, age);
                to.addFather(node);
                break;
            }
            case 2: {
                double id = to.getId() + to.getId() / 2;
                FamilyNode node = new FamilyNode(id, name, age);
                to.addMother(node);
                break;
            }
            default: throw new IllegalArgumentException("Non correct relation");
        }
        return root;
    }

    public void printTree() {
        // BFS to print the tree
        Queue<FamilyNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            FamilyNode node = queue.poll();
            FamilyNode father = node.getFather();
            FamilyNode mother = node.getMother();
            queue.add(father);
            queue.add(mother);
            System.out.println(node);

        }
        System.out.println(root.toString());

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