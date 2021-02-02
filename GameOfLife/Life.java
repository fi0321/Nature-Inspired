

import java.awt.Color;


public class Life { 
    private static int N;                     // N-by-N grid of cells
	private static int magnification;  // pixel-width of each cell
	private static int iter;
    private static boolean[][] cells;         // cell[i][j] = true if alive, false o/w
	private static boolean[][] cells2;         // cell[i][j] = true if alive, false o/w
    private static String patt;
    private static Picture pic;

    // nice pattern
    // double[] weight = {2.0, 2.0, -0.4, -0.4};
    // double threshhold = 1.4;

    // zebra stripes


   
    public Life(int N) {
        this.N = N;
		this.iter=iter;

		this.magnification=10;
        pic = new Picture(N*magnification, N*magnification);

       
    }

    public void update() {

        // update cells
        for (int i = 0; i < N; i++) 
		{
            for (int j = 0; j < N; j++)  
			{
				int counter=0;
				if (i==0&&j==0)
				{

					if (cells[i][j+1])
					{
						counter++;
					}
					if (cells[i+1][j+1])
					{
						counter++;
					}
					if (cells[i+1][j])
					{
						counter++;
					}
					
					if (cells[N-1][j])
					{
						counter++;
					}
					if (cells[N-1][j+1])
					{
						counter++;
					}
					if (cells[N-1][N-1])
					{
						counter++;
					}
					if (cells[i][N-1])
					{
						counter++;
					}
					if (cells[i+1][N-1])
					{
						counter++;
					}

					
				}
				else if(i==N-1 && j==N-1)
				{	
					if (cells[i-1][j])
					{
						counter++;
					}
		

					if (cells[i-1][j-1])
					{
						counter++;
					}

					if (cells[i][j-1])
					{
						counter++;
					}
					if (cells[0][0])
					{
						counter++;
					}
					if (cells[1][0])
					{
						counter++;
					}
					if (cells[2][0])
					{
						counter++;
					}
					if (cells[0][1])
					{
						counter++;
					}
					if (cells[0][2])
					{
						counter++;
					}
					
					

				}
				
				else if(i==0 && j==N-1)
				{	
					if (cells[i+1][j])
					{
						counter++;
					}
		

					if (cells[i+1][j-1])
					{
						counter++;
					}
					

					if (cells[i][j-1])
					{
						counter++;
					}
					
					
					if (cells[0][0])
					{
						counter++;
					}
					if (cells[1][0])
					{
						counter++;
					}
					
					
					if (cells[N-1][N-1])
					{
						counter++;
					}
					if (cells[N-1][N-2])
					{
						counter++;
					}
					
					if (cells[N-1][0])
					{
						counter++;
					}
				}
				
				/////here
				else if (i==N-1 && j==0)
				{
					
					if (cells[i-1][j])
					{
						counter++;
					}
					
					if (cells[i-1][j+1])
					{
						counter++;
					}
					
	
					if (cells[i][j+1])
					{
						counter++;
					}
					
						
					if (cells[0][0])
					{
						counter++;
					}
						
					if (cells[0][j+1])
					{
						counter++;
					}
						
					if (cells[N-1][N-1])
					{
						counter++;
					}
						
					if (cells[N-2][N-1])
					{
						counter++;
					}
						
					if (cells[0][N-1])
					{
						counter++;
					}
					
				}
				///here now
				else if (i==0)
				{
					
							
					if (cells[i+1][j])
					{
						counter++;
					}

					if (cells[i][j+1])
					{
						counter++;
					}

					if (cells[i+1][j+1])
					{
						counter++;
					}
						
					if (cells[i][j-1])
					{
						counter++;
					}
					if (cells[i+1][j-1])
					{
						counter++;
					}
					if (cells[N-1][j])
					{
						counter++;
					}
					if (cells[N-1][j-1])
					{
						counter++;
					}
					if (cells[N-1][j+1])
					{
						counter++;
					}

				}
				//here now
				
				else if(i==N-1)
				{

					if (cells[i-1][j])
					{
						counter++;
					}
					if (cells[i-1][j+1])
					{
						counter++;
					}
					
					if (cells[i][j+1])
					{
						counter++;
					}


					if (cells[i][j-1])
					{
						counter++;
					}
					if (cells[i-1][j-1])
					{
						counter++;
					}
					
					if (cells[0][j])
					{
						counter++;
					}
					if (cells[0][j-1])
					{
						counter++;
					}
					if (cells[0][j+1])
					{
						counter++;
					}

				}
				else if (j==0)
				{
					if (cells[i-1][j])
					{
						counter++;
					}
					

					if (cells[i+1][j])
					{
						counter++;
					}
					
					
					if (cells[i][j+1])
					{
						counter++;
					}
					if (cells[i-1][j+1])
					{
						counter++;
					}
					
					if (cells[i+1][j+1])
					{
						counter++;
					}
					if (cells[i][N-1])
					{
						counter++;
					}
					if (cells[i-1][N-1])
					{
						counter++;
					}
					if (cells[i+1][N-1])
					{
						counter++;
					}					
				}
				//here now now
				else if (j==N-1)
				{
										
					if (cells[i-1][j])
					{
						counter++;
					}
					
					
					if (cells[i-1][j-1])
					{
						counter++;
					}
					if (cells[i][j-1])
					{
						counter++;
					}
					
					
					if (cells[i+1][j-1])
					{
						counter++;
					}
					
					
					if (cells[i+1][j])
					{
						counter++;
					}
					
					
					
					if (cells[i][0])
					{
						counter++;
					}
					
					
					if (cells[i+1][0])
					{
						counter++;
					}
					if (cells[i-1][0])
					{
						counter++;
					}
						
				}
				else
				{
					if (cells[i-1][j])
					{
						counter++;
					}
					if (cells[i+1][j])
					{
						counter++;
					}
					if (cells[i][j+1])
					{
						counter++;
					}
					if (cells[i][j-1])
					{
						counter++;
					}
					if (cells[i-1][j+1])
					{
						counter++;
					}
					if (cells[i-1][j-1])
					{
						counter++;
					}
					if (cells[i+1][j+1])
					{
						counter++;
					}
					if (cells[i+1][j-1])
					{
						counter++;
					}
				}
                // update cell (i, j) by looking at neighbors
           //     double sum = 0.0;
              //  for (int ii = i - MAXDIST; ii <= i + MAXDIST; ii++) {
              //      for (int jj = j - MAXDIST; jj <= j + MAXDIST; jj++) {
                        //// dist = Math.abs(ii-i) + Math.abs(jj-j);
                 //       dist = Math.abs(ii-i);

                        // uses most up-to-date values, avoid out-of-bounds
                   //     color = cells[(ii + N) % N][(jj + N) % N];

                        // if (dist <= MAXDIST && color) sum += weight[dist];
                   //     if (color) sum += weight[dist];

				if(counter<2)
				{
					cells2[i][j]=false;
				}
				else if((counter==2 || counter==3)&& cells[i][j]==true)
				{
					cells2[i][j]=true;
				}
				else if(counter>3 && cells[i][j]==true)
				{
					cells2[i][j]=false;
				}
				else if(counter==3 && cells[i][j]==false)
				{
					cells2[i][j]=true;
				}
	

			
				
            }
        }
		for (int v=0;v<N;v++)
		{
			for (int p=0;p<N;p++)
			{
				//cells[v][p]=cells2[v][p];
				cells[v][p]=cells2[v][p];
			}
			
		}
            
        
    }

