package Caller;


import Manager.Manager;
import Model.*;

import java.io.DataInputStream;

public class Caller {
    public static void main(String[] args) throws Exception {

        Manager manager = new Manager();
        DataInputStream dis = new DataInputStream(System.in);

        String options="enter 0 to finish process \n"
                +"enter 1 to see users data \n"
                + "enter 2 to see posts data \n"
                + "enter 3 to add new   users  \n"
                +"enter 4 to delete a  users  \n"
                +"enter 5 to update users name \n"
                +"enter 6 to show all users ids of friends\n"
                +"enter 7 to show all comments on any post  \n"
                +"enter 8 to add posts  \n"
                +"enter 9 to add friends \n"
                +"enter 10 to add interaction   \n"
                +"enter 11 to get number of likes of a post   \n"
                +"enter 12 to show all posts  of friends  \n";

        System.out.println();

        User user;
        Post post ;
        Friendship friendship;
        Interaction interaction ;
        int user_id,post_id,interaction_id,interaction_type,replyTo,comment_id;
        int option=-1;
        String userName,firstName,lastName,email,pass,postName,postContent,comment_data;
        while(option!=0){
            System.out.println(options);
            option=dis.readInt();
            System.out.println("\n******************** \n");

            switch(option) {
                case 0:
                    option = 0;
                    System.out.println("Bye  :");
                    break;
                case 1:
                    System.out.println("Enter user id");
                    user_id= dis.readInt();
                    user= manager.getUser(user_id);
                    System.out.println(user);
                    break;
                case 2:
                    System.out.println("Enter post id");
                    post_id= dis.readInt();
                    post= manager.getPost(post_id);
                    System.out.println(post);
                    break;
                case 3:

                    System.out.println("Enter username firstname  last name and  email and pass city");
                    userName=dis.readLine();
                    firstName=dis.readLine();
                    lastName=dis.readLine();
                    email=dis.readLine();
                    pass=dis.readLine();
                    String city=dis.readLine();
                    manager.addUser(userName,firstName,lastName,email,pass,null,city);

                    break;
                case 4:
                    System.out.println("Enter user id");
                    user_id= dis.readInt();
                    manager.removeUser(user_id);

                    break;
                case 5:
//                    user.setUserId();
//                    user.updateUserName(con);
                    break;
                case 6:
//                    user.setUserId();
//                    user.getAllFriendsOfUser(con);
                    break;
                case 7:
                    // getALLCommentsForPost(con);
                    break;
                case 8:
                    System.out.println("Enter postName,postContent,userId");

                    postName=dis.readLine();
                    postContent=dis.readLine();
                    user_id=dis.readInt();
                    manager.addPost(postName,postContent,manager.getUser(user_id));

                    break;
                case 9:
//                    friendship.setUsers();
//                    friendship.addFriends(con);
                    break;
                case 10:

                    System.out.println("Enter post_id,userid,interaction type");
                    post_id=dis.readInt();
                    user_id=dis.readInt();
                    interaction_type=dis.readInt();
                    comment_data=null;
                    replyTo=-1;

                    if(interaction_type==2 || interaction_type==4){
                        System.out.println("Enter comment  data");
                        comment_data=dis.readLine();
                    }
                    if(interaction_type==3 || interaction_type==4){
                        System.out.println("Enter comment id to which you are interacting ");
                        replyTo= dis.readInt();
                    }
                    manager.addInteraction(manager.getPost(post_id), manager.getUser(user_id),interaction_type,comment_data,replyTo,1);

                    break;
                case 11:
//                    interaction.setInteractingUser();
//                    interaction.setPostForInteraction();
//                    interaction.numberOfLikesForPost(con);
                    break;
                case 12:
                    //getAllPostsOfFriends(con);
                    break;
            }
        }
    }
}
