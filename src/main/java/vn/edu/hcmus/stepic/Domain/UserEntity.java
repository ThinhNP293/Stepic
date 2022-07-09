package vn.edu.hcmus.stepic.Domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user_tbl")
public class UserEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private float balance;

    private List<ProductEntity> ownedGame;

    private String role;
}
