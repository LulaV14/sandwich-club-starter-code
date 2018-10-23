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

    private static final String KEY_NAME = "name",
                                KEY_MAIN_NAME = "mainName",
                                KEY_ALSO_KNOWN_AS = "alsoKnownAs",
                                KEY_PLACE_OF_ORIGIN = "placeOfOrigin",
                                KEY_DESCRIPTION = "description",
                                KEY_INGREDIENTS = "ingredients",
                                KEY_IMAGE = "image";

    public static Sandwich parseSandwichJson(String json) {
        String mainName, placeOfOrigin, description, image;
        JSONArray alsoKnownAs, ingredients;
        JSONObject name;
        Sandwich sandwichObject = null;
        try {
            JSONObject sandwichDetails = new JSONObject(json);
            name = sandwichDetails.getJSONObject(KEY_NAME);
            mainName = name.getString(KEY_MAIN_NAME);
            alsoKnownAs = name.getJSONArray(KEY_ALSO_KNOWN_AS);
            placeOfOrigin = sandwichDetails.getString(KEY_PLACE_OF_ORIGIN);
            description = sandwichDetails.getString(KEY_DESCRIPTION);
            image = sandwichDetails.getString(KEY_IMAGE);
            ingredients = sandwichDetails.getJSONArray(KEY_INGREDIENTS);
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
