package com.kamalova.amphora;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTreeTest {


    @Test
    @DisplayName("When add to tree then data is correct")
    void add() {
        FamilyTree t = new FamilyTree();
        // A, B - no kids
        assertEquals("A", t.add("A", 1900, null, null).getName());
        assertEquals("B", t.add("B", 1900, null, null).getName());
        // A1, B1 - two kids
        assertEquals("A1", t.add("A1", 1900, null, null).getName());
        assertEquals("B1", t.add("B1", 1900, null, null).getName());
        assertEquals("aA1", t.add("aA1", 1920, "A1", "B1").getName());
        assertEquals("bA1", t.add("bA1", 1922, "A1", "B1").getName());


        assertEquals("Anna", t.add("Anna", 1900, null, null).getName());
        assertEquals("Bob", t.add("Bob", 1900, null, null).getName());
        assertEquals("Loly", t.add("Loly", 1890, null, null).getName());
        assertEquals("Erich", t.add("Erich", 1890, null, null).getName());
        assertEquals("Elise", t.add("Elise", 1920, "Anna", "Bob").getName());
        assertEquals("Tom", t.add("Tom", 1917, null, "Anna").getName());
        assertEquals("Linda", t.add("Linda", 1917, "Bob", null).getName());
        assertEquals("Paul", t.add("Paul", 1910, "Loly", "Erich").getName());
        assertEquals("Samanta", t.add("Samanta", 1940, "Paul", "Elise").getName());
        assertEquals("David", t.add("David", 1960, null, "Samanta").getName());
        assertEquals("Clark", t.add("Clark", 1980, "David", null).getName());
    }

    @Test
    void findByName() {
        FamilyTree t = new FamilyTree();
        t.add("Anna", 1900, null, null);
        t.add("Bob", 1900, null, null);
        assertEquals(1900, t.findByName("Anna").getAge());
        assertEquals(1900, t.findByName("Bob").getAge());
        assertThrows(IllegalArgumentException.class, () -> t.findByName("Unknown"));
    }


}