package TicTacToe;

/**
 * Information data storage
 *
 * @author HiamKaito
 */
public class Memory {

    static String Player_Name = "Hiam";
    static java.awt.Color colorThemes;
    static String iconName = "boy 1";

//    system 
    static Runnable_Song bgSong;
    static boolean playSong = true;
    /**
     * Version 1.0 only bot, no sound
     * Version 1.1 bot and sound
     * Version 1.2 bot normal
     * Version 1.3 bot hard
     * Version 1.4 connect LAN
     * Version 1.5 fix bug LAN
     * Version 1.6 add document and comment
     */
    static String version = "Version 1.6";
    
//    ======================================================================
//    ======================================================================

//    Client and System
    static Client client;
    static Server server;
    
//    About Client to CLien
    static String messenger = " ";
    /**
     * true if there is a messenger come from the other client
     * <br>It mean in your turn is false
     */
    static boolean playerMessenger = true;
    
    static String statusMessenger = " ";
    static String playerConnectName = "";
    static boolean playerConnect = false;

//    About player in Connect
    /**
     * Host go fist: true
     */
    static boolean playerTurn;
    /**
     * Host = 1, Client = 2
     */
    static int iValue = -1;
}
