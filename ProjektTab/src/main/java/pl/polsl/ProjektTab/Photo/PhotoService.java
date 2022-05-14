package pl.polsl.ProjektTab.Photo;

import java.util.ArrayList;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.polsl.Exceptions.PhotoNotFoundException;
import pl.polsl.Exceptions.ProductInfoNotFoundException;
import pl.polsl.ProjektTab.Reference;
import pl.polsl.ProjektTab.ProductInfo.ProductInfo;
import pl.polsl.ProjektTab.ProductInfo.ProductInfoRepository;

@Service
public class PhotoService {
    
    private final PhotoRepository photoRepository;
    private final ProductInfoRepository productInfoRepository;
    private final Algorithm algorithm = Algorithm.HMAC256(Reference.JWTSecret);
    private final JWTVerifier verifier = JWT.require(algorithm).build();

    @Autowired
    public PhotoService(PhotoRepository photoRepository, ProductInfoRepository productInfoRepository) {
        this.photoRepository = photoRepository;
        this.productInfoRepository = productInfoRepository;
    }

    public ResponseEntity<List<Photo>> getPhotos() {
        return ResponseEntity.ok(photoRepository.findAll());
    }

    public ResponseEntity<List<Photo>> getPhotosByProductInfoId(Long productInfoId) {
        return ResponseEntity.ok(photoRepository.findByProductInfoId(productInfoId));
    }

    public ResponseEntity<Photo> addPhoto(String token, Photo photo) {
        try {
            verifier.verify(token);
            
            return ResponseEntity.ok(photoRepository.save(photo));
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<List<Photo>> batchAddPhoto(String token, List<Photo> photoList) {
        try {
            verifier.verify(token);
            
            return ResponseEntity.ok(photoRepository.saveAll(photoList));
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<Photo> assignProductInfoToPhoto(String token, Long photoId, Long productInfoId) {
        try {
            verifier.verify(token);

            Photo photo = photoRepository.findById(photoId).orElseThrow(() ->
                new PhotoNotFoundException(photoId)
            );
            ProductInfo productInfo = productInfoRepository.findById(productInfoId).orElseThrow(() ->
                new ProductInfoNotFoundException(productInfoId)
            );
            photo.setProductInfo(productInfo);   
            return ResponseEntity.ok(photoRepository.save(photo));
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<List<Photo>> batchAssignProductInfoToPhoto(String token, Long productInfoId, List<Photo> photoList) {
        try {
            verifier.verify(token);
            
            List<Photo> assignedPhotos = new ArrayList<>();
            for(Photo photo : photoList) {
                ResponseEntity<Photo> result = assignProductInfoToPhoto(token, photo.getId(), productInfoId);
                assignedPhotos.add(result.getBody());
            }
            return ResponseEntity.ok(assignedPhotos);
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<Photo> editPhoto(String token, Long photoId, Photo photo) {
        try {
            verifier.verify(token);
            
            Photo editedPhoto = photoRepository.findById(photoId).orElseThrow(() ->
                new PhotoNotFoundException(photoId)
            );
            if(photo.getPhotoUrl() != null)
                editedPhoto.setPhotoUrl(photo.getPhotoUrl());
            return ResponseEntity.ok(photoRepository.save(editedPhoto));
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<Photo> deletePhoto(String token, Long photoId) {
        try {
            verifier.verify(token);
            
            Photo photo = photoRepository.findById(photoId).orElseThrow(() ->
                new PhotoNotFoundException(photoId)
            );
            photoRepository.delete(photo);
            return ResponseEntity.ok(photo);
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
