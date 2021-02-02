import java.lang.Math;
import java.util.*;

/* YOU NEED TO ADD YOUR CODE TO THIS CLASS, REMOVING ALL DUMMY CODE
 *
 * DO NOT CHANGE THE NAME OR SIGNATURE OF ANY OF THE EXISTING METHODS
 * (Signature includes parameter types, return types and being static)
 *
 * You can add private methods to this class if it makes your code cleaner,
 * but this class MUST work with the UNMODIFIED Tester.java class.
 *
 * This is the ONLY class that you can submit for your assignment.
 *
 * MH 2020
 */
public class Queens
{
    private static int boardSize = 10;
    // creates a valid genotype with random values
    public static Integer [] randomGenotype()
    {	
			Integer [] genotype = new Integer [boardSize];

			genotype = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
			for (int i=0; i<2000;i++)
			{
				genotype=mutate(genotype,1);
			}
        // END OF YOUR CODE
        
        return genotype;
    }
    
    // swaps 2 genes in the genotype
    // the swap happens with probability p, so if p = 0.8
    // then 8 out of 10 times this method is called, a swap happens
    public static Integer[] mutate(Integer[] genotype, double p)
    {	
		Random random=new Random();

		if (random.nextDouble()<p)
		{	
			


			int temp1=random.nextInt(10);
			int temp2=temp1;
			while(temp2==temp1)
			{
				temp2=random.nextInt(10);
				
			}
			int gene=genotype[temp1];
			genotype[temp1]=genotype[temp2];
			genotype[temp2]=gene;
		}
        // END OF YOUR CODE
        
        return genotype;
    }
    
    // creates 2 child genotypes using the 'cut-and-crossfill' method
    public static Integer[][] crossover(Integer[] parent0, Integer[] parent1)
    {	
		Integer [] tempArr = new Integer [boardSize-5];
		for(int b=0; b<tempArr.length;b++)
		{
			tempArr[b]=0;
		}
		boolean checker=false;
        Integer [][] children = new Integer [2][boardSize];
        for (int i=0;i<5;i++)
		{
			children[0][i]=parent0[i];
		}

		 for (int j=5;j<10;j++)
		{	
			checker=false;
			int holder=parent1[j];
			for (int k=0; k<5;k++)
			{
				if (holder==children[0][k])
				{
					checker=true;
				}
			}
			for (int l=0;l<tempArr.length;l++)
			{
				if (holder==tempArr[l])
				{
					checker=true;
				}
					
			}
			if (checker==false)
			{	
				
				for(int g=0;g<tempArr.length;g++)
				{
					if(tempArr[g]==0)
					{
						tempArr[g]=holder;
						break;
					}
					
				}
				
			}
			if (j==9 && tempArr[4]==0)
			{
				j=0;
				
			}
			if(tempArr[4]!=0)
			{
				break;
			}
			
		}
		for(int p=5; p<boardSize;p++)
		{
			children[0][p]=tempArr[p-5];
			
		}
		
		Integer [] tempArr2 = new Integer [boardSize-5];
		for(int b2=0; b2<tempArr2.length;b2++)
		{
			tempArr2[b2]=0;
		}
		boolean checker2=false;
        for (int i2=0;i2<5;i2++)
		{
			children[1][i2]=parent1[i2];
		}

		 for (int j2=5;j2<10;j2++)
		{	
			checker2=false;
			int holder2=parent0[j2];
			for (int k2=0; k2<5;k2++)
			{
				if (holder2==children[1][k2])
				{
					checker2=true;
				}
			}
			for (int l2=0;l2<tempArr2.length;l2++)
			{
				if (holder2==tempArr2[l2])
				{
					checker2=true;
				}
					
			}
			if (checker2==false)
			{	
				
				for(int g2=0;g2<tempArr2.length;g2++)
				{
					if(tempArr2[g2]==0)
					{
						tempArr2[g2]=holder2;
						break;
					}
					
				}
				
			}
			if (j2==9 && tempArr2[4]==0)
			{
				j2=0;
				
			}
			if(tempArr2[4]!=0)
			{
				break;
			}
			
		}
		for(int p2=5; p2<boardSize;p2++)
		{
			children[1][p2]=tempArr2[p2-5];
			
		}
		
        // END OF YOUR CODE
        
        return children;
    }
    
    // calculates the fitness of an individual
    public static int measureFitness(Integer [] genotype)
    {
        /* The initial fitness is the maximum pairs of queens
         * that can be in check (all possible pairs in check).
         * So we are using it as the maximum fitness value.
         * We deduct 1 from this value for every pair of queens
         * found to be in check.
         * So, the lower the score, the lower the fitness.
         * For a 10x10 board the maximum fitness is 45 (no checks),
         * and the minimum fitness is 0 (all queens in a line).
         */
        int fitness = (int) (0.5 * boardSize * (boardSize - 1));
		
        
        return fitness;
    }
	

}
