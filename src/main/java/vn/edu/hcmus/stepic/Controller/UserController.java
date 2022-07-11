package vn.edu.hcmus.stepic.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmus.stepic.Domain.UserEntity;
import vn.edu.hcmus.stepic.Service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser(@RequestBody String email){
        return userService.getCurrentUserByEmail(email);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user){
        return userService.createUser(user);
    }
}
