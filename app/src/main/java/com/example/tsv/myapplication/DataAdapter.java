package com.example.tsv.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<String> arrayList;
    private LayoutInflater layoutInflater;

    public DataAdapter(List<String> arrayList, Context context) {
        this.arrayList = (ArrayList<String>) arrayList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_message, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String msg = arrayList.get(i);
        viewHolder.textView.setText(msg);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
