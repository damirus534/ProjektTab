package pl.polsl.ProjektTab.ProductInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-info")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductInfoController {
        
    private final ProductInfoService productInfoService;

    @Autowired
    public ProductInfoController(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
    }

    @GetMapping("/info")
    public ProductInfo getProductInfoByid(Long id){
        return this.productInfoService.getProductInfoById(id);
    }
    @GetMapping
    public ResponseEntity<List<ProductInfo>> getProductInfo() {
        return productInfoService.getProductInfo();
    }

    @PostMapping
    public ResponseEntity<ProductInfo> addProductInfo(@RequestHeader(value = "Authorization") String token, @RequestBody ProductInfo productInfo) {
        return productInfoService.addProductInfo(token.substring(7), productInfo);
    }

    @PutMapping("/{productInfoId}/category/{categoryId}")
    public ResponseEntity<ProductInfo> assignCategoryToProductInfo(@RequestHeader(value = "Authorization") String token, @PathVariable Long productInfoId, @PathVariable Long categoryId) {
        return productInfoService.assignCategoryToProductInfo(token.substring(7), productInfoId, categoryId);
    }

    @PutMapping("/edit/{productInfoId}")
    public ResponseEntity<ProductInfo> editProductInfo(@RequestHeader(value = "Authorization") String token, @PathVariable Long productInfoId, @RequestBody ProductInfo productInfo) {
        return productInfoService.editProductInfo(token.substring(7), productInfoId, productInfo);
    }

    @DeleteMapping("/delete/{productInfoId}")
    public ResponseEntity<ProductInfo> deleteProductInfo(@RequestHeader(value = "Authorization") String token, @PathVariable Long productInfoId) {
        return productInfoService.deleteProductInfo(token.substring(7), productInfoId);
    }

}
