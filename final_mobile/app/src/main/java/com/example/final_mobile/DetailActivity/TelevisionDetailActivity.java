package com.example.final_mobile.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.final_mobile.Model.Television;
import com.example.final_mobile.MainActivity;
import com.example.final_mobile.R;
import com.example.final_mobile.fragments.TelevisionDetailFragment;

public class TelevisionDetailActivity extends AppCompatActivity {

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_television_detail);

        btnBack = findViewById(R.id.ivBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelevisionDetailActivity.this, MainActivity.class);
                intent.putExtra("fragment", "television");
                startActivity(intent);
                finish();
            }
        });


        // Mendapatkan data Television dari Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("tvShow")) {
            Television tvShow = intent.getParcelableExtra("tvShow");
            if (tvShow != null) {
                // Mengirim data Television ke TelevisionDetailFragment
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = TelevisionDetailFragment.newInstance(tvShow);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
            }
        }
    }
}
