package com.jspiders.hibernateonetomany.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.hibernateonetomany.dto.CustomerDTO;
import com.jspiders.hibernateonetomany.dto.ProductDTO;


public class CustomerProductDAO {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
	
	private static void openConnection() {
		entityManagerFactory=Persistence.createEntityManagerFactory("OneToMany");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();
		
		
	}
	private static void closeConnection() {
		if(entityManagerFactory != null) {
			entityManagerFactory.close();
		}
		if(entityManager !=null) {
			entityManager.close();
		}
		if(entityTransaction.isActive()) {
			entityTransaction.rollback();
		}
	}
	public static void main(String[] args) {
		try {
			openConnection();
			entityTransaction.begin();
			
			ProductDTO product1 = new ProductDTO();
			product1.setId(1);
			product1.setName("T-shirt");
			product1.setCatogery("Clothings");
			product1.setPrice(500);
			
			entityManager.persist(product1);
			
			
			ProductDTO product2 = new ProductDTO();
			product2.setId(2);
			product2.setName("perfum");
			product2.setCatogery("Grooming");
			product2.setPrice(300);
			
			entityManager.persist(product2);
			
			List<ProductDTO>products=Arrays.asList(product1,product2);
			
			CustomerDTO customer = new CustomerDTO();
			customer.setId(1);
			customer.setName("Nitin");
			customer.setCity("Pune");
			customer.setContact(1234567890L);
			customer.setProducts(products);
			entityManager.persist(customer);
			
			
			entityTransaction.commit();
			
		} finally {
	
			closeConnection();
		}
	}

}
