package zumbi;

import java.io.IOException;

public class Clear {
    
    public static void clear() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException e) {

        }
    }
}
