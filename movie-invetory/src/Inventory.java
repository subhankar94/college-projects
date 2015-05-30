import java.util.ArrayList;

/**
 * This class represents a movie inventory, and uses
 * an ArrayList to hold the data
 * 
 * @author subhankarghosh
 * @version Nov 03, 2014
 *
 */
public class Inventory {
	
	private ArrayList <Movie> list;

	/**
	 * Constructs a new object and creates an arrayList
	 */
	public Inventory () {
		this.list = new ArrayList <Movie> ();
	}

	/**
	 * Method for returning a movie to the inventory that takes
	 * as parameters title, year of release and rating. Checks if
	 * the movie is already in the inventory, if so, increments
	 * the quantity by one. Else creates a new movie object and
	 * adds it to the list if parameters are valid.
	 * @param title
	 * @param yearReleased
	 * @param rating
	 */	
	public void returnMovie (String title, int yearReleased, double rating) {
		try {
			for (int i = 0; i < this.list.size(); i++) {
				if (this.list.get(i).getTitle().equalsIgnoreCase(title) && this.list.get(i).getYear() == yearReleased) {
					this.list.get(i).incrementQuantity();
					this.list.get(i).setRating(rating);
					return;
				}
			}
			this.list.add ( new Movie (title, yearReleased, rating) );
		}
		catch (IllegalArgumentException e) {}
	}

	/** If the movie matching the title and year exist in the inventory,
	 * its count is decreased by 1. If the count of a given movie goes 
	 * down to zero, it is completely removed from the inventory.
	 * @param title
	 * @param yearReleased
	 */
	public void rent (String title, int yearReleased) {
		for (int i = 0; i < this.list.size(); i++) {
			if (this.list.get(i).getTitle().equalsIgnoreCase(title) && this.list.get(i).getYear() == (yearReleased)) {
				this.list.get(i).decrementQuantity();
				if (this.list.get(i).getQuantity() == 0)
					list.remove(i);
				break;
			}
		}
	}

	@Override
	public String toString () {
		String list = "";
		for (int i = 0; i < this.list.size(); i++) {
			list = list + this.list.get(i).toString() + "\n";
		}
		list += "-------------------------------";
		return list;
	}

}