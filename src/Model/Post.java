package Model;

import java.sql.*;
import java.util.Scanner;

public class Post {

    private static int PostNo;
    private int postId;
    private String postTitle;
    private Timestamp postTimestamp;
    private String content;
    //contentId
    private int postDataType;
    private User user;




    Post(User user){
        this.user=user;
        postId=getPostId();
    }

    Post(User user,int pstId){
        this.user=user;
        this.postId=pstId;
    }

    int getPostId(){
        PostNo++;
        return PostNo;
    }
    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public Timestamp getPostTimestamp() {
        return postTimestamp;
    }

    public void setPostTimestamp(Timestamp postTimestamp) {
        this.postTimestamp = postTimestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPostDataType() {
        return postDataType;
    }

    public void setPostDataType(int postDataType) {
        this.postDataType = postDataType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postTitle='" + postTitle + '\'' +
                ", postTimestamp=" + postTimestamp +
                ", content='" + content + '\'' +
                ", postDataType=" + postDataType +
                ", user=" + user +
                '}';
    }

}
