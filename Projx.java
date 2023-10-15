import java.io.BufferedReader; //BufferedReader is used to include the BufferedReader class which is part of the java.io package.Allows you to read text from a character-input stream(such as a file) efficiently if you don't use BufferedReader you will probably have to use lower level input operations such as reading one character at a time or fixed side blocks of data.
import java.io.FileReader; // FileReader is used for reading character files in java. FileReader is part of the java.io library and is useful for reading text files(designed for reading data from files). You can use it to open & read the contents of a file character by character in large chunks. It is character oriented meaning that it is not byte oriented it stores characters as characters & not as raw bytes like (FileInputStream). Very important for proper character encoding and decoding
import java.io.IOException;//Exception class in java and part of the standard java i/o library. It is a checked exception meaning when you use classes & methods related to input and output operations you are required to handle eor declare the exception. You also have exception handling which would be the catch and handle IOException to manage errors gracefully. Handling the exception can involve logging the error, displaying the error message to the user or taking corrective catches to recover from the error, File handling: while working with files IOException is often thrown if there are issues like the file not being found, not having proper permissions or if there are issues during the file read/write operations // IOException e is a way to catch & handle the IOException when a IOException occurs the code inside the catch executes (e is just the variable name)// e.printStackTree() is a method call on the e object the printStackTrace(); method is used to print the details of the exception to the console. Helps because it helps us identify where the exception occurred in the code & can be crucial for debugging.... ?What happens if the program encounters an error while executing code inside the 'try' block? This error is an IOException//after try the program jumps to the catch block where the 'e' variable holds the exception object// e.PrintStackTrace() is called which prints a stack trace to the console showing the sequence of method calls that led to the exception
import java.util.Scanner;// Scanner is used to import the 'Scanner' class from the java.util package used for reading input from various resources such as they keyboard(System.in) or files. It provides methods for parsing and tokenizing input
import java.text.DecimalFormat;// DecimalFormat allows you to format decimal numbers as Strings according to a specified pattern. Part of the java.txt package and is commonly used for formatting numbers in a human-readable way such as applying a specific number of decimal places

/**
 * EXTRA CREDIT 1 INCLUDED
 * Eli james
 * Project 5 CIS200
 * lab b thurs @ 1:05
 * What does the program do?
 The goal : Create a Java program that reads student data from a password-protected comma-delimited text file and performs the following tasks:

 1. Prompt the user to enter the filename containing student data (not case-sensitive). If the file does not exist, keep prompting until a valid filename is entered.

 2. Read the encrypted password from the first line of the file. Decrypt the password by shifting each character by one ASCII value down.

 3. Validate the password entered by the user. If the user enters an incorrect password, they should be allowed up to three attempts. After three incorrect attempts, display an error message and exit the program.

 4. Read student information from the text file into arrays for all students. Each student's information includes their first name, last name, WID number, and scores on three course exams and one final exam.

 5. Display each student's information in the following format:
 - Last Name, First Name (all uppercase)
 - WID number
 - Exam percentage (calculated as the total of all exam scores plus the final exam score divided by the total possible score, using constants)
 - Final Grade based on exam percentage (A, B, C, D, or F)

 6. Only display information for students whose exam scores fall within the specified ranges and meet certain conditions.

 7. After displaying each student's information, show the total number of valid students, the number of A's, B's, C's, D's, and F's, along with the percentage of each, and the average of all scores.

 8. The program should handle variations in the maximum score for course exams and the number of course exams (as constants) based on the input file.

 EXTRA CREDIT:
 First, display the parameters of a valid password – minimum of 8 characters that includes at least
 one digit and at least one of the following special characters: ! @ # $ *
 -Ask the user for a password then make sure it is valid, following the parameters listed above.
 -If the password is invalid, display one of the following error messages and loop until password is valid
 Invalid password – must be at least 8 characters
 Invalid password – must contain at least one digit
 Invalid password – must contain at least one of the following characters: ! @ # $ *
 (If more than one of these conditions are true, say less than 8 characters and no digit, you only need to
 display one of the appropriate error messages)
 -Once password is valid, confirm the password by having the user retype the password and compare it to
 the originally entered password. If invalid, display an error message and loop until they type in the
 matching password. (For this project, you do not need to allow them to re-enter a new password.)
 For this portion of the extra credit, once password is validated, display “Password is valid and confirmed.”
 */
