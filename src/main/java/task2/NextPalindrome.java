package task2;

import java.util.Scanner;

public class NextPalindrome {

	public int nextPalindrome(int number) {

		int increment;
		int result;
		int lengthNumber = String.valueOf(number).length();

		StringBuilder stringNumber = new StringBuilder(String.valueOf(number));
		StringBuilder newNumber = new StringBuilder();
		// find the left half of string
		StringBuilder leftHalf = new StringBuilder(stringNumber.substring(0,
				lengthNumber / 2));
		// find the middle number
		char middle = stringNumber.charAt(lengthNumber / 2);

		if (lengthNumber % 2 != 0) {
			// find the next increment for odd number
			increment = (int) Math.pow(10, lengthNumber / 2);
			// mirror the number around its center
			newNumber = newNumber.append(leftHalf).append(middle)
					.append(leftHalf.reverse().toString());
		} else {
			// find the next increment for even number
			increment = (int) (1.1 * Math.pow(10, lengthNumber / 2));
			// mirror the number around its center
			newNumber = newNumber.append(leftHalf).append(
					leftHalf.reverse().toString());
		}
		// check if result is greater then entered value
		result = Integer.parseInt(newNumber.toString());
		if (result >= number) {
			return result;
		}
		// else add increment for middle part
		if (middle != '9') {
			return result + increment;
		} else { // round up for next level
			return nextPalindrome(roundUp(number));
		}
	}

	private int roundUp(int number) {
		int lengthNumber = String.valueOf(number).length();
		int increment = (int) Math.pow(10, (lengthNumber / 2) + 1);
		return ((number / increment) + 1) * increment;
	}

	public static void main(String[] args) {
		System.out.println("Enter the number:");
		Scanner scan = new Scanner(System.in);
		if (!scan.hasNext()) {
			System.out.println("You have not provided the number!");
			System.exit(0);
		}
		int number = scan.nextInt();
		NextPalindrome palindrome = new NextPalindrome();
		int result = palindrome.nextPalindrome(number);
		System.out.println(result);

	}
}
