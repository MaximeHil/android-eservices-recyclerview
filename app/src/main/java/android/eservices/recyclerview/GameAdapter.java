package android.eservices.recyclerview;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private List<GameViewModel> gameViewModelList;

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);
        return new GameViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        holder.bind(gameViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.gameViewModelList.size();
    }

    public void bindGameViewModelList(List<GameViewModel> gameViewModelList){
        this.gameViewModelList = gameViewModelList;
        notifyDataSetChanged();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {

        private TextView gameTitleTextView, gameDescriptionTextView;
        private ImageView gameImageView;
        private View gameView;

        public GameViewHolder(View view){
            super(view);
            this.gameView = view;
            this.gameTitleTextView = view.findViewById(R.id.title_textview);
            this.gameImageView = view.findViewById(R.id.icon_imageview);
            this.gameDescriptionTextView = view.findViewById(R.id.description_textview);

        }

        public void bind(GameViewModel gameViewModel){
            this.gameTitleTextView.setText(gameViewModel.getTitle());
            Glide.with(this.gameView).load(Uri.parse(gameViewModel.getImageUrl())).into(this.gameImageView);
            this.gameDescriptionTextView.setText(gameViewModel.getDescription());
        }
    }
}
