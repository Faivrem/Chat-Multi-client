import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Chat_Client implements Runnable {

	private Socket socket;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private Scanner sc;
	private Thread t3, t4;
	private String login;
	private boolean online=true;
	
	public Chat_Client(Socket s,String login){
		socket = s;
		this.login=login;
	}

	public void run() {
		try {
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			//sc = new Scanner(System.in);
			CEmission em=new CEmission(out,login);
			Thread t4 = new Thread(em);
			if(!t4.isInterrupted()){
				t4.start();
				Thread t3 = new Thread(new CReception(in));
				t3.start();
			}
			while (online){
				if(em.isDisconnected()){
					this.online=false;
					t3.interrupt();
					t4.interrupt();
					socket.close();
				}
			}


		} catch (IOException e) {
			System.err.println("Le serveur distant s'est déconnecté !");
		}
	}

}