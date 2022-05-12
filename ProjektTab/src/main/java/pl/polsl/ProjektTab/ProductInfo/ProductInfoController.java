package pl.polsl.ProjektTab.ProductInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-info")
@CrossOrigin
public class ProductInfoController {
        
    private final ProductInfoService productInfoService;

    @Autowired
    public ProductInfoController(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
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
