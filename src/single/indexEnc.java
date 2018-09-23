package single;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Jama.Matrix;

public class indexEnc {	
	public TreeNode encIndexRoot1;//������ּ��ܵ�һ����
	public TreeNode encIndexRoot2;//������ּ��ܵڶ�����
	
	public TreeNode getEncIndexRoot1() {
		return encIndexRoot1;
	}

	public void setEncIndexRoot1(TreeNode encIndexRoot1) {
		this.encIndexRoot1 = encIndexRoot1;
	}

	public TreeNode getEncIndexRoot2() {
		return encIndexRoot2;
	}

	public void setEncIndexRoot2(TreeNode encIndexRoot2) {
		this.encIndexRoot2 = encIndexRoot2;
	}

	public  TreeNode enc_Tree1(TreeNode root,int m,Matrix encMatrix)
	{
		//��һ�������
		if(root==null){
			return null;
		}
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode node=root;//���ڵ�
		TreeNode copyNode=node;
		do {
	          while (node != null) {
	             s.push(node);
	             node = node.left;
	          }
	          node = s.pop();

	          //����s_random_index�����ԭ�������д���  ��+������
	          for(int i=0;i<node.val.length;i++)
	          {
	        	  for(int j=0;j<m;j++)
	        	  {
	        		  if(genKey.s[j]==1)
	        		  {
	        			//  node.val[i][j]=0.5*node.val[i][j]+genKey.s_random_index[j];
	        			  node.val[i][j]=node.val[i][j];
	        		  }else
	        		  {
	        			  node.val[i][j]=node.val[i][j];
	        		  }
	        	  }
	          }
//	           System.out.println("I1����ǰ��");
//	           for(int i=0;i<node.val.length;i++)
//		          {
//		        	  for(int j=0;j<m;j++)
//		        	  {
//		        		  System.out.print(node.val[i][j]+",");
//		        	  }
//		        	  System.out.println();
//		          }
//	           System.out.println();
	          //���� I1*M1	          
	          node.val=new Matrix(node.val).times(encMatrix).getArray();
//	          System.out.println("I1���ܺ�");
//	           for(int i=0;i<node.val.length;i++)
//		          {
//		        	  for(int j=0;j<m;j++)
//		        	  {
//		        		  System.out.print(node.val[i][j]+",");
//		        	  }
//		        	  System.out.println();
//		          }
//	           System.out.println();
	
	          if (node.right != null) {
	        	  node = node.right;
	          }
	          else node = null;
	     } while(node != null || !s.empty());
		
		encIndexRoot1=copyNode;
		return encIndexRoot1;
	}
	
	//�ڶ������ļ��ܴ���
	public  TreeNode enc_Tree2(TreeNode root,int m,Matrix encMatrix)
	{
		//��һ�������
		if(root==null){
			return null;
		}
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode node=root;//���ڵ�
		TreeNode copyNode=node;
		do {
	          while (node != null) {
	             s.push(node);
	             node = node.left;
	          }
	          node = s.pop();
	        //  System.out.print(p.val+" ");
//	          for(int i=0;i<node.val.length;i++)
//	          {
//	        	  for(int j=0;j<node.val[0].length;j++)
//	        	  {
//	        		 System.out.print(node.val[i][j]+" "); 
//	        	  }
//	          }
//	          System.out.println();
	          //����s_random_index�����ԭ�������д���  ��-������
	          for(int i=0;i<node.val.length;i++)
	          {
	        	  for(int j=0;j<m;j++)
	        	  {
	        		  if(genKey.s[j]==1)
	        		  {
	        			//  node.val[i][j]=0.5*node.val[i][j]-genKey.s_random_index[j];
	        			  node.val[i][j]=0;
	        		  }else
	        		  {
	        			  node.val[i][j]=node.val[i][j];
	        		  }
	        	  }
	          }
//	          System.out.println("I2����ǰ��");
//	           for(int i=0;i<node.val.length;i++)
//		          {
//		        	  for(int j=0;j<m;j++)
//		        	  {
//		        		  System.out.print(node.val[i][j]+",");
//		        	  }
//		        	  System.out.println();
//		          }
//	           System.out.println();
	          
//	              ���� I2*M2
	          node.val=new Matrix(node.val).times(encMatrix).getArray();
//	          System.out.println("I2���ܺ�");
//	           for(int i=0;i<node.val.length;i++)
//		          {
//		        	  for(int j=0;j<m;j++)
//		        	  {
//		        		  System.out.print(node.val[i][j]+",");
//		        	  }
//		        	  System.out.println();
//		          }
//	           System.out.println();
	          if (node.right != null) {
	        	  node = node.right;
	          }
	          else node = null;
	     } while(node != null || !s.empty());
		
		encIndexRoot2=copyNode;
		return encIndexRoot2;
		
	}
	public static void main(String[] args)throws IOException 
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
//	    test.readFileName("F:/infocoms/3keyword");
	    test.readFileName("D:\\��ʿѧϰ\\ʵ�����ݼ�+��Դ����\\fuzzySearch\\minTest");
		listD=test.getListDocument();
		listK=test.getListKeyword();
		//2�����ؼ��ֱ������
		String[] randLetter={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"};
		double[] prime ={ 2, 3,5,7,11,
						  13,17,19,23,29};
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
			}
			System.out.println();
		}
		//����
		buildTree buildtree =new buildTree();
		TreeNode demonTree1 =new TreeNode();
		
		demonTree1=	buildtree.buildTree(index);//��һ����
		System.out.println("�����㷨��");
		demonTree1.inOrder_norecursive(demonTree1);


		TreeNode demonTree2=buildtree.buildTree(buildtree.copyIndex(index));//�ڶ�����
		System.out.println();
		demonTree2.inOrder_norecursive(demonTree2);
		
		//�����ļ�����
		//������Կkey
		
		Matrix matrix1=key.genMatri();
		Matrix inver_matrix1=key.genInverMatrix(matrix1);
		Matrix matrix2=key.genMatri();
		Matrix inver_matrix2=key.genInverMatrix(matrix2);
		
//		��ӡ
		System.out.println("s���飺");
		for(int i=0;i<m;i++)
		{
			System.out.print(key.s[i]+",");
		}
		System.out.println();
		System.out.println("s_random_index���飺");
		for(int i=0;i<m;i++)
		{
			System.out.print(key.s_random_index[i]+",");
		}
		System.out.println();
		
		//����
		indexEnc index_Enc=new indexEnc();
		
		index_Enc.encIndexRoot1=index_Enc.enc_Tree1(demonTree1,m,matrix1);
		index_Enc.encIndexRoot2=index_Enc.enc_Tree2(demonTree2,m,matrix2);
		
		//�������ܺ��indexTree
		System.out.println("�����㷨encIndexRoot1��");
		demonTree1.inOrder(index_Enc.encIndexRoot1);
		
		System.out.println("�����㷨encIndexRoot2��");
		demonTree2.inOrder(index_Enc.encIndexRoot2);	
	}

}
