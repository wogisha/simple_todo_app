package com.codepath.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    private String itemText;
    private int itemIndex;

    EditText editText ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        itemText = getIntent().getStringExtra(MainActivity.ITEM_STRING);
        itemIndex = getIntent().getIntExtra(MainActivity.ITEM_INDEX, 0);

        editText = (EditText) findViewById(R.id.editText);
        editText.setText(itemText);

        editText.setSelected(true);
        editText.setSelection(itemText.length());
    }

    public void saveItem(View view) {
        Intent data = new Intent();

        data.putExtra(MainActivity.ITEM_STRING, editText.getText().toString());
        data.putExtra(MainActivity.ITEM_INDEX, itemIndex);

        setResult(RESULT_OK, data);

        finish();
    }
}
