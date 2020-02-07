package com.kamalova.amphora.api.controller;

import com.kamalova.amphora.api.model.ApiError;
import com.kamalova.amphora.api.model.FamilyNodeRequest;
import com.kamalova.amphora.api.model.SortedFamilyResponse;
import com.kamalova.amphora.dao.model.FamilyNode;
import com.kamalova.amphora.service.FamilyTreeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("Family Tree API")
@RequestMapping(name = "amphora", path = "/amphora")
public class ControllerImpl implements Controller {

    @Autowired
    private FamilyTreeService familyTreeService;

    public ResponseEntity<Object> add(FamilyNodeRequest familyNodeRequest) {
        if (StringUtils.isEmpty(familyNodeRequest.getName())) {
            return ResponseEntity.badRequest().body(new ApiError("InvalidNodeName"));
        }
        try {
            FamilyNode familyNode = familyTreeService.addNode(familyNodeRequest.getName(),
                    familyNodeRequest.getAge(),
                    familyNodeRequest.getFather(),
                    familyNodeRequest.getMother());
            return ResponseEntity.status(200).body(familyNode);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiError(e.getMessage()));
        }
    }

    public ResponseEntity<Object> get(boolean ascending) {
        try {
            List<FamilyNode> result = familyTreeService.getSortedTree(ascending);
            return ResponseEntity.ok().header("OK")
                    .body(new SortedFamilyResponse(result));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(new ApiError(e.getMessage()));
        }
    }
}