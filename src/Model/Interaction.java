package Model;

public class Interaction {

    protected int interactionId;
    protected Post post;
    protected User user;
    protected int interactionType;
    protected int replyTo;



    public Interaction(Post post, User user) {

        this.post = post;
        this.user = user;
    }

    public int getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(int interactionId) {
        this.interactionId = interactionId;
    }

    public int getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(int commentId) {
        this.replyTo = commentId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(int interactionType) {
        this.interactionType = interactionType;
    }

    @Override
    public String toString() {
        return "Interaction{" +
                "interactionId=" + interactionId +
                ", post=" + post +
                ", user=" + user +
                ", interactionType=" + interactionType +
                '}';
    }
}

