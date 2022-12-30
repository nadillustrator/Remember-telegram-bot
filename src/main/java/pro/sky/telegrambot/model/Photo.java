package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_id")
    private String fileId;


    public Photo(Long id, String fileId) {
        this.id = id;
        this.fileId = fileId;
    }

    public Photo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id) && Objects.equals(fileId, photo.fileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileId);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", fileId='" + fileId + '\'' +
                '}';
    }
}
