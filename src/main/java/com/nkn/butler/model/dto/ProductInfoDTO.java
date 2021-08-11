package com.nkn.butler.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class  ProductInfoDTO {

   private String name;

   private int stockQuantity;

   private BigDecimal unitPrice;

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
