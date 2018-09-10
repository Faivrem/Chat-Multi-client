import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Set;


public class Chat_ClientServeur implements Runnable {

	public static Chat_ClientServeur instanceChat = null;
	private Hashtable<String,Socket> listSocket = null;
	private Socket socket = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private String login = "zero";
	private Thread t3, t4;
	
	
	private Chat_ClientServeur(Socket s, String log){
		this.listSocket= new Hashtable<String,Socket>();
		this.listSocket.put(log,s);
	}
	
	private void ajouterClient(Socket s, String log){
		this.listSocket.put(log,s);
	}
	public Hashtable<String,Socket> getList(){
		return this.listSocket;
	}
	
	public static Chat_ClientServeur instanceChatMethod (Socket s, String log) {
		if(instanceChat == null) {
			instanceChat = new Chat_ClientServeur(s,log);
		}
		else {
			instanceChat.ajouterClient(s,log);
		}
		return instanceChat;
	}
	

	public void run() {
		
		try {
		
		Set<String> keys=this.listSocket.keySet();
		for (String log : keys){
		Socket client=this.listSocket.get(log);
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream());
		
		SReception T= new SReception(in,log,this.listSocket);
		Thread t3 = new Thread(T);
		t3.start();
		}
		
		} catch (IOException e) {
			System.err.println(login +"s'est déconnecté ");
		}
}
}
