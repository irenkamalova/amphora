package com.kamalova.amphora;

public class Test {

    public static void main(String[] args) {
        FamilyNode r = new FamilyNode(100, "Anna", 1990);

        FamilyTree t = new FamilyTree();
        t.add("Anna", 1900, null, null);
        t.add("Bob", 1900, null, null);

        t.add("Loly", 1890, null, null);
        t.add("Erich", 1890, null, null);

        t.add("Elise", 1920, "Anna", "Bob");
        t.add("Tom", 1917, null, "Anna");
        t.add("Linda", 1917, "Bob", null);
        t.add("Paul", 1910, "Loly", "Erich");

        FamilyNode samanta = t.add("Samanta", 1940, "Elise", "Paul");

        FamilyTreeUtils.printTree(t);
        FamilyTreeUtils.printUpwardsTreeFrom(samanta);

    }
}
