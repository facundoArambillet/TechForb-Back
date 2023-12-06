package com.TF.TechForb.config;

import com.TF.TechForb.model.UserDocumentType.UserDocumentType;
import com.TF.TechForb.service.UserDocumentTypeService;
import com.TF.TechForb.service.UserDocumentTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppRunner implements CommandLineRunner {
    @Autowired
    private UserDocumentTypeService userDocumentTypeService;

    @Override
    public void run(String... args) throws Exception {
        UserDocumentType userDocumentTypeDni = new UserDocumentType();
        userDocumentTypeDni.setType("DNI");
        UserDocumentType userDocumentTypeCuil = new UserDocumentType();
        userDocumentTypeCuil.setType("CUIL");

        userDocumentTypeService.createDocumentType(userDocumentTypeDni);
        userDocumentTypeService.createDocumentType(userDocumentTypeCuil);
    }
}
