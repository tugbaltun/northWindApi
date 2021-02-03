package com.example.northWind.dataAccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.northWind.entities.concretes.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
