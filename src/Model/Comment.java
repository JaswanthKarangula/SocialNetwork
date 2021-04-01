package Model;

public class Comment extends Interaction{

    private int commentId;
    private String commentData;
    private int commentType;


    public Comment(Post post, User user) {
        super(post,user);
        this.commentId=commentId;

    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentData() {
        return commentData;
    }

    public void setCommentData(String commentData) {
        this.commentData = commentData;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

}
