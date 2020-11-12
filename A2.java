/**
 * PUT YOUR STUDENT NAME & NUMBER HERE
 */
public class A2 {
	/*
	 * YOU MAY MODIFY THIS DATA FOR TESTING PURPOSES YOU MUST MAKE SURE YOUR FINAL
	 * SUBMISSION CONTAINS THIS DATASET UNMODIFIED.
	 */
	private String[] menu = { "INDIAN|Biryani|16.99", "VIETNAMESE|17.00", "CHINESE|Chicken with Black Bean Sauce|16.5",//定义类型为字符串数组并赋值
			"MIDDLE_EASTERN|Falafel|17.99", "Indian|Butter Chicken|17.00", "THAI|Pad Thai|23.00",
			"|Orange & Poppyseed Cake|15.99", "GREEK|Moussaka|18.50", "JAPANESE|Sushi|", "ITALIAN|Lasagne|18.50",
			"INDIAN|Dhal Tadka|9.50", "INDIAN|Brinjal|11.50" };

	/*
	 * YOU MAY MODIFY CODE IN THIS METHOD FOR TESTING PURPOSES YOU MUST MAKE SURE
	 * YOUR FINAL SUBMISSION CONTAINS THIS METHOD IN ITS UNMODIFIED STATE.
	 */
	public void run() {
		String preliminary = "\n\nPLEASE NOTE: This code represents initial test data\n"
				+ "You need to thoroughly test your solution.\n"
				+ "During assessment we will change the values in these\n"
				+ "tests and change the underlying dataset.\n" + "When you submit your final version make sure that\n"
				+ "the code in this run method has not been changed.\n\n"
				+ "You should also focus on best practice code by writing \n"
				+ "cohesive methods and following the principles taught in this course\n\n";

		System.out.println(preliminary);
		/**
		 * TESTING Meal Summary
		 */
		System.out.println("TESTING - MEAL SUMMARY (VALID)");
		System.out.println("(16.99 - 18.50)");
		String result = getMealSummaryInRange(16.99, 18.50);
		System.out.println(result);

		System.out.println("TESTING - MEAL SUMMARY (NOT FOUND)");
		System.out.println("(30.00 - 50.00)");
		result = getMealSummaryInRange(30.00, 50.00);
		System.out.println(result);
		System.out.println("------------");

		/**
		 * TESTING Get Change
		 */
		System.out.println("TESTING - GET CHANGE (VALID)");
		System.out.println("(Biryani,Falafel, Moussaka, Lasagne)");
		String[] mealsOrdered = { "Biryani", "Falafel", "Moussaka", "Lasagne" };
		result = getChange(mealsOrdered, 123.00);
		System.out.println(result);

		System.out.println("TESTING - GET CHANGE (INSUFFICIENT FUNDS)");
		System.out.println("(Biryani,Falafel, Moussaka, Lasagne)");
		result = getChange(mealsOrdered, 53.99);
		System.out.println(result);

		System.out.println("\nTESTING - GET CHANGE (EMPTY)");
		mealsOrdered[0] = "";
		mealsOrdered[1] = "";
		mealsOrdered[2] = "";
		mealsOrdered[3] = "";
		result = getChange(mealsOrdered, 123.00);
		System.out.println(result);

		System.out.println("\nTESTING - GET CHANGE (NULL)");
		mealsOrdered = null;
		result = getChange(mealsOrdered, 123.00);
		System.out.println(result);
		System.out.println("------------");

		/**
		 * TESTING Get Change
		 */
		System.out.println("\nTESTING - MEALS BY CUISINE");
		System.out.println("(INDIAN)");
		String[] mealsByCuisine = getMealsByCuisine("INDIAN");
		for (int i = 0; i < mealsByCuisine.length; i++) {
			System.out.println(mealsByCuisine[i]);
		}

		System.out.println("\nTESTING - MEALS BY CUISINE");
		System.out.println("(RUSSIAN)");
		mealsByCuisine = getMealsByCuisine("RUSSIAN");
		for (int i = 0; i < mealsByCuisine.length; i++) {
			System.out.println(mealsByCuisine[i]);
		}

		System.out.println("\nTESTING - MEALS BY CUISINE");
		System.out.println("(Empty)");
		mealsByCuisine = getMealsByCuisine("");
		for (int i = 0; i < mealsByCuisine.length; i++) {
			System.out.println(mealsByCuisine[i]);
		}

		System.out.println("\nTESTING - MEALS BY CUISINE");
		System.out.println("(NULL)");
		mealsByCuisine = getMealsByCuisine(null);
		for (int i = 0; i < mealsByCuisine.length; i++) {
			System.out.println(mealsByCuisine[i]);
		}

		System.out.println("------------");

		System.out.println("\nTESTING - MEALS HISTOGRAM");
		result = mealsAvailableHistogram();
		System.out.println(result);
		System.out.println("------------");

	}

