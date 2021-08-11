package com.nkn.butler.service;

import com.nkn.butler.model.Product;
import com.nkn.butler.model.dto.ProductInfoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

   void createProduct(ProductInfoDTO productInfoDTO);

   ProductInfoDTO readProduct(String name);

   List<ProductInfoDTO> readAllProducts();

   ProductInfoDTO updateProduct(ProductInfoDTO productInfoDTO);

   void deleteProduct(Long productId);
}
