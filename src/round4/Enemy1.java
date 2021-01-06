package round4;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import project.Main;

public class Enemy1 extends Thread {
	JLabel ene;
	ImageIcon img1, img2;
	ImageIcon imga1, imga2;
	int cnt = 0;// 5번 반복마다 이미지체인지
	int img = 1;
	int ax1 = 260, ay1 = 260;

	int x = 0, y = 0;
	Random rnd;
	int hp;
	int atk;
	int move;
	int dropMoney;
	int score;
	int hitx;
	int hity;
	int atks;
	boolean flag = true;
	int hp1;
	R1GameUi gu;
	

	Enemy1(JLabel ene,Object obj) {
		this.ene = ene;
		gu = (R1GameUi)obj;
		rnd = new Random();
		x = rnd.nextInt(3000) + 1500;
		y = rnd.nextInt(400) + 300;
		System.out.println("x = " + x + " y = " + y);
		img1 = new ImageIcon("src/mon4/monm1.png");
		img2 = new ImageIcon("src/mon4/monm2.png");
		imga1 = new ImageIcon("src/mon4/mona1.png");
		imga2 = new ImageIcon("src/mon4/mona2.png");
		hp = 3;
		atk = 500;
		move = 15;
		atks = 100;
		hitx = 150;
		hity = 200;
		score = 10;
		dropMoney = 1;

	}

	public int getHp() {
		return hp;
	}

	public void atkHp(int atk) {
		this.hp -= atk;
	}

	public int getDropMoney() {
		return dropMoney;
	}

	public void setDropMoney(int dropMoney) {
		this.dropMoney = dropMoney;
	}

	@Override
	public void run() {
		ene.setBounds(x, y, 200, 250);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ene.setIcon(img1);
		while (flag) {// y는 몬스터 y값, ay2는 임시값
			y = ene.getY();
			x = ene.getX();
			x -= 1;
//			x -= x;
			cnt++;
			if (x != ax1) {
				if (cnt > 20) {
					if (img == 1) {
						ene.setIcon(img1);
						cnt = 0;
						img = 0;
					} else {
						ene.setIcon(img2);
						cnt = 0;
						img = 1;
					}
				}
				
				try {
					sleep(move);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ene.setLocation(x, y);
			}

			else if (x == ax1) {
				if (cnt > 10) {
					if (img == 1) {
						ene.setIcon(imga1);
						cnt = 0;
						img = 0;
					} else {
						ene.setIcon(imga2);
						cnt = 0;
						img = 1;
						hp1 = gu.castleHp;
						hp1 -= atk;
						gu.castleHp = hp1;
						gu.jt1.setText("Castle Strength : "+gu.castleHp);
						if(gu.castleHp < 0) {
							ene.setLocation(2000, 2000);
							ene.setIcon(imga1);
							stop();
						}
					}
				}
				try {
					sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(hp<=0) {
				ene.setLocation(2000, 2000);
				ene.setIcon(imga1);
				gu.money+=dropMoney;
				gu.gold+=dropMoney;
				gu.jt2.setText("Money : "+gu.money+"                 ");
				gu.spawn-=1;
				gu.jt3.setText("Remaining Monster : "+ gu.spawn+"                 ");
				gu.score += score;
				gu.jt4.setText("Current Score : "+gu.score+"                 ");
				if(gu.spawn == 0) {
					gu.gamecnt = 1;
					gu.update();
					System.out.println(Main.money);
					gu.result.setLocation(400, 150);
					gu.btn.setLocation(700,665);
					gu.jtr1.setBounds(635 , 208,410,85);
					gu.jtr2.setBounds(639 , 309,400,90);
					gu.jtr3.setBounds(635 , 432,410,80);
					gu.jtr4.setBounds(635 , 542,400,90);
					if(Main.round == 4) {
						Main.round = 5;
					}
//					gu.setVisible(false);
//					new Main();
					gu.killcnt++;
					
					
				}
				flag = false;
			}
			
		}

	}

}
