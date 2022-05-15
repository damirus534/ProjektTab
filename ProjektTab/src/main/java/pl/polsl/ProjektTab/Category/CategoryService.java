package pl.polsl.ProjektTab.Category;

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
import pl.polsl.ProjektTab.Reference;
import pl.polsl.ProjektTab.ProductInfo.ProductInfo;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final Algorithm algorithm = Algorithm.HMAC256(Reference.JWTSecret);
    private final JWTVerifier verifier = JWT.require(algorithm).build();

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<List<Category>> getCategories(String token) {
        // Backend verification of JWT token - if signature is correct etc.
        try {
            token = token.substring(7);
            verifier.verify(token);
            return ResponseEntity.ok(categoryRepository.findAll());
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    public ResponseEntity<Category> addCategory(String token, Category category) {
        try {
            token = token.substring(7);
            verifier.verify(token);
            return ResponseEntity.ok(categoryRepository.save(category));
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public Category editCategory(String token, Long categoryId, Category category) {
        Category editedCategory = categoryRepository.findById(categoryId).orElseThrow(() ->
            new CategoryNotFoundException(categoryId)
        );
        if(category.getCategoryName() != null)
            editedCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(editedCategory);
    }

    public ResponseEntity<Category>  deleteCategory(String token, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
            new CategoryNotFoundException(categoryId)
        );
        for(ProductInfo productInfo : category.getProductInfo()) {
            productInfo.setCategory(null);
        }
        categoryRepository.delete(category);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public List<Category> getCategoriesUnLog() {
        return categoryRepository.findAll();
    }
}
