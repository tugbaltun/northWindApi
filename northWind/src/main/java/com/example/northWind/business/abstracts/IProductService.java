package com.example.northWind.business.abstracts;

import com.example.northWind.entities.concretes.Product;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IProductService {

  List<Product> getAll();

  Product getById(Integer productId);

  Map<String, Product> add(Product product);

  Product update(Integer productId, Product product);

  Map<String, Boolean> delete(Integer productId);
}
