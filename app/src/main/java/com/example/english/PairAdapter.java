package com.example.english;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PairAdapter extends RecyclerView.Adapter<PairAdapter.PairViewHolder> {

    private List<Pair> pairs;

    public PairAdapter(List<Pair> pairs) {
        this.pairs = pairs;
    }

    @NonNull
    @Override
    public PairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pair, parent, false);
        return new PairViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PairViewHolder holder, int position) {
        Pair pair = pairs.get(position);
        holder.wordTextView.setText(pair.getWord());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(holder.itemView.getContext(), android.R.layout.simple_spinner_item, pair.getPossibleTranslations());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.translationSpinner.setAdapter(adapter);

        holder.translationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                pair.setUserTranslation(parent.getItemAtPosition(pos).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pair.setUserTranslation(null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pairs.size();
    }

    static class PairViewHolder extends RecyclerView.ViewHolder {

        TextView wordTextView;
        Spinner translationSpinner;

        public PairViewHolder(@NonNull View itemView) {
            super(itemView);
            wordTextView = itemView.findViewById(R.id.wordTextView);
            translationSpinner = itemView.findViewById(R.id.translationSpinner);
        }
    }
}