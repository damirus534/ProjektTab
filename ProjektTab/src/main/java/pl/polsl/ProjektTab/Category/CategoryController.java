package pl.polsl.ProjektTab.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories(@RequestHeader(value = "Authorization") String token) {
        return categoryService.getCategories(token);
    }
    @GetMapping("/all")
    public List<Category> getCategoriesUnLog(){
        return categoryService.getCategoriesUnLog();
    }


    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestHeader(value = "Authorization") String token, @RequestBody Category category) {
        return categoryService.addCategory(token, category);
    }

    @PutMapping("/edit/{categoryId}")
    public Category editCategory(@RequestHeader(value = "Authorization") String token, @PathVariable Long categoryId, @RequestBody Category category) {
        return categoryService.editCategory(token, categoryId, category);
    }

    @DeleteMapping("delete/{categoryId}")
    public ResponseEntity<Category> deleteCategory(@RequestHeader(value = "Authorization") String token, @PathVariable Long categoryId) {
        return categoryService.deleteCategory(token, categoryId);
    }

}
