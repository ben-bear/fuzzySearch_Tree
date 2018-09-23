package security;
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
	 * @param s 每个Q扩充成s列 一共 k*s列
	 * @return
	 */
	public List<double[][]> fillQueryM(List<double[][]> listQ,int m,int s)
	{
		List<double[][]> tempResult =new ArrayList<double[][]>();
		List<double[][]> result =new ArrayList<double[][]>();
		for(int i=0;i<listQ.size();i++)
		{
					
			//复制
			for(int j=0;j<listQ.get(i)[0].length;j++)
			{
				double[][] temp=new double[m][listQ.get(i)[0].length];	
				double[][] fun=new double[m][1];
				for(int k=0;k<26;k++)
				{
					temp[k][j]=listQ.get(i)[k][j];
					fun[k][0]=listQ.get(i)[k][j];
				}
				for(int k=26;k<m;k++)
				{
					temp[k][j]=0;
					fun[k][0]=0;
				}
				if(s>1)
				{
					temp=securityFillQueryM(fun,m,s);
				}
				
				tempResult.add(temp);	
			}				
		}	
		//整合成一个double[][]
		double[][] matrix=new double[m][s*(listQ.get(0)[0].length)];
		int matrixColumn=0;
		for(int i=0;i<tempResult.size();i++)
		{  
			double[][] temp=tempResult.get(i);
			for(int j=0;j<s;j++)
			{
				for(int k=0;k<m;k++)
				{
					matrix[k][matrixColumn]=temp[k][j];					
				}
				matrixColumn++;
			}
			
		}
		result.add(matrix);
		return result;
	}
	/**
	 * 功能：一个Q扩充成m*s列
	 * @param Q  m行1列
	 * @param m   
	 * @param s 扩充成s列
	 * @return
	 */
	public double[][] securityFillQueryM( double[][] Q,int m,int s)
	{
		double[][] queryMatrix=new double[m][s];
		double[] prime2={0,1,2};
		Random random=new Random();
		int Q_position=0;
		int P2_position=0;
		int p2Length=prime2.length;
		for(int i=0;i<m;i++)
		{
			Q_position=random.nextInt(s);
			System.out.println("Q_position="+(Q_position));
			queryMatrix[i][Q_position]=Q[i][0];
			P2_position=random.nextInt(p2Length);
//			System.out.println("P2_position="+(P2_position));
			//获得随机数列表
			double[] value=randomNum(Q[i][0],prime2[P2_position],s-1);
			int flag=0;
			//矩阵填充		
			for(int j=0;j<s;j++)
			{
				if(j!=Q_position && i<26)
				{
					queryMatrix[i][j]=value[flag];					
					flag++;
				}
				if(j>=1 && i>=26)
				{
					queryMatrix[i][j]=value[flag];
					flag++;
				}
			}
		}
		return queryMatrix;		
	}
	//随机数集合
	public double[] randomNum(double Qvaule,double primeNum,int num)
	{
		System.out.println("选中的素数："+primeNum);
		double[] vaule=new double[num];
		int middle=(int)((Qvaule+10)*primeNum)/num+1;
		Random random=new Random();
		double addVaule=0;
		for(int i=0;i<num-1;i++)
		{
			vaule[i]=random.nextInt(middle);
			addVaule=addVaule+vaule[i];
		}
		vaule[num-1]=Qvaule*primeNum-addVaule;	
		//打印vaule
		for(int i=0;i<vaule.length;i++)
		{
			System.out.print(vaule[i]+" ");
		}
		System.out.println();
		return vaule;
	}
	/**
	 * 功能：根据向量S，把Q拆分成I1和I2
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
					}
				}
			}
			spliteQuery1.add(temp1);
			spliteQuery2.add(temp2);
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
			Enc_query2.add(result);	
		}		
		
	}
	//测试
	public static void main(String[] args)
	{
		double[][] d1={{0,12},
				       {0,13},
				       {0,14},
				       {1,15},
	                   {2,16},
	                   {3,17},
	                   {4,18},
	                   {5,19},
	                   {9,20},
	                   {10,21},
	                   {3,4},
	                   {4,5},
	                   {1,2},
	                   {2,3},
	                   {3,4},
	                   {4,5},
	                   {1,2},
	                   {2,3},
	                   {3,4},
	                   {4,5},
	                   {1,2},
	                   {2,3},
	                   {3,4},
	                   {4,5},
	                   {1,2},
	                   {2,3}};
		queryEnc t=new queryEnc();
        List<double[][]> Q=new ArrayList<double[][]>();
        Q.add(d1);
        List<double[][]> R=new ArrayList<double[][]>();
        R=t.fillQueryM(Q, 30, 3);
        System.out.println("R.size()="+R.size());
        for(int i=0;i<R.size();i++)
        {
             double[][] temp=R.get(i);
             for(int j=0;j<temp.length;j++)
             {
	               for(int k=0;k<temp[0].length;k++)
	              {
		              System.out.print(temp[j][k]+" ");
	              }
	            System.out.println();
              }
          }
	
	}

}
