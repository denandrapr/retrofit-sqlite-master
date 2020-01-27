package com.example.mahasiswafavorit.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mahasiswafavorit.R;
import com.example.mahasiswafavorit.db.DatabaseHandler;
import com.example.mahasiswafavorit.model.Mahasiswa;
import com.example.mahasiswafavorit.model.MahasiswaModel;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {

    private Context context;
    private List<MahasiswaModel> result;
    private List<MahasiswaModel> listLocalMahasiswa = new ArrayList<>();

    public MahasiswaAdapter(Context context, List<MahasiswaModel> result) {
        this.context = context;
        this.result = result;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa,parent,false);
        MahasiswaAdapter.ViewHolder holder = new MahasiswaAdapter.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MahasiswaAdapter.ViewHolder holder, int position){
        MahasiswaModel results = result.get(position);

        final DatabaseHandler db = new DatabaseHandler(context);

        final String nama = results.getNama();
        final String umur = results.getUmur();
        final String id = results.getId();
        
        holder.txtNama.setText(nama);
        holder.txtUmur.setText(umur);

        listLocalMahasiswa.addAll(db.getAllRecord());

        for (int i = 0; i < listLocalMahasiswa.size(); i++){
            if (listLocalMahasiswa.get(i).getId().equals(id)){
                holder.favorit.setChecked(true);
            }
        }

        holder.favorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.favorit.isChecked()){

                    MahasiswaModel mahasiswaModel = new MahasiswaModel(
                            id, nama, umur, 1);
                    db.addRecord(mahasiswaModel);

                    Toast.makeText(context, nama+" Berhasil Ditambah", Toast.LENGTH_SHORT).show();
                }else{
                    db.hapus_favorit(id);
                    Toast.makeText(context, nama+" Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                }
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