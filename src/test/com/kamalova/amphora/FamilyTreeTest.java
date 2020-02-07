package com.kamalova.amphora;

import com.kamalova.amphora.dao.FamilyTree;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTreeTest {

    @Test
    @DisplayName("When add to tree then data is correct")
    void testCorrectInjection() {
        FamilyTree t = new FamilyTree();
        // A, B - no kids
        assertEquals("A", t.save("A", 1900, null, null).getName());
        assertEquals("B", t.save("B", 1900, null, null).getName());
        // A1, B1 - two kids
        assertEquals("A1", t.save("A1", 1900, null, null).getName());
        assertEquals("B1", t.save("B1", 1900, null, null).getName());
        assertEquals("aA1", t.save("aA1", 1920, "A1", "B1").getName());
        assertEquals("bA1", t.save("bA1", 1922, "A1", "B1").getName());

        assertEquals("Anna", t.save("Anna", 1900, null, null).getName());
        assertEquals("Bob", t.save("Bob", 1900, null, null).getName());
        assertEquals("Loly", t.save("Loly", 1890, null, null).getName());
        assertEquals("Erich", t.save("Erich", 1890, null, null).getName());
        assertEquals("Elise", t.save("Elise", 1920, "Anna", "Bob").getName());
        assertEquals("Tom", t.save("Tom", 1917, null, "Anna").getName());
        assertEquals("Linda", t.save("Linda", 1917, "Bob", null).getName());
        assertEquals("Paul", t.save("Paul", 1910, "Loly", "Erich").getName());
        assertEquals("Samanta", t.save("Samanta", 1940, "Paul", "Elise").getName());
        assertEquals("David", t.save("David", 1960, null, "Samanta").getName());
        assertEquals("Clark", t.save("Clark", 1980, "David", null).getName());
    }

    @Test
    @DisplayName("When add two the same names then fail with exception")
    void testIncorrectNameInjection() {
        FamilyTree t = new FamilyTree();
        // A, B - no kids
        assertEquals("A", t.save("A", 1900, null, null).getName());
        assertThrows(IllegalArgumentException.class, () -> t.save("A", 1900, null, null));
    }

    @Test
    @DisplayName("When add with no exist parent then fail with exception")
    void testIncorrectParentInjection() {
        FamilyTree t = new FamilyTree();
        assertThrows(IllegalArgumentException.class, () -> t.save("A", 1900, "B", null));
        assertThrows(IllegalArgumentException.class, () -> t.save("A", 1900, null, "C"));
    }

    @Test
    void testCorrectPossibilityFindNode() {
        FamilyTree t = new FamilyTree();
        t.save("Anna", 1900, null, null);
        t.save("Bob", 1900, null, null);
        assertEquals(1900, t.findByName("Anna").getAge());
        assertEquals(1900, t.findByName("Bob").getAge());
        assertNull(t.findByName("Unknown"));
    }
}