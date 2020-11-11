import java.util.*;
import java.util.Scanner;
import java.io.File;

public class Terminal {
	static Scanner in = new Scanner(System.in);
	static Parser parser = new Parser();
	static String currentDir = "C:\\", defaultDir = "C:\\";
	
	public static void cp(String sourcePath, String destinationPath ) {
		
	};
	public static void mv(String sourcePath, String destinationPath) {
		
	};
	public static void rm(String sourcePath) {
		
	};
	// Prints the current directory
	public static void pwd() {
		System.out.println(currentDir);
	};
	public static void cat(String[] paths) {
		
	};
	// Lists all the files/folders in the current or given directory
	public static void ls(String[] arguments) {
		File dir;
		if (arguments != null) {
			dir = new File(arguments[0]);
		} else {
			dir = new File(currentDir);
		}
		// If there are any errors caught, that means the directory the user entered is incorrect
		try {
			File allFiles[] = dir.listFiles();
	        for (int i = 0; i < allFiles.length; i++) {
	           System.out.print(allFiles[i].getName() + "  ");
	        }
		} catch (Exception e) {
			System.out.print("Cannot access '"+ arguments[0] + "' : No such file or directory");
		}
        System.out.println("\n");
	};
	// Changes the current directory
	public static void cd(String[] arguments) {
		String path;
		// If there are no arguments change the directory to the default directory
		if (arguments == null) {
			path = defaultDir;
		} else {
			path = arguments[0];
		}
		File dir = new File(path);
		// Checks if the directory the user entered exists or not
		if (dir.isDirectory()) {
			currentDir = path;
		} else {
			System.out.println("'" +path + "' : No such file or directory");
		}
	}
	
	// Add any other required command in the same structure
	
	public static void main(String[] args) {
		boolean run = true;
		String cmd;
		String [] arg;
		System.out.print("\n");
		
		while(run) {
			if (defaultDir.equals(currentDir)) {
				System.out.print(" ~$ ");
			} else {
				System.out.print(" " + currentDir + "$ ");
			}
			String input = in.nextLine();
			if (input.equals("exit")) {
				run = false;
			} else if (parser.parse(input)) {
				cmd = parser.getCmd();
				arg = parser.getArguments();
				
				switch(cmd) {
					case "cp":
						cp(arg[0], arg[1]);
						break;
					case "mv":
						mv(arg[0], arg[1]);
						break;
					case "rm":
						rm(arg[0]);
						break;
					case "pwd":
						pwd();
						break;
					case "cat":
						cat(arg);
						break;
					case "ls":
						ls(arg);
						break;
					case "cd":
						cd(arg);
						break;
				}

			}
		}
	}
}

