package telegram.bot.Transletionbot;

import UserDataBase.UserDB;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.Transletionbot.Menus.Menus;

import java.util.Objects;

import static java.lang.String.valueOf;


public class BotTranslater extends TelegramLongPollingBot {
    private static final String YOUR_PROFILE_REQUEST ="Your Profile";
    private static final String SENDING_SETTINGS_REQUEST ="Settings";
    private static final String CONTACT_LIST_REQUEST ="Contact list";
    private static final String ADD_TO_CONTACT_LIST_REQUEST ="Add to contact list";
    private static final String EXIT_REQUEST ="Exit";


    public String getBotUsername() {
        return "LangGramBot";
    }

    public String getBotToken() {
        return "1474723881:AAFboB7tftao2GR6nwstZbEcrClraUJ8ras";
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();



        //SendMessage respone = new SendMessage();
        //respone.setText(BotBrain.TransM(message.getText(),"uk","en"));

        if (UserDB.getUser(message.getChatId()) == null){
            UserDB.addUser(message);
        }

        try {
            execute(getResponseMessage(message));

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }




    private SendMessage getResponseMessage(Message message){
        SendMessage mess = new SendMessage();
        mess.setChatId(valueOf(message.getChatId()));

        if(UserDB.getUser(message.getChatId()).isInAdding!=null){
            if (UserDB.getUser(message.getChatId()).isInAdding == true){
                System.out.println(UserDB.getUser(message.getChatId()).isInAdding);
                if (UserDB.getUser(Long.parseLong(message.getText())) != null){
                    mess.setText("Ми додали користувача до вашого списку контактів");
                    UserDB.getUser(message.getChatId()).isInAdding = false;
                    mess.setReplyMarkup(Menus.getMainMenu());
                    return mess;
                }
                else {
                    mess.setText("Ідентифікаційний номер користувача не дійсний, спробуйте знову");
                    return mess;
                }
            }
        }
        else {
            switch (message.getText()){
                case YOUR_PROFILE_REQUEST:
                    mess.setReplyMarkup(Menus.getProfileMenu());
                    mess.setText(YOUR_PROFILE_REQUEST);
                    //mess.setParseMode("HTML");
                    mess.setText(Objects.requireNonNull(UserDB.getUser(message.getChatId())).firstName
                            +" "+Objects.requireNonNull(UserDB.getUser(message.getChatId())).lastName+"\uD83D\uDC76"
                            +"\n"+"Ваш ідентифікаційний номер: "+Objects.requireNonNull(UserDB.getUser(message.getChatId())).chatId
                            +"\n"+"Ваша мова "+Objects.requireNonNull(UserDB.getUser(message.getChatId())).yourLang
                            +"\n"+"Мова на яку буде переклад "+Objects.requireNonNull(UserDB.getUser(message.getChatId())).sourceLang
                            +"\n"+"Мова з якої буде переклад "+Objects.requireNonNull(UserDB.getUser(message.getChatId())).targetLang
                    );
                    UserDB.addContact(message,"ua");
                    UserDB.getUser(message.getChatId()).isInAdding = false;
                    return mess;

                case SENDING_SETTINGS_REQUEST:
                    mess.setReplyMarkup(Menus.getSettingsMenu(message));
                    mess.setText(SENDING_SETTINGS_REQUEST);
                    UserDB.getUser(message.getChatId()).isInAdding = false;
                    return mess;
                case ADD_TO_CONTACT_LIST_REQUEST:
                    UserDB.getUser(message.getChatId()).isInAdding = true;
                    mess.setText("Введіть ідентифікаційний номер користувача");
                    return mess;
                case EXIT_REQUEST:
                    mess.setText("Ви перенесены на початкове меню");
                    mess.setReplyMarkup(Menus.getMainMenu());
                    UserDB.getUser(message.getChatId()).isInAdding = false;
                    return mess;
                default:
                    mess.setText("You are new user");
                    mess.setReplyMarkup(Menus.getMainMenu());
                    UserDB.getUser(message.getChatId()).isInAdding = false;
                    return mess;
            }
        }
        return mess;
    }




}

