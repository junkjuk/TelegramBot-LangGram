package google.translate;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;


public class BotBrain {


    public static String TransM(String Mess, String input, String output) {
        Translate translate = TranslateOptions.getDefaultInstance().getService();

        Translation translation = translate.translate(
                Mess,
                Translate.TranslateOption.sourceLanguage(input),
                Translate.TranslateOption.targetLanguage(output));
        return translation.getTranslatedText();
    }



}
