public class DataType{
    public static void main(String[] args){
        byte b; //���� ���� -128~127
        //b = 128;
        b = -127; //�� ����
        System.out.println(b);    
        b = 0;
        System.out.println(b);

        long l = 10L; //��������� ����
        l = 12345678901L;

        double d;
        d = 12.34567;
        
        //���ͷ� ǥ����
        System.out.println(10); //int
        System.out.println(10L); //long
        System.out.println(10.4F);//float
        System.out.println(10.4); //double

        char c; //2byte����� unicode�������ջ��
        c = '��'; 
        System.out.println(c); //��
        c = 44032; 	
        System.out.println(c); 

        c=97; //a
        System.out.println(c); 

        c=45;
        System.out.println(c); 

        boolean flag;
        flag = true;
        //flag = 1; //������ ����
        flag = false;
        //flag = 0; //������ ����

    }
}