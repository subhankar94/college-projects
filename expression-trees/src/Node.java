

public class Node
{
	private String data;
	private Node left;
	private Node right;
	
	public Node (String data)
	{
		this.data = data;
	}
	
	public void setLeft (Node left)
	{
		this.left = left;
	}
	
	public Node getLeft ()
	{
		return this.left;
	}
	
	public void setRight (Node right)
	{
		this.right = right;
	}
	
	public Node getRight ()
	{
		return this.right;
	}
	
	public void setInfo (String c)
	{
		this.data = c;
	}
	
	public String getInfo ()
	{
		return this.data;
	}
	
}