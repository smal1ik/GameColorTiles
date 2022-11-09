package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    View[][] views = new View[4][4];
    int darkColor;
    int brightColor;
    int clickN = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        views[0][0] = findViewById(R.id.tile00);
        views[0][1] = findViewById(R.id.tile01);
        views[0][2] = findViewById(R.id.tile02);
        views[0][3] = findViewById(R.id.tile03);
        views[1][0] = findViewById(R.id.tile10);
        views[1][1] = findViewById(R.id.tile11);
        views[1][2] = findViewById(R.id.tile12);
        views[1][3] = findViewById(R.id.tile13);
        views[2][0] = findViewById(R.id.tile20);
        views[2][1] = findViewById(R.id.tile21);
        views[2][2] = findViewById(R.id.tile22);
        views[2][3] = findViewById(R.id.tile23);
        views[3][0] = findViewById(R.id.tile30);
        views[3][1] = findViewById(R.id.tile31);
        views[3][2] = findViewById(R.id.tile32);
        views[3][3] = findViewById(R.id.tile33);

        Resources r = getResources();
        darkColor = r.getColor(R.color.dark);
        brightColor = r.getColor(R.color.bright);

        Random rand = new Random();
        for (int i = 0; i < 4; i ++){
            for (int j = 0; j < 4; j++){
                if(rand.nextBoolean()){
                    views[i][j].setBackgroundColor(darkColor);
                }else {
                    views[i][j].setBackgroundColor(brightColor);
                }
            }
        }
    }

    public void changeColor(View v) {
        ColorDrawable d = (ColorDrawable) v.getBackground();
        if (d.getColor() == brightColor) {
            v.setBackgroundColor(darkColor);
        } else {
            v.setBackgroundColor(brightColor);
        }
    }

    public void onClick(View v) {
        int res1 = 0;
        int res2 = 0;
        String tag = v.getTag().toString();
        int x = Integer.parseInt(tag.substring(0, 1));
        int y = Integer.parseInt(tag.substring(1,2));
        Log.i("COORD", String.valueOf(x));
        Log.i("COORD", String.valueOf(y));
        changeColor(v);
        for (int i = 0; i < 4; i++) {
            changeColor(views[x][i]);
            changeColor(views[i][y]);
        }

        TextView t = findViewById(R.id.Text);
        clickN++;
        t.setText("Количество нажатий: " + clickN);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                ColorDrawable d = (ColorDrawable) views[i][j].getBackground();
                if(d.getColor() == brightColor){
                    res1++;
                }else{
                    res2++;
                }
            }
        }
        if (res1 == 16 || res2 == 16){
            Toast.makeText(this, "Ты победил!!!", Toast.LENGTH_LONG).show();
        }

    }
}