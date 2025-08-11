package th.mfu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController controller;

    @Test
    public void createAndGetProduct() {
        // Create a new product
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("This is a test product");
        product.setPrice(99.99);

        ResponseEntity<String> createResponse = controller.createProduct(product);
        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());

        // Get all products
        ResponseEntity<Collection> getResponse = controller.getAllProducts();
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertNotNull(getResponse.getBody());

        boolean exists = ((Collection<Product>) getResponse.getBody())
                .stream()
                .anyMatch(p -> "Test Product".equals(p.getName()));
        assertEquals(true, exists);
    }

    @Test
    public void createAndDeleteProduct() {
        // Get initial size
        ResponseEntity<Collection> initialGetResponse = controller.getAllProducts();
        assertEquals(HttpStatus.OK, initialGetResponse.getStatusCode());
        int initialSize = initialGetResponse.getBody().size();

        // Create product to delete
        Product product = new Product();
        product.setName("Delete Product");
        product.setDescription("Product to be deleted");
        product.setPrice(50.0);

        ResponseEntity<String> createResponse = controller.createProduct(product);
        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());

        // Find the product ID
        ResponseEntity<Collection> afterCreateResponse = controller.getAllProducts();
        List<Product> products = (List<Product>) afterCreateResponse.getBody();
        Product createdProduct = products.stream()
                .filter(p -> "Delete Product".equals(p.getName()))
                .findFirst()
                .orElse(null);
        assertNotNull(createdProduct);
        Integer id = createdProduct.getId();

        // Delete product
        ResponseEntity<String> deleteResponse = controller.deleteProduct(id);
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());

        // Verify size is back to original
        ResponseEntity<Collection> afterDeleteResponse = controller.getAllProducts();
        assertEquals(initialSize, afterDeleteResponse.getBody().size());
    }
}