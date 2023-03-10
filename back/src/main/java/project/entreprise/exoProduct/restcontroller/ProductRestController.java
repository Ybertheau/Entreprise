package project.entreprise.exoProduct.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;
import project.entreprise.exoProduct.entity.Product;
import project.entreprise.exoProduct.jsonviews.Views;
import project.entreprise.exoProduct.services.ProductService;
import project.entreprise.exoProduct.util.Check;

@RestController
@RequestMapping("/products")
public class ProductRestController {
	@Autowired
	private ProductService productSrv;

	@GetMapping("")
	@JsonView(Views.Common.class)
	public List<Product> getAll() {
		return productSrv.getAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.Common.class)
	public Product getById(@PathVariable Long id) {
		return productSrv.getById(id);
	}

	@PostMapping("")
	@JsonView(Views.Common.class)
	public Product create(@Valid @RequestBody Product product, BindingResult br) {
		Check.checkBindingResulHasError(br);
		return productSrv.create(product);
	}

	@PutMapping("/{id}")
	@JsonView(Views.Common.class)
	public Product update(@Valid @RequestBody Product product, BindingResult br, @PathVariable Long id) {
		Check.checkBindingResulHasError(br);
		return productSrv.create(product);
	}

	@PatchMapping("/{id}")
	@JsonView(Views.Common.class)
	public Product patch(@RequestBody Map<String, Object> fields, @PathVariable Long id) {
		Product product = productSrv.getById(id);

		return productSrv.update(product);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public void delete(@PathVariable Long id) {
		productSrv.delete(id);
	}
}
