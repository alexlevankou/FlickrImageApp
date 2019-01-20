package by.alexlevankou.flickrimageapp.presenter;

import by.alexlevankou.flickrimageapp.App;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class ItemPresenter extends BasePresenter<ItemFragmentView> implements BaseContract.Presenter {

    public void onActivityCreated(int id) {
        view.hideViews();
        view.showLoading();

        BaseContract.Model model = App.getInstance().getRepository();
        Disposable disposable = model.getPost(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(post -> {
                    if(post != null) {
                        view.showPost(post);
                    }
                });
        disposables.add(disposable);
    }

    public void onPhotoLoaded() {
        view.hideLoading();
        view.showViews();
    }

    public void onPhotoError() {
        view.hideLoading();
        view.showNoDataText();
    }
}
