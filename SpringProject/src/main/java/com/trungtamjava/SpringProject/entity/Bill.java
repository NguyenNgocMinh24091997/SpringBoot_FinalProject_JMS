package com.trungtamjava.SpringProject.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "bill")
@AllArgsConstructor
@NoArgsConstructor
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "buyerId")
	private User buyer;
	
	@Column(name = "priceTotal")
	private long priceTotal;
	
	@Column(name = "dateBuy")
	private Date dateBuy;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bill")
	private List<BillProduct> billProducts;
}
