package edu.utep.cs5374.ltlgenerator.generator;

import java.util.Scanner;


public class Generic {

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
		String[] PProp = new String[n];
		String[] QProp = new String[n];
		String[] RProp = new String[n];
		for(int i=0;i<n;i++)
		{
			LProp[i]="l"+(i+1);
			PProp[i]="p"+(i+1);
			QProp[i]="q"+(i+1);
			RProp[i]="r"+(i+1);

			
			//System.out.println(LProp[i]);
		}
		
		System.out.println("Enter the type of proposition for L");
		l=sc.nextInt();
		System.out.println("Enter the type of proposition for P");
		p=sc.nextInt();
		
		if(l==1)
		{
			L=AtleastOneC(LProp);
			System.out.println("AtleastOneC pattern is"+L);

		} 
		if(l==2)
		{
			L=ParallelC(LProp);
			System.out.println("ParallelC pattern is"+L);

			
		} 

	}

	
	// generates formula for AtleastOnceC
	public static String AtleastOneC(String[] strCP)
	{
		String str="(";
		for(int j=0;j<strCP.length-1;j++)
		{
		str=str+strCP[j]+"|";
		}
		for(int i=0;i<strCP.length;i++)
		{
			if(i==strCP.length-1)
			{
			str=str+strCP[i]+")";
			}
			
		}
		
		
			return str;
	}
	
	
	// generates formula for ParallelC
	public static String ParallelC(String[] strCP)
	{
		String str="(";
		for(int j=0;j<strCP.length-1;j++)
		{
		str=str+strCP[j]+"&";
		}
		for(int i=0;i<strCP.length;i++)
		{
			if(i==strCP.length-1)
			{
			str=str+strCP[i]+")";
			}
					}
		
		
			return str;
	}
}
