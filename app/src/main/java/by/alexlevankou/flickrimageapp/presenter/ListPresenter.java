package by.alexlevankou.flickrimageapp.presenter;

import by.alexlevankou.flickrimageapp.App;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class ListPresenter extends BasePresenter<ListFragmentView> implements BaseContract.Presenter {

    private static boolean hasData = false;

    public void onActivityCreated() {
        view.showLoading();

        BaseContract.Model model = App.getInstance().getRepository();
        Disposable disposable = model.getAllPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(posts -> {
                    view.stopRefreshing();
                    view.hideLoading();
                    if(posts != null && posts.size() > 0) {
                        view.showPosts(posts);
                    } else {
                        view.showNoDataText();
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
                model::addPost,
                Throwable::printStackTrace,
                () -> { model.updatePosts(); hasData = true;}
        );
        disposables.add(disposable);
    }
}
