package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static pro.sky.telegrambot.model.ConstantAnswer.*;

@Service
public class SendMessageService {

    @Autowired
    TelegramBot telegramBot;


    public void sendMessage(Long chatId, String message) {
        telegramBot.execute(new SendMessage(chatId, message));
    }

    public void sendMessage(Long chatId, String message, String userName) {
        String str = String.format(message, userName);
        telegramBot.execute(new SendMessage(chatId, str));
    }

    public void sendPhoto(Long chatId, String fileId) {
        telegramBot.execute(new SendPhoto(chatId, fileId));
    }

    public void sendWelcome(Update update) {
        Long chatId = update.message().chat().id();
        String userName = update.message().chat().firstName();
        sendMessage(chatId, STARTANSWER, userName);
        sendMessage(chatId, SKILLS);
    }

    public void sendCanNotRecognizeRequest(Update update) {
        Long chatId = update.message().chat().id();
        sendMessage(chatId, CANNOTRECOGNIZEREQUEST);
    }

    public void sendNotificationSuccessfullyAdded(Update update) {
        Long chatId = update.message().chat().id();
        sendMessage(chatId, NOTIFICATIONSUCCESFULLYADDED);
    }

    public void sendIncorrectDateTimeRequest(Update update) {
        Long chatId = update.message().chat().id();
        sendMessage(chatId, INCORRECTDATETIMEREQUEST);
    }

    public void sendPastDateTimeRequest(Update update) {
        Long chatId = update.message().chat().id();
        sendMessage(chatId, PASTDATETIMEREQUEST);
    }
}
