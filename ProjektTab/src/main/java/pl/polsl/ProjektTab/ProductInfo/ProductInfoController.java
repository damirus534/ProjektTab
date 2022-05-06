package pl.polsl.ProjektTab.ProductInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ProductInfo> getProductInfo(@RequestHeader(value = "Authorization") String token) {
        return productInfoService.getProductInfo(token);
    }

    @PostMapping
    public ProductInfo addProductInfo(@RequestHeader(value = "Authorization") String token, @RequestBody ProductInfo productInfo) {
        return productInfoService.addProductInfo(token, productInfo);
    }

    @PutMapping("/{productInfoId}/category/{categoryId}")
    public ProductInfo assignCategoryToProductInfo(@RequestHeader(value = "Authorization") String token, @PathVariable Long productInfoId, @PathVariable Long categoryId) {
        return productInfoService.assignCategoryToProductInfo(token, productInfoId, categoryId);
    }

    @PutMapping("/edit/{productInfoId}")
    public ProductInfo editProductInfo(@RequestHeader(value = "Authorization") String token, @PathVariable Long productInfoId, @RequestBody ProductInfo productInfo) {
        return productInfoService.editProductInfo(token, productInfoId, productInfo);
    }

    @DeleteMapping("/delete/{productInfoId}")
    public ProductInfo deleteProductInfo(@RequestHeader(value = "Authorization") String token, @PathVariable Long productInfoId) {
        return productInfoService.deleteProductInfo(token, productInfoId);
    }

}
