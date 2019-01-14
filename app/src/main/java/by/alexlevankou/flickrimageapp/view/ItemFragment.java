package by.alexlevankou.flickrimageapp.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import by.alexlevankou.flickrimageapp.R;
import by.alexlevankou.flickrimageapp.model.FlickrPost;
import by.alexlevankou.flickrimageapp.presenter.ItemFragmentView;
import by.alexlevankou.flickrimageapp.presenter.ItemPresenter;

public class ItemFragment extends Fragment implements ItemFragmentView {

    private ItemPresenter presenter;

    private TextView mTitle;
    private ImageView mPhoto;
    private TextView mPhotoTitle;
    private TextView mBody;

    public static ItemFragment newInstance() {
        return new ItemFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(by.alexlevankou.flickrimageapp.R.layout.item_fragment, container, false);
        mTitle = view.findViewById(R.id.postTitle);
        mPhoto = view.findViewById(R.id.photo);
        mPhotoTitle = view.findViewById(R.id.photoTitle);
        mBody = view.findViewById(R.id.body);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle extras = getArguments();
        if(extras != null) {
            int id = extras.getInt("id");
            presenter = ViewModelProviders.of(getActivity()).get(ItemPresenter.class);
            presenter.attachView(this, getLifecycle());
            presenter.onActivityCreated(id);
        }
    }

    @Override
    public void showPost(FlickrPost flickrPost) {
        mTitle.setText(flickrPost.getPost().getTitle());
        mPhotoTitle.setText(flickrPost.getPhoto().getTitle());
        mBody.setText(flickrPost.getPost().getBody());
        Picasso
            .with(getActivity())
            .load(flickrPost.getPhoto().getUrl())
            .into(mPhoto);
    }
}
