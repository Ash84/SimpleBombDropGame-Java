import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        boolean startagain = true;
        // Saisie de la zone de lancement
        System.out.print("Enter your positions : first X : ");
        double xLaunchpad = sc.nextDouble();
        System.out.print("                        Then Y : ");
        double yLaunchpad = sc.nextDouble();
        while(startagain) {
            AtomicBomb bombeH = new AtomicBomb();            
            bombeH.defineLaunchPosition(xLaunchpad, yLaunchpad);
            bombeH.waiting(2000);
            System.out.println("\n" + bombeH);
            
            while(true) {
                
                // Definition de cible
                System.out.print("Enter desired target X : ");
                double xTarget = sc.nextDouble();
                System.out.print("Enter desired target Y : ");
                double yTarget = sc.nextDouble();
                bombeH.setTarget(xTarget, yTarget);
                System.out.println("\n" + bombeH);
                bombeH.waiting(2000);
                // Armement
                bombeH.setReady();
                System.out.println("BOMB SET UP\n\n");
                bombeH.waiting(2000);
                // Tentative de tir
                bombeH.shoot();
                if(!bombeH.hasExploded()) {
                    break;
                }
                else {
                    System.out.println("''Success !!''");
                    bombeH.waiting(2000);
                    System.out.println(bombeH);
                    bombeH.waiting(2000);
                    break;
                }
            }
            System.out.print("Want to try another shot (Y/N)? : ");
            while (true) {
                char yesorno = sc.next().charAt(0); 
                if (yesorno == 'Y')
                    break;
                else if (yesorno == 'N') {
                    System.out.println("Have a nice day !");
                    sc.close();
                    startagain = false;
                    break;
                }
                else {
                    System.out.println("Not a choice; try another shot (Y/N)?");
                    continue;
                }
            }
        }
    }
}