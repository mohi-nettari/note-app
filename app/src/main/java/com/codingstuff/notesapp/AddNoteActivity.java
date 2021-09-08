package com.codingstuff.notesapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AddNoteActivity extends AppCompatActivity {

    private EditText mTitleEdit , mDescEdit;
    private Button mSaveNoteBtn;
    private NumberPicker numberPicker;
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private NoteViewModel noteViewModel;
    public static final String EXTRA_SEE = "codingstuff.notesapp.EXTRA_TITLE";
    public static final String EXTRA_TITLE = "com.codingstuff.notesapp.EXTRA_TITLE";
    public static final String EXTRA_ID = "com.codingstuff.notesapp.EXTRA_ID";
    public static final String EXTRA_DESC = "com.codingstuff.notesapp.EXTRA_DESC";
    public static final String EXTRA_PRIORITY = "com.codingstuff.notesapp.EXTRA_PRIORITY";
    private RecyclerView.ViewHolder viewHolder;

//On key Down Events
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
          // moveTaskToBack(true);
            String title = mTitleEdit.getText().toString();
            String desc = mDescEdit.getText().toString();
            int priority = numberPicker.getValue();
            if (title.trim().isEmpty() && desc.trim().isEmpty()){
                Intent intent = new Intent(AddNoteActivity.this,MainActivity.class);
                Toast.makeText(AddNoteActivity.this ,"No Note Has Been Added", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                return false;
            }

            if(title.trim().isEmpty()){
                title = " ";

            }
            else if (desc.trim().isEmpty()){
                desc = " ";

            }

            Intent dataIntent = new Intent();
            dataIntent.putExtra(EXTRA_TITLE ,title);
            dataIntent.putExtra(EXTRA_DESC , desc);
            dataIntent.putExtra(EXTRA_PRIORITY , priority);

            int id = getIntent().getIntExtra(EXTRA_ID , -1);
            if (id != -1){
                dataIntent.putExtra(EXTRA_ID , id);
            }

            setResult(RESULT_OK , dataIntent);
            Intent intent = new Intent(AddNoteActivity.this,MainActivity.class);
            //Toast.makeText(AddNoteActivity.this ,"No Note Has Been Added", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

//intializing the widgits

        mTitleEdit = findViewById(R.id.editTextTitle);
        mDescEdit = findViewById(R.id.editTextDesc);
        mSaveNoteBtn = findViewById(R.id.saveBtn);
        numberPicker = findViewById(R.id.number_picker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);

        Intent editIntent = getIntent();

        if (editIntent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            mTitleEdit.setText(editIntent.getStringExtra(EXTRA_TITLE));
            mDescEdit.setText(editIntent.getStringExtra(EXTRA_DESC));
            numberPicker.setValue(editIntent.getIntExtra(EXTRA_PRIORITY , 1));
        }else if (editIntent.hasExtra(EXTRA_SEE)){
            setTitle("See Note");
            mTitleEdit.setText(editIntent.getStringExtra(EXTRA_TITLE));
            mDescEdit.setText(editIntent.getStringExtra(EXTRA_DESC));
            numberPicker.setValue(editIntent.getIntExtra(EXTRA_PRIORITY , 1));
            Toast.makeText(AddNoteActivity.this, "U Can't Change The Note From Here If U Want To Change The Note Go Back And Swap Back", Toast.LENGTH_LONG).show();

        }
        else{
            setTitle("Add Note");
        }

        //save Button Envents
        mSaveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mTitleEdit.getText().toString();
                String desc = mDescEdit.getText().toString();
                int priority = numberPicker.getValue();

               if (title.trim().isEmpty() && desc.trim().isEmpty()){
                    Toast.makeText(AddNoteActivity.this, "Emtpy fields Are not Allowed", Toast.LENGTH_SHORT).show();
                    return;
                }



               if(title.trim().isEmpty()){
                   title = " ";

               }
               else if (desc.trim().isEmpty()){
                   desc = " ";

               }
                Intent dataIntent = new Intent();
                dataIntent.putExtra(EXTRA_TITLE ,title);
                dataIntent.putExtra(EXTRA_DESC , desc);
                dataIntent.putExtra(EXTRA_PRIORITY , priority);

                int id = getIntent().getIntExtra(EXTRA_ID , -1);
                if (id != -1){
                    dataIntent.putExtra(EXTRA_ID , id);
                }

                setResult(RESULT_OK , dataIntent);
                finish();
                }
                
        });



    }
}

















