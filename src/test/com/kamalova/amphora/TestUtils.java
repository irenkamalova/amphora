package com.kamalova.amphora;

public class TestUtils {

    public static FamilyTree createFamilyTree() {
        FamilyTree familyTree = new FamilyTree();
        familyTree.add("Anna", 1900, null, null); // 3
        familyTree.add("Bob", 1910, null, null); // 4
        familyTree.add("Loly", 1889, null, null); // 1
        familyTree.add("Erich", 1890, null, null); // 2

        familyTree.add("Elise", 1920, "Anna", "Bob"); // 8
        familyTree.add("Tom", 1917, null, "Anna"); // 7
        familyTree.add("Linda", 1916, "Bob", null); // 6
        familyTree.add("Paul", 1911, "Loly", "Erich"); // 5

        familyTree.add("Samanta", 1940, "Elise", "Paul"); // 9
        familyTree.add("David", 1960, null, "Samanta"); // 10

        return familyTree;
    }
}
