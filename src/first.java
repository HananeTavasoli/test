

public class first {

	public static void main(String[] args) {
		
		String filename1="";
		String filename2="";
		
		if (args.length > 1) {
		     	filename1 = args[0];
		    	filename2 =args[1];
		}
		else
		{
			System.out.println("Usage: first.java filename_1 filename_2");
			return;
		}
		
		Document d1=new Document(filename1);
		Document d2=new Document(filename2);
		double result= d1.vector_angle(d2);
				
		System.out.printf("The distance between the document %s and %s is: %f \n", filename1, filename2,result);
	}




}
