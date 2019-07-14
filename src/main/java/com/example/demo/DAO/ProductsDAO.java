package com.example.demo.DAO;

import com.example.demo.Models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsDAO extends JpaRepository <Products, Integer> {

}
