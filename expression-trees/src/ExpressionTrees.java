


import java.util.*;

public class ExpressionTrees
{
	
	public static void main (String [] args)
	{
		Scanner input = new Scanner (System.in);
		boolean more = true;
		char moreQ;
		
		while (more)
		{
			try
			{
				System.out.println ("Enter expression: ");
				String expression = input.nextLine();
				Node tree = toTree(expression);
				
				System.out.println("Using preorder : "  + preorder(tree));
				System.out.println("Using inorder  : "   + inorder(tree));
				System.out.println("Using postorder: " + postorder(tree));

			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
			
			System.out.println("\nDo you want to convert another expression? Y/N");
			moreQ = input.nextLine().charAt(0);
			if (moreQ == 'n' || moreQ == 'N')
			{
				more = false;
				System.out.println("\nThanks for using the converter.");
			}
		}	
		
		input.close();

	}
	
	public static Node toTree (String infix) throws Exception
	{
		ArrayStack <Node> stack = new ArrayStack <Node> ();
		Converter converter = new Converter ();
		String postfix = converter.toPostFix(infix);
		StringTokenizer st = new StringTokenizer (postfix);
		
		while (st.hasMoreTokens())
		{
			String s = st.nextToken();
			if (isNum(s))
				stack.push(new Node(s));
			else
			{
				Node operator = new Node (s);
				operator.setRight(stack.top());
				stack.pop();
				operator.setLeft(stack.top());
				stack.pop();
				stack.push(operator);
			}
		}
		
		return stack.top();
	}
	
	public static String preorder (Node tree)
	{
		String expression = "";
		expression = expression + tree.getInfo() + " ";
		if (tree.getLeft() != null)
		{
			expression += preorder(tree.getLeft());
			expression += preorder(tree.getRight());
		}
		return expression;
	}
	
	public static String inorder (Node tree)
	{
		String expression = "";
		if (tree.getLeft() != null)
		{
			if (precedence(tree.getInfo().charAt(0)) >= precedence(tree.getLeft().getInfo().charAt(0)))
				expression = expression + "(" + inorder(tree.getLeft()) + ")";
			else
				expression = expression + inorder(tree.getLeft());
			expression = expression + tree.getInfo();
			if (precedence(tree.getInfo().charAt(0)) >= precedence(tree.getRight().getInfo().charAt(0)))
				expression = expression + "(" + inorder(tree.getRight()) + ")";
			else
				expression = expression + inorder(tree.getRight());
		}
		else
			expression = expression + tree.getInfo();
		return expression;
	}
//	If precedence of child operator is lower than parent, then parenthesis over child expression
	
	public static String postorder (Node tree)
	{
		String expression = "";
		if (tree.getLeft() != null)
		{
			expression += postorder(tree.getLeft());
			expression += postorder(tree.getRight());
			expression = expression + tree.getInfo() + " ";
		}
		else
			expression = expression + tree.getInfo() + " ";
		return expression;
	}
	
	/**
	 * This method determines whether the first
	 * character of a string is a number or not
	 * @param String
	 * @return true if character at index 0 is
	 * 			a number
	 */
	public static boolean isNum (String s)
	{
		char c = s.charAt(0);
		if (c >= '0' && c <= '9')
			return true;
		else
			return false;
	}
	
	/**
	 * This method determines whether the first
	 * character of a string is an operator or not
	 * @param String
	 * @return true if character at index 0 is
	 * 			an operator
	 */
	public static boolean isOper (String s)
	{
		char c = s.charAt(0);
		if (c == '^' || c == '*' ||
				c == '/' || c == '+' || c == '-')
			return true;
		else
			return false;
	}
	
	public static int precedence (char c)
	{
		if (c == '^')
			return 3;
		else if (c == '*' | c == '/')
			return 2;
		else if (c == '+' | c == '-')
			return 1;
		else
			return 4;
	}

}
