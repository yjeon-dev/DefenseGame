package project;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame implements ActionListener, KeyListener {

	JLabel lbID, lbPW, lbNickname, jlb, jlbgeneral;
	JButton btnRegister, btnCancel, btnIdCheck, btnNickCheck;
	JTextField tfID = null, tfName;
	JPasswordField passFd;
	NewLogIn nl;

	ImageIcon imgGeneral, imgId, imgPw, imgTitle, imgNick, imgregisterlok, imgcancel, imgcheck, imgdoublecheck;

	public Register(NewLogIn nl) {
		super("회원가입");
		this.nl = nl;
		setLayout(null);
		// -----------------------------------------------------//
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
		double width = d.getWidth();
		double height = d.getHeight();

		int x = (int) (width / 2 - 800 / 2);
		int y = (int) (height / 2 - 520 / 2);
		setBounds(x, y, 800, 520);
		// -----------------------------------------------------//
		imgTitle = new ImageIcon("src/images/registerbig.png");
		imgId = new ImageIcon("src/images/id.png");
		imgPw = new ImageIcon("src/images/pw.png");
		imgNick = new ImageIcon("src/images/nick2.png");
		imgGeneral = new ImageIcon("src/images/generalregister.png");
		imgregisterlok = new ImageIcon("src/images/registerlok.png");
		imgdoublecheck = new ImageIcon("src/images/doublecheck.png");

		imgcancel = new ImageIcon("src/images/cancel.png");
		imgcheck = new ImageIcon("src/images/check.png");

		lbID = new JLabel(imgId);
		lbPW = new JLabel(imgPw);
		lbNickname = new JLabel(imgNick);
		jlb = new JLabel(imgTitle);
		jlbgeneral = new JLabel(imgGeneral);

		btnRegister = new JButton(imgregisterlok);
		btnCancel = new JButton(imgcancel);
		btnIdCheck = new JButton(imgdoublecheck);
		btnNickCheck = new JButton(imgdoublecheck);

		tfID = new JTextField(15);
		tfName = new JTextField(15);
		passFd = new JPasswordField(15);

		lbID.setBounds(15, 170, 120, 60);
		lbPW.setBounds(15, 250, 120, 60);
		lbNickname.setBounds(15, 330, 120, 60);

		btnRegister.setBounds(130, 410, 100, 50);
		btnCancel.setBounds(260, 410, 100, 50);
		btnIdCheck.setBounds(400, 180, 50, 50);
		btnNickCheck.setBounds(400, 330, 50, 50);

		tfID.setBounds(160, 180, 200, 50);
		passFd.setBounds(160, 260, 200, 50);
		tfName.setBounds(160, 330, 200, 50);
		jlb.setBounds(0, 0, 800, 600);

		jlbgeneral.setBounds(450, 0, 400, 520);
		jlb.add(jlbgeneral);

		add(lbID);
		add(lbPW);
		add(lbNickname);

		add(btnRegister);
		add(btnCancel);
		add(btnIdCheck);
		add(btnNickCheck);

		add(tfID);
		add(tfName);
		add(passFd);

		add(jlb);

		Font f = new Font("굴림체", Font.BOLD, 20);
		tfID.setFont(f);
		tfName.setFont(f);
		passFd.setFont(f);

		// Event add
		btnRegister.addActionListener(this);
		btnCancel.addActionListener(this);
		btnIdCheck.addActionListener(this);
		btnNickCheck.addActionListener(this);

		tfID.addKeyListener(this);
		tfName.addKeyListener(this);
		passFd.addKeyListener(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == btnRegister) {

			String id = tfID.getText();
			String pw = new String(passFd.getPassword());
			String nick = tfName.getText();

			int money = 100;
			int hp = 1000;
			int atk = 100;
			int dis = 0;
			int buck = 0;
			int score = 0;
			int round = 0;

			System.out.println("id : " + id);
			System.out.println("pwd : " + pw);
			System.out.println("name : " + nick);

			ProjectDAO dao = new ProjectDAO();

			User_DataDAO dao1 = new User_DataDAO();

			boolean isOk = dao1.isExists(id);

			User_DataDAO dao2 = new User_DataDAO();
			boolean isOk1 = dao2.isExists1(nick);

			if (tfID.getText().length() < 1 || tfName.getText().length() < 1 || passFd.getText().length() < 1) {
				JOptionPane.showConfirmDialog(this, "Please enter your ID / password", "Register Fail", JOptionPane.PLAIN_MESSAGE);

			} else {
				if (isOk) {
					JOptionPane.showConfirmDialog(this, "This ID is not available.", "duplication!", JOptionPane.PLAIN_MESSAGE);
					tfID.setText("");
				} else if (isOk1) {

					JOptionPane.showConfirmDialog(this, "This Nickname is not available.", "duplication!", JOptionPane.PLAIN_MESSAGE);
					tfName.setText("");
				} else {

					dao.insertOne(id, pw, nick, money, hp, atk, dis, buck, score, round);

					JOptionPane.showConfirmDialog(this, "Sucess", "Welcome", JOptionPane.PLAIN_MESSAGE);

					this.setVisible(false);
					new NewLogIn();
				}

			}
		} else if (obj == btnCancel) {

			this.setVisible(false);
			new NewLogIn();

		} else if (obj == btnNickCheck) {
			String nick = tfName.getText();
			User_DataDAO dao = new User_DataDAO();
			boolean isOk = dao.isExists1(nick);

			if (isOk) {
				JOptionPane.showConfirmDialog(this, "This Nickname is not available.", "duplication", JOptionPane.PLAIN_MESSAGE);
				tfName.setText("");
			} else {
				if (nick.length() == 0) {
					JOptionPane.showConfirmDialog(this, "Please Enter your Nickname", "Nickname required", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showConfirmDialog(this, "This Nickname is available.", "available", JOptionPane.PLAIN_MESSAGE);
				}
			}

		} else if (obj == btnIdCheck) {

			// 중복 체크하는 법 넣기
			// -------------------------------------------------------------------------//
			String id = tfID.getText();

			User_DataDAO dao = new User_DataDAO();

			boolean isOk = dao.isExists(id);

			if (isOk) {
				JOptionPane.showConfirmDialog(this, "This ID is not available.", "duplication", JOptionPane.PLAIN_MESSAGE);
				tfID.setText("");
			} else {
				if (id.length() == 0) {
					JOptionPane.showConfirmDialog(this, "Please Enter your ID ", "ID required", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showConfirmDialog(this, "This ID is available.", "available", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
//   tfID.addKeyListener(this);
//   tfName.addKeyListener(this);
//   passFd.addKeyListener(this);

	public void keyPressed(KeyEvent e) {

		// get text 가져와서 string으로 해서 length메소드 써서 길이 가져오기

		String a = tfID.getText();
		String b = tfName.getText();
		String c = passFd.getText();

		int length = a.length();
		int length2 = b.length();
		int length3 = c.length();

		if (length > 10) {
			JOptionPane.showConfirmDialog(this, "Please enter an ID of 10 digits or less.", "ID check", JOptionPane.PLAIN_MESSAGE);
			tfID.setText("");

		} else if (length2 > 10) {
			JOptionPane.showConfirmDialog(this, "Please enter an Nickname of 10 digits or less.", "Nickname check", JOptionPane.PLAIN_MESSAGE);
			tfName.setText("");

		} else if (length3 > 10) {
			JOptionPane.showConfirmDialog(this, "Please enter an PW of 10 digits or less.", "PW check", JOptionPane.PLAIN_MESSAGE);
			passFd.setText("");

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}