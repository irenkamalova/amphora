package com.kamalova.amphora;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LinkedFamilyNode extends FamilyNode {

    @Setter
    private LinkedFamilyNode previous;
    @Setter
    private LinkedFamilyNode next;
    @Getter
    private List<LinkedFamilyNode> parents = null;

    public LinkedFamilyNode(FamilyNode node) {
        super(node.getGeneration(), node.getName(), node.getAge());

        FamilyNode[] parents = node.getParents();
        for (FamilyNode parent : parents) {
            if (parent != null) {
                if (this.parents == null) {
                    this.parents = new ArrayList<>();
                }
                this.parents.add(new LinkedFamilyNode(parent));
            }
        }
    }
}
