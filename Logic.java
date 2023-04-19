package com.example.demo.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Logic {

    public Logic(){}


    Scanner scan = new Scanner(System.in);

    public String fetchProfileDescription(BinarySearchTree newNode, String username) {
        String profDes = newNode.findUserProfileDescription(username);
        if (profDes == null) {
            return "Oops! User with that username does not exist.";
        }
        else {
            return "Their profile description is: " + profDes;
        }
    }

    public UserAccount searchUser(BinarySearchTree newNode, String username) {
        UserAccount userProfile = newNode.findUser(username);
        return userProfile;
    }

//    public boolean userAuthentication(BinarySearchTree node, String username, String password) {
//        UserAccount userProfile = node.findUser(username);
//        if (userProfile != null) {
//            return password.equals(userProfile.getPassword());
//        }
//        else {
//            return false;
//        }
//    }

    /**
     *When called, this function inserts a new node into the binary search tree
     * */
    public String createAccount(BinarySearchTree newNode, String userName, String firstName, String lastName, String profileDescription, String password, String passwordConfirm) {
        if (password.equals(passwordConfirm) && userName != "" && profileDescription != "") {
            if (password.length() >= 6) {
                UserAccount user1 = new UserAccount(userName, firstName, lastName, profileDescription, password);
                return newNode.insert(user1) + " " + userName;
            }
            else {
                return "Your password must be at least 6 characters long! Please try again.";
            }
        }
        else if (!password.equals(passwordConfirm)) {
            return "Oops! Passwords do not match. Please try again";
        }
        else {
            return "Username or profile description cannot be empty! Please try again.";
        }
    }

    public void deleteAccount(BinarySearchTree newNode, String currentUser) {
        System.out.println("Enter the account username:");
        String uname = scan.nextLine();
        System.out.println(newNode.delete(uname));
    }

    public void displayAllPosts(BinarySearchTree newNode) {
        System.out.println("Enter the account username:");
        String uname = scan.nextLine();
        ArrayList<Post> posts = newNode.findUserPosts(uname);

        if (posts != null) {
            if (posts.size() != 0) {
                for (Post post : posts) {
                    System.out.println("Post id: " + post.getPostId());
                    System.out.println("Title: " + post.getTitle());
                    System.out.println("Video file: " + post.getNameOfVideoClip());
                    System.out.println("Number of likes: " + post.getNumOfLikes());
                    System.out.println("Number of views: " + post.getNumOfViews());
                    System.out.println(" ");
                }
            }
            else {
                System.out.println("This user does not have any posts");
            }
        }
        else {
            System.out.println("Oops! User with that username does not exist");
        }
    }

    public void addNewPosts(BinarySearchTree newNode, String currentUser) throws FileNotFoundException {

        System.out.println("How would you like to add a post?:");
        System.out.println("1. Manually add post details");
        System.out.println("2. Load a file");

        String ChoiceOfAdding = scan.nextLine();

        switch (ChoiceOfAdding) {
            case "1": {
                String uname = currentUser;
                System.out.println("Enter video title: ");
                String title = scan.nextLine();
                System.out.println("Enter video: ");
                String video = scan.nextLine();
                System.out.println("Enter video likes: ");
                String likes = scan.nextLine();
                System.out.println("Enter video views: ");
                String views = scan.nextLine();

                Post post = new Post(title, video, likes, views);
                System.out.println(newNode.addPost(uname, post));
                break;
            }
            case "2": {
                System.out.println("Enter the file path: ");
                String path = scan.nextLine();

                File dataset = new File(path);
                Scanner textScanner = new Scanner(dataset);

                while(textScanner.hasNextLine()) {
                    String action = textScanner.nextLine();
                    String[] splitData = action.split(" ");
                    String command = splitData[0];

                    if (command.equals("Add")) {

                        String username = splitData[1];
                        String title = "";

                        String videoFile = splitData[2];
                        String likes = splitData[3];
                        for (int i=4; i<9; i++) {
                            String rawTitle = title + " " + splitData[i];
                            title = rawTitle.trim().substring(0,1).toUpperCase() + rawTitle.trim().substring(1);
                        }
                        Post post = new Post(title, videoFile, likes);
                        System.out.println(newNode.addPost(username, post));
                    }
                }
                break;
            }
        }
    }

    public String loadFile(BinarySearchTree newNode, String path) throws FileNotFoundException {
        File dataset = new File(path);
        Scanner textScanner = new Scanner(dataset);

        while(textScanner.hasNextLine()) {
            String action = textScanner.nextLine();
            String[] splitData = action.split(" ");
            String command = splitData[0];

            String username = splitData[1];
            String title = "";
            if (command.equals("Create")) {
                for (int i=2; i<5; i++) {
                   String rawTitle = title + " " + splitData[i];
                   title = rawTitle.trim().substring(0,1).toUpperCase() + rawTitle.trim().substring(1);

                }
                UserAccount newUser = new UserAccount(username, title);
                newNode.insert(newUser);
            }
        }
        return "Users successfully added.";
    }

    public void listUsers(BinarySearchTree newNode) {

        if (newNode.getRoot() != null) {
            newNode.preOrder();
        }
        else {
            System.out.println("There are no users.");
        }
    }

    public void followUser(BinarySearchTree node, String currentUser) {
        System.out.println("Enter the account username:");
        String username = scan.nextLine();
        System.out.println(node.follow(username, currentUser));
    }

    public void changeProfileDetails(BinarySearchTree node, String currentUser) {
        System.out.println("1. First name and last name");
        System.out.println("2. Profile description");
        System.out.println(" ");
        System.out.println("Which field(s) do you want to change?");
        String choice = scan.nextLine();

        UserAccount current = node.findUser(currentUser);
        if (current != null) {
            if (choice.equals("1")) {
                System.out.println("New first name:");
                String name = scan.nextLine();
                System.out.println("New last name:");
                String lname = scan.nextLine();
                current.setFirstName(name);
                current.setLastName(lname);
                System.out.println("First and last name successfully changed!");
            }
            else if (choice.equals("2")) {
                System.out.println("New profile description");
                String profileDescription = scan.nextLine();
                current.setProfileDescription(profileDescription);
                System.out.println("Profile description successfully changed!");
            }
        }
        else {
            System.out.println("Sorry, your account has been deleted!");
        }
    }

    public void deletePost(BinarySearchTree node, String currentUser) {
        System.out.println("Enter the id of the post you want to delete:");
        String postId = scan.nextLine();
        UserAccount current = node.findUser(currentUser);

        if (current.posts.size() != 0) {
            String outcome = "";
            boolean match = false;
            for (int i=0; i<current.posts.size(); i++) {
                if (Integer.toString(current.posts.get(i).getPostId()).equals(postId)) {
                    current.posts.remove(i);
                    current.userPosts--;
                    match = true;
                    outcome = "Post deleted successfully";
                    break;
                }
            }

            if (!match) {
                System.out.println("Sorry, you can only make changes to YOUR profile!");
            }
            else {
                System.out.println(outcome);
            }
        }
        else {
            System.out.println("You do not have any posts.");
        }
    }
}
