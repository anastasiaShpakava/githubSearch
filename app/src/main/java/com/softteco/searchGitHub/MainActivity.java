package com.softteco.searchGitHub;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.softteco.searchGitHub.model.Item;
import com.softteco.searchGitHub.ui.ItemAdapter;
import com.softteco.searchGitHub.ui.ItemViewModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemViewModel itemViewModel;
    private TextInputEditText textInputEditText;
    private LinearLayoutManager layoutManager;
    private ImageButton search;
    private ItemAdapter adapter;
    private TextView textView;
    private String q;
    private int page = 1;
    private final int resultsPerPage = 50;
    private ArrayList<Item> items = new ArrayList<>();
    private int resultTotalCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.result_count_text);

        layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ItemAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        itemViewModel.init();
        itemViewModel.getItemsResponseLiveData().observe(this, responseObject -> {
            resultTotalCount = responseObject.getTotal_count();
            String result = String.valueOf(resultTotalCount);
            String totalResults = getString(R.string.total_count, result);

            textView.setText(totalResults);
            items.addAll(responseObject.getItems());
            adapter.setItems(items);

        });

        pagesPagination();

        textInputEditText = findViewById(R.id.et_search);
        search = findViewById(R.id.ib_search);

        search.setOnClickListener(view -> {
            if (textInputEditText.getText() != null) {
                q = textInputEditText.getText().toString();
                itemViewModel.searchItems(q, page, resultsPerPage);
            }
        });
    }

    private void pagesPagination() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1) && dy != 0) {
                    if (layoutManager.findLastCompletelyVisibleItemPosition() ==
                            adapter.getItemCount() - 1) {
                        if (items.size() < resultTotalCount) {
                            page++;
                            itemViewModel.searchItems(q, page, resultsPerPage);
                        }
                    }
                }
            }
        });
    }
}