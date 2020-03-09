package com.virajjage.ecommerceapp.db;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.virajjage.ecommerceapp.models.ProductsBean;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class DataTypeConverter {

    private static Gson gson = new Gson();
    @TypeConverter
    public static List<ProductsBean> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<ProductsBean>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<ProductsBean> someObjects) {
        return gson.toJson(someObjects);
    }
}
