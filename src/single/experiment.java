package single;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.Callable;

import Jama.Matrix;

public class experiment implements Callable<List<Integer>> {
	public List<String> keyword =new ArrayList<String>();//��Źؼ���
	public List<String> file =new ArrayList<String>();//��Źؼ��ֶ�Ӧ���ļ�
	public List<String> fuzzyQuery =new ArrayList<String>();//���ģ����ѯ
	public List<Integer> searchResult = new ArrayList<Integer>();
	public double rightHasSearchTotle;
	public List<String> getKeyword() {
		return keyword;
	}
	public void setKeyword(List<String> keyword) {
		this.keyword = keyword;
	}
	public List<String> getFile() {
		return file;
	}
	public void setFile(List<String> file) {
		this.file = file;
	}
	public List<String> getFuzzyQuery() {
		return fuzzyQuery;
	}
	public void setFuzzyQuery(List<String> fuzzyQuery) {
		this.fuzzyQuery = fuzzyQuery;
	}
	public List<Integer> getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(List<Integer> searchResult) {
		this.searchResult = searchResult;
	}

	private TreeNode treeNode = null;
	private String mode = null;
	private double accracy = 0.0;

	experiment() {}

	experiment(TreeNode treeNode, String mode, double accracy) {
		this.treeNode = treeNode;
		this.mode = mode;
		this.accracy = accracy;
	}

	/*
	 * ���ܣ����ѡȡһ���ļ���һ���ؼ��֣�
	 * �ؼ��ִ���keyword��
	 * �ؼ��ֶ�Ӧ���ļ�����file��
	 * ģ����ѯ����fuzzyQuery��
	 */
	/**
	 * ���ѡȡһ���ļ���һ���ؼ���
	 * @param ListD
	 * @param ListK
	 * @param numOfqueryKeyword һ����ѯ�ж����ؼ���
//	 * @param numKeywordOfFile  һ���ļ��ж������ؼ���
	 * @param numFuzzy ģ�������� *
	 */
	public void exper(List<String> ListD,List<List<String>> ListK,int numOfqueryKeyword,int numFuzzy,String model)
	{
		if(model=="and")
		{
			int size =ListK.size();
			Random random =new Random();
			int indexFile=random.nextInt(size);
			//�ж�����ļ��Ĺؼ��������Ƿ�����numFuzzy
			int flag=1;
			int count=0;
			List<String> listKTemp=new ArrayList<String>();
			while(flag==1)
			{
				listKTemp=ListK.get(indexFile);
				for(int i=0;i<listKTemp.size();i++)
				{
					String str=listKTemp.get(i);
					if(str.length()>=numFuzzy)
					{
						count++;
					}
				}
				if(count>=numOfqueryKeyword)
				{
					flag=0;
				}else
				{
					indexFile=random.nextInt(size);
					count=0;
				}
			}
		
			count=0;
			file.add(ListD.get(indexFile));
			for(int i=0;i<numOfqueryKeyword && count<listKTemp.size();i++)
			{
				flag=1;
				
				while(flag==1)
				{
					String str=listKTemp.get(count);
			
					if(str.length()>=numFuzzy)
					{
						keyword.add(str);//û���滻��*����ǰ����ĸ
						fuzzyQuery.add(repalceLetter(str,numFuzzy));
						flag=0;
						count++;
					}else
					{
						count++;
					}
				}		
			}	
		}else if(model=="or")
		{
			int size =ListK.size();
			Random random =new Random();
			for(int i=0;i<numOfqueryKeyword;i++)
			{
				//�ж�����ļ��Ĺؼ��������Ƿ�����numFuzzy
				int flag=1;
				int count=0;
				List<String> listKTemp=new ArrayList<String>();		
				while(flag==1)
				{
					int indexFile=random.nextInt(size);
					listKTemp=ListK.get(indexFile);
					for(int j=0;j<listKTemp.size();j++)
					{
						String str=listKTemp.get(j);
						if(str.length()>=numFuzzy)
						{
							keyword.add(str);//û���滻��*����ǰ����ĸ
							fuzzyQuery.add(repalceLetter(str,numFuzzy));
							flag=0;
							break;							
						}
					}
				}
			}
			
			
		}
			
		
		
	}
	public String repalceLetter(String str,int numFuzzy)
	{
	    Random random=new Random();
		String[] Str={"*"};
		String newStr=new String();
		for(int i=0;i<numFuzzy;i++)
		{
			newStr=newStr+Str[0];
		}
	    newStr=newStr+str.substring(numFuzzy, str.length());
		return newStr;
	}

