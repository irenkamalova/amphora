package com.kamalova.amphora.api.controller;

import com.kamalova.amphora.api.model.FamilyNodeRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface Controller {
    @ApiOperation(value = "Add family node", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Node successfully added"),
            @ApiResponse(code = 404, message = "Bad Request")
    })
    @PostMapping(value = "/",
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    ResponseEntity<Object> add(@RequestBody FamilyNodeRequest familyNodeRequest);


    @ApiOperation(value = "To get sorted family list for ascending and descending")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 404, message = "Bad Request")
    })
    @GetMapping(value = "/",
            produces = {"application/json", "application/xml"})
    ResponseEntity<Object> get(@RequestParam(value = "ascending") boolean ascending);

}
