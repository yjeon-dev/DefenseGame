package project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.StyledEditorKit.BoldAction;

public class Stat extends JFrame implements ActionListener {
	JButton jbtnGoWeapon, jbtnMain;
	JLabel jlb, jlbAttack, jlbAttack1, jlbCastleHp, jlbCastleHp1, jlbResilience, jlbResilience1, jlbScore, jlbScore1,
			jlbHero, jlbRound, jlbRound1;
	ImageIcon imgStat, imgGoWeapon, imgMain, imgAttack, imgHp, imgRe, imgScore, imgRound, imgHero;
	Main main;
	Font f = new Font("°ß°íµñ", Font.BOLD, 60);

	Stat() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		imgStat = new ImageIcon("src/images/mystat.png");
		imgGoWeapon = new ImageIcon("src/images/goweapon.png");
		imgMain = new ImageIcon("src/images/left.png");
		imgAttack = new ImageIcon("src/images/attack.png");
		imgHp = new ImageIcon("src/images/castlehp.png");
		imgRe = new ImageIcon("src/images/castleres.png");
		imgScore = new ImageIcon("src/images/rankScore.png");
		imgRound = new ImageIcon("src/images/rankRound.png");
		imgHero = new ImageIcon("src/heroimg/hero1.png");

		String atk = Integer.toString(Main.atk);
		String hp = Integer.toString(Main.hp);
		String score = Integer.toString(Main.score);
		String round = Integer.toString(Main.round);

		jbtnGoWeapon = new JButton(imgGoWeapon);
		jbtnMain = new JButton(imgMain);
		jlb = new JLabel(imgStat);
		jlbAttack = new JLabel(imgAttack);
		jlbAttack1 = new JLabel(atk);
		jlbCastleHp = new JLabel(imgHp);
		jlbCastleHp1 = new JLabel(hp);
		jlbResilience = new JLabel(imgRe);
		jlbResilience1 = new JLabel("0");
		jlbScore = new JLabel(imgScore);
		jlbScore1 = new JLabel(score);
		jlbRound = new JLabel(imgRound);
		jlbRound1 = new JLabel(round);

		jlbHero = new JLabel(imgHero);

		jlbAttack1.setFont(f);
		jlbCastleHp1.setFont(f);
		jlbResilience1.setFont(f);
		jlbScore1.setFont(f);
		jlbRound1.setFont(f);

		jbtnGoWeapon.setBounds(670, 520, 110, 50);
		jbtnMain.setBounds(20, 20, 70, 40);
		jlbAttack.setBounds(50, 120, 150, 60);
		jlbAttack1.setBounds(220, 120, 250, 60);
		jlbCastleHp.setBounds(50, 200, 150, 60);
		jlbCastleHp1.setBounds(220, 200, 200, 60);
		jlbResilience.setBounds(50, 280, 150, 60);
		jlbResilience1.setBounds(220, 280, 150, 60);
		jlbScore.setBounds(50, 360, 150, 60);
		jlbScore1.setBounds(220, 360, 350, 60);
		jlbRound.setBounds(50, 440, 150, 60);
		jlbRound1.setBounds(220, 440, 150, 60);
		jlbHero.setBounds(500, 120, 160, 330);

		add(jbtnGoWeapon);
		add(jbtnMain);
		add(jlbAttack);
		add(jlbAttack1);
		add(jlbCastleHp);
		add(jlbCastleHp1);
		add(jlbResilience);
		add(jlbResilience1);
		add(jlbScore);
		add(jlbScore1);
		add(jlbRound);
		add(jlbRound1);
		add(jlbHero);
		add(jlb);

		jbtnGoWeapon.addActionListener(this);
		jbtnMain.addActionListener(this);

		setBounds(560, 240, 800, 600);
		setVisible(true);

		setResizable(false);

	}

//	Stat(Object obj) {
//		main = (Main) obj;
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//		imgStat = new ImageIcon("src/images/mystat.png");
//		imgGoWeapon = new ImageIcon("src/images/goweapon.png");
//		imgMain = new ImageIcon("src/images/left.png");
//		imgAttack = new ImageIcon("src/images/attack.png");
//		imgHero = new ImageIcon("src/heroimg/hero1.png");
//
//		String atk = Integer.toString(Main.atk);
//
//		jbtnGoWeapon = new JButton(imgGoWeapon);
//		jbtnMain = new JButton(imgMain);
//		jlb = new JLabel(imgStat);
//		jlbAttack = new JLabel(imgAttack);
//		jlbAttack1 = new JLabel(atk);
//		jlbCastleHp = new JLabel(imgAttack);
//		jlbCastleHp1 = new JLabel(imgAttack);
//		jlbRange = new JLabel(imgAttack);
//		jlbRange1 = new JLabel(imgAttack);
//		jlbBuck = new JLabel(imgAttack);
//		jlbBuck1 = new JLabel(imgAttack);
//		jlbHero = new JLabel(imgHero);
//
//		jbtnGoWeapon.setBounds(670, 520, 110, 50);
//		jbtnMain.setBounds(20, 20, 70, 40);
//		jlbAttack.setBounds(50, 100, 150, 40);
//		jlbAttack1.setBounds(220, 120, 150, 40);
//		jlbCastleHp.setBounds(50, 220, 150, 40);
//		jlbCastleHp1.setBounds(220, 220, 150, 40);
//		jlbRange.setBounds(50, 320, 150, 40);
//		jlbRange1.setBounds(220, 320, 150, 40);
//		jlbBuck.setBounds(50, 420, 150, 40);
//		jlbBuck1.setBounds(220, 420, 150, 40);
//		jlbHero.setBounds(500, 120, 160, 330);
//
//		add(jbtnGoWeapon);
//		add(jbtnMain);
//		add(jlbAttack);
//		add(jlbAttack1);
//		add(jlbCastleHp);
//		add(jlbCastleHp1);
//		add(jlbRange);
//		add(jlbRange1);
//		add(jlbBuck);
//		add(jlbBuck1);
//		add(jlbHero);
//		add(jlb);
//
//		jbtnGoWeapon.addActionListener(this);
//		jbtnMain.addActionListener(this);
//
//		setBounds(560, 240, 800, 600);
//		setVisible(true);
//
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == jbtnGoWeapon) {
			this.setVisible(false);
			new Weapon();
		} else if (obj == jbtnMain) {
			this.setVisible(false);
			new Main();
		}
	}

}
