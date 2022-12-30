package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    Photo findPhotoByFileId(String fileId);

    Photo findPhotoById(Long id);
}
