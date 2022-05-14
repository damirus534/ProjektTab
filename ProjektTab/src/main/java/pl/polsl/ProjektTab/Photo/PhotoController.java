package pl.polsl.ProjektTab.Photo;

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
@RequestMapping("/photos")
@CrossOrigin
public class PhotoController {
        
    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping
    public ResponseEntity<List<Photo>> getPhotos() {
        return photoService.getPhotos();
    }

    @GetMapping("/{productInfoId}")
    public ResponseEntity<List<Photo>> getPhotosByProductInfoId(@PathVariable Long productInfoId) {
        return photoService.getPhotosByProductInfoId(productInfoId);
    }

    @PostMapping
    public ResponseEntity<Photo> addPhoto(@RequestHeader(value = "Authorization") String token, @RequestBody Photo photo) {
        return photoService.addPhoto(token.substring(7), photo);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Photo>> batchAddPhoto(@RequestHeader(value = "Authorization") String token, @RequestBody List<Photo> photoList) {
        return photoService.batchAddPhoto(token.substring(7), photoList);
    }

    @PutMapping("/{photoId}/product-info/{productInfoId}")
    public ResponseEntity<Photo> assignProductInfoToPhoto(@RequestHeader(value = "Authorization") String token, @PathVariable Long photoId, @PathVariable Long productInfoId) {
        return photoService.assignProductInfoToPhoto(token.substring(7), photoId, productInfoId);
    }

    @PutMapping("/batch/product-info/{productInfoId}")
    public ResponseEntity<List<Photo>> batchAssignProductInfoToPhoto(@RequestHeader(value = "Authorization") String token, @PathVariable Long productInfoId, @RequestBody List<Photo> photoList) {
        return photoService.batchAssignProductInfoToPhoto(token.substring(7), productInfoId, photoList);
    }

    @PutMapping("/edit/{photoId}")
    public ResponseEntity<Photo> editPhoto(@RequestHeader(value = "Authorization") String token, @PathVariable Long photoId, @RequestBody Photo photo) {
        return photoService.editPhoto(token.substring(7), photoId, photo);
    }

    @DeleteMapping("/delete/{photoId}")
    public ResponseEntity<Photo> deletePhoto(@RequestHeader(value = "Authorization") String token, @PathVariable Long photoId) {
        return photoService.deletePhoto(token.substring(7), photoId);
    }
    
}