	/**
	 * Implement Q1, you must implement the body of this method NOTE: EACH OF THE
	 * PRIMARY METHODS FOR THE QUESTIONS WILL BE TESTED INDIVIUDALLY WITHOUT
	 * REFERENCE TO ANY PRE-VALIDATION YOU MIGHT IMPLEMENT
	 */
	private String getMealSummaryInRange(double startPrice, double upperPrice) {
		// Implement the method logic here
		String result = "";
		StringBuilder sb = new StringBuilder();
		// process the menu array
		for (String string : menu) {  //取出数组 menu[],每个元素都取出赋值给 string
			String temp[] = string.split("\\|");
			boolean allCap = true;
			// check the CUISINE is all in caps
			for (int i = 0; i < temp[0].length(); i++) {
				if (Character.isLowerCase(temp[0].charAt(i))) {
					allCap = false;
					break;
				}
			}

			// check missing values/empty string
			if (temp.length < 3 || temp[0].equals("") || temp[1].equals("")) {
				// skip this
			} else if (allCap) {
				double price = Double.parseDouble(temp[2]);
				if (price <= upperPrice && price >= startPrice) {
					// formated
					String priceString = "$" + String.format("%.2f", price);
					String finalString = String.format("%-20s %10s%n", temp[1], priceString);
					sb.append(finalString);
				}

			}

		}
		result = sb.toString();
		if (result.equals("")) {
			result = "No meals found within range.";
		}

		return result;
	}

	/**
	 * Implement Q2, you must implement the body of this method NOTE: EACH OF THE
	 * PRIMARY METHODS FOR THE QUESTIONS WILL BE TESTED INDIVIUDALLY WITHOUT
	 * REFERENCE TO ANY PRE-VALIDATION YOU MIGHT IMPLEMENT
	 */
	private String getChange(String[] meals, double paid) {
		// Implement the method logic here
		String result = "";
		double sum = 0;
		StringBuilder sb = new StringBuilder();
		// check for the amount tendered
		if (paid <= 0) {
			result = "ERROR!The amount tendered is $0.00 or less.";
			return result;
		}
		// check for null meals
		if (meals == null) {
			result = "The method was passed a null value for meals.";
			return result;
		}
		// process Menu
		String processedMenu[] = new String[menu.length];
		int INDEX = 0;
		for (String string : menu) {
			String temp[] = string.split("\\|");
			// check the CUISINE is all in caps
			boolean allCap = true;
			for (int i = 0; i < temp[0].length(); i++) {
				if (Character.isLowerCase(temp[0].charAt(i))) {
					allCap = false;
					break;
				}
			}

			// check missing values/empty string
			if (temp.length < 3 || temp[0].equals("") || temp[1].equals("")) {
				// skip this
			} else if (allCap) {
				processedMenu[INDEX] = string;
				INDEX++;
			}
		}
		// process meals
		for (String string : meals) {
			if (string.equals("")) {
				result = "Your meals data is invalid.";
				return result;
			}
		}
		// calculate sum
		for (String string : meals) {
			for (String string2 : menu) {
				String price[] = string2.split("\\|");
				if (string.equals(price[1])) {
					sum += Double.parseDouble(price[2]);
				}
			}
		}
		// what format?
		if (paid > sum) {
			sb.append(String.format("%-30s %10s%n", "You tendered:", "$" + String.format("%.2f", paid)));
			sb.append(
					String.format("%-30s %10s%n", "The total cose of the meal is:", "$" + String.format("%.2f", sum)));

			sb.append(String.format("%-30s %10s%n", "Your change:", "$" + String.format("%.2f", paid - sum)));
		}
		// if insufficient
		if (paid < sum) {
			String remove = "";
			for (String string : meals) {
				for (String string2 : menu) {
					String price[] = string2.split("\\|");
					if (string.equals(price[1])) {
						double tempPrice = Double.parseDouble(price[2]);
						if (sum - tempPrice < paid) {
							remove = string;
						}
					}
				}
			}
			sb.append(String.format("%-30s %10s%n", "You tendered:", "$" + String.format("%.2f", paid)));
			sb.append(
					String.format("%-30s %10s%n", "The total cose of the meal is:", "$" + String.format("%.2f", sum)));
			sb.append(String.format("You provided insufficient funds. Please remove %s from your order.%n", remove));

		}
		result = sb.toString();
		return result;
	}

