package UserDataBase;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Iterator;


public class UserDB {
    public static MongoCollection<UserDBOne> collection = new MongoClient(new MongoClientURI(
            "mongodb+srv://vadumpast:paccword@cluster0.0e623.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"))
            .getDatabase("BotUsersTelegram")
            .withCodecRegistry(CodecRegistries.fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())))
            .getCollection("BotUsersTelegram", UserDBOne.class);



    public CodecRegistry registry= CodecRegistries.fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
    );



    public static void addUser(Message message){
        collection.insertOne(new UserDBOne(message));
    }


    public static void changeField(Message message, boolean a){
        collection.updateOne(
                Filters.eq("chatId",message.getChatId()),
                Updates.set("isInAdding",a)
        );
    }

    public static void addContact(Message message, Long chatId){
        collection.updateOne(
                Filters.eq("chatId",message.getChatId()),
                Updates.push("contactList",new ContactUser(chatId))
        );
    }

    public static UserDBOne getUser(long chatId){

        FindIterable<UserDBOne> iterDoc = collection.find();

        Iterator it = iterDoc.iterator();

        while (it.hasNext()) {
            UserDBOne user = (UserDBOne) it.next();
            if(user.getChatId() == chatId)return user;
        }
        return null;
    }



}