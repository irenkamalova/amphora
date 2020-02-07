package com.kamalova.amphora.utils;

import com.kamalova.amphora.model.FamilyNode;
import com.kamalova.amphora.service.FamilyTree;
import com.kamalova.amphora.model.LinkedFamilyNode;

import java.util.*;

public class MissingMemberHandler {

    /*
    An algorithm that can insert a new family member ( assume we found a long missing member!) in
    to the correct place of a sorted (ascending) list based on age with time complexity better O(n)

    Yes, we can do it in provided sorted linked list:
     -> we use the additional information that we have in familyNode - children
     We can change our range of search correct nodes by this information

     Complexity will be O(M + H),
     there M - maximum number of nodes on the one level
     and   H - maximum number of generations (height of the family tree)

     We need here extended structure - not only get parent, but also
     now, what there the next element, so we use the new structure
     LinkedFamilyNode

     One more idea: to store list in AVL data structure - so, we can insert data by complexity of O(N)

     */

    public static LinkedFamilyNode addMissingMember(LinkedFamilyNode sortedNodeHead,
                                                    FamilyNode missingNode) {

        LinkedFamilyNode node = new LinkedFamilyNode(missingNode);
        if (sortedNodeHead == null) {
            sortedNodeHead = node;
            return sortedNodeHead;
        }
        LinkedFamilyNode start = sortedNodeHead;
        LinkedFamilyNode end = null;
        while (start.getNext() != null) {

            if (start.getAge() >= node.getAge()) {
                if (start.equals(sortedNodeHead)) {
                    node.setNext(sortedNodeHead);
                    sortedNodeHead.setPrevious(node);
                    return node;
                } else {
                    node.setPrevious(start.getPrevious());
                    start.getPrevious().setNext(node);
                    node.setNext(start);
                    start.setPrevious(node);
                    return sortedNodeHead;
                }
            }
            if (end != null) {
                if (end.getAge() <= node.getAge()) {
                    node.setNext(end.getNext());
                    node.setPrevious(end);
                    end.setNext(node);
                    return sortedNodeHead;
                } else if (start.getGeneration() == end.getGeneration()) {
                    return insertThroughGeneration(sortedNodeHead, start, end, node);
                }
            }

            List<LinkedFamilyNode> linkedKids = start.getLinkedKids();
            if (linkedKids.size() == 0) {
                start = start.getNext();
            } else {
                LinkedFamilyNode nextStart = start;
                for (LinkedFamilyNode kid : linkedKids) {
                    if (kid.getAge() == node.getAge()) {
                        node.setNext(kid.getNext());
                        kid.setPrevious(node);
                        return sortedNodeHead;
                    }
                    if (kid.getAge() < node.getAge()) {
                        nextStart = kid;
                    }
                    if (kid.getAge() > node.getAge()) {
                        end = kid;
                    }
                }
                if (nextStart == start) {
                    start = start.getNext();
                } else {
                    start = nextStart;
                }
            }
        }
        // start.getNext == null -> : add to the end
        node.setPrevious(start);
        start.setNext(node);
        return sortedNodeHead;
    }

    private static LinkedFamilyNode insertThroughGeneration(LinkedFamilyNode head,
                                                            LinkedFamilyNode start,
                                                            LinkedFamilyNode end,
                                                            LinkedFamilyNode node) {
        while (start.getAge() < node.getAge() && !start.equals(end)) {
            start = start.getNext();
        }
        node.setPrevious(start.getPrevious());
        start.getPrevious().setNext(node);
        start.setPrevious(node);
        node.setNext(start);
        return head;
    }

    public static LinkedFamilyNode sortToExtendedStructure(FamilyTree familyTree,
                                                           Comparator<Integer> comparator) {
        List<FamilyNode> sorted = SortFamilyTree.sort(familyTree, comparator);

        Iterator<FamilyNode> iterator = sorted.iterator();
        LinkedFamilyNode head = null;
        LinkedFamilyNode current = null;
        LinkedFamilyNode next = null;

        Map<FamilyNode, LinkedFamilyNode> map = new HashMap<>();

        if (iterator.hasNext()) {
            FamilyNode node = iterator.next();
            current = new LinkedFamilyNode(node);
            map.put(node, current);
            head = current;
        }

        while (iterator.hasNext()) {
            FamilyNode node = iterator.next();
            next = new LinkedFamilyNode(node);
            map.put(node, next);
            current.setNext(next);
            next.setPrevious(current);
            current = next;
        }

        for (FamilyNode node : map.keySet()) {
            List<FamilyNode> kids = node.getKids();
            LinkedFamilyNode linkedFamilyNode = map.get(node);
            for (FamilyNode kid : kids) {
                LinkedFamilyNode linkedKid = map.get(kid);
                linkedFamilyNode.addLinkedKid(linkedKid);
            }
        }

        return head;
    }
}
