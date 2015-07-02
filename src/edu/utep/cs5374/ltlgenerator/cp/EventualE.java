package edu.utep.cs5374.ltlgenerator.cp;

public class EventualE extends CompositePropositionParent {

	
	public static void main(String[] args)
	{
		for(int i = 0; i < 10; i++)
		{
			System.out.println(compute(i));
		}
	}
	
	public static String compute(int aCount)
	{
		if(aCount <= 0)
		{
			return "";
		}
		
		StringBuilder stringBuilder = new StringBuilder("(");
		
		for (int i = 0; i < aCount; i++)
		{
			stringBuilder.append(" !p" + i + " ");
			
			if(i < aCount - 1)
			{
				stringBuilder.append("&");
			}
		}
		stringBuilder.append(") & (");
		
		for (int front = 0; front < aCount; front++)
		{
			stringBuilder.append("(");
			for (int i = front; i < aCount; i++)
			{
				stringBuilder.append(" !p" + i + " ");
				if(i < aCount - 1)
				{
					stringBuilder.append("&");
				}
			}
			
			stringBuilder.append(") U (");
			for (int i = front; i < aCount; i++)
			{
				String negation = i == front ? "" : "!";
				stringBuilder.append(" " + negation + "p" + i + " ");
				if (front != aCount - 1)
				{
					stringBuilder.append("&");
				}
			}
			
			if(front < aCount - 1)
			{
				stringBuilder.append("(");
			}
		}
		
		for (int i = 0; i <= aCount; i++)
		{
			stringBuilder.append(")");
		}
		
		return stringBuilder.toString();
	}

}
