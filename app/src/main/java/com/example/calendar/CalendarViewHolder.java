package com.example.calendar;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final CalenderViewAdapter.OnItemListener onItemListener;
    public final TextView dayOfMonth;
    public CalendarViewHolder(CalenderViewAdapter. OnItemListener onItemListener, @NonNull View itemView) {
        super(itemView);
        this.onItemListener = onItemListener;
        dayOfMonth=itemView.findViewById(R.id.cellDayText);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
