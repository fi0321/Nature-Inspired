/******************************************************************************
 *  ACO for TSP
 *
 *  @author M.Hatcher March 2020
 *
 *  This code is NOT optimal.
 *
 *  To calculate the edge-choice probabilities there are several method calls
 *  in tour() that each iterate through the same arrays.
 *
 *  These methods could be brought together to make the code way more efficient,
 *  in time and space.
 *
 *  Hoever the functionality has been separated out into those worker methods in
 *  this demonstration for clarity.
 *
 *  ****THIS IS THE ONLY .java FILE THAT YOU NEED TO SUBMIT****
 *
 *  DO NOT ADD ANY OTHER METHODS TO THIS CODE
 *  YOU CAN ONLY ADD CODE IN THE AREAS SPECIFIED
 *  DO NOT CREATE OR SUBMIT ANY OTHER CLASSES
 *
 ******************************************************************************/
import java.lang.Math;
import java.util.*;

public class Ant
{
    private AntSim sim;           // simulation that this ant belongs to
    private int [] tour;          // list of nodes in the order they were visited
    private int tourLength;       // length of tour
    private int numNodes;         // size of problem
    
    // **** YOU NEED TO COMPLETE THE CODE FOR THE 6 METHODS BELOW ****
    
    /* (1)
    * returns a list of nodes not yet visited by this ant
    *
    * input parameter:
    *    boolean array visited[], where visited[i] == true if node i has been visited by this ant
    */
    public ArrayList<Integer> getNotVisited(boolean visited[])
    {
        ArrayList<Integer> notVisited = new ArrayList<Integer>();
        
        for(int i=0;i<visited.length;i++)
        {
            if (!visited[i])
            {
                notVisited.add(i);
            }
        }
        
        return notVisited;
    }
    
    /* (2)
     * returns the lengths of the edges leading to nodes not yet visited by this ant
     *
     * input parameters:
     *    currentNode: int representig the ant's current position
     *    notVisited: the list of nodes returned by the getNotVisited() method
     *
     * hints:
     *    sim.getEdgeLength(i,j) provides the edge length of edge (i,j)
     *    for each edge: i is the current node, and j is a node not yet visited
     */
    public ArrayList<Integer> getLengths(int currentNode, ArrayList<Integer> notVisited)
    {
        ArrayList<Integer> lengths = new ArrayList<Integer>();
        for(int j=0;j<notVisited.size();j++)
        {
            lengths.add(sim.getEdgeLength(currentNode,notVisited.get(j)));
        }
        // END OF YOUR CODE
        
        return lengths;
    }
    
    /* (3)
    * returns the pheromone levels of the edges leading to nodes not yet visited by this ant
    *
    * input parameters:
    *    currentNode: int representig the ant's current position
    *    notVisited: the list of nodes returned by the getNotVisited() method
    *
    * hint:
    *    sim.getPheromoneLevel(i,j) provides the pheromone level on edge (i,j)
    */
    public ArrayList<Double> getLevels(int currentNode, ArrayList<Integer> notVisited)
    {
        ArrayList<Double> levels = new ArrayList<Double>();
        
        for(int k=0;k<notVisited.size();k++)
        {
            levels.add(sim.getPheromoneLevel(currentNode,notVisited.get(k)));
        }
        
        return levels;
    }
    
    /* (4)
     * calculates and returns the probabilities of choosing each edge that leads a node not yet visited
     *
     * input parameters:
     *    levels: the list of pheromone levels returned by the getLevels() method
     *    lengths: the list of edge lengths returned by the getLevels() method
     *
     * must use the standard ACO Edge Selection formula, as provided in the class slides:
     *    p(choose edge i) = (τ^alpha . η^beta) / SUM(τ^alpha . η^beta)
     *    where for each edge i:
     *       τ = level of pheromone on an edge
     *       η = 1/(length of edge i)
     */
    public ArrayList<Double> getProbabilities(ArrayList<Double> levels, ArrayList<Integer> lengths)
    {
        ArrayList<Double> probabilities = new ArrayList<Double>();
        double sum=0;
        for(int y=0;y<levels.size();y++)
        {
            sum=sum+(((Math.pow((levels.get(y)),sim.getAlpha())*(Math.pow((1.0/(lengths.get(y))),sim.getBeta())))));
        }
    
        for(int u=0;u<levels.size();u++)
        {
            probabilities.add(Math.pow((levels.get(u)),sim.getAlpha())*(Math.pow((1.0/(lengths.get(u))),sim.getBeta()))/sum);
        }
        
        return probabilities;
    }
    
