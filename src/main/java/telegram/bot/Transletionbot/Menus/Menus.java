package telegram.bot.Transletionbot.Menus;

import UserDataBase.UserDB;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Menus {
    private static final String YOUR_PROFILE_REQUEST ="Your Profile";
    private static final String SENDING_SETTINGS_REQUEST ="Settings";
    private static final String CONTACT_LIST_REQUEST ="Contact list";
    private static final String ADD_TO_CONTACT_LIST_REQUEST ="Add to contact list";
    private static final String EXIT_REQUEST ="Exit";




    public static ReplyKeyboardMarkup getMainMenu() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(YOUR_PROFILE_REQUEST);
        row1.add(SENDING_SETTINGS_REQUEST);
        KeyboardRow row2 = new KeyboardRow();
        row2.add(CONTACT_LIST_REQUEST);
        row2.add(ADD_TO_CONTACT_LIST_REQUEST);
        KeyboardRow row3 = new KeyboardRow();
        row3.add(EXIT_REQUEST);

        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        markup.setKeyboard(rows);
        return markup;
    }
    public static ReplyKeyboardMarkup getProfileMenu(){
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(EXIT_REQUEST);
        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(row1);
        markup.setKeyboard(rows);
        return markup;
    }
    public static ReplyKeyboardMarkup getAddedMenu(){
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(EXIT_REQUEST);
        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(row1);
        markup.setKeyboard(rows);
        return markup;
    }

    public static ReplyKeyboardMarkup getSettingsMenu(Message message){
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();




        KeyboardRow row1 = new KeyboardRow();

        row1.add("Your languages"+ "\n"+ Objects.requireNonNull(UserDB.getUser(message.getChatId())).yourLang);
        row1.add("Target languages"+ "\n"+ Objects.requireNonNull(UserDB.getUser(message.getChatId())).targetLang);
        row1.add("Source languages"+ "\n"+ Objects.requireNonNull(UserDB.getUser(message.getChatId())).sourceLang);

        KeyboardRow row2 = new KeyboardRow();
        row2.add("Exit");

        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);
        markup.setKeyboard(rows);
        return markup;
    }
}
