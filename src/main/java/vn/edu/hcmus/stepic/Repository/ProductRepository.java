package vn.edu.hcmus.stepic.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.edu.hcmus.stepic.Domain.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("select product from ProductEntity product where product.name like :keyword")
    List<ProductEntity> searchProduct(@Param("keyword") String keyword);
}

