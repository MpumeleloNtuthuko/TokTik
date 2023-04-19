package com.example.demo.model;

import java.util.ArrayList;
import java.util.Objects;

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        root = null;
    }

//  ================================ HANDLE USER INSERTION ============================
    public String insert(UserAccount details) {
        root = insertAssistant(root, details);
        return "User with username '" + details.getUsername() + "' added successfully!";
    }

    public Node getRoot() {
        return root;
    }

    private Node insertAssistant(Node current, UserAccount details) {
        if (current == null) {
            return new Node(details);
        }

//      Check if the new username is less than i.e. not the same as the username of the current node.
        if (details.getUsername().compareTo(current.getUserDetails().getUsername()) < 0) {
            if (current.getLeftChild() == null) {
                current.setLeftChild(insertAssistant(current.getLeftChild(), details));
            }
            else {
                insertAssistant(current.getLeftChild(), details);
            }
        }
//      Check if the new username is greater than i.e. not the same as the username of the current node.
        else if (details.getUsername().compareTo(current.getUserDetails().getUsername()) > 0) {
            if (current.getRightChild() == null) {
                current.setRightChild(insertAssistant(current.getRightChild(), details));
            }
            else {
                insertAssistant(current.getRightChild(), details);
            }
        }
//        else {
//            // If the user already exists, update their password
//            current.getUserDetails().setPassword(data.getPassword());
//        }

        return current;
    }
//  ================================ HANDLE USER INSERTION END ============================


//  ================================ HANDLE USER SEARCH/FIND DATA ============================
    public UserAccount findUser(String username) {
        return findUserAssistant(root, username);
    }

    private UserAccount findUserAssistant(Node current, String username) {
        if (current == null) {
            return null;
        }

        if (username.equals(current.getUserDetails().getUsername())) {
            return current.getUserDetails();
        } else if (username.compareTo(current.getUserDetails().getUsername()) < 0) {
            return findUserAssistant(current.getLeftChild(), username);
        } else {
            return findUserAssistant(current.getRightChild(), username);
        }
    }
//  ================================ HANDLE USER SEARCH/FIND DATA END ============================


//  ================================ HANDLE DATA DELETION ============================
    public String delete(String username) {
        String outcome = "";
        if (root == null) {
            outcome = "There are no users in the tree";
        }
        else {
            root = deleteAssistant(root, username);
            outcome = "User deleted successfully";
        }
        return outcome;
    }

    private Node deleteAssistant(Node current, String username) {
//        if (current == null) {
//            return null;
//        }

        if (username.equals(current.getUserDetails().getUsername())) {
            // if the node has no children
            if (current.getLeftChild() == null && current.getRightChild() == null) {
                return null;
            }
            // if the node has one child
            if (current.getLeftChild() == null) {
                return current.getRightChild();
            }
            if (current.getRightChild() == null) {
                return current.getLeftChild();
            }
            // if the node has two children
            Node smallestRight = findSmallestNode(current.getRightChild());
            current.setUserDetails(smallestRight.getUserDetails());
            current.setRightChild(deleteAssistant(current.getRightChild(), smallestRight.getUserDetails().getUsername()));
            return current;
        } else if (username.compareTo(current.getUserDetails().getUsername()) < 0) {
            current.setLeftChild(deleteAssistant(current.getLeftChild(), username));
        } else {
            current.setRightChild(deleteAssistant(current.getRightChild(), username));
        }
        return current;
    }

    private Node findSmallestNode(Node node) {
        Node currentNode = node;
        while (currentNode.getLeftChild() != null) {
            currentNode = currentNode.getLeftChild();
        }
        return currentNode;
    }
//  ================================ HANDLE DATA DELETION END ============================


//  ================================ FIND PROFILE DESCRIPTION ============================
    public String findUserProfileDescription(String username) {
        return findUserAssistantFunction(root, username);
    }

    private String findUserAssistantFunction(Node current, String username) {
        if (current == null) {
            return null;
        }

        if (username.equals(current.getUserDetails().getUsername())) {
            return current.getUserDetails().getProfileDescription();
        } else if (username.compareTo(current.getUserDetails().getUsername()) < 0) {
            return findUserAssistantFunction(current.getLeftChild(), username);
        } else {
            return findUserAssistantFunction(current.getRightChild(), username);
        }
    }
//  ================================ FIND PROFILE DESCRIPTION END ============================


//  ================================ FIND USER POSTS ============================
    public ArrayList<Post> findUserPosts(String username) {
        return findPostAssistantFunction(root, username);
    }

    private ArrayList<Post> findPostAssistantFunction(Node current, String username) {
        if (current == null) {
            return null;
        }

        if (username.equals(current.getUserDetails().getUsername())) {
            return current.getUserDetails().getPost();
        } else if (username.compareTo(current.getUserDetails().getUsername()) < 0) {
            return findPostAssistantFunction(current.getLeftChild(), username);
        } else {
            return findPostAssistantFunction(current.getRightChild(), username);
        }
    }
//  ================================ FIND USER POSTS END ============================


//  ================================ ADD POST ============================
    public String addPost(String username, Post post) {
        return findPostAssistantFunction(root, username, post);
    }

    private String findPostAssistantFunction(Node current, String username, Post post) {
        String response = "";
        if (current == null) {
            response = "Oops! Account with that username does not exist.";
        }

        if (username.equals(current.getUserDetails().getUsername())) {
            current.getUserDetails().setPost(post);
            response = "Post added successfully.";
        } else if (username.compareTo(current.getUserDetails().getUsername()) < 0) {
            findPostAssistantFunction(current.getLeftChild(), username, post);
        } else {
            findPostAssistantFunction(current.getRightChild(), username, post);
        }
        return response;
    }
//  ================================ ADD POST END ============================

    public void preOrder()
    {
        preOrderHelper(root);
    }
    public void preOrderHelper(Node node)
    {
        if (node != null)
        {
            System.out.println(node.getUserDetails().getUsername());
            preOrderHelper(node.getLeftChild());
            preOrderHelper(node.getRightChild());
        }
    }

    public String follow(String uname, String currentUser) {
        UserAccount toFollow = findUserAssistant(root, uname);
        UserAccount current = findUserAssistant(root, currentUser);

        String outcome = "";
        if (toFollow != null) {
            toFollow.setFollowers(toFollow.getFollowers()+1);
            current.setFollowing(current.getFollowing()+1);
            outcome = "You now follow " + uname;
        }
        else {
            outcome = "Oops! User with that username does not exist";
        }
        return outcome;
    }
}
