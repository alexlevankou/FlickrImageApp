package by.alexlevankou.flickrimageapp.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import by.alexlevankou.flickrimageapp.R;
import by.alexlevankou.flickrimageapp.model.FlickrPost;
import by.alexlevankou.flickrimageapp.presenter.ItemFragmentView;
import by.alexlevankou.flickrimageapp.presenter.ItemPresenter;

public class ItemFragment extends Fragment implements ItemFragmentView {

    private ItemPresenter presenter;
    private FragmentActivity mActivity;

    private TextView mTitle;
    private ImageView mPhoto;
    private TextView mPhotoTitle;
    private TextView mBody;
    private ProgressBar mProgressBar;
    private TextView mNoPhoto;

    public static ItemFragment newInstance() {
        return new ItemFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentActivity){
            mActivity =(FragmentActivity) context;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(by.alexlevankou.flickrimageapp.R.layout.item_fragment, container, false);
        mTitle = view.findViewById(R.id.postTitle);
        mPhoto = view.findViewById(R.id.photo);
        mPhotoTitle = view.findViewById(R.id.photoTitle);
        mBody = view.findViewById(R.id.body);
        mProgressBar = view.findViewById(R.id.loadingImage);
        mNoPhoto = view.findViewById(R.id.noPhotoText);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle extras = getArguments();
        if(extras != null) {
            int id = extras.getInt(getResources().getString(R.string.bundle_id));
            presenter = ViewModelProviders.of(mActivity).get(ItemPresenter.class);
            presenter.attachView(this, getLifecycle());
            presenter.onActivityCreated(id);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void showPost(FlickrPost flickrPost) {
        mTitle.setText(flickrPost.getPost().getTitle());
        mPhotoTitle.setText(flickrPost.getPhoto().getTitle());
        mBody.setText(flickrPost.getPost().getBody());
        Picasso
            .with(getActivity())
            .load(flickrPost.getPhoto().getUrl())
            .fit()
            .into(mPhoto, new Callback() {
                @Override
                public void onSuccess() {
                    presenter.onPhotoLoaded();
                }
                @Override
                public void onError() {
                    presenter.onPhotoError();
                }
            });
    }

    @Override
    public void showViews(){
        mTitle.setVisibility(View.VISIBLE);
        mPhoto.setVisibility(View.VISIBLE);
        mPhotoTitle.setVisibility(View.VISIBLE);
        mBody.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideViews(){
        mTitle.setVisibility(View.INVISIBLE);
        mPhoto.setVisibility(View.INVISIBLE);
        mPhotoTitle.setVisibility(View.INVISIBLE);
        mBody.setVisibility(View.INVISIBLE);
        mNoPhoto.setVisibility(View.GONE);
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
    public void showNoDataText() {
        mNoPhoto.setVisibility(View.VISIBLE);
    }
}
