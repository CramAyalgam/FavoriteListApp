package com.example.favoritelistapp;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView txtCategoryNumber;
    private TextView txtCategoryName;


    public CategoryViewHolder(View view){
        super(view);
        txtCategoryName = view.findViewById(R.id.category_name_textview);
        txtCategoryNumber = view.findViewById(R.id.category_number_textview);

    }

    public TextView getTxtCategoryNumber() {
        return txtCategoryNumber;
    }

    public TextView getTxtCategoryName() {
        return txtCategoryName;
    }
}
