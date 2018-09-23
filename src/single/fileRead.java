package single;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/*
 * 读取文件集中的文件和文件里的关键字
 */
public class fileRead {
	//目录文件夹下所有文件的文件名
		public List<String> listDocument =new ArrayList<String>();
		//目录文件夹下所有文件的文件关键字
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
		 * 通过一个路径读取文件夹下的所有文件，获取所有关键字
		 * @param path 文件夹路径
		 * @throws IOException
		 */
		public void readFileName(String path) throws IOException
		{
			File file=new File(path);
			String[] list=file.list();
			for(int i=0;i<list.length;i++)
			{
				//一份txt文件的路径filePath
				String filePath=null;
				listDocument.add(list[i]);
				filePath=path+'/'+list[i];
				readFileKeyword(filePath);
			}
	     }
		/**
		 * 通过一个路径读取文件下的关键字，获取文件夹下文件集的关键字集合
		 * @param path 文件路径
		 * @throws IOException
		 */
		public  void readFileKeyword(String path) throws IOException
		{
			//1、读取文件内容
			File file=new File(path);
			List<String> keywordOfFile=new ArrayList<String>();
			Reader read=null;
			String str="";
			BufferedReader bin=null;
			read=new FileReader(file);
			bin=new BufferedReader(read);
			//读取出来的文件内容存放str
			str=bin.readLine();
			bin.close();
			read.close();
			//2、处理str，去掉空格，将关键字放集合中
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
					//一个新的字符串，该字符串值包含 stringObject 的一个子字符串，其内容是从 start 处到 stop-1 处的所有字符
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
			//读取出来的文件内容存放str
			str=bin.readLine();
			bin.close();
			read.close();
			//2、处理str，去掉空格，将关键字放集合中
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
					//一个新的字符串，该字符串值包含 stringObject 的一个子字符串，其内容是从 start 处到 stop-1 处的所有字符
					String newstr=str.substring(beginIndex, endIndex+1);
					//String 转化为 double
					primeTable[count++]=Double.valueOf(newstr).doubleValue();

				}
			}
		}
		

//		测试：
		public static void main(String[] args) throws IOException
		{
			long a=System.currentTimeMillis();
			List<List<String>> listK =new ArrayList<List<String>>();
			//文件夹下所有文件的文件关键字
			List<String> listD =new ArrayList<String>();
			//文件夹下的素数表
//			double[] primeTable=new double[1230];
			fileRead test=new fileRead();
			test.readFileName("D:\\博士学习\\实验数据集+开源代码\\fuzzySearch\\minTest");
			listD=test.getListDocument();
			listK=test.getListKeyword();
			//输出
			for(int i=0;i<listD.size();i++)
			{
				System.out.println(listD.get(i)+":"+listK.get(i));			
			}
            System.out.println("listD.size()："+listD.size());
            System.out.println("listK.size()："+listK.size());
			 
		}
}

