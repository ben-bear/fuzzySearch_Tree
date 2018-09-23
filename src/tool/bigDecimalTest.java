package tool;

import java.math.BigDecimal;

public class bigDecimalTest {
	
	public  double GCD1(String  str1, String  str2) {
		//准备：
		
		BigDecimal n=new BigDecimal(String.valueOf(str1));
		BigDecimal m=new BigDecimal(String.valueOf(str2));
		
		//求解:
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double a=3;
		double b=1;
		for(int i=0;i<90;i++)
		{
			a=a*10;
			b=b*10;
		}
		double c=new bigDecimalTest().GCD1(String.valueOf(a),String.valueOf(b));
		System.out.println(c);

	}

}
