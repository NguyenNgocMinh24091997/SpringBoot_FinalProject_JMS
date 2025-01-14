package com.trungtamjava.SpringProject.dao;

import java.util.List;

import com.trungtamjava.SpringProject.entity.Bill;

public interface BillDao {
	void create(Bill bill);

	void update(Bill bill);

	void delete(Bill bill);

	Bill getById(int id);

	List<Bill> getByUser(int userId);

	List<Bill> getAll();

	int count(String name);

	List<Bill> search(String keyword, int maxPerPage, int offset);

	Bill getLastestBill();

}
