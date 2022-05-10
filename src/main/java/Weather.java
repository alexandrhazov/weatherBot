import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    //d7f1afccb1cd04e81da986c079fbbd53
    //https://api.unsplash.com/search/photos?page=1&query=office
    //XDEJ82v1bKlyjTEL2eY6Tt2zAGhWZ8r1X8NOkRSYaY8
    //https://api.unsplash.com/search/photos?page=1&query=TelAviv&client_id=XDEJ82v1bKlyjTEL2eY6Tt2zAGhWZ8r1X8NOkRSYaY8&per_page=20


    public static String getWeather(String message, Model model) throws IOException {
        URL url =  new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=d7f1afccb1cd04e81da986c079fbbd53");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";

        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon(obj.getString("icon"));
            model.setMain(obj.getString("main"));
        }

        return "City: " + model.getName() + "\n" +
                "Temparature: " + Math.round(model.getTemp()) + "C" + "\n" +
                "Humidity: " + model.getHumidity() + "%" + "\n" +
                "Main: " + model.getMain() + "\n";
    }
}
