package single;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class buildTree {
	//���򹹽�������
		public  TreeNode buildTree(List<double[][]> arr)
		{
			
			if(arr.size()==0){
				return null;			
			}
			List<TreeNode> nodes=new ArrayList<>();
			//����Ҷ�ӽڵ�
			for(int i=0;i<arr.size();i++){				
				TreeNode node=new TreeNode(arr.get(i),i);
			nodes.add(node);
			}
			//�����Ҷ�ӽڵ㣺(��ӡ����)
//			System.out.println("��ӡҶ�ӽڵ㣺");
//			for(int i=0;i<nodes.size();i++)
//			{
//				double[][] temp=nodes.get(i).getVal();
//				for(int j=0;j<temp.length;j++)
//				{
//					for(int k=0;k<temp[0].length;k++)
//					{
//						System.out.print(temp[j][k]+" ");
//					}
//					System.out.println("---------------");
//				}
//				System.out.print("Id="+i);
//				System.out.println();
//			}
			if(nodes.size()==1){
				return nodes.get(0);
			}
			List<TreeNode> exNodes=new ArrayList<>();//������תվ
			Iterator<TreeNode> iterator= nodes.iterator();
			//���ڵ�������Ϊ2^n��ʽ
			//�жϽڵ����Ƿ�Ϊ2^n
			//�ο���//https://blog.csdn.net/best_fiends_zxh/article/details/53873212
			int count=0;
			while(((nodes.size()+exNodes.size()) & ((nodes.size()+exNodes.size()) -1)) != 0)
			{
				System.out.println("�ڵ���nodes.size()="+nodes.size()+",exNodes.size()="+exNodes.size());
				TreeNode node1=iterator.next();
				iterator.remove();
				TreeNode node2=iterator.next();								
				iterator.remove();
				
				TreeNode newNode=new TreeNode(conbinePareNode(node1.getVal(),node2.getVal(),count),node1,node2);	
				System.out.println("count="+(++count));
				exNodes.add(newNode);
			}
			nodes.addAll(0,exNodes);
			exNodes.clear();
			
			
			System.out.println("�ڵ�ϲ���ɣ�");
			int count2=0;
			while(nodes.size()!=1){//�ڵ���λ1ʱ���������
				Iterator<TreeNode> ite= nodes.iterator();
				while(ite.hasNext()){//���Ϲ���һ��
					TreeNode node1=ite.next();
					ite.remove();
					TreeNode node2=ite.next();								
					ite.remove();
					
					TreeNode newNode=new TreeNode(conbinePareNode(node1.getVal(),node2.getVal(),count2),node1,node2);
					System.out.println("count2="+(++count2));
					exNodes.add(newNode);
				}
				nodes.addAll(0,exNodes);
				exNodes.clear();
			}		
			System.out.println("buildTree����������");
			return nodes.get(0);
			
		}
		public  double[][] conbinePareNode(double[][] node1,double[][] node2,int count)
		{
			//����node1.length=node2.length,�����ļ��ؼ��ʸ���һ��
			if(node1.length==node2.length)
			{
				double[][] newNode = new double [node1.length][node1[0].length];
				for(int i=0;i<node1.length;i++)
				{
					for(int j=0;j<node1[i].length;j++)
					{
						newNode[i][j]=new buildTree().gongBeiShu(String.valueOf(node1[i][j]),String.valueOf(node2[i][j]));
						if(count==16380)
						{
							System.out.println(node1[i][j]);
							System.out.println(node2[i][j]);
							System.out.println(newNode[i][j]+" ");
						}
						
					}
		
				}
				return newNode;
				
			}else if(node1.length>node2.length)
			{
				double[][] newNode = new double [node1.length][node1[0].length];
				for(int i=0;i<node2.length;i++)
				{
					for(int j=0;j<node2[i].length;j++)
					{
						newNode[i][j]=new buildTree().gongBeiShu(String.valueOf(node1[i][j]),String.valueOf(node2[i][j]));
						if(count==16380)
						{
							System.out.println(node1[i][j]);
							System.out.println(node2[i][j]);
							System.out.println(newNode[i][j]+" ");
						}
					}
					
				}
				//����
				for(int i=node2.length;i<node1.length;i++)
				{
					for(int j=0;j<node1[0].length;j++)
					{
						newNode[i][j]=node1[i][j];
					}
				}
				return newNode;
			}else if(node2.length>node1.length)
			{
				double[][] newNode = new double [node2.length][node2[0].length];
				for(int i=0;i<node1.length;i++)
				{
					for(int j=0;j<node1[i].length;j++)
					{
						newNode[i][j]=new buildTree().gongBeiShu(String.valueOf(node1[i][j]),String.valueOf(node2[i][j]));
						if(count==16380)
						{
							System.out.println(node1[i][j]);
							System.out.println(node2[i][j]);
							System.out.println(newNode[i][j]+" ");
						}
					}
				}
				//����
				for(int i=node1.length;i<node2.length;i++)
				{
					for(int j=0;j<node2[0].length;j++)
					{
						newNode[i][j]=node2[i][j];
					}
				}
				return newNode;
			}
		return null;	
	}
//		public  double gongYueShu(double m, double n){
//		    if(m < n){   //��֤m>n
//		    	double temp = n;
//		        n = m;
//		        m = temp;
//		    }
//		    if(m%n == 0){
//		        return n;
//		    }
//		    return gongYueShu(n,m%n);
//		}
		//�ǵݹ� ��С������ ����ջ���
		public  double gongBeiShu(double n, double m) {
			if(n==0 || m==0)
			{
				return 0.0;
			}
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
		//��С������ bigDecimal
		public  double gongBeiShu(String  str1, String  str2) {
			
			//׼����
			
			BigDecimal n=new BigDecimal(String.valueOf(str1));
			BigDecimal m=new BigDecimal(String.valueOf(str2));
			
			//���:
			if(n.compareTo(BigDecimal.ZERO)==0 || m.compareTo(BigDecimal.ZERO)==0)
			{
				return 0.0;
			}
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
		//��¡һ����
		 public  TreeNode cloneTree(TreeNode root){
			 TreeNode node=null;
		     if(root==null) 
		    	 return null;
		     node=new TreeNode(root.val);
		     node.left=cloneTree(root.left);
		     node.right=cloneTree(root.right);
	         return node;
		    }
		 public List<double[][]> copyIndex(List<double[][]> index)
		 {
			 List<double[][]> resule=new ArrayList<double[][]>();
			 for(int i=0;i<index.size();i++)
			 {
				 double[][] tempIndex=index.get(i);
				 double[][] copyTemp=new double[tempIndex.length][tempIndex[0].length];
				 for(int j=0;j<tempIndex.length;j++)
				 {
					 for(int k=0;k<tempIndex[0].length;k++)
					 {
						 copyTemp[j][k]=tempIndex[j][k];
					 }
				 }
				 resule.add(copyTemp);
			 }
			 return resule;
		 }
		 public void change(TreeNode node)
		 {
			 if(node==null){
					return;
				}
				Stack<TreeNode> s = new Stack<TreeNode>();
				TreeNode p=node;//���ڵ�
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
			        		  p.val[i][j]=1111;
//				        	System.out.print(p.val[i][j]+" "); 
			        	  }
			          }
			          System.out.println();
			          if (p.right != null) {
			              p = p.right;
			          }
			          else p = null;
			     } while(p != null || !s.empty());
		 }
			public static void main(String[] args) throws IOException 
			{
				
				//0.1������genKey
				genKey key=new genKey();
				int m=31;//��ʱ�̶�
				key.setM(m);
				//1����ȡ���еĹؼ���
				List<String> listD =new ArrayList<String>();
				//�ļ����������ļ����ļ��ؼ���
				List<List<String>> listK =new ArrayList<List<String>>();
				List<double[][]> index=new ArrayList<double[][]>();
				fileRead test=new fileRead();
				indexText inTx=new indexText();
//			    test.readFileName("F:/infocoms/3keyword");
			    test.readFileName("D:\\��ʿѧϰ\\ʵ�����ݼ�+��Դ����\\fuzzySearch\\minTest");
				listD=test.getListDocument();
				listK=test.getListKeyword();
				//2�����ؼ��ֱ������
				String[] randLetter={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"};
				double[] prime ={ 2,3,5,7, 11,
				          17,23,29,31,
				          37,41,43,53};
				inTx.setRandLetter(randLetter);
				inTx.setPrime(prime);
				inTx.keywordMain(listK, 10);
				index=inTx.getKeywordVector();
				System.out.println("index.size()="+index.size());
				System.out.println("index������");
				for(int i=0;i<index.size();i++)
				{
					double[][] temp=index.get(i);
					for(int j=0;j<temp.length;j++)
					{
						for(int k=0;k<temp[0].length;k++)
						{
							System.out.print(temp[j][k]+",");
						}
						System.out.println();
					}
					
					System.out.println("-----------------");
				}
				
				//����
				buildTree buildtree =new buildTree();
				TreeNode Tree1 =new TreeNode();
				Tree1=buildtree.buildTree(index);
				
				TreeNode Tree2=new TreeNode();
				Tree2=buildtree.buildTree(buildtree.copyIndex(index));
				
				System.out.println("Tree1�����㷨��");
				Tree1.inOrder(Tree1);
				System.out.println();
				
				System.out.println("Tree2�����㷨��");
				Tree2.inOrder(Tree2);
				
			    buildtree.change(Tree1);
			    System.out.println("change Tree1�����㷨��");
				Tree1.inOrder(Tree1);
				System.out.println();
				
				
				System.out.println("Tree2�����㷨��");
				Tree2.inOrder(Tree2);
			    
				
				
				
				
				
			}

}
