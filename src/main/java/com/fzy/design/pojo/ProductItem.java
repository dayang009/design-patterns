package com.fzy.design.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "product_item")
public class ProductItem implements Serializable {

	private static final long serialVersionUID = 8816730537624161838L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int pid;

}
