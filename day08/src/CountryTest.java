import java.util.Scanner;

import world.asia.Japen;
//import world.asia.*;
import world.asia.Korea;
import world.europe.France;//ctrl+shift+o

public class CountryTest {
	public static void main(String[] args) {
//		world.asia.Korea k;
//		k = new world.asia.Korea();
//		world.asia.Japen j = new world.asia.Japen();
//		world.europe.France f = new world.europe.France();
		
		Korea k = new Korea();		
		Japen j = new Japen();
		France f = new France();
		
		//k.population = 0; //한국의 인구
		k.setPopulation(-100);
		int koreaPopulation = k.getPopulation();
		System.out.println("현재 한국의 인구는 " + koreaPopulation + "명");
		k.language = "일본어"; //한국의 언어
		k.capital = "평양"; //한국의 수도
		
		
		Scanner sc = new Scanner(System.in);
		
	}
}
