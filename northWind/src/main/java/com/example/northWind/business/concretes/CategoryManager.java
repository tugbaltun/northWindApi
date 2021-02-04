package com.example.northWind.business.concretes;

import com.example.northWind.business.abstracts.ICategoryService;
import com.example.northWind.dataAccess.concretes.CategoryRepository;
import com.example.northWind.entities.concretes.Category;
import com.example.northWind.exception.ApiErrorException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements ICategoryService {

  @Autowired private CategoryRepository categoryRepository;

  @Override
  public Category getById(Integer id) {
    return categoryRepository
        .findById(id)
        .orElseThrow(() -> new ApiErrorException("No category with id:" + id));
  }

  @Override
  public List<Category> getAll() {
    if (categoryRepository.findAll().isEmpty()) {
      throw new ApiErrorException("Category cannot be found in the cache");
    }
    return categoryRepository.findAll();
  }

  @Override
  public Map<String, Category> add(Category category) {
    String message = "Kategori eklendi";
    categoryRepository.save(category);
    Map<String, Category> response = new HashMap<>();
    response.put(message, category);
    categoryRepository.save(category);
    return response;
  }

  @Override
  public Category update(Integer id, Category category) {
    Category categorytoUpdate =
        categoryRepository
            .findById(id)
            .orElseThrow(() -> new ApiErrorException("No category with id:" + id));
    categorytoUpdate.setName(category.getName());
    categorytoUpdate.setDescription(category.getDescription());
    categoryRepository.save(categorytoUpdate);
    return categoryRepository.save(categorytoUpdate);
  }

  @Override
  public Map<String, Boolean> delete(Integer id) {
    return categoryRepository
        .findById(id)
        .map(
            category -> {
              categoryRepository.delete(category);
              Map<String, Boolean> response = new HashMap<>();
              response.put("The given category is deleted", Boolean.TRUE);
              return response;
            })
        .orElseThrow(() -> new ApiErrorException("No category with id:" + id));
  }
}
