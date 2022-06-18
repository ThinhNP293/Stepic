package vn.edu.hcmus.stepic.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmus.stepic.Domain.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}

