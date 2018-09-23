package single;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Jama.Matrix;


public class search {
	public int numOfqueryKeyword;
	public String path="D:\\��ʿѧϰ\\ʵ�����ݼ�+��Դ����\\fuzzySearch\\minTest";
	public int numXing=2;
	public String model="and";
	public double accracy=0.1;//���ȿ���
    public List<double[][]> dotProductValue=new ArrayList<double[][]>();
    public List<Integer> fileId=new ArrayList<Integer>();
    
	public double getAccracy() {
		return accracy;
	}
	public void setAccracy(double accracy) {
		this.accracy = accracy;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public List<double[][]> getDotProductValue() {
		return dotProductValue;
	}
	public void setDotProductValue(List<double[][]> dotProductValue) {
		this.dotProductValue = dotProductValue;
	}
	
    public int getNumOfqueryKeyword() {
		return numOfqueryKeyword;
	}
	public void setNumOfqueryKeyword(int numOfqueryKeyword) {
		this.numOfqueryKeyword = numOfqueryKeyword;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getNumXing() {
		return numXing;
	}
	public void setNumXing(int numXing) {
		this.numXing = numXing;
	}
	//	public void dotProduct(Matrix i1,Matrix q1,Matrix i2,Matrix q2)
//	{    
//		double[][] indexArray1 =i1.getArray();
//		double[][] indexArray2 =i2.getArray();
//		double[][] queryArray1 =q1.getArray();
//		double[][] queryArray2 =q2.getArray();
//		double[][] part1 =new double[indexArray1.length][queryArray1[0].length];
//		double[][] part2 =new double[indexArray1.length][queryArray1[0].length];
//		double[][] result=new double[indexArray1.length][queryArray1[0].length];
//        for(int i=0;i<indexArray1.length;i++)
//		{
//			for(int j=0;j<queryArray1[0].length;j++)
//			{
//			    for(int k=0;k<queryArray1.length;k++)
//				{
//					part1[i][j]=part1[i][j]+indexArray1[i][k]*queryArray1[k][j];
//				}
//					
//			}
//		}
//		for(int i=0;i<indexArray2.length;i++)
//		{
//			for(int j=0;j<queryArray2[0].length;j++)
//			{
//				for(int k=0;k<queryArray2.length;k++)
//				{
//					part2[i][j]=part2[i][j]+indexArray2[i][k]*queryArray2[k][j];
//				}
//			}
//	    }
//		for(int i=0;i<part1.length;i++)
//		{
//			for(int j=0;j<part1[0].length;j++)
//			{
//				result[i][j]=part1[i][j]+part2[i][j];
//			}
//		}
//		dotProductValue.add(result);
//	
//	}
	public void dotProduct(Matrix i1,Matrix q1,Matrix i2,Matrix q2)
	{
		Matrix resultMatrix=i1.times(q1).plus(i2.times(q2));
		double[][] result=resultMatrix.getArray();
//         System.out.println("��������");
//         for(int i=0;i<result.length;i++)
//         {
//        	 for(int j=0;j<result[0].length;j++)
//        		 System.out.print(result[i][j]+" ");
//        	 System.out.println();
//         }
		dotProductValue.add(result);
	}
	public void topTreeNode(TreeNode encIndexRoot1,TreeNode encIndexRoot2,Matrix q1,Matrix q2)
	{
		if((encIndexRoot1==null) || (encIndexRoot1==null) ){
		return ;
	    }
		TreeNode p_index1=encIndexRoot1;//���ڵ�
		TreeNode p_index2=encIndexRoot2;//���ڵ�
		p_index1.val=new Matrix(p_index1.val).times(q1).getArray();
		p_index2.val=new Matrix(p_index2.val).times(q2).getArray();
		p_index1.val=new Matrix(p_index1.val).plus(new Matrix(p_index2.val)).getArray();
		
		System.out.println("������ֵ��");
        for(int i=0;i< p_index1.val.length;i++)
        {
      	  for(int j=0;j<p_index1.val[0].length;j++)
      	  {
      		  System.out.print(p_index1.val[i][j]+" ");
      	  }
      	  System.out.println();
        }
		
	}
	//������ʽ��search����
	public TreeNode dotProduct(TreeNode encIndexRoot1,TreeNode encIndexRoot2,Matrix q1,Matrix q2) {
		if((encIndexRoot1==null) || (encIndexRoot1==null) ){
			return null;
		}
		Stack<TreeNode> s_index1 = new Stack<TreeNode>();
		Stack<TreeNode> s_index2 = new Stack<TreeNode>();
		TreeNode p_index1=encIndexRoot1;//���ڵ�
		TreeNode p_index2=encIndexRoot2;//���ڵ�
		do {
	          while (p_index1 != null  ) {
	        	  s_index1.push(p_index1);
	        	  p_index1 = p_index1.left;
	        	  
	        	  s_index2.push(p_index2);
	        	  p_index2 = p_index2.left;
	          }
	          p_index1 = s_index1.pop();
	          p_index1.val=new Matrix(p_index1.val).times(q1).getArray();
	          
	          p_index2 = s_index2.pop();
	          p_index2.val=new Matrix(p_index2.val).times(q2).getArray();
	          
	          p_index1.val=new Matrix(p_index1.val).plus(new Matrix(p_index2.val)).getArray();
	          
//	         System.out.println(p_index1.val[0][0]);//���ؼ��ֵ��ļ��뵥�ؼ��ֲ�ѯ
	          System.out.println("�������");
	          for(int i=0;i< p_index1.val.length;i++)
	          {
	        	  for(int j=0;j<p_index1.val[0].length;j++)
	        	  {
	        		  System.out.print(p_index1.val[i][j]+" ");
	        	  }
	        	  System.out.println();
	          }

	          if (p_index1.right != null) {
	        	  p_index1 = p_index1.right;
	        	  p_index2 = p_index2.right;
	          }
	          else 
	         {
	        	  p_index1 = null;
	        	  p_index2 = null;
	         }
	     } while(p_index1 != null || !s_index1.empty());
		return encIndexRoot1;
	}
	public void judgeInteger(TreeNode node)
	{
		if(node==null){
		return;
	    }
	Stack<TreeNode> s = new Stack<TreeNode>();
	TreeNode p=node;//���ڵ�
	do {
		
          while (p != null && Math.abs(p.val[0][0]-Math.round(p.val[0][0]))<0.0001) {
        	 
        	  System.out.println("p.val[0][0]="+p.val[0][0]);
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
       

          if (p.right != null && Math.abs(p.right.val[0][0]-Math.round(p.right.val[0][0]))<0.0001) {
              p = p.right;
          }
          else p = null;
     } while(p != null || !s.empty());
	}


	//�ж��Ƿ���Ҫ��������
    public void juge(TreeNode node)
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
		        	System.out.print(p.val[i][j]+" "); 
	        	  }
	        	  System.out.println();
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
		//0.1��������Կ ��ΪindexText�õ�
		genKey key=new genKey();
		int m=26;//��ʱ�̶�
		key.setM(m);
		//1����ȡ���еĹؼ���
				List<String> listD =new ArrayList<String>();
				//�ļ����������ļ����ļ��ؼ���
				List<List<String>> listK =new ArrayList<List<String>>();
				List<double[][]> index=new ArrayList<double[][]>();
				fileRead test=new fileRead();
				indexText inTx=new indexText();
//			    test.readFileName("F:/infocoms/3keyword");
//			    test.readFileName("D:\\��ʿѧϰ\\ʵ�����ݼ�+��Դ����\\fuzzySearch\\1keyword");
//				test.readFileName("F:\\˶ʿ\\G��\\10000");
				test.readFileName("D:\\��ʿѧϰ\\ʵ�����ݼ�+��Դ����\\fuzzySearch\\minTest");
				listD=test.getListDocument();
				listK=test.getListKeyword();
				//2�����ؼ��ֱ������
				String[] randLetter={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
				double[] prime ={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
				inTx.setRandLetter(randLetter);
				inTx.setPrime(prime);
				inTx.keywordMain(listK, 20);
				index=inTx.getKeywordVector();
//				System.out.println("index.size()="+index.size());
//				System.out.println("index������");
//				for(int i=0;i<index.size();i++)
//				{
//					double[][] temp=index.get(i);
//					for(int j=0;j<temp.length;j++)
//					{
//						for(int k=0;k<temp[0].length;k++)
//						{
//							System.out.print(temp[j][k]+",");
//						}
//						System.out.println();
//					}
//					System.out.println();
//				}
				//����
				buildTree buildtree =new buildTree();
				TreeNode demonTree1 =new TreeNode();
				
				demonTree1=	buildtree.buildTree(index);//��һ����
//				System.out.println("�����㷨��");
//				demonTree1.inOrder_norecursive(demonTree1);


				TreeNode demonTree2=buildtree.buildTree(buildtree.copyIndex(index));//�ڶ�����
//				System.out.println();
//				demonTree2.inOrder_norecursive(demonTree2);
				
				//�����ļ�����
				//������Կkey
//				genKey key=new genKey();
//				int m=26;//��ʱ�̶�
//				key.setM(m);
				Matrix matrix1=key.genMatri();
				Matrix inver_matrix1=key.genInverMatrix(matrix1);
				Matrix matrix2=key.genMatri();
				Matrix inver_matrix2=key.genInverMatrix(matrix2);
				
//				��ӡ
//				System.out.println("s���飺");
//				for(int i=0;i<m;i++)
//				{
//					System.out.print(key.s[i]+",");
//				}
//				System.out.println();
//				System.out.println("s_random_index���飺");
//				for(int i=0;i<m;i++)
//				{
//					System.out.print(key.s_random_index[i]+",");
//				}
//				System.out.println();
				
				//����
				indexEnc index_Enc=new indexEnc();
				
				index_Enc.encIndexRoot1=index_Enc.enc_Tree1(demonTree1,m,matrix1);
				index_Enc.encIndexRoot2=index_Enc.enc_Tree2(demonTree2,m,matrix2);
				
				//�������ܺ��indexTree
//				System.out.println("�����㷨encIndexRoot1��");
//				demonTree1.inOrder(index_Enc.encIndexRoot1);
//				
//				System.out.println("�����㷨encIndexRoot2��");
//				demonTree2.inOrder(index_Enc.encIndexRoot2);
//				
				//��ѯ������Reaction
				//AND �� OR ģʽת��
                String model="and";
				experiment ex=new experiment();
				List <String> query=new ArrayList<String>();
				List<double[][]> vector= new ArrayList<double[][]>();
				queryText qt=new queryText();
				//��Ϊ��ӵĲ������
		//		query.add("*ware");
				//�Զ������������������
				ex.exper(listD,listK,2,1,model);
				query=ex.fuzzyQuery;
				qt.setPrime(prime);
				qt.setQuery(query);
				qt.setRandLetter(randLetter);
				qt.queryMain(query, 10);
				vector=qt.getQueryVector();
				System.out.println(qt.getQuery()+":");
				double[][] temp=new double[m][query.size()];
				temp=vector.get(0);
//				for(int i=0;i<temp.length;i++)
//				{
//					for(int j=0;j<temp[0].length;j++)
//					{
//						System.out.print(temp[i][j]+" ");
//					}
//				}
//				System.out.println();
				
				//���ܲ�ѯ������
				queryEnc query_en =new queryEnc();
				List<double[][]> queryMLength=new ArrayList<double[][]>();
				queryMLength=query_en.fillQueryM(qt.getQueryVector(), m);
				query_en.spilte(queryMLength,genKey.s, m);
				List<double[][]>query1 =new ArrayList<double[][]>();
				List<double[][]>query2 =new ArrayList<double[][]>();
				query1=query_en.getSpliteQuery1();//�������1
				query2=query_en.getSpliteQuery2();//�������2
				query_en.Enc1(query1, inver_matrix1);
				query_en.Enc2(query2, inver_matrix2);
				
				
				//�������
				search se=new search();
				
//				 se.topTreeNode(index_Enc.encIndexRoot1,
//					      index_Enc.encIndexRoot2,
//					      query_en.Enc_query1.get(0),
//					      query_en.Enc_query2.get(0));
				
				
				
				TreeNode node1=se.dotProduct(index_Enc.encIndexRoot1,
						      index_Enc.encIndexRoot2,
						      query_en.Enc_query1.get(0),
						      query_en.Enc_query2.get(0));
               
                double accracy=0.001;
                System.out.println("--ִ��searchStandard����----");
                 
                
                ex.searchStandard(node1, model, accracy);
                System.out.println("��ѯ�Ĺؼ��֣�");
                for(int i=0;i<query.size();i++)
                {
                	System.out.print(query.get(i)+",");
                }
                System.out.println();
                System.out.println("��ѯ�����");
                List<List<String>> targetKList =new ArrayList<List<String>>();
                for(int i=0;i<ex.searchResult.size();i++)
                {
                	targetKList.add(listK.get(ex.searchResult.get(i)));
                	System.out.println(listK.get(ex.searchResult.get(i)));
                }
               System.out.println("ģʽ��"+model+" ��ȷ�Ⱥ��ٻ��ʣ�");
               
               ex.searchPrecision(targetKList, query,model);
               ex.searchRecall(listK, query,model);
               
               
               
		 
	}

}
