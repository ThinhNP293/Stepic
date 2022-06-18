package vn.edu.hcmus.stepic.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmus.stepic.Domain.OrderDetailEntity;
import vn.edu.hcmus.stepic.Domain.OrderEntity;
import vn.edu.hcmus.stepic.Service.OrderService;
import vn.edu.hcmus.stepic.Service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity findAllOrderOfUser(Principal principal) {
        return orderService.findAllOrderOfUser(principal);
    }

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestBody List<OrderDetailEntity> orderDetailList){
        return orderService.createOrder(orderDetailList);
    }

}
