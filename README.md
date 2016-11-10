Seman 5: Base de Datos MVP
=========================
[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)

> - Base de Datos  
> - MVP (Modelo Vista Presentador)
## Enunciado del Ejercicio
- Tomando como punto de partida la funcionalidad que generaste para mostrar las ultimas 5 mascotas, es momento de dar persistencia a esta información.

- Estas 5 mascotas estarán cambiando conforme el usuario da rating a una o varias, mascotas. En el POJO que estas manejando para la entidad mascota genera un identificador el cuál deberá ser único y te permita diferenciar una mascota de otra.

- Crea un modelo de base de datos que contenga una tabla llamada mascota esta debe ser idéntica al POJO de mascota, de tal forma que cuando una persona de rating a una foto puedas guardar los datos completos de la entidad en la base de datos. Para fines de este ejercicio tu tabla solo estará guardando las últimas 5 mascotas con rating.

## Descripción del proyecto:
- Acontinuacion describiré de forma brebe mi trabajo:

### 1. Crear un nuevo paquete a la entidad mascota.
- Para representarla se crea un nuevo paquete Java con el nombre "db". Dentro de este, se añade una clase llamada BaseDatos, ConstructorMascota y ConstantesBaseDatos.
- Cuyos atributos son:
Mascota 
> - id
> - nombre
> - foto

Mascotas Likes 
> - id
> - id_mascota
> - numero_likes

- Para empezar modificamos el Pojo añadiento el id 
#### Java: Pojo del proyecto
````java
public class Mascota implements Serializable{
    private int id;
    private String nombreMascota;
    private int foto;
    private int likes;
}
````
## MVP (Modelo - Vista - Presentador)
- Acontuanición Muestro la aplicacion del MVP 

### MODELO

#### Se crea una clase ConstantesBaseDatos.
- Se añade dentro del paquete "db" una nueva clase llamada ConstantesBaseDatos.java que define una clase interna con los datos de la tabla "mascota" que se creará en la base de datos.

#### ConstantesBaseDatos.java
````java

public final class ConstantesBaseDatos {

    public static final String DATABASE_NAME = "mascotas";
    public static final int DATE_BASE_VERSION = 1;

    // 1er Tabla: Mascota
    public static final String TABLE_MASCOTA = "mascota";
    public static final String TABLE_MASCOTA_ID = "id";
    public static final String TABLE_MASCOTA_NONMBRE = "nombre";
    public static final String TABLE_MASCOTA_FOTO = "foto";

    // 2da Tabla: Mascota Likes
    public static final String TABLE_LIKES_MASCOTA = "mascotas_likes";
    public static final String TABLE_LIKES_MASCOTA_ID = "id";
    public static final String TABLE_LIKES_MASCOTA_ID_MASCOTA = "id_mascota";
    public static final String TABLE_LIKES_MASCOTA_NUMERO_LIKES= "numero_likes";
}
````

### Crear BaseDatos.java que extienda de SQliteOpenHelper.

- SQLiteOpenHelper nos permitirá comunicar nuestra aplicación con la base de datos. Se trata de una clase abstracta que nos provee los mecanismos básicos para la relación entre la aplicación Android y la información.
- Para implementar este controlador se debe:

>- Crear una clase que extienda de SQLiteOpenHelper
>- Configurar un constructor apropiado
>- Sobrescribir los métodos onCreate() y onUpgrade()

### Crear BaseDatos.java

- Crear una nueva clase BaseDatos.java que extienda de SQLiteOpenHelper. El constructor BaseDatos usa super para mantener la herencia del helper.
```java
public class BaseDatos extends SQLiteOpenHelper {
    // Variable Global
    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }
}
```
### Código SQL para crear una base de datos
- Una vez terminado el esquema, crear la tabla de mascotas en onCreate() con el metodo execSQL() y el comando CREATE TABLE:
```java
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA + " (" +
                ConstantesBaseDatos.TABLE_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE + " TEXT," +
                ConstantesBaseDatos.TABLE_MASCOTA_FOTO + " INTEGER" +
                " )";

        String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA + " (" +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + " INTEGER, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTA + "(" + ConstantesBaseDatos.TABLE_MASCOTA_ID + ")" +
                " )";
        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);
    }
```
### Insertar Información En La Base De Datos
- El método cuya funcionalidad es añadir filas a nuestras tablas se llama SQLiteDatabase.insert().

> - La receta a seguir para usarlo es:
> - 1. Crea un objeto del tipo ContentValues. Este permite almacenar las columnas del registro en pares clave-valor
> - 2. Añadir los pares con el método put()
> - 3. Invocar a insert() a través de la instancia de la base de datos

```java
 public void insertarDatosIniciales(SQLiteDatabase db){
ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Muñeco");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_2);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Laica");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_3);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Petrolero");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_4);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Valvula");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_5);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Gordo");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_6);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Mariposa");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_7);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Campeón");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_8);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Dumbo");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_9);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Chimbo");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_10);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Lasi");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_11);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Poker");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_12);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NONMBRE, "Thimboy");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.mascota_19_13);
        db.insertarMascotas(contentValues);

    }
```
### Sobrescribe el método onUpgrade().

