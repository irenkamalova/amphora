package com.kamalova.amphora.service;

import com.kamalova.amphora.dao.model.FamilyNode;

import java.util.List;

public interface FamilyTreeService {
    // Enter individual family nodes
    void addNode(String name, int age,
                 String father, String mother);

    // To get sorted family list for ascending and descending
    List<FamilyNode> getSorted(boolean ascending);

}
