package pro.sky.telegrambot.model;

import com.pengrad.telegrambot.model.Chat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "notification_tasks")
public class NotificationTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String task;
    @Column(name = "dateandtime")
    private LocalDateTime time;
    @Column(name = "isshown")
    private boolean isShown;

    @OneToOne
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;

    public NotificationTask() {
    }

    public NotificationTask(Long chatId, String task, LocalDateTime time) {
        this.chatId = chatId;
        this.task = task;
        this.time = time;
        this.isShown = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public boolean isShown() {
        return isShown;
    }

    public void setShown(boolean shown) {
        isShown = shown;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationTask)) return false;
        NotificationTask task1 = (NotificationTask) o;
        return Objects.equals(id, task1.id) && Objects.equals(chatId, task1.chatId) && Objects.equals(task, task1.task) && Objects.equals(time, task1.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, task, time);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", task='" + task + '\'' +
                ", time=" + time +
                ", isShown=" + isShown +
                '}';
    }
}
