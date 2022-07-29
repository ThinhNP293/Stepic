package vn.edu.hcmus.stepic.Domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<String> genres;
}
