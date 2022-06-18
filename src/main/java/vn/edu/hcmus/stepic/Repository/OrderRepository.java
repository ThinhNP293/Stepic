package vn.edu.hcmus.stepic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hcmus.stepic.Domain.OrderEntity;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("SELECT order FROM OrderEntity order WHERE order.userEmail = :email")
    List<OrderEntity> findAllOrderOfUser(@Param("email") String email);
}
