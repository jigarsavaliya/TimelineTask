package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class CustomAdapter extends RecyclerView.Adapter<vh> {
    ArrayList<StatusStep> arrayList = new ArrayList<>();

    public ArrayList<StatusStep> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<StatusStep> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data,parent,false);

        return new vh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull vh holder, int position) {

        holder.bindData(arrayList.get(holder.getAdapterPosition()),position == arrayList.size()-1);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
class vh extends RecyclerView.ViewHolder{

    View line;
    View circle;
    TextView title;
    TextView subtitle;
    public vh(@NonNull View itemView) {
        super(itemView);
        line = itemView.findViewById(R.id.viewLine);
        circle = itemView.findViewById(R.id.viewCircle);
        title = itemView.findViewById(R.id.tvTitle);
        subtitle = itemView.findViewById(R.id.tvSubTitle);
    }
    public void bindData(StatusStep data,boolean islast){

       if (islast) line.setVisibility(View.GONE);
       else line.setVisibility(View.VISIBLE);

       title.setText(data.getStatus());
        subtitle.setText(data.getInfo());
        circle.setEnabled(data.getStepStatus().equals("1"));
    }
}
