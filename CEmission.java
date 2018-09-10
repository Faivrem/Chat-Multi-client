import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class CEmission implements Runnable {

	private boolean disconnected =false;
	private PrintWriter out;
	private String login = null, message = null;
	private Scanner sc = null;

	public CEmission(PrintWriter out,String login) {
		this.out = out;
		this.login=login;
	}

	public boolean isDisconnected(){
		return this.disconnected;
	}

	public void run() {

		while(!disconnected){
			sc = new Scanner(System.in);
			message = sc.nextLine();
			out.println(login);
			out.println(message);
			if (message!=null){
				out.flush();
				if (message.equals("/quit")){
					this.disconnected=true;
					System.out.println("Vous êtes déconnecté.");
					sc.close();
					System.exit(0);
				}
			}
		}
	}
}
