package com.trungtamjava.SpringProject.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trungtamjava.SpringProject.dao.BillProductDao;
import com.trungtamjava.SpringProject.entity.BillProduct;
import com.trungtamjava.SpringProject.model.BillProductDTO;
import com.trungtamjava.SpringProject.service.BillProductService;

@Service
@Transactional
public class BillProductServiceImpl implements BillProductService {

	@Autowired
	private BillProductDao billProductDao;

	@Autowired
	private ProductServiceImpl productService;

	@Autowired
	private BillServiceImpl billServiceImpl;

	@Override
	public void create(BillProductDTO billProduct) {
		billProductDao.create(convertToEntity(billProduct));
	}

	@Override
	public void update(BillProductDTO billProduct) {
		billProductDao.update(convertToEntity(billProduct));
	}

	@Override
	public void delete(BillProductDTO billProductDTO) {
		billProductDao.delete(convertToEntity(billProductDTO));
	}

	@Override
	public BillProductDTO getById(int id) {
		return convertToDTO(billProductDao.getById(id));
	}

	@Override
	public List<BillProductDTO> getByBill(int billId) {
		List<BillProduct> billProducts = billProductDao.getByBill(billId);
		List<BillProductDTO> billProductDTOs = new ArrayList<BillProductDTO>();

		for (BillProduct billProduct : billProducts) {
			billProductDTOs.add(convertToDTO(billProduct));
		}
		return billProductDTOs;
	}

	@Override
	public int count(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BillProductDTO> search(String name, int maxPage, int offset) {
		List<BillProduct> billProducts = billProductDao.search(name, maxPage, offset);
		List<BillProductDTO> billProductDTOs = new ArrayList<BillProductDTO>();
		for (BillProduct billProduct : billProducts) {
			billProductDTOs.add(convertToDTO(billProduct));
		}
		return billProductDTOs;
	}

	@Override
	public double getCoupon(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	public BillProduct convertToEntity(BillProductDTO billProductDTO) {
		BillProduct billProduct = new BillProduct();
		billProduct.setId(billProductDTO.getId());
		billProduct.setProduct(productService.convertToEntity(billProductDTO.getProduct()));
		billProduct.setBill(billServiceImpl.convertToEntity(billProductDTO.getBill()));
		billProduct.setQuantity(billProductDTO.getQuantity());
		billProduct.setUnitPrice(billProductDTO.getUnitPrice());

		return billProduct;
	}

	public BillProductDTO convertToDTO(BillProduct billProduct) {
		BillProductDTO billProductDTO = new BillProductDTO();
		billProductDTO.setId(billProduct.getId());
		billProductDTO.setProduct(productService.convertToDTO(billProduct.getProduct()));
		billProductDTO.setBill(billServiceImpl.convertToDTO(billProduct.getBill()));
		billProductDTO.setQuantity(billProduct.getQuantity());
		billProductDTO.setUnitPrice(billProduct.getUnitPrice());

		return billProductDTO;
	}
}
