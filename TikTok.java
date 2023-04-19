package com.example.demo.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class TikTok {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);

        String userInput;
        BinarySearchTree newNode = new BinarySearchTree();
        Logic logic = new Logic();

        System.out.println("");
        System.out.println("");
        System.out.println("========================= Welcome to TikTok ========================");
        System.out.println("");
        System.out.println("-------  |  | / ------  -----  | /");
        System.out.println("   |     |  |/     |    |   |  |/");
        System.out.println("   |     |  |\\     |    |   |  |\\");
        System.out.println("   |     |  | \\    |    -----  | \\");
        System.out.println("");
        System.out.println("====================================================================");
        System.out.println("");
        System.out.println("In order to use the app you need to register an account");
        System.out.println("1. Register/Create an new account");
        System.out.println("");
        System.out.println("Enter option 1:");
        String choice = scan.nextLine();

        if (choice.equals("1")) {
            System.out.println("Enter username:");
            String userName = scan.nextLine();
            System.out.println("Enter first name:");
            String firstName = scan.nextLine();
            System.out.println("Enter last name:");
            String lastName = scan.nextLine();
            System.out.println("Enter profile description:");
            String profileDescription = scan.nextLine();
            System.out.println("Enter password:");
            String password = scan.nextLine();
            System.out.println("Confirm password:");
            String passwordConfirm = scan.nextLine();

            String response = logic.createAccount(newNode, userName, firstName, lastName, profileDescription, password, passwordConfirm);
            String[] split = response.split(" ");

            if (split[0].equals("User")) {
                do {
                    System.out.println("----------------------------------------------------------------");
                    System.out.println(" ");
                    System.out.println("Logged in as " + split[3]);
                    System.out.println(" ");
                    System.out.println("Choose an action from the menu:");
                    System.out.println("1. Find the profile description for a given account");
                    System.out.println("2. View profile for a given user");
                    System.out.println("3. List all accounts");
                    System.out.println("4. Create an account");
                    System.out.println("5. Delete an account");
                    System.out.println("6. Display all posts for a single account");
                    System.out.println("7. Add a new post for an account");
                    System.out.println("8. Load a file of actions from disk and process this");
                    System.out.println("9. Follow a user");
                    System.out.println("10. Change profile details");
                    System.out.println("11. Delete a post");
                    System.out.println("12. Logout/Quit");
                    System.out.println("Enter your choice: ");

                    userInput = scan.nextLine();

                    switch (userInput) {
                        case "1": {
                            System.out.println("Enter the account username: ");
                            String username = scan.nextLine();
                            System.out.println(logic.fetchProfileDescription(newNode, username));
                            break;
                        }
                        case "2": {
                            System.out.println("Enter the account username: ");
                            String username = scan.nextLine();
                            UserAccount userProfile = logic.searchUser(newNode, username);

                            if (userProfile != null) {
                                System.out.println("The user's profile details are:\n");
                                System.out.println("Username: " + userProfile.getUsername());
                                System.out.println("First name: " + userProfile.getFirstName());
                                System.out.println("Last name: " + userProfile.getLastName());
                                System.out.println("Followers: " + userProfile.getFollowers());
                                System.out.println("People following: " + userProfile.getFollowing());
                                System.out.println("Total likes: " + userProfile.getTotalLikes());
                                System.out.println("Profile description: " + userProfile.getProfileDescription());
                                System.out.println("Number of posts: " + userProfile.userPosts);
                            }
                            else {
                                System.out.println("Oops! User with that username does not exist");
                            }
                            break;
                        }
                        case "3": {
                            logic.listUsers(newNode);
                            break;
                        }
                        case "4": {
                            System.out.println("Enter username:");
                            String useName = scan.nextLine();
                            System.out.println("Enter first name:");
                            String firsName = scan.nextLine();
                            System.out.println("Enter last name:");
                            String lasName = scan.nextLine();
                            System.out.println("Enter profile description:");
                            String profilDescription = scan.nextLine();
                            System.out.println("Enter password:");
                            String pasword = scan.nextLine();
                            System.out.println("Confirm password:");
                            String paswordConfirm = scan.nextLine();
                            System.out.println(logic.createAccount(newNode, useName, firsName, lasName, profilDescription, pasword, paswordConfirm));
                            break;
                        }
                        case "5": {
                            logic.deleteAccount(newNode, split[6]);
                            break;
                        }
                        case "6": {
                            logic.displayAllPosts(newNode);
                            break;
                        }
                        case "7": {
                            logic.addNewPosts(newNode, split[6]);
                            break;
                        }
                        case "8": {
                            System.out.println("What is the file path?");
                            String path = scan.nextLine();
                            System.out.println(logic.loadFile(newNode, path));
                            break;
                        }
                        case "9": {
                            logic.followUser(newNode, split[6]);
                            break;
                        }
                        case "10": {
                            logic.changeProfileDetails(newNode, split[6]);
                            break;
                        }
                        case "11": {
                            logic.deletePost(newNode, split[6]);
                            break;
                        }
                    }
                } while (!userInput.equals("12"));
                System.out.println("Bye!");
            } else {
                System.out.println(response);
            }
        } else {
            System.out.println("Please enter a valid option!");
        }
    }
}
