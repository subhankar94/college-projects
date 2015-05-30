

import java.util.Scanner;
import Converter;

public class Calculator 
{	
	public static void main (String [] args)
	{	
			Scanner input = new Scanner (System.in);
			
			Converter conv = new Converter ();
			
			boolean more = true;
			char moreQ;
			
			while (more)
			{
				try
				{
					System.out.println ("Enter expression: ");
					String userIFExp = input.nextLine();
					String userPFExp = conv.toPostFix(userIFExp);
					System.out.println("\nConverted to postfix: " + userPFExp);

					float result = (float) calculate(userPFExp);
			
					if(!Double.isInfinite(result))
						System.out.println("\nAnswer is " + (int) result);
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
				System.out.println("\nDo you want to evauate another expression? Y/N");
				moreQ = input.nextLine().charAt(0);
				if (moreQ == 'n' || moreQ == 'N')
				{
					more = false;
					System.out.println("\nThanks for using the calculator.");
				}
			}	
			
			input.close();

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
	
	/**
	 * This method calculates the value of an
	 * expression. It takes a string representing
	 * a postfix expression as the parameter and
	 * performs the calculation using two stacks.
	 * 
	 * @param String postfix expression
	 * @return value of expression 
	 */
	public static double calculate (String s)
	{
		double value = 0;
		
		ArrayStack<String> exp = new ArrayStack<String> ();
		ArrayStack<String> cal = new ArrayStack<String> ();
		
		String [] userArray = s.split(" ");
		try
		{
			for (int i = userArray.length - 1; i >= 0; i--)
			{
				exp.push(userArray[i]);
			}
			do
			{
				if (isNum(exp.top()))
				{
						cal.push(exp.top());
						exp.pop();
				}
				else if (!cal.isEmpty())
				{
					String operator = exp.top();
					exp.pop();
					double op2 = Double.parseDouble(cal.top());
					cal.pop();
					double op1 = Double.parseDouble(cal.top());
					cal.pop();
					switch (operator.charAt(0))
					{
					case '^' : value = Math.pow (op1, op2); break;
					case '*' : value = op1*op2; break;
					case '/' : value = op1/op2; break;
					case '+' : value = op1+op2; break;
					case '-' : value = op1-op2; break;
					default  : break;
					}
					exp.push("" + value);
				}
				else
					throw new Exception ("\nInvalid expression");

			} while (!exp.isEmpty());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return value;
	}


}