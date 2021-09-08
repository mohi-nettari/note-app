package com.codingstuff.notesapp;

import static android.app.Activity.RESULT_OK;
import static androidx.core.app.ActivityCompat.startActivityForResult;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends ListAdapter<Note , NoteAdapter.NoteViewHolder> {

    Context context;

    public NoteAdapter(Context context){
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull  Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item , parent , false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder holder, int position) {

        
        Note note = getItem(position);
        holder.mTitle.setText(note.getTitle());
        holder.mDesc.setText(note.getDescription());
        holder.mPriority.setText(String.valueOf(note.getPriority()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddNoteActivity.class);
                intent.putExtra(AddNoteActivity.EXTRA_SEE,"0");
                intent.putExtra(AddNoteActivity.EXTRA_ID,note.getId());
                intent.putExtra(AddNoteActivity.EXTRA_TITLE,note.getTitle());
                intent.putExtra(AddNoteActivity.EXTRA_DESC,note.getDescription());
                intent.putExtra(AddNoteActivity.EXTRA_PRIORITY,note.getPriority());
            }
        });

    }


    public Note getNote(int position){
        return getItem(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle , mDesc , mPriority;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.title_tv);
            mDesc = itemView.findViewById(R.id.description_Tv);
            mPriority = itemView.findViewById(R.id.priority_tv);
        }
    }
}
