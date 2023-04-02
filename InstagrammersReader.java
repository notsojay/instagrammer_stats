package labs.lab10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * A utility class for reading a CSV-formatted file of top Instagrammers
 *
 */
public class InstagrammersReader {
	
	/**
	 * Reads a CSV-formatted file of top Instagrammers data, and returns a list of 
	 * Instagrammers
	 * 
	 * @param filename	the CSV file with the Instagrammers data to read
	 * @return	a list of Instagrammers from the file
	 */
	public static List<Instagrammer> readFile(String filename) {
		List<Instagrammer> igers = new ArrayList<>();
		try (Scanner in = new Scanner(new File(filename))) {
			while (in.hasNextLine()) {
				String line = in.nextLine();
				/*
				 * The format of each line is name,rank,category,numFollowers,country,engagement,engagementAvg
				 */

				Scanner scanner = new Scanner(line);
				scanner.useDelimiter(",");
				
				String name = scanner.next();
				System.out.println("name: " + name);
				int rank = scanner.nextInt();
				System.out.println("rank: " + rank);
				String category = scanner.next();
				System.out.println("category: " + category);
				long numFollowers = parseNumber(scanner.next());
				System.out.println("numFollowers: " + numFollowers);
				String country = scanner.next();
				System.out.println("country: " + country);
				long engagement = parseNumber(scanner.next());
				System.out.println("engagement: " + engagement);
				
				scanner.close();
				
				igers.add(new Instagrammer(name, rank, category, numFollowers, country, engagement));
			}
		} catch (FileNotFoundException e) {
			System.out.println("File: " + filename + " not found");
		} 
		return igers;
	}
	
	
	private static long parseNumber(String str) {
		int multiplier = 1;
		if (str.endsWith("K")) {
			multiplier = 1000;
			str = str.substring(0, str.length()-1);
		}
		else if (str.endsWith("M")) {
			multiplier = 1000000;
			str = str.substring(0, str.length()-1);
		}
		return Math.round(Double.parseDouble(str) * multiplier);
	}
	
	
	public static void main(String[] args) {
		List<Instagrammer> igers = InstagrammersReader.readFile("res/top_instagrammers.csv");
		System.out.println(igers);
	}
}
