package com.abhi.orders.repo;

import com.abhi.orders.entity.Product;
import com.abhi.orders.repo.dao.ProductDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductDao {
}