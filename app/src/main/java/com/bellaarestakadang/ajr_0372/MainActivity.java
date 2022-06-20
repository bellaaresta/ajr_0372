package com.bellaarestakadang.ajr_0372;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bellaarestakadang.ajr_0372.preferences.UserPreferences;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {
    public static Activity mainActivity;

    private MaterialTextView tvAset, tvPromo;
    private ImageView ivAset, ivPromo;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;
        setContentView(R.layout.activity_main);
        setTitle("home");

        userPreferences = new UserPreferences(MainActivity.this);

        tvAset = findViewById(R.id.tv_aset);
        tvPromo = findViewById(R.id.tv_promo);
        ivAset = findViewById(R.id.iv_aset);
        ivPromo = findViewById(R.id.iv_promo);

        ivPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                finish();
                startActivity(new Intent(MainActivity.this, PromoActivity.class));
            }
        });

        ivAset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MainActivity.this, AsetActivity.class));
            }
        });
    }
}