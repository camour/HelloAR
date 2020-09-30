 package com.example.helloar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import org.w3c.dom.Text;

 public class MainActivity extends AppCompatActivity {

     private TextView mAppTxt;
     private TextView mGreetingTxt;
     private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       mAppTxt = (TextView) findViewById(R.id.activity_main_app_txt);
       mGreetingTxt = (TextView) findViewById(R.id.activity_main_greeting_txt);
       mButton = (Button) findViewById(R.id.activity_main_button_start);
       mButton.setEnabled(true);

       mButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent arActivityIntent = new Intent(MainActivity.this, ARActivity.class);
               startActivity(arActivityIntent);
           }
       });

    }

 }
