package com.example.northWind.business.abstracts;

import com.example.northWind.entities.concretes.Category;
import java.util.List;
import java.util.Map;

public interface ICategoryService {

  List<Category> getAll();

  Category getById(Integer id);

  Map<String, Category> add(Category category);

  Category update(Integer id, Category category);

  Map<String, Boolean> delete(Integer id);
}
