package edu.utep.cs5374.ltlgenerator.generator;

import java.util.Scanner;




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
			System.out.println("AtleastOneH pattern is" + L);


		}
		
		if(l==3)
		{

			
			L= AtleastOneE.compute(n);
			System.out.println("AtleastOneE pattern is" + L);


		}
		
		if(l==4)
		{

			
			L= ParallelC.compute(n);
			System.out.println("ParallelC pattern is" + L);


		}
		if(l==5)
		{

			
			L= ParallelH.compute(n);
			System.out.println("ParallelH pattern is" + L);
		}
		
		if(l==6)
		{
			L=ParallelE.compute(n);
			System.out.println("ParallelE pattern is" + L);
		} 
		if(l==8)
		{
			L = ConsecutiveH.compute(n);
			System.out.println("ConsecutiveH pattern is" + L);
		}
		if(l==12)
		{
			L = EventualE.compute(n);
			System.out.println("ConsecutiveH pattern is" + L);
		}
		
		sc.close();
	}
}

