package android.eservices.recyclerview;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private List<GameViewModel> gameViewModelList;
    private GameActionInterface gameActionInterface;

    public GameAdapter(MainActivity mainActivity) {
        gameActionInterface = mainActivity;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);
        return new GameViewHolder(v, gameActionInterface);
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
        private ImageButton gameInfoButton, gameButton;
        private GameActionInterface gameActionInterface;
        private GameViewModel gameViewModel;


        public GameViewHolder(final View view, final GameActionInterface gameActionInterface){
            super(view);
            gameView = view;
            gameTitleTextView = view.findViewById(R.id.title_textview);
            gameImageView = view.findViewById(R.id.icon_imageview);
            gameDescriptionTextView = view.findViewById(R.id.description_textview);
            gameInfoButton = view.findViewById(R.id.info_button);
            gameButton = view.findViewById(R.id.game_button);
            this.gameActionInterface = gameActionInterface;

            setUpListeners();

        }

        private void setUpListeners() {
            gameInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gameActionInterface.onGameInfoClicked(gameViewModel.getTitle());
                }
            });

            gameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gameActionInterface.onGameClicked(gameViewModel.getTitle());
                }
            });
        }

        public void bind(GameViewModel gameViewModel){
            this.gameViewModel = gameViewModel;
            gameTitleTextView.setText(gameViewModel.getTitle());
            Glide.with(gameView).load(Uri.parse(gameViewModel.getImageUrl())).into(gameImageView);
            gameDescriptionTextView.setText(gameViewModel.getDescription());
        }
    }
}
