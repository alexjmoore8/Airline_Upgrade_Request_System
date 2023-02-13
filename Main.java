package NUA;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        UpgradeRequest request = new UpgradeRequest();
        
        request.userMenu(scnr);
        
        //close scanner
		scnr.close();
		
    }//end main
}//end class
