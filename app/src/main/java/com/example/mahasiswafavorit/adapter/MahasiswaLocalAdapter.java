package com.example.mahasiswafavorit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mahasiswafavorit.R;
import com.example.mahasiswafavorit.db.DatabaseHandler;
import com.example.mahasiswafavorit.model.MahasiswaModel;

import java.util.List;

public class MahasiswaLocalAdapter extends RecyclerView.Adapter<MahasiswaLocalAdapter.ViewHolder> {

    private Context context;
    private List<MahasiswaModel> result;

    public MahasiswaLocalAdapter(Context context, List<MahasiswaModel> result) {
        this.context = context;
        this.result = result;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa,parent,false);
        MahasiswaLocalAdapter.ViewHolder holder = new MahasiswaLocalAdapter.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MahasiswaLocalAdapter.ViewHolder holder, int position){
        MahasiswaModel results = result.get(position);

        final String nama = results.getNama();
        final String umur = results.getUmur();
        final String id = results.getId();
        
        holder.txtNama.setText(nama);
        holder.txtUmur.setText(umur);
        holder.favorit.setChecked(true);
        
        holder.favorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();        
            }
        });
    }

    @Override
    public int getItemCount(){
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtNama;
        TextView txtUmur;
        CheckBox favorit;

        public ViewHolder(View itemView){
            super(itemView);

            favorit = itemView.findViewById(R.id.checkFavorit);
            txtUmur = itemView.findViewById(R.id.txtUmur);
            txtNama = itemView.findViewById(R.id.txtNama);
        }
    }
}