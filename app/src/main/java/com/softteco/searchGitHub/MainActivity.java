package com.softteco.searchGitHub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;
import com.softteco.searchGitHub.ui.ItemAdapter;
import com.softteco.searchGitHub.ui.ItemViewModel;

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
        itemViewModel = new ViewModelProvider(this).get(ViewModel.class);
    }
}