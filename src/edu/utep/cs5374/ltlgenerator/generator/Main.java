package edu.utep.cs5374.ltlgenerator.generator;

import java.util.Scanner;


import edu.utep.cs5374.ltlgenerator.cp.AtleastOneH;

import edu.utep.cs5374.ltlgenerator.cp.ConsecutiveH;

import edu.utep.cs5374.ltlgenerator.cp.ParallelC;
import edu.utep.cs5374.ltlgenerator.cp.ParallelE;

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
			
			if(l==1)
			{
				L= AtleastOneC.compute(n);
				System.out.println("AtleastOneC pattern is" + L);

			} 
			if(l==2)
			{

				L= AtleastOneH.compute(n);
				System.out.println("ParallelC pattern is"+L);

			} 
			if(l==4)
			{

				L= ParallelC.compute(n);
				System.out.println("ParallelC pattern is"+L);


			} 
			if(l==8)
			{
				ConsecutiveH cshCP = new ConsecutiveH();
				L=cshCP.compute(n);
				System.out.println("ConsecutiveH pattern is"+L);

			} 
			sc.close();
		}
	}
