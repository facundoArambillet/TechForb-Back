package com.TF.TechForb.controller;

import com.TF.TechForb.model.User.User;
import com.TF.TechForb.model.UserDocumentType.UserDocumentType;
import com.TF.TechForb.service.UserDocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-document-type")
public class UserDocumentTypeController {
    @Autowired
    private UserDocumentTypeService userDocumentTypeService;
    @GetMapping
    public List<UserDocumentType> getAll() {
        return userDocumentTypeService.getAll();
    }
    @GetMapping("/{idType}")
    public UserDocumentType getById(@PathVariable("idType") Long idType) {
        return userDocumentTypeService.getById(idType);
    }
    @GetMapping("/type/{type}")
    public UserDocumentType getByType(@PathVariable("type") String type) {
        return userDocumentTypeService.getByType(type);
    }
    @PostMapping
    public UserDocumentType createDocumentType(UserDocumentType userDocumentType) {
        return userDocumentTypeService.createDocumentType(userDocumentType);
    }
    @DeleteMapping
    public UserDocumentType deleteDocumentType(Long id) {
        return userDocumentTypeService.deleteDocumentType(id);
    }
}
