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

    @GetMapping("/{productInfoId}")
    public List<Product> getProductsByProductInfoId(@PathVariable Long productInfoId) {
        return productService.getProductsByProductInfoId(productInfoId);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PostMapping("/batch")
    public List<Product> addProductBatch(@RequestBody List<Product> productList) {
        return productService.addProductBatch(productList);
    }

    @PutMapping("/{productId}/product-info/{productInfoId}")
    public Product assignProductInfoToProduct(@PathVariable Long productId, @PathVariable Long productInfoId) {
        return productService.assignProductInfoToProduct(productId, productInfoId);
    }

    @PutMapping("/batch-assign/product-info/{productInfoId}")
    public List<Product> batchAssignProductInfoToProduct(@PathVariable Long productInfoId, @RequestBody List<Product> productList) {
        return productService.batchAssignProductInfoToProduct(productInfoId, productList);
    }
    
    @PutMapping("/edit/{productId}")
    public Product editProduct(@PathVariable Long productId, @RequestBody Product product) {
        return productService.editProduct(productId, product);
    }

    @PutMapping("/edit/batch")
    public List<Product> batchEditProduct(@RequestBody List<Product> productList) {
        return productService.batchEditProduct(productList);
    }

    @DeleteMapping("/delete/{productId}")
    public Product deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }
}
