//채팅창 만들어 보기2
//이너 클래스 만들어서, 바깥에 프라이빗한것도 쓸 수 있음
//채팅창에 글 올리려면, 밖에 있는 것에 접근 가능해야함
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

	// 2) 어레이 리스트 만들때 제너릭하게 생성
	// 제네릭은 자료형을 정하지 않고
	// 여러가지 타입을 한번에 사용하는 방식이다
	ArrayList<MServer> list = new ArrayList<MServer>();

	Map<String, BufferedWriter> map;
	
	ServerSocket ss; // 서버소켓 만들기
	JTextArea jta; // 채팅 올라가는 큰 화면
	JButton jbtnExit; // 종료버튼
	JScrollPane jsp; // 스크롤 패널
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
		
		
		jta = new JTextArea(); // 큰 패널, 여러줄 쓰기
		// 스크롤바 만들기, 큰패널에 붙이기
		jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		jbtnExit = new JButton("종료"); // 종료창
		Font f = new Font("굴림체", Font.PLAIN, 20);

		// 이벤트 처리
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
	// 받은걸 모두에게 전달 broadcast
	private void vChatStart() {
		try {
			// 서버소켓 생성
			ss = new ServerSocket(5000);

			while (true) {

				// 사용자 연결 기다림
				Socket client = ss.accept();
				
				MServer ms = new MServer(client);

			
				list.add(ms);
				ms.start(); 

			}
		} catch (IOException e) {
			list.remove(this);
		}

	}

	// 메인메소드
	public static void main(String[] args) {
		ChatServer cs = new ChatServer(userId);
	}

	// 종료하면 나가지도록
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
		// 참조값 전달
		MServer(Socket client) {
			this.client = client;
			// ip
			ip = client.getInetAddress().getHostAddress();
			try {

				br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())));
				
				//아까 소켓에서 연결하자 마자 아이디를 받았기 때문에
				//가능한것이다
				userId = br.readLine();
				System.out.println(userId);
				
			} catch (IOException e) {
				
				list.remove(this);

			}

		}

		@Override
		// 멀티 스레드

		public void run() {
	
			while (true) {
				String msg = null;
		
				try {
					System.out.println();
					msg = br.readLine();
					
					jta.append("[" + userId +"]"+ msg + "\n");

					JScrollBar sb = jsp.getVerticalScrollBar();
				
					int position = sb.getMaximum();
					// 이동
					sb.setValue(position);

					// 모든 접속자 에게 이 메세지를 전달
					// 아이피 누가 어떤 메세지 썼다
					
					broadcast("[" + userId + "] : " + msg);

				} catch (IOException e) {
					// 읽어내고 , 예외가 터지면 그걸 제외해라(누가 채팅방 나가거나, 들어올때 에러)
					list.remove(this);

				}
			}
		}

		// 5)
		private void broadcast(String msg) {
			// ArrayList 안에있는 객체를 하나씩 꺼내서 전송
			// 프린트 라이터 - 객체로 프린터를 준다.
			// 접속한 모든 사용자에게 msg 를 하나씩 전달해 준다

			for (MServer x : list) {
				x.pw.println(msg);
				x.pw.flush();
			}

		}
	}

}
