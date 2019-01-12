package by.alexlevankou.flickrimageapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import by.alexlevankou.flickrimageapp.R;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<Integer> values;
    //private final OnListFragmentInteractionListener mListener;

    public RecyclerViewAdapter(Context context/*, OnListFragmentInteractionListener listener*/) {

        //mListener = listener;
    }

    public void setItems(List<Integer> items)
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onListFragmentInteraction(holder.item.getId());
                //}
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View itemView;
        //final ImageView image;
        //final TextView urlText;
        //final TextView nameText;
        Integer item;

        ViewHolder(View view) {
            super(view);
            itemView = view;
        }
    }
}
