# Construction
Software Construction LTL generator

1. Run the main.java file.

2. Next, select the CP type, program will ask for number of propositions.

	Enter the number of propositions for l : 
	
3. Then the program will ask for the type of CP you want for that Proposition

	1 # AtleastOneC 
	2 # AtleastOneH 
	3 # AtleastOneE 
	4 # ParallelC
	5 # ParallelH
	6 # ParallelE
	7 # ConsecutiveC
	8 # ConsecutiveH
	9 # ConsecutiveE
	10 # EventualC
	11 # EventualH
	12 # EventualE
	Select the type of CP:

4. After inputing number of prositions, the program will ask the same questions for Pltl, Qltl, Rltl.

5. Then program will ask the user to select the type of Global Scope.

	Global Scope
	1 # Absence of P 
	2 # Existence of P 
	3 # Q Responds to P 
	4 # Q Strictly Precedes Pc
	5 # Q Strictly Precedes Pe
	6 # Q Precedes Pc*
	7 # Q Precedes Pc+
	8 # Q Precedes Pe*
	9 # Q Precedes Pe+

	Enter the type of Global Scope:

6. After inputing the Global Scope type, program will generate the corresponding global scope.

7. Then it will ask the user to select the type of Before R Scope.

	Before R Scope
	1 # AbsenceofPBeforeRc
	2 # AbsenceofPBeforeRe
	3 # ExistenceofPBeforeRc
	4 # ExistanceOfPBeforeRe
	5 # QPrecedesPcBeforeRc
	6 # QPrecedesPeBeforeRc
	7 # QPrecedesPcBeforeRe
	8 # QPrecedesPeBeforeRe
	9 # QStrictlyPrecedesPcBeforeRc
	10 # QStrictlyPrecedesPeBeforeRc
	11 # QStrictlyPrecedesPcBeforeRe
	12 # QStrictlyPrecedesPeBeforeRe
	13 # QRespondstoPBeforeRc 
	14 # QRespondstoPBeforeRe 

	Enter the type of Before R Scope:

8. Program will generate the corresponding Before R Scope.

9. The it will ask for Remaining Scope type.

	Remaining Scope
	1 # After L
	2 # Between L and Rc 
	3 # Between L and Re 
	4 # After L until Rc 
	5 # After L until Re

10. The program will terminate by showing the selected Remaining Scope output.