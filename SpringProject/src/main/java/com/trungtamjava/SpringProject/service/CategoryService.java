package com.trungtamjava.SpringProject.service;

import java.util.List;

import com.trungtamjava.SpringProject.model.CategoryDTO;

public interface CategoryService {
	void add(CategoryDTO category);

	void delete(int id);

	void update(CategoryDTO updateCategory);

	CategoryDTO getById(int Id);

	List<CategoryDTO> getByName(String name);

	List<CategoryDTO> getAll();

	List<CategoryDTO> search(String name, int offset, int maxPerPage);

	int count(String name);
}
