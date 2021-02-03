package com.example.northWind.business.abstracts;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.northWind.entities.concretes.Category;;
public interface ICategoryService {

	List<Category> getAll();
	Optional<Category> getById(Integer id);
	public Map<String, Category> add(Category category);
	public ResponseEntity<Category> update(Integer id, Category category ) throws Exception;
	public Map<String, Boolean> delete(Integer id) throws Exception;
}
