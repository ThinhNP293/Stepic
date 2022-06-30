package vn.edu.hcmus.stepic.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmus.stepic.Service.ProductService;
import vn.edu.hcmus.stepic.Service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {
    private final UserService userService;
    private final ProductService productService;

    public HomeController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/index")
    public ResponseEntity<Object> homePage() {
        HashMap<String, Object> home = new HashMap();
        home.put("User", userService.getCurrentUser());
        home.put("Product", productService.findAll());
        return ResponseEntity.ok().body(home);
    }
}
