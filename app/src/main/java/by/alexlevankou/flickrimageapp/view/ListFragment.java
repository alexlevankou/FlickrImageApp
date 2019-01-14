package by.alexlevankou.flickrimageapp.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import by.alexlevankou.flickrimageapp.R;
import by.alexlevankou.flickrimageapp.adapter.RecyclerViewAdapter;
import by.alexlevankou.flickrimageapp.model.FlickrPost;
import by.alexlevankou.flickrimageapp.presenter.ListFragmentView;
import by.alexlevankou.flickrimageapp.presenter.ListPresenter;
import by.alexlevankou.flickrimageapp.viewModel.ListViewModel;

public class ListFragment extends Fragment implements ListFragmentView {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    private ListPresenter presenter;
    private ListViewModel mViewModel;
    private OnListFragmentInteractionListener mListener;
    private RecyclerViewAdapter mRecycleViewAdapter;

    private RecyclerView mRecyclerView;
    private TextView mNoDataText;


    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.list);
        mNoDataText = view.findViewById(R.id.no_data_text);

        if (mRecyclerView != null) {
            Context context = view.getContext();
            if (mColumnCount <= 1) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            mRecycleViewAdapter = new RecyclerViewAdapter(context, mListener);
            mRecyclerView.setAdapter(mRecycleViewAdapter);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = ViewModelProviders.of(getActivity()).get(ListPresenter.class);
        presenter.attachView(this, getLifecycle());
        presenter.onActivityCreated();

//        mViewModel = ViewModelProviders.of(getActivity()).get(ListViewModel.class);
//        mViewModel.getAllPosts().observe(getActivity(), new Observer<List<PostAndPhoto>>() {
//            @Override
//            public void onChanged(@Nullable List<PostAndPhoto> posts) {
//                if(posts != null && posts.size() > 0) {
//                    showDataList(posts);
//                } else {
//                    showNoDataText();
//                }
//            }
//        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showPosts(List<FlickrPost> posts) {
        if(posts != null && posts.size() > 0) {
            showDataList(posts);
        } else {
            showNoDataText();
        }
    }

    public void showDataList(List<FlickrPost> posts) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mNoDataText.setVisibility(View.GONE);
        mRecycleViewAdapter.setItems(posts);
    }

    public void showNoDataText() {
        mRecyclerView.setVisibility(View.GONE);
        mNoDataText.setVisibility(View.VISIBLE);
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(int id);
    }
}
