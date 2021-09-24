package com.example.kkulkarni.flixter_project_2.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kkulkarni.flixter_project_2.DetailActivity;
import com.example.kkulkarni.flixter_project_2.R;
import com.example.kkulkarni.flixter_project_2.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    //Usually involves inflating layout from XML and returning holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter","onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(movieView);
    }
    //Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get movie at position
        Log.d("MovieAdapter","onBindViewHolder" + position);
        Movie movie = movies.get(position);
        //bind movie data into the movie viewholder
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (itemView.findViewById(R.id.tvTitle));
            tvOverview = (itemView.findViewById(R.id.tvOvrvw));
            ivPoster = (itemView.findViewById(R.id.ivPoster));
            container = (itemView.findViewById(R.id.container));
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            Glide.with(context).load(movie.getPosterPath()).into(ivPoster);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                // 1. Reg click container on the whole row
                // 2. Navigate to new activity on tap
                public void onClick(View view) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });
        }
    }
}
