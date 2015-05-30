
import ArrayStack;

public class Converter 
{
	/**
	 * This method takes an infix expression as an
	 * input in the form of a string and converts 
	 * it to a postfix expression by evaluating the
	 * string character by character and performing
	 * relevant operations
	 * 
	 * @param String infix expression
	 * @return String postfix expression
	 */
	public String toPostFix (String iFExp) throws Exception
	{		
//		Initialize string to be returned
		String pFExp = "";
//		Create stack of characters to deal with operators
		ArrayStack<Character> stack = new ArrayStack<Character> ();
		
//		int variables to ensure number of open and
//		close parenthesis in expression are equal
		int numOpen = 0;
		int numClose = 0;
		
//		for loop to run through the input string and
//		perform relevant operation on character in string
		for (int i = 0; i < iFExp.length(); i++)
		{
//			switch statement to determine what operation
//			to perform on character in the string.
//			If number, append; if space, ignore; if 
//			operator check precedence and either push to 
//			stack or append appropriate operator; if open
//			parenthesis push to stack; if close parenthesis
//			pop operators from stack into string until first 
//			open parenthesis, then pop that from stack; if
//			not number, operator or space--throw exception
			char c = iFExp.charAt(i);
			switch (c) 
			{
			case ' ' : break;
			case '0' :
			case '1' :
			case '2' :
			case '3' :
			case '4' :
			case '5' :
			case '6' :
			case '7' :
			case '8' :
			case '9' : pFExp = pFExp + c; 
						break;
			case '(' : stack.push(c); numOpen++; break;
			case ')' : pFExp = pFExp + ' '; 
						while (!stack.isEmpty())
						{
							if (stack.top() != '(')
							{
								pFExp = pFExp + stack.top();
								stack.pop();
							}
							else
								break;
						}
						numClose++;
						if (!stack.isEmpty() && stack.top() == '(')
							stack.pop();
						else
							throw new Exception ("Invalid input at position " + i); break;
			case '^' : 
			case '*' :
			case '/' :
			case '+' :
			case '-' : pFExp = pFExp + ' '; 
						while (!stack.isEmpty() && stack.top() != '(' && !precedence (c, stack.top()))
						{
							pFExp = pFExp + stack.top() + ' ';
							stack.pop();
						}
						stack.push(c); break; 
			default : throw new Exception ("Invalid input at position " + i);
			}
		}
	while (!stack.isEmpty())
	{	
		if (stack.top() == '(')
			stack.pop();
		else
		{
			pFExp = pFExp + ' ' + stack.top();
			stack.pop();
		}

	}
//	Check if number of open and close parenthesis correct
	if (numOpen != numClose)
		throw new Exception ("\nInvalid Expression");
	return pFExp;
	}
	
	/**
	 * This method takes two operators and evaluates
	 * their relative precedence
	 * 
	 * @param character c1
	 * @param character c2
	 * @return true if character c1 has higher precedence
	 * 			than character c2
	 */
	public static boolean precedence (char c1, char c2)
	{
		if (c2 == '(')
			return true;
		else if (c1 == '^' && c2 != '^')
			return true;
		else if (c1 == '*' || c1 == '/')
		{
			if (c2 == '^' || c2 == '/' || c2 == '*')
				return false;
			else
				return true;	
		}
		else
			return false;
	}

}
