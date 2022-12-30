package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Photo;
import pro.sky.telegrambot.repository.PhotoRepository;

import java.util.Arrays;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public Long savePhoto(Update update) {
        PhotoSize photoSize = Arrays.stream(update.message().photo()).findFirst().get();
        String fileId = photoSize.fileId();
        Photo photo = new Photo();
        photo.setFileId(fileId);
        photoRepository.save(photo);
        return photoRepository.findPhotoByFileId(fileId).getId();
    }

    public Photo getById(Long photoId) {
        return photoRepository.getById(photoId);
    }
}
