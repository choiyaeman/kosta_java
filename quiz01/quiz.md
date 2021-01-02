# Quiz 

1.

![1](https://user-images.githubusercontent.com/63957819/103460492-f2734200-4d59-11eb-90c3-1a9290c87e5c.jpg)

2.

![2](https://user-images.githubusercontent.com/63957819/103460494-f3a46f00-4d59-11eb-8d63-9bed19c82348.jpg)

3.

![3](https://user-images.githubusercontent.com/63957819/103460495-f43d0580-4d59-11eb-8314-7219bd6df054.jpg)

4.

![4](https://user-images.githubusercontent.com/63957819/103460496-f43d0580-4d59-11eb-8d8b-e816dad8e3e1.jpg)

**5**.

![5](https://user-images.githubusercontent.com/63957819/103460497-f4d59c00-4d59-11eb-912d-bb48be080c22.jpg)

= 하나면 대입 연산자이다. 그러므로 조건 비교를 할 수 없다. ==이 조건 비교이다.

**6.**

![6](https://user-images.githubusercontent.com/63957819/103460498-f4d59c00-4d59-11eb-8b86-7b9142b67da8.jpg)

i=0으로 선언되어 있는데 좌측부터 i≠0이라는 조건이 거짓이므로 우측은 아예 수행이 안된다. 만에하나 좌측이 true이더라도 해도 null.length로 nullpointException발생한다.

7.

![7](https://user-images.githubusercontent.com/63957819/103460499-f56e3280-4d59-11eb-9c66-55a897a312c6.jpg)

**8.**

![8](https://user-images.githubusercontent.com/63957819/103460501-f56e3280-4d59-11eb-82f0-c321a6b2c915.jpg)

1번이 오답인 이유는 a에서 이미 true이므로 첫 번째  System.out.println(A); 값을 출력하고 마친다. 그러므로 밑에 else if가 실행이 안된다. 2번도 마찬가지로 a가 true이므로 A가 출력. 4번은 a,b 모두 false라면 not B가 출력 된다.

9.

![9](https://user-images.githubusercontent.com/63957819/103460502-f606c900-4d59-11eb-9b0d-6bbaf972a94e.jpg)

**10.**

![10](https://user-images.githubusercontent.com/63957819/103460503-f69f5f80-4d59-11eb-8157-f4d5ef38cecb.jpg)

protected는 class 선언 앞에 못쓴다.

**11.**

![11](https://user-images.githubusercontent.com/63957819/103460504-f69f5f80-4d59-11eb-8f05-1ea7cc40b811.jpg)

20을 출력하기 위해서는 현재 사용 중인 객체 i를 출력해야 한다. 그러므로 this.i가 정답이다. 그냥 i는 바로 위에 있는 지역변수 i를 가지고 온다. 

12.

![12](https://user-images.githubusercontent.com/63957819/103460505-f737f600-4d59-11eb-831f-0cac13b2eede.jpg)

13.

![13](https://user-images.githubusercontent.com/63957819/103460506-f737f600-4d59-11eb-997f-2e90a4c65197.jpg)

**14.**

![14](https://user-images.githubusercontent.com/63957819/103460507-f7d08c80-4d59-11eb-95cc-f39a043285b0.jpg)

abstract은 재정의를 해야 하고 final은 끝이라는 의미로 재정의 금지라는 의미이다.⇒abstract은 final과 같이 사용(x) 

**15.**

![15](https://user-images.githubusercontent.com/63957819/103460509-f7d08c80-4d59-11eb-8045-ab2eb0701ed9.jpg)

super(i);→ 매개변수 하나 있는 부모 생성자를 호출하여 MySub객체 안에는 2*2=4가 저장되지만 코드에는 i값을 출력하는 거기 때문에 Mysub생성자의 i값이므로 결과 값은 2가 출력 된다.  

**16.**

![16](https://user-images.githubusercontent.com/63957819/103460510-f8692300-4d59-11eb-8fb8-2e4dd0324b54.jpg)

매개변수가 없으면 자동으로 매개변수 없는 super();가 호출되고 MySuper클래스 안에 매개변수 없는 MySuper(){} 생성자가 있어야 한다.

17.

![17](https://user-images.githubusercontent.com/63957819/103460511-f901b980-4d59-11eb-9cf1-95b211ea4e9f.jpg)

18.

![18](https://user-images.githubusercontent.com/63957819/103460512-f901b980-4d59-11eb-85f6-e4c3185a4186.jpg)

**19.**

```java
//다음 숫자들을 SELECTION정렬기법을 이용하여 내림차순 정렬하시오.
//int[] arr ={4, 6, 1, 9, 5, 3, 7};

public class Quiz19 {
	public static void main(String[] args) {
		int [ ] arr = { 4, 6, 1, 9, 5, 3, 7};
		int size = arr.length;
		//이곳을 완성하시오.
		//SELECTION SORT 내림차순
		for(int i=0; i<size-1; i++) {
			for(int j=i+1; j<size; j++) {
				if(arr[i] < arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}

		//BUBBLE SORT 내림차순
//		int bubbleSize = size-1;
//		for(int i=0; i<bubbleSize; i++) {
//			for(int j=0; j<bubbleSize-i; j++) {
//				if(arr[i] > arr[j]) {
//					int temp = arr[i];
//					arr[i] = arr[j];
//					arr[j] = temp;
//				}
//			}
//		}
		for(int i=0; i<arr.length; i++){
		    System.out.print(arr[i]);   //arr변수 오타!
		   System.out.print("\t");
		}
		System.out.println();

	}

}
```

**20.**

```java
public class Quiz20 {
	public static void main(String[] args) {
		int [ ][ ]arr = new int[4][3];
		/*
		 * [0][0]에는 1, 
[1][0]에는 2, 
[2][0]에는 3, 
[3][0]에는 4,
[0][1]에는 5, 
[1][1]에는 6,
[2][1]에는 7,
[3][1]에는 8
[0][2]에는 9
[1][2]에는 10.
[2][2]에는 11,
[3][2]에는 12가 저장되도록 한다 
		 */
		  //TODO이곳을 완성하시오.
		int num=1;
		for(int col=0; col<arr[0].length; col++) {
			for(int row=0; row<arr.length; row++) {
				arr[row][col] = num++;
			}
		}
		for(int i=0; i<arr.length; i++){
		   for(int j=0; j<arr[i].length; j++){
		      System.out.print(arr[ i ][ j ] );
		  }
		  System.out.println( );
		}

	}
}
```
