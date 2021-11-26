package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import main.MinHeap;
import main.HashMap;
import main.GraphNode;


class MinHeapTest {
	
	MinHeap heap = new MinHeap();
	GraphNode g1 = new GraphNode("5451397c-0d6e-4d7d-985f-bd627dcd2fcc", false);
	GraphNode g2 = new GraphNode("6451485c-1d6e-3d7d-980f-bd717dcd2feg", false);
	GraphNode g3 = new GraphNode("1352385c-1d6e-3d7d-980f-bd717dcd2feg", false);

	@Test
	void test() {
		
		g1.priority = 1;
		g2.priority = 2;
		g3.priority = 3;
		
		heap.insert(g1);
		heap.insert(g2);
		heap.insert(g3);
		
		assertTrue(heap.isInHeap(g1));
		assertTrue(heap.isInHeap(g2));
		assertTrue(heap.isInHeap(g3));
		
		
		assertEquals(3, heap.currCapacity);
		
		assertEquals(g1, heap.findMin());
		
		assertEquals(g1, heap.extractMin());
		assertEquals(2, heap.currCapacity);
		
		assertEquals(g2, heap.findMin());
		assertEquals(g2, heap.extractMin());
		assertEquals(1, heap.currCapacity);
		
		assertEquals(g3, heap.findMin());
		assertEquals(g3, heap.extractMin());
		
		assertTrue(heap.isEmpty());
		
	
		
	}

}
