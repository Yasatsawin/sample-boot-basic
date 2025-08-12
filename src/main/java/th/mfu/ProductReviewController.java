package th.mfu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ProductReviewController {

    @Autowired
    private ProductReviewRepository reviewRepo;

    @Autowired
    private CustomerRepository custRepo;

    @Autowired
    private ProductRepository prodRepo;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody ProductReview review) {
        if (review.getCustomer() == null || review.getCustomer().getId() == null ||
            review.getProduct() == null || review.getProduct().getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer ID and Product ID are required");
        }

        Optional<Customer> custOpt = custRepo.findById(review.getCustomer().getId());
        Optional<Product> prodOpt = prodRepo.findById(review.getProduct().getId());

        if (!custOpt.isPresent() || !prodOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Customer ID or Product ID");
        }

        review.setCustomer(custOpt.get());
        review.setProduct(prodOpt.get());

        reviewRepo.save(review);
       return new ResponseEntity<String>("Customer created", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllReviews() {
        return ResponseEntity.ok(reviewRepo.findAll());
    }
}