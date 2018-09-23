package single;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * 处理明文关键字集：1、把关键字集处理成定长  2、把关键字集处理成向量形式
 */
public class indexText {
	//关键字向量，一份文件对应一个数组double[][]
	public List<double[][]> keywordVector=new ArrayList<double[][]>();
	//填充字母
	public String[] randLetter;
	//向量素数
	public double[] prime;
	//填充关键字
	public List<List<String>> keywordRandLetter=new ArrayList<List<String>>();
	
	public List<List<String>> getKeywordRandLetter() {
		return keywordRandLetter;
	}
	public void setKeywordRandLetter(List<List<String>> keywordRandLetter) {
		this.keywordRandLetter = keywordRandLetter;
	}
	

	public List<double[][]> getKeywordVector() {
		return keywordVector;
	}
	public void setKeywordVector(List<double[][]> keywordVector) {
		this.keywordVector = keywordVector;
	}
	public String[] getRandLetter() {
		return randLetter;
	}
	public void setRandLetter(String[] randLetter) {
		this.randLetter = randLetter;
	}
	public double[] getPrime() {
		return prime;
	}
	public void setPrime(double[] prime) {
		this.prime = prime;
	}
	/*
	 * 1、把关键字集处理成定长
	 * 传入明文关键字集合和关键字最大长度
	 * (接口)
	 */
	public void keywordMain(List<List<String>> listK,int maxLength)
	{
		for(int i=0;i<listK.size();i++)
		{
			keywordMain1(listK.get(i),maxLength);
		}
	}
	public void keywordMain1(List<String> listKeyword,int maxLength)
	{
		List<String> fileOfFile=new ArrayList<String>();
		for(int i=0;i<listKeyword.size();i++)
		{
			String str=listKeyword.get(i);
			if(listKeyword.get(i).length()!=maxLength)
			{
				int num=maxLength-str.length();			
				for(int j=0;j<num;j++)
				{
					str=str+randLetter[j];
				}
				fileOfFile.add(str);
			}
			else
			{
				fileOfFile.add(str);
			}
		}	
		keywordTransferVector(fileOfFile);
	}
	/*
	 * 2、把关键字处理成向量形式
	 * 传入填充后的固定长度明文关键字集合
	 */
	public void keywordTransferVector(List<String> keywordOfFile)
	{
		double[][] vector=new double[keywordOfFile.size()][genKey.m];
		for(int i=0;i<keywordOfFile.size();i++)
		{
			vector[i]=keywordTransferVector1(keywordOfFile.get(i));
		}
		keywordVector.add(vector);
	}
	public double[] keywordTransferVector1(String strb)
	{
		
//		double[] vector=new double[26];
		double[] vector=new double[genKey.m];
		char temp;
		int  pos=0;//向量存放的位置
		//向量初始化为1.0
		for(int i=0;i<26;i++)
		{
			vector[i]=1.0;
		}
		for(int i=26;i<genKey.m;i++)
		{
			vector[i]=0.0;
		}
		//关键字转化成过渡向量
//		System.out.println("strb="+strb);
		for(int i=0;i<strb.length();i++)
		{
			temp=strb.charAt(i);
		    //字符是小写字母的情况
			if(temp>96 && temp<123)
			   {
				pos=temp-97;
			   }
			//字符是大写字母的情况
			else if(temp>64 && temp<91)
			   {
				pos=temp-65;
			   }
//			System.out.println("pos="+pos);
//			System.out.println("prime[i]="+prime[i]);
//			System.out.println("vector[pos]="+vector[pos]);
//			System.out.println("i="+i);
			vector[pos]=(prime[i])*vector[pos];			
		}
		//打印
//		System.out.println("indexVector:"+strb);
//		for(int i=0;i<26;i++)
//		{
//			System.out.print(vector[i]+" ");
//		}
//		System.out.println();
		//关键字转化成最终的向量
		//说明：tree版本修改：index的整数，故注释掉以下内容
//		for(int i=0;i<26;i++)
//		{
//			if(vector[i]==1.0)
//				vector[i]=0.0;
//			else
//				vector[i]=1.0/vector[i];
//		}
		return vector;
	}
	//测试
	
	public static void main(String[] args) throws IOException
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
//		test.readFileName("F:/infocoms/3keyword");
		test.readFileName("D:\\博士学习\\实验数据集+开源代码\\fuzzySearch\\minTest");
		listD=test.getListDocument();
		listK=test.getListKeyword();
		//2、将关键字变成索引
		String[] randLetter={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"};
		double[] prime ={ 2,3,5,7, 11,
				          17,23,29,31,
				          37,41,43,53};
		inTx.setRandLetter(randLetter);
		inTx.setPrime(prime);
		inTx.keywordMain(listK, 10);
		index=inTx.getKeywordVector();
		for(int i=0;i<listK.size();i++)
		{
			
			System.out.println(listD.get(i)+":"+listK.get(i));
			double[][] temp=index.get(i);
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

