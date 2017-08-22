package com.codepath.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String > items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.lvItems);

        readItems();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        lvItems.setAdapter(itemsAdapter);



        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                items.remove(i);
                itemsAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });
    }

    public void onAddItem(View view) {
        EditText etNewItem = (EditText) findViewById(R.id.evNewItem);
        String itemText = etNewItem.getText().toString();
        items.add(itemText);
        etNewItem.setText("");
        writeItems();
    }

    private static final String TAG = "MainActivity";
    private void readItems(){
        File fileDir = getFilesDir();
        File todoFile = new File(fileDir, "todo.txt");

        try {
            items = new ArrayList<>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            e.printStackTrace();
            items = new ArrayList<>();
        }
    }
    private void writeItems(){
        File fileDir = getFilesDir();
        File todoFile = new File(fileDir, "todo.txt");

        try {
            FileUtils.writeLines(todoFile,items);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
