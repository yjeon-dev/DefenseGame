package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame implements ActionListener{
	ImageIcon imgMainBackground, imgStart, imgStore, imgRank, imgChat, imgStat;
	JLabel jlb;
	JButton jbtnStart, jbtnStore, jbtnRank, jbtnChat, jbtnStat;
	public static String uid;
	public static int hp;
	public static int atk;
	public static int money;
	public static int buck;
	public static int dis;
	public static int score;
	public static int round;
	
	Main(String uid) {
		setTitle("Defense Game");
		this.uid = uid;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		imgMainBackground = new ImageIcon("src/images/mainbackground.png");
		imgStart = new ImageIcon("src/images/start.png");
		imgStore = new ImageIcon("src/images/store.png");
		imgRank = new ImageIcon("src/images/rank.png");
		imgChat = new ImageIcon("src/images/chat.png");
		imgStat = new ImageIcon("src/images/stat.png");
		
		jlb = new JLabel(imgMainBackground);

		jbtnStart = new JButton(imgStart);
		jbtnStore = new JButton(imgStore);
		jbtnRank = new JButton(imgRank);
		jbtnChat = new JButton(imgChat);
		jbtnStat = new JButton(imgStat);

		jbtnStart.setBounds(560, 60, 200, 80);
		jbtnStat.setBounds(560, 160, 200, 80);
		jbtnStore.setBounds(560, 260, 200, 80);
		jbtnRank.setBounds(560, 360, 200, 80);
		jbtnChat.setBounds(560, 460, 200, 80);
		
		
		jbtnStart.addActionListener(this);
		jbtnStore.addActionListener(this);
		jbtnRank.addActionListener(this);
		jbtnChat.addActionListener(this);
		jbtnStat.addActionListener(this);

		add(jbtnStart);
		add(jbtnStore);
		add(jbtnRank);
		add(jbtnChat);
		add(jbtnStat);
		add(jlb);
		setResizable(false);
		/////////저장하기////////////
		//여기선 X
		
		
		
		
		
		/////////불러오기////////////
		
		User_DataDAO udd= new User_DataDAO();
		User_DataVO udv = udd.selecttwo(uid);
		System.out.println(uid);
		this.hp = udv.getHp();
		this.money = udv.getMoney();
		this.atk = udv.getAtk();
		this.buck = udv.getBuck();
		this.dis = udv.getDis();
		this.score = udv.getScore();
		this.round = udv.getRound();
		
		System.out.println(hp);
		System.out.println(money);
		System.out.println(atk);
		System.out.println(score);
		// 중앙에 위치 하게 창 설정 
		setBounds(560, 240, 800, 600);
		setVisible(true);
		udd.close();
	}
	public Main() {
		setTitle("디펜스 게임");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		imgMainBackground = new ImageIcon("src/images/mainbackground.png");
		imgStart = new ImageIcon("src/images/start.png");
		imgStore = new ImageIcon("src/images/store.png");
		imgRank = new ImageIcon("src/images/rank.png");
		imgChat = new ImageIcon("src/images/chat.png");
		imgStat = new ImageIcon("src/images/stat.png");
		
		jlb = new JLabel(imgMainBackground);

		jbtnStart = new JButton(imgStart);
		jbtnStore = new JButton(imgStore);
		jbtnRank = new JButton(imgRank);
		jbtnChat = new JButton(imgChat);
		jbtnStat = new JButton(imgStat);

		jbtnStart.setBounds(560, 60, 200, 80);
		jbtnStat.setBounds(560, 160, 200, 80);
		jbtnStore.setBounds(560, 260, 200, 80);
		jbtnRank.setBounds(560, 360, 200, 80);
		jbtnChat.setBounds(560, 460, 200, 80);
		
		
		jbtnStart.addActionListener(this);
		jbtnStore.addActionListener(this);
		jbtnRank.addActionListener(this);
		jbtnChat.addActionListener(this);
		jbtnStat.addActionListener(this);

		add(jbtnStart);
		add(jbtnStore);
		add(jbtnRank);
		add(jbtnChat);
		add(jbtnStat);
		add(jlb);
		setResizable(false);
		/////////저장하기////////////
		User_DataDAO udd= new User_DataDAO();
		User_DataVO udv;
		udd.updateAll(uid, hp, atk, money, buck, dis, score, round);
		
		/////////불러오기////////////
		udv = udd.selecttwo(uid);
		System.out.println(uid);
		this.hp = udv.getHp();
		this.money = udv.getMoney();
		this.atk = udv.getAtk();
		this.dis= udv.getDis();
		this.score = udv.getScore();
		this.round = udv.getRound();
		
		
		
		System.out.println(hp);
		System.out.println(money);
		System.out.println(atk);
		System.out.println(score);
		
		// 중앙에 위치 하게 창 설정 
		setBounds(560, 240, 800, 600);
		setVisible(true);
		udd.close();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == jbtnStore) {
			this.setVisible(false);
			new Store();
		}else if(obj == jbtnStart) {
			this.setVisible(false);
			new GameExplain(this);
		}else if(obj == jbtnChat) {
			new GameChatClient(uid);
		}else if(obj == jbtnStat) {
			this.setVisible(false);
			new Stat();
		}else if(obj == jbtnRank) {
			this.setVisible(false);
			new Rank();
		}
	}
}
