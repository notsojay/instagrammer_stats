package labs.lab10.tests;

import org.junit.Before;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import labs.lab10.*;


public class Lab10Test {
	
	private final double EPSILON = 1e-3;

	List<Instagrammer> igList1;
	List<Instagrammer> igList2;
	List<Instagrammer> igList3;
	List<Instagrammer> igList4;
	List<Instagrammer> igListBLANK;
	
	//This method will be executed before all the other methods.
	@Before
	public void before() {
		String path = "res/"; //<- Assign the path of the .csv files to this variable. Do not include the file name, 
								// it will be added below.
		igList1 = InstagrammersReader.readFile(path + "top_instagrammers.csv");
		igList2 = InstagrammersReader.readFile(path + "top_instagrammers2.csv");
		igList3 = InstagrammersReader.readFile(path + "top_instagrammers3.csv");
		igList4 = InstagrammersReader.readFile(path + "top_instagrammers4.csv");
		igListBLANK = InstagrammersReader.readFile(path + "top_instagrammersBLANK.csv");
	}
	
	
	@Test
	public void problem1() {
		// igList1:
		Optional<Instagrammer> ig = InstagrammersStats.problem1_getTopIGerInCategory(igList1.stream(), "Sports");
		assertTrue(ig.isPresent());
		if (ig.isPresent()) {
			assertEquals("cristiano", ig.get().getName());
		}
		
		ig = InstagrammersStats.problem1_getTopIGerInCategory(igList1.stream(), "music");
		assertTrue(ig.isPresent());
		if (ig.isPresent()) {
			assertEquals("arianagrande", ig.get().getName());
		}
		
		ig = InstagrammersStats.problem1_getTopIGerInCategory(igList1.stream(), "FashionBeauty");
		assertTrue(ig.isPresent());
		if (ig.isPresent()) {
			assertEquals("kimkardashian", ig.get().getName());
		}
		
		ig = InstagrammersStats.problem1_getTopIGerInCategory(igList1.stream(), "PHOTOGRAPHY");
		assertTrue(ig.isPresent());
		if (ig.isPresent()) {
			assertEquals("instagram", ig.get().getName());
		}
		
		ig = InstagrammersStats.problem1_getTopIGerInCategory(igList1.stream(), "ics45j");
		assertFalse(ig.isPresent());
		
		
		// igList2:
		ig = InstagrammersStats.problem1_getTopIGerInCategory(igList2.stream(), "lifestylemodelingcinema & actors/actresses");
		assertTrue(ig.isPresent());
		if (ig.isPresent()) {
			assertEquals("nidhhiagerwal", ig.get().getName());
		}
		
		ig = InstagrammersStats.problem1_getTopIGerInCategory(igList2.stream(), "Family");
		assertTrue(ig.isPresent());
		if (ig.isPresent()) {
			assertEquals("evaluna", ig.get().getName());
		}
		
		ig = InstagrammersStats.problem1_getTopIGerInCategory(igList2.stream(), "sports");
		assertTrue(ig.isPresent());
		if (ig.isPresent()) {
			assertEquals("vinijr", ig.get().getName());
		}
		
		ig = InstagrammersStats.problem1_getTopIGerInCategory(igList2.stream(), "Humor");
		assertTrue(ig.isPresent());
		if (ig.isPresent()) {
			assertEquals("luisitocomunica", ig.get().getName());
		}
	}
	
	
	@Test
	public void problem2() {
		List<String> result = InstagrammersStats.problem2_getTopNCategories(igList1.stream(), 1);
		assertEquals(1, result.size());
		assertEquals("Music", result.get(0));
		
		result = InstagrammersStats.problem2_getTopNCategories(igList1.stream(), 2);
		assertEquals(2, result.size());
		assertEquals("Music", result.get(0));
		assertEquals("Cinema & Actors/actresses", result.get(1));
		
		result = InstagrammersStats.problem2_getTopNCategories(igList1.stream(), 3);
		assertEquals(3, result.size());
		assertEquals("Music", result.get(0));
		assertEquals("Cinema & Actors/actresses", result.get(1));
		assertEquals("Sports with a ball", result.get(2));
		
		result = InstagrammersStats.problem2_getTopNCategories(igList2.stream(), 1);
		assertEquals(1, result.size());
		assertEquals("Sports with a ball", result.get(0));
		
		result = InstagrammersStats.problem2_getTopNCategories(igList2.stream(), 2);
		assertEquals(2, result.size());
		assertEquals("Sports with a ball", result.get(0));
		assertEquals("Cinema & Actors/actresses", result.get(1));
		
		result = InstagrammersStats.problem2_getTopNCategories(igList2.stream(), 3);
		assertEquals(3, result.size());
		assertEquals("Sports with a ball", result.get(0));
		assertEquals("Cinema & Actors/actresses", result.get(1));
		assertEquals("Music", result.get(2));
		
		result = InstagrammersStats.problem2_getTopNCategories(igList3.stream(), 1);
		assertEquals(1, result.size());
		assertEquals("Music", result.get(0));
		
		result = InstagrammersStats.problem2_getTopNCategories(igList3.stream(), 2);
		assertEquals(2, result.size());
		assertEquals("Music", result.get(0));
		assertEquals("Sports with a ball", result.get(1));
		
		result = InstagrammersStats.problem2_getTopNCategories(igList3.stream(), 9);
		assertEquals(9, result.size());
		assertEquals("Music", result.get(0));
		assertEquals("Sports with a ball", result.get(1));
		assertEquals("Family", result.get(2));
		assertEquals("Lifestyle", result.get(3));
		assertEquals("LifestyleModelingCinema & Actors/actresses", result.get(4));
		assertEquals("LifestyleMusic", result.get(5));
		assertEquals("Shows", result.get(6));
		assertEquals("Cinema & Actors/actresses", result.get(7));
		assertEquals("Cinema & Actors/actressesLifestyle", result.get(8));
		
		result = InstagrammersStats.problem2_getTopNCategories(igList3.stream(), 10);
		assertEquals(10, result.size());
		assertEquals("Music", result.get(0));
		assertEquals("Sports with a ball", result.get(1));
		assertEquals("Family", result.get(2));
		assertEquals("Lifestyle", result.get(3));
		assertEquals("LifestyleModelingCinema & Actors/actresses", result.get(4));
		assertEquals("LifestyleMusic", result.get(5));
		assertEquals("Shows", result.get(6));
		assertEquals("Cinema & Actors/actresses", result.get(7));
		assertEquals("Cinema & Actors/actressesLifestyle", result.get(8));
		assertEquals("Fitness & Gym", result.get(9));

		result = InstagrammersStats.problem2_getTopNCategories(igList4.stream(), 1);
		assertEquals(1, result.size());
		assertEquals("Cinema & Actors/actresses", result.get(0));
		
		result = InstagrammersStats.problem2_getTopNCategories(igList4.stream(), 2);
		assertEquals(2, result.size());
		assertEquals("Cinema & Actors/actresses", result.get(0));
		assertEquals("Cinema & Actors/actressesLifestyle", result.get(1));
		
		result = InstagrammersStats.problem2_getTopNCategories(igList4.stream(), 3);
		assertEquals(3, result.size());
		assertEquals("Cinema & Actors/actresses", result.get(0));
		assertEquals("Cinema & Actors/actressesLifestyle", result.get(1));
		assertEquals("Fitness & Gym", result.get(2));
		
		result = InstagrammersStats.problem2_getTopNCategories(igList4.stream(), 4);
		assertEquals(4, result.size());
		assertEquals("Cinema & Actors/actresses", result.get(0));
		assertEquals("Cinema & Actors/actressesLifestyle", result.get(1));
		assertEquals("Fitness & Gym", result.get(2));
		assertEquals("Lifestyle", result.get(3));
		
		result = InstagrammersStats.problem2_getTopNCategories(igList4.stream(), 5);
		assertEquals(4, result.size());
		assertEquals("Cinema & Actors/actresses", result.get(0));
		assertEquals("Cinema & Actors/actressesLifestyle", result.get(1));
		assertEquals("Fitness & Gym", result.get(2));
		assertEquals("Lifestyle", result.get(3));
		
		assertEquals(0, InstagrammersStats.problem2_getTopNCategories(igListBLANK.stream(), 4).size());
	}
	
	
	@Test
	public void problem3() {
		assertTrue(InstagrammersStats.problem3_getMostFollowedIGerInCountry(igList1.stream(), "Java").isEmpty());
		
		List<Instagrammer> result = InstagrammersStats.problem3_getMostFollowedIGerInCountry(igList1.stream(), 
				"United States");
		assertTrue(result.size() == 1 && result.get(0).getName().equals("kyliejenner"));
		
		result = InstagrammersStats.problem3_getMostFollowedIGerInCountry(igList1.stream(), 
				"South Korea");
		assertTrue(result.size() == 1 && result.get(0).getName().equals("uarmyhope"));

		result = InstagrammersStats.problem3_getMostFollowedIGerInCountry(igList3.stream(), "India");
		assertTrue(result.size() == 2 &&
				result.get(0).getName().equals("nidhhiagerwal2") &&
				result.get(1).getName().equals("nidhhiagerwal"));
		
		result = InstagrammersStats.problem3_getMostFollowedIGerInCountry(igList3.stream(), 
				"Russia");
		assertTrue(result.size() == 3 &&
				result.get(0).getName().equals("kto_takaya") &&
				result.get(1).getName().equals("kto_takaya2") &&
				result.get(2).getName().equals("subo_m"));
	}
	
	
	@Test
	public void problem4() {
		// igListBLANK:
		assertEquals("", InstagrammersStats.problem4_getAllIGersInCountry(igListBLANK.stream(), "Brazil"));
		
		// igList1:
		assertEquals("alvaromorata bowerjamie canyaman charles_leclerc chiaraferragni f1 f1 fabriziorom fedez iamzlatanibrahimovic madonna maluma maneskinofficial mrancelotti vicdeangelis violets.tv", 
				InstagrammersStats.problem4_getAllIGersInCountry(igList1.stream(), "Italy"));
		assertEquals("fcbayern julienco_ toni.kr8s toni.kr8s", 
				InstagrammersStats.problem4_getAllIGersInCountry(igList1.stream(), "Germany"));
		assertEquals("arashi_5_official kentooyamazaki tomo.y9 watanabenaomi703", 
				InstagrammersStats.problem4_getAllIGersInCountry(igList1.stream(), "Japan"));
		assertEquals("", 
				InstagrammersStats.problem4_getAllIGersInCountry(igList1.stream(), "Afghanistan"));
		
		// igList3:
		assertEquals("britneyspears dudewithsign emrata jamorant lanarhoades miketyson", 
				InstagrammersStats.problem4_getAllIGersInCountry(igList3.stream(), "United States"));
		assertEquals("sinavaliollah", 
				InstagrammersStats.problem4_getAllIGersInCountry(igList3.stream(), "Iran"));
		assertEquals("chelseafc saythename_17 saythename_172 you_r_love you_r_love", 
				InstagrammersStats.problem4_getAllIGersInCountry(igList3.stream(), "Indonesia"));
	}
	
	
	@Test
	public void problem5() {
		assertEquals(0, InstagrammersStats.problem5_countCountries(igListBLANK.stream()));
		assertEquals(33, InstagrammersStats.problem5_countCountries(igList1.stream()));
		assertEquals(24, InstagrammersStats.problem5_countCountries(igList2.stream()));
		assertEquals(10, InstagrammersStats.problem5_countCountries(igList3.stream()));
		assertEquals(4, InstagrammersStats.problem5_countCountries(igList4.stream()));
	}
	
	
	@Test
	public void problem6() {
		Map<String, Double> result = InstagrammersStats.problem6_getAvgNumFollowersPerIGerPerCountry(igList2.stream());
		assertTrue(result.size() == 24);
		assertEquals(1.478E7, result.get("Argentina"), EPSILON);
		assertEquals(2.2325E7, result.get("Colombia"), EPSILON);
		assertEquals(1.769375E7, result.get("India"), EPSILON);
		assertEquals(9900000.0, result.get("Japan"), EPSILON);
		assertEquals(7400000.0, result.get("Morocco"), EPSILON);
		assertEquals(1.17E7, result.get("Russia"), EPSILON);
		assertEquals(1.18E7, result.get("Thailand"), EPSILON);
		assertEquals(6900000.0, result.get("United Arab Emirates"), EPSILON);
		
		result = InstagrammersStats.problem6_getAvgNumFollowersPerIGerPerCountry(igList3.stream());
		assertTrue(result.size() == 10);
		assertEquals(1.3033333333333334E7, result.get("India"), EPSILON);
		assertEquals(7300000.0, result.get("Iran"), EPSILON);
		assertEquals(1.55E7, result.get("Mexico"), EPSILON);
		assertEquals(7000000.0, result.get("Russia"), EPSILON);
		assertEquals(2.39E7, result.get("United States"), EPSILON);
		
		result = InstagrammersStats.problem6_getAvgNumFollowersPerIGerPerCountry(igList4.stream());
		assertTrue(result.size() == 4);
		assertEquals(2.53E7, result.get("Brazil"), EPSILON);
		assertEquals(1.44E7, result.get("India"), EPSILON);
		assertEquals(6900000.0, result.get("Kazakhstan"), EPSILON);
		assertEquals(1.9E7, result.get("United States"), EPSILON);
		
		result = InstagrammersStats.problem6_getAvgNumFollowersPerIGerPerCountry(igListBLANK.stream());
		assertTrue(result.size() == 0);
	}
	
	
	@Test
	public void problem7() {
		// igList1:
		Optional<Instagrammer> ig = 
				InstagrammersStats.problem7_findIGerByName(igList1.stream(), "toni.kr8s");
		assertTrue(ig.isPresent());
		if (ig.isPresent()) {
			assertEquals("toni.kr8s", ig.get().getName());
			assertEquals(612, ig.get().getRank());
			assertEquals("Sports with a ball", ig.get().getCategory());
			assertEquals(34200000, ig.get().getNumFollowers());
			assertEquals("Germany", ig.get().getCountry());
			assertEquals(85800, ig.get().getEngagement());
		}
		
		ig = InstagrammersStats.problem7_findIGerByName(igList1.stream(), "Toni.kr8s");
		assertFalse(ig.isPresent());
		
		ig = InstagrammersStats.problem7_findIGerByName(igList1.stream(), "robert_thepuppy");
		assertFalse(ig.isPresent());
		
		ig = InstagrammersStats.problem7_findIGerByName(igList1.stream(), "taylor");
		assertFalse(ig.isPresent());
		
		//igList3:
		ig = InstagrammersStats.problem7_findIGerByName(igList3.stream(), "nidhhiagerwal");
		assertTrue(ig.isPresent());
		if (ig.isPresent()) {
			assertEquals("nidhhiagerwal", ig.get().getName());
			assertEquals(854, ig.get().getRank());
			assertEquals("LifestyleModelingCinema & Actors/actresses", ig.get().getCategory());
			assertEquals(24300000, ig.get().getNumFollowers());
			assertEquals("India", ig.get().getCountry());
			assertEquals(87300, ig.get().getEngagement());
		}
		
		ig = InstagrammersStats.problem7_findIGerByName(igList3.stream(), "you_r_love");
		assertTrue(ig.isPresent());
		if (ig.isPresent()) {
			assertEquals("you_r_love", ig.get().getName());
			assertEquals(850, ig.get().getRank());
			assertEquals("", ig.get().getCategory());
			assertEquals(5500000, ig.get().getNumFollowers());
			assertEquals("Indonesia", ig.get().getCountry());
			assertEquals(364200, ig.get().getEngagement());
		}
		
		ig = InstagrammersStats.problem7_findIGerByName(igList3.stream(), "");
		assertFalse(ig.isPresent());
		
		ig = InstagrammersStats.problem7_findIGerByName(igList3.stream(), "massafera");
		assertTrue(ig.isPresent());
		if (ig.isPresent()) {
			assertEquals("massafera", ig.get().getName());
			assertEquals(870, ig.get().getRank());
			assertEquals("Cinema & Actors/actressesLifestyle", ig.get().getCategory());
			assertEquals(25300000, ig.get().getNumFollowers());
			assertEquals("Brazil", ig.get().getCountry());
			assertEquals(67200, ig.get().getEngagement());
		}
		
		ig = InstagrammersStats.problem7_findIGerByName(igList3.stream(), "vinijr");
		assertTrue(ig.isPresent());
		if (ig.isPresent()) {
			assertEquals("vinijr", ig.get().getName());
			assertEquals(135, ig.get().getRank());
			assertEquals("Sports with a ball", ig.get().getCategory());
			assertEquals(21300000, ig.get().getNumFollowers());
			assertEquals("Brazil", ig.get().getCountry());
			assertEquals(806700, ig.get().getEngagement());
		}
	}
	
	
	@Test
	public void problem8() {
		assertEquals(38, InstagrammersStats.problem8_countDuplicateIGers(igList1.stream()));
		assertEquals(10, InstagrammersStats.problem8_countDuplicateIGers(igList2.stream()));
		assertEquals(2, InstagrammersStats.problem8_countDuplicateIGers(igList3.stream()));
		assertEquals(0, InstagrammersStats.problem8_countDuplicateIGers(igList4.stream()));
		assertEquals(0, InstagrammersStats.problem8_countDuplicateIGers(igListBLANK.stream()));
	}
	
	
	@Test
	public void problem9() {
		List<String> result = InstagrammersStats.problem9_getAllIGersInNumFollowersRange(igListBLANK.stream(), 6900000, 25300000);
		assertEquals(0, result.size());
		
		result = InstagrammersStats.problem9_getAllIGersInNumFollowersRange(igList4.stream(), 6900000, 25300000);
		assertEquals(4, result.size());
		assertEquals("kikakim", result.get(0));
		assertEquals("massafera", result.get(1));
		assertEquals("miketyson", result.get(2));
		assertEquals("sonu_sood", result.get(3));
		
		result = InstagrammersStats.problem9_getAllIGersInNumFollowersRange(igList4.stream(), 0, 250000000);
		assertEquals(4, result.size());
		assertEquals("kikakim", result.get(0));
		assertEquals("massafera", result.get(1));
		assertEquals("miketyson", result.get(2));
		assertEquals("sonu_sood", result.get(3));
		
		result = InstagrammersStats.problem9_getAllIGersInNumFollowersRange(igList4.stream(), 13000000, 20000000);
		assertEquals(2, result.size());
		assertEquals("miketyson", result.get(0));
		assertEquals("sonu_sood", result.get(1));
		
		result = InstagrammersStats.problem9_getAllIGersInNumFollowersRange(igList4.stream(), 25300001, 999999999);
		assertEquals(0, result.size());
		
		result = InstagrammersStats.problem9_getAllIGersInNumFollowersRange(igList3.stream(), 10000000, 20000000);
		assertEquals(5, result.size());
		assertEquals("belindapop", result.get(0));
		assertEquals("lanarhoades", result.get(1));
		assertEquals("luara", result.get(2));
		assertEquals("miketyson", result.get(3));
		assertEquals("sonu_sood", result.get(4));
		
		result = InstagrammersStats.problem9_getAllIGersInNumFollowersRange(igList3.stream(), 0, 5000000);
		assertEquals(2, result.size());
		assertEquals("josephquinn", result.get(0));
		assertEquals("priyankaamohanofficial", result.get(1));
	}
	
	
	@Test
	public void problem10() {
		Optional<Instagrammer> result = InstagrammersStats.problem10_getIGerWithLongestName(
				igListBLANK.stream());
		assertFalse(result.isPresent());
		
		result = InstagrammersStats.problem10_getIGerWithLongestName(igList4.stream());
		assertTrue(result.isPresent());
		if (result.isPresent()) {
			assertEquals("massafera", result.get().getName());
		}
		
		result = InstagrammersStats.problem10_getIGerWithLongestName(igList3.stream());
		assertTrue(result.isPresent());
		if (result.isPresent()) {
			assertEquals("priyankaamohanofficial", result.get().getName());
		}
		
		result = InstagrammersStats.problem10_getIGerWithLongestName(igList2.stream());
		assertTrue(result.isPresent());
		if (result.isPresent()) {
			assertEquals("saharghoreyshiofficialpage", result.get().getName());
		}
		
		result = InstagrammersStats.problem10_getIGerWithLongestName(igList1.stream());
		assertTrue(result.isPresent());
		if (result.isPresent()) {
			assertEquals("taehyung.bighitentertainment", result.get().getName());
		}
	}
}

