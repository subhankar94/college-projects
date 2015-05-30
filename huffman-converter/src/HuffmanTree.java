



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanTree
{
	HuffmanNode root;
	 
	public HuffmanTree (HuffmanNode huff)
	{
		root = huff;
	}
	
	public HuffmanNode getRoot ()
	{
		return root;
	}
	
	public void printLegend ()
	{
		printLegend (root, "");
	}
	
	private void printLegend (HuffmanNode t, String s)
	{
		if (t.letter.length() > 1)
		{
			printLegend(t.left, s + "0");
			printLegend(t.right, s + "1");
		}
		else
		{
//			System.out.println(t.letter + " = " + s);
			if (t.letter.equals("\n")) System.out.println("\\n = " + s);
			else System.out.println(t.letter + " = " + s);
		}
	}
	
	public static BinaryHeap fileToHeap (String filename) throws IOException
	{
		 BufferedReader in = new BufferedReader(new FileReader(filename));
		 String fileString = "";
		 
		 while (true)
		 {
			 String line = in.readLine();
			 if(line == null) break;
			 fileString += line;
		 }
		 
//		 System.out.println(fileString);
		 
		 String [] fileInfo = fileString.split(" ");
//		 // Quick test to see elements in fileInfo
//		 for (int i = 0; i <fileInfo.length; i++){
//			 System.out.println(fileInfo[i]);
//		 }
//		 
		 
		 HuffmanNode [] info = new HuffmanNode [fileInfo.length/2];
		 int i = 0;
		 
		 for (int j = 0; j < fileInfo.length; j += 2)
			 info[i++] = new HuffmanNode (fileInfo [j], Double.parseDouble(fileInfo [j+1]));
		 
		 return new BinaryHeap<HuffmanNode> (info);
		 
	}
	
	public static HuffmanTree createFromHeap (BinaryHeap <HuffmanNode> b)
	{
		while (b.getSize() > 1)
		{
			HuffmanNode r = b.deleteMin();
			HuffmanNode l = b.deleteMin();
			HuffmanNode n = new HuffmanNode (l, r);
			b.insert(n);
		}
		
		return new HuffmanTree (b.deleteMin());
	}
	
	public static void main (String [] args) throws IOException
	{
		BinaryHeap bheap = fileToHeap (args[0]);
		HuffmanTree htree = createFromHeap (bheap);
		htree.printLegend();
	} 
	   // - add a constructor to init the Tree from a HuffmanNode
	   // - the main method will go here, as well as code to take
	   //   a command-line argument for the input file name
}