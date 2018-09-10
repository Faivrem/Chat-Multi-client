import java.net.*;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.io.*;

public class Authentification implements Runnable {

	private Socket socket;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private String login = "zero";
	public boolean authentifier = false;
	public Thread t2=null;
	public Chat_ClientServeur chat=null;

	public Authentification(Socket s){
		socket = s;
	}
	public void run() {

		try {

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());

			while(!authentifier){

				out.println("Entrez votre login :");
				out.flush();
				login = in.readLine();



				out.println("connecte");
				System.out.println(login +" vient de se connecter ");
				out.flush();
				authentifier = true;
				

				this.chat = Chat_ClientServeur.instanceChatMethod(socket, login);
				t2 = new Thread(this.chat);
				t2.start();
				Hashtable<String,Socket> list=this.chat.getList();
				Set<String> keys=list.keySet();
				for (String log : keys){
					if (!log.equals(login)){
						//System.out.println("Liste Client :\n"+log);
						out = new PrintWriter(list.get(log).getOutputStream());
						Thread t4 = new Thread(new SEmission(out,login," s'est connecté!"));
						t4.start();
					}
				}
			}
			
		}catch (IOException e) {

			System.err.println(login+" ne répond pas !");
		}
	}


}