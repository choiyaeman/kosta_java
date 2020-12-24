package world.asia;

public class Korea {
	//public int population; //인구
	private int population; //인구
	String language; //언어
	public String capital; //수도
	void k() {
		population = 10;
		language = "한국어";
		capital = "서울";
	}
	public void setPopulation(int population) {
		if(population <= 0 ) {
			System.out.println("인구는 1이상이어야합니다");
			return;
		}
		this.population = population;
	}
	public int getPopulation() {
		return population;
	}
	
	
}
