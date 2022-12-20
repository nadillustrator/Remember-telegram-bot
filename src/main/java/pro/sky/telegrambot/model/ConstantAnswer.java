package pro.sky.telegrambot.model;

public class ConstantAnswer {


    public static final String STANDARDNOTIFICATION = "ДД.ММ.ГГГГ ЧЧ:ММ Текст напоминания";

    public static final String SKILLS = "Добавьте напоминание в виде" + "\n" +
            STANDARDNOTIFICATION + "\n" +
            "и я вовремя пришлю сообщение в этот чат.";

    public static final String STARTANSWER = "Привет, %s!";

    public static final String NOTIFICATIONSUCCESFULLYADDED = "Напоминание добавлено.";

    public static final String CANNOTRECOGNIZEREQUEST = "Не могу распознать запрос. Введите запрос в формате:"
            + "\n" + STANDARDNOTIFICATION;

    public static final String INCORRECTDATETIMEREQUEST = "Некорректно введена дата или время.";

    public static final String PASTDATETIMEREQUEST = "Вы ввели прошедшую дату или время.";
}
