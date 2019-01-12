package by.alexlevankou.flickrimageapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.Nullable;

import java.util.List;

import by.alexlevankou.flickrimageapp.model.Post;

@Dao
public interface PostDao {

    @Nullable
    @Query("SELECT * FROM post")
    LiveData<List<Post>> getAllPosts();

    @Query("SELECT * FROM post WHERE id = :id")
    LiveData<Post> getPostById(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPosts(List<Post> posts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Post post);

    @Update
    void update(Post post);

    @Delete
    void delete(Post post);
}
