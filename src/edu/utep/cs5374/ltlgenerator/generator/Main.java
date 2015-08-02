package edu.utep.cs5374.ltlgenerator.generator;

import java.util.Scanner;

import RemainingScopes.AfterLUntilRc;
import RemainingScopes.AfterLUntilRe;
import edu.utep.cs5374.ltlgenerator.and.*;
import edu.utep.cs5374.ltlgenerator.beforerscope.*;
import edu.utep.cs5374.ltlgenerator.cp.*;
import edu.utep.cs5374.ltlgenerator.globalscope.AbsenseOfP;
import edu.utep.cs5374.ltlgenerator.globalscope.ExistenceOfP;
import edu.utep.cs5374.ltlgenerator.globalscope.QPrecedesPcPlus;
import edu.utep.cs5374.ltlgenerator.globalscope.QPrecedesPcStar;
import edu.utep.cs5374.ltlgenerator.globalscope.QPrecedesPePlus;
import edu.utep.cs5374.ltlgenerator.globalscope.QPrecedesPeStar;
import edu.utep.cs5374.ltlgenerator.globalscope.QRespondsP;
import edu.utep.cs5374.ltlgenerator.globalscope.QStrictlyPrecedesPc;
import edu.utep.cs5374.ltlgenerator.globalscope.QStrictlyPrecedesPe;

