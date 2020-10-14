package android.eservices.recyclerview;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GameActionInterface, ActionMode.Callback {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private GameAdapter mAdapter;

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        setupRecyclerView();


    }

    private void setupRecyclerView() {
        //TODO Bind recyclerview and set its adapter.
        recyclerView = findViewById(R.id.my_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        //Use data generator to get data to display.
        List<GameViewModel> gameViewModelList = DataGenerator.generateData();
        mAdapter = new GameAdapter(this);
        mAdapter.bindGameViewModelList(gameViewModelList);
        recyclerView.setLayoutManager(this.layoutManager);
        recyclerView.setAdapter(this.mAdapter);

    }

    public void displaySnackBar(String message) {
        //TODO write a method that displays a snackbar in the coordinator layout with the "message" parameter as content.
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onGameInfoClicked(String gameTitle) {
        displaySnackBar(getString(R.string.game_info_clicked, gameTitle));
    }

    @Override
    public void onGameClicked(String gameTitle) {
        displaySnackBar(getString(R.string.game_clicked, gameTitle));
    }

    //TODO create callback methods for item click
    //Use ressource strings to get the text to display
    // Called when the user selects a contextual menu item
    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        if (menuItem.getOrder() == 0) {
            //Handle click on What's hot menu item
            displaySnackBar(getString(R.string.whatshot_clicked));
        }
        return true;
    }
}