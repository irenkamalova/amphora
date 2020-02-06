package com.kamalova.amphora;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FamilyNode {
    private String name;
    // year of birth
    private int age;
    //private final Parents parents = new Parents();
    private final FamilyNode[] parents = new FamilyNode[2];
    private final List<FamilyNode> kids = new ArrayList<>();
    private int generation;

    public FamilyNode(int generation, String name, int age) {
        this.generation = generation;
        this.name = name;
        this.age = age;
    }

    public FamilyNode addKid(FamilyNode familyNode) {
        kids.add(familyNode);
        return this;
    }

    @Override
    public String toString() {
        return "FamilyNode{" +
                "gen=" + generation +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", parents=" + printParents() +
                printKids() +
                '}';
    }

    private String printParents() {
        StringBuilder sb = new StringBuilder();
        for (FamilyNode parent : parents) {
            if (parent != null) {
                sb.append(parent.name)
                        .append(", ")
                        .append(parent.age)
                        .append(";");
            } else {
                sb.append(" --;");
            }
        }
        return sb.toString();
    }

    private String printKids() {
        StringBuilder sb = new StringBuilder();
        if (kids.size() > 0) {
            sb.append(" kids=");
            kids.forEach(kid ->
                    sb.append(kid.name)
                            .append(" ")
                            .append(kid.age)
                            .append(";"));
        }
        return sb.toString();
    }

    public boolean hasKids() {
        return !kids.isEmpty();
    }
}
