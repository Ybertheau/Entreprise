package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entity.Product;
import services.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productSrv;
	@GetMapping("")
	public String list(
			Model model,
			@RequestParam(name = "element",required = false,defaultValue = "10")int element,
			@RequestParam(name = "page",required = false,defaultValue = "0")int numeroPage) {
		model.addAttribute("page",productSrv.getAll(PageRequest.of(numeroPage,element)));
		return "product/list";
	}
	@GetMapping("/delete")
	public String delete (@RequestParam Long id, Model model) {
		productSrv.delete(id);
		return "redirect:/product";
	}
	@GetMapping("/add")
	public String add(Model model) {
		return form(model, new Product());
	}
	@GetMapping("/edit")
	public String edit(Model model, @RequestParam Long id) {
		return form(model,productSrv.getById(id));
	}
	
	private String form(Model model, Product product) {
		model.addAttribute("product",product);
		return "product/edit";
	}
}
