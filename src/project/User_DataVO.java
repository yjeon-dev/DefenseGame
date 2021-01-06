package project;

public class User_DataVO {

	String id;
	String pw;
	String nick;
	int money;
	int hp;
	int atk;
	int dis;

	int buck;
	int score;
	int round;

	public User_DataVO() {

	}

	public User_DataVO(String id, String pw, String nick, int money, int hp, int atk, int dis, int buck, int score,
			int round) {
		super();
		this.id = id;
		this.pw = pw;
		this.nick = nick;
		this.money = money;
		this.hp = hp;
		this.atk = atk;
		this.dis = dis;
		this.buck = buck;
		this.score = score;
		this.round = round;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDis() {
		return dis;
	}

	public void setDis(int dis) {
		this.dis = dis;
	}

	public int getBuck() {
		return buck;
	}

	public void setBuck(int buck) {
		this.buck = buck;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

}