package UserDataBase;

public class ContactUser {
    public String firstName;
    public String lastName;
    public long chatId;


    public ContactUser(long chatId) {
        UserDB.getUser(chatId);

        this.firstName = UserDB.getUser(chatId).firstName;
        this.lastName = UserDB.getUser(chatId).lastName;
        this.chatId = chatId;
    }
    public ContactUser() {
    }
}
