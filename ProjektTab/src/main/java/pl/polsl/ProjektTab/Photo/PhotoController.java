package pl.polsl.ProjektTab.Photo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/photos")
@CrossOrigin(origins = "http://localhost:4200")
public class PhotoController {
        
    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping
    public List<Photo> getPhotos() {
        return photoService.getPhotos();
    }

    @GetMapping("/urls")
    public List<String> getUrlsById(Long id){
        return photoService.getUrlsById(id);
    }

    @PostMapping
    public Photo addPhoto(@RequestBody Photo photo) {
        return photoService.addPhoto(photo);
    }

    @PutMapping("/{photoId}/product-info/{infoId}")
    public Photo assignProductInfoToPhoto(@PathVariable Long photoId, @PathVariable Long infoId) {
        return photoService.assignProductInfoToPhoto(photoId, infoId);
    }

    @PutMapping("/edit/{photoId}")
    public Photo editPhoto(@PathVariable Long photoId, @RequestBody Photo photo) {
        return photoService.editPhoto(photoId, photo);
    }

    @DeleteMapping("/delete/{photoId}")
    public Photo deletePhoto(@PathVariable Long photoId) {
        return photoService.deletePhoto(photoId);
    }
    
}
