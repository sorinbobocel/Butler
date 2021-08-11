package com.nkn.butler.service.impl;

import com.nkn.butler.model.Product;
import com.nkn.butler.model.dto.ProductInfoDTO;
import com.nkn.butler.repository.ProductRepository;
import com.nkn.butler.service.ProductService;
import com.nkn.butler.transform.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

   private static final String NO_PRODUCT = "The required product is not registered in database.";

   private ProductRepository productRepository;

   private ProductConverter converter;

   @Autowired
   public ProductServiceImpl(ProductRepository productRepository, ProductConverter converter) {
      this.productRepository = productRepository;
      this.converter = converter;
   }

   @Override
   public void createProduct(ProductInfoDTO productInfoDTO) {
      if (!isPresent(productInfoDTO.getName())) {
         productRepository.save(converter.convertToProduct(productInfoDTO));
         System.out.println("CREATED");
      } else {
         throw new EntityExistsException("This product already exists in database.");
      }
   }

   @Override
   public ProductInfoDTO readProduct(String name) {
      Optional<Product> optionalProduct = productRepository.findProductByName(name);
      if (optionalProduct.isPresent()) {
         return converter.convertToProductDTO(optionalProduct.get());
      } else {
         throw new NoSuchElementException(NO_PRODUCT);
      }
   }

   @Override
   public List<ProductInfoDTO> readAllProducts() {
      List<Product> productList = productRepository.findAll();
         return productList.stream()
               .map(converter::convertToProductDTO)
               .collect(Collectors.toList());
   }

      @Override
      public ProductInfoDTO updateProduct (ProductInfoDTO productInfoDTO){
         Optional<Product> optionalProduct = productRepository.findProductByName(productInfoDTO.getName());
         if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setStockQuantity(productInfoDTO.getStockQuantity());
            existingProduct.setUnitPrice(productInfoDTO.getUnitPrice());
            productRepository.save(existingProduct);
            return converter.convertToProductDTO(existingProduct);
         } else {
            throw new NoSuchElementException(NO_PRODUCT);
         }
      }

      @Override
      public void deleteProduct (Long productId){
         Optional<Product> optionalProduct = productRepository.findById(productId);
         if (optionalProduct.isPresent()) {
            productRepository.deleteById(productId);
         } else {
            throw new NoSuchElementException(NO_PRODUCT);
         }
      }

      private boolean isPresent (String name){
         Optional<Product> existingProduct = productRepository.findProductByName(name);
         return existingProduct.isPresent();
      }
   }
