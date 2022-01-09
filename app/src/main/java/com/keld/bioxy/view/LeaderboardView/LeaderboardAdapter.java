package com.keld.bioxy.view.LeaderboardView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.keld.bioxy.R;
import com.keld.bioxy.model.Leaderboard;

import java.util.Arrays;
import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.CardViewViewHolder> {
    private Context context;
    private List<Leaderboard.Leaderboards> leaderboardsList;

    public LeaderboardAdapter(Context context) {
        this.context = context;
    }
    public List<Leaderboard.Leaderboards> getLeaderboardsList() {
        return leaderboardsList;
    }
    public void setLeaderboardsList(List<Leaderboard.Leaderboards> coursesList) {
        this.leaderboardsList = coursesList;
    }


    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_leaderboard, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final Leaderboard.Leaderboards results = getLeaderboardsList().get(position);
        int ranking = 0;
        if (results.getDifficulty().equals("Mudah")) {
            if (ranking == 0) {
                ranking = 1;
            }
            ranking += 1;
        }
        if (results.getDifficulty().equals("Sedang")) {
            if (getLeaderboardsList().get(position-1).getDifficulty().equals("Mudah")) {
                ranking = 1;
            }
            else {
                ranking += 1;
            }
        }
        if (results.getDifficulty().equals("Sulit")) {
            if (getLeaderboardsList().get(position-1).getDifficulty().equals("Sedang")) {
                ranking = 1;
            }
            else {
                ranking += 1;
            }
        }
        if (results.getDifficulty().equals("Sangat Sulit")) {
            if (getLeaderboardsList().get(position-1).getDifficulty().equals("Sulit")) {
                ranking = 1;
            }
            else {
                ranking += 1;
            }
        }

        holder.lb_rank.setText("" + ranking);
        holder.lb_difficulty.setText(results.getDifficulty());
        holder.lb_username.setText(results.getUsername());
        holder.lb_point.setText("Poin: " + results.getPoint());
        holder.lb_accuracy.setText("Akurasi: " + results.getAccuracy() + "%");
        holder.lb_date.setText("Tanggal Pengerjaan:\n" + results.getCreated_at());
    }

    @Override
    public int getItemCount() {
        int itemCount;
        if (getLeaderboardsList() != null && !getLeaderboardsList().isEmpty()) {
            itemCount = getLeaderboardsList().size();
        }
        else {
            itemCount = 0;
        }
        return itemCount;
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView lb_rank, lb_difficulty, lb_username, lb_point, lb_accuracy, lb_date;
        CardView cardView;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            lb_rank = itemView.findViewById(R.id.lb_rank);
            lb_difficulty = itemView.findViewById(R.id.lb_difficulty);
            lb_username = itemView.findViewById(R.id.lb_username);
            lb_point = itemView.findViewById(R.id.lb_point);
            lb_accuracy = itemView.findViewById(R.id.lb_accuracy);
            lb_date = itemView.findViewById(R.id.lb_date);
            cardView = itemView.findViewById(R.id.cv_layout_leaderboard);
        }
    }
}
