package com.softteco.searchGitHub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.softteco.searchGitHub.ui.ItemAdapter;
import com.softteco.searchGitHub.ui.ItemViewModel;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemViewModel itemViewModel;
    private TextInputEditText textInputEditText;
    private ImageButton search;
    private ItemAdapter adapter;
    private TextView textView;
    private String q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.result_count_text);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        itemViewModel.init();
        itemViewModel.getItemsResponseLiveData().observe(this, responseObject -> {
            String result = String.valueOf(responseObject.getTotal_count());
            String totalResults = getString(R.string.total_count, result);
            adapter.setItems(responseObject.getItems());
            textView.setText(totalResults);
        });

        textInputEditText = findViewById(R.id.et_search);
        search = findViewById(R.id.ib_search);

        search.setOnClickListener(view -> {
            if (textInputEditText.getText()!=null) {
               q = textInputEditText.getText().toString();
                itemViewModel.searchItems(q);
            }
        });
    }
}