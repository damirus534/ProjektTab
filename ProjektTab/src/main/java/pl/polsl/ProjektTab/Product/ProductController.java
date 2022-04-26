package pl.polsl.ProjektTab.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Product> getProducts() {
        return productService.getProducts();
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
