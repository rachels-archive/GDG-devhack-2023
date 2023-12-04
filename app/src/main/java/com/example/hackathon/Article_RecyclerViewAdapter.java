package com.example.hackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Article_RecyclerViewAdapter extends RecyclerView.Adapter<Article_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<ArticleModel> articleModels;

    public Article_RecyclerViewAdapter(Context context, ArrayList<ArticleModel> articleModels) {
        this.context = context;
        this.articleModels = articleModels;
    }

    @NonNull
    @Override
    public Article_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);

        return new Article_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Article_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(articleModels.get(position).getArticleTitle());
        holder.tvAuthor.setText(articleModels.get(position).getArticleAuthor());
        holder.imageView.setImageResource(articleModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return articleModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle, tvAuthor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.article_image);
            tvTitle = itemView.findViewById(R.id.article_title);
            tvAuthor = itemView.findViewById(R.id.article_author);
        }
    }
}
