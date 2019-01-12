package by.alexlevankou.flickrimageapp.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import by.alexlevankou.flickrimageapp.model.Post;

@Database(entities = {Post.class}, version = 2)
public abstract class PostDatabase extends RoomDatabase {
    public abstract PostDao postDao();
}
