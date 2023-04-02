package labs.lab10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A class for performing various analyses on a set of Instagrammers data
 *
 */
public class InstagrammersStats {
	
	/**
	 * Given a Stream of Instagrammers and a category, return the top Instagrammer
	 * (by rank) in the given category. If there are no Instagrammers in that category,
	 * return an empty Optional<Instagrammer>.
	 * 
	 * Note: Some Instagrammers have multiple categories. For this method, as long as 
	 * the given category string appears in their category entry, it is considered a 
	 * match. For example, an Instagrammer with the category "MusicFashion" would match
	 * both "music" and "fashion" (matching is case-insensitive).
	 * 
	 * @param	instagrammers	stream of Instagrammers
	 * @param	category		category to search for (case-insensitive)
	 * 
	 * @return	top Instagrammer in category, or empty Optional, if none in category
	 */
	public static Optional<Instagrammer> problem1_getTopIGerInCategory(Stream<Instagrammer> instagrammers,
			String category) {
		 return instagrammers.filter(i -> i.getCategory().toLowerCase().contains(category.toLowerCase()))
		            .sorted(Comparator.comparingInt(Instagrammer::getRank))
		            .findFirst();
	}
	
	
	/**
	 * Given a Stream of Instagrammers and an integer n, return a list of the top n categories, 
	 * sorted in descending order by number of Instagrammers. If two categories have the same number
	 * of Instagrammers, order them lexicographically by category name. Ignore blank category
	 * names.
	 * 
	 * If there are < n countries represented in the stream, return them all, sorted in the order
	 * previously stated.
	 * 
	 * Note: For this method, consider an Instagrammer's category to be its entire category string.
	 * For example, "LifestyleMusic" is one category. "Lifestyle" is different category. "Music" is
	 * yet another different category, etc.
	 * 
	 * @param instagrammers		stream of Instagrammers
	 * @param n					number of results desired
	 * 
	 * @return	a list of the top n categories
	 */
	public static List<String> problem2_getTopNCategories(Stream<Instagrammer> instagrammers, int n) {
		return instagrammers.map(Instagrammer::getCategory)
			    .flatMap(category -> Stream.of(category.split(",")))
			    .filter(category -> !category.isBlank())
			    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
			    .entrySet()
			    .stream()
			    .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
			    .limit(n)
			    .map(Map.Entry::getKey)
			    .collect(Collectors.toList());
	}
	
	
	/**
	 * Given a Stream of Instagrammers and a country, return a List containing the 
	 * Instagrammer(s) in that country with the most followers. The list should only 
	 * contain multiple Instagrammers if there are multiple Instagrammers who have the 
	 * most followers. The order of the list should follow the order in which those 
	 * Instagrammers appear in the stream.
	 * 
	 * @param	instagrammers	stream of Instagrammers
	 * @param	country			country to search for
	 * 
	 * @return	List containing top Instagrammer(s) in country
	 */
	public static List<Instagrammer> problem3_getMostFollowedIGerInCountry(Stream<Instagrammer> instagrammers,
			String country) {
		 List<Instagrammer> filteredList = instagrammers.filter(i -> i.getCountry().equalsIgnoreCase(country))
			        .collect(Collectors.toList());
		 OptionalLong maxFollowers = filteredList.stream()
			        .mapToLong(Instagrammer::getNumFollowers)
			        .max();
		 List<Instagrammer> mostFollowedInstagrammers = filteredList.stream()
			        .filter(i -> i.getNumFollowers() == maxFollowers.getAsLong())
			        .collect(Collectors.toList());
		 return mostFollowedInstagrammers;
	}
	
	
	/**
	 * Given a Stream of Instagrammers and a country name, return a String that 
	 * contains the names of all Instagrammers in that country,
	 * ordered lexicographically, each name separated by a single space
	 * 
	 * @param	instagrammers	stream of Instagrammers
	 * @param	country			country to search for
	 */
	public static String problem4_getAllIGersInCountry(Stream<Instagrammer> instagrammers, 
			String country) {
		 return instagrammers.filter(i -> i.getCountry().equals(country))
		            .map(Instagrammer::getName)
		            .sorted()
		            .collect(Collectors.joining(" "));
	}
	

