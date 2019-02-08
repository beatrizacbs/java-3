package challenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class MainTest {

	@Test
	public void q1() throws Exception {
		assertEquals(164, new Main().q1());
	}

	@Test
	public void q2() throws Exception {
		assertEquals(647, new Main().q2());
	}

	@Test
	public void q3() throws Exception {
		List<String> expected = Arrays.asList(
				"C. Ronaldo dos Santos Aveiro",
				"Lionel Messi",
				"Neymar da Silva Santos Jr.",
				"Luis SuÃ¡rez",
				"Manuel Neuer",
				"Robert Lewandowski",
				"David De Gea Quintana",
				"Eden Hazard",
				"Toni Kroos",
				"Gonzalo HiguaÃ­n",
				"Sergio Ramos GarcÃ­a",
				"Kevin De Bruyne",
				"Thibaut Courtois",
				"Alexis SÃ¡nchez",
				"Luka ModriÄ‡",
				"Gareth Bale",
				"Sergio AgÃ¼ero",
				"Giorgio Chiellini",
				"Gianluigi Buffon",
				"Paulo Dybala");

		assertEquals(expected, new Main().q3());
	}

	@Test
	public void q4() throws Exception {
		List<String> expected = Arrays.asList(
				"Neymar da Silva Santos Jr.",
				"Lionel Messi",
				"Luis SuÃ¡rez",
				"C. Ronaldo dos Santos Aveiro",
				"Eden Hazard",
				"Toni Kroos",
				"Kevin De Bruyne",
				"Antoine Griezmann",
				"Robert Lewandowski",
				"Gareth Bale");

		assertEquals(expected, new Main().q4());
	}

	@Test
	public void q5() throws Exception {
		List<String> expected = Arrays.asList(
				"Barry Richardson",
				"Essam El Hadary",
				"Ã“scar PÃ©rez",
				"Jimmy Walker",
				"Danny Coyne",
				"Chris Day",
				"Joaquim Manuel Sampaio Silva",
				"Kjetil WÃ¦hler",
				"Timmy Simons",
				"Benjamin Nivet");

		assertEquals(expected, new Main().q5());
	}

	@Test
	public void q6() throws Exception {
		Map<Integer, Integer> expected = new HashMap<>();
		expected.put(16, 18);
		expected.put(17, 270);
		expected.put(18, 682);
		expected.put(19, 1088);
		expected.put(20, 1252);
		expected.put(21, 1275);
		expected.put(22, 1324);
		expected.put(23, 1395);
		expected.put(24, 1321);
		expected.put(25, 1515);
		expected.put(26, 1199);
		expected.put(27, 1153);
		expected.put(28, 1053);
		expected.put(29, 1127);
		expected.put(30, 807);
		expected.put(31, 666);
		expected.put(32, 506);
		expected.put(33, 610);
		expected.put(34, 271);
		expected.put(35, 188);
		expected.put(36, 137);
		expected.put(37, 69);
		expected.put(39, 18);
		expected.put(38, 38);
		expected.put(40, 4);
		expected.put(41, 3);
		expected.put(43, 2);
		expected.put(44, 2);
		expected.put(47, 1);

		assertEquals(expected, new Main().q6());
	}}