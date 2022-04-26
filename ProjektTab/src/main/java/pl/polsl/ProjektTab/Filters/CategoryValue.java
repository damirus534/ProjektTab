package pl.polsl.ProjektTab.Filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor

@ToString
public class CategoryValue {
    private Long id;
    private String description;
    private String productName;
    private List<String> photoUrl;

    public CategoryValue(Long id, String description, String productName,String photoURL) {
        this.id = id;
        this.description = description;
        this.productName = productName;
        this.photoUrl=new ArrayList<>();
        this.photoUrl.add(photoURL);
    }
}
