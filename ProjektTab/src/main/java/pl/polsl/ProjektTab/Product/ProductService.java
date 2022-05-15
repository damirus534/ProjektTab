package pl.polsl.ProjektTab.Product;

import java.util.ArrayList;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.polsl.Exceptions.ProductInfoNotFoundException;
import pl.polsl.Exceptions.ProductNotFoundException;
import pl.polsl.ProjektTab.Reference;
import pl.polsl.ProjektTab.Cart.Cart;
import pl.polsl.ProjektTab.Filters.ProductOff;
import pl.polsl.ProjektTab.Filters.ReturnValue;
import pl.polsl.ProjektTab.Order.Order;
import pl.polsl.ProjektTab.ProductInfo.ProductInfo;
import pl.polsl.ProjektTab.ProductInfo.ProductInfoRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductInfoRepository productInfoRepository;
    private final Algorithm algorithm = Algorithm.HMAC256(Reference.JWTSecret);
    private final JWTVerifier verifier = JWT.require(algorithm).build();

    @Autowired
    public ProductService(ProductRepository productRepository, ProductInfoRepository productInfoRepository) {
        this.productRepository = productRepository;
        this.productInfoRepository = productInfoRepository;

    }

    public ResponseEntity<List<Product>> getProducts() { 
        return ResponseEntity.ok(productRepository.findAll());
    }
    
    public ResponseEntity<List<Product>> getProductsByProductInfoId(Long productInfoId) {
        return ResponseEntity.ok(productRepository.findByProductInfoId(productInfoId));
    }

    public ResponseEntity<Product> addProduct(String token, Product product) {
        try {
            verifier.verify(token);
            
            return ResponseEntity.ok(productRepository.save(product));
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<List<Product>> addProductBatch(String token, List<Product> productList) {
        try {
            verifier.verify(token);
            
            return ResponseEntity.ok(productRepository.saveAll(productList));
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<Product> assignProductInfoToProduct(String token, Long productId, Long productInfoId) {
        try {
            verifier.verify(token);
            Product product = productRepository.findById(productId).orElseThrow(() -> 
                new ProductNotFoundException(productId)
            );
            ProductInfo productInfo = productInfoRepository.findById(productInfoId).orElseThrow(() ->
                new ProductInfoNotFoundException(productInfoId)
            );
            product.setProductInfo(productInfo);
            return ResponseEntity.ok(productRepository.save(product));
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<List<Product>> batchAssignProductInfoToProduct(String token, Long productInfoId, List<Product> productList) {
        try {
            verifier.verify(token);
            
            List<Product> assignedProducts = new ArrayList<>();
            for(Product product : productList) {
                ResponseEntity<Product> result = assignProductInfoToProduct(token, product.getId(), productInfoId);
                assignedProducts.add(result.getBody());
            }
            return ResponseEntity.ok(assignedProducts);
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<Product> editProduct(String token, Long productId, Product product) {
        try {
            verifier.verify(token);
            
            Product editedProduct = productRepository.findById(productId).orElseThrow(() -> 
                new ProductNotFoundException(productId)
            );
            if(product.getAmountAvailable() != null)
                editedProduct.setAmountAvailable(product.getAmountAvailable());
            if(product.getSize() != null)
                editedProduct.setSize(product.getSize());
            return ResponseEntity.ok(productRepository.save(editedProduct));
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<List<Product>> batchEditProduct(String token, List<Product> productList) {
        try {
            verifier.verify(token);
            
            List<Product> editedProducts = new ArrayList<>();
            for(Product product : productList) {
                ResponseEntity<Product> result = editProduct(token, product.getId(), product);
                editedProducts.add(result.getBody());
            }
            return ResponseEntity.ok(editedProducts);
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<Product> deleteProduct(String token, Long productId) {
        try {
            verifier.verify(token);
            
            Product product = productRepository.findById(productId).orElseThrow(() -> 
                new ProductNotFoundException(productId)
            );
            for(Order order : product.getOrders()) {
                order.setProduct(null);
            }
            for(Cart cart : product.getCarts()) {
                cart.setProduct(null);
            }
            productRepository.delete(product);
            return ResponseEntity.ok(product);
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public List<ReturnValue> getProductsSeparated(){
        return productRepository.getSeperatedProduct();
    }

    public List<ReturnValue> getProductByKategory(Long id){return productRepository.getFilteredProduct(id);}

    public List<ProductOff> getProductByCategoryId(Long id){
        return productRepository.getProductByCategoryId(id);
    }
}

