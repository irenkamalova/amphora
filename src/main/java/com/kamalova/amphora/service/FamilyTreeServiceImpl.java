package com.kamalova.amphora.service;

import com.kamalova.amphora.model.FamilyNode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyTreeServiceImpl implements FamilyTreeService {


    @Override
    public void addNode(String name, int age, String father, String mother) {

    }

    @Override
    public List<FamilyNode> getSorted(boolean ascending) {
        return null;
    }
}
