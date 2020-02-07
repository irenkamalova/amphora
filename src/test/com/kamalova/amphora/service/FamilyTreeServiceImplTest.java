package com.kamalova.amphora.service;

import com.kamalova.amphora.dao.FamilyTreeRepository;
import com.kamalova.amphora.dao.model.FamilyNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
public class FamilyTreeServiceImplTest {

    @MockBean(name = "repository")
    FamilyTreeRepository familyTreeRepository;

    @Autowired
    private FamilyTreeService familyTreeService;

    @Test
    public void whenGetTreeThenSuccess() {
        FamilyNode mockNode = new FamilyNode(0, "name", 1900);
        FamilyNode mockNode2 = new FamilyNode(0, "name2", 1902);
        List<FamilyNode> mockedList = Arrays.asList(mockNode, mockNode2);

        given(familyTreeRepository.getSortedNodes(true)).willReturn(mockedList);

        List<FamilyNode> sortedTree = familyTreeService.getSortedTree(true);

        assertEquals(2, sortedTree.size());
        assertEquals(1900, sortedTree.iterator().next().getAge());

        Collections.reverse(mockedList);
        given(familyTreeRepository.getSortedNodes(false)).willReturn(mockedList);
        sortedTree = familyTreeService.getSortedTree(false);

        assertEquals(2, sortedTree.size());
        assertEquals(1902, sortedTree.iterator().next().getAge());
    }

    @Test
    public void whenAddNodeThenSuccess() {

        String name = "testName";
        int age = 1000;

        FamilyNode mockNode = new FamilyNode(0, name, age);

        given(familyTreeRepository.save(name, age, null, null))
                .willReturn(mockNode);

        FamilyNode result = familyTreeService
                .addNode(name, age, null, null);
        assertEquals(0, result.getGeneration());
        assertEquals(name, result.getName());
        assertEquals(age, result.getAge());
    }

    @TestConfiguration
    static class FamilyTreeServiceTestConfiguration {
        @Bean
        public FamilyTreeService familyTreeService() {
            return new FamilyTreeServiceImpl();
        }
    }

}