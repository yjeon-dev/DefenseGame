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

public class Store2 extends JFrame implements ActionListener {
	JButton jbtnRight, jbtnLeft, jbtnBack, jbtnBuy;
	ImageIcon imgLeft, imgRight, imgSM1, imgBack, imgBuy, imgBuyEnd;
	JLabel jlb;
	JTextField jtf;
	String gold = Integer.toString(Main.money);
	Font f = new Font("견고딕", Font.BOLD, 30);

	Store2() {
		setTitle("디펜스 게임");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		imgLeft = new ImageIcon("src/images/left.png");
		imgRight = new ImageIcon("src/images/right.png");
		imgSM1 = new ImageIcon("src/images/storemain6.png");
		imgBack = new ImageIcon("src/images/back.png");
		imgBuy = new ImageIcon("src/images/buybuck.png");
		imgBuyEnd = new ImageIcon("src/images/buyend.png");

		jbtnRight = new JButton(imgRight);
		jbtnLeft = new JButton(imgLeft);
		jbtnBack = new JButton(imgBack);
		if (Main.buck == 0) {
			jbtnBuy = new JButton(imgBuy);
		} else if (Main.buck == 1) {
			jbtnBuy = new JButton(imgBuyEnd);
		}
		jtf = new JTextField(gold);
		jlb = new JLabel(imgSM1);

		jtf.setFont(f);

		jbtnLeft.setBounds(20, 280, 70, 40);
		jbtnRight.setBounds(720, 280, 70, 40);
		jbtnBack.setBounds(670, 530, 100, 40);
		jbtnBuy.setBounds(300, 480, 200, 80);
		jtf.setBounds(130, 510, 120, 50);
		jlb.setBounds(0, 0, 800, 600);

		jbtnRight.addActionListener(this);
		jbtnLeft.addActionListener(this);
		jbtnBack.addActionListener(this);
		jbtnBuy.addActionListener(this);

		add(jbtnRight);
		add(jbtnLeft);
		add(jbtnBack);
		add(jbtnBuy);
		add(jtf);
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
			new Store3();
		} else if (obj == jbtnLeft) {
			this.setVisible(false);
			new Store1();
		} else if (obj == jbtnBack) {
			this.setVisible(false);
			new Main();

		} else if (obj == jbtnBuy) {
			if (jbtnBuy.getIcon().equals(imgBuy)) {
				int result = JOptionPane.showConfirmDialog(null, "구매하시겠습니까?", "구매", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.CLOSED_OPTION) {
					this.setVisible(false);
					new Store2();
				} else if (result == JOptionPane.YES_OPTION) {
					if (Main.money >= 10000) {
						if (Main.buck == 0) {
							Main.money -= 10000;
							Main.buck = 1;
							jbtnBuy.setIcon(imgBuyEnd);
						}
					} else {

						JOptionPane.showConfirmDialog(this, "소지 금액이 적습니다. ", "오류 ", JOptionPane.PLAIN_MESSAGE);
					}
				}
			} else if (jbtnBuy.getIcon().equals(imgBuyEnd)) {
				JOptionPane.showConfirmDialog(this, "이미 구입 하셨습니다.", "오류", JOptionPane.CLOSED_OPTION);
			}
			System.out.println("보유한돈  : " + Main.money + "   산탄총 사용가능 : " + Main.buck);

		}

	}// actionlistener end

}// Store1 end
