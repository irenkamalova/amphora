package com.kamalova.amphora.service;

import com.kamalova.amphora.dao.FamilyTreeRepository;
import com.kamalova.amphora.dao.model.FamilyNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyTreeServiceImpl implements FamilyTreeService {

    @Autowired
    FamilyTreeRepository familyTreeRepository;

    @Override
    public FamilyNode addNode(String name, int age, String father, String mother) {
        return familyTreeRepository.save(name, age, father, mother);
    }

    @Override
    public List<FamilyNode> getSortedTree(boolean ascending) {
        return familyTreeRepository.getSortedNodes(ascending);
    }
}
