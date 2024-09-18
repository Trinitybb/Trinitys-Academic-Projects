import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Collections;
import java.util.Arrays;
public class SetOpt2 {

    /*A - B difference */
    public static Set<String> difference(Set<String> setA, Set<String> setB) {
        Set <String> uni_set = new HashSet<>();
        Set<String> diff;
        if(setA.contains("@") && !setB.contains("@")) { // if set A is @, but set B isn't
            for (char let = 'A'; let <= 'Z'; let++) {
                String low_case = String.valueOf(let);
                String upp_case = String.valueOf(Character.toLowerCase(let));
                uni_set.add(low_case);
                uni_set.add(upp_case);
            }
            diff = new HashSet<>(uni_set); // all letters of the universal set are placed here
            diff.removeAll(setB);          // A - B (subtracting set B from a universal set)
        }
        else if (setB.contains("@") && !setA.contains("@")){ // if set b is @, but set a isn't
            for (char let = 'A'; let <= 'Z'; let++) {
                String low_case = String.valueOf(let);
                String upp_case = String.valueOf(Character.toLowerCase(let));
                uni_set.add(low_case);
                uni_set.add(upp_case);
            }
            diff = new HashSet<>(setA);
            diff.removeAll(uni_set); // A - B (subtracting set A from a universal set)
        }
        else{                           // in the case both sets have multiply elements, both sets are @, both sets are *, etc.
            diff = new HashSet<>(setA);
            diff.removeAll(setB);
        }
        return diff;
    }

    /*B - A difference */
    public static Set<String> difference1(Set<String> setA, Set<String> setB) {
        Set <String> uni_set = new HashSet<>();
        Set<String> diff2;
        if(setB.contains("@") && !setA.contains("@")) {  // if set B is a universal set @
            for (char let = 'A'; let <= 'Z'; let++) {
                String low_case = String.valueOf(let);
                String upp_case = String.valueOf(Character.toLowerCase(let));
                uni_set.add(low_case);
                uni_set.add(upp_case);
            }
            diff2 = new HashSet<>(uni_set); // all letters of the universal set are placed here
            diff2.removeAll(setA); // B - A (subtracting set A from the universal set)  || set A is not @ in this case
        }
        else if (setA.contains("@") && !setB.contains("@")){ // if set A is a universal set @
            for (char let = 'A'; let <= 'Z'; let++) {
                String low_case = String.valueOf(let);
                String upp_case = String.valueOf(Character.toLowerCase(let));
                uni_set.add(low_case);
                uni_set.add(upp_case);
            }
            diff2 = new HashSet<>(setB);
            diff2.removeAll(uni_set); // B - A (subtracting universal set for setB (if set B is anything other than @))
        }
        else{                                      // in the case both sets have multiply elements, both sets are @, both sets are *, etc.
            diff2 = new HashSet<>(setB);
            diff2.removeAll(setA);
        }
        return diff2;

    }
    // Function to determine if sets A and B are disjoint
    public static boolean is_disjoint(Set<String> setA, Set<String> setB) {
        return Collections.disjoint(setA, setB); // imported collections class for disjoint operation
    }

    // Function to display the menu and handle user inputs
    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        Set<String> setA = new HashSet<>();
        Set<String> setB = new HashSet<>();

        while (true) {
            System.out.println("================");
            System.out.println(" 1. difference");
            System.out.println(" 2. is_disjoint");
            System.out.println(" 3. Quit");
            System.out.println("================");
            System.out.print("Enter a menu (1, 2, 3): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("-->The entered menu option is not valid! Try again!");
                continue;
            }

            switch (choice) {
                // calling the difference() function to get result of A-B or B-A
                case 1:
                    System.out.println("'@' is an universal set & '*' is an empty set ");
                    System.out.println("Enter the elements for set A (separated by commas & no spaces): ");
                    String inputA = scanner.nextLine();

                    setA = new HashSet<>(Arrays.asList(inputA.split(",")));
                    System.out.println("Enter the elements for set B (separated by commas & no spaces): ");
                    String inputB = scanner.nextLine();

                    setB = new HashSet<>(Arrays.asList(inputB.split(",")));
                    Set<String> diff1 = difference(setA, setB); //A-B
                    Set<String> diff2 = difference1(setA, setB); //B-A


                    System.out.println("A - B = " + diff1 +
                                        "\nB - A = " + diff2);


                    break;
                case 2:
                    // calling is_disjoint()
                    boolean disjoint = is_disjoint(setA, setB);
                    if (disjoint) {
                        System.out.println(" Yes, the sets A and B are disjoint.");
                    } else {
                        System.out.println("No, the sets A and B are not disjoint.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("-->The entered menu option is not valid! Try again!");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        menu();
    }
}