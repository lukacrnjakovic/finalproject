import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame{
	
	private final int PORT = 16788;
	private JTextArea jta;
	private JTextField jtf;
	private PrintWriter pw;
	private BufferedReader br;
	private Socket s;
	
	public ChatClient(String address){
		
		try {
			
			s = new Socket("localhost", PORT);
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			br = new BufferedReader(new InputStreamReader(in));
			pw = new PrintWriter(new OutputStreamWriter(out));
		
			while(true){
				jta.append(br.readLine());
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jta = new JTextArea(10,10);
		jta.setEditable(false);
		jtf = new JTextField(20);
		JPanel jp2 = new JPanel();
		jp2.setLayout(new FlowLayout());
		jp2.add(jtf);
		JButton jbSend = new JButton("Send");
		jbSend.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(ae.getSource()==jbSend){
					System.out.println("pusi jaje");
				}
			}
		});
		jp2.add(jbSend);
		jp.add(jta, BorderLayout.CENTER);
		jp.add(jp2, BorderLayout.SOUTH);
		add(jp);
		setSize(400,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[]args){
		if (args.length!=1){
			System.out.println("Need address of server");
		}
		else{
			new ChatClient(args[0]);
		}
	}
}
