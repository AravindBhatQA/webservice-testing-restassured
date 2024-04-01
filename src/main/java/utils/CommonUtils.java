package utils;

import com.github.javafaker.Faker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class CommonUtils {
    public static Faker faker = new Faker();
    private static final Properties props = new Properties();
    private static final String CONFIG_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/config.properties";
    public static String formatDate(Date date){
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dmyFormat.format(date);
    }
    public static Properties getConfigProperties() throws IOException {
        try (FileInputStream in = new FileInputStream(CONFIG_FILE_PATH)) {
            props.load(in);
        }
        return props;
    }
    public static void writeInConfigProperties(String key, String value) throws IOException {
        Properties props = getConfigProperties();
        try (FileOutputStream out = new FileOutputStream(CONFIG_FILE_PATH)){
            props.setProperty(key, value);
            props.store(out, null);
        }
    }
    public static String generateIncorrectToken() throws IOException {
        String incorrectToken;
        String token = getConfigProperties().getProperty("token");
        while((incorrectToken = faker.name().username()).equals(token)){};
        return  incorrectToken;
    }
    public static String getToken() throws IOException {
        return getConfigProperties().getProperty("token");
    }


}
