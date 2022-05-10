package pl.polsl.ProjektTab.Photo;

import java.util.ArrayList;
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

    public List<Photo> getPhotosByProductInfoId(Long productInfoId) {
        return photoRepository.findByProductInfoId(productInfoId);
    }

    public Photo addPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public List<Photo> batchAddPhoto(List<Photo> photoList) {
        return photoRepository.saveAll(photoList);
    }

    public Photo assignProductInfoToPhoto(Long photoId, Long productInfoId) {
        Photo photo = photoRepository.findById(photoId).orElseThrow(() ->
            new PhotoNotFoundException(photoId)
        );
        ProductInfo productInfo = productInfoRepository.findById(productInfoId).orElseThrow(() ->
            new ProductInfoNotFoundException(productInfoId)
        );
        photo.setProductInfo(productInfo);
        return photoRepository.save(photo);
    }

    public List<Photo> batchAssignProductInfoToPhoto(Long productInfoId, List<Photo> photoList) {
        List<Photo> assignedPhotos = new ArrayList<>();
        for(Photo photo : photoList) {
            assignedPhotos.add(assignProductInfoToPhoto(photo.getId(), productInfoId));
        }
        return assignedPhotos;
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

}
