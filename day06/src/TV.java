public class TV {
	boolean power; //전원
	int volume; //음량
	int channel;//채널
	
	void powerOn() {
		power = true;
	}
	void powerOff() {
		power = false;
	}
	void volumeUp() {
		volume++;
	}
	void setChannel(int ch) {
		channel = ch;
	}
	void channelUp() {
		channel++;
	}
}