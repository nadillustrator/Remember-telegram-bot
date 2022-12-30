package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.telegrambot.model.NotificationTask;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {

    Collection<NotificationTask> findAllByTime(LocalDateTime time);

    @Transactional
    @Modifying
    @Query(value = "delete from notification_tasks where isshown = true", nativeQuery = true)
    void deleteShownNotificationTasks();

//    void deleteAllByShownIsTrue();


}
