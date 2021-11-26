/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import main.HashMap;
import main.GraphNode;

import org.junit.jupiter.api.Test;

class HashMapTest {
	HashMap map = new HashMap();
	GraphNode g1 = new GraphNode("5451397c-0d6e-4d7d-985f-bd627dcd2fcc", false);
	GraphNode g2 = new GraphNode("6451485c-1d6e-3d7d-980f-bd717dcd2feg", false);
	GraphNode g3 = new GraphNode("1352385c-1d6e-3d7d-980f-bd717dcd2feg", false);
	GraphNode g4 = new GraphNode("7242375c-1d6e-3d7d-980f-bd717dcd2feg", false);
	GraphNode g5 = new GraphNode("2446385c-1d6e-3d7d-980f-bd717dcd2feg", false);

	@Test
	void test() {
		map.set(g1, 1);
		map.set(g2, 3);
		assertEquals(2, map.currCapacity);
		assertEquals(10, map.length);
		assertEquals(1, map.getValue(g1));
		assertEquals(-1, map.getValue(g3));
		assertTrue(map.hasKey(g1));	
		
		map.deleteEntry(g1);
		assertEquals(-1, map.getValue(g1));
		
		map.set(g1, 1);
		map.set(g2, 4);
		map.set(g4, 5);
		map.set(g5, 6);
		
		assertEquals(20, map.length);
	}

}
