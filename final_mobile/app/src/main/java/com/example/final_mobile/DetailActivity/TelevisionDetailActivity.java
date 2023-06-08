package com.example.final_mobile.DetailActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.final_mobile.Class.Television;
import com.example.final_mobile.fragments.TelevisionDetailFragment;
import com.example.final_mobile.R;

public class TelevisionDetailActivity extends AppCompatActivity {

    private Television television;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_television_detail);

        // Get the Television object from the Intent
        television = getIntent().getParcelableExtra("television");

        // Create an instance of TelevisionDetailFragment and pass the Television object
        TelevisionDetailFragment fragment = TelevisionDetailFragment.newInstance(television);

        // Add the fragment to the container
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
