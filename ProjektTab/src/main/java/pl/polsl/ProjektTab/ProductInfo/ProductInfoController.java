package pl.polsl.ProjektTab.ProductInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-info")
public class ProductInfoController {
        
    private final ProductInfoService productInfoService;

    @Autowired
    public ProductInfoController(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
    }

    @GetMapping
    public List<ProductInfo> getProductInfo() {
        return productInfoService.getProductInfo();
    }

    @PostMapping
    public ProductInfo addProductInfo(@RequestBody ProductInfo productInfo) {
        return productInfoService.addProductInfo(productInfo);
    }

    @PutMapping("/{infoId}/category/{categoryId}")
    public ProductInfo assignCategoryToProductInfo(@PathVariable Long productInfoId, @PathVariable Long categoryId) {
        return productInfoService.assignCategoryToProductInfo(productInfoId, categoryId);
    }

    @PutMapping("/edit/{productInfoId}")
    public ProductInfo editProductInfo(@PathVariable Long productInfoId, @RequestBody ProductInfo productInfo) {
        return productInfoService.editProductInfo(productInfoId, productInfo);
    }

    @DeleteMapping("/delete/{productInfoId}")
    public ProductInfo deleteProductInfo(@PathVariable Long productInfoId) {
        return productInfoService.deleteProductInfo(productInfoId);
    }

}
