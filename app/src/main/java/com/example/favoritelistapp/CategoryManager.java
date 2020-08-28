package com.example.favoritelistapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

public class CategoryManager {

    private Context mContext;

    public CategoryManager(Context context) {
        this.mContext = context;
    }

    //method to save category to sharedPreferences
    public void saveCategory(Category category){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //getArrayList of category to itemsHashSet
        HashSet itemsHashSet = new HashSet(Arrays.asList(category.getItems()));

        editor.putStringSet(category.getName(), itemsHashSet);

        editor.apply();

    }

    //method to retrive items from sharedPreferences
    public ArrayList<Category> retrieveCategories(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);

        //String is the key , ? any value
        Map<String, ?> data = sharedPreferences.getAll();

        ArrayList<Category> categories = new ArrayList<>();

        //loop to get all items from Sharepref to Map named entry then put the items to category
        for(Map.Entry<String, ?> entry : data.entrySet()) {

            //convert hashset to arraylist
            //casting (HashSet)
            Category category = new Category(entry.getKey(), new ArrayList<String>((HashSet) entry.getValue()));
            categories.add(category);

        }

        return  categories;
    }

}
