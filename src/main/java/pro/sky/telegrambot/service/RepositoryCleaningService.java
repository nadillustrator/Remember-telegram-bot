package pro.sky.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

@Service
public class RepositoryCleaningService {

    @Autowired
    private NotificationTaskRepository notificationTaskRepository;

//        @Scheduled(cron = "0 0 6 * * *")
    @Scheduled(cron = "@hourly")
    @Transactional
    void cleanRepository() {
        notificationTaskRepository.deleteShownNotificationTasks();
    }

//    @Scheduled(cron = "0 0/1 * * * *")
//    private void cleanRepository() {
//        notificationTaskRepository.deleteAllByShownIsTrue();
//    }

}
