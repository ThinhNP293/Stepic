package vn.edu.hcmus.stepic.Service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.hcmus.stepic.Base.ResponseBody;
import vn.edu.hcmus.stepic.Domain.ProductEntity;
import vn.edu.hcmus.stepic.Domain.UserEntity;
import vn.edu.hcmus.stepic.Repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not present"));

        UserDetails userDetails = User.withUsername(user.getEmail()).password(user.getPassword())
                .authorities(user.getRole()).build();

        return userDetails;
    }

    public ResponseEntity<?> createUser(UserEntity user){
        boolean exist = userRepository.existsByEmail(user.getEmail());
        if (exist){
            return ResponseEntity.badRequest().body(new ResponseBody("Email existed!"));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        user.setBalance(0);
        userRepository.save(user);
        return ResponseEntity.created(null).body(new ResponseBody("Register success!"));
    }

    public Authentication getCurrentAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public ResponseEntity<?> getCurrentUser(){
        Authentication authentication = getCurrentAuthentication();
        UserEntity user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not present"));

        return ResponseEntity.ok().body(new ResponseBody(user));
    }

    public ResponseEntity<?> getCurrentUserByEmail(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isPresent()){
            return ResponseEntity.ok().body(new ResponseBody(user));
        }
        else{
            return ResponseEntity.badRequest().body(new ResponseBody("User not exist!"));
        }
    }
    
    @Transactional
    public void addProduct(ProductEntity product) {
        Authentication authentication = getCurrentAuthentication();
        UserEntity user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not present"));
        user.getOwnedGame().add(product);
    }

}
