
public class Operator {
	public static void main(String[] args) {
		//연산자종류
		/*
		산술연산자 : +, -, *, /, %
		대입연산자 : =, +=, -=, *=, /=, %=
		증감연산자(단항) : ++, --
		비교연산자 : >, >=, <, <=, ==, !=
		논리연산자 : &, &&, |, ||, !
		삼항연산자 : ?: 
		*/
		int num1, num2;
		num1 = (1+2)*3 % 2;
		num2 = num1;
		
		char c = 'A'; //아스키코드값: 65
		System.out.println((char)(c+3));//68
		
		num1 = 2;
		num2 = 3;
		System.out.println(num1/num2);//0
		//System.out.println(num1/0);
		
		short s1,s2,s3;
		s1 = 2; 
		s2 = 3;
		s3 = (short)(s1 + s2);
		System.out.println(s3); //5
		
		num2 = 1;
		num2 += 3;//num2 = num2+3;
		
		num2 = 1;
		num1 = num2++;
		System.out.println(num1);//1
		System.out.println(num2);//2
		
		byte b = 127;
		b++;
		System.out.println(b);//-128
		
		num2 = 1;
		num1 = ++num2;
		System.out.println(num1);//2
		System.out.println(num2);//2
		
		num1=100;
		System.out.println(num1 >= 50); //true
		
		num1++;
		System.out.println(num1%2 == 0);//false
		System.out.println(num1%3 != 0);//true
		
		// AND연산자: &     ~~~이고 ~~~이다
		// OR연산자:  |     ~~~이거나 ~~~이다
		num1=4;
		num2=25;
		System.out.println(num1%2==0 & num2%5==0);//true
		System.out.println(num1%2==1 & num2%5==0);//false
		System.out.println(num1%2==0 & num2%5!=0);//false
		System.out.println(num1%2==1 & num2%5!=0);//false
		
		
		System.out.println(num1%2==0 | num2%5==0);//true
		System.out.println(num1%2==1 | num2%5==0);//true
		System.out.println(num1%2==0 | num2%5!=0);//true
		System.out.println(num1%2==1 | num2%5!=0);//false
		
		System.out.println(!(num1%2==0 & num2%5==0));//false
			
		System.out.println(num1%2==0?"짝수":"홀수");
		
		//double r = Math.random();
		//System.out.println(r);
		
		//Math.random()메서드의 반환값범위를 1<=  <4로 조절
		int r = (int)(Math.random()*3 +1); 
		          // 1<=  <4
		System.out.println(r);
		/*
		System.out.print(r==1?"가위":"");
		System.out.print(r==2?"바위":"");
		System.out.println(r==3?"보":"");
		*/
		System.out.println((r==1)?"가위":(r==2)?"바위":"보");
		
		int x,y,z;
		x=10; y=20;
		z = (++x) + (y--);
		System.out.println(z); //31
		
		
		
	}
}