public class Proj5 {
    public static void main(String[] args) { // method where code begins
        //changed constants for professor below
        final int CLASS_SIZE = 50; // constant variable using the final keyword to set the class size, cannot be changed within program
        final int MAX_EXAMS = 3; // constant variable using the final keyword to set the maximum amount of exams, cannot be changed within program
        final int MAXIMUM_EXAM_SCORE_COURSE = 50; // constant variable using final keyword to set the maximum score a student can get on a regular course exam, cannot be changed within program
        final int MAXIMUM_EXAM_SCORE_FINAL = 100; // constant variable using final keyword to set the maximum score a student can get on their final exam, cannot be changed within the program

        String[] firstNames = new String[CLASS_SIZE]; // Array of strings that store the first names of the students. The size(elements) of this array is determined by the CLASS_SIZE constant
        String[] lastNames = new String[CLASS_SIZE]; // Array of strings that store the last names of the students. The size(elements) of this array is determined by the CLASS_SIZE constant
        String[] kStateWid = new String[CLASS_SIZE]; // Array of strings that store student id numbers(wid). The size(elements) of this array is determined by the CLASS_SIZE constant
        BufferedReader fileReader = null; // BufferedReader variable named fileReader that it is initialized to null for later assignment
        String storedPassword = null; // string given the name storedPassword set to the value null for later assignment
        String filePath ; // creating string varibale filePath
        int[][] examScores = new int[CLASS_SIZE][MAX_EXAMS]; // 2D array of integers that store the scores of the students exams. The size(elements) of the first array is determined by the CLASS_SIZE constant and the size(index) of the second array is determined by the MAX_EXAMS constant
        int[] finalExamScores = new int[CLASS_SIZE]; // Array of integers that store the final exam scores for the students. The size of this array is determined by the CLASS_SIZE constant
        double examPercentage; // taking the added items / divisor and multiplying by 100 and converting to double
        double overallAverage; //calculating the overall average of every student who had a valid grade
        double[] examPercentageArray = new double[CLASS_SIZE]; // Array of doubles that store the total calculated grade that the students got on their exams. The size of this array is determined by the CLASS_SIZE constant
        double gradePercentageCalculatorA; // double that stores the percentage of students who scored an A on their exam
        double gradePercentageCalculatorB; // double that stores the percentage of students who scored an B on their exam
        double gradePercentageCalculatorC; // double that stores the percentage of students who scored an C on their exam
        double gradePercentageCalculatorD; // double that stores the percentage of students who scored an D on their exam
        double gradePercentageCalculatorF; // double that stores the percentage of students who scored an F on their exam
        double preCalculatedAddedExams; // have to be double because if int will just be 0.
        boolean finalExamOverload; // boolean that will be thrown into conditionals below for invalid messages to be printed on the console
        boolean courseExamOverload; // boolean that will be thrown into conditionals below for invalid messages to be printed on the console
        int passwordAttempts = 0; //creating type int with name: passwordAttempts to increment the attempts in the loop below
        int studentCounter = 0; // integer that counts the students who data has been processed for. Started at 0 and will be incremented every time that data is processed for a student
        int validCounter = 0; // integer that counts the number of students who had their valid exam scores properly displayed. Started at 0 and will be incremented when the percentages get outputted
        int aCounter = 0; // integer that counts the number of students who score an A on their exam
        int bCounter = 0; // integer that counts the number of students who score an B on their exam
        int cCounter = 0; // integer that counts the number of students who score an C on their exam
        int dCounter = 0; // integer that counts the number of students who score an D on their exam
        int fCounter = 0; // integer that counts the number of students who score an F on their exam
        int divisor; // int divisor calculations which will be called in preCalculatedAddedExams
        String line; // place-holder variable to call into conditional

        Scanner s = new Scanner(System.in); // Creating a scanner named s to read input from the keyboard
        System.out.print("Filename of the file containing student data: ");
        while (true) {
            filePath = s.nextLine();
            if (filePath.equals("Proj5_Test1.txt") || filePath.equals("Proj5_Test2.txt")) {
                break; // Valid file path provided, exit the loop
            } else {
                System.out.print("Invalid filename. Please re-enter: ");
            }
        }
            // surrounding this block of code where exceptions might occur and used for exception handling therefore you can catch and handle any exceptions that might be thrown during the execution of this block when there is an exception the program immediately jumps to the catch block
            try {
                fileReader = new BufferedReader(new FileReader(filePath)); // BufferedReader fileReader is now initialized (null @ line 36)  and is now being assigned to read text from a character input stream new FileReader(filePath) which is creating a FileReader object that reads from filePath and then filePath is wrapped in BufferedReader which provides more efficient reading of the file. FileReader is a class used for reading character files which has character data(text). While FileInputStream reads and processes data as raw bytes(binary data)
                storedPassword = fileReader.readLine(); // String is now initialized and now has BufferedReader fileReader reading the encrypted password efficiently from text file line 1 and storing it back into storedPassword
                // Decrypting the password stored in the text file
                StringBuilder decryptedPassword = new StringBuilder();// creating a StringBuilder named decryptedPassword is a class in java that is used for building and manipulating strings efficiently so in this case we can modify and manipulate decryptedPassword. StringBuilder is mutable therefore it allows us to efficiently append, insert, or modify characters in a string without creating a new string object every time. When to use StringBuilder? When you need to build a string dynamically especially inside of loops or modifying strings. Building a string dynamically means you are gradually constructing the string by adding characters or other string values to a normally empty string. You can append instead of concatenating especially when you have a lot of data it would take up a lot of memory.

                for (char v : storedPassword.toCharArray()) { // for each loop(meaning for each character and char is named v), string storedPassword is getting converted to individual characters with the toCharArray() method therefore you can access the characters individually with v being able to manipulate the values
                    int characterConversion = (int) v; // it is a for loop so every v which now equals characterConversion will loop as long as it is in between 32 inclusive and 122 inclusive(which are typical printable characters) this line converts the current char into its ascii(integer) value and stores it in the characterConversion variable so every character in the decryptedPassword string has gotten an ascii value now

                    if (characterConversion >= 32 && characterConversion <= 122) { // condition to see if characterConversion is in between the printable characters
                        decryptedPassword.append((char) (characterConversion - 1)); // if characterConversion is a valid printable character this line subtracts 1 from the current value of the characterConversion(remember characterConversion is has type int) now the new character runs into decrypted.Password.append(char) and gets converted back into a char and appends it into the decryptedPassword StringBuilder and now the char has an ascii value one less than what it came into the condition with (man that is some manipulation)
                    } else { //condition if characterConversion is not a printable character
                        System.out.println("Invalid characters in the stored password."); // outputting invalid message to the console
                        return; // Exit the program if there's an error
                    }
                }

                System.out.print("Enter in File Password: "); // prompting user to enter the file password
                String theFilePassword = s.nextLine(); // taking users input from the line above and storing it in theFilePassword

                while (!theFilePassword.equals(decryptedPassword.toString())) {//while loop stating if theFilePassword does not equal decryptedPassword will repeat only 3 times before exit

                    System.out.print("Invalid Password. Please re-enter: "); //prompts user on console to input filePassword again
                    theFilePassword = s.nextLine(); // taking input from above and storing it in theFilePassword
                    passwordAttempts++; //incrementing passwordAttempts by 1 every loop

                    if (theFilePassword.equals(decryptedPassword.toString())) { //if you are in the loop and filePassword at any point is correct continue and exit the while loop
                        continue;//return back to the while loop and will continue in the program because the while loop would be false
                    }

                    if (passwordAttempts > 1) { //if passwordAttempts was put in 3 separate times incorrectly display error message and program ends with return
                        System.out.println("Invalid Password entered 3 times - Exiting"); //text invalid display to console
                        return;// end program
                    }
                }

                System.out.println(); //printing an empty line to the console
                System.out.println("***Class Results***");// printing banner to the console

                // while reading the file and file is not empty(there is something to be read) and studentCounter is less than the constant CLASS_SIZE(should be because it is only incremented when there is valid student entry also it is initialized to 0
                while ((line = fileReader.readLine()) != null && studentCounter < CLASS_SIZE) {
                    String[] textArray = line.split(","); // array of strings per line that splits the text by "," and gives line element values. length is undefined
                    if (textArray.length >= 3) { // if the length of the array is greater than or equal to 3 set variables to the specific indexes within the array
                        firstNames[studentCounter] = textArray[0]; // first names are being retrieved from the first index of the array textArray
                        lastNames[studentCounter] = textArray[1]; // last names are being retrieved from the second index of the array textArray
                        kStateWid[studentCounter] = textArray[2]; // student id numbers are being retrieved from the third index of the array textArray

                        for (int iterator = 0; iterator < MAX_EXAMS; iterator++) { // knowing MAX_EXAMS = 3 created a for loop that looped and incremented iterator per iteration starting at 0 inclusive to 3 exclusive
                            examScores[studentCounter][iterator] = Integer.parseInt(textArray[3 + iterator]); // examScores for [student/row] for spots [3]-[5] those are the exams which makes the [3] + iterator, so you are taking the input from [3]...[4]..and...[5] and storing it into the exams, and it can only be 3 because MAX_EXAMS is set to 3  int iterator = 0 iterator++ means from 0 1 2 you are taking the input from parts [3]-[5] and converting it into an int because first iteration is 3 + 0 so you [3] is one then next iteration iterate = 1 so 3 + 1 = 4 [4] and the same thing for 3

                        }
                        finalExamScores[studentCounter] = Integer.parseInt(textArray[textArray.length - 1]); //extracting the final exam score from the array which is the last element subtracting 1 because there is always one more element than index therefore we have to store the index value which explains the minus 1
                        studentCounter++; //increment studentCounter every iteration until there is nothing else to read hint the != null when file goes null escape loop
                    }
                }
            }

            // catch block for handling any IOException that might occur when reading the file
            catch (IOException examine) {
                System.out.println("Error opening the file."); //display message to console
                return; // exit the program if there's an error
            }
            // finally block for cleaning up operations
            finally {
                //Try
                try {
                    // if fileReader is not null
                    if (fileReader != null) {
                        fileReader.close(); // close fileReader
                    }
                }
                // if an IOException occurs
                catch (IOException examine) {
                    examine.printStackTrace(); // print the stack trace to help with debugging
                }
            }

            // for loop that takes user row and passes it to the inner loop as long as it is less than studentCounter. Initiates row and col student counter enters this loop already equal to 1 because data is already stored from a previous conditional so our row = 0 is studentCounter = 1 hypothetically lets say studentCount is 1 - 7 inclusive this for loop will break out of itself after the 7th iteration (row = 6 for us because row starts at 0 when studentCount = 1) because row is no longer < studentCount (iterations start at 0 therefore it will run 7 times our last iteration will be row = 6 6< 7) therefore it is only going to run and store the sum of the examScores for those iterated students
            for (int row = 0; row < studentCounter; row++) {
                int sum = 0; // setting int sum to 0 and resets the sum value after the inner loop is complete and stored
                // for loop that takes user row from outer loop and stores that in i and p starts at 0 and increments up to examScores[i].length which in this case is 3 but 0-2 since we are using indexes
                for (int col = 0; col < examScores[row].length; col++) {
                    sum += examScores[row][col]; // Accumulate exam scores for the current student (inserted with 'i') by adding each exam score (inserted and looped by 'p' until you reached exam examScores[i].length) and resetting the sum once p has reached examScores[i].length;
                }

                // calculations for output lining
                divisor = (MAXIMUM_EXAM_SCORE_COURSE * MAX_EXAMS) + MAXIMUM_EXAM_SCORE_FINAL; // int divisor calculations which will be called in preCalculatedAddedExams
                preCalculatedAddedExams = (double) (sum + finalExamScores[row]) / divisor; // have to be double because if int will just be 0.
                examPercentage = (double) (preCalculatedAddedExams * 100.0); // taking the added items / divisor and multiplying by 100 and converting to double
                courseExamOverload = false; // boolean that will be thrown into conditionals below for invalid messages to be printed on the console
                finalExamOverload = false; // boolean that will be thrown into conditionals bellow for invalid messages to be printed on the console

                //for loop to show invalid user message to console for student and student gets
                for (int col = 0; col < examScores[row].length; col++) {
                    if (examScores[row][col] > MAXIMUM_EXAM_SCORE_COURSE) {
                        courseExamOverload = true;
                        System.out.println("***Course Exam score invalid for " + firstNames[row].toUpperCase() + " " + lastNames[row].toUpperCase());
                        System.out.println();
                        break;
                    }
                }

                if (finalExamScores[row] > MAXIMUM_EXAM_SCORE_FINAL) {
                    finalExamOverload = true;
                    System.out.println("***Final Exam score invalid for " + firstNames[row].toUpperCase() + " " + lastNames[row].toUpperCase());
                    System.out.println();
                }

                if (courseExamOverload || finalExamOverload) {
                    continue;
                }

                System.out.println(lastNames[row].toUpperCase() + "," + firstNames[row].toUpperCase());
                System.out.println(kStateWid[row]);
                DecimalFormat df = new DecimalFormat("0.0"); // creating new DecimalFormat named df
                System.out.println("Exam Percentage: " + df.format(examPercentage));
                char grade = 'x';

                if (examPercentage >= 90 && examPercentage <= 100) {
                    grade = 'A';
                    examPercentageArray[row] = examPercentage;
                    validCounter++;
                    aCounter++;
                }

                if (examPercentage >= 80 && examPercentage < 90) {
                    grade = 'B';
                    examPercentageArray[row] = examPercentage;
                    validCounter++;
                    bCounter++;

                }

                if (examPercentage >= 70 && examPercentage < 80) {
                    grade = 'C';
                    examPercentageArray[row] = examPercentage;
                    validCounter++;
                    cCounter++;
                }

                if (examPercentage >= 60 && examPercentage < 70) {
                    grade = 'D';
                    examPercentageArray[row] = examPercentage;
                    validCounter++;
                    dCounter++;

                }

                if (examPercentage < 60) {
                    grade = 'F';
                    examPercentageArray[row] = examPercentage;
                    validCounter++;
                    fCounter++;
                }

                System.out.println("Final Grade: " + grade);
                System.out.println("Press enter to display next student...");
                s.nextLine();
            }

            System.out.println("***Class Summary***");
            System.out.println("Total number of students with valid scores: " + validCounter);
            System.out.println("\t\t\t Total number of A's: " + aCounter);
            System.out.println("\t\t\t Total number of B's: " + bCounter);
            System.out.println("\t\t\t Total number of C's: " + cCounter);
            System.out.println("\t\t\t Total number of D's: " + dCounter);
            System.out.println("\t\t\t Total number of F's: " + fCounter);
            System.out.println();
            double preCalculations = 100.0 / validCounter;
            gradePercentageCalculatorA = preCalculations * aCounter;
            gradePercentageCalculatorB = preCalculations * bCounter;
            gradePercentageCalculatorC = preCalculations * cCounter;
            gradePercentageCalculatorD = preCalculations * dCounter;
            gradePercentageCalculatorF = preCalculations * fCounter;
            DecimalFormat nw = new DecimalFormat("#.#");
            overallAverage = 0.0;

            for (int j = 0; j < studentCounter; j++) {
                if (examPercentageArray[j] >= 0) {
                    overallAverage += examPercentageArray[j];
                }
            }
            if (validCounter > 0) {
                overallAverage /= validCounter;
            }
            System.out.println("Individual grade percentages...");
            System.out.printf("\t\t\t A: %.1f%%%n", gradePercentageCalculatorA);
            System.out.printf("\t\t\t B: %.1f%%%n", gradePercentageCalculatorB);
            System.out.printf("\t\t\t C: %.1f%%%n", gradePercentageCalculatorC);
            System.out.printf("\t\t\t D: %.1f%%%n", gradePercentageCalculatorD);
            System.out.printf("\t\t\t F: %.1f%%%n", gradePercentageCalculatorF);
            System.out.println();
            System.out.println("Overall Average Score = " + nw.format(overallAverage) + "%");
            System.out.println();
            System.out.print("Push enter to read or create a file or 'Q' (not case sensitive) to quit program: ");
            while (true) {
                String entry = s.nextLine();
                if (entry.equalsIgnoreCase("Q")) {
                    System.out.println("Thank you! Have a good day!");
                    return;
                }
                if (entry.isEmpty()) {
                    System.out.println("Valid passwords include a minimum of 8 characters " + "\n" +
                            "and include at least one digit and at least one of the" + "\n" +
                            "following special characters: ! @ # $ *");
                    System.out.println();
                    System.out.print("Please enter a password: ");
                    break;
                }
                else {
                    System.out.print("Invalid entry (Push enter to read or create a file or 'Q' (not case sensitive) to quit program): ");
                    // entry = s.nextLine();
                    continue;
                }
            }


            String enteredPassword;

            while (true) {

                enteredPassword = s.nextLine();

                if (enteredPassword.length() < 8) {
                    System.out.println("Invalid password - must be at least 8 characters");
                    System.out.print("Please enter a valid password: ");
                    continue;
                }

                boolean containsDigit = false;
                boolean containsSpecialCharacter = false;

                for (char c : enteredPassword.toCharArray()) {
                    if (Character.isDigit(c)) {
                        containsDigit = true;
                    } else if ("!@#$*".contains(String.valueOf(c))) {
                        containsSpecialCharacter = true;
                    }
                }
                if (storedPassword.length() < 8) {
                    System.out.println("Invalid password – must be at least 8 characters");
                    System.out.print("Please enter a valid password: ");
                    continue;
                } //Invalid password – must contain at least one digit
                //Invalid password – must be at least 8 characters
                //Invalid password – must contain at least one of the following characters: ! @ # $ *
                if (!containsDigit) {
                    System.out.println("Invalid password – must contain at least one digit");
                    System.out.print("Please enter a valid password: ");
                    continue;
                }
                if (!containsSpecialCharacter) {
                    System.out.println("Invalid password – must contain at least one of the following characters: ! @ # $ *");
                    System.out.print("Please enter a valid password: ");
                    continue;
                }


                System.out.println("To validate, please re-enter your password: ");
                String confirmationPassword = s.nextLine();
                while (true) {
                    if (!enteredPassword.equals(confirmationPassword)) {
                        System.out.println("\t" + "**Passwords must match**");
                        System.out.print("To validate, please re-enter your password: ");
                        confirmationPassword = s.nextLine();
                        continue;
                    }
                    break;
                }

                System.out.println("Password is valid and confirmed");
                return;
            }
        }
    }