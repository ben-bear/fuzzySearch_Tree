package treeBug;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class conTree {
	public double[][] val;
	public conTree left;
	public conTree right;
	
	public conTree()
	{
		
	}
	public conTree(double[][] val){
		this.val=val;
		this.left=null;
		this.right=null;
	}
	public conTree(double[][] val,conTree l,conTree r){
		this.val=val;
		this.left=l;
		this.right=r;
	}
	public double[][] getVal() {
		return val;
	}
	public void setVal(double[][] val) {
		this.val = val;
	}
	public conTree getLeft() {
		return left;
	}
	public void setLeft(conTree left) {
		this.left = left;
	}
	public conTree getRight() {
		return right;
	}
	public void setRight(conTree right) {
		this.right = right;
	}
	//�������
	public  void inOrder(conTree node){
		if(node==null){
			return;
		}
		inOrder(node.left);
		for(int i=0;i<node.getVal().length;i++)
		{
			for(int j=0;j<node.getVal()[0].length;j++)
			{
				System.out.print(node.getVal()[i][j]+" ");
			}
		}
		System.out.println();
		inOrder(node.right);
		}
	//�ǵݹ���� �������
	public void inOrder_norecursive(conTree node)
	{
		if(node==null){
			return;
		}
		Stack<conTree> s = new Stack<conTree>();
		conTree p=node;//���ڵ�
		do {
	          while (p != null) {
	             s.push(p);
	             p = p.left;
	          }
	          p = s.pop();
	          for(int i=0;i<p.val.length;i++)
	          {
	        	  for(int j=0;j<p.val[0].length;j++)
	        	  {
		        	System.out.print(p.val[i][j]+" "); 
	        	  }
	          }
	          System.out.println();
	          if (p.right != null) {
	              p = p.right;
	          }
	          else p = null;
	     } while(p != null || !s.empty());
		
	}
	
	//�������
	public  void preOrder(conTree node){
		if(node==null){
			return;
		}
		System.out.print(node.getVal()+" ");
		preOrder(node.left);			
		preOrder(node.right);
	}
	//���򹹽�������
	public  conTree buildTree(List<double[][]> arr)
	{
		
		if(arr.size()==0){
			return null;			
		}
		List<conTree> nodes=new ArrayList<>();
		//����Ҷ�ӽڵ�
		for(int i=0;i<arr.size();i++){				
		conTree node=new conTree(arr.get(i));
		nodes.add(node);
		}
		//�����Ҷ�ӽڵ㣺(��ӡ����)
		System.out.println("��ӡҶ�ӽڵ㣺");
		for(int i=0;i<nodes.size();i++)
		{
			double[][] temp=nodes.get(i).getVal();
			for(int j=0;j<temp.length;j++)
			{
				for(int k=0;k<temp[0].length;k++)
				{
					System.out.print(temp[j][k]+" ");
				}
			}
			System.out.println();
		}
		if(nodes.size()==1){
			return nodes.get(0);
		}
		List<conTree> exNodes=new ArrayList<>();//������תվ
		Iterator<conTree> iterator= nodes.iterator();
		//���ڵ�������Ϊ2^n��ʽ
		//�жϽڵ����Ƿ�Ϊ2^n
		//�ο���//https://blog.csdn.net/best_fiends_zxh/article/details/53873212
		while(((nodes.size()+exNodes.size()) & ((nodes.size()+exNodes.size()) -1)) != 0)
		{
			conTree node1=iterator.next();
			iterator.remove();
			conTree node2=iterator.next();								
			iterator.remove();
			
			conTree newNode=new conTree(conbinePareNode(node1.getVal(),node2.getVal()),node1,node2);	
			exNodes.add(newNode);
		}
		nodes.addAll(0,exNodes);
		exNodes.clear();
		
		while(nodes.size()!=1){//�ڵ���λ1ʱ���������
			Iterator<conTree> ite= nodes.iterator();
			while(ite.hasNext()){//���Ϲ���һ��
				conTree node1=ite.next();
				ite.remove();
				conTree node2=ite.next();								
				ite.remove();
				
				conTree newNode=new conTree(conbinePareNode(node1.getVal(),node2.getVal()),node1,node2);
				exNodes.add(newNode);
			}
			nodes.addAll(0,exNodes);
			exNodes.clear();
		}		
		return nodes.get(0);
		
	}
	public  double[][] conbinePareNode(double[][] node1,double[][] node2)
	{
		double[][] newNode = new double [node1.length][node1[0].length];
		for(int i=0;i<node1.length;i++)
		{
			for(int j=0;j<node1[i].length;j++)
			{
				newNode[i][j]=new conTree().gongBeiShu(node1[i][j],node2[i][j]);
			}
		}
		return newNode;
	}
	public  double gongYueShu(double m, double n){
	    if(m < n){   //��֤m>n
	    	double temp = n;
	        n = m;
	        m = temp;
	    }
	    if(m%n == 0){
	        return n;
	    }
	    return gongYueShu(n,m%n);
	}
	//�ǵݹ� ��С������ ����ջ���
	public  double gongBeiShu(double n, double m) {
		double num1=n;
		double num2=m;
		if (n < m) 
		{
			double temp=n;
			n=m;
			m=temp;
		}
		while ((n % m) != 0) {
		n=n%m;
		{
			double temp=n;
			n=m;
			m=temp;
		}
		}
		return (num1*num2)/m;
		}
	//��С������
//	public static double gongBeiShu(double m, double n){
//	    return m*n/gongYueShu(m,n);
//	}
	//��¡һ����
	 public  conTree cloneTree(conTree root){
		 conTree node=null;
	     if(root==null) 
	    	 return null;
	     node=new conTree(root.val);
	     node.left=cloneTree(root.left);
	     node.right=cloneTree(root.right);
         return node;
	    }
	
	

}
