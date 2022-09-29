/*
 * nassefsPyramidApp
 * Frank Bernal
 * CIS-055
 * Date Started:  28 Sep 2022
 * Date Finished: 29 Sep 2022
 */

 // NassefsPyramidApp.java has the main method for the entire app included with functions for the menu
package com.pyramidscheme;

import java.util.*;
import org.json.simple.*;
import java.util.InputMismatchException;

public class NassefsPyramidApp {


  // I've used two arrays here for O(1) reading of the pharaohs and pyramids.
  // other structures or additional structures can be used
  protected Pharaoh[] pharaohArray;
  protected Pyramid[] pyramidArray;
  protected HashSet<Pyramid> requestedPyramids = new HashSet<Pyramid>();

  // Main method
  public static void main(String[] args) {
    // create and start the app
    NassefsPyramidApp app = new NassefsPyramidApp();
    app.start();
  }

  // main loop for app
  public void start() {
    Scanner scan = new Scanner(System.in);
    Character command = '_';

    // loop until user quits
    while (command != 'q') {
      printMenu();
      System.out.print("Enter a command: ");
      command = menuGetCommand(scan);

      executeCommand(scan, command);
    }
  }

  // constructor to initialize the app and read commands
  public NassefsPyramidApp() {
    // read egyptian pharaohs
    String pharaohFile =
      "src/main/java/com/pyramidscheme/pharaoh.json";
    JSONArray pharaohJSONArray = JSONFile.readArray(pharaohFile);

    // create and intialize the pharaoh array
    initializePharaoh(pharaohJSONArray);

    // read pyramids
    String pyramidFile =
      "src/main/java/com/pyramidscheme/pyramid.json";
    JSONArray pyramidJSONArray = JSONFile.readArray(pyramidFile);

    // create and initialize the pyramid array
    initializePyramid(pyramidJSONArray);

  }

  // initialize the pharaoh array
  private void initializePharaoh(JSONArray pharaohJSONArray) {
    // create array and hash map
    pharaohArray = new Pharaoh[pharaohJSONArray.size()];

    // initalize the array
    for (int i = 0; i < pharaohJSONArray.size(); i++) {
      // get the object
      JSONObject o = (JSONObject) pharaohJSONArray.get(i);

      // parse the json object
      Integer id = toInteger(o, "id");
      String name = o.get("name").toString();
      Integer begin = toInteger(o, "begin");
      Integer end = toInteger(o, "end");
      Integer contribution = toInteger(o, "contribution");
      String hieroglyphic = o.get("hieroglyphic").toString();

      // add a new pharoah to array
      Pharaoh p = new Pharaoh(id, name, begin, end, contribution, hieroglyphic);
      pharaohArray[i] = p;
    }
  }

    // initialize the pyramid array
    private void initializePyramid(JSONArray pyramidJSONArray) {
      // create array and hash map
      pyramidArray = new Pyramid[pyramidJSONArray.size()];
  
      // initalize the array
      for (int i = 0; i < pyramidJSONArray.size(); i++) {
        // get the object
        JSONObject o = (JSONObject) pyramidJSONArray.get(i);
  
        // parse the json object
        Integer id = toInteger(o, "id");
        String name = o.get("name").toString();
        JSONArray contributorsJSONArray = (JSONArray) o.get("contributors");
        String[] contributors = new String[contributorsJSONArray.size()];
        for (int j = 0; j < contributorsJSONArray.size(); j++) {
          String c = contributorsJSONArray.get(j).toString();
          contributors[j] = c;
        }
  
        // add a new pyramid to array
        Pyramid p = new Pyramid(id, name, contributors);
        pyramidArray[i] = p;
      }
    }

  // get a integer from a json object, and parse it
  private Integer toInteger(JSONObject o, String key) {
    String s = o.get(key).toString();
    Integer result = Integer.parseInt(s);
    return result;
  }

  // get first character from input
  private static Character menuGetCommand(Scanner scan) {
    Character command = '_';

    String rawInput = scan.nextLine();

    if (rawInput.length() > 0) {
      rawInput = rawInput.toLowerCase();
      command = rawInput.charAt(0);
    }

    return command;
  }

