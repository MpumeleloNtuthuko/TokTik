package com.example.demo.model;

import lombok.Data;

@Data
public class Node {

    private UserAccount userDetails;
    private Node leftChild;
    private Node rightChild;

    public Node (UserAccount userDetails) {
        this.userDetails = userDetails;
        leftChild = null;
        rightChild = null;
    }


    public UserAccount getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserAccount data) {
        this.userDetails = data;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node left) {
        this.leftChild = left;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node right) {
        this.rightChild = right;
    }
}
