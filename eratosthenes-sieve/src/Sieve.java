
public class Sieve 
{
//	Constructor
	public Sieve ()
	{
		
	}
	
	/**
	 * This method takes a number as a parameter and prints
	 * a list of the prime numbers between 0 and the upper
	 * bound.
	 * @param Upper bound integer, >2
	 */
	public void primesTo (int n) throws Exception
	{
//		Instantiates a queue and populates it with the numbers from 2 to n
		ArrayUnbndQueue <Integer> numbers = new ArrayUnbndQueue <Integer> ();
		for (int i = 2; i <= n; i++)
			numbers.enqueue(i);
		
//		Instantiate a queue to hold the values of all the primes in the range
		ArrayUnbndQueue <Integer> primes = new ArrayUnbndQueue <Integer> ();
		
		int p = numbers.dequeue();
		double sqrtn = Math.sqrt(n);
		
		while (p <= sqrtn)
		{
			primes.enqueue(p);
//			Instantiate a queue to hold the numbers not divisible by p
			ArrayUnbndQueue <Integer> indivisible = new ArrayUnbndQueue <Integer> ();
			while (!numbers.isEmpty())
			{
				int q = numbers.dequeue();
				if (q%p != 0)
					indivisible.enqueue(q);
			}
//			assign the queue of indivisible numbers to ref var numbers
			numbers = indivisible; 
			p = numbers.dequeue();
		}
		
//		push the remaining primes into the primes queue 
		primes.enqueue(p);
		while (!numbers.isEmpty())
		{
			primes.enqueue(numbers.dequeue());
		}
		
//		print the list of all the primes
		System.out.print("\nPrimes up to " + n + " are: ");
		while (!primes.isEmpty())
			System.out.print(primes.dequeue() + " ");
	}

}
