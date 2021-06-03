package com.softteco.searchGitHub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;
import com.softteco.searchGitHub.model.Item;
import com.softteco.searchGitHub.network.ResponseObject;
import com.softteco.searchGitHub.ui.ItemAdapter;
import com.softteco.searchGitHub.ui.ItemViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ItemViewModel itemViewModel;
    private TextInputEditText textInputEditText;
    private ImageButton search;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        itemViewModel.init();
        itemViewModel.getItemsResponseLiveData().observe(this, new Observer<ResponseObject>() {
            @Override
            public void onChanged(ResponseObject responseObject) {
                adapter.setItems(responseObject.getItems());
            }

        });
        textInputEditText = findViewById(R.id.et_search);
        search = findViewById(R.id.ib_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = textInputEditText.getText().toString();
                if(!q.isEmpty())
                {
                    itemViewModel.searchItems(q);
                }
            }
        });

    }
}