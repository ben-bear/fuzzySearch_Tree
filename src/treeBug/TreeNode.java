package treeBug;



public class TreeNode {

	
	public double[][] val;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode(double[][] val){
		this.val=val;
		this.left=null;
		this.right=null;
	}
	
	public TreeNode(double[][] val,TreeNode l,TreeNode r){
		this.val=val;
		this.left=l;
		this.right=r;
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
	
	public  void print(TreeNode node){
		//¥Ú”°
		for(int i=0;i<node.val.length;i++)
		{
			for(int j=0;j<node.val[0].length;j++)
			{
				System.out.print(node.val[i][j]+",");
			}
		}
		System.out.println();
	}
	
}
