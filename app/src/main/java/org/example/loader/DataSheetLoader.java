package org.example.loader;

import com.google.gson.Gson;
import org.example.model.datasheet.DataSheet;

import java.io.InputStream;
import java.io.InputStreamReader;

public class DataSheetLoader {

    public static DataSheet load(String resourcePath) {
        Gson gson = new Gson();
        InputStream is = DataSheetLoader.class.getResourceAsStream(resourcePath);
        return gson.fromJson(new InputStreamReader(is), DataSheet.class);
    }
}
