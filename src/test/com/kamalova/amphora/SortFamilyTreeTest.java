package com.kamalova.amphora;

import static org.junit.jupiter.api.Assertions.*;

import com.kamalova.amphora.dao.model.FamilyNode;
import com.kamalova.amphora.dao.FamilyTree;
import com.kamalova.amphora.utils.SortFamilyTreeUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class SortFamilyTreeTest {

    @Test
    @DisplayName("When sort three then data is correct")
    void testCorrectSorting() {
        FamilyTree t = TestUtils.createFamilyTree();

        // Q3 - sort tree in ascending order:
        List<FamilyNode> sortedAscendingOrder = SortFamilyTreeUtils.sort(t, Integer::compareTo);

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
        List<FamilyNode> sortedDescendingOrder = SortFamilyTreeUtils.sort(t,
                (Integer x, Integer y) -> Integer.compare(x, y) * (-1));

        Collections.reverse(sortedAscendingOrder);
        assertIterableEquals(sortedAscendingOrder, sortedDescendingOrder);
        assertEquals(1960, sortedDescendingOrder.get(0).getAge());
    }
}
