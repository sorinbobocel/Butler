package com.nkn.butler.repository;

import com.nkn.butler.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

   Optional<Product> findProductByName(String name);
}
