package by.alexlevankou.flickrimageapp.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import by.alexlevankou.flickrimageapp.App;
import by.alexlevankou.flickrimageapp.model.Post;

public class ListViewModel extends ViewModel {

    public LiveData<List<Post>> getAllPosts() {
        return App.getInstance().getRepository().getAllPosts();
    }

    public LiveData<Post> getPost(int id) {
        return App.getInstance().getRepository().getPost(id);
    }
}

