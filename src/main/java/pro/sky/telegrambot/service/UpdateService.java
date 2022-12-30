package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.IncorrectCommandException;

import java.util.Arrays;

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
            if (update.message().text() != null && update.message().text().startsWith("/")) {
                slashCommandsService.checkCommand(update);
            } else if (update.message().text() != null && update.message().text().matches(regex)) {
                updateParsingService.parseMessageToDateTimeAndTask(update, update.message().text());
            }else if(update.message().photo() != null
                    && update.message().caption() != null
                    && update.message().caption().matches(regex)) {
                updateParsingService.parseMessageToDateTimeAndTask(update, update.message().caption());
            } else {
                sendMessageService.sendCanNotRecognizeRequest(update);
            }
        } catch (NullPointerException | IncorrectCommandException e) {
            e.printStackTrace();
        }

    }
}

