
public class Player {

	static double taxes = 0.05;

	int resourceId, rating;
	double lowestPrice, secondLowestPrice;
	String firstName, lastName, timeUpdated, nationality;

	public Player(String[] attrs) {
		resourceId = Integer.parseInt(attrs[0]);
		firstName = attrs[1];
		lastName = attrs[2];
		rating = Integer.parseInt(attrs[3]);
		nationality = attrs[4];
	}

	public boolean isProfitable() {
		return (lowestPrice * (1 + taxes) < secondLowestPrice);
	}

	public double profit() {
		return secondLowestPrice - lowestPrice * (1 + taxes);
	}

	public double price() {
		return lowestPrice * (1 + taxes);
	}

	public String toString() {
		return firstName + " " + lastName + ", " + rating + ", " + nationality + "\n\tLowestPrice: " + lowestPrice
				+ "\n\t2nd Lowest Price: " + secondLowestPrice + "\n\tTime updated: " + timeUpdated;
	}

}
