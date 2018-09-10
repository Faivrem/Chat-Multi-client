import java.io.*;
import java.net.*;

public class Serveur {
 public static ServerSocket s = null;
 public static Thread t;

 
    public static void main(String[] args) {
        
        try {
            s = new ServerSocket(2009);
            System.out.println("Le serveur est à l'écoute du port "+s.getLocalPort());
        
            t = new Thread(new AccepterClients(s));
            t.start();
            
        } catch (IOException e) {
            System.err.println("Le port est déjà utilisé !");
        }
    
    }

    
    }
