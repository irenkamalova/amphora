package com.kamalova.amphora;

import com.kamalova.amphora.dao.model.FamilyNode;
import com.kamalova.amphora.dao.FamilyTree;
import com.kamalova.amphora.dao.model.LinkedFamilyNode;
import com.kamalova.amphora.utils.MissingMemberHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MissingMemberHandlerTest {

    @Test
    @DisplayName("When add missing node then should be on correct place")
    public void testAddingMissingNumber() {
        FamilyTree t = TestUtils.createFamilyTree();

        LinkedFamilyNode head = MissingMemberHandler.sortToExtendedStructure(t, Integer::compareTo);
        LinkedFamilyNode node = head;
        List<Integer> correctValues = Arrays.asList(1889, 1890, 1900, 1910, 1911, 1916, 1917, 1920, 1940, 1960);
        for (Integer correctValue : correctValues) {
            assertEquals(correctValue, node.getAge());
            node = node.getNext();
        }

        head = MissingMemberHandler.addMissingMember(head, new FamilyNode(0, "Carl", 1901));
        node = head;
        List<Integer> corrected0 = Arrays.asList(1889, 1890, 1900, 1901, 1910, 1911, 1916, 1917, 1920, 1940, 1960);
        for (Integer correctedValue : corrected0) {
            assertEquals(correctedValue, node.getAge());
            node = node.getNext();
        }

        List<Integer> corrected1 = Arrays.asList(1888, 1889, 1890, 1900, 1901, 1910, 1911, 1916, 1917, 1920, 1940, 1960);
        head = MissingMemberHandler.addMissingMember(head, new FamilyNode(0, "Bred", 1888));
        node = head;
        for (Integer correctedValue : corrected1) {
            assertEquals(correctedValue, node.getAge());
            node = node.getNext();
        }

        List<Integer> corrected2 = Arrays.asList(1888, 1889, 1890, 1900, 1901, 1910, 1911, 1916, 1917, 1920, 1940, 1960, 1980);
        head = MissingMemberHandler.addMissingMember(head, new FamilyNode(2, "Tom", 1980));
        node = head;
        for (Integer correctedValue : corrected2) {
            assertEquals(correctedValue, node.getAge());
            node = node.getNext();
        }

        List<Integer> corrected3 = Arrays.asList(1888, 1889, 1890, 1900, 1901, 1910, 1911, 1916, 1917, 1918, 1920, 1940, 1960, 1980);
        head = MissingMemberHandler.addMissingMember(head, new FamilyNode(2, "Ken", 1918));
        node = head;
        for (Integer correctedValue : corrected3) {
            assertEquals(correctedValue, node.getAge());
            node = node.getNext();
        }
    }


}