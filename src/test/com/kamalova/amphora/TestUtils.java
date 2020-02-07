package com.kamalova.amphora;

import com.kamalova.amphora.dao.FamilyTree;

public class TestUtils {

    public static FamilyTree createFamilyTree() {
        FamilyTree familyTree = new FamilyTree();
        familyTree.save("Anna", 1900, null, null); // 3
        familyTree.save("Bob", 1910, null, null); // 4
        familyTree.save("Loly", 1889, null, null); // 1
        familyTree.save("Erich", 1890, null, null); // 2

        familyTree.save("Elise", 1920, "Anna", "Bob"); // 8
        familyTree.save("Tom", 1917, null, "Anna"); // 7
        familyTree.save("Linda", 1916, "Bob", null); // 6
        familyTree.save("Paul", 1911, "Loly", "Erich"); // 5

        familyTree.save("Samanta", 1940, "Elise", "Paul"); // 9
        familyTree.save("David", 1960, null, "Samanta"); // 10

        return familyTree;
    }
}
