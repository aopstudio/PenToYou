package tool;

public class StringFunc {
	public static int count(String str, String tag) {
		int index = 0;
		int count = 0;	   
		 while ((index = str.indexOf(tag)) != -1 ) {
			 str = str.substring(index + tag.length()); 
			 count++;
		}
		return count;
	}
}
