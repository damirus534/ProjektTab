package pl.polsl.ProjektTab.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.ProjektTab.Filters.ProductOff;
import pl.polsl.ProjektTab.Filters.ReturnValue;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
        
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Product>> addProductBatch(@RequestHeader(value = "Authorization") String token, @RequestBody List<Product> productList) {
        return productService.addProductBatch(token.substring(7), productList);
    }
    @PutMapping("/batch-assign/product-info/{productInfoId}")
    public ResponseEntity<List<Product>> batchAssignProductInfoToProduct(@RequestHeader(value = "Authorization") String token, @PathVariable Long productInfoId, @RequestBody List<Product> productList) {
        return productService.batchAssignProductInfoToProduct(token.substring(7), productInfoId, productList);
    }

    @GetMapping("/main")
    public List<ReturnValue> getSeperateProducts(){
        return productService.getProductsSeparated();
    }

    @GetMapping("/main/category")
    public List<ReturnValue> getProductsByFilter(@RequestParam Long id){
        return productService.getProductByKategory(id);
    }
    @GetMapping("/main/category/id")
    public List<ProductOff> getProductsByCategoryId(Long id){
        return productService.getProductByCategoryId(id);
    }


    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestHeader(value = "Authorization") String token, @RequestBody Product product) {
        return productService.addProduct(token.substring(7), product);
    }

    @PutMapping("/{productId}/product-info/{productInfoId}")
    public ResponseEntity<Product> assignProductInfoToProduct(@RequestHeader(value = "Authorization") String token, @PathVariable Long productId, @PathVariable Long productInfoId) {
        return productService.assignProductInfoToProduct(token.substring(7), productId, productInfoId);
    }

    @PutMapping("/edit/{productId}")
    public ResponseEntity<Product> editProduct(@RequestHeader(value = "Authorization") String token, @PathVariable Long productId, @RequestBody Product product) {
        return productService.editProduct(token.substring(7), productId, product);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Product> deleteProduct(@RequestHeader(value = "Authorization") String token, @PathVariable Long productId) {
        return productService.deleteProduct(token.substring(7), productId);
    }
}
