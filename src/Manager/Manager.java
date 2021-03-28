package Manager;

import DbServer.DbManager;
import Model.*;

import java.sql.Timestamp;
import java.util.*;

public class Manager {
    protected User currentUser;
    protected Map<Integer, User> users;
    protected Map<Integer, Post> posts;
    protected Map<Integer, Interaction> interactions;
    protected Map<Integer,ArrayList<Integer>> allInteractionsToPost;
    protected Map<Integer,Comment> comments;
    protected Map<Integer,Integer> interactionToComment;
    protected Map<Integer,ArrayList<Integer> > allCommentsToPost;
    protected Map<User,User> friendships;

    private DbManager dbManager;

    public Manager() {
        currentUser = null;
        users = new HashMap<Integer, User>();
        posts = new HashMap<Integer, Post>();
        interactions=new HashMap<Integer,Interaction>();
        comments =new HashMap<>();
        dbManager = new DbManager();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void addUser(int id, String userName, String email, String password,
                        Date dob, int gender, String mobileNo, String city) {

        if (users.containsKey(email)) {
            User user = new User(email, password);
            user.setUserName(userName);
            user.setDateOfBirth((java.sql.Date) dob);
            user.setCity(city);
            users.put(id, user);
            System.out.println(users);
            this.setCurrentUser(user);
            dbManager.addUserDB(user);
            System.out.println("User added successfully...");
        } else {
            System.out.println("Can not add user...");
        }
    }

    public void removeUser(int  id) {
        User user = getUser(id);
        if (user != null) {
                dbManager.removeUserDB(user);
                users.remove(user);
                System.out.println(users);
                System.out.println("User removed successfully...");

        } else {
            System.out.println("Error occurred...Can not remove user...");
        }
    }

    public User getUser(int id) {
        if (users.containsKey(id)) {
            return users.get(id);
        } else {
            System.out
                    .println("Your  id does not match with us... Please provide correct one...");
        }
        return null;
    }
    public void addPost(int postId,String postName,String postContent,User postUser){
        Timestamp currTimestamp=new Timestamp(System.currentTimeMillis());
        Post post=new Post(postUser,postId);
        post.setPostTitle(postName);
        post.setContent(postContent);
        post.setPostTimestamp(currTimestamp);
        posts.put(postId,post);
        dbManager.addPostDB(post);

    }

    public Post getPost(int postId) {
        if (posts.containsKey(postId)) {
            return posts.get(postId);
        } else {
            System.out.println("The screen you have entered is not available. Please check post id...");
        }
        return null;
    }

    public void removePost(int postID){
        Post post = getPost(postID);
        if (post != null) {

                dbManager.removePostDB(post);
                posts.remove(postID);
                System.out.println(users);
                System.out.println("User removed successfully...");

        } else {
            System.out.println("Error occurred...Can not remove Post...");
        }
    }

    public void addInteraction(Post post,User user,int interactionType,int interactionId,String commentData,int commentId,int replyTo,int commentType){
        Interaction interaction= new Interaction(post,user,interactionId);
        interaction.setInteractionType(interactionType);
        if(interactionType==2){
            addComment(commentId,commentData,interaction);
        }
        else if(interactionType==3){
            interaction.setReplyTo(commentId);
        }
        else if(interactionType==4){

            interaction.setReplyTo(commentId);
            addComment(commentId,commentData,interaction);
        }
        interactions.put(interactionId,interaction);
        dbManager.addInteractionDB(interaction);

    }
    public void addComment(int commentid,String commentData,Interaction interaction){
        Comment comment=new Comment(interaction.getPost(),interaction.getUser(),interaction.getInteractionId(),commentid);
        comment.setCommentData(commentData);
        comments.put(commentid,comment);
        int key=interaction.getPost().getPostId1();
        ArrayList<Integer> list =allCommentsToPost.get(key);
        if(list==null){
            list=new ArrayList<Integer>();
            allCommentsToPost.put(key,list);
        }
        list.add(commentid);
        dbManager.addComment(comment);

    }
    public Interaction getInteraction(int interactionId) {
        if (interactions.containsKey(interactionId)) {
            return interactions.get(interactionId);
        } else {
            System.out.println("The Interaction id  you have entered is not available. Please check interaction id  id...");
        }
        return null;
    }
    public void deleteInteraction(int interactionId){
        Interaction interaction = getInteraction(interactionId);
        if (interaction != null) {

                dbManager.removeInteractionDB(interaction);
                interactions.remove(interaction);
                System.out.println(users);
                System.out.println("Interaction  removed successfully...");
                Integer postId=interaction.getPost().getPostId1();
                ArrayList<Integer> list= allInteractionsToPost.get(postId);
                list.remove(new Integer(interactionId));
                if(interaction.getInteractionType()==2  || interaction.getInteractionType()==4){
                    ArrayList<Integer> list1 = allCommentsToPost.get(postId);
                    int commentId=interactionToComment.get(new Integer(interactionId));
                    list1.remove(new Integer(commentId));
                }

            }
        else {
                System.out.println("User email id or password does not match...");
            }

    }
}
