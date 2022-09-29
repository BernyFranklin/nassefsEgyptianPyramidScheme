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

  // takes contribution hash code and match it to the appropriate pharaoh
  public String contributionInfo(String contributor, Pharaoh[] pharaohArray) {
    String info = "";
    // iterate through array to search for specific hash code, constructs a string if matched
    for (Pharaoh person: pharaohArray) {
      if (contributor.compareTo(person.hieroglyphic) == 0) {
        info = person.name + " " + person.contribution.toString() + " Gold Coins";
      }
    }
    return info;
  }

  // takes contribution hash code and returns the amount contributed
  public Integer contributionTotal(String contributor, Pharaoh[] pharaohArray) {
    Integer contribution = 0;
    // iterates through array for specified hash code, and pulls contribution amount
    for (Pharaoh person: pharaohArray) {
      if (contributor.compareTo(person.hieroglyphic) == 0) {
        contribution = person.contribution;
      }
    }
    return contribution;
  }
  
  // print pyramid
  public void print(Pharaoh[] pharaohArray) {
    // Initialize with first contribution value
    Integer total = 0;
    System.out.printf("%s Pyramid\n", name);
    System.out.printf("\tID:\t\t%d\n", id);
    // Adds all contributions and displays contributor name
    for (int i = 0; i < contributors.length; i++){
      total += contributionTotal(contributors[i], pharaohArray);
      System.out.printf("\tContributor %d: \t%s\n", i+1, contributionInfo(contributors[i], pharaohArray));
    }
    System.out.printf("\tTotal:\t\t%d Gold Coins\n", total);
  }
}
