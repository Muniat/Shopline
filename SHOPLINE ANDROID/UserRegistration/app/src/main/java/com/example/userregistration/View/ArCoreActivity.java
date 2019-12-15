package com.example.userregistration.View;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.userregistration.R;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;

public class ArCoreActivity extends AppCompatActivity {
    private ArFragment arFragment;
    private String ASSET_3D = "http://ed49757d.ngrok.io/model.gltf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_core);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentAr);



        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            placeModel(hitResult.createAnchor());
        });

    }

    private void placeModel(Anchor anchor) {
        ModelRenderable
                .builder()
                .setSource(
                        this,
                        RenderableSource
                        .builder()
                        .setSource(this, Uri.parse(ASSET_3D), RenderableSource.SourceType.GLTF2)
                        .setScale(0.25f)
                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                        .build()
                ).setRegistryId(ASSET_3D)
                .build()
                .thenAccept(modelRenderable -> addNodeToScene(modelRenderable,anchor))
                .exceptionally(throwable ->{
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    return  null;
                });
    }

    private void addNodeToScene(ModelRenderable modelRenderable, Anchor anchor) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        anchorNode.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
    }
}
