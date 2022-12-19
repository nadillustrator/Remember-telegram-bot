package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.IncorrectCommandException;

import static pro.sky.telegrambot.model.ConstantAnswer.*;

@Service
public class SlashCommandsService {
    @Autowired
    private SendMessageService sendMessageService;

    public void checkCommand(Update update) {
        String message = update.message().text();

        switch (message) {
            case "/start":
                sendMessageService.sendWelcome(update);
                break;
            default:
                sendMessageService.sendCanNotRecognizeRequest(update);
                throw new IncorrectCommandException();

        }

    }




}
