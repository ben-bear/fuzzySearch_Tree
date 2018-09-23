package single;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Jama.Matrix;
/**
 * 将查询的语句全部变成一个矩阵
 * @author admin
 *
 */
public class queryEnc {
	public List<double[][]>spliteQuery1 =new ArrayList<double[][]>();//拆分索引1
	public List<double[][]>spliteQuery2 =new ArrayList<double[][]>();//拆分索引2
	public List<Matrix> Enc_query1 = new ArrayList<Matrix>();
	public List<Matrix> Enc_query2 = new ArrayList<Matrix>();

	public List<double[][]> getSpliteQuery1() {
		return spliteQuery1;
	}
	public void setSpliteQuery1(List<double[][]> spliteQuery1) {
		this.spliteQuery1 = spliteQuery1;
	}
	public List<double[][]> getSpliteQuery2() {
		return spliteQuery2;
	}
	public void setSpliteQuery2(List<double[][]> spliteQuery2) {
		this.spliteQuery2 = spliteQuery2;
	}
	public List<Matrix> getEnc_query1() {
		return Enc_query1;
	}
	public List<Matrix> getEnc_query2() {
		return Enc_query2;
	}
	public void setEnc_query1(List<Matrix> enc_query1) {
		Enc_query1 = enc_query1;
	}
	public void setEnc_query2(List<Matrix> enc_query2) {
		Enc_query2 = enc_query2;
	}
	/**
	 * 功能：将查询填充成大小为m,返回填充后的List<double[]> fillQueryM
	 * @param listQ
	 * @param m 矩阵大小
	 * @return
	 */
	public List<double[][]> fillQueryM(List<double[][]> listQ,int m)
	{
		List<double[][]> result =new ArrayList<double[][]>();
		for(int i=0;i<listQ.size();i++)
		{
			double[][] temp=new double[m][listQ.get(i)[0].length];
			//复制
			for(int j=0;j<temp[0].length;j++)
			{
				for(int k=0;k<26;k++)
				{
					temp[k][j]=listQ.get(i)[k][j];
				}
				for(int k=26;k<m;k++)
				{
					temp[k][j]=0;
				}
			}
			result.add(temp);
		}
		return result;
	}
	/**
	 * 功能：根据向量S，把Q拆分成q1和q2
	 * @param result
	 * @param s
	 * @param m
	 */
	public void spilte(List<double[][]> result,int[] s,int m)
	{
		Random random = new Random();
		for(int i=0;i<result.size();i++)
		{
			double[][] temp1=new double[m][result.get(i)[0].length];
			double[][] temp2=new double[m][result.get(i)[0].length];
			for(int j=0;j<result.get(i)[0].length;j++)
			{
				for(int k=0;k<m;k++)
				{
					if(s[k]==1)
					{
						temp1[k][j]=result.get(i)[k][j];
						temp2[k][j]=result.get(i)[k][j];
					}else
					{
						int r=random.nextInt(2)+1;//随机数1和2
						
						temp1[k][j]=0.5*result.get(i)[k][j]+r;
						temp2[k][j]=0.5*result.get(i)[k][j]-r;	
//						temp1[k][j]=result.get(i)[k][j];
//						temp2[k][j]=0;
					}
				}
			}
			spliteQuery1.add(temp1);
			spliteQuery2.add(temp2);
//			System.out.println("Q拆分后：");
//			System.out.println("Q1：");
//			for(int k=0;k<temp1.length;k++)
//			{
//				for(int l=0;l<temp1[0].length;l++)
//				{
//					System.out.print(temp1[k][l]+",");
//				}
//				System.out.println();
//			}
//			System.out.println("Q2：");
//			for(int k=0;k<temp2.length;k++)
//			{
//				for(int l=0;l<temp2[0].length;l++)
//				{
//					System.out.print(temp2[k][l]+",");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
		
	}
	/**
	 * 功能：加密，生成List<Matrix> Enc_query2
	 * @param query
	 * @param matrix
	 */
	public void Enc1(List<double[][]> query,Matrix matrix)
	{
		for(int i=0;i<query.size();i++)
		{
			Matrix matrixQ=new Matrix(query.get(i));
			Matrix result = matrix.times(matrixQ);
			double[][] temp=result.getArray();
//			System.out.println("加密Q1：");
//			for(int k=0;k<temp.length;k++)
//			{
//				for(int l=0;l<temp[0].length;l++)
//				{
//					System.out.print(temp[k][l]+",");
//				}
//				System.out.println();
//			}
//			System.out.println();
			Enc_query1.add(result);	
		}
	}
	/**
	 * 功能：加密，生成List<Matrix> Enc_query2
	 * @param query
	 * @param matrix
	 */
	public void Enc2(List<double[][]> query,Matrix matrix)
	{
		for(int i=0;i<query.size();i++)
		{
			Matrix matrixQ=new Matrix(query.get(i));
			Matrix result = matrix.times(matrixQ);
			double[][] temp=result.getArray();
//			System.out.println("加密Q2：");
//			for(int k=0;k<temp.length;k++)
//			{
//				for(int l=0;l<temp[0].length;l++)
//				{
//					System.out.print(temp[k][l]+",");
//				}
//				System.out.println();
//			}
//			System.out.println();
			Enc_query2.add(result);	
		}		
		
	}
	//测试
	public static void main(String[] args)
	{
		queryEnc query_en =new queryEnc();
		queryText qt=new queryText();
		double[] prime ={2,3,5,7,11,17,23,29,31,37,41,43,53,59,61,67,71,73,79,83,89,97,101};
		String[] randLetter={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"};
		List <String> query=new ArrayList<String>();
		int m=30;
		query.add("w*th");
		query.add("*ontent");
		qt.setPrime(prime);
		qt.setQuery(query);
		qt.setRandLetter(randLetter);
		qt.queryMain(query, 8);
		genKey key=new genKey();
		key.setM(m);
		Matrix tempMatrix1=key.genMatri();//加密矩阵1
		Matrix tempInverMatrix1=key.genInverMatrix(tempMatrix1);//加密矩阵1的逆矩阵
		Matrix tempMatrix2=key.genMatri();//加密矩阵2
		Matrix tempInverMatrix2=key.genInverMatrix(tempMatrix2);//加密矩阵2的逆矩阵
		int[] s=key.getS();
		List<double[][]> queryMLength=new ArrayList<double[][]>();
		queryMLength=query_en.fillQueryM(qt.getQueryVector(), m);
		query_en.spilte(queryMLength, s, m);
		List<double[][]>query1 =new ArrayList<double[][]>();
		List<double[][]>query2 =new ArrayList<double[][]>();
		query1=query_en.getSpliteQuery1();//拆分索引1
		query2=query_en.getSpliteQuery2();//拆分索引2
		query_en.Enc1(query1, tempInverMatrix1);
		query_en.Enc2(query2, tempInverMatrix2);	
		System.out.println(query_en.getEnc_query1().size());
		System.out.println(query_en.getEnc_query2().size());
	     
	}

}

