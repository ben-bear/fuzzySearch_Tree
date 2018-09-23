package security;

import java.util.Stack;

public class TreeNode {
	public double[][] val;
	public TreeNode left;
	public TreeNode right;
	public int file_id;//默认是-1，非叶子节点都用默认值
	                    //叶子节点对应文件编号
	public TreeNode()
	{
		
	}
	
	//默认是-1，非叶子节点都用默认值
	public TreeNode(double[][] val){
		this.val=val;
		this.left=null;
		this.right=null;
		this.file_id=-1;
		
	}
	//叶子节点对应文件编号
	public TreeNode(double[][] val,int file_id){
		this.val=val;
		this.left=null;
		this.right=null;
		this.file_id=file_id;
		
	}
	//默认是-1，非叶子节点都用默认值
	public TreeNode(double[][] val,TreeNode l,TreeNode r){
		this.val=val;
		this.left=l;
		this.right=r;
		file_id=-1;
	}
	//叶子节点对应文件编号
	public TreeNode(double[][] val,TreeNode l,TreeNode r,int file_id){
		this.val=val;
		this.left=l;
		this.right=r;
		this.file_id=file_id;
	}
	public double[][] getVal() {
		return val;
	}
	public void setVal(double[][] val) {
		this.val = val;
	}
	public TreeNode getLeft() {
		return left;
	}
	public void setLeft(TreeNode left) {
		this.left = left;
	}
	public TreeNode getRight() {
		return right;
	}
	public void setRight(TreeNode right) {
		this.right = right;
	}
	
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	//中序遍历
	public  void inOrder(TreeNode node){
		if(node==null){
			return;
		}
		inOrder(node.left);
		for(int i=0;i<node.getVal().length;i++)
		{
			for(int j=0;j<node.getVal()[0].length;j++)
			{
				System.out.print(node.getVal()[i][j]+" ");
			}
			System.out.println("------------");
		}
		System.out.print("ID="+node.file_id+" ");
		System.out.println();
		inOrder(node.right);
		}
	//非递归遍历 中序遍历
	public void inOrder_norecursive(TreeNode node)
	{
		if(node==null){
			return;
		}
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode p=node;//根节点
		do {
	          while (p != null) {
	             s.push(p);
	             p = p.left;
	          }
	          p = s.pop();
	          for(int i=0;i<p.val.length;i++)
	          {
	        	  for(int j=0;j<p.val[0].length;j++)
	        	  {
		        	System.out.print(p.val[i][j]+" "); 
	        	  }
	        	  System.out.println("------------");
	          }
	          System.out.print("ID="+p.file_id+" ");
	          System.out.println();
	          if (p.right != null) {
	              p = p.right;
	          }
	          else p = null;
	     } while(p != null || !s.empty());
		
	}
	
	//先序遍历
	public  void preOrder(TreeNode node){
		if(node==null){
			return;
		}
		System.out.print(node.getVal()+" ");
		preOrder(node.left);			
		preOrder(node.right);
	}
	public static void main(String[] args)
	{
		
	}

}
