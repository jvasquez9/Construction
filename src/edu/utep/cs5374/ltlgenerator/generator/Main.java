package edu.utep.cs5374.ltlgenerator.generator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
import edu.utep.cs5374.ltlgenerator.remainingscopes.AfterL;
import edu.utep.cs5374.ltlgenerator.remainingscopes.AfterLUntilRc;
import edu.utep.cs5374.ltlgenerator.remainingscopes.AfterLUntilRe;
import edu.utep.cs5374.ltlgenerator.remainingscopes.BetweenLandRc;
import edu.utep.cs5374.ltlgenerator.remainingscopes.BetweenLandRe;

public class Main {
	public static void main(String[] args) {

		int n, globalScope, beforeRScope, remainingScope; // to store user input type of proposition
		String L="", P="", Q="", R=""; //to keep pattern before ANDL, ANDR
		String globalFormula = "", beforeRFormula = "", remainingScopeFormula = "";

		Scanner sc = new Scanner(System.in);

		Map<String, String> LPQAndRMap = new HashMap<String, String>();
		
		String[] LPQAndR = {"l", "p", "q", "r"};
		
		System.out.println("Enter the number of propositions:");
		n=sc.nextInt();
		
		System.out.println("\n");
		
		for(int i = 0; i < LPQAndR.length; i++)
		{
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
			int selection = sc.nextInt();
			
			CompositePropositionParent[] cpTable = {
					new AtleastOneC(), new AtleastOneH(), new AtleastOneE(),
					new ParallelC(), new ParallelH(), new ParallelE(),
					new ConsecutiveC(), new ConsecutiveH(), new ConsecutiveE(),
					new EventualC(), new EventualH(), new EventualE()
			};
	
			if(selection >= 1 && selection <= cpTable.length)
			{
				String result = cpTable[selection - 1].compute(n, LPQAndR[i].charAt(0));
				LPQAndRMap.put(LPQAndR[i], result);
				System.out.println("\n" + LPQAndR[i] + "ltl: " + LPQAndRMap.get(LPQAndR[i]));
				System.out.println();
				System.out.println("Hit enter to continue");
				try {
					System.in.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				i--;
			}
		}
		
		L = LPQAndRMap.get("l");
		P = LPQAndRMap.get("p");
		Q = LPQAndRMap.get("q");
		R = LPQAndRMap.get("r");
		
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
		
		globalFormula = getGlobalScopeFormula(globalScope, P, Q, n);
		
		System.out.println(
				"\n\nBefore R Scope\n" +
				"1 # AbsenceofPBeforeRc"+"\n"+
				"2 # AbsenceofPBeforeRe"+"\n"+
				"3 # ExistenceofPBeforeRc"+"\n"+
				"4 # ExistanceOfPBeforeRe"+"\n"+
				"5 # QPrecedesPcBeforeRc"+"\n"+
				"6 # QPrecedesPeBeforeRc"+"\n"+
				"7 # QPrecedesPcBeforeRe"+"\n"+
				"8 # QPrecedesPeBeforeRe"+"\n"+
				"9 # QStrictlyPrecedesPcBeforeRc"+"\n"+
				"10 # QStrictlyPrecedesPeBeforeRc"+"\n"+
				"11 # QStrictlyPrecedesPcBeforeRe"+"\n"+
				"12 # QStrictlyPrecedesPeBeforeRe"+"\n"+				
				"13 # QRespondstoPBeforeRc "+"\n"+
				"14 # QRespondstoPBeforeRe "+"\n"
				);
		
		System.out.println("Enter the type of Before R Scope:");
		beforeRScope = sc.nextInt();
		
		beforeRFormula = getBeforeRScopeFormula(beforeRScope, P, Q, R, n);
		
		System.out.println(
				"\n\nRemaining Scope\n" +
				"1 # After L"+"\n"+ 
				"2 # Between L and Rc "+"\n"+ 
				"3 # Between L and Re "+"\n" +
				"4 # After L until Rc "+"\n"+
				"5 # After L until Re "+"\n"				
				);
		System.out.println("Select the type of Remaining Scope:");
		
		remainingScope = sc.nextInt();

		remainingScopeFormula = getRemainingScopeFormula(remainingScope, beforeRFormula, globalFormula, R, L, n);
		
		sc.close();
	}
	
	public static String getGlobalScopeFormula(int globalScope, String P, String Q, int n){
		String globalFormula = "";
		if(globalScope == 1){
			// Abscence of P
			globalFormula = new AbsenseOfP().getFormula(P, Q, n);
			System.out.println("Abscence of P : \n" + globalFormula);
		}
		else if(globalScope == 2){
			// Existance of P
			globalFormula = new ExistenceOfP().getFormula(P, Q, n);
			System.out.println("Existance of P : \n" + globalFormula);
		}
		else if(globalScope == 3){
			// Q responds to P
			globalFormula = new QRespondsP().getFormula(P,Q, n);
			System.out.println("Q Responds P : \n" + globalFormula);
		}
		else if(globalScope == 4){
			// Q Strictly Precedes Pc
			globalFormula = new QStrictlyPrecedesPc().getFormula(P, Q, n);
			System.out.println("Q Strictly Precedes Pc : \n" + globalFormula);
		}
		else if(globalScope == 5){
			// Q Strictly Precedes Pc
			globalFormula = new QStrictlyPrecedesPe().getFormula(P, Q, n);
			System.out.println("Q Strictly Precedes Pe : \n" + globalFormula);
		}
		else if(globalScope == 6){
			// Q precedes Pc star
			globalFormula = new QPrecedesPcStar().getFormula(P, Q, n);
			System.out.println("Q Precedes Pc* : \n" + globalFormula);
		}
		else if(globalScope == 7){
			// Q precedes Pc Plus
			globalFormula = new QPrecedesPcPlus().getFormula(P, Q, n);
			System.out.println("Q Precedes Pc+ : \n" + globalFormula);
		}
		else if(globalScope == 8){
			// Q Precedes Pe Star
			globalFormula = new QPrecedesPeStar().getFormula(P, Q, n);
			System.out.println("Q Precedes Pe* : \n" + globalFormula);
		}
		else if(globalScope == 9){
			// Q Precedes Pe Plus
			globalFormula = new QPrecedesPePlus().getFormula(P, Q, n);
			System.out.println("Q Precedes Pe+ : \n" + globalFormula);
		}
		else{
			System.out.println("Global scope doesn't match!");
		}
		return globalFormula;

	}
	
	public static String getBeforeRScopeFormula(int beforeRScope, String P, String Q, String R, int n){
		String beforeRFormula = "";
	
		if(beforeRScope == 1){
			// AbsenceofPBeforeRc
			beforeRFormula = new AbsenceofPBeforeRc().getFormula(P,Q,R,n);
			System.out.println("AbsenceofPBeforeRc : \n" + beforeRFormula);
		}
		else if(beforeRScope == 2){
			// AbsenceofPBeforeRe
			beforeRFormula = new AbsenceofPBeforeRe().getFormula(P,Q,R,n);
			System.out.println("AbsenceofPBeforeRe : \n" + beforeRFormula);
		}
		else if(beforeRScope == 3){
			// ExistenceofPBeforeRc
			beforeRFormula = new ExistenceofPBeforeRc().getFormula(P,Q,R,n);
			System.out.println("Existance of P Before Rc : \n" + beforeRFormula);
		}
		else if(beforeRScope == 4){
			// ExistanceOfPBeforeRe
			beforeRFormula = new ExistanceOfPBeforeRe().getFormula(P,Q,R,n);
			System.out.println("Existance of P Before Re : \n" + beforeRFormula);
		}
		else if(beforeRScope == 5){
			// QPrecedesPcBeforeRc
			beforeRFormula = new QPrecedesPcBeforeRc().getFormula(P,Q,R,n);
			System.out.println("QPrecedesPcBeforeRc : \n" + beforeRFormula);
		}
		else if(beforeRScope == 6){
			// QPrecedesPeBeforeRc
			beforeRFormula = new QPrecedesPeBeforeRc().getFormula(P,Q,R,n);
			System.out.println("QPrecedesPeBeforeRc : \n" + beforeRFormula);
		}
		else if(beforeRScope == 7){
			// QPrecedesPcBeforeRe
			beforeRFormula = new QPrecedesPcBeforeRe().getFormula(P,Q,R,n);
			System.out.println("QPrecedesPcBeforeRe : \n" + beforeRFormula);
		}
		else if(beforeRScope == 8){
			// QPrecedesPeBeforeRe
			beforeRFormula = new QPrecedesPeBeforeRe().getFormula(P,Q,R,n);
			System.out.println("QPrecedesPeBeforeRe : \n" + beforeRFormula);
		}
		else if(beforeRScope == 9){
			//  QStrictlyPrecedesPcBeforeRc
			beforeRFormula = new QStrictlyPrecedesPcBeforeRc().getFormula(P,Q,R,n);
			System.out.println("QStrictlyPrecedesPcBeforeRc : \n" + beforeRFormula);
		}
		else if(beforeRScope == 10){
			//  QStrictlyPrecedesPeBeforeRc
			beforeRFormula = new QStrictlyPrecedesPeBeforeRc().getFormula(P,Q,R,n);
			System.out.println("QStrictlyPrecedesPeBeforeRc : \n" + beforeRFormula);
		}
		
		else if(beforeRScope == 11){
			// QStrictlyPrecedesPcBeforeRe
			beforeRFormula = new QStrictlyPrecedesPcBeforeRe().getFormula(P,Q,R,n);
			System.out.println("QStrictlyPrecedesPcBeforeRe : \n" + beforeRFormula);
		}
		
		else if(beforeRScope == 12){
			// QStrictlyPrecedesPcBeforeRe
			beforeRFormula = new QStrictlyPrecedesPeBeforeRe().getFormula(P,Q,R,n);
			System.out.println("QStrictlyPrecedesPeBeforeRe : \n" + beforeRFormula);
		}
		else if(beforeRScope == 13){
			// QStrictlyPrecedesPcBeforeRe
			beforeRFormula = new QRespondstoPBeforeRc().getFormula(P,Q,R,n);
			System.out.println("QRespondstoPBeforeRc : \n" + beforeRFormula);
		}
		else if(beforeRScope == 14){
			// QStrictlyPrecedesPcBeforeRe
			beforeRFormula = new QRespondstoPBeforeRe().getFormula(P,Q,R,n);
			System.out.println("QRespondstoPBeforeRe : \n" + beforeRFormula);
		}
		return beforeRFormula;
	}

	public static String getRemainingScopeFormula(int remainingScope, String beforeRFormula, String globalFormula, String R, String L, int n){
		String remainingScopeFormula = "";
	
		if(remainingScope == 1){
			// After L
			remainingScopeFormula = new AfterL().getFormula(beforeRFormula,globalFormula,R,L,n);
			System.out.println("After L : \n" + remainingScopeFormula);
		}
		else if(remainingScope == 2){
			// BetweenLandRc
			remainingScopeFormula = new BetweenLandRc().getFormula(beforeRFormula,globalFormula,R,L,n);
			System.out.println("BetweenLandRc : \n" + remainingScopeFormula);
		}
		else if(remainingScope == 3){
			// BetweenLandRe
			remainingScopeFormula = new BetweenLandRe().getFormula(beforeRFormula,globalFormula,R,L,n);
			System.out.println("Between L and Re : \n" + remainingScopeFormula);
		}
	
		else if(remainingScope == 4){
			// AfterLUntilRc
			remainingScopeFormula = new AfterLUntilRc().getFormula(beforeRFormula,globalFormula,R,L,n);
			System.out.println("After L until Rc : \n" + remainingScopeFormula);
		}
		else if(remainingScope == 5){
			// AfterLUntilRe
			remainingScopeFormula = new AfterLUntilRe().getFormula(beforeRFormula,globalFormula,R,L,n);
			System.out.println("After L until Re : \n" + remainingScopeFormula);
		}
		return remainingScopeFormula;
	}

}

