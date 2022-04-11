package pl.polsl.ProjektTab.Product;

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

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product assignProductInfoToProduct(Long productId, Long infoId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> 
            new ProductNotFoundException(productId)
        );
        ProductInfo productInfo = productInfoRepository.findById(infoId).orElseThrow(() ->
            new ProductInfoNotFoundException(infoId)
        );
        product.setProductInfo(productInfo);
        return productRepository.save(product);
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
