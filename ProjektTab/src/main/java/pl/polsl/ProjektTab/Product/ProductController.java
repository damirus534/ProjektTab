package pl.polsl.ProjektTab.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
        
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{productId}/product-info/{infoId}")
    public Product assignProductInfoToProduct(@PathVariable Long productId, @PathVariable Long infoId) {
        return productService.assignProductInfoToProduct(productId, infoId);
    }
    
    @PutMapping("/edit/{productId}")
    public Product editProduct(@PathVariable Long productId, @RequestBody Product product) {
        return productService.editProduct(productId, product);
    }

    @DeleteMapping("/delete/{productId}")
    public Product deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }
}
