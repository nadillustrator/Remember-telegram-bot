package pro.sky.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

@Service
public class ScheduleNotificationService {

    @Autowired
    private NotificationTaskRepository notificationTaskRepository;
    @Autowired
    private SendMessageService sendMessageService;

    @Scheduled(cron = "0 0/1 * * * *")
    public void checkRepository() {
        Collection<NotificationTask> notificationTasks = findActualNotification();
        notificationTasks.forEach(n ->
                sendMessageService.sendMessage(n.getChatId(), n.getTask())
        );
        markAsShown(notificationTasks);
    }

    private Collection<NotificationTask> findActualNotification() {
        LocalDateTime currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        System.out.println(currentTime);
        return notificationTaskRepository.findAllByTime(currentTime);
    }

    private void markAsShown(Collection<NotificationTask> notificationTasks) {
        notificationTasks.forEach(notificationTask ->
                notificationTask.setShown(true)
        );
        notificationTaskRepository.saveAll(notificationTasks);
    }
}
