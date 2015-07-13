package edu.utep.cs5374.ltlgenerator.generator;

import java.util.Scanner;

import edu.utep.cs5374.ltlgenerator.and.*;
import edu.utep.cs5374.ltlgenerator.cp.*;

public class Main {
	public static void main(String[] args) {

		int l, p, q, r, n, andType; // to store user input type of proposition
		String L="", P="", Q="", R=""; //to keep pattern before ANDL, ANDR

		Scanner sc = new Scanner(System.in);

		System.out.println(
				"1 # AtleastOneC "+"\n"+ 
				"2 # AtleastOneH "+"\n"+ 
				"3 # AtleastOneE "+"\n"+ 
				"4 # ParallelC"+"\n"+
				"5 # ParallelH"+"\n"+
				"6 # ParallelE"+"\n"+		
				"7 # ConsecutiveC" +"\n"+
				"8 # ConsecutiveH" +"\n"+
				"9 # ConsecutiveE" +"\n"+		
				"10 # EventualC"+"\n"+
				"11 # EventualH"+"\n"+
		        "12 # EventualE");

		System.out.println("Enter the number of propositions:");
		n=sc.nextInt();

		System.out.println("Enter the type of proposition for L:");
		l=sc.nextInt();
		
		System.out.println(
				"And\n" +
				"1 # AndR "+"\n"+ 
				"2 # AndL "+"\n"+ 
				"3 # AndMinusL "+"\n");
		System.out.println("Enter the type of And (P is fixed for now):");
		andType = sc.nextInt();
		
		CompositePropositionParent[] cpTable = {
				new AtleastOneC(), new AtleastOneH(), new AtleastOneE(),
				new ParallelC(), new ParallelH(), new ParallelE(),
				new ConsecutiveC(), new ConsecutiveH(), new ConsecutiveE(),
				new EventualC(), new EventualH(), new EventualE()
		};
		
		if(l >= 1 && l <= cpTable.length)
		{
			L = cpTable[l - 1].compute(n);
			System.out.println("Result: " + L);
		}
		
		
		String andResult;
		if(andType == 1)
		{
			// AndR
		}
		else if(andType == 2)
		{
			// AndL
			andResult = new AndL().and(L, "P");
			System.out.println("AndL result " + andResult);
		}
		else if(andType == 3)
		{
			// AndMinusL
			andResult = new AndMinusL().and(L, "Q");
			System.out.println("AndMinusL result " + andResult);
			
			AndMinusL aml = new AndMinusL();
			
			L = aml.replaceAndLMinus(L);
			
			System.out.println(L);
		}
		else 
		{
			System.out.println("And type doesn't match");
		}
		
		
		sc.close();
	}
}

