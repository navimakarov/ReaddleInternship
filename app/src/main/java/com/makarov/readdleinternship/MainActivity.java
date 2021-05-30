package com.makarov.readdleinternship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static com.makarov.readdleinternship.ItemAdapter.SPAN_COUNT_ONE;
import static com.makarov.readdleinternship.ItemAdapter.SPAN_COUNT_FIVE;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<Profile> profiles;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addUsers();
        gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT_ONE);
        itemAdapter = new ItemAdapter(profiles, gridLayoutManager);
        recyclerView = (RecyclerView) findViewById(R.id.contacts_recView);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void addUsers() {
        profiles = new ArrayList<>(20);
        for(int i = 0; i < 20; i++) {
            profiles.add(new Profile(true, String.valueOf(i), String.valueOf(i)));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.change_view_menu, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switchLayout();

        item.setVisible(false);
        switch(item.getItemId()) {
            case R.id.list_menu_item:
                menu.findItem(R.id.grid_menu_item).setVisible(true);
                break;
            case R.id.grid_menu_item:
                menu.findItem(R.id.list_menu_item).setVisible(true);
        }
        return true;
    }

    private void switchLayout() {
        if(gridLayoutManager.getSpanCount() == SPAN_COUNT_ONE) {
            gridLayoutManager.setSpanCount(SPAN_COUNT_FIVE);
        }
        else {
            gridLayoutManager.setSpanCount(SPAN_COUNT_ONE);
        }
        itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
    }
}