package by.alexlevankou.flickrimageapp.presenter;

import java.util.List;

import by.alexlevankou.flickrimageapp.model.FlickrPost;

public interface ListFragmentView extends BaseContract.View {
    void showPosts(List<FlickrPost> flickrPosts);
}
