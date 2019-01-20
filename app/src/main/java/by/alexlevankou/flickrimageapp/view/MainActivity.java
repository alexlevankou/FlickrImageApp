package by.alexlevankou.flickrimageapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import by.alexlevankou.flickrimageapp.presenter.BaseContract;

import by.alexlevankou.flickrimageapp.R;


public class MainActivity extends AppCompatActivity implements BaseContract.View, ListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(by.alexlevankou.flickrimageapp.R.layout.main_activity);
        if (savedInstanceState == null) {
            showFragment(ListFragment.newInstance(), getResources().getString(R.string.list_fragment_tag));
        }
    }

    @Override
    public void onBackPressed() {
        Fragment itemFragment = getSupportFragmentManager().findFragmentByTag(getResources().getString(R.string.item_fragment_tag));
        if (itemFragment != null && itemFragment.isVisible()) {
            showFragment(ListFragment.newInstance(), getResources().getString(R.string.list_fragment_tag));
        } else {
            finishAndRemoveTask();
        }
    }

    @Override
    public void onListFragmentInteraction(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(getResources().getString(R.string.bundle_id), id);
        Fragment itemFragment = ItemFragment.newInstance();
        itemFragment.setArguments(bundle);
        showFragment(itemFragment, getResources().getString(R.string.item_fragment_tag));
    }

    private void showFragment(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(by.alexlevankou.flickrimageapp.R.id.container, fragment, tag)
                .commit();
    }
}