	public List<Integer> searchStandardRecursively(TreeNode node, String mode, double accracy) {
		List<Integer> list = new ArrayList<>();
		if (node == null || searchStandard(node.val, mode, accracy) != 1) {
			return new ArrayList<>();
		} else {
			if (node.file_id != -1) {
				list.add(node.file_id);
			}
			list.addAll(searchStandardRecursively(node.left, mode, accracy));
			list.addAll(searchStandardRecursively(node.right, mode, accracy));
		}
		return list;
	}

	public void searchStandard(TreeNode node,String model,double accracy)
	{
		
        if(node==null){
			return;
		}
		Stack<TreeNode> s = new Stack<TreeNode>();
	    TreeNode p=node;//���ڵ�
		do {
			//searchStandard(p.val,model,accracy)==1 ˵���ڵ���ϣ���ջ
			 
			 while (p != null && searchStandard(p.val,model,accracy)==1) {  
				
				 
	         s.push(p);
	         p = p.left;
	         }
			 if(s.size()!=0)
			 {
				 p = s.pop();
			 }else
			 {
				 System.out.println("����ջΪ�գ�");
				 return ;
			 }
			 //����ڵ����Ӻ��Ӻ����Ӻ���Ϊ�գ��������Ӻ��Һ��Ӷ���Ҷ�ӽڵ�
			 //��������Ҫ���ص��ļ�
			 if(p.file_id!=-1)
			 {
				 searchResult.add(p.file_id);
			 }

	         for(int i=0;i<p.val.length;i++)
	         {
	            for(int j=0;j<p.val[0].length;j++)
	            {
	    	        System.out.print(p.val[i][j]+" "); 
	            }
	            System.out.println();
	         }
	        System.out.println("-----�ڵ�ֽ���-----------");
	          if (p.right != null && searchStandard(p.right.val,model,accracy)==1) {
	              p = p.right;
	          }
	          else p = null;
	     } while(p != null || !s.empty());		
	}
	
	
	public int searchStandard(double[][] value,String model,double accracy)
	{
//		double accracy = 0.01;//���ȿ���
		if(model=="and")
		{
		    double tempElement;
			int flagCount=0;//ÿһ�������ĸ���
			//ÿһ�У�����һ��������ֹͣ�����ж�
			for(int j=0;j<value[0].length;j++)
			{
				for(int k=0;k<value.length;k++)
				{
					tempElement=value[k][j];
					//Math.abs(tempElement-Math.round(tempElement))<accracy ���㾫�ȷ�Χ����ֵ
					//tempElement%1==0 ��ֵΪ����
					if(Math.abs(tempElement-Math.round(tempElement))<accracy || tempElement%1==0)
				     {
						flagCount++;
						break;
					}
				}		
			}
			//ÿһ�ж���������������flagCount
				if(flagCount>=value[0].length)
				{
					return 1;
				}else
				{
					return 0;
			}
		}else if(model=="or")
		{
//			double accracy = 0.000001;//���ȿ���
			double tempElement ;
			int flagCount=0;//ÿһ�������ĸ���
			//ÿһ�У�����һ��������ֹͣ�����ж�
			for(int j=0;j<value[0].length;j++)
			{
				for(int k=0;k<value.length;k++)
				{
					tempElement=value[k][j];
					if(Math.abs(tempElement-Math.round(tempElement))<accracy || tempElement%1==0)
				   {
						flagCount++;
						break;
				   }
			    }		
			}
			//ֻҪ��һ����һ��������������OR����
				if(flagCount>=1)
				{
					return 1;
				}else 
				{
					return 0;
			}
		}
		return 1;
	}
	/*
	 * ��ȷ�ȵ��ж�  
	 */
	public void searchPrecision(List<List<String>> keyword,List<String> fuzzyQuery,String model)
	{
		double totle=keyword.size();
		double rightTotle=0; 
      //�ж�ÿһ���ļ��Ƿ��������ѯ�Ĺؼ���
        for(int i=0;i<totle;i++)
		{
		   rightTotle=rightTotle+isContainQuery(keyword.get(i),fuzzyQuery,model);
		}
        rightHasSearchTotle=rightTotle;
		System.out.println("��ȷ��="+rightTotle/totle);
     }
    
