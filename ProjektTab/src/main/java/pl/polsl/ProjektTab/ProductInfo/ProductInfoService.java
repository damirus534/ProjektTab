package pl.polsl.ProjektTab.ProductInfo;

import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.polsl.Exceptions.CategoryNotFoundException;
import pl.polsl.Exceptions.ProductInfoNotFoundException;
import pl.polsl.ProjektTab.Reference;
import pl.polsl.ProjektTab.Category.Category;
import pl.polsl.ProjektTab.Category.CategoryRepository;
import pl.polsl.ProjektTab.Photo.Photo;
import pl.polsl.ProjektTab.Product.Product;

@Service
public class ProductInfoService {
        
    private final ProductInfoRepository productInfoRepository;
    private final CategoryRepository categoryRepository;
    private final Algorithm algorithm = Algorithm.HMAC256(Reference.JWTSecret);
    private final JWTVerifier verifier = JWT.require(algorithm).build();

    @Autowired
    public ProductInfoService(ProductInfoRepository productInfoRepository, CategoryRepository categoryRepository) {
        this.productInfoRepository = productInfoRepository;
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<List<ProductInfo>> getProductInfo() {
        return ResponseEntity.ok(productInfoRepository.findAll());
    }

    public ResponseEntity<ProductInfo> addProductInfo(String token, ProductInfo productInfo) {
        try {
            verifier.verify(token);
            
            return ResponseEntity.ok(productInfoRepository.save(productInfo));
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<ProductInfo> assignCategoryToProductInfo(String token, Long productInfoId, Long categoryId) {
        try {
            verifier.verify(token);
            
            ProductInfo productInfo = productInfoRepository.findById(productInfoId).orElseThrow(() ->
                new ProductInfoNotFoundException(productInfoId)
            );
            Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new CategoryNotFoundException(categoryId)
            );
            productInfo.setCategory(category);
            return ResponseEntity.ok(productInfoRepository.save(productInfo));
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<ProductInfo> editProductInfo(String token, Long productInfoId, ProductInfo productInfo) {
        try {
            verifier.verify(token);
            
            ProductInfo editedProductInfo = productInfoRepository.findById(productInfoId).orElseThrow(() ->
                new ProductInfoNotFoundException(productInfoId)
            );
            if(productInfo.getProductName() != null)
                editedProductInfo.setProductName(productInfo.getProductName());
            if(productInfo.getCategory() != null)
                editedProductInfo.setCategory(productInfo.getCategory());
            if(productInfo.getDescription() != null)
                editedProductInfo.setDescription(productInfo.getDescription());
            if(productInfo.getBuyingPrice() != null)
                editedProductInfo.setBuyingPrice(productInfo.getBuyingPrice());
            if(productInfo.getSellingPrice() != null)
                editedProductInfo.setSellingPrice(productInfo.getSellingPrice());
            if(productInfo.getIsActive() != null)
                editedProductInfo.setIsActive(productInfo.getIsActive());
            return ResponseEntity.ok(productInfoRepository.save(editedProductInfo));
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<ProductInfo> deleteProductInfo(String token, Long productInfoId) {
        try {
            verifier.verify(token);
            
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
            return ResponseEntity.ok(productInfo);
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}

