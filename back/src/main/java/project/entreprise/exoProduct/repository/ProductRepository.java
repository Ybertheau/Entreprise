package project.entreprise.exoProduct.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import project.entreprise.exoProduct.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Page<Product> findAll(Pageable pageable);

}
