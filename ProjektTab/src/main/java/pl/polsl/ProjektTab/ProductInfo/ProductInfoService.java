package pl.polsl.ProjektTab.ProductInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.polsl.Exceptions.CategoryNotFoundException;
import pl.polsl.Exceptions.ProductInfoNotFoundException;
import pl.polsl.ProjektTab.Category.Category;
import pl.polsl.ProjektTab.Category.CategoryRepository;
import pl.polsl.ProjektTab.Photo.Photo;
import pl.polsl.ProjektTab.Product.Product;

@Service
public class ProductInfoService {
        
    private final ProductInfoRepository productInfoRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductInfoService(ProductInfoRepository productInfoRepository, CategoryRepository categoryRepository) {
        this.productInfoRepository = productInfoRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductInfo> getProductInfo(String token) {
        return productInfoRepository.findAll();
    }

    public ProductInfo addProductInfo(String token, ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    public ProductInfo assignCategoryToProductInfo(String token, Long productInfoId, Long categoryId) {
        ProductInfo productInfo = productInfoRepository.findById(productInfoId).orElseThrow(() ->
            new ProductInfoNotFoundException(productInfoId)
        );
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
            new CategoryNotFoundException(categoryId)
        );
        productInfo.setCategory(category);
        return productInfoRepository.save(productInfo);
    }

    public ProductInfo editProductInfo(String token, Long productInfoId, ProductInfo productInfo) {
        ProductInfo editedProductInfo = productInfoRepository.findById(productInfoId).orElseThrow(() ->
            new ProductInfoNotFoundException(productInfoId)
        );
        if(productInfo.getProductName() != null)
            editedProductInfo.setProductName(productInfo.getProductName());
        if(productInfo.getDescription() != null)
            editedProductInfo.setDescription(productInfo.getDescription());
        if(productInfo.getBuyingPrice() != null)
            editedProductInfo.setBuyingPrice(productInfo.getBuyingPrice());
        if(productInfo.getSellingPrice() != null)
            editedProductInfo.setSellingPrice(productInfo.getSellingPrice());
        return productInfoRepository.save(editedProductInfo);
    }

    public ProductInfo deleteProductInfo(String token, Long productInfoId) {
        ProductInfo productInfo = productInfoRepository.findById(productInfoId).orElseThrow(() ->
            new ProductInfoNotFoundException(productInfoId)
        );
        for(Product product : productInfo.getProducts()) {
            product.setProductInfo(null);
        }
        for(Photo photo : productInfo.getPhotos()) {
            photo.setProductInfo(null);
        }
        productInfoRepository.delete(productInfo);
        return productInfo;
    }

}

