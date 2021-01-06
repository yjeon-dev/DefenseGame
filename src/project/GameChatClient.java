package project;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameChatClient extends JFrame implements ActionListener, KeyListener, Runnable {
   JPanel jp1, jp2;
   CardLayout cl;
   JLabel jlbIp, jlbPort;
   JButton jbtnLogin, jbtnExit;
   JButton jbtnSend;
   JTextField jtfIp, jtfPort;
   JScrollPane jsp;
   JPanel jpSouth;
   JTextField jtf;
   JTextArea jta;
   String ip;
   int port;
   Socket s;
   BufferedReader br;
   PrintWriter pw;
   String userId;
   String uid;
   User_DataDAO udd = new User_DataDAO();
   User_DataVO udv;
   
   public GameChatClient(String uid) {
	   this.uid = uid;
      // 컴포넌트 초기화
      // jp1 = new JPanel();
      jp2 = new JPanel();
      jpSouth = new JPanel();
      jtf = new JTextField(28);
      jta = new JTextArea();
      jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      jbtnSend = new JButton("전송");
      jpSouth.add(jtf);
      jpSouth.add(jbtnSend);

      jp2.setLayout(new BorderLayout());
      jp2.add(jsp, "Center");
      jp2.add(jpSouth, "South");

      jbtnSend.addActionListener(this);
      jbtnSend.addKeyListener(this);
      jtf.addKeyListener(this);
      udv = udd.selectnick(uid);
      System.out.println(udv.getNick());
      userId = udv.getNick();
      System.out.println(userId);
      setBounds(1500, 0, 400, 900);

      add(jp2);

      setVisible(true);
      VChatting();
   }

   

   @Override
   public void actionPerformed(ActionEvent e) {
      Object obj = e.getSource();

      if (obj == jbtnSend) {
         // 자주 사용 되는 코드를 Refactor를 하게되면 코드를 더 간결하게 만들수 있다.
         send();
      }

   }

   private void send() {
      String msg = jtf.getText();
      
      pw.println(msg);
      pw.flush();
      // 글자를 지우고
      jtf.setText("");
      // 포커스를 준다.
      jtf.requestFocus();
   }

   private void VChatting() {
      // 현재 클라이언트 프로그래을 여러개 띄우수 있도록 한다.
      Thread th = new Thread(this);
      th.start();

   }

   @Override
   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void keyPressed(KeyEvent e) {
      int code = e.getKeyCode();
      if (code == 10) {
         // 자주 사용 되는 코드를 Refactor를 하게되면 코드를 더 간결하게 만들수 있다.
         send();
      }
   }

   @Override
   public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void run() {
      // 채팅
      // 1. 소캣객체 생성
      try {
         s = new Socket("192.168.0.62", 5000);
         // 2. 수신부
         // 2. 수신부
         br = new BufferedReader(new InputStreamReader(s.getInputStream()));

         // 3. 발신부
         pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
         //생성하자마자 아이디 보냈음
         pw.println(userId);
         pw.flush();
         
         // 4. 반복해서 읽기
         String msg = null;
         while (true) {
            msg = br.readLine();

            // 5. 화면에 출력
            jta.append(msg + "\n");

            // 오토 스크롤 기능
            JScrollBar sb = jsp.getVerticalScrollBar();

            // 스크롤의 가장 밑에 위치를 가져온다.
            int position = sb.getMaximum();

            // 이동
            sb.setValue(position);
         }

      } catch (UnknownHostException e) {
//         e.printStackTrace();
         System.out.println("11");
      } catch (IOException e) {
         // e.printStackTrace();
         JOptionPane.showConfirmDialog(this, "채팅 서버가 닫혀있습니다.", "채팅 서버 오류", JOptionPane.PLAIN_MESSAGE);
         this.setVisible(false);
      }
   }

}