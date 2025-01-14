package com.trungtamjava.SpringProject.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trungtamjava.SpringProject.dao.CategoryDao;
import com.trungtamjava.SpringProject.entity.Category;
import com.trungtamjava.SpringProject.model.CategoryDTO;
import com.trungtamjava.SpringProject.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public void add(CategoryDTO category) {
		categoryDao.add(convertToEntity(category));
	}

	@Override
	public void delete(int id) {
		Category category = categoryDao.getById(id);
		if (category != null) {
			categoryDao.delete(category);
		}
	}

	@Override
	public void update(CategoryDTO updateCategory) {
		categoryDao.update(convertToEntity(updateCategory));
	}

	@Override
	public CategoryDTO getById(int Id) {
		return convertToDTO(categoryDao.getById(Id));
	}

	@Override
	public List<CategoryDTO> getByName(String name) {
		List<Category> categories = categoryDao.getByName(name);
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		for (Category category : categories) {
			categoryDTOs.add(convertToDTO(category));
		}
		return categoryDTOs;
	}

	@Override
	public List<CategoryDTO> getAll() {
		List<Category> categories = categoryDao.getAll();
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		for (Category category : categories) {
			categoryDTOs.add(convertToDTO(category));
		}
		return categoryDTOs;
	}

	@Override
	public List<CategoryDTO> search(String name, int offset, int maxPerPage) {
		List<Category> categories = categoryDao.search(name, offset, maxPerPage);
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		for (Category category : categories) {
			categoryDTOs.add(convertToDTO(category));
		}
		return categoryDTOs;
	}

	@Override
	public int count(String name) {
		return 0;
	}

	public Category convertToEntity(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		return category;
	}

	public CategoryDTO convertToDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		category.setId(category.getId());
		category.setName(category.getName());
		return categoryDTO;
	}

}
