package UserDataBase;

public interface AbstractUser {

    int getId();
    void setId(int id);


    long getChatId();
    void setChatId(long chatId);

    String getFirstName();
    void setFirstName();

    String getLastName();
    void setLastName();

}