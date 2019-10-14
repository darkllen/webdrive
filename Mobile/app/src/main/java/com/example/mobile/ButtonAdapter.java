package com.example.mobile;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


import com.chauthai.swipereveallayout.SwipeRevealLayout;

import java.util.ArrayList;
import java.util.List;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.InfoViewHolder> {

    class InfoViewHolder extends RecyclerView.ViewHolder {
        LinearLayout main;
        CardView cv;
        SwipeRevealLayout revealLayout;
        Button hrefButton;
        Button deleteButton;
        public InfoViewHolder(View itemView) {
            super(itemView);
            main = itemView.findViewById(R.id.mainLa);
            cv = itemView.findViewById(R.id.cv);
            hrefButton = itemView.findViewById(R.id.button);
            deleteButton = itemView.findViewById(R.id.buttonDelete);
            revealLayout = itemView.findViewById(R.id.revLa);
        }
    }

    private List<Info> infos = new ArrayList<>();
    private MainActivity activity;

    public ButtonAdapter(List<Info> infos, MainActivity activity) {
        this.infos = infos;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ButtonAdapter.InfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.maket, viewGroup, false);
        return new InfoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonAdapter.InfoViewHolder infoViewHolder, int i) {
        infoViewHolder.hrefButton.setText(infos.get(i).getName());
        infoViewHolder.hrefButton.setOnClickListener(x->{

            infoViewHolder.main.removeView(infoViewHolder.cv);



            HttpRequests httpRequests = new HttpRequests(infos.get(i));
            httpRequests.start();
            while (httpRequests.isAlive()){}

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(infos.get(i).getHref()));
            infos.remove(i);
            activity.startActivity(browserIntent);

        });
        infoViewHolder.revealLayout.setSwipeListener(new SwipeRevealLayout.SwipeListener() {
            @Override
            public void onClosed(SwipeRevealLayout view) {

            }

            @Override
            public void onOpened(SwipeRevealLayout view) {
                infoViewHolder.main.removeView(infoViewHolder.cv);

                HttpRequests httpRequests = new HttpRequests(infos.get(i));
                httpRequests.start();
                while (httpRequests.isAlive()){}
                infos.remove(i);
            }

            @Override
            public void onSlide(SwipeRevealLayout view, float slideOffset) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}