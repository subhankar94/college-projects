

public class HuffmanNode implements Comparable<Object>
{
   public String letter; 
   public Double frequency;
   public HuffmanNode left, right;
   
   public HuffmanNode (String letter, Double frequency)
   {
	   this.letter = letter;
	   this.frequency = frequency;
	   left = null;
	   right = null;
   }
   
   public HuffmanNode (HuffmanNode left, HuffmanNode right)
   {
	   this.left = left;
	   this.right = right;
	   letter = left.letter + right.letter;
	   frequency = left.frequency + right.frequency;
   }
   
   public String toString ()
   {
	   if (letter.equals("\n")) return "<\\n, "+ (int) frequency.doubleValue()+">";
	   else return "<"+letter+", "+ (int) frequency.doubleValue()+">";
   }
   
   public int compareTo (Object o)
   {
	   HuffmanNode huff = (HuffmanNode) o;
	   return this.frequency.compareTo(huff.frequency);
   }
 
   // - add constructors to initialize letter and frequency, etc.
   // - add int compareTo(Object o) method so we can compare 
   //   two HuffmanNodes based on their frequency attributes. 
   // - we're going to build a .heap. of these HuffmanNodes...
}
