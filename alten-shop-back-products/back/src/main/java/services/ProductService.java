package services;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import entity.Product;
import exception.IdException;
import exception.ProductException;
import repository.ProductRepository;

public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	
	//find all et pagination
	public List<Product> getAll(){
		return productRepo.findAll();
	}
	public Page<Product>getAll(Pageable pageable){
		if(pageable==null) {
			throw new ProductException();
		}
		return productRepo.findAll(pageable);
	}
	public Page<Product> getNextPage(Page<Product> page){
		if(page==null) {
			throw new ProductException();
		}
		return productRepo.findAll(page.nextPageable());
	}
	public Page<Product> getPrevPage(Page<Product> page){
		if(page==null) {
			throw new ProductException();
		}
		return productRepo.findAll(page.previousOrFirstPageable());
	}
	
	//recuperation par ID
	public Product getById(Long id) {
		if (id==null) {
			throw new IdException();
		}
		return productRepo.findById(id).orElseThrow(()->{
			throw new ProductException("product unknow");
		});
	}
}
