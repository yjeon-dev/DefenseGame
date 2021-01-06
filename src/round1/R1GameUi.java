package round1;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import project.Main;



public class R1GameUi extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
	JLabel jbcas, jbhero;
	JLabel boomL[];
	JLabel ene[];
	JTextField jt1, jt2, jt3, jt4, jt5;
	JLabel result;
	ImageIcon castle;
	ImageIcon hero1 = new ImageIcon("src/heroimg/hero1.png");
	ImageIcon hero2 = new ImageIcon("src/heroimg/hero2.png");
	ImageIcon hero3 = new ImageIcon("src/heroimg/hero3.png");
	ImageIcon hero4 = new ImageIcon("src/heroimg/hero4.png");
	ImageIcon boom = new ImageIcon("src/images/boom.png");
	ImageIcon loseimg = new ImageIcon("src/images/loseresult.png");
	ImageIcon checkbtn = new ImageIcon("src/images/checkbtn.png");
	ImageIcon icon[][];
	ImageIcon resultImg = new ImageIcon("src/images/result.png");
	Font f1, f2;
	int gold = 0;
	int check = 0;
	int test = 1;
	int castleHp;
	int dmg;
	int money;
	int buck;
	int dis;
	int score = 0;
	int disxy = 80;
	int x;
	int y;
	int x1;
	int y1;
	int ex;
	int ey;
	int mx;
	int my;
	int killcnt=0;
	Enemy1 eneC[];
	castleCheck cc;
	int i = 1;
	int spawn = 100;
	int maxSpawn = spawn;
	Main main;
	int discnt = 0;
	int boomcnt = 0;
	JButton btn;
	JTextField jtr1, jtr2, jtr3, jtr4;

	int gamecnt = 0;

	public R1GameUi(Object obj) {
		main = (Main) obj;
		f1 = new Font("∞ÌµÒ", Font.BOLD, 40);
		f2 = new Font("∞ÌµÒ", Font.BOLD, 30);
		boomL = new JLabel[10];
		btn = new JButton(checkbtn);
		jtr1 = new JTextField();
		jtr2 = new JTextField();
		jtr3 = new JTextField();
		jtr4 = new JTextField();
		jtr1.setFont(f1);
		jtr2.setFont(f1);
		jtr3.setFont(f1);
		jtr4.setFont(f1);

		add(jtr1);
		add(jtr2);
		add(jtr3);
		add(jtr4);

		add(btn);
		btn.setBounds(10000, 10000, 100, 50);
		result = new JLabel(resultImg);
		add(result);
		result.setBounds(10000, 10000, 700, 595);
		gamecnt = 0;
		for (int i = 0; i < 10; i++) {
			boomL[i] = new JLabel();
			boomL[i].setIcon(boom);
			add(boomL[i]);
			boomL[i].setBounds(5000, 5000, 300, 300);
		}

		check = 0;
		setLayout(null);
		stat();
		icon = new ImageIcon[4][2];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				icon[i][j] = new ImageIcon("src/heroimg/hero" + (i + 1) + "f" + (j + 1) + ".png");
				System.out.println("src/heroimg/hero" + (i + 1) + "f" + (j + 1) + ".png");
			}
		}
		jt1 = new JTextField("CastlePower: " + castleHp);
		jt2 = new JTextField("Money : " + money);
		jt3 = new JTextField("Monster : " + spawn);
		jt4 = new JTextField("Score : " + score);

		jt1.setFont(f2);
		jt2.setFont(f2);
		jt3.setFont(f2);
		jt4.setFont(f2);

		ene = new JLabel[maxSpawn];
		for (int i = 0; i < maxSpawn; i++) {
			ene[i] = new JLabel();
		}
		// ƒƒ∆˜≥Õ∆Æ
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
		castle = new ImageIcon("src/images/castle.jpg");
		jbcas = new JLabel(castle);
		jbhero = new JLabel(hero1);
		double width = d.getWidth();
		double height = d.getHeight();
		int x = (int) (width / 2 - 1500 / 2);
		int y = (int) (height / 2 - 900 / 2);
		for (int i = 0; i < maxSpawn; i++) {
			add(ene[i]);
		}

		add(jt1);
		add(jt2);
		add(jt3);
		add(jt4);
		add(jbhero);
		add(jbcas);
		jtr1.setEditable(false);
		jtr2.setEditable(false);
		jtr3.setEditable(false);
		jtr4.setEditable(false);
		btn.addActionListener(this);
		jbhero.setBounds(100, 30, 200, 220);
		jbcas.setBounds(0, 0, 1500, 900);
		jt1.setBounds(0, 0, 300, 40);
		jt2.setBounds(1200, 0, 300, 40);
		jt3.setBounds(600, 0, 300, 40);
		jt4.setBounds(1200, 40, 300, 40);
		eneC = new Enemy1[maxSpawn];
		for (int i = 0; i < maxSpawn; i++) {

			eneC[i] = new Enemy1(ene[i], this);
			eneC[i].start();
		}

		cc = new castleCheck(this);
		cc.start();

		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		//setBounds(x, y, 1500, 900);
		setBounds(0, 0, 1500, 900);

		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		Random rnd = new Random();
		int temp = 0;
		if (gamecnt == 0) {
			if (jbhero.getIcon().equals(hero1)) {
				temp = rnd.nextInt(2);
				System.out.println("temp : " + temp);
				jbhero.setIcon(icon[0][temp]);
				x1 = 285;
				y1 = 212;
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (jbhero.getIcon().equals(hero2)) {
				temp = rnd.nextInt(2);
				System.out.println("temp : " + temp);
				jbhero.setIcon(icon[1][temp]);
				x1 = 250;
				y1 = 230;
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (jbhero.getIcon().equals(hero3)) {
				temp = rnd.nextInt(2);
				System.out.println("temp : " + temp);
				jbhero.setIcon(icon[2][temp]);
				x1 = 255;
				y1 = 248;
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (jbhero.getIcon().equals(hero4)) {
				temp = rnd.nextInt(2);
				System.out.println("temp : " + temp);
				jbhero.setIcon(icon[3][temp]);
				x1 = 223;
				y1 = 256;
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (dis == 1) {
				discnt += 1;
				System.out.println(discnt);
				if (discnt >= 10) {
					discnt = 0;
					disxy = 300;
					if (boomcnt > 9) {
						boomcnt = 0;
					}
					boomTr bt = new boomTr(this, e.getX(), e.getY(), disxy, boomcnt);
					boomcnt++;
					bt.start();
				}
			}
			for (int i = 0; i < maxSpawn; i++) {
				ex = ene[i].getX();
				ex = ex + 75;
				ey = ene[i].getY();
				ey = ey + 100;
				mx = e.getX();
				my = e.getY();

				double dis = Math.sqrt((mx - ex) * (mx - ex) + (my - ey) * (my - ey));
				if (dis < disxy) {
					System.out.println(dis);
					eneC[i].atkHp(dmg);
					System.out.println("≥≤¿∫√º∑¬ : " + eneC[i].getHp());
					if (disxy == 300) {
						System.out.println("ƒÁ~!");
					}
				}

			}
			if (buck == 1) {
				disxy = 160;
			} else if (buck == 0) {
				disxy = 80;
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Random rnd = new Random();
		if (gamecnt == 0) {
			g.drawLine(x1, y1, x, y);
			System.out.println(Main.buck);
			if (buck == 1) {
				System.out.println("º¶∞« πﬂªÁ");
				g.drawLine(x1, y1, x + rnd.nextInt(160) - 80, y + rnd.nextInt(160) - 80);
				g.drawLine(x1, y1, x + rnd.nextInt(160) - 80, y + rnd.nextInt(160) - 80);
				g.drawLine(x1, y1, x + rnd.nextInt(160) - 80, y + rnd.nextInt(160) - 80);
				g.drawLine(x1, y1, x + rnd.nextInt(160) - 80, y + rnd.nextInt(160) - 80);
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
//		System.out.print("x = " + e.getX() + " ");
//		System.out.println(" y = " + e.getY());
		if (gamecnt == 0) {
			if (x > 1100) {
				jbhero.setIcon(hero1);
			} else if (x > 850) {
				jbhero.setIcon(hero2);
			} else if (x > 600) {
				jbhero.setIcon(hero3);
			} else if (x > 0) {
				jbhero.setIcon(hero4);
			}
		}
//		System.out.println(jbhero.getIcon());
	}

	public void Lose() {
		gamecnt = 1;
		result.setIcon(loseimg);
		result.setLocation(400, 150);
		btn.setLocation(700, 665);
		jtr1.setBounds(635, 208, 410, 85);
		jtr2.setBounds(639, 309, 400, 90);
		jtr3.setBounds(635, 432, 410, 80);
		jtr4.setBounds(635, 542, 400, 90);
		jtr1.setText("0"+"           	     	 ");
		jtr2.setText(killcnt+"           	     	 ");
		jtr3.setText(gold+"      		           ");
		jtr4.setText(score+"       	          ");
	}
	

	public void stat() {
		dmg = main.atk;
		castleHp = main.hp;
		money = main.money;
		buck = main.buck;
		dis = main.dis;
		if (buck == 1) {
			disxy = 160;
		}
	}

	public void update() {
		jtr1.setText(castleHp + "");
		jtr2.setText(maxSpawn + "");
		jtr3.setText(gold + "");
		jtr4.setText(score + "");
		Main.money = money;
		Main.score += score;
		System.out.println("∏ﬁ¿Œ" + Main.score);
		System.out.println("∑Œƒ√" + score);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		main = new Main();
	}

}
