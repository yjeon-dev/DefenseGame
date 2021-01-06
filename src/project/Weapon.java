package project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Weapon extends JFrame implements ActionListener {
	JButton jbtnGoStat, jbtnMain;
	JLabel jlb, jlbGun, jlbBuck, jlbRange, jlbBullet, jlbReload;
	ImageIcon imgWeapon, imgGoStat, imgMain, imgGun, imgBullet, imgReload, imgBuck, imgRange, imgBuckOk, imgRangeOk;
	JTextField jtfBullet, jtfReload, jtfBuck;
	Font f = new Font("°ß°íµñ", Font.BOLD, 50);

	Weapon() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		imgWeapon = new ImageIcon("src/images/weapon.png");
		imgGoStat = new ImageIcon("src/images/gostat.png");
		imgMain = new ImageIcon("src/images/left.png");
		imgGun = new ImageIcon("src/images/weaponGun.png");
		imgBullet = new ImageIcon("src/images/weaponBullet.png");
		imgReload = new ImageIcon("src/images/weaponReload.png");
		imgBuck = new ImageIcon("src/images/weaponBuck.png");
		imgRange = new ImageIcon("src/images/weaponRange.png");
		imgBuckOk = new ImageIcon("src/images/weaponBuckOk.png");
		imgRangeOk = new ImageIcon("src/images/weaponRangeOk.png");

		jbtnGoStat = new JButton(imgGoStat);
		jbtnMain = new JButton(imgMain);
		jlb = new JLabel(imgWeapon);
		jlbGun = new JLabel(imgGun);
		if (Main.buck == 0)
			jlbBuck = new JLabel(imgBuck);
		else if (Main.buck == 1)
			jlbBuck = new JLabel(imgBuckOk);
		if (Main.dis == 0)
			jlbRange = new JLabel(imgRange);
		if (Main.dis == 1)
			jlbRange = new JLabel(imgRangeOk);
		jlbBullet = new JLabel(imgBullet);
		jlbReload = new JLabel(imgReload);
		jtfBullet = new JTextField("¡Ä");
		jtfReload = new JTextField("0ÃÊ");

		jbtnGoStat.setBounds(670, 520, 110, 50);
		jbtnMain.setBounds(20, 20, 70, 40);
		jlbGun.setBounds(300, 200, 200, 170);
		jlbBuck.setBounds(50, 100, 200, 150);
		jlbRange.setBounds(50, 350, 200, 150);
		jlbBullet.setBounds(300, 400, 200, 150);
		jlbReload.setBounds(550, 200, 200, 150);

		jtfBullet.setBounds(380, 480, 100, 50);
		jtfReload.setBounds(630, 280, 100, 50);

		jtfBullet.setFont(f);
		jtfReload.setFont(f);

		add(jbtnGoStat);
		add(jbtnMain);
		add(jtfBullet);
		add(jtfReload);
		add(jlbGun);
		add(jlbBuck);
		add(jlbBullet);
		add(jlbReload);
		add(jlbRange);

		add(jlb);

		jbtnGoStat.addActionListener(this);
		jbtnMain.addActionListener(this);

		setBounds(560, 240, 800, 600);
		setVisible(true);

		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == jbtnGoStat) {
			this.setVisible(false);
			new Stat();
		} else if (obj == jbtnMain) {
			this.setVisible(false);
			new Main();
		}
	}
}
