import java.io.BufferedReader;
import java.io.IOException;


public class CReception implements Runnable {

	private BufferedReader in;
	private String message = null;
	private String login =null;

	public CReception(BufferedReader in){

		this.in = in;
	}

	public void run() {

		while(true){
			try {
				if (in.ready()){
					message = in.readLine();
					System.out.println(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
