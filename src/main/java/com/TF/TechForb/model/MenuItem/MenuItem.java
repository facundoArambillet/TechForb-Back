package com.TF.TechForb.model.MenuItem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Long idItem;
    @Column(nullable = false)
    private String icon;
    @Column(nullable = false)
    private String title;
}
