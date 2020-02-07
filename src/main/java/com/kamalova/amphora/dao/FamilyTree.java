package com.kamalova.amphora.dao;

import com.kamalova.amphora.dao.model.FamilyNode;
import com.kamalova.amphora.utils.SortFamilyTreeUtils;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
public class FamilyTree implements FamilyTreeRepository {
    @Getter
    private final List<FamilyNode> roots = new ArrayList<>();

    public FamilyTree() {
    }

    @Override
    public FamilyNode save(String name, int age,
                           String father, String mother) {
        FamilyNode node;
        if (StringUtils.isEmpty(father) && StringUtils.isEmpty(mother)) {
            node = new FamilyNode(0, name, age);
            roots.add(node);
            return node;
        } else if (father != null && mother != null) {
            FamilyNode fn = findByName(father);
            FamilyNode mn = findByName(mother);
            if (fn.getGeneration() != mn.getGeneration()) {
                throw new IllegalArgumentException("Incorrect parents");
            }
            node = new FamilyNode(fn.getGeneration() + 1, name, age);
            fn.addKid(node);
            mn.addKid(node);
            FamilyNode[] parents = node.getParents();
            parents[0] = fn;
            parents[1] = mn;
            return node;
        } else if (!StringUtils.isEmpty(mother)) {
            return addToParent(name, age, mother);
        } else {
            return addToParent(name, age, father);
        }
    }

    private FamilyNode addToParent(String name, int age, String parent) {
        if (parent == null) {
            throw new IllegalArgumentException("Error with " + name);
        }
        FamilyNode parentNode = findByName(parent);
        FamilyNode node = new FamilyNode(parentNode.getGeneration() + 1, name, age);
        FamilyNode[] parents = node.getParents();
        parents[0] = parentNode;
        parentNode.addKid(node);
        return node;
    }

    @Override
    public FamilyNode findByName(String name) {
        // BFS to search node - O(N) complexity
        Set<FamilyNode> seen = new HashSet<>();
        Queue<FamilyNode> queue = new LinkedList<>(roots);

        while (!queue.isEmpty()) {
            FamilyNode node = queue.poll();
            if (node.getName().equals(name)) {
                return node;
            }
            List<FamilyNode> kids = node.getKids();
            kids.forEach(kid -> {
                if (!seen.contains(kid)) {
                    queue.add(kid);
                    seen.add(kid);
                }
            });
            seen.add(node);
        }
        throw new IllegalArgumentException("Parent " + name + " not founded");
    }

    @Override
    public List<FamilyNode> getSortedNodes(boolean ascending) {
        Comparator<Integer> comparator = Integer::compareTo;
        return ascending ? SortFamilyTreeUtils.sort(this, comparator) :
                SortFamilyTreeUtils.sort(this, comparator.reversed());
    }
}