	public int isContainQuery(List<String> keyword,List<String> query,String model)
	{
		int result=0;
		//1.����
		List<String> tempKeyword=new ArrayList<String>();
		for(int i=0;i<keyword.size();i++)
		{
			tempKeyword.add(keyword.get(i));
		}
		//2.����
		int flag=0;
		for(int i=0;i<query.size();i++)
		{
			for(int j=0;j<tempKeyword.size();j++)
			{
				if(is_sameStr(tempKeyword.get(j),query.get(i))==1)
				{
					flag++;
//					tempKeyword.remove(j);
					break;
				}
			}
		}
		if(model=="and")
		{
			//3.�ж�
			if(flag==query.size())
			{
				result=1;
			}else
			{
				result=0;
			}
		}else if(model=="or")
		{
			//3.�ж�
			if(flag>0)
			{
				result=1;
			}else
			{
				result=0;
			}
		}
		
		return result;
	}
	
	/**
	 * �ж������ַ����Ƿ�һ�£�������ģ����ѯ������
	 * @param keywordStr
	 * @param queryStr
	 * @return ���򷵻�1 ���򷵻�0
	 */	
	public int is_sameStr(String keywordStr,String queryStr)
	{
		int flagCount=0;
		int result=0;
		if(keywordStr.length()!=queryStr.length())//���Ȳ���ͬ���϶���һ��
		{
			result=0;
		}else
		{
			for(int i=0;i<queryStr.length();i++)
			{
				char tempChar=queryStr.charAt(i);
				if(tempChar=='*')//�����*�ţ������ж�
				{
					flagCount++;
					continue;
					
				}else
				{
					
				if(tempChar==keywordStr.charAt(i) || tempChar+32==keywordStr.charAt(i) || tempChar-32==keywordStr.charAt(i))
				{
					flagCount++;
				}
			}
					
		}
			if(flagCount==queryStr.length())
			{
				result=1;				
			}else
			{
				result=0;
			}
		}
		return result;
	}

	/*
	 * �ٻ��ʵ��ж�,��ģ����ѯ�Ž�ListK���бȶԣ������ж��ٸ�����������
	 */
	public void searchRecall(List<List<String>> listK,List<String> fuzzyQuery,String model)
	{
		double totle=listK.size();
		double rightTotle=0;
      //�ж�ÿһ���ļ��Ƿ��������ѯ�Ĺؼ���
        for(int i=0;i<totle;i++)
		{
		   rightTotle=rightTotle+isContainQuery(listK.get(i),fuzzyQuery,model);
		}

		System.out.println("�ٻ���="+ rightHasSearchTotle/rightTotle);
}


	private static int getAvaliableCpuNumber() {
		return Runtime.getRuntime().availableProcessors();
	}

	public static void main(String[] args) throws IOException
	{
//		experiment ex = new experiment();
//		List<String> listD = new ArrayList<String>();
//		List<String> experimentK = new ArrayList<String>();
//		List<String> experimentD = new ArrayList<String>();
//		// �ļ����������ļ����ļ��ؼ���
//		List<String> listK = new ArrayList<String>();
//		fileRead test = new fileRead();
////		test.readFileName("F:/infocoms/3keyword");
//		test.readFileName("G:/test");
//		listD = test.getListDocument();
//		listK = test.getListKeyword();
//		List<String> keyword =new ArrayList<String>();//��Źؼ���
//		List<String> file =new ArrayList<String>();//��Źؼ��ֶ�Ӧ���ļ�
//		List<String> fuzzyQuery =new ArrayList<String>();//���ģ����ѯ
//		ex.exper(listD, listK, 2, 3, 1);
//		keyword=ex.getKeyword();
//		file=ex.getFile();
//		fuzzyQuery=ex.getFuzzyQuery();
//		System.out.println("keyword.size()="+keyword.size()+" file.size="+file.size()+" fuzzyQuery.size()="+fuzzyQuery.size());
//		
//		
//		
//		
//		int num=10;
//		ex.setNum(num);
////		ex.exper(listD, listK);
//		experimentD=ex.getFile();
//		experimentK=ex.getKeyword();
//		for(int i=0;i<num;i++)
//		{
//			System.out.println(experimentD.get(i)+":"+experimentK.get(i));
//		}
//		System.out.println("��ӡģ����ѯ�ַ���");
//		for(int i=0;i<num;i++)
//		{
//			System.out.print(ex.getFuzzyQuery().get(i)+" ");
//		}
	}

	@Override
	public List<Integer> call() throws Exception {
		return searchStandardRecursively(treeNode, mode, accracy);
	}
}

