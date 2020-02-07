package com.kamalova.amphora.api.controller;

import com.kamalova.amphora.api.controller.model.ApiError;
import com.kamalova.amphora.api.controller.model.FamilyNodeRequest;
import com.kamalova.amphora.model.FamilyNode;
import com.kamalova.amphora.service.FamilyTreeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "realm", path = "/service/user")
@Api(value = "Realm Management System")
public class ControllerImpl implements Controller {

    @Autowired
    private FamilyTreeService familyTreeService;

    public ResponseEntity<Object> add(FamilyNodeRequest familyNodeRequest) {
        if (StringUtils.isEmpty(familyNodeRequest.getName())) {
            return ResponseEntity.badRequest().body(new ApiError("InvalidNodeName"));
        }
        try {
            familyTreeService.addNode(familyNodeRequest.getName(),
                    familyNodeRequest.getAge(),
                    familyNodeRequest.getFather(),
                    familyNodeRequest.getMother());
            return ResponseEntity.status(200).body("Successfully added node");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiError("Bad request"));
        }
    }

    public ResponseEntity<Object> get(boolean ascending) {
        try {
            List<FamilyNode> result = familyTreeService.getSorted(ascending);
            return ResponseEntity.ok().header("OK").body(result);
        } catch (Exception e) {
            return ResponseEntity.status(404)
                    .body(new ApiError("RealmNotFound"));
        }
    }
}