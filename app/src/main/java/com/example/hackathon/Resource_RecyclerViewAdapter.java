package com.example.hackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Resource_RecyclerViewAdapter extends RecyclerView.Adapter<Resource_RecyclerViewAdapter.MyViewHolder>{

    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<ResourceModel> resourceModels;


    public Resource_RecyclerViewAdapter(Context context, ArrayList<ResourceModel> resourceModels, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.resourceModels = resourceModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public Resource_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.resource_item, parent, false);

        return new Resource_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.resourceName.setText(resourceModels.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return resourceModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView resourceName;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            resourceName = itemView.findViewById(R.id.resource_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }


}
