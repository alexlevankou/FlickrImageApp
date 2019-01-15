package by.alexlevankou.flickrimageapp.presenter;

import java.util.List;

import by.alexlevankou.flickrimageapp.App;
import by.alexlevankou.flickrimageapp.model.FlickrPost;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ListPresenter extends BasePresenter<ListFragmentView> implements BaseContract.Presenter {

    private static boolean hasData = false;

    public void onActivityCreated() {
        view.showLoading();

        BaseContract.Model model = App.getInstance().getRepository();
        Disposable disposable = model.getAllPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FlickrPost>>() {
                    @Override
                    public void accept(List<FlickrPost> posts) throws Exception {
                        view.stopRefreshing();
                        view.hideLoading();
                        if(posts != null && posts.size() > 0) {
                            view.showPosts(posts);
                        } else {
                            view.showNoDataText();
                        }
                    }
                });
        disposables.add(disposable);

        if(!hasData) {
            onLoadData();
        }
    }

    public void onLoadData() {
        BaseContract.Model model = App.getInstance().getRepository();
        Disposable disposable = model.requestData().subscribe(
                v -> model.addPost(v),
                e -> e.printStackTrace(),
                () -> { model.updatePosts(); hasData = true;}
        );
        disposables.add(disposable);
    }
}
