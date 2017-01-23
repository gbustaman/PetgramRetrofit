package com.gecode.petgrammascotas.menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gecode.petgrammascotas.R;

public class ConfigurarCuenta extends AppCompatActivity {

    private TextInputEditText etextNombrePerfil;
    private TextInputLayout tilNombrePerfil;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        tilNombrePerfil = (TextInputLayout) findViewById(R.id.tilNombreUsuario);
        etextNombrePerfil = (TextInputEditText) findViewById(R.id.etexNombreCuenta);
        etextNombrePerfil.addTextChangedListener(new MyTextWatcher(etextNombrePerfil));
        btnGuardar = (Button) findViewById(R.id.btnGuardarCuenta);

        mostrarPreferencia();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitFormulario();
            }
        });
    }

    private void submitFormulario() {
        if (!validaNombrePerfil()) {
            return;
        }

        SharedPreferences miPreferenciaCompartida = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = miPreferenciaCompartida.edit();

        String nombrePerfil = etextNombrePerfil.getText().toString();

        editor.putString(getResources().getString(R.string.NombrePerfil), nombrePerfil);

        editor.commit();

        Toast.makeText(ConfigurarCuenta.this, "Se ha guardado la cuenta de Instagram", Toast.LENGTH_SHORT).show();
        etextNombrePerfil.setText("");

        mostrarPreferencia();
    }

    private boolean validaNombrePerfil() {
        if (etextNombrePerfil.getText().toString().trim().isEmpty()) {
            tilNombrePerfil.setError(getString(R.string.err_msg));
            requestFocus(etextNombrePerfil);
            return false;
        } else {
            tilNombrePerfil.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void mostrarPreferencia() {
        SharedPreferences miPreferenciaCompartida = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);

        String nombrePerfilActual = miPreferenciaCompartida.getString(getResources().getString(R.string.NombrePerfil), "");
        TextView tvPreferenciaCompartida = (TextView) findViewById(R.id.tvCuentaUsuario);
        tvPreferenciaCompartida.setText(nombrePerfilActual);
    }

    private class MyTextWatcher implements TextWatcher {
        private  View view;
        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.textNombre:
                    validaNombrePerfil();
                    break;
            }
        }
    }
}
