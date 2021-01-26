package TicTacToe;

import OneTwoThree.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Runnable_Song implements Runnable {

    private Thread t;
    private String threadName;
    Player player;
    
    public Runnable_Song(String threadName) {
        this.threadName =threadName;
    }

    public void playSound(String namepath) {
        String s = "C:/Users/HiamKaito/Desktop/Java/java_swing/JavaSwing/src/OneTwoThree/" + namepath;
        try {
            FileInputStream fileInputStream = new FileInputStream(s);
            player = new Player(fileInputStream);
            System.out.println("Song is playing...");
            System.out.println(s);
            player.play();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JavaLayerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            if (threadName.equalsIgnoreCase("Music")) {
                String[] arrStr = new String[2];
                int i = 0;
                
                arrStr[0] = "Every Jojo’s Bizarre Adventure Opening But With 8-Bit Music.mp3";
                arrStr[1] = "Golden Wind.mp3";
                
                do {
                    playSound(arrStr[i]);
//                    Thread.sleep(0);
                    i++;
                    if (i == 2) i =0;
                } while ( player.isComplete() );
            }
            else {
                Thread.sleep(0);
                GUI gui = new GUI();
                gui.setVisible(true);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
    
    public void stop() {
        System.out.println("Stopping music");
        player.close();
    }

}