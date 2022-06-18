package vn.edu.hcmus.stepic.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.edu.hcmus.stepic.Base.ResponseBody;
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

    public ResponseEntity createUser(UserEntity user){
        boolean exist = userRepository.existsByEmail(user.getEmail());
        if (exist){
            return ResponseEntity.badRequest().body(new ResponseBody("Email existed!"));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return ResponseEntity.created(null).body(new ResponseBody("Register success!"));
    }

    public Authentication getCurrentAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
