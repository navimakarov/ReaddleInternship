package com.makarov.readdleinternship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static com.makarov.readdleinternship.ItemAdapter.SPAN_LIST_COUNT;
import static com.makarov.readdleinternship.ItemAdapter.SPAN_GRID_COUNT;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<Profile> profiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addUsers();
        gridLayoutManager = new GridLayoutManager(this, SPAN_LIST_COUNT);
        itemAdapter = new ItemAdapter(profiles, gridLayoutManager);
        recyclerView = (RecyclerView) findViewById(R.id.contacts_recView);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(gridLayoutManager.getSpanCount() == SPAN_LIST_COUNT) {
            outState.putInt("SPAN_COUNT", SPAN_LIST_COUNT);
        }
        else {
            outState.putInt("SPAN_COUNT", SPAN_GRID_COUNT);
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        gridLayoutManager.setSpanCount(savedInstanceState.getInt("SPAN_COUNT"));
        itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switchLayout();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem switchLayoutItem = menu.findItem(R.id.switch_layout_menu_item);
        if(gridLayoutManager.getSpanCount() == SPAN_LIST_COUNT) {
            switchLayoutItem.setTitle(R.string.grid_menu_item_name);
        }
        else {
            switchLayoutItem.setTitle(R.string.list_menu_item_name);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private void switchLayout() {
        if(gridLayoutManager.getSpanCount() == SPAN_LIST_COUNT) {
            gridLayoutManager.setSpanCount(SPAN_GRID_COUNT);
        }
        else {
            gridLayoutManager.setSpanCount(SPAN_LIST_COUNT);
        }
        itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
    }
}