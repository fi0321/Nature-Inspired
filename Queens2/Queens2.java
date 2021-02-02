import java.lang.Math;
import java.util.*;

/* YOU NEED TO ADD YOUR CODE TO THIS CLASS, REMOVING ALL DUMMY CODE
 *
 * DO NOT CHANGE THE NAME OR SIGNATURE OF ANY OF THE EXISTING METHODS
 * (Signature includes parameter types, return types and being static)
 *
 * You can add private methods to this class if it makes your code cleaner,
 * but this class MUST work with the UNMODIFIED Tester2.java class.
 *
 * This is the ONLY class that you can submit for your assignment.
 *
 * MH 2020
 */
public class Queens2
{
    private static int boardSize = 10;
    
    // inverts the order of a series of genes in the genotype
    public static Integer[] inversionMutate(Integer[] genotype, double p)
    {
            Random random=new Random();
        if (random.nextDouble()<p)
		{	
			int temp1=random.nextInt(10);
			int temp2=random.nextInt(10);

            if (temp2>temp1)
            {
                Integer [] genotype2 = new Integer [(temp2-temp1)+1];

                for(int i=temp1; i<=temp2; i++)
                {
                    genotype2[i-temp1] = genotype[i];
                }
                for(int i=0; i<genotype2.length/2; i++)
                {
                    int temp = genotype2[i];
                    genotype2[i] = genotype2[genotype2.length -i -1];
                    genotype2[genotype2.length -i -1] = temp;
                }
                 for(int i=temp1; i<=temp2; i++)
                {
                    genotype[i] = genotype2[i-temp1];
                }
 
            }
            if (temp1>temp2)
            {
                Integer [] genotype2 = new Integer [(temp1-temp2)+1];

                for(int i=temp2; i<=temp1; i++)
                {
                    genotype2[i-temp2] = genotype[i];
                }
                for(int i=0; i<genotype2.length/2; i++)
                {
                    int temp = genotype2[i];
                    genotype2[i] = genotype2[genotype2.length -i -1];
                    genotype2[genotype2.length -i -1] = temp;
                }
                 for(int i=temp2; i<=temp1; i++)
                {
                    genotype[i] = genotype2[i-temp2];
                }
 
            }
        }
        
        return genotype;
    }
    
    /* performs fitness-proportional parent selection
     * also known as 'roulette wheel' selection
     * selects two parents that are different to each other
     */
    public static Integer[][] rouletteSelect(Integer[][] population)
    {
      
        Random random=new Random();
		float temp1=random.nextFloat();
		float temp2=random.nextFloat();
        Integer [][] parents = new Integer [2][boardSize];

        Double [] fitness = new Double [population.length];
        Double [] fitness_norm = new Double  [population.length];
        Double [] fitness_cumu = new Double [population.length];
        double hold=0;

        Integer [] temp = new Integer [boardSize];

       
        for(int i=0;i<population.length;i++)
        {
            for (int j=0;j<boardSize; j++)
            {
                temp[j]=population[i][j];

            }
            fitness[i]=(double)Queens.measureFitness(population[i]);
        }
        double totalfit=0;
        for(int k=0;k<fitness.length;k++)
        {
            totalfit=totalfit+fitness[k];
        }
        for(int l=0;l<fitness.length;l++)
        {   
            
            
            fitness_norm[l]=fitness[l]/totalfit;
  
        }
        for (int p=0; p<population.length;p++)
        {
            hold=hold+ fitness_norm[p];
            fitness_cumu[p]=hold;
        }
        for (int m=0; m<population.length;m++)
        {
            if(temp1<fitness_cumu[m])
            {
                for(int b=0; b<boardSize;b++)
                {
                    parents[0][b]=population[m][b];
                }
                break;
            }
        }
        for (int g=0; g<population.length;g++)
        {
            if(temp2<fitness_cumu[g])
            {
                for(int a=0; a<boardSize;a++)
                {
                    parents[1][a]=population[g][a];
                }
                break;
            }
        }
        

        
        
        
        return parents;
    }
    
    /* creates a new population through λ + μ survivor selection
     * given a population of size n, and a set of children of size m
     * this method will measure the fitness of all individual in the
     * combined population, and return the n fittest individuals
     * as the new population
     */
         public static void printGenotype1(Integer[] genotype)
    {
        for (int index = 0; index < 10; index ++)
        {
            System.out.print(" " + genotype[index]);
        }
        System.out.println();
    }
    public static Integer[][] survivorSelection(Integer[][] population, Integer [][] children)
    {
        Integer [][] newPop = new Integer [population.length][boardSize];
        Integer [][] temp_pop = new Integer [population.length+children.length][boardSize];

        Integer [] fitness = new Integer [population.length+children.length];
        Integer [] temp_new = new Integer [boardSize];
        Integer [] temp_old = new Integer [boardSize];

        int temp_fit=0;
        for (int i=0; i<population.length;i++)
        {
          temp_pop[i]=population[i];
        }
        for (int j=population.length; j<population.length+children.length;j++)
        {
          temp_pop[j]=children[j-population.length];
        }
        for (int m=0; m<population.length+children.length;m++)
        {
            fitness[m]=Queens.measureFitness(temp_pop[m]);
        }
        int t=fitness.length;
        for(int b=0; b<t-1;b++)
        {
            for(int p=0; p<t-b-1;p++)
            {
                if(fitness[p]>fitness[p+1])
                {
                    int temp=fitness[p];
                    temp_old=temp_pop[p];
                    fitness[p]=fitness[p+1];
                    temp_pop[p]=temp_pop[p+1];
                    fitness[p+1]=temp;
                    temp_pop[p+1]=temp_old;
                }
            }
        }
        for (int u=0;u<temp_pop.length/2;u++)
        {
            temp_new =temp_pop[u];
            temp_pop[u]= temp_pop[temp_pop.length-u-1];
            temp_pop[temp_pop.length -u -1]=temp_new;
        }
        // END OF YOUR CODE
        for (int n=0; n<population.length; n++)
        {
            newPop[n]=temp_pop[n];
        }

        return newPop;
    }
    
    // counts the number of unique genotypes in the population
    public static int genoDiversity(Integer[][] population)
    {
        boolean checker=false;


        int uniqueTypes = 1;
        int counter =0;
        int counter1 =1;

        Integer [][] unique = new Integer [population.length][boardSize];
        for (int i=0; i< boardSize;i++)
        {
            unique[0][i]=population[0][i];

        }
        for (int j=1; j< population.length;j++)
        {
            for(int k=0; k<j;k++)
            {
                if(Arrays.equals(population[j],unique[k]))
                {
                    checker=true;
                }
                 
            }
            if (checker==true)
            {
                counter--;
            }
            if (checker==false)
            {
                unique[j+counter]=population[j];
                uniqueTypes++;
            }

        }
        // END OF YOUR CODE
        return uniqueTypes;
    }

    public static void printGenotype(Double[] genotype)
    {
        for (int index = 0; index < 10; index ++)
        {
            System.out.print(" " + genotype[index]);
        }
        System.out.println();
    }
  
        public static void printGenotype2(Double[] genotype)
    {
        for (int index = 0; index < 5; index ++)
        {
            System.out.print(" " + genotype[index]);
        }
        System.out.println();
    }
    
    public static void main(String[]args)
{


}

}
