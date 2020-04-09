import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class AtomicBomb {

    private double falloutDistance = 300.0;
    private boolean armed = false;
    private double[] launchPosition = new double[2];
    private double[] targetPosition = new double[2];
    private boolean detonated = false;
    private double[] fallout = new double[3];

    public AtomicBomb() {
        falloutDistance = 300.0;
        armed = false;
        launchPosition = new double[2];
        targetPosition = new double[2];
        detonated = false;
        fallout = new double[3];
    } 

    private boolean safetyDistance(double[] launchPosition, double[] targetPosition) {
        double distance = Math.sqrt(Math.pow(targetPosition[0] - launchPosition[0], 2) + Math.pow(targetPosition[1] - launchPosition[1], 2));
        if (distance < falloutDistance)
            return false;
        return true;
    }

    public void waiting(int x) {
        try {
            Thread.sleep(x); // Temps d'attente
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void setTarget(double x, double y) {
        targetPosition[0] = x;
        targetPosition[1] = y;
        System.out.println("\nYou are successfully pointing the Bomb to [" + x + ", "+ y + "]");
        waiting(2000);;
    }

    public void defineLaunchPosition(double x, double y) {
        launchPosition[0] = x;
        launchPosition[1] = y;
        System.out.println("\nYour secret base setup is at coordinates [" + x + ", "+ y + "]");
    }

    public void shoot() {
        Random rand = new Random();
        if (!armed) {
            System.out.println("You have to set the Bomb ready");
            return;
        }
        if (detonated) {
            System.out.println("You already killed a ton of people with that toy, only ashes are left here");
            return;
        }
        if (!safetyDistance(launchPosition, targetPosition)) {
            System.out.println("You just tried to kill yourself and your crew, you are in the fallout zone !");
            this.setDown();
            targetPosition[0] = 0;
            targetPosition[1] = 0;
            waiting(2000);
            waiting(2000);
            System.out.println(this.toString());
            return;         
        }
        System.out.print("Bomb's flying away to destroy your target.");
        waiting(200);
        double distance = Math.sqrt(Math.pow(targetPosition[0] - launchPosition[0], 2) + Math.pow(targetPosition[1] - launchPosition[1], 2));
        for (int i = 0; i < distance/300; i++) {
            System.out.print(".");
            waiting(200);
        }
        System.out.print("*HIT*\n");
        waiting(3000);
        fallout[0] = targetPosition[0];
        fallout[1] = targetPosition[1];
        fallout[2] = falloutDistance - (int)(rand.nextDouble()*140);
        detonated = true;
        armed = false;
    }

    public void setReady() {
        armed = true;
        return;
    }
    
    public boolean hasExploded() {
        return detonated;
    }

    public void setDown() {
        armed = false;
        return;
    }

    public String toString() {
        String str = "\nStatus = ";
        str = str + "You are here : [" + launchPosition[0] + ", " + launchPosition[1] + "]\n"; 
        if (armed)
            str = str + "Bomb is ready to fire";
        else
            str = str + "Bomb is in security mode";
        str = str + "\nBomb has a max effect array of " + falloutDistance + " km (Safety distance)";
        if (detonated)
            str = str + "\n \n**** Bomb has landed on [" + fallout[0] + ", " + fallout[1] + "], killing all human being in a range of " + fallout[2] + " kilometers. ****\n";
        else
            str = str + "\nBomb is actually pointing [" + targetPosition[0] + ", " + targetPosition[1] + "]\n"; 
        return str;
    }
}