  // get valid integer id
  private Integer getValidID(Scanner scan) {
    Integer id = 0;
    boolean isNumeric = false;
    while (!isNumeric) {
      try {
        System.out.printf("Please enter valid ID: ");
        id = scan.nextInt();
        isNumeric = true;
      } catch (InputMismatchException e) {
        // Set Flag
        isNumeric = false;
        // Clear Buffer
        scan.nextLine();
        // Alert user
        System.out.println("Input may only be numeric, please try again");
      }
    }
    return id;
  }

  // print all pharaohs
  private void printAllPharaoh() {
    // iterate and print each pharaoh
    for (int i = 0; i < pharaohArray.length; i++) {
      printMenuLine();
      pharaohArray[i].print();
    }
  }

  // print one pharaoh
  private void printOnePharaoh(Scanner scan) {
    Integer id = 0;
    // Get valid input
    id = getValidID(scan);
    // prime the loop
    boolean found = false;
    //iterate through each pharaoh and print the match
    for (Pharaoh person: pharaohArray) {
      if (id.compareTo(person.id) == 0) {
        // set flag to break loop
        found = true;
        printMenuLine();
        person.print();
      }
    }
    if (!found) {
      // alert user if not found
      System.out.printf("There are no Pharaohs with the ID of %d\n", id);
    }
    // Clear buffer
    scan.nextLine();
  }

  // print all pyramids
  private void printAllPyramids() {
    // Iterate through pyramid array and orint each one
    for (int i = 0; i <pyramidArray.length; i++) {
      printMenuLine();
      pyramidArray[i].print(pharaohArray);
    }
  }

  // print one pyramid
  private void printOnePyramid(Scanner scan) {
    Integer id = 0;
    // get valid input
    id = getValidID(scan);
    // prime loop
    boolean found = false;
    // iterate through pyramids and print the match
    for (Pyramid elem: pyramidArray) {
      if (id.compareTo(elem.id) == 0) {
        found = true;
        printMenuLine();
        elem.print(pharaohArray);
        // Add pyramid to set
        requestedPyramids.add(elem);
      }
    }
    if (!found) {
      // alert user if not found
      System.out.printf("There are no Pyramids with the ID of %d\n", id);
    }
    // Clear buffer
    scan.nextLine();
  }

  // print all requested pyramids with no repeats
  private void printRequestedPyramids() {
    if (requestedPyramids.isEmpty()) {
      System.out.printf("\nNo pyramids have been requested yet\n");
    }
    else {
      printMenuLine();
      System.out.println("List of previously requested pyramids");
      printMenuLine();
      System.out.printf("ID\t\tName");
      System.out.printf("\n----\t\t-------\n");
      // iterate through set and print data
      for(Pyramid pyramid: requestedPyramids) {
        System.out.printf("%d\t\t%s Pyramid\n", pyramid.id, pyramid.name);
      }
    }
  }

  // take input and execute the appropriate command
  private Boolean executeCommand(Scanner scan, Character command) {
    Boolean success = true;

    switch (command) {
      case '1':
        printAllPharaoh();
        break;
      case '2':
        printOnePharaoh(scan);
        break;
      case '3':
        printAllPyramids();
        break;
      case '4':
        printOnePyramid(scan);
        break;
      case '5':
        printRequestedPyramids();
        break;
      case 'q':
        System.out.println("Thank you for using Nassef's Egyptian Pyramid App!");
        break;
      default:
        System.out.println("ERROR: Unknown commmand");
        success = false;
    }

    return success;
  }

  // Formats menu commands
  private static void printMenuCommand(Character command, String desc) {
    System.out.printf("%s\t\t%s\n", command, desc);
  }

  // prints a big ol' line
  private static void printMenuLine() {
    System.out.println(
      "---------------------------------------------------------------------------------------");
  }

  // prints the menu
  public static void printMenu() {
    printMenuLine();
    System.out.println("Nassef's Egyptian Pyramids App");
    printMenuLine();
    System.out.printf("Command\t\tDescription\n");
    System.out.printf("-------\t\t-----------------------------------------------------------------------\n");
    printMenuCommand('1', "List All Pharoahs");
    printMenuCommand('2', "Display a Specific Pharaoh");
    printMenuCommand('3', "List All Pyramids");
    printMenuCommand('4', "Display a Specific Pyramid");
    printMenuCommand('5', "Display List of Requested Pyramids");
    printMenuCommand('q', "Quit");
    printMenuLine();
  }
}
