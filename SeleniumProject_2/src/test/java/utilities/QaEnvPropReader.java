package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class QaEnvPropReader {

    private static Properties properties;

    public static void init() {
        if (properties == null) {
            try {
                properties=new Properties();
                properties.load(new FileInputStream("src/test/resources/env.properties"));
                System.out.println(properties.getProperty("url"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
        public static String getProperty(String key){
            init();
            return properties.getProperty(key);
        }
    }

