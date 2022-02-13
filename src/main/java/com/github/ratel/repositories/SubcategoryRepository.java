package com.github.ratel.repositories;

import com.github.ratel.entity.Subcategory;
import com.github.ratel.entity.enums.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

}
