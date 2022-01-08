package com.keld.bioxy.view.LeaderboardView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keld.bioxy.model.Leaderboard;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {

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
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
