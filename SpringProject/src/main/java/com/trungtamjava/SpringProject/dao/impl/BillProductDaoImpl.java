package com.trungtamjava.SpringProject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.trungtamjava.SpringProject.dao.BillProductDao;
import com.trungtamjava.SpringProject.entity.BillProduct;

@Repository
@Transactional
public class BillProductDaoImpl implements BillProductDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(BillProduct billProduct) {
		entityManager.persist(billProduct);
	}

	@Override
	public void update(BillProduct billProduct) {
		entityManager.merge(billProduct);
	}

	@Override
	public void delete(BillProduct billProduct) {
		entityManager.remove(billProduct);
	}

	@Override
	public BillProduct getById(int id) {
		return entityManager.find(BillProduct.class, id);
	}

	@Override
	public List<BillProduct> getByBill(int billId) {
		String jql = "SELECT bp FROM billproduct bp join bp.bill b where b.id = :billId";
		return entityManager.createQuery(jql, BillProduct.class).setParameter("billId", billId).getResultList();
	}

	@Override
	public int count(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BillProduct> search(String name, int maxPage, int offset) {
		String jql = "SELECT billproduct FROM billproduct WHERE billproduct.name LIKE :name";
		return entityManager.createQuery(jql, BillProduct.class).setParameter("name", "%" + name + "%")
				.setFirstResult(offset).setMaxResults(maxPage).getResultList();
	}

	@Override
	public double getCoupon(String name) {
		/*
		 * String jql =
		 * "SELECT coupon.saleValue FROM coupon WHERE name LIKE :saleValue"; return
		 * entityManager.createQuery(jql, Coupon);
		 */
		return 0;
	}

}