    /* (5)
    * returns cumulative probabilities of choosing edges
    *
    * input parameter:
    *    probabilities: the list of probabilities returned by the getProbabilities() method
    */
    public ArrayList<Double> getCumulativeProbabilities(ArrayList<Double> probabilities)
    {
        ArrayList<Double> cumulative = new ArrayList<Double>();
        double holder=0;
        // YOUR CODE GOES HERE - REPLACE THIS DUMMY CODE
        for(int p=0;p<probabilities.size();p++)
        {
            holder=holder+probabilities.get(p);
            cumulative.add(holder);
        }
        // END OF YOUR CODE
        
        return cumulative;
    }
    
    /* (6)
     * chooses the next node to visit using roulette wheel selection
     *
     * input parameter:
     *    cumulative: the list of cumulative proabilities returned by the getCumulativeProbabilities() method
     *
     * returns an integer in range [0,n-1] where n = cumulative.size()
     */
    public int chooseNextNode(ArrayList<Double> cumulative)
    {
        Random random=new Random();        
        float temp1=random.nextFloat();
        int act_val=0;
        for (int h=0;h<cumulative.size();h++)
        {
            if(temp1<cumulative.get(h))
            {
                act_val=h;
                break;
            }


        }
        return act_val;

        // END OF YOUR CODE
    }
    
    // **** END OF METHODS TO CODE - DO NOT TOUCH ANY CODE BELOW THIS LINE! ****
    // constructor
    public Ant(AntSim sim, int numNodes)
    {
        this.sim = sim;
        this.numNodes = numNodes;
        tour = new int [numNodes];
    }
    
    // visit every node, record the route taken and return the tour length
    public int tour()
    {
        // place this ant on a starting node, chosen at random
        int currentNode = (int) (Math.random() * numNodes);
        
        // initialise the recording of the tour
        boolean [] visited = new boolean [numNodes];
        visited [currentNode] = true;
        tour [0] = currentNode;
        tourLength = 0;
        for (int index = 0; index < numNodes; index ++)
        {
            if (index != currentNode) { visited [index] = false; }
        }
        
        int nodeCount = 1; // how many nodes have been visited so far
        
        // keep going until the tour is complete:
        while (nodeCount < numNodes)
        {
            // determine which nodes have not yet been visited
            ArrayList<Integer> notVisited = getNotVisited(visited);
            
            // for each of those nodes, get the lengths and pheromone levels of the edges that lead to them
            ArrayList<Integer> lengths = getLengths(currentNode, notVisited);
            ArrayList<Double> levels = getLevels(currentNode, notVisited);
            
            // ...hence get the probability of choosing each edge
            ArrayList<Double> probabilities = getProbabilities( levels, lengths);
            
            // ...hence get the cumulative probabilties
            ArrayList<Double> cumulative = getCumulativeProbabilities(probabilities);
            
            // ...and finally choose the next node to visit using roulette wheel selection
            int chosenIndex = chooseNextNode(cumulative);
            int chosen = notVisited.get(chosenIndex);
            
            // update the tour records
            visited[chosen] = true;
            tourLength += sim.getEdgeLength(currentNode, chosen);
            currentNode = chosen;
            tour[nodeCount] = chosen;
            nodeCount ++;
        }
        // there is one final edge to visit, which takes the ant back to its starting node
        tourLength += sim.getEdgeLength(tour[numNodes - 1], 0);
        
        return tourLength;
    }
    
    // lay pheromone inversely proportional to the length of this ant's tour
    public void layPheromone()
    {
        // how much pheromone to lay on each edge
        double perEdge = (double) (sim.getTotalPheromone() / tourLength);
        
        // re-trace this ants tour and evenly spread pheromone on each edge
        int previous = 0;
        for (int index = 1; index < numNodes + 1; index ++)
        {
            int current = tour[index % numNodes];
            sim.addPheromone(previous, current, perEdge);
            previous = current;
        }
    }
    
    // return the length of this ant's tour
    public int getTourLength()  { return tourLength; }
    
    // print the tour details to console
    public void printTour()
    {
        System.out.println("Tour length of " + tourLength +
                           ", visiting the nodes in this order:");
        for (int index = 0; index < tour.length - 1; index ++)
        {
            System.out.print(tour [index] + ", ");
        }
        System.out.println(tour [tour.length - 1] + ".");
    }
}
