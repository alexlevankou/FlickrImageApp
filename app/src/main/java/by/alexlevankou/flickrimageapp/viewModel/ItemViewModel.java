package by.alexlevankou.flickrimageapp.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import by.alexlevankou.flickrimageapp.App;
import by.alexlevankou.flickrimageapp.model.PostAndPhoto;

public class ItemViewModel extends ViewModel {

    public LiveData<PostAndPhoto> getPost(int id) {
        return App.getInstance().getRepository().getPost(id);
    }
}
