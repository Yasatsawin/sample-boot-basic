package th.mfu;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Customer {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("fullname")
    @Column(name="displayname")
    private String name;

    private String address;

    private String email;

    @JsonProperty("tel")
    private String phone;

    private LocalDate birthday;

    @ManyToOne
    private CustomerTier customerTier;

    @ManyToOne
    private ProductReview productReview;


    @OneToMany(mappedBy="customer")
    @JsonManagedReference("customer-order")
    private List<SaleOrder> saleOrders;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }



    public CustomerTier getCustomerTier() {
        return customerTier;
    }
    public void setCustomerTier(CustomerTier customerTier) {
        this.customerTier = customerTier;
    }




    public ProductReview getProductReview() {
        return productReview;
    }
    public void setProductReview(ProductReview productReview) {
        this.productReview = productReview;
    }




}