- Este es ejecutado si se identificó que el usuario tiene una versión antigua de la base de datos. En su interior establecerá instrucciones para modificar el esquema de la base de datos, como por ejemplo eliminar todo el esquema y recrearlo, agregar una nueva tabla, añadir una nueva columna, etc.

```java
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROB TABLE IF EXIST " + ConstantesBaseDatos.TABLE_MASCOTA);
        db.execSQL("DROB TABLE IF EXIST " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA);
        onCreate(db);
 }
```
### Resultado
<img src="imgagendb1.gif" width="270" height="480"/>

### Crear un manejador de Datos:
- Esta clase se encargara de obtener los datos de las mascotas y los likes de cada mascota 
```java
public class ConstructorMascotas {

   private static final int LIKE = 1 ;
    private Context context;
    BaseDatos db;
    public ConstructorMascotas(Context context) {
        this.context = context;
        db = new BaseDatos(context);
    }

    public ArrayList<Mascota> obtenerMascotas (){
        // Ejecutamos la conexion a mi Base de Datos
        db = new BaseDatos(context);
        //insertarTresMascotas(db);
        return db.obtenerMascotasDB();
    }

    public ArrayList<Mascota> obtenerLikesMascotas(){
       // BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascotasDB();
    }

    public void darLikeMascota (Mascota mascota){
        //BaseDatos db =new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES, mascota.getLikes() + LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public int obtenerLikesMascota (Mascota mascota){
        //BaseDatos db = new BaseDatos(context);
        return  db.obtenerLikesMascota(mascota);
    }
}
```

## VISTA
- La vista, habitualmente implementada por una Activity (aunque puede ser un fragment, una View… según como se estructure la App), contendrá una referencia al presenter

```java
public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView{
    // Variables Globales
    private RecyclerView listadoMascotas;
    //ArrayList<Mascota> lstmascotas;


    private IRecyclerViewFragmentPresenter presenter;

    public RecyclerViewFragment() {

    }

    public static RecyclerViewFragment newInstance(String param1, String param2) {
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
        recyclerViewFragment.setArguments(args);
        return recyclerViewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recycleview,container,false);
        // Creamos el RecyclerView
        listadoMascotas = (RecyclerView) v.findViewById( R.id.rvMascota );
        RecyclerViewFragmentPresenter recyclerViewFragmentPresenter = new RecyclerViewFragmentPresenter(this, getActivity(),false);
        //generarLinearLayoutVertical();
       // presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listadoMascotas.setLayoutManager( llm );
    }

    @Override
    public void crearAdapter(ArrayList<Mascota> lstMascotas) {
        AdapterMascota adaptador = new AdapterMascota(lstMascotas,getActivity());
        listadoMascotas.setAdapter(adaptador);
    }
}
```
- Implemetamos una interface para la Vista
```java
public interface IRecyclerViewFragmentView {
    public void generarLinearLayoutVertical();
    public void crearAdapter (ArrayList<Mascota> lstmascotas);
}
```

## PRESENTADOR
- El presenter se encarga de actuar de intermediario entre la vista y el modelo. Recupera los datos del modelo y se los devuelve a la vista formateados.

- RecyclerViewFragmentPresenter.java
```java
public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter{

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    ConstructorMascotas constructorMascotas;
    //private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context, boolean bRaiting) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        constructorMascotas= new ConstructorMascotas(this.context);
        //obtenerMascotasBaseDatos();

        if(bRaiting) {
            mostarMascotasFavoritas();
            return;
        }
        mostrarMascotas();
    }

    @Override
    public void mostrarMascotas() {
        ArrayList<Mascota> mascotas = obtenerMascotas();
        Log.d("Mascota ",String.valueOf(mascotas.size()));
        if(mascotas.size() == 0){
            constructorMascotas.insertarMascotas();
            mascotas = obtenerMascotas();
        }
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
        iRecyclerViewFragmentView.crearAdapter(mascotas);
    }

    @Override
    public void mostarMascotasFavoritas() {
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
        iRecyclerViewFragmentView.crearAdapter(obtenerLikesMascotas());

    }

    @Override
    public ArrayList<Mascota> obtenerLikesMascotas() {
        return constructorMascotas.obtenerLikesMascotas();
    }

    @Override
    public ArrayList<Mascota> obtenerMascotas() {
        return constructorMascotas.obtenerMascotas();
    }
}
```

### Implementacion de su Iterface 
- IRecyclerViewFragmentPresenter.java
```java
public interface IRecyclerViewFragmentPresenter {

    public void mostrarMascotas();
    public void mostarMascotasFavoritas();

    public ArrayList<Mascota> obtenerLikesMascotas();
    public ArrayList<Mascota> obtenerMascotas();
}
```
