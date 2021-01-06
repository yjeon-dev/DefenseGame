package round3;

public class boomTr extends Thread{
	int x,y,disxy,cnt;
	R1GameUi gu;
	
	boomTr(Object obj,int x,int y,int disxy,int cnt){
		gu = (R1GameUi)obj;
		this.cnt = cnt;
		this.x = x;
		this.y = y;
		this.disxy = disxy;
	}
	
	

	@Override
	public void run() {
		gu.boomL[cnt].setLocation(x-disxy/2,y-disxy/2);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gu.boomL[cnt].setLocation(5000,5000);
	}
}
