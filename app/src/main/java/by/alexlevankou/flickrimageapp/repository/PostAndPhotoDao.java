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

import by.alexlevankou.flickrimageapp.model.PostAndPhoto;

@Dao
public interface PostAndPhotoDao {

    @Nullable
    @Query("SELECT * FROM postandphoto")
    LiveData<List<PostAndPhoto>> getAllPosts();

    @Query("SELECT * FROM postandphoto WHERE id = :id")
    LiveData<PostAndPhoto> getPostById(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPosts(List<PostAndPhoto> posts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PostAndPhoto postAndPhoto);

    @Update
    void update(PostAndPhoto postAndPhoto);

    @Delete
    void delete(PostAndPhoto postAndPhoto);
}
