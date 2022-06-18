package vn.edu.hcmus.stepic.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.edu.hcmus.stepic.Domain.OrderDetailEntity;
import vn.edu.hcmus.stepic.Domain.OrderEntity;
import vn.edu.hcmus.stepic.Domain.ProductEntity;
import vn.edu.hcmus.stepic.Repository.OrderDetailRepository;
import vn.edu.hcmus.stepic.Repository.OrderRepository;

import java.security.Principal;
import java.util.List;

@Service
public class OrderService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;

    public OrderService(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository, ProductService productService, UserService userService) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.userService = userService;
    }

    public ResponseEntity findAllOrderOfUser(Principal principal) {
        return ResponseEntity.ok().body(orderRepository.findAllOrderOfUser(principal.getName()));
    }

    public ResponseEntity createOrder(List<OrderDetailEntity> orderDetailList){
        OrderEntity newOrder = new OrderEntity();
        newOrder.setUserEmail((String) userService.getCurrentAuthentication().getPrincipal());
        int price = 0;

        //Calc price of order
        for (OrderDetailEntity orderDetail : orderDetailList)
        {
            ProductEntity product = productService.findByProductId(orderDetail.getProductId());
            price += product.getPrice();
        }
        newOrder.setPrice(price);

        //Save new order
        orderRepository.save(newOrder);

        //Set orderId, email, date for all product
        for (OrderDetailEntity orderDetail : orderDetailList)
        {
            orderDetail.setOrderId(newOrder.getId());
            orderDetail.setUserEmail(newOrder.getUserEmail());
            orderDetail.setDate(newOrder.getDate());
        }

        //Save all product
        orderDetailRepository.saveAll(orderDetailList);

        return ResponseEntity.created(null).body("Create success!");
    }
}
