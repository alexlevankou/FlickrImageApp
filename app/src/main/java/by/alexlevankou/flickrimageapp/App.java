package by.alexlevankou.flickrimageapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import by.alexlevankou.flickrimageapp.repository.PostDatabase;
import by.alexlevankou.flickrimageapp.repository.Repository;

public class App extends Application {

    public static App mInstance;
    private PostDatabase mDatabase;
    private Repository mRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mDatabase = Room.databaseBuilder(this, PostDatabase.class, "post_database")
                .fallbackToDestructiveMigration()
                .build();
        mRepository = new Repository(mDatabase.postDao());
    }

    public static App getInstance() {
        return mInstance;
    }

    public PostDatabase getDatabase() {
        return mDatabase;
    }

    public Repository getRepository() {
        return mRepository;
    }
}
