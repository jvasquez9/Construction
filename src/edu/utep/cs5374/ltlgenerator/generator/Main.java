package edu.utep.cs5374.ltlgenerator.generator;

import java.util.Scanner;

import edu.utep.cs5374.ltlgenerator.cp.AtleastOneC;
import edu.utep.cs5374.ltlgenerator.cp.ParallelC;


public class Main {
	public static void main(String[] args) {

			int l, p, q, r, n; // to store user input type of proposition
			String L="", P="", Q="", R=""; //to keep pattern before ANDL, ANDR
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println(
			"1 # AtleastOneC "+"\n"+ 
			"2 # ParallelC"+"\n"+
			"3 # ConsecutiveC" +"\n"+
			"4 # EventualC"+"\n"+
			"5 # AtleastOneE"+"\n"+
		    "6 # ParallelE"+"\n"+
		    "7 # ConsecutiveE"+"\n"+
		    "8 # EventualE");
			
			System.out.println("Enter the number of propositions:");
			n=sc.nextInt();
			
			System.out.println("Enter the type of proposition for L:");
			l=sc.nextInt();
			
			if(l==1)
			{
				L= AtleastOneC.compute(n);
				System.out.println("AtleastOneC pattern is" + L);

			} 
			if(l==2)
			{
				L= ParallelC.compute(n);
				System.out.println("ParallelC pattern is"+L);
			} 
			
			sc.close();
		}
	}
