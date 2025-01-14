package com.trungtamjava.SpringProject.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trungtamjava.SpringProject.dao.ProductDao;
import com.trungtamjava.SpringProject.entity.Category;
import com.trungtamjava.SpringProject.entity.Product;
import com.trungtamjava.SpringProject.model.CategoryDTO;
import com.trungtamjava.SpringProject.model.ProductDTO;
import com.trungtamjava.SpringProject.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Autowired
	private CategoryServiceImpl categoryService;

	@Override
	public void add(ProductDTO product) {
		productDao.add(convertToEntity(product));
	}

	@Override
	public void update(ProductDTO product) {
		productDao.update(convertToEntity(product));
	}

	@Override
	public void delete(int id) {
		Product product = productDao.getProductById(id);
		if (product != null) {
			productDao.delete(product);
		}
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		List<Product> products = productDao.getAllProducts();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Product product : products) {
			productDTOs.add(convertToDTO(product));
		}
		return productDTOs;
	}

	@Override
	public ProductDTO getProductById(int id) {
		return convertToDTO(productDao.getProductById(id));
	}

	@Override
	public List<ProductDTO> getProductByName(String name) {
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		List<Product> products = productDao.getProductByName(name);
		for (Product product : products) {
			productDTOs.add(convertToDTO(product));
		}
		return productDTOs;
	}

	@Override
	public List<ProductDTO> getByCategory(int categoryId) {
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		List<Product> products = productDao.getByCategory(categoryId);
		for (Product product : products) {
			productDTOs.add(convertToDTO(product));
		}
		return productDTOs;
	}

	@Override
	public List<ProductDTO> getLimitProduct(int numProduct) {
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		List<Product> products = productDao.getLimitProduct(numProduct);
		for (Product product : products) {
			productDTOs.add(convertToDTO(product));
		}
		return productDTOs;
	}

	@Override
	public int count(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductDTO> search(String name, int maxPage, int offset) {
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		List<Product> products = productDao.search(name, maxPage, offset);
		for (Product product : products) {
			productDTOs.add(convertToDTO(product));
		}
		return productDTOs;
	}

	public Product convertToEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setDescription(productDTO.getDescription());
		product.setImage(productDTO.getImage());
		product.setCategory(categoryService.convertToEntity(productDTO.getCategory()));
		return product;
	}

	public ProductDTO convertToDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setDescription(product.getDescription());
		productDTO.setImage(product.getImage());
		productDTO.setCategory(categoryService.convertToDTO(product.getCategory()));
		return productDTO;
	}

}
