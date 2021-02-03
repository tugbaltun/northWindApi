package com.example.northWind.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.northWind.business.abstracts.IProductService;
import com.example.northWind.dataAccess.concretes.ProductRepository;
import com.example.northWind.entities.concretes.Product;

@Service
public class ProductManager implements IProductService{
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAll() {
		
		return productRepository.findAll();
	}
	
	@Override
	public Optional<Product> getById(Integer productId) {
		
		return productRepository.findById(productId);
	}

	@Override
	public Map<String, Product >  add(Product product) {
		String message ;
		int counter=0;
		for(int i =0; i< productRepository.findAll().size(); i++) {
			if(product.getCategoryId() == productRepository.findAll().get(i).getCategoryId()) {
				counter++;
			}
		}
		
		if(product.getProductName().length()<2 || counter>10) {
			message = "ProductName en az 2 karakterden oluşmalı ve Kategori tablosuna 10'dan fazla aynı tür ürün eklenemez!";
		}

		else {
			productRepository.save(product);
			message = "Product başarıyla eklendi";
		}
		Map<String, Product >  response = new HashMap<>();
		response.put(message, product);
		return response;
	}
	
	@Override
	public ResponseEntity<Product> update(Integer productId, Product product) throws Exception{
		Product producttoUpdate = productRepository.findById(productId)
				.orElseThrow(()->new Exception("No product with id:"+productId));
					
		producttoUpdate.setProductName(product.getProductName());
		producttoUpdate.setUnitPrice(product.getUnitPrice());
		productRepository.save(producttoUpdate);
		Product uptatedProduct = productRepository.save(producttoUpdate);
		return ResponseEntity.ok(uptatedProduct);
	}
	
	@Override
	public Map<String, Boolean> delete(Integer productId) throws Exception{
		
		Product productDelete = productRepository.findById(productId)
		.orElseThrow(()->new Exception("No product with id: "+productId));
		productRepository.delete(productDelete);
		Map<String, Boolean> response = new HashMap<>();
		response.put("The given product is deleted", Boolean.TRUE);
		return response;
	}
}
