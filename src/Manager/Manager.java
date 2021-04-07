package Manager;

import DbServer.DbManager;
import Model.*;

import java.sql.Timestamp;
import java.util.*;

public class Manager {
    private User currentUser;
    private Map<Integer, User> users;
    private Map<Integer, Post> posts;
    private Map<Integer, Interaction> interactions;
    private Map<Integer,ArrayList<Integer>> allInteractionsToPost;
    private Map<Integer,Comment> comments;
    private Map<Integer,Integer> interactionToComment;
    private Map<Integer,ArrayList<Integer> > allCommentsToPost;
    private Map<User,User> friendships;

    private  final DbManager dbManager;

    public Manager() {
        currentUser = null;
        users = new HashMap<>();
        posts = new HashMap<>();
        interactions=new HashMap<>();
        comments =new HashMap<>();
        dbManager = new DbManager();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void addUser( String userName, String firstName,String lastName,String email, String password,
                        Date dob, String city) {

        int id= dbManager.getNextUserIdDB();

            User user = new User(email, password);
            user.setUserName(userName);
            user.setLastName(lastName);
            user.setFirstName(firstName);
            user.setDateOfBirth((java.sql.Date) dob);
            user.setCity(city);
            users.put(id, user);
            System.out.println(user);
            this.setCurrentUser(user);
            dbManager.addUserDB(user);
            System.out.println("User added successfully...");

    }

    public void removeUser(int  id) {
        User user = getUser(id);
        if (user != null) {
                dbManager.removeUserDB(user);
                users.remove(id);
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
    public void addPost(String postName,String postContent,User postUser){
        int postId= dbManager.getNextPostIdDB();
        Timestamp currTimestamp=new Timestamp(System.currentTimeMillis());
        Post post=new Post(postUser);
        post.setPostId(postId);
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
                System.out.println("post removed successfully...");

        } else {
            System.out.println("Error occurred...Can not remove Post...");
        }
    }

    public void addInteraction(Post post,User user,int interactionType,String commentData,int replyTo,int commentType){
        int interactionId= dbManager.getNextInteractionIdDB();
        Interaction interaction= new Interaction(post,user);
        interaction.setInteractionId(interactionId);
        interaction.setInteractionType(interactionType);
        if(interactionType==2){

            addComment(commentData,interaction);
        }
        else if(interactionType==3){
            interaction.setReplyTo(replyTo);
        }
        else if(interactionType==4){

            interaction.setReplyTo(replyTo);
            addComment(commentData,interaction);
        }
        interactions.put(interactionId,interaction);
        dbManager.addInteractionDB(interaction);

    }
    public void addComment(String commentData,Interaction interaction){
        int commentid= dbManager.getNextCommentIdDB();
        Comment comment=new Comment(interaction.getPost(),interaction.getUser());
        comment.setInteractionId(interaction.getInteractionId());
        comment.setCommentId(commentid);
        comment.setCommentData(commentData);
        comments.put(commentid,comment);
        int key=interaction.getPost().getPostId1();
        ArrayList<Integer> list = allCommentsToPost.computeIfAbsent(key, k -> new ArrayList<>());
        list.add(commentid);
        dbManager.addCommentDB(comment);

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
                interactions.remove(interactionId);
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
