package by.alexlevankou.flickrimageapp.repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.Nullable;

import java.util.List;

import by.alexlevankou.flickrimageapp.model.FlickrPost;
import io.reactivex.Flowable;

@Dao
public interface PostAndPhotoDao {

    @Nullable
    @Query("SELECT * FROM FlickrPost")
    Flowable<List<FlickrPost>> getAllPosts();

    @Query("SELECT * FROM FlickrPost WHERE id = :id")
    Flowable<FlickrPost> getPostById(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPosts(List<FlickrPost> posts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FlickrPost flickrPost);

    @Update
    void update(FlickrPost flickrPost);

    @Delete
    void delete(FlickrPost flickrPost);
}
