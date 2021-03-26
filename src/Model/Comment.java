package Model;

public class Comment extends Interaction{

    private int commentId;
    private String commentData;
    private int commentType;


    public Comment(Post post, User user,int interactionid,int commentId) {
        super(post,user,interactionid);
        this.commentId=commentId;

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
