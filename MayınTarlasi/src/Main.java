public class Main implements Runnable {
	
	KullaniciAraYuzu gui = new KullaniciAraYuzu();

	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

	@Override
	public void run() {
		while(true) {
			gui.repaint();
			if(gui.resetter == false) {
				gui.checkVictoryStatus();
				//System.out.println("Victory: "+gui.kazanmak+" Defeat: "+gui.kaybetmek);
			}
		}
	}

}
