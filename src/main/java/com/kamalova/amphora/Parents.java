package com.kamalova.amphora;

public class Parents implements Pair<FamilyNode, FamilyNode> {
    private FamilyNode father;
    private FamilyNode mother;

    public Parents() {}

    public FamilyNode getFather() {
        return father;
    }

    public void setFather(FamilyNode father) {
        this.father = father;
    }

    public FamilyNode getMother() {
        return mother;
    }

    public void setMother(FamilyNode mother) {
        this.mother = mother;
    }

    @Override
    public String toString() {
        return "Parents{" +
                "father=" + getFatherName() +
                ", mother=" + getMotherName() +
                '}';
    }

    private String getFatherName() {
        return father != null ? father.getName() + ", " + father.getAge() : "Unknown";
    }

    private String getMotherName() {
        return mother != null ? mother.getName() + ", " + mother.getAge() : "Unknown";
    }
}
