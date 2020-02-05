package com.kamalova.amphora;

public class Test {

    public static void main(String[] args) {
        FamilyNode r = new FamilyNode(100, "Anna", 1990);
        FamilyTree t = new FamilyTree(r);

        t.add("Father", 1970, 100, 1);
        t.add("Mother", 1970, 100, 2);
        t.add("Kid0", 2001, 100, 0);
        t.add("Kid1", 2002, 100, 0);

        t.printTree();

    }
}
