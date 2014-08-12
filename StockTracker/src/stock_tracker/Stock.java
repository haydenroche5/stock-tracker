package stock_tracker;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class Stock {
	
	private double price;
	private String symbol;
	private String url;
	private String priceChange;
	private Document stockPage;
	
	public Stock(String inputSymbol) {
		this.symbol = inputSymbol.toLowerCase();
		this.url = "http://finance.yahoo.com/q?s=" + symbol;
		// change is false when price is down (default)
		this.priceChange = "no change";
	}

	public double getPrice() {
		try {
			stockPage = Jsoup.connect(url).get();
			Elements priceQuotes = stockPage.select("span[class=time_rtq_ticker");
			Element priceQuote = priceQuotes.get(0);
			System.out.println(priceQuote.text());
			setPrice(Double.parseDouble(priceQuote.text()));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("That symbol doesn't correspond to a real stock!");
		}
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	// if change is true, the stock has increased in price
	public String whatPriceChange() {
		// a list of Element objects (spans) with this ID (there will only be one)
		Elements changes = stockPage.select("span[id=yfs_c63_" + symbol + "]");
		// the parent of the element that shows us if the stock is up or down
		Element changeParent = changes.first();
		// the child that has the up/down information
		Element change = changeParent.child(0);
		if(change.hasAttr("alt=Up")) {
			priceChange = "Up";
		}
		else if(change.hasAttr("alt=Down")) {
			priceChange = "Down";
		}
		return priceChange;
	}
}
