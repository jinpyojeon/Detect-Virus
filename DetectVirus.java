/*
 * Program that traverses for the possibility of virus spreading
 * 
 * @author Jin Pyo Jeon
 */

import java.util.*;
import java.io.*;

/*
 * Class that contains the main method
 */
public class DetectVirus {
	
	/*
	 * Main method in which everything occurs
	 * @param args Not used
	 * @return Nothing
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// Taking in the number of nodes (N) and edges (M) 
		// N is incremented to allow for N + 1 position in array
		int N = (sc.nextInt()) + 1;
		int M = sc.nextInt();
		
		// Adjacency List is created, with array of ArrayList of Integer array 
		ArrayList<Integer[]> nodes[] = new ArrayList[N]; 
	
		for (int i = 0; i < N; i++) {
			nodes[i] = new ArrayList<Integer[]>();
		}
		
		// Inputting all the nodes, along with the weights (the time of transmission)
		int fromNode, toNode, transmitTime;
		for (int i = 0; i < M; i++) {
			fromNode = sc.nextInt();
			toNode = sc.nextInt();
			transmitTime = sc.nextInt();
			nodes[fromNode].add( new Integer[]{ toNode, transmitTime });
			nodes[toNode].add( new Integer[]{ fromNode, transmitTime });
		}
		

		ListIterator<Integer[]> iter; 
		Integer[] curIter; 
		
		for (int i = 0; i < N; i++) {
			iter = nodes[i].listIterator();
			while (iter.hasNext()) {
				curIter = iter.next();
				// System.out.printf("%d: %d, %d\n", i, curIter[0], curIter[1]);
			}
		} 

		int insertLoc = sc.nextInt();
		int insertTime = sc.nextInt();
		int endLoc = sc.nextInt();
		int endTime = sc.nextInt();
	
		// Map for reached nodes and their time
		int[] nodesReachedTime = new int[N];	
		
		// Initialize node reached time with -1	
		for (int i = 0; i < N; i++) nodesReachedTime[i] = Integer.MAX_VALUE; 

		// Queue for BFS
		Queue<Integer> nodesToVisit = new LinkedList<Integer>();
		
		// Start out BFS with first node
		nodesToVisit.add(insertLoc);
		nodesReachedTime[insertLoc] = insertTime;
		
		int remainingNodes;
		int curNode, curNodeTime; 

		// Number of nodes in current breadth of BFS
		remainingNodes = nodesToVisit.size();
		
		// Starting BFS search
		while (remainingNodes > 0) {
			for (int i = 0; i < remainingNodes; i++) {
				curNode = nodesToVisit.remove();

				iter = nodes[curNode].listIterator();
				
				int destNode, destNodeTime;
				while (iter.hasNext()) {
					curIter = iter.next();
					destNode = curIter[0];
					destNodeTime = curIter[1];
				
					// Visit node only if it has yet been visited in the earliest time
					// If node is reachable within time frame, add to path
					// Compare with beginning, previous node time, and end
					if (destNodeTime < nodesReachedTime[destNode]) {
						if (destNodeTime >= nodesReachedTime[curNode] && 
								destNodeTime >= insertTime &&
								destNodeTime <= endTime) {
								// Add node to Queue
								nodesToVisit.add(curIter[0]);
								nodesReachedTime[destNode] = destNodeTime;
							}
						}		
					}

			}
			// Start another layer of BFS if there are remaining nodes
			remainingNodes = nodesToVisit.size();
		}
		
		// Print out the result of the traversal
		// for (int i = 0; i < N; i++) System.out.printf("%d ", nodesReachedTime[i]); 
		if (nodesReachedTime[endLoc] <= endTime) System.out.println("Yes, the node is reachable");
		else System.out.println("No, the node is not reachable");
	}
}
