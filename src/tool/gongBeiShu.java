package tool;

import java.math.BigDecimal;
import java.math.BigInteger;

public class gongBeiShu {
	
	public static double getGongYueShu(double m, double n){
	    if(m < n){   //保证m>n
	    	double temp = n;
	        n = m;
	        m = temp;
	    }
	    if(m%n == 0){
	        return n;
	    }
	    return getGongYueShu(n,m%n);
	}
	//最小公倍数
	public static double getGongBeiShu(double m, double n){
	    return m*n/getGongBeiShu(m,n);
	}
	public  double GCD1(long n, long m) {
		long num1=n;
		long num2=m;
		if (n < m) 
		{
			long temp=n;
			n=m;
			m=temp;
		}
		while ((n % m) != 0) {
		n=n%m;
		{
			long temp=n;
			n=m;
			m=temp;
		}
		}
		return (num1*num2)/m;
		}
	
	
	public  double GCD1(BigDecimal  n, BigDecimal  m) {
		BigDecimal num1=n;
		BigDecimal num2=m;
		if (n.compareTo(m)==-1) 
		{
			BigDecimal temp=n;
			n=m;
			m=temp;
		}		
	  while (n.divideAndRemainder(m)[1].compareTo(BigDecimal.ZERO)!=0) {
		n=n.divideAndRemainder(m)[1];
		{
			BigDecimal temp=n;
			n=m;
			m=temp;
		}
		}
	    BigDecimal temp=num1.multiply(num2);    
		return temp.divide(m,10,BigDecimal.ROUND_HALF_UP).doubleValue();

	}
	public static double change(String str1,String str2)
	{		
		BigDecimal num1=new BigDecimal(String.valueOf(str1));
		BigDecimal num2=new BigDecimal(String.valueOf(str2));
	
		return new gongBeiShu().GCD1(num1,num2);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double a=3;
		double b=1;
		for(int i=0;i<90;i++)
		{
			a=a*10;
			b=b*10;
		}
		double c=change(String.valueOf(a),String.valueOf(b));
		System.out.println(c);
		
		
		
		

	}

}
