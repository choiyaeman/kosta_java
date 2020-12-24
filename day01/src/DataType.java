public class DataType{
    public static void main(String[] args){
        byte b; //변수 선언 -128~127
        //b = 128;
        b = -127; //값 대입
        System.out.println(b);    
        b = 0;
        System.out.println(b);

        long l = 10L; //변수선언과 대입
        l = 12345678901L;

        double d;
        d = 12.34567;
        
        //리터럴 표현법
        System.out.println(10); //int
        System.out.println(10L); //long
        System.out.println(10.4F);//float
        System.out.println(10.4); //double

        char c; //2byte기반의 unicode문자조합사용
        c = '가'; 
        System.out.println(c); //가
        c = 44032; 	
        System.out.println(c); 

        c=97; //a
        System.out.println(c); 

        c=45;
        System.out.println(c); 

        boolean flag;
        flag = true;
        //flag = 1; //컴파일 오류
        flag = false;
        //flag = 0; //컴파일 오류

    }
}