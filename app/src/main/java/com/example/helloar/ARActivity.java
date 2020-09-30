package com.example.helloar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import org.w3c.dom.NodeList;

import java.util.Objects;

public class ARActivity extends AppCompatActivity {
    private ArFragment arFragment ;
    private Button mbutton_back ;
    private Anchor anchor;
    AnchorNode anchorNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        mbutton_back = findViewById(R.id.activity_ar_button_back);
        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.arFragment);


        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {

           anchor = hitResult.createAnchor();
            ModelRenderable.builder()
                    .setSource(this, Uri.parse("Basketball.sfb"))
                    .build()
                    .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
                    .exceptionally(throwable -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage(throwable.getMessage())
                                .show();
                        return null;
                    });

        });


        mbutton_back.setEnabled(true);
        mbutton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(anchorNode != null) {
                        arFragment.getArSceneView().getScene().removeChild(anchorNode);
                    }

            }
        });

}

    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
        anchorNode = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());
        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }
}
