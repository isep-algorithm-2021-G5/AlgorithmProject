package LIU.graph;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParser {
    String data;

    public JsonParser() throws IOException {
        this.data = new String(Files.readAllBytes(Paths.get("./src/result.json")));
    }

    public JSONObject getStops()  {
        JSONObject stopsJsonData = new JSONObject(this.data);
        return stopsJsonData;
    }
}
