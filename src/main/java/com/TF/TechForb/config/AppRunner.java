package com.TF.TechForb.config;

import com.TF.TechForb.model.MenuItem.MenuItem;
import com.TF.TechForb.model.UserDocumentType.UserDocumentType;
import com.TF.TechForb.service.MenuItemService;
import com.TF.TechForb.service.UserDocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppRunner implements CommandLineRunner {
    @Autowired
    private UserDocumentTypeService userDocumentTypeService;
    @Autowired
    private MenuItemService menuItemService;

    @Override
    public void run(String... args) throws Exception {
        UserDocumentType userDocumentTypeDni = new UserDocumentType();
        userDocumentTypeDni.setType("DNI");
        UserDocumentType userDocumentTypeCuil = new UserDocumentType();
        userDocumentTypeCuil.setType("CUIL");

        MenuItem start = new MenuItem();
        MenuItem cards = new MenuItem();
        MenuItem loans = new MenuItem();
        MenuItem operations = new MenuItem();
        MenuItem weOfferYou = new MenuItem();
        MenuItem insurances = new MenuItem();
        MenuItem points = new MenuItem();
        MenuItem help = new MenuItem();
        MenuItem logOut = new MenuItem();

        start.setIcon("bi bi-house-door");
        cards.setIcon("bi bi-wallet");
        loans.setIcon("bi bi-cash");
        operations.setIcon("bi bi-arrow-left-right");
        weOfferYou.setIcon("bi bi-tag");
        insurances.setIcon("bi bi-shield");
        points.setIcon("bi bi-gift");
        help.setIcon("bi bi-question-circle");
        logOut.setIcon("bi bi-box-arrow-in-right");
        start.setTitle("Inicio");
        cards.setTitle("Tarjetas");
        loans.setTitle("Prestamos");
        operations.setTitle("Operaciones");
        weOfferYou.setTitle("Te ofrecemos");
        insurances.setTitle("Seguros");
        points.setTitle("Puntos");
        help.setTitle("Ayuda");
        logOut.setTitle("Cerrar Sesion");

        userDocumentTypeService.createDocumentType(userDocumentTypeDni);
        userDocumentTypeService.createDocumentType(userDocumentTypeCuil);

        menuItemService.createItem(start);
        menuItemService.createItem(cards);
        menuItemService.createItem(loans);
        menuItemService.createItem(operations);
        menuItemService.createItem(weOfferYou);
        menuItemService.createItem(insurances);
        menuItemService.createItem(points);
        menuItemService.createItem(help);
        menuItemService.createItem(logOut);

    }
}
