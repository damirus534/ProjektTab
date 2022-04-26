package pl.polsl.ProjektTab.Photo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.polsl.Exceptions.PhotoNotFoundException;
import pl.polsl.Exceptions.ProductInfoNotFoundException;
import pl.polsl.ProjektTab.ProductInfo.ProductInfo;
import pl.polsl.ProjektTab.ProductInfo.ProductInfoRepository;

@Service
public class PhotoService {
    
    private final PhotoRepository photoRepository;
    private final ProductInfoRepository productInfoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository, ProductInfoRepository productInfoRepository) {
        this.photoRepository = photoRepository;
        this.productInfoRepository = productInfoRepository;
    }

    public List<Photo> getPhotos() {
        return photoRepository.findAll();
    }

    public Photo addPhoto(Photo photo) {
        return photoRepository.save(photo);

    }

    public Photo assignProductInfoToPhoto(Long photoId, Long infoId) {
        Photo photo = photoRepository.findById(photoId).orElseThrow(() ->
            new PhotoNotFoundException(photoId)
        );
        ProductInfo productInfo = productInfoRepository.findById(infoId).orElseThrow(() ->
            new ProductInfoNotFoundException(infoId)
        );
        photo.setProductInfo(productInfo);
        return photoRepository.save(photo);
    }

    public Photo editPhoto(Long photoId, Photo photo) {
        Photo editedPhoto = photoRepository.findById(photoId).orElseThrow(() ->
            new PhotoNotFoundException(photoId)
        );
        if(photo.getPhotoUrl() != null)
            editedPhoto.setPhotoUrl(photo.getPhotoUrl());
        return photoRepository.save(editedPhoto);
    }

    public Photo deletePhoto(Long photoId) {
        Photo photo = photoRepository.findById(photoId).orElseThrow(() ->
            new PhotoNotFoundException(photoId)
        );
        photoRepository.delete(photo);
        return photo;
    }
    public List<String> getUrlsById(Long id){
        return photoRepository.findAllPhotos(id);
    }
}
