package FUTBIN;

import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Finished_WellParsedHTML {

	public static void main(String[] args) throws IOException {
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner (System.in);
		
		//FIX PLAYER IDS FOR FIFA 16: 4537 is pele
		
		System.out.print("URLs for All IFs: 21135 to 21335 (totw 10, 18 more players added every week)"
		
				+ "\n\nURLs for All Players: 3071 to 14388"
				+ "\nURLs for All Goalkeepers: 19760 to 21126"
				
				+ "\n\nURLs for Legends: 19709 to 19759 and 21130 to 21134"
				 
				+ "\n\nURLs for Gold Players: 3071 to 4074"
				
				+ "\n\nURLs for Silver Players: 4075 to 8976"
				
				+ "\n\nURLs for Bronze Players: 8977 to 14388");
		 
		System.out.print("\n\nHow many sets of URLs do you want to input?: ");
		int setsOfIDs = scan.nextInt();
		int playerID;
		
		int[] from = new int[setsOfIDs];
		int[] to = new int[setsOfIDs];
		
		System.out.print("\nWhich URLs do you want to use?: ");
		
		for (int i=0;i<setsOfIDs;i++){
		
			System.out.print("\nFrom: ");
			from[i]=scan.nextInt();
			System.out.print("To: ");
			to[i]=scan.nextInt();
        
		}
		
		System.out.print("\nWhich console are you on? (Xbox-0, Playstation-1): ");
		double console = scan.nextInt();
		
		System.out.print("\nHow many coins do you have to spend?: ");
  		double maxCoins = scan.nextDouble();
  		
		System.out.print("\nWhat is the minimum coins you want to make?: ");
		double minProfit = scan.nextInt();
		
		System.out.println();
		System.out.println();
		
		if (console==0){
			
			for (int i=0;i<setsOfIDs;i++){
				
				playerID = from[i];
				
				while (playerID <= to[i]){

					Document doc = Jsoup.connect("http://www.futbin.com/16/player/"+playerID+"/").get();
					
					//Xbox BINS
					Element xboxlink1 = doc.getElementsByAttributeValue("id","xboxlowest").first();
					int xboxlowest = Integer.parseInt(xboxlink1.text());
					
					Element xboxlink2 = doc.getElementsByAttributeValue("id","xboxlowest2").first();
					int xboxlowest2 = Integer.parseInt(xboxlink2.text());
					
					Element xboxlink3 = doc.getElementsByAttributeValue("id","xboxlowest3").first();
					int xboxlowest3 = Integer.parseInt(xboxlink3.text());
					
					//Xbox time
					Element xboxlink4 = doc.getElementsByAttributeValue("style", "text-align:center;color: white;").last();
					String xboxtime = xboxlink4.text();
					xboxtime = xboxtime.substring(xboxtime.indexOf("- ")+2);
	
					if (xboxlowest2*0.95-xboxlowest >= minProfit && xboxlowest <= maxCoins){
						
						//Player Name
						Elements nameLink = doc.getElementsByAttributeValue("class", "pcdisplay-name");
						String playerName = nameLink.text();
						
						//Player Rating
						Elements ratingLink = doc.getElementsByAttributeValue("class", "pcdisplay-rat");
						String playerRating = ratingLink.text();
						
						System.out.println(playerName+", "+playerRating);
						System.out.println(xboxtime);
						System.out.println("Profit: "+(xboxlowest2*0.95-xboxlowest));
						System.out.println("Lowest: "+xboxlowest);
						System.out.println("Second Lowest: "+xboxlowest2);
						System.out.println("Third Lowest: "+xboxlowest3);
						System.out.println();
						System.out.println();
						
					}
					playerID++;
				}
			}

		}
		
		if (console==1){
		
			for (int i=0;i<setsOfIDs;i++){
							
				playerID = from[i];
							
				while (playerID <= to[i]){
					
					Document doc = Jsoup.connect("http://www.futbin.com/16/player/"+playerID+"/").get();
				
					//PS BINS
					Element pslink1 = doc.getElementsByAttributeValue("id","pslowest").first();
					int pslowest = Integer.parseInt(pslink1.text());
					
					Element pslink2 = doc.getElementsByAttributeValue("id","pslowest2").first();
					int pslowest2 = Integer.parseInt(pslink2.text());
					
					Element pslink3 = doc.getElementsByAttributeValue("id","pslowest3").first();
					int pslowest3 = Integer.parseInt(pslink3.text());
					
					//PS time
					Element pslink4 = doc.getElementsByAttributeValue("style", "text-align:center;color: white;").first();
					String pstime = pslink4.text();
					pstime = pstime.substring(pstime.indexOf("- ")+2);
		
		
					if (pslowest2*0.95-pslowest >= minProfit && pslowest <= maxCoins){
						
						//Player Name
						Elements playerLink = doc.getElementsByAttributeValue("class", "pcdisplay-name");
						String playerName = playerLink.text();
						
						//Player Rating
						Elements ratingLink = doc.getElementsByAttributeValue("class", "pcdisplay-rat");
						String playerRating = ratingLink.text();
						
						System.out.println(playerName+", "+playerRating);
						System.out.println(pstime);
						System.out.println("Profit: "+(pslowest2*0.95-pslowest));
						System.out.println("Lowest: "+pslowest);
						System.out.println("Second Lowest: "+pslowest2);
						System.out.println("Third Lowest: "+pslowest3);
						System.out.println();
						System.out.println();
						
					}
					playerID++;
				}
			}
		}
	}
}
