package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.PastTimeException;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.model.Photo;
import pro.sky.telegrambot.repository.NotificationTaskRepository;
import pro.sky.telegrambot.repository.PhotoRepository;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@Service
public class UpdateParsingService {

    @Autowired
    private NotificationTaskRepository notificationRepository;
    @Autowired
    private SendMessageService sendMessageService;
    @Autowired
    private PhotoService photoService;


    public void parseMessageToDateTimeAndTask(Update update, String message) {
        try {
            String task = extractTask(message);
            LocalDateTime time = extractDateAndTime(message);
            NotificationTask notification = new NotificationTask(update.message().chat().id(), task, time);
            if(update.message().photo() != null) {
                Long photoId = photoService.savePhoto(update);
                notification.setPhoto(photoService.getById(photoId));
            }
            notificationRepository.save(notification);
            sendMessageService.sendNotificationSuccessfullyAdded(update);
        } catch (DateTimeException e) {
            sendMessageService.sendIncorrectDateTimeRequest(update);
            e.printStackTrace();
        } catch (PastTimeException e) {
            sendMessageService.sendPastDateTimeRequest(update);
            e.printStackTrace();
        }

    }

    private LocalDateTime extractDateAndTime(String message) {
        int separator = 16;
        String dateTimeStr = message.substring(0, separator);
        String[] dateTime = dateTimeStr.split(" ");
        String[] date = dateTime[0].split("\\.");
        System.out.println(date[2] + "-" + date[1] + "-" + date[0] + "T" + dateTime[1]);
        LocalDateTime correctDateTime = LocalDateTime.parse(date[2] + "-" + date[1] + "-" + date[0] + "T" + dateTime[1]);
        checkCorrectDateTime(correctDateTime);
        return correctDateTime;
    }

    private void checkCorrectDateTime(LocalDateTime correctDateTime) {
        LocalDateTime currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        if (correctDateTime.isBefore(currentTime)) {
            throw new PastTimeException();
        }
    }

    private String extractTask(String message) {
        int separator = 17;
        String task = message.substring(separator);
        System.out.println(task);
        return task;
    }


}
