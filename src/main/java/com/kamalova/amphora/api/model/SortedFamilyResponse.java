package com.kamalova.amphora.api.model;

import com.kamalova.amphora.dao.model.FamilyNode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SortedFamilyResponse {
    private List<FamilyNode> familyNodes;
}
