package com.example.northWind.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.northWind.entities.abstracts.IEntity;

import lombok.Data;

@Data
@Entity
@Table(name="products")
public class Product implements IEntity{  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private int productId;
	@Column(name="product_name")
	private String productName;
	@Column(name="category_id")
	private int categoryId;
	@Column(name="quantity_per_unit")
	private String quantityPerUnit;
	@Column(name="unit_price")
	private double unitPrice;
	
	@Column(name="supplier_id")
	private int supplierId;
	@Column(name="units_in_stock")
	private double unitsInStock;
	@Column(name="units_on_order")
	private int unitsOnOrder;
	@Column(name="reorder_level")
	private int reorderLevel;
	@Column(name="discontinued")
	private int discountinued;

	public Product(int productId, String productName, int categoryId, String quantityPerUnit, double unitPrice) {
		this.productId = productId;
		this.productName = productName;
		this.categoryId = categoryId;
		this.quantityPerUnit = quantityPerUnit;
		this.unitPrice = unitPrice;
	}
	
	public Product() {}

}
