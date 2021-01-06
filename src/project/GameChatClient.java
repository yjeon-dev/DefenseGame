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
      // ������Ʈ �ʱ�ȭ
      // jp1 = new JPanel();
      jp2 = new JPanel();
      jpSouth = new JPanel();
      jtf = new JTextField(28);
      jta = new JTextArea();
      jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      jbtnSend = new JButton("����");
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
         // ���� ��� �Ǵ� �ڵ带 Refactor�� �ϰԵǸ� �ڵ带 �� �����ϰ� ����� �ִ�.
         send();
      }

   }

   private void send() {
      String msg = jtf.getText();
      
      pw.println(msg);
      pw.flush();
      // ���ڸ� �����
      jtf.setText("");
      // ��Ŀ���� �ش�.
      jtf.requestFocus();
   }

   private void VChatting() {
      // ���� Ŭ���̾�Ʈ ���α׷��� ������ ���� �ֵ��� �Ѵ�.
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
         // ���� ��� �Ǵ� �ڵ带 Refactor�� �ϰԵǸ� �ڵ带 �� �����ϰ� ����� �ִ�.
         send();
      }
   }

   @Override
   public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void run() {
      // ä��
      // 1. ��Ĺ��ü ����
      try {
         s = new Socket("192.168.0.62", 5000);
         // 2. ���ź�
         // 2. ���ź�
         br = new BufferedReader(new InputStreamReader(s.getInputStream()));

         // 3. �߽ź�
         pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
         //�������ڸ��� ���̵� ������
         pw.println(userId);
         pw.flush();
         
         // 4. �ݺ��ؼ� �б�
         String msg = null;
         while (true) {
            msg = br.readLine();

            // 5. ȭ�鿡 ���
            jta.append(msg + "\n");

            // ���� ��ũ�� ���
            JScrollBar sb = jsp.getVerticalScrollBar();

            // ��ũ���� ���� �ؿ� ��ġ�� �����´�.
            int position = sb.getMaximum();

            // �̵�
            sb.setValue(position);
         }

      } catch (UnknownHostException e) {
//         e.printStackTrace();
         System.out.println("11");
      } catch (IOException e) {
         // e.printStackTrace();
         JOptionPane.showConfirmDialog(this, "ä�� ������ �����ֽ��ϴ�.", "ä�� ���� ����", JOptionPane.PLAIN_MESSAGE);
         this.setVisible(false);
      }
   }

}