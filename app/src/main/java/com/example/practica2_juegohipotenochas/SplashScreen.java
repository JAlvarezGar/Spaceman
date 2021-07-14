package com.example.practica2_juegohipotenochas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    // variables de animation
    Animation topAnim,bottomAnim;
    ImageView splashTitle,splashSpaceman;

    private final int DURACION_SPLASH = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Animaciones
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        // Hooks
        splashSpaceman= findViewById(R.id.splashSpaceman);
        splashTitle= findViewById(R.id.splashTitle);

        splashSpaceman.setAnimation(bottomAnim);
        splashTitle.setAnimation(topAnim);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    };


}