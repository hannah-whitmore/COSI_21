/**
 * Hannah Whitmore
 * hwhitmore@brandeis.edu
 * December 8, 2020
 * PA #3
 * Runs dijsktras algorithm 
 * Known bugs: unsure
 */

package main;

import java.io.*;

public class FindMinPath {
	
	public static MinQueue pq  = new MinQueue();

	/**
	 * main method that runs dijsktras algorithm 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String [] args) throws IOException {		
		GraphWrapper gw = new GraphWrapper(false);
		GraphNode home = gw.getHome();
		home.priority = 0;
		pq.insert(home);
		String direction = "";
		GraphNode goal = null;
		GraphNode curr = home;
		while (!(pq.isEmpty()) && !(curr.isGoalNode())) {
			curr = pq.pullHighestPriorityElement();
			
			if (curr.isGoalNode()) {
				goal = curr;	
			}
			// for each neighbor
			if (curr.hasNorth()) {
				checkNeighbors(curr, curr.getNorth(), curr.getNorthWeight(), "North");
			} 
			if (curr.hasEast()) {
				checkNeighbors(curr, curr.getEast(), curr.getEastWeight(), "East");
			} 
			if (curr.hasWest()) {
				checkNeighbors(curr, curr.getWest(), curr.getWestWeight(), "West");
			}
			if (curr.hasSouth()) {
				checkNeighbors(curr, curr.getSouth(), curr.getSouthWeight(), "South");
			}
		}
		
		FileWriter file = new FileWriter("answer.txt");
		saveAnswerToFile(file, goal, direction);
		
	}
	
	/**
	 * writes final shortest path to a file
	 * @param file
	 * @param goal
	 * @param ans
	 * @throws IOException
	 */
	public static void saveAnswerToFile(FileWriter file, GraphNode goal, String ans) throws IOException{
		if (goal==null) {
			ans = "There is no path.";
		} else {
			while (goal.previousNode != null) {
				ans = goal.previousDirection + "\n" + ans;
				goal = goal.previousNode;
			}
		}
		file.write(ans);
		file.close();	
	}
	
	/**
	 * repeats this process for every neighbor of curr we can access, and continues until the queue is empty
	 * @param curr
	 * @param neighbor
	 * @param weight
	 * @param direction
	 */
	public static void checkNeighbors(GraphNode curr, GraphNode neighbor, int weight, String direction) {
		int x = curr.priority + weight;
		if (!(pq.isInQueue(neighbor))) {
			neighbor.priority = x;
			neighbor.previousNode=curr;
			neighbor.previousDirection = direction;
			pq.insert(neighbor);
		} else {
			if (pq.isInQueue(neighbor) && x < neighbor.priority) {
				neighbor.priority = x;
				pq.rebalance(neighbor);
				neighbor.previousNode = curr;
				neighbor.previousDirection = direction;
			}
		}
		
	}
	
}
