package project;

public class ProjectVO {
	String id;
	String name;
	String pwd;
	int money;
	int hp;
	int atk;
	int dis;
	int buck;
	int score;
	int round;

	public ProjectVO() {

	}

	public ProjectVO(String id, String name, String pwd, int money, int hp, int atk, int dis, int buck, int score,
			int round) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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