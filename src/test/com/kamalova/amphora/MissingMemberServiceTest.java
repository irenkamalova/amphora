package com.kamalova.amphora;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class MissingMemberServiceTest {

    @Test
    @DisplayName("When add missing node then should be on correct place")
    public void testAddingMissingNumber() {
        FamilyTree t = TestUtils.createFamilyTree();

        LinkedList<LinkedFamilyNode> sortedList = MissingMemberService.sortToExtendedStructure(t, Integer::compareTo);

        assertEquals(1889, sortedList.get(0).getAge());
        assertEquals(1890, sortedList.get(1).getAge());
        assertEquals(1900, sortedList.get(2).getAge());
        assertEquals(1910, sortedList.get(3).getAge());
        assertEquals(1911, sortedList.get(4).getAge());
        assertEquals(1916, sortedList.get(5).getAge());
        assertEquals(1917, sortedList.get(6).getAge());
        assertEquals(1920, sortedList.get(7).getAge());
        assertEquals(1940, sortedList.get(8).getAge());
        assertEquals(1960, sortedList.get(9).getAge());

        FamilyNode familyNode = new FamilyNode(0, "Boris", 1901);
        MissingMemberService.addMissingMember(sortedList, familyNode);

        assertEquals(1889, sortedList.get(0).getAge());
        assertEquals(1890, sortedList.get(1).getAge());
        assertEquals(1900, sortedList.get(2).getAge());
        assertEquals(1901, sortedList.get(3).getAge());
        assertEquals(1910, sortedList.get(4).getAge());
        assertEquals(1911, sortedList.get(5).getAge());
        assertEquals(1916, sortedList.get(6).getAge());
        assertEquals(1917, sortedList.get(7).getAge());
        assertEquals(1920, sortedList.get(8).getAge());
        assertEquals(1940, sortedList.get(9).getAge());
        assertEquals(1960, sortedList.get(10).getAge());

    }


}