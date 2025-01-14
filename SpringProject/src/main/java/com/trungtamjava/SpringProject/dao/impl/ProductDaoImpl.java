package com.trungtamjava.SpringProject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.trungtamjava.SpringProject.dao.ProductDao;
import com.trungtamjava.SpringProject.entity.Product;
import com.trungtamjava.SpringProject.entity.User;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(Product product) {
		entityManager.persist(product);
	}

	@Override
	public void update(Product product) {
		entityManager.merge(product);
	}

	@Override
	public void delete(Product product) {
		entityManager.remove(product);
	}

	@Override
	public List<Product> getAllProducts() {
		String jql = "SELECT * FROM product";
		return entityManager.createQuery(jql, Product.class).getResultList();
	}

	@Override
	public Product getProductById(int id) {
		return entityManager.find(Product.class, id);
	}

	@Override
	public List<Product> getProductByName(String name) {
		String jql = "SELECT product FROM product WHERE product.name LIKE :name";
		return entityManager.createQuery(jql, Product.class).setParameter("name", "%" + name + "%").getResultList();
	}

	@Override
	public List<Product> getByCategory(int categoryId) {
		String jql = "SELECT p FROM product p join p.category c WHERE c.id = :categoryId";
		return entityManager.createQuery(jql, Product.class).setParameter("categoryId", categoryId).getResultList();
	}

	@Override
	public List<Product> getLimitProduct(int numProduct) {
		String jql = "SELECT product from product LIMIT :numProduct";
		return entityManager.createQuery(jql, Product.class).setParameter("numProduct", numProduct).getResultList();
	}

	@Override
	public int count(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Product> search(String name, int maxPage, int offset) {
		String jql = "SELECT product FROM product WHERE product.name LIKE :name";
		return entityManager.createQuery(jql, Product.class).setParameter("name", "%" + name + "%")
				.setFirstResult(offset).setMaxResults(maxPage).getResultList();
	}

}
