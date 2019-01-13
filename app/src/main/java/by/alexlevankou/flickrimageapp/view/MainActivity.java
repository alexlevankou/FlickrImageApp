package by.alexlevankou.flickrimageapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(by.alexlevankou.flickrimageapp.R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(by.alexlevankou.flickrimageapp.R.id.container, ListFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void onListFragmentInteraction(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        Fragment itemFragment = ItemFragment.newInstance();
        itemFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(by.alexlevankou.flickrimageapp.R.id.container, itemFragment)
                .addToBackStack(null)
                .commit();
    }
}
