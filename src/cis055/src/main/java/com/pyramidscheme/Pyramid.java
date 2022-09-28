// Pyramid.java is a class that creates objects of type Pyramid
package com.pyramidscheme;

// pyramid class, that corresponds to the information in the json file
public class Pyramid {

  protected Integer id;
  protected String name;
  protected String[] contributors;

  // constructor
  public Pyramid(
    Integer pyramidId,
    String pyramidName,
    String[] pyramidContributors
  ) {
    id = pyramidId;
    name = pyramidName;
    contributors = pyramidContributors;
  }

  public String contributionInfo(String contributor, Pharaoh[] pharaohArray) {
    String info = "";
    for (Pharaoh person: pharaohArray) {
      if (contributor.compareTo(person.hieroglyphic) == 0) {
        info = person.name;
      }
    }
    return info;
  }
  // print pyramid
  // Need to update to print off 
  // Pyramid Namw
  // ID
  // Contributor by name with amount of gold
  // Total contributions
  public void print(Pharaoh[] pharaohArray) {
    String info = "Dude";
    System.out.printf("%s Pyramid\n", name);
    System.out.printf("\tid:\t\t%d\n", id);
    System.out.printf("\tcontributors: ");
    for (int i = 0; i < contributors.length; i++){
      info = contributionInfo(contributors[i], pharaohArray);
      System.out.println(info);
    }
  }
}
