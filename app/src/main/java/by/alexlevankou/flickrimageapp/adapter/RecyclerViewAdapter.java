package by.alexlevankou.flickrimageapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.alexlevankou.flickrimageapp.R;
import by.alexlevankou.flickrimageapp.model.FlickrPost;
import by.alexlevankou.flickrimageapp.view.ListFragment;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<FlickrPost> values;
    private final ListFragment.OnListFragmentInteractionListener mListener;

    public RecyclerViewAdapter(Context context, ListFragment.OnListFragmentInteractionListener listener) {
        mListener = listener;
        values = new ArrayList<>();
    }

    public void setItems(List<FlickrPost> items)
    {
        values = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = values.get(position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.item.getPost().getId());
                }
            }
        });
        holder.titleText.setText(holder.item.getPost().getTitle());
        holder.photoUrl.setText(holder.item.getPhoto().getUrl());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        final TextView titleText;
        final TextView photoUrl;
        FlickrPost item;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            titleText = view.findViewById(R.id.title);
            photoUrl = view.findViewById(R.id.photo_url);
        }
    }
}
