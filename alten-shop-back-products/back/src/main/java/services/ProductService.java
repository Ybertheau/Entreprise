package services;

import java.util.List;
import java.util.Set;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import entity.Product;
import exception.IdException;
import exception.ProductException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	@Autowired 
	private Validator validator;
	
	private static final Logger LOG=LoggerFactory.getLogger(ProductService.class);
	
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
	
	//CRUD
	//create
	public Product create(Product product) {
		if (product==null) {
			throw new ProductException("product null");
		}
		Set<ConstraintViolation<Product>>violation=validator.validate(product);
		if(!violation.isEmpty()) {
			violation.forEach(v->{
				LOG.debug(v.toString());
			});
			throw new ProductException();
		}
		return productRepo.save(product);
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
	//delete
	public void delete(Long id) {
		deleteById(id);
	}
	public void deleteById(Long id) {
		productRepo.deleteById(id);
	}
	//mise a jour qui va ecraser le product à l'ID indiqué
	public Product update(Product product) {
		//on à pas de valeur obligatoire pour le moment donc on fais un get classique
		Product productDataBase=getById(product.getId());
		productDataBase.setCode(product.getCode());
		productDataBase.setName(product.getName());
		productDataBase.setDescription(product.getDescription());
		productDataBase.setPrice(product.getPrice());
		productDataBase.setQuantity(product.getQuantity());
		productDataBase.setInventoryStatus(product.getInventoryStatus());
		productDataBase.setCategory(product.getCategory());
		productDataBase.setImage(product.getImage());
		productDataBase.setRating(product.getRating());
		return productRepo.save(productDataBase);
		
	}
	
	
}
