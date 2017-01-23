package com.gecode.petgrammascotas.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gecode.petgrammascotas.DetalleFoto;
import com.gecode.petgrammascotas.R;
import com.gecode.petgrammascotas.modelo.PerfilMascota;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by gregorybr on 30-10-16.
 */

    public class AdapterPerfilMascota extends RecyclerView.Adapter<AdapterPerfilMascota.PerfilMascotaViewHolder>{
    ArrayList<PerfilMascota> mascotasPerfil;
    Activity activityPerfil;

    public AdapterPerfilMascota(ArrayList<PerfilMascota> mascotasPerfil, Activity activityPerfil ) {
        this.activityPerfil = activityPerfil;
        this.mascotasPerfil = mascotasPerfil;
    }
    //Inflar el layout y lo pasará al view holder para obtener los views
    @Override
    public PerfilMascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.cardview_perfil, parent, false );
        return new PerfilMascotaViewHolder( v );
    }
    //Asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(PerfilMascotaViewHolder holder, int position) {

        final PerfilMascota m = mascotasPerfil.get( position );
        Picasso.with(activityPerfil)
                .load(m.getUrlFoto())
                .placeholder(R.drawable.mascota_19_2)
                .into(holder.imgFotoPerfil);

       /* holder.imgFotoPerfil.setImageResource( m.getPerfilFoto() );
        holder.tvRaitingPerfil.setText( m.getPerfilRaiting() );*/

        holder.tvRaitingPerfil.setText(String.valueOf(m.getLikesFoto()));

        holder.imgFotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* m.setPerfilRaiting(String.valueOf(Integer.parseInt(m.getPerfilRaiting()) + 1));
                notifyDataSetChanged();*/
                //Toast.makeText(activityPerfil,"Diste like" ,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activityPerfil, DetalleFoto.class);
                intent.putExtra(activityPerfil.getResources().getString(R.string.pUrl), m.getUrlFoto());
                intent.putExtra(activityPerfil.getResources().getString(R.string.pLikes), m.getLikesFoto());
                activityPerfil.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotasPerfil.size();
    }
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public static class PerfilMascotaViewHolder extends RecyclerView.ViewHolder {
        //private TextView tvNombreMascotaPerfil;
        private ImageView imgFotoPerfil;
        private TextView tvRaitingPerfil;
        //private ImageButton btnLikePerfil;

        public PerfilMascotaViewHolder(View itemView) {
            super(itemView);
            //tvNombreMascotaPerfil = (TextView) itemView.findViewById( R.id.tvNombrePerfil );
            imgFotoPerfil = (ImageView) itemView.findViewById( R.id.imgFotoMascotaPerfil );
            tvRaitingPerfil = (TextView) itemView.findViewById( R.id.tvPerfil );
            //btnLikePerfil = (ImageButton) itemView.findViewById(R.id.btnLikePerfil);
        }
    }
}
