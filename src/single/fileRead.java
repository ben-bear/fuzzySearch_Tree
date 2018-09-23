package single;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/*
 * ��ȡ�ļ����е��ļ����ļ���Ĺؼ���
 */
public class fileRead {
	//Ŀ¼�ļ����������ļ����ļ���
		public List<String> listDocument =new ArrayList<String>();
		//Ŀ¼�ļ����������ļ����ļ��ؼ���
//		public List<String> listKeyword =new ArrayList<String>();
		public List<List<String>> listKeyword =new ArrayList<List<String>>();
		
		
		public List<String> getListDocument() {
			return listDocument;
		}
		public void setListDocument(List<String> listDocument) {
			this.listDocument = listDocument;
		}
		public List<List<String>> getListKeyword() {
			return listKeyword;
		}
		public void setListKeyword(List<List<String>> listKeyword) {
			this.listKeyword = listKeyword;
		}

		public double[] primeTable=new double[1230];
		
		public double[] getPrimeTable() {
			return primeTable;
		}
		/**
		 * ͨ��һ��·����ȡ�ļ����µ������ļ�����ȡ���йؼ���
		 * @param path �ļ���·��
		 * @throws IOException
		 */
		public void readFileName(String path) throws IOException
		{
			File file=new File(path);
			String[] list=file.list();
			for(int i=0;i<list.length;i++)
			{
				//һ��txt�ļ���·��filePath
				String filePath=null;
				listDocument.add(list[i]);
				filePath=path+'/'+list[i];
				readFileKeyword(filePath);
			}
	     }
		/**
		 * ͨ��һ��·����ȡ�ļ��µĹؼ��֣���ȡ�ļ������ļ����Ĺؼ��ּ���
		 * @param path �ļ�·��
		 * @throws IOException
		 */
		public  void readFileKeyword(String path) throws IOException
		{
			//1����ȡ�ļ�����
			File file=new File(path);
			List<String> keywordOfFile=new ArrayList<String>();
			Reader read=null;
			String str="";
			BufferedReader bin=null;
			read=new FileReader(file);
			bin=new BufferedReader(read);
			//��ȡ�������ļ����ݴ��str
			str=bin.readLine();
			bin.close();
			read.close();
			//2������str��ȥ���ո񣬽��ؼ��ַż�����
			int beginIndex=0,endIndex=0;
			for(int i=0;i<str.length();i++)
			{
				if((str.charAt(i)!=' ')&&(i+1!=str.length()))
					endIndex++;
				else if((str.charAt(i)==' '))
				{
					String newstr=str.substring(beginIndex, endIndex);
					keywordOfFile.add(newstr);	
					endIndex++;
					beginIndex=endIndex;
	            }
				else if(i+1==str.length())
				{
					//һ���µ��ַ��������ַ���ֵ���� stringObject ��һ�����ַ������������Ǵ� start ���� stop-1 ���������ַ�
					String newstr=str.substring(beginIndex, endIndex+1);
					keywordOfFile.add(newstr);
				}
			}
			listKeyword.add(keywordOfFile);
		 }
		public void readPrimeTable(String path) throws IOException
		{
			int count=0;
			File file=new File(path);
			Reader read=null;
			String str="";
			BufferedReader bin=null;
			read=new FileReader(file);
			bin=new BufferedReader(read);
			//��ȡ�������ļ����ݴ��str
			str=bin.readLine();
			bin.close();
			read.close();
			//2������str��ȥ���ո񣬽��ؼ��ַż�����
			int beginIndex=0,endIndex=0;
			for(int i=0;i<str.length();i++)
			{
				if((str.charAt(i)!=' ')&&(i+1!=str.length()))
					endIndex++;
				else if((str.charAt(i)==' '))
				{
					String newstr=str.substring(beginIndex, endIndex);
					primeTable[count++]=Double.valueOf(newstr).doubleValue();
					endIndex++;
					beginIndex=endIndex;
	            }
				else if(i+1==str.length())
				{
					//һ���µ��ַ��������ַ���ֵ���� stringObject ��һ�����ַ������������Ǵ� start ���� stop-1 ���������ַ�
					String newstr=str.substring(beginIndex, endIndex+1);
					//String ת��Ϊ double
					primeTable[count++]=Double.valueOf(newstr).doubleValue();

				}
			}
		}
		

//		���ԣ�
		public static void main(String[] args) throws IOException
		{
			long a=System.currentTimeMillis();
			List<List<String>> listK =new ArrayList<List<String>>();
			//�ļ����������ļ����ļ��ؼ���
			List<String> listD =new ArrayList<String>();
			//�ļ����µ�������
//			double[] primeTable=new double[1230];
			fileRead test=new fileRead();
			test.readFileName("D:\\��ʿѧϰ\\ʵ�����ݼ�+��Դ����\\fuzzySearch\\minTest");
			listD=test.getListDocument();
			listK=test.getListKeyword();
			//���
			for(int i=0;i<listD.size();i++)
			{
				System.out.println(listD.get(i)+":"+listK.get(i));			
			}
            System.out.println("listD.size()��"+listD.size());
            System.out.println("listK.size()��"+listK.size());
			 
		}
}

