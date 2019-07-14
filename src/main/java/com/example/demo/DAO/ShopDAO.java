package com.example.demo.DAO;

import com.example.demo.Models.Shops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopDAO extends JpaRepository <Shops, Integer> {
}
