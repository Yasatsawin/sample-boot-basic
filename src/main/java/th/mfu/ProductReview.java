package th.mfu;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int rating;
    private String comment;
    // private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private Customer customer;  // Many customers → many reviews

    @ManyToOne
    private Product product;    // Many products → many reviews

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    // public LocalDateTime getCreatedAt() {
    //     return createdAt;
    // }
    // public void setCreatedAt(LocalDateTime createdAt) {
    //     this.createdAt = createdAt;
    // }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}