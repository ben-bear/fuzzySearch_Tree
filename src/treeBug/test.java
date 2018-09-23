package treeBug;

import java.util.ArrayList;
import java.util.List;

public class test {
	public static void main(String[] agrs)
	{
		
		double[][] b= {{1,2},{2,3}};
		double[][] a=new double[1][2];
		List<double[][]> aList=new ArrayList<double[][]>();
		List<double[][]> bList=new ArrayList<double[][]>();
		aList.add(a);
		bList.add(b);
		System.out.println(aList.toString());
		System.out.println(bList.toString());
		aList=bList;
		System.out.println(aList.toString());
		System.out.println(bList.toString());
		
	}

}
