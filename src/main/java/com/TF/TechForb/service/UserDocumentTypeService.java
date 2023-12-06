package com.TF.TechForb.service;

import com.TF.TechForb.model.UserDocumentType.UserDocumentType;

public interface UserDocumentTypeService {
    UserDocumentType getById(Long id);
    UserDocumentType getByType(String type);
    UserDocumentType createDocumentType(UserDocumentType userDocumentType);
    UserDocumentType deleteDocumentType(Long id);
}