    // draw cells
    public void draw() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
				for (int offsetX = 0; offsetX < magnification; offsetX++)
					{
					for (int offsetY = 0; offsetY < magnification; offsetY++)
						{
							if (cells[i][j])
							{
								pic.set((i*magnification)+offsetX, (j*magnification)+offsetY, Color.BLACK);
							}
							else            
							{	
								pic.set((i*magnification)+offsetX, (j*magnification)+offsetY, Color.WHITE);
							}
						}
						
					}
             
            }
        }
			  pic.show();	
				
      }


    // test client
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
		int iter = Integer.parseInt(args[1]);
		String patt= args[2];
        cells = new boolean[N][N];
	    cells2 = new boolean[N][N];
		if (N<40)
		{
			System.out.println("Please enter a grid size above 40");
			return;
		}
        // initialize with random pattern
        if(patt.equals("R") || patt.equals("r"))
		{
			
			for (int i = 0; i < N; i++)
			{
				for (int j = 0; j < N; j++)
				{
					cells[i][j] = Math.random() < 0.5;
					cells2[i][j]=cells[i][j];
					
				}
			}
		}
		else if(patt.equals("G")||patt.equals("g"))
		{
			for (int i = 0; i < N; i++)
			{
				for (int j = 0; j < N; j++)
				{
					cells[i][j] = false;
					cells2[i][j]=false;
					
				}
			}
			cells[26][2]=true;
			cells[24][3]=true;
			cells[26][3]=true;
			cells[14][4]=true;
			cells[15][4]=true;
			cells[22][4]=true;
			cells[23][4]=true;
			cells[36][4]=true;
			cells[37][4]=true;
			cells[13][5]=true;
			cells[17][5]=true;
			cells[22][5]=true;
			cells[23][5]=true;
			cells[36][5]=true;
			cells[37][5]=true;
			cells[2][6]=true;
			cells[3][6]=true;
			cells[12][6]=true;
			cells[18][6]=true;
			cells[22][6]=true;
			cells[23][6]=true;
			cells[2][7]=true;
			cells[3][7]=true;
			cells[12][7]=true;
			cells[16][7]=true;
			cells[18][7]=true;
			cells[19][7]=true;
			cells[24][7]=true;
			cells[26][7]=true;
			cells[12][8]=true;
			cells[18][8]=true;
			cells[26][8]=true;
			cells[13][9]=true;
			cells[17][9]=true;
			cells[14][10]=true;
			cells[15][10]=true;
			
			
			cells2[26][2]=true;
			cells2[24][3]=true;
			cells2[26][3]=true;
			cells2[14][4]=true;
			cells2[15][4]=true;
			cells2[22][4]=true;
			cells2[23][4]=true;
			cells2[36][4]=true;
			cells2[37][4]=true;
			cells2[13][5]=true;
			cells2[17][5]=true;
			cells2[22][5]=true;
			cells2[23][5]=true;
			cells2[36][5]=true;
			cells2[37][5]=true;
			cells2[2][6]=true;
			cells2[3][6]=true;
			cells2[12][6]=true;
			cells2[18][6]=true;
			cells2[22][6]=true;
			cells2[23][6]=true;
			cells2[2][7]=true;
			cells2[3][7]=true;
			cells2[12][7]=true;
			cells2[16][7]=true;
			cells2[18][7]=true;
			cells2[19][7]=true;
			cells2[24][7]=true;
			cells2[26][7]=true;
			cells2[12][8]=true;
			cells2[18][8]=true;
			cells2[26][8]=true;
			cells2[13][9]=true;
			cells2[17][9]=true;
			cells2[14][10]=true;
			cells2[15][10]=true;

		}
		else
		{
			System.out.println("Please enter an R or G for the initial pattern");
			return;
		}
  
		System.out.println("the pattern is " + patt);

        Life life = new Life(N);
        life.draw();

        for (int i = 0; i < iter; i++) {
            try { Thread.sleep(35); } // so we can see what's happening!
            catch (Exception ex) { /* ignore */ }
            
            life.update();
            life.draw();
        }
    }

}
