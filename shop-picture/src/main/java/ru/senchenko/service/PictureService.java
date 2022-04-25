package ru.senchenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.senchenko.entities.PictureData;
import ru.senchenko.entities.Picture;
import ru.senchenko.repositories.PictureRepo;

import java.util.Optional;

@Service
@ConditionalOnProperty(name = "picture.storage.type", havingValue = "database")
public class PictureService {

    private final PictureRepo pictureRepo;

    @Autowired
    public PictureService(PictureRepo pictureRepo) {
        this.pictureRepo = pictureRepo;
    }

    public Optional<String> getPictureContentTypeById(Integer id) {
        return pictureRepo.findById(id).map(Picture::getContentType);
    }

    public Optional<byte[]> getPictureDataById(Integer id) {
        return pictureRepo.findById(id).map(p -> p.getPictureData().getData());
    }

    public PictureData createPictureData(byte[] picture) {
        return new PictureData(picture);
    }

    public void deleteById(Integer id) {
        pictureRepo.deleteById(id);
    }
}
