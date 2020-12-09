public class Operator {

	public static void main(String[] args) {
		// 연산자종류
		/* 
		산술연산자 : +, -, *, /, %
		대입연산자 : =, +=, -=, *=, /=
		증감연산자(단항) : ++, --
		비교연산자 : >, >=, <, <=, ==, !=
		논리연산자 : &, &&, |, ||, !
		삼항연산자 : ?:
		*/
		int num1, num2;
		num1 = (1+2)*3;
		// num1 = num2; //num2가 초기화 되지 않았으므로 오류..
		num2 = num1;
		
		char c = 'A'; //아스키코드값: 65
		System.out.println(c+3); //68
		System.out.println((char)(c+3)); //D
		
		num1 = 2;
		num2 = 3;
		System.out.println(num1/num2); //0
		//System.out.println(num1/0); // 0으로 나눌수없다 오류..
		System.out.println(num1%num2); //2
		
		short s1, s2, s3;
		s1 = 2;
		s2 = 3;
		//s3 = s1 + s2; //오류...s1이 연산할때 잠깐 int가 되고 s2인 short를 만나면                   서 int+short가된다.. 무조건 int가 된다..그러므로 강제형 변환 필요
		s3 = (short)(s1 + s2);
		System.out.println(s3); //5
		
		num2 = 1;
		num2 += 3;
		
		System.out.println("---------후위 연산자--------------");
		num2 = 1;
		num1 = num2++;
		System.out.println(num1); //1
		System.out.println(num2); //2
		
		byte b = 127;
		b++;
		System.out.println(b); //-128  값의 범위를 넘어서면 최솟값으로 이동하게 된다.
		
		System.out.println("---------전위 연산자--------------");
		num2 = 1;
		num1 = ++num2;
		System.out.println(num1); //2
		System.out.println(num2); //2
		
		System.out.println("---------비교 연산자--------------");
		num1=100;
		System.out.println(num1 >= 50); //true
		
		num1++;
		System.out.println(num1%2 == 0); //false
		System.out.println(num1%3 != 0); //true
		
		// And연산자: &	~~~이고 ~~~이다
		// OR연산자: |	~~~이거나 ~~~이다
		System.out.println("---------And 연산자--------------");
		num1=4;
		num2=25;
		System.out.println(num1%2==0 & num2%5==0); //true
		System.out.println(num1%2==1 & num2%5==0); //false
		System.out.println(num1%2==0 & num2%5!=0); //false
		System.out.println(num1%2==1 & num2%5!=0); //false
		
		System.out.println("---------OR 연산자--------------");
		System.out.println(num1%2==0 | num2%5==0); //true
		System.out.println(num1%2==1 | num2%5==0); //true
		System.out.println(num1%2==0 | num2%5!=0); //true
		System.out.println(num1%2==1 | num2%5!=0); //false
		
		System.out.println(!(num1%2==0 & num2%5==0)); //true
		
		System.out.println("---------삼항 연산자--------------");
		System.out.println(num1%2==0?"짝수":"홀수");
		
		//println 자바에서 제공되는 기본 라이브러리 jre시스템을 열어서 각 라이브러                   리들을 확인 할 수 있다..
		System.out.println("---------랜덤 함수--------------");
		double r = Math.random();
		System.out.println(r);
		
		//Math random() 최대값 지정 : *
		//Math.random() * 최대값
		double z = Math.random()*3; // 0.0<= <3.0
		System.out.println(z);
		
		//Math.random()메서드의 반환값범위를 1<=<4로 조절
		// 최소값 + 최대값 동시 지정
		//( Math.random () * (최대값 - 최소값) ) + 최소값
		int k = (int)(Math.random()*3+1); // 1<= <4
		System.out.println(k);
		System.out.println("---------가위바위보 랜덤--------------");
		System.out.println(k);
//		System.out.print(k==1?"가위":"");
//		System.out.print(k==2?"바위":"");
//		System.out.print(k==3?"보":"");
		System.out.println((k==1)?"가위":(k==2)?"바위":"보");
		
		//Ex2)
		int x= 10;
		int y= 20;
		int zz= ++x + y--;
		System.out.println(zz); // 31
	}

}


