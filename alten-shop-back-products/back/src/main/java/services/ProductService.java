package services;

import org.springframework.beans.factory.annotation.Autowired;

import repository.ProductRepository;

public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	
}
