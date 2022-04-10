package pl.polsl.ProjektTab.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.polsl.Exceptions.CategoryNotFoundException;
import pl.polsl.ProjektTab.ProductInfo.ProductInfo;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category editCategory(Long categoryId, Category category) {
        Category editedCategory = categoryRepository.findById(categoryId).orElseThrow(() ->
            new CategoryNotFoundException(categoryId)
        );
        if(category.getCategoryName() != null)
            editedCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(editedCategory);
    }

    public Category deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
            new CategoryNotFoundException(categoryId)
        );
        for(ProductInfo productInfo : category.getProductInfo()) {
            productInfo.setCategory(null);
        }
        categoryRepository.delete(category);
        return category;
    }
    
}
