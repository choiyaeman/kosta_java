public class WatchTV {
	public static void main(String[] args) {
		TV tv1;
		tv1 = new TV();
		//tv1.power = true;
		tv1.powerOn();
		tv1.volume = 5;
		for(int i=0; i<10; i++) {
			tv1.volumeUp();
		}
		System.out.println(tv1.volume);//15
		
		//tv1.channel = 11;
		tv1.setChannel(11);
		tv1.channelUp();
		System.out.println(tv1.channel);//12
	}
}
