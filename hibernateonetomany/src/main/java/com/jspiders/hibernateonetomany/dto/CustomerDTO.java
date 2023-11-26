package com.jspiders.hibernateonetomany.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity

public class CustomerDTO {
	@Id
	
	private String name;
	private String price;
	private long category;
	
	@OneToMany
	List<ProductDTO> products;

}
