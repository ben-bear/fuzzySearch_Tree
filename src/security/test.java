package security;

import java.io.IOException;


public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		search s=new search();
		String[] path={"D:\\博士学习\\实验数据集\\fuzzySearch\\minTest"};
		int[] numOfqueryKeyword={1};
		int[] xingNum={1};
		String[] model={"and"};
		double[] accracy={0.01,0.001};
		int numExper=1;
		System.out.println("1.and 0.0001 normal");
		for(int i=0;i<numOfqueryKeyword.length;i++)
		{
			s.setPath(path[0]);
			s.setNumXing(xingNum[0]);
			s.setNumOfqueryKeyword(numOfqueryKeyword[i]);
			s.setModel(model[0]);//and
			s.setAccracy(accracy[0]);//0.001
			System.out.println("path="+s.getPath()+",numOfqueryKeyword="+s.getNumOfqueryKeyword()+",model="+s.getModel()+",numXing="+s.getNumXing()+",accracy="+s.getAccracy());			
			for(int j=0;j<numExper;j++)
			{
				System.out.println("("+(j+1)+")");
//				s.run();
			}
		}	

	}

}
