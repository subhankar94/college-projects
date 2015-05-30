

import java.io.BufferedReader;
import java.io.FileReader;

public class HuffmanConverter 
{
	  // The # of chars in the ASCII table dictates
	  // the size of the count[] & code[] arrays.
	  public static final int NUMBER_OF_CHARACTERS = 256;
	 
	  // the contents of our message...
	  private String contents;
	 
	  // the tree created from the msg
	  private HuffmanTree huffmanTree;
	 
	  // tracks how often each character occurs
	  private int count[];
	 
	  // the huffman code for each character
	  private String code[];
	 
	  // stores the # of unique chars in contents
	  private int uniqueChars = 0; //(optional)
	 
	  /** Constructor taking input String to be converted */
	  public HuffmanConverter(String input)
	  {
	    this.contents = input;
	    this.count = new int[NUMBER_OF_CHARACTERS];
	    this.code = new String[NUMBER_OF_CHARACTERS];
	  }
	 
	  /**
	   * Records the frequencies that each character of our
	   * message occurs...
	   * I.e., we use 'contents' to fill up the count[] list...
	   */
	  public void recordFrequencies() throws Exception
	  {
		  for (int i = 0; i < this.contents.length(); i++)
			  count[(int) this.contents.charAt(i)]++;
		  
		  for (int j = 0; j < this.count.length; j++)
			  if (count[j] > 0) this.uniqueChars++;
	  }
	 
	  /**
	   * Converts our frequency list into a Huffman Tree. We do this by
	   * taking our count[] list of frequencies, and creating a binary
	   * heap in a manner similar to how a heap was made in HuffmanTree's
	   * fileToHeap method. Then, we print the heap, and make a call to
	   * HuffmanTree.heapToTree() method to get our much desired
	   * HuffmanTree object, which we store as huffmanTree.
	   */
	  public void frequenciesToTree()  
	  {  
		  HuffmanNode [] nodes = new HuffmanNode [this.uniqueChars];
		  
		  int j = 0;
		  for (int i = 0; i < count.length; i++)
		  {
			  if (count[i] != 0)
			  {
				  nodes[j] = new HuffmanNode ("" + (char) i, Double.parseDouble("" + count[i]));
				  j++;
			  }
		  }
		  
		  BinaryHeap<HuffmanNode> bheap = new BinaryHeap<HuffmanNode> (nodes);
		  
		  System.out.println(bheap);
		  
		  this.huffmanTree = HuffmanTree.createFromHeap(bheap);

	  }
	 
	  /**
	   * Iterates over the huffmanTree to get the code for each letter.
	   * The code for letter i gets stored as code[i]... This method
	   * behaves similarly to HuffmanTree's printLegend() method...
	   * Warning: Don't forget to initialize each code[i] to ""
	   * BEFORE calling the recursive version of treeToCode...
	   */
	  public void treeToCode() 
	  {
		  treeToCode(huffmanTree.getRoot(), "");
	  }
	 
	  /*
	   * A private method to iterate over a HuffmanNode t using s, which
	   * contains what we know of the HuffmanCode up to node t. This is
	   * called by treeToCode(), and resembles the recursive printLegend
	   * method in the HuffmanTree class. Note that when t is a leaf node,
	   * t's letter tells us which index i to access in code[], and tells
	   * us what to set code[i] to...
	   */
	  private void treeToCode(HuffmanNode t, String s)
	  {
		  if (t.letter.length() > 1)
		  {
			  treeToCode(t.left, s + "0");
			  treeToCode(t.right, s + "1");
		  }
		  else if (t.letter.length() == 1)
			  code[(int) t.letter.charAt(0)] = s;
	  }
	 
	  /**
	   * Using the message stored in contents, and the huffman conversions
	   * stored in code[], we create the Huffman encoding for our message
	   * (a String of 0's and 1's), and return it...
	   */
	  public String encodeMessage()
	  {
		  String encoded = "";
		  for (int i = 0; i < contents.length(); i++)
			  encoded += code[(int) contents.charAt(i)];
		  return encoded;
	  }
	 
	  /**
	   * Reads in the contents of the file named filename and returns
	   * it as a String. The main method calls this method on args[0]...
	   */
	  public static String readContents(String filename) throws Exception
	  {
		  BufferedReader in = new BufferedReader(new FileReader (filename));
		  String fileToString = "";
		  
		  while (true)
		  {
			  String line = in.readLine();
			  if(line == null) break;
			  fileToString = fileToString + line + '\n';
		  }
		  
		  return fileToString;
	  }
	 
	  /**
	   * Using the encoded String argument, and the huffman codings,
	   * re-create the original message from our
	   * huffman encoding and return it...
	   */
	  public String decodeMessage(String encodedStr)
	  {
		  String decoded = "";
		  HuffmanNode n = this.huffmanTree.getRoot();
		  
		  for (int i = 0; i < encodedStr.length(); i++)
		  {
			  if (encodedStr.charAt(i) == '0')
			  {
				  if (n.left != null) n = n.left;
				  else
				  {
					  decoded += n.letter;
					  n = this.huffmanTree.getRoot().left;
				  }
			  }
			  if (encodedStr.charAt(i) == '1')
			  {
				  if (n.right != null) n = n.right;
				  else
				  {
					  decoded += n.letter;
					  n = this.huffmanTree.getRoot().right;
				  }
			  }
				  
		  }
		  
		  return decoded;
	  }
	 
	  /**
	   * Uses args[0] as the filename, and reads in its contents. Then
	   * instantiates a HuffmanConverter object, using its methods to
	   * obtain our results and print the necessary output. Finally,
	   * decode the message and compare it to the input file.<p>
	   * NOTE: Example method provided below...
	   */
	  public static void main(String args[]) throws Exception
	  {

		  String fileString = HuffmanConverter.readContents(args[0]);
		  HuffmanConverter huffer = new HuffmanConverter (fileString);
		  
		  huffer.recordFrequencies();
		  huffer.frequenciesToTree();
		  huffer.treeToCode();
		  
		  huffer.huffmanTree.printLegend();
		  
		  String message = huffer.encodeMessage();
		  System.out.println("\nHuffman Encoding:\n" + message);
		  System.out.println("\nMessage size in ASCII encoding = " + (huffer.contents.length() * 8));
		  System.out.println("Message size in Huffman encoding = " + (message.length()));
		  
		  System.out.println("\n" + huffer.decodeMessage(message));
	  }
}
