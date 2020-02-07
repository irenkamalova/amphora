package com.kamalova.amphora.dao;


import com.kamalova.amphora.dao.model.FamilyNode;

import java.util.List;

public interface FamilyTreeRepository {
    FamilyNode save(String name, int age,
                    String father, String mother);

    FamilyNode findByName(String name);

    List<FamilyNode> getSortedNodes(boolean ascending);

}