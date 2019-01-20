package by.alexlevankou.flickrimageapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import by.alexlevankou.flickrimageapp.repository.PostAndPhotoDatabase;
import by.alexlevankou.flickrimageapp.repository.Repository;

public class App extends Application {

    private static App mInstance;
    private Repository mRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        PostAndPhotoDatabase mDatabase = Room.databaseBuilder(this, PostAndPhotoDatabase.class, "flickr_database")
                .fallbackToDestructiveMigration()
                .build();
        mRepository = new Repository(mDatabase.postAndPhotoDao());
    }

    public static App getInstance() {
        return mInstance;
    }

    public Repository getRepository() {
        return mRepository;
    }
}
