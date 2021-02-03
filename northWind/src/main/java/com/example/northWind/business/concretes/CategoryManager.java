package com.example.northWind.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.northWind.entities.concretes.Category;
import com.example.northWind.business.abstracts.ICategoryService;
import com.example.northWind.dataAccess.concretes.CategoryRepository;

@Service
public class CategoryManager implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Optional<Category> getById(Integer id) {
		
		return categoryRepository.findById(id);
	}
	
	@Override
	public List<Category> getAll() {
		
		return categoryRepository.findAll();
	}
	
	@Override
	public Map<String, Category> add(Category category) {
		String message;
		if(categoryRepository.findAll().size() > 10) {
			message = "Toplam kategori sayısı 10'dan büyük olamaz";
		}
		else {
			message = "Kategori eklendi";
			categoryRepository.save(category);		
		}
		Map<String, Category> response = new HashMap<>();
		response.put(message, category);
		categoryRepository.save(category);
		return response;
	}

	@Override
	public ResponseEntity<Category> update(Integer id, Category category) throws Exception {
		Category categorytoUpdate = categoryRepository.findById(id)
				.orElseThrow(()-> new Exception("No category with id:"+id));
		categorytoUpdate.setName(category.getName());
		categorytoUpdate.setDescription(category.getDescription());
		categoryRepository.save(categorytoUpdate);
		Category updatedCategory = categoryRepository.save(categorytoUpdate);
		return ResponseEntity.ok(updatedCategory);
	}

	@Override
	public Map<String, Boolean> delete(Integer id) throws Exception {
		Category categoryDelete = categoryRepository.findById(id)
				.orElseThrow(() -> new Exception("No category with id:"+id)) ;
		categoryRepository.delete(categoryDelete);
		Map<String, Boolean> response = new HashMap<>();
		response.put("The given category is deleted", Boolean.TRUE);
		return response;
	}
}
