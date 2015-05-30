
import java.util.Scanner;

public class Eratosthenes 
{
	public static void main (String [] args)
	{
		Scanner stdin = new Scanner (System.in);
		System.out.println("Please enter an upper bound integer >2 :");
		
		int n = stdin.nextInt();
//		Ensure the upper bound integer is valid
		while (!(n > 2))
		{
			System.out.println("Invalid input. Please enter an integer >2 :");
			n = stdin.nextInt();
		}
			
//		Instantiate the sieve class
		Sieve newSieve = new Sieve ();
		
		try
		{
//			print the list of all the primes up to n
			newSieve.primesTo(n);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		stdin.close();
	}
}
