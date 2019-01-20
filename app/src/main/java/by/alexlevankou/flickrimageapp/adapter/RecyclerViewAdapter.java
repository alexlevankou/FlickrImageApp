package by.alexlevankou.flickrimageapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import by.alexlevankou.flickrimageapp.R;
import by.alexlevankou.flickrimageapp.model.FlickrPost;
import by.alexlevankou.flickrimageapp.view.ListFragment;


public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private List<FlickrPost> values;
    private final ListFragment.OnListFragmentInteractionListener mListener;

    public RecyclerViewAdapter(ListFragment.OnListFragmentInteractionListener listener) {
        mListener = listener;
        values = new ArrayList<>();
    }

    public void setItems(List<FlickrPost> items)
    {
        values = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        FlickrPost item = values.get(position);
        ((ViewHolder)holder).bindItem(item);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
