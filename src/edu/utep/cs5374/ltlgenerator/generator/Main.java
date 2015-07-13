package edu.utep.cs5374.ltlgenerator.generator;

import java.util.Scanner;

import edu.utep.cs5374.ltlgenerator.and.AndMinusL;
import edu.utep.cs5374.ltlgenerator.cp.*;

public class Main {
	public static void main(String[] args) {

		int l, p, q, r, n; // to store user input type of proposition
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
		
		sc.close();
		
		AndMinusL aml = new AndMinusL();
		
		L = aml.replaceAndLMinus(L);
		
		System.out.println(L);
	}
}

