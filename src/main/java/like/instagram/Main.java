package like.instagram;

import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Please enter your username : ");
        String username = input.nextLine();
        System.out.println("Please enter your password : ");
        String password = input.nextLine();
        System.out.println("Please enter new target profile name : ");
        String newTargetProfileName = input.nextLine();


        App app = new App();
        app.login(username, password);
        app.navigateToNewTargetProfile(newTargetProfileName);
        app.clickFirstPost();
        app.likePost(app.getPostCount());
    }
}
