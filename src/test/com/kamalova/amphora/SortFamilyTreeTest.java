package com.kamalova.amphora;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class SortFamilyTreeTest {

    @Test
    @DisplayName("When sort three then data is correct")
    void testCorrectSorting() {
        FamilyTree t = new FamilyTree();
        t.add("Anna", 1900, null, null); // 3
        t.add("Bob", 1910, null, null); // 4
        t.add("Loly", 1889, null, null); // 1
        t.add("Erich", 1890, null, null); // 2

        t.add("Elise", 1920, "Anna", "Bob"); // 8
        t.add("Tom", 1917, null, "Anna"); // 7
        t.add("Linda", 1916, "Bob", null); // 6
        t.add("Paul", 1911, "Loly", "Erich"); // 5

        t.add("Samanta", 1940, "Elise", "Paul"); // 9
        t.add("David", 1960, null, "Samanta"); // 10

        // Q3 - sort tree in ascending order:
        List<FamilyNode> sortedAscendingOrder = SortFamilyTree.sort(t, Integer::compareTo);

        assertEquals(1889, sortedAscendingOrder.get(0).getAge());
        assertEquals(1890, sortedAscendingOrder.get(1).getAge());
        assertEquals(1900, sortedAscendingOrder.get(2).getAge());
        assertEquals(1910, sortedAscendingOrder.get(3).getAge());
        assertEquals(1911, sortedAscendingOrder.get(4).getAge());
        assertEquals(1916, sortedAscendingOrder.get(5).getAge());
        assertEquals(1917, sortedAscendingOrder.get(6).getAge());
        assertEquals(1920, sortedAscendingOrder.get(7).getAge());
        assertEquals(1940, sortedAscendingOrder.get(8).getAge());
        assertEquals(1960, sortedAscendingOrder.get(9).getAge());

        // Q2 - sort tree in descending order:
        List<FamilyNode> sortedDescendingOrder = SortFamilyTree.sort(t,
                (Integer x, Integer y) -> Integer.compare(x, y) * (-1));

        Collections.reverse(sortedAscendingOrder);
        assertIterableEquals(sortedAscendingOrder, sortedDescendingOrder);
        assertEquals(1960, sortedDescendingOrder.get(0).getAge());


    }

}