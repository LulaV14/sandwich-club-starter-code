package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String ACTIVITY_NAME = JsonUtils.class.getName();

    public static Sandwich parseSandwichJson(String json) {
        String mainName, placeOfOrigin, description, image;
        JSONArray alsoKnownAs, ingredients;
        JSONObject name;
        Sandwich sandwichObject = null;
        try {
            JSONObject sandwichDetails = new JSONObject(json);
            name = sandwichDetails.getJSONObject("name");
            mainName = name.getString("mainName");
            alsoKnownAs = name.getJSONArray("alsoKnownAs");
            placeOfOrigin = sandwichDetails.getString("placeOfOrigin");
            description = sandwichDetails.getString("description");
            image = sandwichDetails.getString("image");
            ingredients = sandwichDetails.getJSONArray("ingredients");
            sandwichObject = new Sandwich(mainName, parseArray(alsoKnownAs), placeOfOrigin,
                    description, image, parseArray(ingredients));
        } catch(JSONException e) {
            Log.e(ACTIVITY_NAME, e.getMessage());
        }
        return sandwichObject;
    }

    private static List<String> parseArray(JSONArray array) {
        List<String> parsedArray = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                parsedArray.add(array.get(i).toString());
            }

        } catch(JSONException e) {
            Log.e(ACTIVITY_NAME, e.getMessage());
        }
        return parsedArray;
    }
}
