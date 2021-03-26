package Model;

public class Comment extends Interaction{

    private int commentId;
    private String commentData;
    private int commentType;
    private Comment replyTo;

    public Comment(Post post, User user) {
        super(post, user);
    }

    public Comment(Comment cmt,User user){
        super(cmt.getPost(),user);
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

    public Comment getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Comment replyTo) {
        this.replyTo = replyTo;
    }


}
