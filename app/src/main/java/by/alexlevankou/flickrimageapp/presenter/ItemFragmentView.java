package by.alexlevankou.flickrimageapp.presenter;

import by.alexlevankou.flickrimageapp.model.FlickrPost;

public interface ItemFragmentView extends BaseContract.View {
    void showPost(FlickrPost post);
}
