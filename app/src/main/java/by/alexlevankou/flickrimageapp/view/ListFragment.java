package by.alexlevankou.flickrimageapp.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import by.alexlevankou.flickrimageapp.R;
import by.alexlevankou.flickrimageapp.adapter.RecyclerViewAdapter;
import by.alexlevankou.flickrimageapp.model.FlickrPost;
import by.alexlevankou.flickrimageapp.presenter.ListFragmentView;
import by.alexlevankou.flickrimageapp.presenter.ListPresenter;

public class ListFragment extends Fragment implements ListFragmentView {

    private FragmentActivity mActivity;
    private ListPresenter presenter;
    private OnListFragmentInteractionListener mListener;
    private RecyclerViewAdapter mRecycleViewAdapter;

    private RecyclerView mRecyclerView;
    private TextView mNoDataText;
    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentActivity){
            mActivity =(FragmentActivity) context;
        }
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + getResources().getString(R.string.list_fragment_interaction_interface_not_implemented));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.list);
        mNoDataText = view.findViewById(R.id.no_data_text);
        mProgressBar = view.findViewById(R.id.progressBar);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_container);

        if (mRecyclerView != null) {
            Context context = view.getContext();
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            mRecycleViewAdapter = new RecyclerViewAdapter(mListener);
            mRecyclerView.setAdapter(mRecycleViewAdapter);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = ViewModelProviders.of(mActivity).get(ListPresenter.class);
        presenter.attachView(this, getLifecycle());
        presenter.onActivityCreated();
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.onLoadData();
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
        mListener = null;
    }

    @Override
    public void showPosts(List<FlickrPost> posts) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mNoDataText.setVisibility(View.GONE);
        mRecycleViewAdapter.setItems(posts);
    }

    @Override
    public void showNoDataText() {
        mRecyclerView.setVisibility(View.GONE);
        mNoDataText.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void stopRefreshing(){
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(int id);
    }
}
