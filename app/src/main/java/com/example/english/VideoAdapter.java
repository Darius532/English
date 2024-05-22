package com.example.english;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<Video> videoList;
    private Context context;
    private OnItemClickListener listener; // Объявление переменной интерфейса

    // Интерфейс для обработки кликов на элементах списка
    public interface OnItemClickListener {
        void onItemClick(String videoId);
    }
    // Конструктор адаптера
    public VideoAdapter(Context context, List<Video> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    // Метод для установки обработчика кликов
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Вложенный класс ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_title);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video = videoList.get(position);
        holder.textTitle.setText(video.getTitle());

        // Устанавливаем обработчик кликов на элемент списка
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(video.getVideoId()); // Вызываем метод onItemClick интерфейса
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}

