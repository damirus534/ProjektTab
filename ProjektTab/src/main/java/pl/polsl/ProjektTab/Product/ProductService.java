package pl.polsl.ProjektTab.Product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.polsl.Exceptions.ProductInfoNotFoundException;
import pl.polsl.Exceptions.ProductNotFoundException;
import pl.polsl.ProjektTab.Cart.Cart;
import pl.polsl.ProjektTab.Order.Order;
import pl.polsl.ProjektTab.ProductInfo.ProductInfo;
import pl.polsl.ProjektTab.ProductInfo.ProductInfoRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductInfoRepository productInfoRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductInfoRepository productInfoRepository) {
        this.productRepository = productRepository;
        this.productInfoRepository = productInfoRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    
    public List<Product> getProductsByProductInfoId(Long productInfoId) {
        return productRepository.findByProductInfoId(productInfoId);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> addProductBatch(List<Product> productList) {
        return productRepository.saveAll(productList);
    }

    public Product assignProductInfoToProduct(Long productId, Long productInfoId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> 
            new ProductNotFoundException(productId)
        );
        ProductInfo productInfo = productInfoRepository.findById(productInfoId).orElseThrow(() ->
            new ProductInfoNotFoundException(productInfoId)
        );
        product.setProductInfo(productInfo);
        return productRepository.save(product);
    }

    public List<Product> batchAssignProductInfoToProduct(Long productInfoId, List<Product> productList) {
        List<Product> assignedProducts = new ArrayList<>();
        for(Product product : productList) {
            assignedProducts.add(assignProductInfoToProduct(product.getId(), productInfoId));
        }
        return assignedProducts;
    }

    public Product editProduct(Long productId, Product product) {
        Product editedProduct = productRepository.findById(productId).orElseThrow(() -> 
            new ProductNotFoundException(productId)
        );
        if(product.getAmountAvailable() != null)
            editedProduct.setAmountAvailable(product.getAmountAvailable());
        if(product.getSize() != null)
            editedProduct.setSize(product.getSize());
        return productRepository.save(editedProduct);
    }

    public List<Product> batchEditProduct(List<Product> productList) {
        List<Product> editedProducts = new ArrayList<>();
        for(Product product : productList) {
            editedProducts.add(editProduct(product.getId(), product));
        }
        return editedProducts;
    }

    public Product deleteProduct(Long productId) {
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
        return product;
    }

}
