package by.alexlevankou.flickrimageapp.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import by.alexlevankou.flickrimageapp.model.PostAndPhoto;

@Database(entities = {PostAndPhoto.class}, version = 1)
public abstract class PostAndPhotoDatabase extends RoomDatabase {
    public abstract PostAndPhotoDao postAndPhotoDao();
}
