package by.alexlevankou.flickrimageapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import by.alexlevankou.flickrimageapp.R;
import by.alexlevankou.flickrimageapp.model.FlickrPost;
import by.alexlevankou.flickrimageapp.view.ListFragment;

class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ListFragment.OnListFragmentInteractionListener itemListener;
    private final TextView titleText;
    private final TextView photoUrl;

    ViewHolder(View view, ListFragment.OnListFragmentInteractionListener itemListener) {
        super(view);
        view.setOnClickListener(this);
        this.itemListener = itemListener;
        titleText = view.findViewById(R.id.title);
        photoUrl = view.findViewById(R.id.photo_url);
    }

    void bindItem(FlickrPost flickrPost){
        titleText.setText(flickrPost.getPost().getTitle());
        photoUrl.setText(flickrPost.getPhoto().getUrl());
    }

    @Override
    public void onClick(View v) {
        itemListener.onListFragmentInteraction(this.getAdapterPosition());
    }
}
