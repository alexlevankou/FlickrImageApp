package by.alexlevankou.flickrimageapp.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class PostAndPhoto {

    @PrimaryKey
    private int id;

    @Embedded(prefix = "post")
    private Post post;

    @Embedded(prefix = "photo")
    private Photo photo;

    public PostAndPhoto(Post post, Photo photo) {
        this.post = post;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
