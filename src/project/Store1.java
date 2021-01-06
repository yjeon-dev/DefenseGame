package project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Store1 extends JFrame implements ActionListener {
	JButton jbtnRight, jbtnLeft, jbtnBack, jbtnBuy;
	ImageIcon imgLeft, imgRight, imgSM1, imgBack, imgBuy,imgMoney;
	JLabel jlb, jlbBuy;
	JTextField jtf, jtfBuy;
	Main main;
	int price = 200;
	int cnt = (Main.hp - 1000)/100+1;
	int priceF = price * cnt;
	String gold = Integer.toString(Main.money);
	Font f = new Font("견고딕", Font.BOLD, 30);

	Store1() {
		System.out.println(priceF);
		setTitle("Defense Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		imgLeft = new ImageIcon("src/images/left.png");
		imgRight = new ImageIcon("src/images/right.png");
		imgSM1 = new ImageIcon("src/images/storemain1.png");
		imgBack = new ImageIcon("src/images/back.png");
		imgBuy = new ImageIcon("src/images/buy.png");
		imgMoney = new ImageIcon("src/images/storemoney.png");

		jbtnRight = new JButton(imgRight);
		jbtnLeft = new JButton(imgLeft);
		jbtnBack = new JButton(imgBack);
		jbtnBuy = new JButton(imgBuy);
		jtf = new JTextField(gold);
		jtfBuy = new JTextField(Integer.toString(priceF));
		jlbBuy = new JLabel(imgMoney);
		jlb = new JLabel(imgSM1);

		jbtnLeft.setBounds(20, 280, 70, 40);
		jbtnRight.setBounds(720, 280, 70, 40);
		jbtnBack.setBounds(670, 530, 100, 40);
		jbtnBuy.setBounds(300, 480, 200, 30);
		jtf.setBounds(130, 510, 120, 50);
		jtfBuy.setBounds(360, 515, 130, 40);
		jlbBuy.setBounds(315, 515, 30, 40);
		jlb.setBounds(0, 0, 800, 600);

		jtf.setFont(f);
		jtfBuy.setFont(f);

		jbtnRight.addActionListener(this);
		jbtnLeft.addActionListener(this);
		jbtnBack.addActionListener(this);
		jbtnBuy.addActionListener(this);

		add(jbtnRight);
		add(jbtnLeft);
		add(jbtnBack);
		add(jtfBuy);
		add(jbtnBuy);
		add(jtf);
		add(jlbBuy);
		add(jlb);

		setBounds(560, 240, 800, 600);
		setVisible(true);

		setResizable(false);
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == jbtnRight) {
			this.setVisible(false);
			new Store2();
		} else if (obj == jbtnLeft) {
			this.setVisible(false);
			new Store();
		} else if (obj == jbtnBack) {
			this.setVisible(false);
			new Main();

		} else if (obj == jbtnBuy) {
			int result = JOptionPane.showConfirmDialog(null, "구매하시겠습니까?", "purchase", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.CLOSED_OPTION) {
				this.setVisible(false);
				new Store1();
			} else if (result == JOptionPane.YES_OPTION) {
				if (Main.money >= Integer.parseInt(jtfBuy.getText())) {
					Main.money -= Integer.parseInt(jtfBuy.getText());
					Main.hp += 100;
				} else {
					JOptionPane.showConfirmDialog(this, "소지 금액이 적습니다. ", "error ", JOptionPane.PLAIN_MESSAGE);
				}
			}
			System.out.println("보유한돈  : " + Main.money + "   성 체력 : " + Main.hp);
			this.setVisible(false);
			new Store1();
		}

	}// actionlistener end

}// Store1 end
