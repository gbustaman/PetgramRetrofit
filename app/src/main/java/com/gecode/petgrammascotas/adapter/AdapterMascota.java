package com.gecode.petgrammascotas.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gecode.petgrammascotas.DetalleFoto;
import com.gecode.petgrammascotas.R;
import com.gecode.petgrammascotas.modelo.PerfilMascota;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by gregorybr on 28-10-16.
 */

public class AdapterMascota extends RecyclerView.Adapter<AdapterMascota.MascotaViewHolder> {

    ArrayList<PerfilMascota> mascotas;
   // Context context;
    Activity activity;

    public AdapterMascota(ArrayList<PerfilMascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.cardview_mascotas, parent, false );
        return new MascotaViewHolder( v );
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, int position) {
        final PerfilMascota perfilmascota = mascotas.get( position );
        Picasso.with(activity)
                .load(perfilmascota.getUrlFoto())
                .placeholder(R.drawable.mascota_19_2)
                .into(holder.imgFoto);

        holder.tvNombreMascota.setText(perfilmascota.getNombreCompleto());

        String raiting = "";
        raiting = String.valueOf(perfilmascota.getLikesFoto());
        holder.tvRaiting.setText(String.valueOf(raiting)); //mascota.getRaiting())+" Likes"

        /*
        holder.tvNombreMascota.setText( mascota.getNombreMascota() );
        holder.imgFoto.setImageResource( mascota.getFoto() );
        holder.tvRaiting.setText(String.valueOf(mascota.getLikes())  );*/

        holder.imgFoto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Toast.makeText(activity, perfilmascota.getNombreCompleto(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, DetalleFoto.class);
                intent.putExtra(activity.getResources().getString(R.string.pUrl), perfilmascota.getUrlFoto());
                intent.putExtra(activity.getResources().getString(R.string.pLikes), perfilmascota.getLikesFoto());
                activity.startActivity(intent);
            }
        });

        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*mascota.setLikes(mascota.getLikes() + 1);
                notifyDataSetChanged();*/
                Snackbar.make(v,"Diste like a " + perfilmascota.getUsuario(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                /*ConstructorMascotas constructorMascotas = new ConstructorMascotas(context);
                constructorMascotas.darLikeMascota(mascota);
                holder.tvRaiting.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(mascota)));*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return ((mascotas==null)? 0 : mascotas.size());
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNombreMascota;
        private ImageView imgFoto;
        private TextView tvRaiting;
        private ImageButton btnLike;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            tvNombreMascota = (TextView) itemView.findViewById( R.id.tvNombreMascotaCV );
            imgFoto = (ImageView) itemView.findViewById( R.id.imgFotoMascota );
            tvRaiting = (TextView) itemView.findViewById( R.id.tvRaitingCV );
            btnLike = (ImageButton) itemView.findViewById(R.id.btnLike);
        }
    }
}