public class Main {
	public static void main(String[] args) {

		int l, p, q, r, n, andType, globalScope, beforeRScope, remainingScope; // to store user input type of proposition
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

		System.out.println("Select the type of CP:");
		l=sc.nextInt();
		
		System.out.println("Enter the number of propositions:");
		n=sc.nextInt();
		
		CompositePropositionParent[] cpTable = {
				new AtleastOneC(), new AtleastOneH(), new AtleastOneE(),
				new ParallelC(), new ParallelH(), new ParallelE(),
				new ConsecutiveC(), new ConsecutiveH(), new ConsecutiveE(),
				new EventualC(), new EventualH(), new EventualE()
		};

		if(l >= 1 && l <= cpTable.length)
		{
			P = cpTable[l - 1].compute(n,'p');
			Q = cpTable[l - 1].compute(n,'q');
			R = cpTable[l - 1].compute(n,'r');
			System.out.println("\n\nPltl :" + P);
			System.out.println("Qltl :" + Q);
		}

				
		System.out.println(
				"\n\nGlobal Scope\n" +
				"1 # Absence of P "+"\n"+ 
				"2 # Existence of P "+"\n"+ 
				"3 # Q Responds to P "+"\n"+ 
				"4 # Q Strictly Precedes Pc"+"\n"+
				"5 # Q Strictly Precedes Pe"+"\n"+
				"6 # Q Precedes Pc*"+"\n"+		
				"7 # Q Precedes Pc+" +"\n"+
				"8 # Q Precedes Pe*" +"\n"+
				"9 # Q Precedes Pe+" +"\n");

		System.out.println("Enter the type of Global Scope:");
		globalScope = sc.nextInt();
		if(globalScope == 1){
			// Abscence of P
			String formula = new AbsenseOfP().getFormula(Q, n);
			System.out.println("Abscence of P : \n" + formula);
		}
		else if(globalScope == 2){
			// Existance of P
			String formula = new ExistenceOfP().getFormula(Q, n);
			System.out.println("Existance of P : \n" + formula);
		}
		else if(globalScope == 3){
			// Q responds to P
			String formula = new QRespondsP().getFormula(P,Q, n);
			System.out.println("Q Responds P : \n" + formula);
		}
		else if(globalScope == 4){
			// Q Strictly Precedes Pc
			String temp = new QStrictlyPrecedesPc().formulaOfScope(Q, P);
			System.out.println("Q Strictly Precedes Pc : \n" + temp);
		}
		else if(globalScope == 5){
			// Q Strictly Precedes Pc
			String temp = new QStrictlyPrecedesPe().getFormula(Q, n);
			System.out.println("Q Strictly Precedes Pe : \n" + temp);
		}
		else if(globalScope == 6){
			// Q precedes Pc star
			String temp = new QPrecedesPcStar().formulaOfScope(P, Q);
			System.out.println("Q Precedes Pc* : \n" + temp);
		}
		else if(globalScope == 7){
			// Q precedes Pc Plus
			String temp = new QPrecedesPcPlus().formulaOfScope(Q, P);
			System.out.println("Q Precedes Pc+ : \n" + temp);
		}
		else if(globalScope == 8){
			// Q Precedes Pe Star
			String temp = new QPrecedesPeStar().getFormula(Q, n);
			System.out.println("Q Precedes Pe* : \n" + temp);
		}
		else if(globalScope == 9){
			// Q Precedes Pe Plus
			String temp = new QPrecedesPePlus().getFormula(Q, n);
			System.out.println("Q Precedes Pe+ : \n" + temp);
		}
		else{
			System.out.println("Global scope doesn't match!");
		}

		System.out.println(
				"\n\nBefore R Scope\n" +
				"1 # QPrecedesPcBeforeRe"+"\n"+ 
				"2 # QStrictlyPrecedesPcBeforeRe "+"\n"+ 
				"3 # QPrecedesPeBeforeRe "+"\n" +
				"4 # QStrictlyPrecedesPeBeforeRe "+"\n"+
				"5 # Existance of P before Re "+"\n"+
				"13 # QRespondstoPBeforeRc "+"\n"+
				"14 # QRespondstoPBeforeRe "+"\n"
				);

		System.out.println("Enter the type of Before R Scope:");
		beforeRScope = sc.nextInt();
		if(beforeRScope == 1){
			// QPrecedesPcBeforeRe
			String formula = new QPrecedesPcBeforeRe().getFormula(P,Q,R,n);
			System.out.println("QPrecedesPcBeforeRe : \n" + formula);
		}
		else if(beforeRScope == 2){
			// QStrictlyPrecedesPcBeforeRe
			String formula = new QStrictlyPrecedesPcBeforeRe().getFormula(P,Q,R,n);
			System.out.println("QStrictlyPrecedesPcBeforeRe : \n" + formula);
		}
		else if(beforeRScope == 3){
			// QStrictlyPrecedesPcBeforeRe
			String formula = new QPrecedesPeBeforeRe().getFormula(P,Q,R,n);
			System.out.println("QPrecedesPeBeforeRe : \n" + formula);
		}
		else if(beforeRScope == 4){
			// QStrictlyPrecedesPcBeforeRe
			String formula = new QStrictlyPrecedesPeBeforeRe().getFormula(P,Q,R,n);
			System.out.println("QStrictlyPrecedesPeBeforeRe : \n" + formula);
		}
		else if(beforeRScope == 5){
			// QStrictlyPrecedesPcBeforeRe
			String formula = new ExistanceOfPBeforeRe().getFormula(P,Q,R,n);
			System.out.println("Existance of P Before Re : \n" + formula);
		}
		
		else if(beforeRScope == 13){
			// QStrictlyPrecedesPcBeforeRe
			String formula = new QRespondstoPBeforeRc().getFormula(P,Q,R,n);
			System.out.println("QRespondstoPBeforeRc : \n" + formula);
		}
		else if(beforeRScope == 14){
			// QStrictlyPrecedesPcBeforeRe
			String formula = new QRespondstoPBeforeRe().getFormula(P,Q,R,n);
			System.out.println("QRespondstoPBeforeRe : \n" + formula);
		}
		
		System.out.println(
				"\n\nRemaining Scope\n" +
				"1 # After L"+"\n"+ 
				"2 # Between L and Rc "+"\n"+ 
				"3 # Between L and Re "+"\n" +
				"4 # After L until Rc "+"\n"+
				"5 # After L until Re "+"\n"				
				);
		remainingScope = sc.nextInt();
		if(remainingScope == 1){
			// After L
			//String formula = new AfterL().getFormula(P,Q,R,n);
			//System.out.println("After L : \n" + formula);
		}
		else if(remainingScope == 2){
			// BetweenLandRc
			//String formula = new BetweenLandRc().getFormula(P,Q,R,n);
			//System.out.println("BetweenLandRc : \n" + formula);
		}
		else if(remainingScope == 3){
			// BetweenLandRe
			//String formula = new BetweenLandRe().getFormula(P,Q,R,n);
			//System.out.println("Between L and Re : \n" + formula);
		}
		else if(remainingScope == 4){
			// AfterLUntilRc
			String formula = new AfterLUntilRc().getFormula(P,Q,R,L,n);
			System.out.println("QStrictlyPrecedesPeBeforeRe : \n" + formula);
		}
		else if(remainingScope == 5){
			// AfterLUntilRe
			String formula = new AfterLUntilRe().getFormula(P,Q,R,L,n);
			System.out.println("Existance of P Before Re : \n" + formula);
		}
		

		
		sc.close();
	}
}

