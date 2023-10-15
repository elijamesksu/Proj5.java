# Proj5.java
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
