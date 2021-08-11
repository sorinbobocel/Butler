package com.nkn.butler.transform;

import com.nkn.butler.model.Product;
import com.nkn.butler.model.dto.ProductInfoDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductConverterTest {

   private ProductConverter converter = Mappers.getMapper(ProductConverter.class);

   @Test
   void testConversionToProduct() {

      ProductInfoDTO productInfoDTO = new ProductInfoDTO();
      productInfoDTO.setName("Unique");
      productInfoDTO.setStockQuantity(15);
      productInfoDTO.setUnitPrice(new BigDecimal(10.50));
      Product product = converter.convertToProduct(productInfoDTO);

      assertEquals(productInfoDTO.getName(), product.getName());
   }

   @Test
   void testConversionToDTO() {

      Product product = new Product();
      product.setProductId(1L);
      product.setName("Product");
      product.setStockQuantity(12);
      product.setUnitPrice(new BigDecimal(5.00));
      ProductInfoDTO productInfoDTO = converter.convertToProductDTO(product);

      assertEquals(productInfoDTO.getStockQuantity(), product.getStockQuantity());
   }
}