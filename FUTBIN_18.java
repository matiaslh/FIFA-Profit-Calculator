import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;

public class FUTBIN_18 {

	public static void main(String[] args) throws Exception {

		// get list of player ids
		String filename = "output.txt";
		ArrayList<Player> players = readFile(filename);

		String apiUrl = "https://www.futbin.com/18/playerPrices?player=";

		// from user
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the console you have (ps, xbox, pc).");
		String console = scan.next();
		System.out.println("Enter the max coins you have to spend.");
		double maxCoins = scan.nextDouble();
		System.out.println("Enter the min profit you want to make.");
		double minProfit = scan.nextDouble();
		scan.close();
		
		for (Player currentPlayer : players) {

			String html = getHTML(apiUrl + currentPlayer.resourceId);
			if(html==null){
				continue;
			}
			
			JSONObject apiResult = new JSONObject(html);
			JSONObject idResultJSON = apiResult.getJSONObject(currentPlayer.resourceId + "");
			JSONObject prices = idResultJSON.getJSONObject("prices");
			JSONObject consolePrices = prices.getJSONObject(console);

			Object updated = consolePrices.get("updated");
			currentPlayer.timeUpdated = updated.toString();

			String buffer = consolePrices.get("LCPrice").toString().replaceAll(",", "");
			currentPlayer.lowestPrice = Integer.parseInt(buffer);
			buffer = consolePrices.get("LCPrice2").toString().replaceAll(",", "");
			currentPlayer.secondLowestPrice = Integer.parseInt(buffer);

			if (maxCoins > currentPlayer.price() && minProfit <= currentPlayer.profit()
					&& currentPlayer.isProfitable()) {
				System.out.println("\n" + currentPlayer.toString());
				Toolkit.getDefaultToolkit().beep();

			} else {
				System.out.print(".");
			}
		}
	}

	public static String getHTML(String urlToRead) throws Exception {
		StringBuilder sb = null;
		try {
			URLConnection connection = new URL(urlToRead).openConnection();
			connection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			connection.connect();
			BufferedReader r = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
			sb = new StringBuilder();
			String line;
			while ((line = r.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sb.toString();
	}

	public static ArrayList<Player> readFile(String filename) {
		ArrayList<Player> list = new ArrayList<Player>();
		try {
			File f = new File("playerDatabase.txt");
			System.out.println(f.getAbsolutePath());
			InputStream stream = new FileInputStream(f);
			Scanner scan = new Scanner(stream);
			String line;
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				String[] attributes = line.split(",");
				Player p = new Player(attributes);
				list.add(p);
			}
			scan.close();
			return list;
		} catch (Exception e) {
			System.out.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
			return list;
		}
	}
}
