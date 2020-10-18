package com.example.drip;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.drip.ColorMatch.getOutfit;
import static com.example.drip.ColorMatch.getOutfitPrice;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();

        String palette = intent.getStringExtra("com.example.drip");
        double[][] outfit = getOutfit(palette);
        double outfitPrice = getOutfitPrice(outfit);
        TextView priceText = (TextView) findViewById(R.id.price);
        String priceString = String.format("%.2f",outfitPrice);
        priceText.setText("$" + priceString);

        String pantFile = RGB.fileNames[((int)outfit[0][1]) + 39];
        String shirtFile = RGB.fileNames[((int)outfit[1][1]) + 55];
        String sweaterFile = RGB.fileNames[((int)outfit[2][1]) + 127];
        String maskFile = RGB.fileNames[((int)outfit[3][1]) + 28];
        String hatFile = RGB.fileNames[((int)outfit[5][1]) + 1];

        int resIDPant = getResources().getIdentifier(pantFile.substring(0, pantFile.indexOf(".")) , "drawable", getPackageName());
        int resIDShirt = getResources().getIdentifier(shirtFile.substring(0, shirtFile.indexOf(".")) , "drawable", getPackageName());
        int resIDSweater = getResources().getIdentifier(sweaterFile.substring(0, sweaterFile.indexOf(".")) , "drawable", getPackageName());
        int resIDMask = getResources().getIdentifier(maskFile.substring(0, maskFile.indexOf(".")) , "drawable", getPackageName());
        int resIDHat = getResources().getIdentifier(hatFile.substring(0, hatFile.indexOf(".")) , "drawable", getPackageName());

        ImageView hat = (ImageView) findViewById(R.id.hat);
        hat.setImageResource(resIDHat);
        ImageView mask = (ImageView) findViewById(R.id.mask);
        mask.setImageResource(resIDMask);
        ImageView shirt = (ImageView) findViewById(R.id.shirt);
        shirt.setImageResource(resIDShirt);
        ImageView pant = (ImageView) findViewById(R.id.pant);
        pant.setImageResource(resIDPant);
        ImageView sweater = (ImageView) findViewById(R.id.sweater);
        sweater.setImageResource(resIDSweater);

    }


}