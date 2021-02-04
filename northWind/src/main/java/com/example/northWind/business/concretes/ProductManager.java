package com.example.northWind.business.concretes;

import com.example.northWind.business.abstracts.IProductService;
import com.example.northWind.dataAccess.concretes.ProductRepository;
import com.example.northWind.entities.concretes.Product;
import com.example.northWind.exception.ApiErrorException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductManager implements IProductService {
  @Autowired private ProductRepository productRepository;

  @Override
  public List<Product> getAll() {
    if (productRepository.findAll().isEmpty()) {
      throw new ApiErrorException("Product cannot be found in the cache");
    }
    return productRepository.findAll();
  }

  @Override
  public Product getById(Integer productId) {
    return productRepository
        .findById(productId)
        .orElseThrow(() -> new ApiErrorException("No product with id:" + productId));
  }

  @Override
  public Map<String, Product> add(Product product) {
    String message;
    int counter = 0;
    for (int i = 0; i < productRepository.findAll().size(); i++) {
      if (product.getCategoryId() == productRepository.findAll().get(i).getCategoryId()) {
        counter++;
      }
    }

    if (product.getProductName().length() < 2 || counter > 10) {
      throw new ApiErrorException(
          "ProductName en az 2 karakterden oluşmalı ve Kategori tablosuna 10'dan fazla aynı tür ürün eklenemez!");
    } else {
      productRepository.save(product);
      message = "Product başarıyla eklendi";
    }
    Map<String, Product> response = new HashMap<>();
    response.put(message, product);
    return response;
  }

  @Override
  public Product update(Integer productId, Product product) {
    Product productToUpdate =
        productRepository
            .findById(productId)
            .orElseThrow(() -> new ApiErrorException("No product with id:" + productId));
    productToUpdate.setProductName(product.getProductName());
    productToUpdate.setUnitPrice(product.getUnitPrice());
    productRepository.save(productToUpdate);
    return productRepository.save(productToUpdate);
  }

  @Override
  public Map<String, Boolean> delete(Integer productId) {
    return productRepository
        .findById(productId)
        .map(
            product -> {
              productRepository.delete(product);
              Map<String, Boolean> response = new HashMap<>();
              response.put("The given product is deleted", Boolean.TRUE);
              return response;
            })
        .orElseThrow(() -> new ApiErrorException("No product with id: " + productId));
  }
}
