package pl.polsl.ProjektTab.Photo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photos")
@CrossOrigin
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
