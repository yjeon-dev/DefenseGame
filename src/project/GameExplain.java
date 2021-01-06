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
import javax.swing.JTextField;

import round1.R1GameUi;

public class GameExplain extends JFrame implements KeyListener, ActionListener {

	JButton jbtnNext;
	JButton jbtnRight, jbtnLeft;
	ImageIcon imgExplain, imgBackground, imgNext;
	ImageIcon imgLeft, imgRight;
	JTextField jbr;
	JLabel jlbTitle, jlbExplain, jlb;
	int round = 0;
	Main main;

	GameExplain(Object obj) {
		Font f = new Font("견고딕", Font.BOLD, 30);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		// --------------------------------------------------------//
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
		double width = d.getWidth();
		double height = d.getHeight();

		int x = (int) (width / 2 - 800 / 2);
		int y = (int) (height / 2 - 520 / 2);
		setBounds(x, y, 800, 520);

		// --------------------------------------------------------//

		main = (Main) obj;

		imgExplain = new ImageIcon("src/images/explain3.png");
		imgBackground = new ImageIcon("src/images/background.png");
		imgNext = new ImageIcon("src/images/next.png");
		imgLeft = new ImageIcon("src/images/left.png");
		imgRight = new ImageIcon("src/images/right.png");
		jlb = new JLabel(imgBackground);
		jlbTitle = new JLabel(imgExplain);
		jlbExplain = new JLabel();
		jbtnNext = new JButton(imgNext);
		jbtnRight = new JButton(imgRight);
		jbtnLeft = new JButton(imgLeft);
		round = Integer.valueOf(main.round);
		jbr = new JTextField();
		jbr.setText(round + "라운드");
		jbr.setFont(f);

		jbtnNext.addKeyListener(this);
		jbtnNext.addActionListener(this);
		jbtnRight.addActionListener(this);
		jbtnLeft.addActionListener(this);

		jbtnLeft.setBounds(235, 505, 70, 40);
		jbtnRight.setBounds(495, 505, 70, 40);
		jlb.setBounds(0, 0, 800, 600);
		jlbTitle.setBounds(50, -40, 700, 550);
		jlbExplain.setBounds(100, 300, 120, 60);
		jbtnNext.setBounds(600, 500, 180, 60);
		jbr.setBounds(325, 500, 150, 50);

		add(jbtnNext);
		jlb.add(jlbTitle);
		add(jbr);
		add(jbtnRight);
		add(jbtnLeft);
		add(jlbTitle);
		add(jlbExplain);
		add(jlb);

		setBounds(560, 240, 800, 600);
		setVisible(true);
		setResizable(false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == 32) {
			this.setVisible(false);
			new R1GameUi(main);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == jbtnNext) {
			if (round == 1) {
				this.setVisible(false);
				new round1.R1GameUi(main);
			}else if(round == 2) {
				this.setVisible(false);
				new round2.R1GameUi(main);
			}else if(round == 3) {
				this.setVisible(false);
				new round3.R1GameUi(main);
			}else if(round == 4) {
				this.setVisible(false);
				new round4.R1GameUi(main);
			}else if(round == 5) {
				this.setVisible(false);
				new round5.R1GameUi(main);
			}
		}

		else if (obj == jbtnRight) {
			if (round < main.round) {
				round++;
				jbr.setText(round + "round");
			}
		} else if (obj == jbtnLeft) {
			if (round > 1) {
				round--;
				jbr.setText(round + "round");
			}
		}

	}

}
