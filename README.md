Seman 4: Menus y Fragment
=========================
[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)


<img src="petagram.gif" alt="sample" title="sample" width="270" height="480" align="right" vspace="52"/>
> - Menus: 
> - Fragmnet: Adapter, View Holder, Libreria CircularImageView
> - Java Mail: Libreria javamail

# DEMO


## Agregando Menus
- Crea un menú de opciones que muestre el Item “Contacto” y el Item “Acerca De” 

### Xml: Menu de Opciones 
```xml
    <menu
      xmlns:android="http://schemas.android.com/apk/res/android">
      <!-- Menu Contacto: Enviar Email -->
      <item android:id="@+id/mContacto"
          android:title="@string/menu_contacto"
          android:orderInCategory="100">
      </item>
      <!-- Menu About: Bio del Desarrollador -->
      <item android:id="@+id/mAbout"
          android:title="@string/menu_about"
          android:orderInCategory="101">
      </item>
    </menu>
```

### Java: Creamos un menú a nuestra aplicacion Principal.

````java
public class ListaMascotas extends AppCompatActivity {
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
        }return super.onOptionsItemSelected(item);
    }
}
````

### Resultado
<img src="imagen1.gif" width="270" height="480"/>

### Contacto deberá:
> - Llevarte a una pantalla con un formulario en el que solicites el nombre, el correo y su mensaje (utiliza los EditText de Material Design),
> - Además habrá un botón de “Enviar Comentario” el cual tomará la información recopilada y con ayuda de la librería JavaMail envíe un mail con el comentario del contacto.

#### Contacto.java [https://github.com/gbustaman/PetgramMascotas/blob/master/app/src/main/java/com/gecode/petgrammascotas/menu/Contacto.java]

````java
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
````

#### About.java [https://github.com/gbustaman/PetgramMascotas/blob/master/app/src/main/java/com/gecode/petgrammascotas/menu/About.java]
````java
public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

    }

    public void irHome (View v){
        finish();
    }
}
````
#### Uso de la Libreria javaMail
- Importamos atraves de gradle en dependencias las librerias de javamail

```
    compile 'com.sun.mail:android-mail:1.5.5'
    compile 'com.sun.mail:android-activation:1.5.5'
```

- En el enlace siguiente encotraremos la configuración de javamail para enviar un comentario al contacto [https://github.com/gbustaman/PetgramMascotas/blob/master/app/src/main/java/com/gecode/petgrammascotas/mail/EnviarMail.java]

#### Para enviar un mensaje al contacto deben ingresar su cuenta de gmail y su contraseña 

- En el archivo EnviarMail.java ingresamos nuestros datos, cabe aclarar que para enviar un comentario tienen que configurar su gmail "Permitir que aplicaciones menos seguras accedan a tu cuenta" 

````java
public class EnviarMail extends AsyncTask<Void,Void,Void> {
    
    private String usuario = ""; // Introdusca su cuenta de gmail.
    private String passwordUsuario = ""; // Indrodusca su contraseña.
}
````

### Resultado
<img src="imagen2.gif" height="480"/>

## Fragments
- Modulariza el proyecto anterior en Fragments para que estos puedan ser mostrados en un ViewPager.
#### Configuramos los Fragments utilizados para este proyecto.

- ListadoMascotas.java
````java
private ArrayList <Fragment> agregarFragments(){
    ArrayList<Fragment> fragments = new ArrayList<>();
    fragments.add(new RecycleViewFragment());
    fragments.add(new FragmentPerfil());
    return fragments;
}
private void setUpViewPager(){
    viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
    tabLayout.setupWithViewPager(viewPager);
    tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
    tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_name);

}
````

- en este elace encontraras los codigos de los Fragmnets utilizados para este proyecto. 
[https://github.com/gbustaman/PetgramMascotas/tree/master/app/src/main/java/com/gecode/petgrammascotas/fragment]

- Código XML: Fragment del Perfil 
```xml
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto">
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

                <com.mikhaellopez.circularimageview.CircularImageView
                    android:id="@+id/civPerfilMascota"
                    android:layout_marginTop="20dp"
                    android:layout_width="130dp"
                    android:layout_height="130dp"
                    android:src="@drawable/mascota_19_2"
                    android:layout_gravity="center"
                    app:civ_border_color="@color/colorPrimaryDark"
                    app:civ_border_width="6dp"
                    app:civ_shadow="true"
                    app:civ_shadow_radius="1"
                    app:civ_shadow_color="#8BC34A"/>

                <TextView
                    android:id="@+id/tvNombrePerfil"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:gravity="center_horizontal"
                    android:textSize="@dimen/text_titulo_size"
                    android:textStyle="bold"
                    android:layout_marginTop="@dimen/layout_margin_top"
                    android:text="@string/name_default"
                    />

                <View
                    android:layout_width="match_parent"
                    android:layout_height="@dimen/divider_fragment_perfil"
                    android:layout_marginTop="@dimen/layout_margin_vertical"
                    android:layout_marginBottom="@dimen/layout_margin_vertical"
                    android:layout_marginLeft="10dp"
                    android:layout_marginRight="10dp"
                    android:background="@color/dividerColor" />

                <android.support.v7.widget.RecyclerView
                    android:id="@+id/rvPerfilMascota"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:paddingRight="@dimen/text_padding_right"/>

        </LinearLayout>

        <GridLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:paddingBottom="@dimen/grid_bottom"
            android:padding="@dimen/grid_rigth">

                <android.support.design.widget.FloatingActionButton
                    android:id="@+id/FAB_Camera_Perfil"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_row="1"
                    android:layout_column="1"
                    android:background="@color/colorPrimaryLight"
                    android:src="@drawable/camera_filled_50_blanco"
                    />
        </GridLayout>

</android.support.design.widget.CoordinatorLayout>
```

### Resultado del Fragment_Perfil
<img src="imagen3.gif" width="270" height="480"/>


- Codigo XML: Fragment Principal
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/rvMascota"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
       />

</LinearLayout>
```

### Resultado del Fragment Principal 
<img src="imagen4.gif" width="270" height="480"/>

### CircularImageView
- Se uso la libreria de lopspower/CircularImageView

#### Uso de la Libreria CircularImageView
```
compile 'com.mikhaellopez:circularimageview:3.0.2'
```
#### Codigo XML
```xml
<com.mikhaellopez.circularimageview.CircularImageView
                    android:id="@+id/civPerfilMascota"
                    android:layout_marginTop="20dp"
                    android:layout_width="130dp"
                    android:layout_height="130dp"
                    android:src="@drawable/mascota_19_2"
                    android:layout_gravity="center"
                    app:civ_border_color="@color/colorPrimaryDark"
                    app:civ_border_width="6dp"
                    app:civ_shadow="true"
                    app:civ_shadow_radius="1"
                    app:civ_shadow_color="#8BC34A"/>
```



