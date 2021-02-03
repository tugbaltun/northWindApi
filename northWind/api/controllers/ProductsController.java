package com.example.northWind.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.northWind.business.abstracts.IProductService;
import com.example.northWind.entities.concretes.Product;

@RestController
@RequestMapping("/api/v1")
public class ProductsController {
	
	IProductService productService;
	public IProductService getProductService() {
        return productService ;
    }

    @Autowired
    public void setProductService(IProductService productService) {
        this.productService = productService;
    }
    
	@GetMapping("/products")
	public List<Product> getAll(){
		return productService.getAll();
	}
	
	@GetMapping("/products/{id}")
	public Optional<Product> getById(@PathVariable(value="id") Integer productId){
		return productService.getById(productId);
	}
	
	@PostMapping("/products")
	public Map<String, Product > add(@RequestBody Product product) {
		return productService.add(product);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> update(@PathVariable(value="id") Integer productId, 
			@RequestBody Product product) throws Exception {
		return productService.update(productId, product);
	}
	
	@DeleteMapping("products/{id}")
	public Map<String, Boolean> delete(@PathVariable(value="id") Integer productId) throws Exception{
		
		return productService.delete(productId);
	}
}


