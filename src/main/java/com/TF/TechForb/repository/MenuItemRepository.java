package com.TF.TechForb.repository;

import com.TF.TechForb.model.MenuItem.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

    Optional<MenuItem> findByTitle(String title);
}
