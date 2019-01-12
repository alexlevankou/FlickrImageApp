package by.alexlevankou.flickrimageapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.List;

import by.alexlevankou.flickrimageapp.R;
import by.alexlevankou.flickrimageapp.model.Post;
import by.alexlevankou.flickrimageapp.viewModel.ListViewModel;

public class ListFragment extends Fragment {

    private ListViewModel mViewModel;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        mViewModel.getAllPosts().observe(getActivity(), new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable List<Post> posts) {
                if(posts != null) {
                    boolean f = true;
                }
            }
        });
    }
}
