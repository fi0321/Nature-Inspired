public class CA{ 
	   public static void main(String[] args) { 
	      int n = Integer.parseInt(args[0]);
		  int j = Integer.parseInt(args[1]);
		  String concatenate;
		  if(j>255 || j<0)
		  {
			  System.out.println("Please enter a value below 256 and it should not be negative");
			  return;
		  }
		  boolean[] x=conboolean(j);

	      int numCells = 2 * n;
		String str = "111@110@101@100@011@010@001@000"; 
        String[] arrOfStr = str.split("@", 8); 

  


	      boolean[] cells = new boolean[numCells];      // cellular automaton at time t
	      boolean[] old   = new boolean[numCells]; 
		  String [] patt=new String[3];
		  // cellular automaton at time t-1
	      cells[numCells/2] = true;
	  
	      for (int t = 1; t < n; t++) {

	         // draw current row
	         for (int i = 0; i < numCells; i++) {
	             if(cells[i]) System.out.print("*");
	            else         System.out.print(" ");
	         }
	         System.out.println("");
			
	         // save current rowjava
	         for (int i = 0; i < numCells; i++)
			 { 
	            old[i] = cells[i];
			 }
			 for (int i = 0; i < numCells; i++)
			 { 
	             if (i==0)
				 {
					 patt[0]="0";
					 if (old[i])
					 {
						 patt[1]="1";
					 }
					 else
					 {
						 patt[1]="0";
					 }
					 if (old[i+1])
					 {
						 patt[2]="1";
					 }
					 else
					 {
						 patt[2]="0";
					 }
			     }
				 else if (i==numCells-1)
				 {
					 if (old[i-1])//boolean to see if the 
					 {
						 patt[0]="1";
					 }
					 else
					 {
						 patt[0]="0";
					 }
					 if (old[i])
					 {
						 patt[1]="1";
					 }
					 else
					 {
						 patt[1]="0";
					 }
					 
					 patt[2]="0";
				 }
				 else
				 {
					 if (old[i-1])//boolean to see if the 
					 {
						 patt[0]="1";
					 }
					 else
					 {
						 patt[0]="0";
					 }
					 if (old[i])
					 {
						 patt[1]="1";
					 }
					 else
					 {
						 patt[1]="0";
					 }
					 if (old[i+1])
					 {
						 patt[2]="1";
					 }
					 else
					 {
						 patt[2]="0";
					 }
				 }
				 concatenate=patt[0]+patt[1]+patt[2];
				 int r=-1;
				 String filler="";

				 while(!concatenate.equals(filler))
				 {
					 filler=arrOfStr[r+1];


					 r++;
					 
				 }

				 cells[i]=x[r];


			 }
				
	      }
	   }
	   public static boolean[] conboolean(int l)
	   {
		   String x=Integer.toBinaryString(l);
	       int q = Integer.parseInt(x);
           String formattedStr = String.format("%08d",q);
		   boolean[] NumBool = new boolean[formattedStr.length()];

		   for (int i=0; i<formattedStr.length(); i++)
		   {
			   if (formattedStr.charAt(i)=='0')
			   {
				   NumBool[i]= false;
			   }
			   else
			   {
				   NumBool[i]=true;
			   }
		   }
		   return NumBool;


	   }
	   
	}
