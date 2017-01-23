package com.gecode.petgrammascotas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gecode.petgrammascotas.adapter.PageAdapter;
import com.gecode.petgrammascotas.menu.About;
import com.gecode.petgrammascotas.menu.ConfigurarCuenta;
import com.gecode.petgrammascotas.menu.Contacto;
import com.gecode.petgrammascotas.vista.fragment.FragmentPerfil;
import com.gecode.petgrammascotas.vista.fragment.RecyclerViewFragment;

import java.util.ArrayList;

public class ListaMascotas extends AppCompatActivity {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascotas);
        // creamos nuestro Toolbat personalizado.
        toolbar = (Toolbar)  findViewById(R.id.miToolBar);
        //setSupportActionBar(toolbar);

        tabLayout = (TabLayout)  findViewById(R.id.tabLayout);
        viewPager = (ViewPager)  findViewById(R.id.viewPager);

        setUpViewPager();

        if (toolbar != null){
            setSupportActionBar(toolbar);
        }

        estableceCuentaInstagram();

        ImageView imgestrella = (ImageView) findViewById(R.id.imgstar);
        imgestrella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MascotasFavoritas.class);
                startActivity(intent);
            }
        });
    }

    private void estableceCuentaInstagram() {
        SharedPreferences miPreferenciaCompartida = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);

        String nombrePerfilActual = miPreferenciaCompartida.getString(getResources().getString(R.string.NombrePerfil), "");

        if(nombrePerfilActual==""){

            SharedPreferences.Editor editor = miPreferenciaCompartida.edit();
            editor.putString(getResources().getString(R.string.NombrePerfil), getResources().getString(R.string.cuentaInstagram));
            editor.commit();
        }
    }

    private ArrayList <Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new FragmentPerfil());
        return fragments;
    }
    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_name);

    }

    // ActionView Estrella: nos lleva a una nueva actividad (MascotasFavoritas)
   /* public  void  irSegundaActividad (View v) {
        Intent intent = new Intent(this,MascotasFavoritas.class);
        startActivity(intent);
    }
*/


    //Creamos un menu a nuestra aplicacion principal.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mContacto:
                Intent intent1 = new Intent(this, Contacto.class);
                startActivity(intent1);
                break;
            case R.id.mAbout:
                Intent intent2 = new Intent(this, About.class);
                startActivity(intent2);
                break;
            case R.id.mCuentaIstagram:
                Toast.makeText(this, getResources().getString(R.string.item_config), Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(this, ConfigurarCuenta.class);
                startActivity(intent3);
                break;
        }return super.onOptionsItemSelected(item);
    }
}
