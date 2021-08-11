package com.nkn.butler.controller;

import com.nkn.butler.model.dto.ProductInfoDTO;
import com.nkn.butler.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {

   private ProductService productService;

   @Autowired
   public ProductController(ProductService productService) {
      this.productService = productService;
   }

   @GetMapping("")
   public String showIndex() {
      return "index";
   }

   @GetMapping("/products")
   public String showProductList(Model model) {
      List<ProductInfoDTO> productList = productService.readAllProducts();
      if (!productList.isEmpty()) {
         model.addAttribute("productList", productList);
         return "products";
      } else {
         return "no-products";
      }
   }

   @GetMapping("/new")
   public String showNewProductPage(Model model) {
      model.addAttribute("productInfoDTO", new ProductInfoDTO());
      return "new-product";
   }

   @PostMapping("/save")
   public String saveNewProduct(@ModelAttribute("productInfoDTO") @Valid ProductInfoDTO productInfoDTO) {
      productService.createProduct(productInfoDTO);
      return "redirect:/products";
   }

   @GetMapping("/updateForm/{name}")
   public ModelAndView showUpdateProductPage(@PathVariable(name = "name") String name) {
      ModelAndView modelAndView = new ModelAndView("update-product");
      ProductInfoDTO productInfoDTO = productService.readProduct(name);
      modelAndView.addObject("productInfoDTO", productInfoDTO);
      return modelAndView;
   }

   @PostMapping("/update/{name}")
   public String updateProduct(@PathVariable("name") String name, ProductInfoDTO productInfoDTO, BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "update-product";
      }
      productService.updateProduct(productInfoDTO);
      model.addAttribute("products", productService.readAllProducts());
      return "redirect:/products";
   }
}
