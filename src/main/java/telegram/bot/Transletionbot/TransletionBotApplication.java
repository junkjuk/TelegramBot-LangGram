package telegram.bot.Transletionbot;

import UserDataBase.UserDB;
import UserDataBase.UserDBOne;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

@SpringBootApplication
public class TransletionBotApplication {




	public static void main(String[] args) throws TelegramApiException {
		SpringApplication.run(TransletionBotApplication.class, args);

		TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(new BotTranslater());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}

	}

}
