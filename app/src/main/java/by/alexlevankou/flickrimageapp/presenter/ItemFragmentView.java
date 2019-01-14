package by.alexlevankou.flickrimageapp.presenter;

import by.alexlevankou.flickrimageapp.model.FlickrPost;

public interface ItemFragmentView extends BaseContract.View {
    void showPost(FlickrPost post);
    void hideViews();
    void showViews();
    void showLoading();
    void hideLoading();
    void showNoDataText();
}
