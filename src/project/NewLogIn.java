package project;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
//import day19.ChatClient;

public class NewLogIn extends JFrame implements ActionListener {

	
	ImageIcon imgTitle, imgId, imgPw, imgGeneral, imglogin, imgregister;
	JLabel jlbId, jlbPw, jlb, jlbgeneral;
	JButton jbtnLogin, jbtnRegister; // 로그인,등록 버튼
	JTextField jtfId = null, jtfPw; // 로그인 패스워드 텍스트필드
	JPasswordField jpf;

	
	NewLogIn() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		// -----------------------------------------------------------//
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
		double width = d.getWidth();
		double height = d.getHeight();

		int x = (int) (width / 2 - 800 / 2);
		int y = (int) (height / 2 - 520 / 2);
		setBounds(x, y, 800, 520);

		// --------------------------------------------------------//

		imgTitle = new ImageIcon("src/images/title2.png");
		imgId = new ImageIcon("src/images/id.png");
		imgPw = new ImageIcon("src/images/pw.png");
		imgGeneral = new ImageIcon("src/images/general.png");
		imglogin = new ImageIcon("src/images/login.png");
		imgregister = new ImageIcon("src/images/register.png");

		// 컴포넌트 초기화
		jlbId = new JLabel(imgId);
		jlbPw = new JLabel(imgPw);
		jbtnLogin = new JButton(imglogin);
		jbtnRegister = new JButton(imgregister);
		jtfId = new JTextField(20);
		jtfPw = new JTextField(20);
		jlb = new JLabel(imgTitle);
		jlbgeneral = new JLabel(imgGeneral);

		// ** 비밀번호
		jpf = new JPasswordField();

		jlbId.setBounds(100, 200, 120, 60);
		jlbPw.setBounds(100, 300, 120, 60);
		jbtnLogin.setBounds(100, 400, 120, 50);
		jbtnRegister.setBounds(300, 400, 120, 50);
		jtfId.setBounds(300, 200, 200, 50);

		jpf.setBounds(300, 300, 200, 50);
		jlb.setBounds(0, 0, 800, 600);

		jlbgeneral.setBounds(450, 0, 400, 520);

		jbtnLogin.addActionListener(this);
		jbtnRegister.addActionListener(this);

		Font f = new Font("굴림체", Font.BOLD, 20);
		jtfId.setFont(f);
		jpf.setFont(f);
		add(jlbId);
		add(jlbPw);
		add(jtfId);
		add(jpf);
		add(jbtnLogin);
		add(jbtnRegister);

		add(jlb);

		jlb.add(jlbgeneral);

		// setBounds(100, 100, 800, 600);

		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new NewLogIn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == jbtnLogin) {

			// ---------------------------------------------------------//

			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@orcl.cl3ehe0nny5q.ap-northeast-2.rds.amazonaws.com:1521:orcl";
			String user = "scott";
			String password = "tiger333";

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, password);

			} catch (ClassNotFoundException e1) {
				System.out.println("드라이버 로딩 실패");
			} catch (SQLException e1) {
				System.out.println("db연결 실패");
			}

			// **********사용자의 입력값 가져오기
			String uid = jtfId.getText();
			char[] ch = jpf.getPassword();

			String pw = new String(ch);

			ProjectDAO dao = new ProjectDAO();

			boolean isOk = dao.isExists(uid, pw);

			if (isOk) {
				// ----------------------------------------------------------------------------------//
				// 우리 메인메뉴로 바꾸기
				new Main(uid);
				this.setVisible(false);

			} else {
				JOptionPane.showConfirmDialog(this, "ID/PW check please", "login fail", JOptionPane.PLAIN_MESSAGE);
				jtfId.setText("");
				jpf.setText("");

			}

		} else if (obj == jbtnRegister) {
			// System.out.println("회원가입 ");

			// *********************************
			new Register(this);
			this.setVisible(false);

		}
	}
}
