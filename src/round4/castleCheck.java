package round4;

public class castleCheck extends Thread {
	boolean flag = true;
	int hp;
	R1GameUi gu;
	int i;
	public castleCheck(Object obj) {
		gu = (R1GameUi)obj;
	}

	@Override
	public void run() {
		while (flag) {
			if (hp != gu.castleHp)
				hp = gu.castleHp;
			if (hp <= 0) {
				gu.Lose();
				flag = false;
			}
			
			
			try {
				sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
