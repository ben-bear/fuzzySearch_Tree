package tool;

public class test {
	public int searchStandard(double[][] value,String model,double accracy)
	{
//		double accracy = 0.01;//���ȿ���
		if(model=="and")
		{
		    double tempElement;
			int flagCount=0;//ÿһ�������ĸ���
			//ÿһ�У�����һ��������ֹͣ�����ж�
			for(int j=0;j<value[0].length;j++)
			{
				for(int k=0;k<value.length;k++)
				{
					tempElement=value[k][j];
					System.out.println("tempElement="+tempElement);
					System.out.println("Math.round(tempElement)="+Math.round(tempElement));
					System.out.println("Math.abs(tempElement-Math.round(tempElement))="+Math.abs(tempElement-Math.round(tempElement)));
					if(Math.abs(tempElement-Math.round(tempElement))<accracy)
				     {
						System.out.println("enter");
						flagCount++;
						break;
					}
				}		
			}
				if(flagCount>=value[0].length)
				{
					return 1;
				}else
				{
					return 0;
			}
		}else if(model=="or")
		{
//			double accracy = 0.000001;//���ȿ���
			double tempElement ;
			int flagCount=0;//ÿһ�������ĸ���
			//ÿһ�У�����һ��������ֹͣ�����ж�
			for(int j=0;j<value[0].length;j++)
			{
				for(int k=0;k<value.length;k++)
				{
					tempElement=value[k][j];
					if(Math.abs(tempElement-Math.round(tempElement))<accracy )
				   {
						flagCount++;
						break;
				   }
			    }		
			}
				if(flagCount>=1)
				{
					return 1;
				}else 
				{
					return 0;
			}
		}
		return 1;
	}
	public static void judg(double value)
	{
		if(value%1==0)
		{
			System.out.println("������");
		}else
		{
			System.out.println("����������");
		}
	}
	public static void main(String[] args)
	{
		double[][] value= {{6.752334268585404E23}};
//		double[][] value= {{1.2}};
		System.out.println(new test().searchStandard(value,"and",0.001));
		judg(1.00001);
		System.out.println(((5+1)/3)-1);
	
	//	System.out.println("Math.max="+Math.max);
	}

}
