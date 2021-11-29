import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
public class pls2 {
	static String[] characterList= new String[] {"Adam","Eve","Satan","God","Jesus","Raphael","Sin","Death","Gabriel"};
	static String first="<div class=\"card mb-2\"><div class=\"card-body p-2 p-sm-3\"><div class=\"media forum-item\"><img src=\"";
	static String firstRe="<div class=\"card mb-2 ml-5\"><div class=\"card-body p-2 p-sm-3\"><div class=\"media forum-item\"><img src=\"";
	static String second="\" class=\"mr-3 rounded-circle\" width=\"50\" alt=\"User\" /><div class=\"media-body\"><h6>";
	static String third="</h6><p class=\"text-secondary\">";
	static String fourth="</p><p class=\"text-muted\">Posted <span class=\"text-secondary font-weight-bold\">10,000 years ago</span></p></div></div></div></div>";
	
	public static void main(String[] args) {
		String filePath = "src/text.txt";

		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"))) {

		 String cLine;
		 while ((cLine = br.readLine()) != null) {
			 
		  sb.append(getFirst(cLine)).append(getImageUrl(cLine)).append(second).append(getCharacter(cLine)).append(third).append(getText(cLine)).append(fourth).append("\n");
		 }
		} catch (IOException e) {
		 e.printStackTrace();
		}
		System.out.println(sb.toString());
	}
	public static String getFirst(String line) {
		for(int i=0;i<line.length();i++) {
			if(line.charAt(i)==':') {
				if(line.charAt(i+1)==':') {
					return first;
				}else {
					return firstRe;
				}
			}
		}
		return firstRe;
	}
	public static String getCharacter(String line) {
		for(int i=0;i<characterList.length;i++) {
			if(line.substring(0,3).equals(characterList[i].substring(0,3))){
				return characterList[i];
			}
		}
		return "ERROR";
	}
	public static String getImageUrl(String line) {
		for(int i=0;i<characterList.length;i++) {
			if(line.substring(0,3).equals(characterList[i].substring(0,3))){
				return characterList[i] + ".png";
			}
		}
		return "ERROR";
	}
	public static String getText(String line) {
		String[] split=line.split(" ");
		int length=split[0].length();
		return line.substring(length+1);
	}
}
