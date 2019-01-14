package by.alexlevankou.flickrimageapp.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import by.alexlevankou.flickrimageapp.model.FlickrPost;

@Database(entities = {FlickrPost.class}, version = 2)
public abstract class PostAndPhotoDatabase extends RoomDatabase {
    public abstract PostAndPhotoDao postAndPhotoDao();
}
