import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestInventory {

	public static void main (String [] args) throws FileNotFoundException {

		Inventory movieList = new Inventory ();

		// Create new File object to handle database
		File file = new File ("movies_db.txt");

		// Check if file actually exists, if not, print error message and exit
		if (!file.exists() || !file.canRead() ) {
			System.err.println("ERROR: cannot read file movies_db.txt.\n\n");
			System.exit(1);
		}
		
		Scanner input = new Scanner (file);
		
		// While loop to enter data from database into inventory
		while (input.hasNext()) {
			String line = input.nextLine ();
			// Use split() method to extract title, year of release and rating
			String [] movieData  = line.split("-");
			movieList.returnMovie ( movieData[0].trim(), 
									Integer.parseInt(movieData[1].trim()), 
									Double.parseDouble(movieData[3].trim()) );			
		}

		// should be added to the inventory or count of movies should increase
		movieList.returnMovie ("Bears", 2014, 3.5);
		movieList.returnMovie ("Star Wars - A New Hope", 1977, 3.8);
		movieList.returnMovie ("Casablanca", 1942, 3.9);
		movieList.returnMovie ("Duck Soup", 1933, 3.75);

		System.out.println ( movieList.toString() );

		// the count of Casablanca should increase
		movieList.returnMovie ("Casablanca", 1942, 3.9);
		System.out.println ( movieList.toString() );

		//this movie should not be added since it has invalid year 
		movieList.returnMovie ("Bears", 2050, 3.5);
		System.out.println ( movieList.toString() );

		// nothing should happen because movie not in inventory
		movieList.rent ("The Nightmare Before Christmas", 1993);
		System.out.println ( movieList.toString() );
		
		// should be removed from the inventory completely
		movieList.rent ("Star Wars - A New Hope", 1977);
		System.out.println ( movieList.toString() );
		
		// count should decrease
		movieList.rent ("Duck Soup", 1933);
		System.out.println ( movieList.toString() );

		input.close();

	}
}