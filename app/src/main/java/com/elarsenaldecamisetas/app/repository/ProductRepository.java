package com.elarsenaldecamisetas.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elarsenaldecamisetas.app.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    List <Product> findByTeamId(Long teamId);
    List <Product> findByCurrentTrue();
    List <Product> findByTeamLeagueId(Long leagueId);
}
