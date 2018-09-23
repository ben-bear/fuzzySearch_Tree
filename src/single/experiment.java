package single;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.Callable;

import Jama.Matrix;

public class experiment implements Callable<List<Integer>> {
	public List<String> keyword =new ArrayList<String>();//存放关键字
	public List<String> file =new ArrayList<String>();//存放关键字对应的文件
	public List<String> fuzzyQuery =new ArrayList<String>();//存放模糊查询
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
	 * 功能：随机选取一份文件的一个关键字，
	 * 关键字存入keyword，
	 * 关键字对应的文件存入file，
	 * 模糊查询存入fuzzyQuery，
	 */
	/**
	 * 随机选取一份文件的一个关键字
	 * @param ListD
	 * @param ListK
	 * @param numOfqueryKeyword 一个查询有多条关键字
//	 * @param numKeywordOfFile  一份文件有多少条关键字
	 * @param numFuzzy 模糊的星数 *
	 */
	public void exper(List<String> ListD,List<List<String>> ListK,int numOfqueryKeyword,int numFuzzy,String model)
	{
		if(model=="and")
		{
			int size =ListK.size();
			Random random =new Random();
			int indexFile=random.nextInt(size);
			//判断这份文件的关键字字数是否不满足numFuzzy
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
						keyword.add(str);//没有替换成*号以前的字母
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
				//判断这份文件的关键字字数是否不满足numFuzzy
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
							keyword.add(str);//没有替换成*号以前的字母
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
	    TreeNode p=node;//根节点
		do {
			//searchStandard(p.val,model,accracy)==1 说明节点符合，入栈
			 
			 while (p != null && searchStandard(p.val,model,accracy)==1) {  
				
				 
	         s.push(p);
	         p = p.left;
	         }
			 if(s.size()!=0)
			 {
				 p = s.pop();
			 }else
			 {
				 System.out.println("出错，栈为空！");
				 return ;
			 }
			 //如果节点左子孩子和右子孩子为空，或者左孩子和右孩子都是叶子节点
			 //就是我们要返回的文件
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
	        System.out.println("-----节点分界线-----------");
	          if (p.right != null && searchStandard(p.right.val,model,accracy)==1) {
	              p = p.right;
	          }
	          else p = null;
	     } while(p != null || !s.empty());		
	}
	
	
	public int searchStandard(double[][] value,String model,double accracy)
	{
//		double accracy = 0.01;//精度控制
		if(model=="and")
		{
		    double tempElement;
			int flagCount=0;//每一列整数的个数
			//每一列，遇到一个整数就停止往下判断
			for(int j=0;j<value[0].length;j++)
			{
				for(int k=0;k<value.length;k++)
				{
					tempElement=value[k][j];
					//Math.abs(tempElement-Math.round(tempElement))<accracy 满足精度范围的数值
					//tempElement%1==0 数值为整数
					if(Math.abs(tempElement-Math.round(tempElement))<accracy || tempElement%1==0)
				     {
						flagCount++;
						break;
					}
				}		
			}
			//每一列都有整数的列数是flagCount
				if(flagCount>=value[0].length)
				{
					return 1;
				}else
				{
					return 0;
			}
		}else if(model=="or")
		{
//			double accracy = 0.000001;//精度控制
			double tempElement ;
			int flagCount=0;//每一列整数的个数
			//每一列，遇到一个整数就停止往下判断
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
			//只要有一列有一个整数即可满足OR条件
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
	 * 精确度的判断  
	 */
	public void searchPrecision(List<List<String>> keyword,List<String> fuzzyQuery,String model)
	{
		double totle=keyword.size();
		double rightTotle=0; 
      //判断每一份文件是否包含待查询的关键词
        for(int i=0;i<totle;i++)
		{
		   rightTotle=rightTotle+isContainQuery(keyword.get(i),fuzzyQuery,model);
		}
        rightHasSearchTotle=rightTotle;
		System.out.println("精确度="+rightTotle/totle);
     }
    
	public int isContainQuery(List<String> keyword,List<String> query,String model)
	{
		int result=0;
		//1.复制
		List<String> tempKeyword=new ArrayList<String>();
		for(int i=0;i<keyword.size();i++)
		{
			tempKeyword.add(keyword.get(i));
		}
		//2.检索
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
			//3.判断
			if(flag==query.size())
			{
				result=1;
			}else
			{
				result=0;
			}
		}else if(model=="or")
		{
			//3.判断
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
	 * 判断两个字符串是否一致，即符合模糊查询的条件
	 * @param keywordStr
	 * @param queryStr
	 * @return 是则返回1 否则返回0
	 */	
	public int is_sameStr(String keywordStr,String queryStr)
	{
		int flagCount=0;
		int result=0;
		if(keywordStr.length()!=queryStr.length())//长度不相同，肯定不一样
		{
			result=0;
		}else
		{
			for(int i=0;i<queryStr.length();i++)
			{
				char tempChar=queryStr.charAt(i);
				if(tempChar=='*')//如果是*号，则不用判断
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
	 * 召回率的判断,将模糊查询放进ListK进行比对，查找有多少个满足条件的
	 */
	public void searchRecall(List<List<String>> listK,List<String> fuzzyQuery,String model)
	{
		double totle=listK.size();
		double rightTotle=0;
      //判断每一份文件是否包含待查询的关键词
        for(int i=0;i<totle;i++)
		{
		   rightTotle=rightTotle+isContainQuery(listK.get(i),fuzzyQuery,model);
		}

		System.out.println("召回率="+ rightHasSearchTotle/rightTotle);
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
//		// 文件夹下所有文件的文件关键字
//		List<String> listK = new ArrayList<String>();
//		fileRead test = new fileRead();
////		test.readFileName("F:/infocoms/3keyword");
//		test.readFileName("G:/test");
//		listD = test.getListDocument();
//		listK = test.getListKeyword();
//		List<String> keyword =new ArrayList<String>();//存放关键字
//		List<String> file =new ArrayList<String>();//存放关键字对应的文件
//		List<String> fuzzyQuery =new ArrayList<String>();//存放模糊查询
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
//		System.out.println("打印模糊查询字符：");
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

