import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Set;


public class SReception implements Runnable {

	private BufferedReader in;
	private PrintWriter out = null;
	private String message = null, login = null;
	private Hashtable<String,Socket> listClient=null;

	public SReception(BufferedReader in, String login, Hashtable<String,Socket> list){
		this.listClient=list;
		this.in = in;
		this.login = login;
	}
	public String getMsg(){
		return this.message;
	}
	public void run() {

		while(true){
			try {
				if (in.ready()){
					login= in.readLine();
					message = in.readLine();
					if (message.equals("/quit")){
						Socket deco=this.listClient.get(login);
						deco.close();
						this.listClient.remove(login);
						message="s'est deconnecté";
					}
					//else {
						System.out.println(login+" : "+message);
						Set<String> keys=this.listClient.keySet();
						for (String log : keys){
							if (!log.equals(login)){
								//System.out.println("Liste Client :\n"+log);
								out = new PrintWriter(this.listClient.get(log).getOutputStream());
								Thread t4 = new Thread(new SEmission(out,login,message));
								t4.start();
							}
						}
				//	}
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
			catch (NullPointerException e ){
				System.out.print(login+" s'est deconnecté subitement.");
			}
		}
	}

}