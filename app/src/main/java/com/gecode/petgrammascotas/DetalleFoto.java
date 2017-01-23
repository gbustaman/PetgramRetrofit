package com.gecode.petgrammascotas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetalleFoto extends AppCompatActivity {
    private ImageView imgFotoDetalle;
    private TextView tvLikesDetalle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_foto);
        Bundle parametros = getIntent().getExtras();

        int likes = parametros.getInt(getResources().getString(R.string.pLikes));
        String urlFoto = parametros.getString(getResources().getString(R.string.pUrl));

        tvLikesDetalle = (TextView) findViewById(R.id.tvLikesDetalle);
        tvLikesDetalle.setText(String.valueOf(likes));
        imgFotoDetalle = (ImageView) findViewById(R.id.imgFotoDetalle) ;
        Picasso.with(this).load(urlFoto).placeholder(R.drawable.mascota_19_2).into(imgFotoDetalle);
    }
    public void irHome (View v){finish();}

}
