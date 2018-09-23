package treeBug;

import java.util.ArrayList;
import java.util.List;

public class BuildTree {
	
	public  TreeNode buildNode(List<double[][]> arr)
	{
		TreeNode treeNode=new TreeNode(arr.get(0));
		return treeNode;		
	}	
	
	public static void main(String[] args) 
	{
		
		List<double[][]> index=new ArrayList<double[][]>();
		List<double[][]> index2=new ArrayList<double[][]>();
		double[][] test= {{1,1,1}};	
		double[][] test2=new double[test.length][test[0].length];	
		for(int i=0;i<test[0].length;i++)
		{
			test2[0][i]=test[0][i];
		}
		index.add(test);
		index2.add(test2);
		
				
		//½¨Ê÷ÒÉÎÊ
		BuildTree builTree=new 	BuildTree();
		TreeNode node1=builTree.buildNode(index);
		TreeNode node2=builTree.buildNode(index2);
		System.out.println(node1.val.toString());
		System.out.println(node2.val.toString());
		
		System.out.println("node1:");
		node1.print(node1);
		
		System.out.println("node2:");
		node2.print(node2);
		
		node1.val[0][0]=11;//ÐÞ¸Ä
		System.out.println("change node1:");
		node1.print(node1);
		
		System.out.println("node2:");
		node2.print(node2);
				
	}

}
