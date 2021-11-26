package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.GraphNode;
import main.MinQueue;

class MinQueueTest {
	MinQueue pq = new MinQueue();
	
	GraphNode g1 = new GraphNode("5451397c-0d6e-4d7d-985f-bd627dcd2fcc", false);
	GraphNode g2 = new GraphNode("6451485c-1d6e-3d7d-980f-bd717dcd2feg", false);
	GraphNode g3 = new GraphNode("1352385c-1d6e-3d7d-980f-bd717dcd2feg", false);

	@Test
	void test() {
		
		g1.priority = 1;
		g2.priority = 2;
		g3.priority = 3;
		
		pq.insert(g1);
		assertEquals(1, pq.size);
		
		pq.insert(g2);
		pq.insert(g3);
		
		assertTrue(pq.isInQueue(g1));
		assertTrue(pq.isInQueue(g2));
		assertTrue(pq.isInQueue(g3));
		
		assertEquals(g1, pq.pullHighestPriorityElement());
		assertEquals(g2, pq.pullHighestPriorityElement());
		assertEquals(g3, pq.pullHighestPriorityElement());
		
		assertTrue(pq.isEmpty());
		
		
	}

}
