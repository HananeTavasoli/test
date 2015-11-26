import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Document {

	Map<String,Integer> wordList;  //Document1's word frequencies
	String fileName;

	public final Map<String, Integer> getWordList() {
		return wordList;
	}

	public final void setWordList(Map<String, Integer> wordList) {
		this.wordList = wordList;
	}

	public void fillWordList(String str){
		String[] words;
		words=str.split(" |,|\\. |\" |\\? |\\s+|\\! |\\d+");
		for(String word: words){
			word=word.toLowerCase();
			int n=(wordList.get(word)==null) ? 1 : wordList.get(word)+1;
			wordList.put(word,n);

		}

	}

	public Document(String fileName) {
		super();
		this.wordList=new HashMap<>();
		this.fileName = fileName;
		try{
			FileReader inputFile=new FileReader(fileName);
			BufferedReader bufferReader = new BufferedReader(inputFile);
			String line;
			while((line=bufferReader.readLine())!=null){
				fillWordList(line);
			}
			bufferReader.close();

		}catch(Exception e){
			System.out.println("Error while reading file line by line:"+ e.getMessage());

		}

	}
	
	
	public double dist1( Document doc2){

		double dist=innerProduct(this.getWordList(),doc2.getWordList()); 
		return dist;
	}

    /*
     * Distance Metric: The document distance metric is the inner product of the vectors D1 and D2 containing the word
     * frequencies for all words in the 2 documents. Equivalently, this is the projection of vectors D1 onto D2 or vice versa.
     * @param M1 word frequencies of document1 
     * @param M2 word frequencies of document2
     * @return   Inner Product
     * 
     */
	private double innerProduct(Map<String,Integer> M1,Map<String,Integer> M2){
		double sum = 0.0;
		for(Entry<String, Integer> val: M1.entrySet() ){
			if(M2.get(val.getKey())!=null)
				sum=sum+ val.getValue()*M2.get(val.getKey());
		}

		return sum;
	}

	/* Angle Metric: The angle between the current document and document D2 gives an indication of overlap between the 2 documents. 
	 * An angle metric of 0 means the two documents are identical whereas an angle metric of PI/2 implies that there 
	 * are no common words.
	 * @param d2 Document2
	 * @return Angle Metric (radian)
	 */
	public double vector_angle(Document d2){

		double numerator=innerProduct(this.wordList, d2.getWordList());
		double denominator=	Math.sqrt(innerProduct(this.wordList,this.wordList)*innerProduct(d2.getWordList(),d2.getWordList()));

		return Math.acos(numerator/denominator);
	}

}
