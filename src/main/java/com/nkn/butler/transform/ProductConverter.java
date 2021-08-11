package com.nkn.butler.transform;

import com.nkn.butler.model.Product;
import com.nkn.butler.model.dto.ProductInfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductConverter {

   Product convertToProduct(ProductInfoDTO productInfoDTO);

   ProductInfoDTO convertToProductDTO(Product product);
}
