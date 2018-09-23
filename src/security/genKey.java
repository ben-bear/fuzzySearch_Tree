package security;

import java.util.Random;
import Jama.Matrix;

public class genKey {
	public static int m;
	public Matrix matrix1;//�������M1
	public Matrix matrix2;//�������M2
	public Matrix inverMatrix1;//M1�������
	public Matrix inverMatrix2;//M2�������
	public static int[] s;
	public static int[] s_random_index;
	public static int[] s_random_query;
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
		s=new int[m];
		s_random_index=new int[m];
		s_random_query=new int[m];
		Random random =new Random();
		for(int i=0;i<m;i++)
		{
			s[i]=random.nextInt(2);//����0��1
		}
		//����index��˵
		//���s[i]=1��I[i]=I1[i]+I2[i]
		//���s[i]=0��I[i]=I1[i]=I2[i]
		for(int i=0;i<m;i++)
		{
			if(s[i]==1)
			{
				s_random_index[i]=random.nextInt(10);
			}
			if(s[i]==0)
			{
				s_random_index[i]=0;
			}
		}
		//����query��˵
		//���s[i]=0��q[i]=q1[i]+q2[i]
		//���s[i]=1��q[i]=q1[i]=q2[i]
		for(int i=0;i<m;i++)
		{
			if(s[i]==0)
			{
				s_random_query[i]=random.nextInt(10);
			}
			if(s[i]==1)
			{
				s_random_query[i]=0;
			}
		}
		
	}
	public Matrix getMatrix1() {
		return matrix1;
	}
	public void setMatrix1(Matrix matrix1) {
		this.matrix1 = matrix1;
	}
	public Matrix getMatrix2() {
		return matrix2;
	}
	public void setMatrix2(Matrix matrix2) {
		this.matrix2 = matrix2;
	}
	public Matrix getInverMatrix1() {
		return inverMatrix1;
	}
	public void setInverMatrix1(Matrix inverMatrix1) {
		this.inverMatrix1 = inverMatrix1;
	}
	public Matrix getInverMatrix2() {
		return inverMatrix2;
	}
	public void setInverMatrix2(Matrix inverMatrix2) {
		this.inverMatrix2 = inverMatrix2;
	}
	public int[] getS() {
		return s;
	}
	public Matrix constMatrix(int r)
	{
		double[][] temp=new double[m][m];
		for(int i=0;i<m;i++)
		{
			temp[i][i]=r;
		}
		Matrix matrix=new Matrix(temp);
		return matrix;
	}
	/*
	 * ���ܣ������������
	 * ���ӿڣ�
	 */
	public Matrix genMatri()
	{
		double[][] array = new double[m][m];
		Random random=new Random();
		//1�����ɵ�λ���󣬲������ʼ��б�Խ��ߵ�ֵ[1,5]
		for(int i=0;i<m;i++)
			for(int j=0;j<m;j++)
			{
				if(i==j)
				{
			//		array[i][j]=random.nextInt(5)+1;
           		array[i][j]=1;
				}
				if(i!=j)
				{
					array[i][j]=0.0;
				}
			}
//		 System.out.println("�任ǰ�������£�");
//			for(int i=0;i<m;i++)
//				for(int j=0;j<m;j++)
//				{
//				   System.out.print(array[i][j]+" ");
//				   if(j+1==m)
//						System.out.println();
//				}
		//2�����г����б任
//	      for(int row=0;row<m;row++)
//			{
//				//���ѡȡһ������[0,m-1]���мӷ��任
//				int tempRow=random.nextInt(m);
//				for(int i=0;i<m;i++)
//				{
//					array[tempRow][i%m] = array[tempRow][i%m]+array[row][i%m];
//				}
//			}
//	      System.out.println("���ܾ���");
//			for(int i=0;i<m;i++)
//				for(int j=0;j<m;j++)
//				{
//				   System.out.print(array[i][j]+" ");
//				   if(j+1==m)
//						System.out.println();
//				}
	      //��double[][]ת���ɾ���

	    Matrix tempMatrix=new Matrix(array);
		return tempMatrix;
	}
	/*
	 * ���ܣ����������
	 * ���ӿڣ�
	 */
	public Matrix genInverMatrix(Matrix matrix)
	{
		Matrix temp = matrix.inverse();
		
		return temp;		
	}
	/*
	 * ���ܣ�����ת��
	 * ���ӿڣ�
	 */
	public Matrix genTransMatrix(Matrix matrix)
	{
		Matrix temp = matrix.transpose();
		temp.print(m, 2);
		return temp;
	}
	/*
	 * ���ܣ���double[]ת����double[][]�ͣ������ɾ���
	 */
	public Matrix genDouble(double[] temp)
	{
		double[][] temp2 =new double[1][temp.length];
		for(int i=0;i<temp.length;i++)
		{
			temp2[0][i]=temp[i];
//			System.out.print(temp2[0][i]+" ");
		}
		Matrix matrix = new Matrix(temp2);	
		return matrix;
	}
	/*
	 * ����
	 */
	public static void main(String[] args)
	{
		genKey test=new genKey();
		int m=5;
		test.setM(m);
		System.out.println("s���飺");
		for(int i=0;i<m;i++)
		{
			System.out.print(test.s[i]+",");
		}
		System.out.println("s_random_index���飺");
		for(int i=0;i<m;i++)
		{
			System.out.print(test.s_random_index[i]+",");
		}
		System.out.println("s_random_query���飺");
		for(int i=0;i<m;i++)
		{
			System.out.print(test.s_random_query[i]+",");
		}
		System.out.println();
		
	}
	
}

