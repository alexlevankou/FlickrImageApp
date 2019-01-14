package by.alexlevankou.flickrimageapp.presenter;

import by.alexlevankou.flickrimageapp.App;
import by.alexlevankou.flickrimageapp.model.FlickrPost;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class ItemPresenter extends BasePresenter<ItemFragmentView> implements BaseContract.Presenter {

    public void onActivityCreated(int id) {
        // loading here
        BaseContract.Model model = App.getInstance().getRepository();
        model.getPost(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FlickrPost>() {
                    @Override
                    public void accept(FlickrPost post) throws Exception {
                        view.showPost(post);
                    }
                });
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
