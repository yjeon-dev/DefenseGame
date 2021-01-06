//ä��â ����� ����2
//�̳� Ŭ���� ����, �ٱ��� �����̺��Ѱ͵� �� �� ����
//ä��â�� �� �ø�����, �ۿ� �ִ� �Ϳ� ���� �����ؾ���
package project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import project.NewLogIn;
import java.util.HashMap;

import java.util.Map;




public class ChatServer extends JFrame implements ActionListener {

	// 2) ��� ����Ʈ ���鶧 ���ʸ��ϰ� ����
	// ���׸��� �ڷ����� ������ �ʰ�
	// �������� Ÿ���� �ѹ��� ����ϴ� ����̴�
	ArrayList<MServer> list = new ArrayList<MServer>();

	Map<String, BufferedWriter> map;
	
	ServerSocket ss; // �������� �����
	JTextArea jta; // ä�� �ö󰡴� ū ȭ��
	JButton jbtnExit; // �����ư
	JScrollPane jsp; // ��ũ�� �г�
	PrintWriter pw; // printWriter
	BufferedReader br; // BufferedReader

	static String userId=null;
	
//	public void getId() {
//		
//		NewLogin getId = new NewLogin(uid);
//		}
	User_DataDAO udd = new User_DataDAO();
	User_DataVO udv = new User_DataVO();
	
	
	
	public ChatServer(String uid) {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		map = new HashMap<>();
		
		
		jta = new JTextArea(); // ū �г�, ������ ����
		// ��ũ�ѹ� �����, ū�гο� ���̱�
		jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		jbtnExit = new JButton("����"); // ����â
		Font f = new Font("����ü", Font.PLAIN, 20);

		// �̺�Ʈ ó��
		jbtnExit.addActionListener(this);

		userId = uid;
		System.out.println(uid);
		
		jta.setFont(f);
		add(jsp, "Center");
		add(jbtnExit, "South");
		setBounds(100, 100, 600, 1000);
		setVisible(true);

		// 1)
		vChatStart();

	}

	// 2)
	// ������ ��ο��� ���� broadcast
	private void vChatStart() {
		try {
			// �������� ����
			ss = new ServerSocket(5000);

			while (true) {

				// ����� ���� ��ٸ�
				Socket client = ss.accept();
				
				MServer ms = new MServer(client);

			
				list.add(ms);
				ms.start(); 

			}
		} catch (IOException e) {
			list.remove(this);
		}

	}

	// ���θ޼ҵ�
	public static void main(String[] args) {
		ChatServer cs = new ChatServer(userId);
	}

	// �����ϸ� ����������
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

	// 3)
	

	class MServer extends Thread {
		Socket client;
		PrintWriter pw;
		BufferedReader br;
		String ip;
		String userId; 
		// ������ ����
		MServer(Socket client) {
			this.client = client;
			// ip
			ip = client.getInetAddress().getHostAddress();
			try {

				br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())));
				
				//�Ʊ� ���Ͽ��� �������� ���� ���̵� �޾ұ� ������
				//�����Ѱ��̴�
				userId = br.readLine();
				System.out.println(userId);
				
			} catch (IOException e) {
				
				list.remove(this);

			}

		}

		@Override
		// ��Ƽ ������

		public void run() {
	
			while (true) {
				String msg = null;
		
				try {
					System.out.println();
					msg = br.readLine();
					
					jta.append("[" + userId +"]"+ msg + "\n");

					JScrollBar sb = jsp.getVerticalScrollBar();
				
					int position = sb.getMaximum();
					// �̵�
					sb.setValue(position);

					// ��� ������ ���� �� �޼����� ����
					// ������ ���� � �޼��� ���
					
					broadcast("[" + userId + "] : " + msg);

				} catch (IOException e) {
					// �о�� , ���ܰ� ������ �װ� �����ض�(���� ä�ù� �����ų�, ���ö� ����)
					list.remove(this);

				}
			}
		}

		// 5)
		private void broadcast(String msg) {
			// ArrayList �ȿ��ִ� ��ü�� �ϳ��� ������ ����
			// ����Ʈ ������ - ��ü�� �����͸� �ش�.
			// ������ ��� ����ڿ��� msg �� �ϳ��� ������ �ش�

			for (MServer x : list) {
				x.pw.println(msg);
				x.pw.flush();
			}

		}
	}

}
