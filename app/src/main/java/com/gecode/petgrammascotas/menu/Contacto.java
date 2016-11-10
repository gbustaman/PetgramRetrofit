package com.gecode.petgrammascotas.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gecode.petgrammascotas.R;
import com.gecode.petgrammascotas.mail.EnviarMail;

public class Contacto extends AppCompatActivity implements View.OnClickListener{

    EditText edtxtMail, edtxtNombre, edtxtMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Button logign   =  (Button) findViewById(R.id.btnEviarMail);
        edtxtMail       = (EditText) findViewById(R.id.textEmail);
        edtxtNombre     = (EditText) findViewById(R.id.textNombre);
        edtxtMensaje    = (EditText) findViewById(R.id.textMensaje);

        logign.setOnClickListener(this);
    }

    public void irHome (View v){finish();}

    public void enviarEmail (View v){
        EnviarMail send = new EnviarMail(this, edtxtMail.getText().toString().trim(),
                                               edtxtNombre.getText().toString().trim(),
                                               edtxtMensaje.getText().toString().trim());
        send.execute();
    }
    @Override
    public void onClick(View v) {
        enviarEmail(v);
    }
}

