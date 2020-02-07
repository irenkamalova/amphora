package com.kamalova.amphora;

import java.util.*;

public class MissingMemberService {

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

    public static LinkedList<LinkedFamilyNode> addMissingMember(LinkedList<LinkedFamilyNode> sortedNodes,
                                                                FamilyNode missingNode) {

        LinkedFamilyNode node = new LinkedFamilyNode(missingNode);
        if (sortedNodes.isEmpty()) {
            sortedNodes.addFirst(node);
            return sortedNodes;
        }
        LinkedFamilyNode start = sortedNodes.getFirst();
        LinkedFamilyNode end = sortedNodes.getLast();
        while (!start.equals(end)) {

            if (start.getAge() >= node.getAge()) {
                node.setPrevious(start.getPrevious());
                node.setNext(start);
                start.setPrevious(node);
                return sortedNodes;
            }
            if (end.getAge() <= node.getAge()) {
                node.setNext(end.getNext());
                node.setPrevious(end);
                end.setNext(node);
                return sortedNodes;
            }

            List<LinkedFamilyNode> parents = end.getLinkedParents();
            if (parents == null || start.getGeneration() == end.getGeneration()) {
                end = end.getPrevious();
                // and here we start to search throw the M nodes without parents
            } else {
                for (LinkedFamilyNode parent : parents) {
                    if (parent.getAge() == node.getAge()) {
                        node.setNext(parent.getNext());
                        parent.setPrevious(node);
                        return sortedNodes;
                    }
                    if (parent.getAge() > node.getAge() && parent.getAge() <= end.getAge()) {
                        end = parent;
                    }
                    if (parent.getAge() < node.getAge() && parent.getAge() >= start.getAge()) {
                        start = parent;
                        if (start.getGeneration() != end.getGeneration()) {
                            end = end.getPrevious();
                        }
                    }
                }
            }
        }
        // start == end:
        node.setPrevious(start.getPrevious());
        node.setNext(start);
        start.setPrevious(node);
        return sortedNodes;
    }

    public static LinkedList<LinkedFamilyNode> sortToExtendedStructure(FamilyTree familyTree,
                                                                       Comparator<Integer> comparator) {
        List<FamilyNode> sorted = SortFamilyTree.sort(familyTree, comparator);
        LinkedList<LinkedFamilyNode> list = new LinkedList<>();

        Iterator<FamilyNode> iterator = sorted.iterator();
        LinkedFamilyNode current = null;
        LinkedFamilyNode next = null;

        if (iterator.hasNext()) {
            current = new LinkedFamilyNode(iterator.next());
            list.add(current);
        } else {
            return list;
        }

        while (iterator.hasNext()) {
            next = new LinkedFamilyNode(iterator.next());
            current.setNext(next);
            next.setPrevious(current);
            current = next;
            list.add(current);
        }

        return list;
    }
}
