package by.alexlevankou.flickrimageapp.presenter;

import java.util.List;

import by.alexlevankou.flickrimageapp.App;
import by.alexlevankou.flickrimageapp.model.FlickrPost;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class ListPresenter extends BasePresenter<ListFragmentView> implements BaseContract.Presenter {

    public void onActivityCreated() {
        // loading here
        BaseContract.Model model = App.getInstance().getRepository();
        model.getAllPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FlickrPost>>() {
                    @Override
                    public void accept(List<FlickrPost> posts) throws Exception {
                        view.showPosts(posts);
                    }
                });

        model.requestData().subscribe(
                v -> model.addPost(v),
                e -> e.printStackTrace(),
                () -> model.updatePosts()
                );
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
