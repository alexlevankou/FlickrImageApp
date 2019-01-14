package by.alexlevankou.flickrimageapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import by.alexlevankou.flickrimageapp.presenter.BaseContract;

public class MainActivity extends AppCompatActivity implements BaseContract.View, ListFragment.OnListFragmentInteractionListener {

    private final static String LIST_FRAGMENT_TAG = "LIST_FRAGMENT";
    private final static String ITEM_FRAGMENT_TAG = "ITEM_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(by.alexlevankou.flickrimageapp.R.layout.main_activity);
        if (savedInstanceState == null) {
            showFragment(ListFragment.newInstance(), LIST_FRAGMENT_TAG);
        }
    }

    @Override
    public void onBackPressed() {
        Fragment itemFragment = getSupportFragmentManager().findFragmentByTag(ITEM_FRAGMENT_TAG);
        if (itemFragment != null && itemFragment.isVisible()) {
            showFragment(ListFragment.newInstance(), LIST_FRAGMENT_TAG);
        } else {
            finishAndRemoveTask();
        }
    }

    @Override
    public void onListFragmentInteraction(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        Fragment itemFragment = ItemFragment.newInstance();
        itemFragment.setArguments(bundle);
        showFragment(itemFragment, ITEM_FRAGMENT_TAG);
    }

    private void showFragment(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(by.alexlevankou.flickrimageapp.R.id.container, fragment, tag)
                .commit();
    }
}
