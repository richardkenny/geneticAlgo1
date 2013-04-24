import java.io.IOException;
import java.util.ArrayList;



public class GeneSource2
{

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException
	{
		ArrayList<Integer> population = new ArrayList<Integer>();
		ArrayList<Integer> fitness = new ArrayList<Integer>();
		ArrayList<Double> probability = new ArrayList<Double>();
		Double populationAverage = 0.00;
		
		int MIN = 1;
		int MAX = 300000;
		int GOAL = 25;
		double TOLERANCEPERCENT = 0.10;
		int TOLERANCE = (int)(GOAL * TOLERANCEPERCENT); 
		int generations = 10000;
		int generation = 0;
		
		//produce a population of random numbers
		Integer populationMember;

		for(int i = 0; i < MAX; i++)
		{
			populationMember = MIN + (int)(Math.random() * ((MAX - MIN) + 1));

			population.add(populationMember);
			
			populationAverage = ((populationAverage * (1+i)) + populationMember) / (2+i);
		}
		System.out.println(generation + "," + populationAverage);
		
		//Evolve until GOAL is met, within a tolerance.
		while(populationAverage < GOAL - TOLERANCE || populationAverage > GOAL + TOLERANCE)
		{
			generation++;
			//Select members with a probability > 80%
			for(int i = 0; i < MAX; i++)
			{
				Integer firstMember, secondMember;
				//fitness test for firstMember
				if(GOAL - 5 < population.get(i) && population.get(i) < GOAL + 5  )
				//if(true)
				{					
					firstMember = population.get(i);
					secondMember = population.get(i);;
					int j = i;
					while(secondMember == null)
					{
						//the selection process is random.
						//this is far from ideal, but the most trivial implementation.
						//a more ideal implementation would run as follows
						//generate population
						//sort
						//select member from a position near the anticipated goal
						j = 0 + (int)(Math.random() * (MAX));
						//fitness test for second member
						
						if( GOAL - 5 < population.get(j) && population.get(j) < GOAL + 5  )
						{
							//System.out.println(population.get(j));
							System.out.print(".");
							secondMember = population.get(j);
							//Random mutations are going to worsen the next generation with this implementation
							//I really don't know why I'm doing this part here.
							if(Math.random() <= 0.001)
							{
								secondMember = new Double(secondMember * Math.random()).intValue();
							}
						}
					};
					//putting this in a random position in the population would likely be more efficient
					populationMember = (firstMember + secondMember)/2;
					population.set(i, populationMember);
				}
				else
				{
					//populationMember i is not a good candidate for the next generation.
					//generate a new one.
					populationMember = MIN + (int)(Math.random() * ((MAX - MIN) + 1));
					population.set(i, populationMember);
				}
			}
			for(int i =0; i<MAX;i++)
			{
				populationAverage = ((populationAverage * (1+i)) + population.get(i)) / (2+i);
			}
		
			if(generation%100 == 0)
			{
				System.out.println(generation+","+populationAverage);
			}
					
		}//end of this generation
		
		for(int i =0; i<MAX;i++)
		{
			populationAverage = ((populationAverage * (1+i)) + population.get(i)) / (2+i);
		}
		
		System.out.println(generation+"," + populationAverage);
		
	}

}
