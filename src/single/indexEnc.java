package single;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Jama.Matrix;

public class indexEnc {	
	public TreeNode encIndexRoot1;//索引拆分加密第一棵树
	public TreeNode encIndexRoot2;//索引拆分加密第二棵树
	
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
		//第一步：拆分
		if(root==null){
			return null;
		}
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode node=root;//根节点
		TreeNode copyNode=node;
		do {
	          while (node != null) {
	             s.push(node);
	             node = node.left;
	          }
	          node = s.pop();

	          //根据s_random_index数组对原向量进行处理  “+”处理
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
//	           System.out.println("I1加密前：");
//	           for(int i=0;i<node.val.length;i++)
//		          {
//		        	  for(int j=0;j<m;j++)
//		        	  {
//		        		  System.out.print(node.val[i][j]+",");
//		        	  }
//		        	  System.out.println();
//		          }
//	           System.out.println();
	          //加密 I1*M1	          
	          node.val=new Matrix(node.val).times(encMatrix).getArray();
//	          System.out.println("I1加密后：");
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
	
	//第二颗树的加密处理
	public  TreeNode enc_Tree2(TreeNode root,int m,Matrix encMatrix)
	{
		//第一步：拆分
		if(root==null){
			return null;
		}
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode node=root;//根节点
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
	          //根据s_random_index数组对原向量进行处理  “-”处理
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
//	          System.out.println("I2加密前：");
//	           for(int i=0;i<node.val.length;i++)
//		          {
//		        	  for(int j=0;j<m;j++)
//		        	  {
//		        		  System.out.print(node.val[i][j]+",");
//		        	  }
//		        	  System.out.println();
//		          }
//	           System.out.println();
	          
//	              加密 I2*M2
	          node.val=new Matrix(node.val).times(encMatrix).getArray();
//	          System.out.println("I2加密后：");
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
		//0.1先设置genKey
		genKey key=new genKey();
		int m=31;//暂时固定
		key.setM(m);

		//1、读取所有的关键字
		List<String> listD =new ArrayList<String>();
		//文件夹下所有文件的文件关键字
		List<List<String>> listK =new ArrayList<List<String>>();
		List<double[][]> index=new ArrayList<double[][]>();
		fileRead test=new fileRead();
		indexText inTx=new indexText();
//	    test.readFileName("F:/infocoms/3keyword");
	    test.readFileName("D:\\博士学习\\实验数据集+开源代码\\fuzzySearch\\minTest");
		listD=test.getListDocument();
		listK=test.getListKeyword();
		//2、将关键字变成索引
		String[] randLetter={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"};
		double[] prime ={ 2, 3,5,7,11,
						  13,17,19,23,29};
		inTx.setRandLetter(randLetter);
		inTx.setPrime(prime);
		inTx.keywordMain(listK, 10);
		index=inTx.getKeywordVector();
		System.out.println("index.size()="+index.size());
		System.out.println("index向量：");
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
		//建树
		buildTree buildtree =new buildTree();
		TreeNode demonTree1 =new TreeNode();
		
		demonTree1=	buildtree.buildTree(index);//第一棵树
		System.out.println("遍历算法：");
		demonTree1.inOrder_norecursive(demonTree1);


		TreeNode demonTree2=buildtree.buildTree(buildtree.copyIndex(index));//第二棵树
		System.out.println();
		demonTree2.inOrder_norecursive(demonTree2);
		
		//加密文件索引
		//产生密钥key
		
		Matrix matrix1=key.genMatri();
		Matrix inver_matrix1=key.genInverMatrix(matrix1);
		Matrix matrix2=key.genMatri();
		Matrix inver_matrix2=key.genInverMatrix(matrix2);
		
//		打印
		System.out.println("s数组：");
		for(int i=0;i<m;i++)
		{
			System.out.print(key.s[i]+",");
		}
		System.out.println();
		System.out.println("s_random_index数组：");
		for(int i=0;i<m;i++)
		{
			System.out.print(key.s_random_index[i]+",");
		}
		System.out.println();
		
		//加密
		indexEnc index_Enc=new indexEnc();
		
		index_Enc.encIndexRoot1=index_Enc.enc_Tree1(demonTree1,m,matrix1);
		index_Enc.encIndexRoot2=index_Enc.enc_Tree2(demonTree2,m,matrix2);
		
		//遍历加密后的indexTree
		System.out.println("遍历算法encIndexRoot1：");
		demonTree1.inOrder(index_Enc.encIndexRoot1);
		
		System.out.println("遍历算法encIndexRoot2：");
		demonTree2.inOrder(index_Enc.encIndexRoot2);	
	}

}
