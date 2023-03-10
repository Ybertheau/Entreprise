package Entreprise.exoProduct;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import jakarta.transaction.Transactional;
import project.entreprise.exoProduct.entity.Product;
import project.entreprise.exoProduct.exception.IdException;
import project.entreprise.exoProduct.exception.ProductException;
import project.entreprise.exoProduct.services.ProductService;

@SpringBootTest
@Transactional
@Rollback
class ProductServiceTest {
	@Autowired
	private ProductService productSrv;
	
	@Test
	void test() {
		//fail("Not yet implemented");
		assertNotNull(productSrv);
	}
	@Test
	void insert() {
		Product p=new Product("testcode", "testname", "testdescr", 42.68, 100, "testStatus", "testCategory");
		productSrv.create(p);
		assertNotNull(productSrv.getById(p.getId()));
	}
	@Test
	void findById() {
		assertThrows(IdException.class, ()->{
			productSrv.getById(null);
		});
		assertThrows(ProductException.class, ()->{
			productSrv.getById(1L);
		});
	}
}
