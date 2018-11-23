package com.example.tsv.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {

     TextView textView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.massage_item);
    }
}
