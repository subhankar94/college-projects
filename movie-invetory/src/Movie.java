/**
 * This class represents a movie.
 * @author  subhankarghosh
 * @version Nov 03, 2014
 *
 */
public class Movie {

	private String title;
	private int yearReleased;
	private double rating;
	private int quantity;

	/**
	 * Constructor that takes as parameters title of the movie, number in inventory,
	 * rating and adds the movie to the inventory if the parameters are valid, else
	 * throws exception.
	 * @param title
	 * @param yearReleased
	 * @param rating
	 * @throws IllegalArgumentException
	 */
	public Movie (String title, int yearReleased, double rating) throws IllegalArgumentException {
		if (title != "") 
			this.title = title;
		else 
			throw new IllegalArgumentException ( "invalid title" );
		if (yearReleased >= 1870 && yearReleased <= 2014) 
			this.yearReleased = yearReleased;
		else
			throw new IllegalArgumentException ( "invalid year" );
		if (rating >= 0.0 && rating <= 4.0)
			this.rating = rating;
		else
			throw new IllegalArgumentException ( "invalid rating" );
		this.quantity = 1;
	}

	/**
	 * increases quantity of movies in inventory by one 
	 */
	public void incrementQuantity () {
		this.quantity += 1;
	}

	/**
	 * decreases quantity of movies in inventory by one 
	 */
	public void decrementQuantity () {
		this.quantity -= 1;
	}

	/**
	 * @return quantity of movies in inventory
	 */
	public int getQuantity () {
		return quantity; 
	}

	/**
	 * Returns the title of the movie object
	 * @return title of the movie
	 */
	public String getTitle () {
		return title;
	}

	/**
	 * This method modifies the title of the movie object if the 
	 * parameter is not null, otherwise throws exception
	 * @param title
	 */
	public void setTitle (String title) throws IllegalArgumentException {
		if (!title.equals("")) 
			this.title = title;
		else 
			throw new IllegalArgumentException ( "invalid title" );
	}

	/**
	 * Returns the year of release of this movie object
	 * @return year of release
	 */
	public int getYear () {
		return yearReleased;
	}

	/**
	 * This method checks if the year value provided is between 1870 & 2014
	 * and modifies the yearOfRelease as such, otherwise throws exception
	 */
	public void setYear (int year) throws IllegalArgumentException {
		if (year >= 1870 && year <= 2014) 
			this.yearReleased = year;
		else
			throw new IllegalArgumentException ( "invalid year" );
	}

	/**
	 * Returns the rating of this movie object
	 * @return rating of movie
	 */
	public double getRating () {
		return rating;
	}

	/**
	 * This method checks if the rating value provided is between 0.0 & 4.0
	 * and modifies the rating value as such, otherwise throws exception
	 */
	public void setRating (double rating) throws IllegalArgumentException {
		if (rating >= 0.0 && rating <= 4.0)
			this.rating = rating;
		else
			throw new IllegalArgumentException ( "invalid rating" );
	}

	/** 
	 * Returns the string representation of the object
	 */
	@Override
	public String toString () {
		return String.format("%-50s (%4d), Rating:%4.1f/4.0, # in stock: %-4d", 
							title, yearReleased, rating, quantity);
	}

}
