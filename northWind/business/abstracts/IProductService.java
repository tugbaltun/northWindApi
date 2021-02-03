package com.example.northWind.business.abstracts;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import com.example.northWind.entities.concretes.Product;

public interface IProductService {

	List<Product> getAll();
	Optional<Product> getById(Integer productId);
	public Map<String, Product > add(Product product);
	public ResponseEntity<Product> update(Integer productId,Product product) throws Exception;
	public Map<String,Boolean> delete(Integer productId) throws Exception;	
}
