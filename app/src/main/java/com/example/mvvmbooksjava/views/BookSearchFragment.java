package com.example.mvvmbooksjava.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmbooksjava.R;
import com.example.mvvmbooksjava.adapters.BookSearchResultsAdapter;
import com.example.mvvmbooksjava.models.VolumesResponse;
import com.example.mvvmbooksjava.viewmodels.BookSearchViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class BookSearchFragment extends Fragment {

    private BookSearchViewModel viewModel;
    private BookSearchResultsAdapter adapter;

    private TextInputEditText txt_keyword, txt_author;
    private Button btn_search;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new BookSearchResultsAdapter();

        viewModel = new ViewModelProvider(this).get(BookSearchViewModel.class);
        viewModel.init();
        viewModel.getVolumesResponseLiveData().observe(this, volumesResponse -> {
            if (volumesResponse != null) {
                adapter.setResults(volumesResponse.getItems());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_booksearch, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_booksearch_searchResultsRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        txt_keyword = view.findViewById(R.id.fragment_booksearch_keyword);
        txt_author = view.findViewById(R.id.fragment_booksearch_author);
        btn_search = view.findViewById(R.id.fragment_booksearch_search);

        btn_search.setOnClickListener(view1 -> performSearch());

        return view;
    }

    public void performSearch() {
        String keyword = txt_keyword.getEditableText().toString();
        String author = txt_author.getEditableText().toString();

        viewModel.searchVolumes(keyword, author);
    }
}
