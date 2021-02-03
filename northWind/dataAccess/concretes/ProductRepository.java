package com.example.northWind.dataAccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.northWind.entities.concretes.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{

	
}
