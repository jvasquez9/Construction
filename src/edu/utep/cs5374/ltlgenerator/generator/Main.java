package edu.utep.cs5374.ltlgenerator.generator;

import java.util.Scanner;

import edu.utep.cs5374.ltlgenerator.cp.AtleastOneC;
import edu.utep.cs5374.ltlgenerator.cp.ParallelC;


public class Main {
	public static void main(String[] args) {
			// TODO Auto-generated method stub
			int l,p,q,r,n; // to store user input type of proposition
			String L="",P="",Q="",R=""; //to keep pattern before ANDL, ANDR
			
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
			System.out.println("Enter the number of propositions");
			n=sc.nextInt();
			String[] LProp = new String[n];
			for(int i=0;i<LProp.length;i++)
			{
				LProp[i]="l"+(i+1);
				//System.out.println(LProp[i]);
			}
			
			System.out.println("Enter the type of proposition for L");
			l=sc.nextInt();
			
			if(l==1)
			{
				AtleastOneC aoCP = new AtleastOneC();
				L=aoCP.compute(n);
				System.out.println("AtleastOneC pattern is"+L);

			} 
			if(l==2)
			{
				ParallelC plCP = new ParallelC();
				L=plCP.compute(n);
				System.out.println("ParallelC pattern is"+L);

				
			} 

		}
	}
