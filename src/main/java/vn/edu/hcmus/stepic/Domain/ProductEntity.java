package vn.edu.hcmus.stepic.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name="product_tbl")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String brand;

    private int price;

    private float discount;

    private String logoImage;

    private String gameImage;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;

    private long purchaseAmount;

    private float rating;
}
