package com.trungtamjava.SpringProject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.trungtamjava.SpringProject.dao.BillDao;
import com.trungtamjava.SpringProject.entity.Bill;

@Repository
@Transactional
public class BillDaoImpl implements BillDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Bill bill) {
		entityManager.persist(bill);
	}

	@Override
	public void update(Bill bill) {
		entityManager.merge(bill);
	}

	@Override
	public void delete(Bill bill) {
		entityManager.remove(bill);
	}

	@Override
	public Bill getById(int id) {
		return entityManager.find(Bill.class, id);
	}

	@Override
	public List<Bill> getByUser(int userId) {
		String jql = "SELECT b FROM Bill b join b.user u WHERE u.id = :userId";
		return entityManager.createQuery(jql, Bill.class).setParameter("userId", userId).getResultList();
	}

	@Override
	public List<Bill> getAll() {
		String jql = "SELECT bill FROM bill";
		return entityManager.createQuery(jql, Bill.class).getResultList();
	}

	@Override
	public int count(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Bill> search(String keyword, int maxPerPage, int offset) {
		String jql = "SELECT bill FROM bill WHERE bill.name LIKE :keyword";
		return entityManager.createQuery(jql, Bill.class).setParameter("keyword", "%" + keyword + "%")
				.setFirstResult(offset).setMaxResults(maxPerPage).getResultList();
	}

	@Override
	public Bill getLastestBill() {
		String jql = "SELECT b FROM bill b ORDER BY bill.id DESC LIMIT 1";
		return entityManager.createQuery(jql, Bill.class).getSingleResult();
	}

}
