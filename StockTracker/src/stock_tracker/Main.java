package stock_tracker;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter stock symbol: ");
		String symbol = input.nextLine();
		Stock stockToQuote = new Stock(symbol);
		stockToQuote.getPrice();
		System.out.println("");
		String priceChange = stockToQuote.whatPriceChange();
		if(priceChange == "Up"){
			System.out.println(symbol + " is up!");
		}
		else if (priceChange == "Down") {
			System.out.println(symbol + " is down.");
		}
		else {
			System.out.println(symbol + " hasn't changed.");
		}
	}
}
