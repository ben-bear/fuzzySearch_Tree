package single;

import java.util.ArrayList;
import java.util.List;

public class queryText {
	//关键字查询集合
	public List<String> query =new ArrayList<String>();
	//查询向量集合
	public List<double[][]> queryVector= new ArrayList<double[][]>();
	//向量素数
	public double[] prime;
	//填充字母
	public String[] randLetter;
	
	public List<String> getQuery() {
		return query;
	}
	public void setQuery(List<String> query) {
		this.query = query;
	}
	public List<double[][]> getQueryVector() {
		return queryVector;
	}
	public void setQueryVector(List<double[][]> queryVector) {
		this.queryVector = queryVector;
	}
	public double[] getPrime() {
		return prime;
	}
	public void setPrime(double[] prime) {
		this.prime = prime;
	}
	public String[] getRandLetter() {
		return randLetter;
	}
	public void setRandLetter(String[] randLetter) {
		this.randLetter = randLetter;
	}
	/*
	 * 1、把关键字集处理成定长
	 * 传入明文关键字集合和关键字最大长度
	 * (接口)
	 */
	public void queryMain(List<String> query,int maxLength)
	{ 
		double[][] queryVectorTemp=new double[26][query.size()];
		List<String> queryTemp=new ArrayList<String>();
		for(int i=0;i<query.size();i++)
		{
			String str=query.get(i);
			if(query.get(i).length()!=maxLength)
			{
				int num=maxLength-query.get(i).length();
				StringBuffer strb=new StringBuffer(query.get(i));
				for(int j=0;j<num;j++)
				{
					str=str+randLetter[j];			
				}
			}
			queryTemp.add(str);
		}
		queryVectorTemp=queryIntoVector(queryTemp);
		queryVector.add(queryVectorTemp);
	}
	public double[][] queryIntoVector(List<String> query)
	{
		double[][] queryVector=new double[26][query.size()];
		double[] temp=new double[26];
		for(int i=0;i<query.size();i++)
		{
			temp=queryIntoVector(query.get(i));
			for(int j=0;j<26;j++)
			{
				queryVector[j][i]=temp[j];
			}
		}
		return queryVector;
	}
	public double[] queryIntoVector(String tempQuery)
	{
		char temp;
		double[] tempQueryVector =new double [26];
		int  pos=0;//向量存放的位置
		//向量初始化为1.0
		for(int i=0;i<26;i++)
		{
			tempQueryVector[i]=1.0;
		}
		//关键字转化成过渡向量
		for(int i=0;i<tempQuery.length();i++)
		{
			temp=tempQuery.charAt(i);
		    //字符是小写字母的情况
			if(temp>96 && temp<123)
			 {
				pos=temp-97;
				tempQueryVector[pos]=(prime[i])*tempQueryVector[pos];
			 }
			//字符是大写字母的情况
			else if(temp>64 && temp<91)
			 {
				pos=temp-65;
				tempQueryVector[pos]=(prime[i])*tempQueryVector[pos];
			 }
			//字符是*号
			else if(temp=='*')
			{
//				for(int j=0;j<26;j++)
//				{
//					tempQueryVector[j]=prime[i]*tempQueryVector[j];
//				}
				//似乎是，遇到了“*”号，不处理
				continue;
			}			
		}
		
		for(int i=0;i<26;i++)
		{
			if(tempQueryVector[i]==1.0)
			{
				tempQueryVector[i]=0.0;
			}else if(tempQueryVector[i]!=1.0)
			{
				tempQueryVector[i]=1/tempQueryVector[i];
			}
		}
		return tempQueryVector;
	}

	//测试
	public static void main(String[] args)
	{
		double[] prime ={ 2, 3,5,7,11,
		                  17,19,21,23,29};
		String[] randLetter={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"};
		List <String> query=new ArrayList<String>();
		List<double[][]> vector= new ArrayList<double[][]>();
		queryText qt=new queryText();
		query.add("wor*d");
		query.add("wor*d");
		qt.setPrime(prime);
		qt.setQuery(query);
		qt.setRandLetter(randLetter);
		qt.queryMain(query, 10);
		vector=qt.getQueryVector();
		System.out.println(qt.getQuery()+":");
		double[][] temp=new double[26][query.size()];
		temp=vector.get(0);
		for(int i=0;i<temp.length;i++)
		{
			for(int j=0;j<temp[0].length;j++)
			{
				System.out.print(temp[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}