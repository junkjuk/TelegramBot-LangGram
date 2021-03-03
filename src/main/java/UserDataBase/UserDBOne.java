package UserDataBase;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;

public class UserDBOne implements AbstractUser {


    public long chatId;
    public int id;
    public String firstName;
    public String lastName;
    public String targetLang;
    public String sourceLang;
    public String yourLang;
    public Boolean isInAdding;
    public long chatUserId;
    public String firstNameTarget;
    public boolean isInChat;
    public boolean isInChoise;


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }







    public ArrayList<ContactUser> contactList = new ArrayList<ContactUser>();

    public void addContact(Long chatId){
        contactList.add(new ContactUser(chatId));
    }

    public ArrayList<ContactUser> getContactList() {
        return contactList;
    }

    public void setContactList(ArrayList<ContactUser> contactList) {
        this.contactList = contactList;
    }




    public UserDBOne(Message message) {
        this.id =  message.getFrom().getId();
        this.chatId = message.getChatId();
        this.firstName = message.getFrom().getFirstName();
        this.lastName = message.getFrom().getLastName();
        this.isInAdding = false;
        this.targetLang = message.getFrom().getLanguageCode();
        this.yourLang = message.getFrom().getLanguageCode();
        this.sourceLang = message.getFrom().getLanguageCode();
        this.chatUserId = message.getChatId();
        this.isInChat = false;
        this.isInChoise = false;
        this.firstNameTarget = message.getFrom().getFirstName();
    }

    public UserDBOne() {
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName() {

    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName() {

    }




    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTargetLang() {
        return targetLang;
    }

    public void setTargetLang(String targenLang) {
        this.targetLang = targenLang;
    }

    public String getSourceLang() {
        return sourceLang;
    }

    public void setSourceLang(String sourceLang) {
        this.sourceLang = sourceLang;
    }

    public String getYourLang() {
        return yourLang;
    }

    public void setYourLang(String yourLang) {
        this.yourLang = yourLang;
    }
}