	/**
	 * Given a Stream of Instagrammers, return the number of different countries
	 * represented by the Instagrammers. 
	 * 
	 * Do not include blank country names in the count.
	 * 
	 * @param	instagrammers	stream of Instagrammers
	 * 
	 * @return	the number of different countries
	 */
	public static long problem5_countCountries(Stream<Instagrammer> instagrammers) {
		return instagrammers.filter(i -> !i.getCountry().isEmpty())
	            .map(Instagrammer::getCountry)
	            .distinct()
	            .count();
	}
	
	
	/**
	 * Given a Stream of Instragrammers, return a Map<String, Double> that maps each country to
	 * the average number of followers per Instagrammer in that country
	 * 
	 * This map should be sorted lexicographically by country name.
	 * 
	 * If the stream is empty, return an empty map.
	 * 
	 * @param	instagrammers	stream of Instagrammers
	 * 
	 * @return	a mapping of country to average number of followers per Instagrammer in that country
	 */
	public static Map<String, Double> problem6_getAvgNumFollowersPerIGerPerCountry(Stream<Instagrammer> instagrammers) {
		return instagrammers.filter(i -> !i.getCountry().isEmpty())
				.collect(Collectors.groupingBy(
                Instagrammer::getCountry,
                TreeMap::new,
                Collectors.averagingLong(Instagrammer::getNumFollowers)
        ));
	}
	
	
	/**
	 * Given a Stream of Instagrammers and a name, return the first Instagrammer
	 * found with that name. If there are no Instagrammers with that name,
	 * return an empty Optional<Instagrammer>.
	 * 
	 * @param	instagrammers	stream of Instagrammers
	 * @param	name			name to search for (case-sensitive)
	 * 
	 * @return	first Instagrammer found with the given name, or empty Optional, if none found
	 */
	public static Optional<Instagrammer> problem7_findIGerByName(Stream<Instagrammer> instagrammers,
			String name) {
		return instagrammers.filter(i -> i.getName().equals(name)).findFirst();
	}
	
	
	/**
	 * Given a Stream of Instagrammers, return the number of Instagrammers whose name is
	 * a duplicate of another Instagrammer
	 * 
	 * @param	instagrammers	stream of Instagrammers
	 * 
	 * @return	the number of duplicate Instagrammers
	 */
	public static long problem8_countDuplicateIGers(Stream<Instagrammer> instagrammers) {
		 Map<String, Long> nameCountMap = instagrammers.map(Instagrammer::getName)
			        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		 return nameCountMap.values().stream()
			        .filter(count -> count > 1)
			        .count();
	}
	
	
	/**
	 * Given a Stream of Instagrammers and two integers (min and max), return a list
	 * of the names of all Instagrammers with num followers within that range,
	 * ordered lexicographically 
	 * 
	 * @param	instagrammers	stream of Instagrammers
	 * @param	min				min num followers
	 * @param	max				max num followers
	 */
	public static List<String> problem9_getAllIGersInNumFollowersRange(
			Stream<Instagrammer> instagrammers, int min, int max) {
	    return instagrammers.filter(i -> i.getNumFollowers() >= min && i.getNumFollowers() <= max)
	        .sorted(Comparator.comparing(Instagrammer::getName))
	        .map(Instagrammer::getName)
	        .collect(Collectors.toList());
	}
	
	
	/**
	 * Given a Stream of Instagrammers, return the Instagrammer with the longest
	 * name. If there is more than one Instagrammer with the longest name, 
	 * return the first one found.
	 * 
	 * Ignore blank names.
	 * 
	 * If the stream is empty, return an empty Optional<Instagrammer>.
	 * 
	 * @param	instagrammers	stream of Instagrammers
	 * 
	 * @return	Instagrammer with the longest name
	 */
	public static Optional<Instagrammer> problem10_getIGerWithLongestName(Stream<Instagrammer> instagrammers) {
		return instagrammers.filter(i -> !i.getName().isEmpty())
		        .max(Comparator.comparingInt(i -> i.getName().length()));
	}
}