package com.kamalova.amphora.model;

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
    private List<LinkedFamilyNode> linkedKids = new ArrayList<>();

    public LinkedFamilyNode(FamilyNode node) {
        super(node.getGeneration(), node.getName(), node.getAge());
    }

    public void addLinkedKid(LinkedFamilyNode kid) {
        linkedKids.add(kid);
    }
}
