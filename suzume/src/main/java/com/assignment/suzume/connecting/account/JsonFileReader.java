package com.assignment.suzume.connecting.account;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.Reader;

public class JsonFileReader {
    private JsonObject jsonObject;

    private JsonFileReader(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public static JsonFileReader createJsonFileReader(Reader reader) {
        JsonReader jsonReader = Json.createReader(reader);
        JsonObject jsonObject = jsonReader.readObject();
        return new JsonFileReader(jsonObject);
    }

    public String getStringValue(String key) {
        return jsonObject.getString(key);
    }
}
