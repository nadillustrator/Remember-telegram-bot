package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.IncorrectCommandException;

@Service
public class UpdateService {

    @Autowired
    UpdateParsingService updateParsingService;
    @Autowired
    private SendMessageService sendMessageService;
    @Autowired
    SlashCommandsService slashCommandsService;

    private final String regex = "(\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2})(\\s|-|\\*)([ \\S]+)$";

    public void checkUpdate(Update update) {
        try {
            String message = update.message().text();
            if (message.startsWith("/")) {
                slashCommandsService.checkCommand(update);
            } else if (message.matches(regex)) {
                updateParsingService.parseMessageToDateTimeAndTask(update, message);
            } else {
                sendMessageService.sendCanNotRecognizeRequest(update);
            }
        } catch (NullPointerException | IncorrectCommandException e) {
            e.printStackTrace();
        }

    }
}

