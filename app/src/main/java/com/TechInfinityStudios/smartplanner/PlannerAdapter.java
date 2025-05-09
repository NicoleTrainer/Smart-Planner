package com.TechInfinityStudios.smartplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlannerAdapter extends RecyclerView.Adapter<PlannerAdapter.PlannerViewHolder> {
    private List<PlannerEvent> eventList;
    private DatabaseHelper dbHelper;

    public PlannerAdapter(List<PlannerEvent> eventList, DatabaseHelper dbHelper) {
        this.eventList = eventList;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public PlannerAdapter.PlannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new PlannerAdapter.PlannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlannerAdapter.PlannerViewHolder holder, int position) {
        PlannerEvent event = eventList.get(position);
        holder.EventTitle.setText(event.getTitle());
        holder.EventTime.setText(event.getTime());

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class PlannerViewHolder extends RecyclerView.ViewHolder {
        TextView EventTitle, EventTime;
        public PlannerViewHolder(@NonNull View itemView) {
            super(itemView);
            EventTitle = itemView.findViewById(R.id.eventName);
            EventTime = itemView.findViewById(R.id.eventTime);
        }
    }
}