import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[]args){
		new Server();
	}
	
	public Server(){
		
		ServerSocket ss = null;
		
		try{
			ss = new ServerSocket(16788);
			Socket cs = null;
			while(true){
				cs = ss.accept();
				ThreadServer ts = new ThreadServer(cs);
				ts.start();
			}
		}
		catch(BindException be){
			System.out.println("Server already running");
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		
	}
	
	class ThreadServer extends Thread{
		
		Socket cs; 
		
		public ThreadServer(Socket cs){
			this.cs = cs;
		}
		
		public void run(){
			BufferedReader br;
			PrintWriter pw;
			String msg;
			try{
				br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
				pw = new PrintWriter(new OutputStreamWriter(cs.getOutputStream()));
				while(true){
					msg = br.readLine();
					pw.println("NAME: " + msg);
					pw.flush();
				}
			}
			catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
	}
}
