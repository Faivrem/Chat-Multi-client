import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class SEmission implements Runnable {

	private PrintWriter out;
	private String message = null;
	private String loginE=null;
	private Scanner sc=null;

	public SEmission(PrintWriter out,String login,String msg) {
		this.out = out;
		this.loginE=login;
		this.message=msg;
	}

	
	public void run() {
		  
		  //while(true){
			  	out.println(loginE+" : "+message);
				out.flush();
				Thread.currentThread().interrupt();
			 // }
	}
}
