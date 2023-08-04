package com.udacity.course3.reviews.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price ;
    @NotEmpty
    private String name;
    @Column(name = "created_at",insertable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "updated_at")
    @CreationTimestamp()
    private Timestamp modifiedAt;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    Set<Review> reviews=new HashSet<>();
    //helper methods to deal with it
    //as it is not recommended to work with setter
    public void addReview(Review review){
        reviews.add(review);
    }
    public void removeReview(Review review){
        reviews.remove(review);
    }
}
