package pl.polsl.ProjektTab.ProductInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
