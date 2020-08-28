package com.example.favoritelistapp;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;
    private CategoryManager mCategoryManager = new CategoryManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList categories = mCategoryManager.retrieveCategories();

        categoryRecyclerView = findViewById(R.id.category_rv);
        categoryRecyclerView.setAdapter(new CategoryRecyclerAdapter(categories));
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this , "Add is Clicked", Toast.LENGTH_SHORT).show();
                displayCreateCategoryDialog();

            }
        });



    }

    private  void displayCreateCategoryDialog(){
        String alertTitle = getString(R.string.create_category);
        String positiveBtnTitle = getString(R.string.positive_button_title);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        final EditText newCategoryEditText = new EditText(this);
        newCategoryEditText.setInputType(InputType.TYPE_CLASS_TEXT);

        alertBuilder.setTitle(alertTitle);
        alertBuilder.setView(newCategoryEditText);
        alertBuilder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Category category = new Category(newCategoryEditText.getText().toString(), new ArrayList<String>());
                mCategoryManager.saveCategory(category);
                CategoryRecyclerAdapter categoryRecyclerAdapter = (CategoryRecyclerAdapter) categoryRecyclerView.getAdapter();
                categoryRecyclerAdapter.addCategory(category);


                dialogInterface.dismiss();

            }
        });

        alertBuilder.create().show();

    }


}