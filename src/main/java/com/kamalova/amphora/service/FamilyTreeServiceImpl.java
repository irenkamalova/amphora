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
    public void addNode(String name, int age, String father, String mother) {
        familyTreeRepository.save(name, age, father, mother);
    }

    @Override
    public List<FamilyNode> getSorted(boolean ascending) {
        return familyTreeRepository.getSortedNodes(ascending);
    }
}
