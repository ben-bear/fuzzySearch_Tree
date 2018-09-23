package single;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * �������Ĺؼ��ּ���1���ѹؼ��ּ�����ɶ���  2���ѹؼ��ּ������������ʽ
 */
public class indexText {
	//�ؼ���������һ���ļ���Ӧһ������double[][]
	public List<double[][]> keywordVector=new ArrayList<double[][]>();
	//�����ĸ
	public String[] randLetter;
	//��������
	public double[] prime;
	//���ؼ���
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
	 * 1���ѹؼ��ּ�����ɶ���
	 * �������Ĺؼ��ּ��Ϻ͹ؼ�����󳤶�
	 * (�ӿ�)
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
	 * 2���ѹؼ��ִ����������ʽ
	 * ��������Ĺ̶��������Ĺؼ��ּ���
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
		int  pos=0;//������ŵ�λ��
		//������ʼ��Ϊ1.0
		for(int i=0;i<26;i++)
		{
			vector[i]=1.0;
		}
		for(int i=26;i<genKey.m;i++)
		{
			vector[i]=0.0;
		}
		//�ؼ���ת���ɹ�������
//		System.out.println("strb="+strb);
		for(int i=0;i<strb.length();i++)
		{
			temp=strb.charAt(i);
		    //�ַ���Сд��ĸ�����
			if(temp>96 && temp<123)
			   {
				pos=temp-97;
			   }
			//�ַ��Ǵ�д��ĸ�����
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
		//��ӡ
//		System.out.println("indexVector:"+strb);
//		for(int i=0;i<26;i++)
//		{
//			System.out.print(vector[i]+" ");
//		}
//		System.out.println();
		//�ؼ���ת�������յ�����
		//˵����tree�汾�޸ģ�index����������ע�͵���������
//		for(int i=0;i<26;i++)
//		{
//			if(vector[i]==1.0)
//				vector[i]=0.0;
//			else
//				vector[i]=1.0/vector[i];
//		}
		return vector;
	}
	//����
	
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
//		test.readFileName("F:/infocoms/3keyword");
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

