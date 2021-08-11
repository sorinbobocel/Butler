package com.nkn.butler.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long productId;

   @NotNull
   private String name;

   @NotNull
   private int stockQuantity;

   @NotNull
   private BigDecimal unitPrice;

   public Long getProductId() {
      return productId;
   }

   public void setProductId(Long productId) {
      this.productId = productId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getStockQuantity() {
      return stockQuantity;
   }

   public void setStockQuantity(int stockQuantity) {
      this.stockQuantity = stockQuantity;
   }

   public BigDecimal getUnitPrice() {
      return unitPrice;
   }

   public void setUnitPrice(BigDecimal unitPrice) {
      this.unitPrice = unitPrice;
   }
}