	/**
	 * Implement Q3, you must implement the body of this method NOTE: EACH OF THE
	 * PRIMARY METHODS FOR THE QUESTIONS WILL BE TESTED INDIVIUDALLY WITHOUT
	 * REFERENCE TO ANY PRE-VALIDATION YOU MIGHT IMPLEMENT
	 */
	private String[] getMealsByCuisine(String cuisine) {
		// Implement the method logic here
		// check the cuisine passed in:
		if (cuisine == null) {
			String result[] = { "A null value was provided for cuisine." };
			return result;
		}
		if (cuisine.equals("")) {
			String result[] = { "Meal not found." };
			return result;
		}

		// process Menu
		String processedMenu[] = new String[menu.length];
		int INDEX = 0;
		StringBuilder sb = new StringBuilder("");
		for (String string : menu) {
			String temp[] = string.split("\\|");
			// check the CUISINE is all in caps
			boolean allCap = true;
			boolean noMissing = true;
			// check missing values/empty string
			if (temp.length < 3 || temp[0].equals("") || temp[1].equals("")) {
				noMissing = false;
			}
			if (noMissing) {
				for (int i = 0; i < temp[0].length(); i++) {
					if (Character.isLowerCase(temp[0].charAt(i))) {
						allCap = false;
						break;
					}
				}
			}
			if (allCap && noMissing) {
				processedMenu[INDEX] = string;
				INDEX++;
			}
		}
		INDEX = 0;
		for (String string : processedMenu) {
			if (string != null) {
				String eachMeal[] = string.split("\\|");
				if (eachMeal[0].equals(cuisine)) {
					sb.append(eachMeal[1] + "*");
					INDEX++;
				}
			}
		}
		// you will need to modify this initialisation
		String[] result = sb.toString().split("\\*");
		if (sb.toString().equals("")) {
			result = new String[] { "Meal not found." };
		}

		return result;
	}

	/**
	 * Implement Q4, you must implement the body of this method NOTE: EACH OF THE
	 * PRIMARY METHODS FOR THE QUESTIONS WILL BE TESTED INDIVIUDALLY WITHOUT
	 * REFERENCE TO ANY PRE-VALIDATION YOU MIGHT IMPLEMENT
	 */
	private String mealsAvailableHistogram() {
		// Implement your method logic here
		String stringResult = "";

		// for the final result
		StringBuilder sb = new StringBuilder();
		// for the processed menu
		StringBuilder processMenu = new StringBuilder();
		// to store unique cuisine names
		StringBuilder cuisineBuilder = new StringBuilder();

		int invalidCount = 0;

		// process Menu
		for (String string : menu) {
			String temp[] = string.split("\\|");
			String cuisine = temp[0];
			// check the CUISINE is all in caps
			boolean allCap = true;
			boolean noMissing = true;

			if (!temp[0].equals("")) {
				for (int i = 0; i < temp[0].length(); i++) {
					if (Character.isLowerCase(temp[0].charAt(i))) {
						allCap = false;
						break;
					}
				}
				// check if this cuisine already existed
				if (allCap && !cuisineBuilder.toString().contains(cuisine)) {
					cuisineBuilder.append(cuisine + "*");
				}
			}
			// check missing values/empty string
			if (temp.length < 3 || temp[0].equals("") || temp[1].equals("")) {
				noMissing = false;
			}
			if (allCap && noMissing) {
				// add valid meal to the processed menu array
				processMenu.append(string + "*");

			} else {
				invalidCount++;
			}
		}
		// get processed menu array and cuisine array respectively
		String processedMenu[] = processMenu.toString().split("\\*");
		String cuisineArray[] = cuisineBuilder.toString().split("\\*");

		// loop through cuisine array
		for (String cuisine : cuisineArray) {
			int count = 0;
			// loop through the processed menu array to count the cuisine
			for (String string : processedMenu) {
				String tempCuisine = string.split("\\|")[0];
				if (cuisine.equals(tempCuisine)) {
					count++;
				}
			}
			sb.append(String.format("%-30s%d%n", cuisine, count));

		}
		sb.append(String.format("%-30s%d%n", "INVALID:", invalidCount));

		stringResult = sb.toString();
		return stringResult;
	}
}