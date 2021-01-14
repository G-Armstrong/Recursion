import java.io.File;
import java.util.Scanner;
public class Recursion {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter a pathname: ");
		String path = keyboard.nextLine();
		int count = fileCount(path);
		long bytes = getSize(path);
		System.out.println("Number of files in " + path + "is " + count + " files.");
		if(bytes<1000000) {
			System.out.println("Total size of  " + path + "is " + (bytes/1000) + " Kbytes.");
		}
		else if(bytes>1000000 && bytes<1000000000) {
			System.out.println("Total size of  " + path + "is " + (bytes/1000000) + " Mbytes.");
		}
		else if(bytes>=1000000000) {
			System.out.println("Total size of  " + path + "is " + (bytes/1000000000) + " Gbytes.");

		}
		
		keyboard.close();
	}
	
	/**
	 * Recursive method to determine the number of files in the provided path
	 * @param pathname of type String: the provided path
	 * @return returns the file count
	 */
	public static int fileCount(String pathname) {
	//	System.out.println("Processing " + pathname);
		int count = 0;
		File fileObj = new File(pathname);
		if(fileObj.isFile()) {
			count = 1;
		}
		else if(fileObj.isDirectory()) {
			File[] fileList = fileObj.listFiles();
			for (int i=0;i<fileList.length;i++) {
				count+= fileCount(fileList[i].getPath());
			}
		}
		return count;
	}
	/**
	 * Recursive method to determine the size in bytes of the pathname provided
	 * @param pathname of type String: the provided path
	 * @return returns the number of bytes
	 */
	public static long getSize(String pathname) {
		File fileObj = new File(pathname);
		long bytes = 0;
		if(fileObj.isFile()) {
			bytes+=fileObj.length();
		}
		else if(fileObj.isDirectory()) {
			File[] fileList = fileObj.listFiles();
			for (int i=0;i<fileList.length;i++) {
				bytes+=getSize(fileList[i].getPath());
			}
		}
		return bytes;
	}

}
