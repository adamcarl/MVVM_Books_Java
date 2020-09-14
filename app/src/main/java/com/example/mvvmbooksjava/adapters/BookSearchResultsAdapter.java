package com.example.mvvmbooksjava.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmbooksjava.R;
import com.example.mvvmbooksjava.models.Volume;
import com.example.mvvmbooksjava.util.Util;

import java.util.ArrayList;
import java.util.List;

public class BookSearchResultsAdapter
        extends RecyclerView.Adapter<BookSearchResultsAdapter.BookSearchResultHolder> {

    private List<Volume> results = new ArrayList<>();

    @NonNull
    @Override
    public BookSearchResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);

        return new BookSearchResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookSearchResultHolder holder, int position) {
        Volume volume = results.get(position);

        holder.txt_title.setText(volume.getVolumeInfo().getTitle());
        holder.txt_publishedDate.setText(volume.getVolumeInfo().getPublishedDate());

        if (volume.getVolumeInfo().getImageLinks() != null) {
            String imageUrl = volume.getVolumeInfo().getImageLinks().getSmallThumbnail()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.img_smallThumbnail);
        }

        if (volume.getVolumeInfo().getAuthors() != null) {
            Util u = new Util();
            String authors = u.StringJoin(volume.getVolumeInfo().getAuthors(), ",");
            holder .txt_authors.setText(authors);
        }

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Volume> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    class BookSearchResultHolder extends RecyclerView.ViewHolder {
        private TextView txt_title;
        private TextView txt_authors;
        private TextView txt_publishedDate;
        private ImageView img_smallThumbnail;

        public BookSearchResultHolder(@NonNull View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.book_item_title);
            txt_authors = itemView.findViewById(R.id.book_item_authors);
            txt_publishedDate = itemView.findViewById(R.id.book_item_publishedDate);
            img_smallThumbnail = itemView.findViewById(R.id.book_item_smallThumbnail);
        }
    }
}
