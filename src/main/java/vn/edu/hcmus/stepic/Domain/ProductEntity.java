package vn.edu.hcmus.stepic.Domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.TypeDef;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product_tbl")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
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

    private String shortDesc;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;

    private long purchaseAmount;

    private float rating;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<String> genres;
}
