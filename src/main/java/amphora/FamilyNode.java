package com.kamalova.amphora;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FamilyNode {
    private final double id;

    private String name;
    // year of birth
    private int age;
    private final Parents parents = new Parents();
    private final List<FamilyNode> kids = new ArrayList<>();

    public FamilyNode(double id) {
        this.id = id;
    }

    public FamilyNode(double id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public FamilyNode addFather(FamilyNode father) {
        parents.setFather(father);
        return this;
    }

    public FamilyNode addMother(FamilyNode mother) {
        parents.setMother(mother);
        return this;
    }

    public FamilyNode addKid(FamilyNode familyNode) {
        kids.add(familyNode);
        return this;
    }

    public FamilyNode getFather() {
        return parents.getFather();
    }

    public FamilyNode getMother() {
        return parents.getMother();
    }

    public FamilyNode getFirstKid() {
        return kids.iterator().next();
    }

    @Override
    public String toString() {
        return "FamilyNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", parents=" + parents +
                ", kids=" + printKids() +
                '}';
    }

    private String printKids() {
        StringBuilder sb = new StringBuilder();
        kids.forEach(kid ->
                sb.append(kid.name)
                        .append(" ")
                        .append(kid.age)
                        .append(";"));
        return sb.toString();
    }

    public boolean hasKids() {
        return !kids.isEmpty();
    }
}
