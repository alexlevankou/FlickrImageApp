package by.alexlevankou.flickrimageapp.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import by.alexlevankou.flickrimageapp.App;
import by.alexlevankou.flickrimageapp.model.PostAndPhoto;

public class ListViewModel extends ViewModel {

    public LiveData<List<PostAndPhoto>> getAllPosts() {
        return App.getInstance().getRepository().getAllPosts();
    }

    public LiveData<PostAndPhoto> getPost(int id) {
        return App.getInstance().getRepository().getPost(id);
    }
}

