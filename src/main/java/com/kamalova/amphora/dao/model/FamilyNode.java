package com.kamalova.amphora.dao.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FamilyNode {
    private String name;
    // year of birth
    private int age;
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

    /*
    Lombok generated
     */
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof FamilyNode)) return false;
        final FamilyNode other = (FamilyNode) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        if (this.getAge() != other.getAge()) return false;
        if (!java.util.Arrays.deepEquals(this.getParents(), other.getParents())) return false;
        final Object this$kids = this.getKids();
        final Object other$kids = other.getKids();
        if (this$kids == null ? other$kids != null : !this$kids.equals(other$kids)) return false;
        if (this.getGeneration() != other.getGeneration()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FamilyNode;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        result = result * PRIME + this.getAge();
        final Object $kids = this.getKids();
        result = result * PRIME + ($kids == null ? 43 : $kids.hashCode());
        result = result * PRIME + this.getGeneration();
        return result;
    }
}
