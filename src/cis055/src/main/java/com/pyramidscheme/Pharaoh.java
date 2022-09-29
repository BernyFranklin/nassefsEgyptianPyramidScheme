// Pharaoh.java is a class that creates objects of type Pharaoh
package com.pyramidscheme;

// pharoah class, that corresponds to the information in the json file
public class Pharaoh {

  // using protected member values, so other classes in this package can have access
  protected Integer id;
  protected String name;
  protected Integer begin;
  protected Integer end;
  protected Integer contribution;
  protected String hieroglyphic;

  // constructor
  public Pharaoh(
    Integer pharaohId,
    String pharaohName,
    Integer pharaohBegin,
    Integer pharaohEnd,
    Integer pharaohContribution,
    String pharaohHieroglyphic
  ) {
    id = pharaohId;
    name = pharaohName;
    begin = pharaohBegin;
    end = pharaohEnd;
    contribution = pharaohContribution;
    hieroglyphic = pharaohHieroglyphic;
  }

  // print pharaoh
  public void print() {
    System.out.printf("Pharaoh %s\n", name);
    System.out.printf("\tID:\t\t%d\n", id);
    System.out.printf("\tBegin:\t\t%d B.C.\n", begin);
    System.out.printf("\tEnd:\t\t%d B.C.\n", end);
    System.out.printf("\tContribution:\t%d gold coins\n", contribution);
    System.out.printf("\tHieroglyphic:\t%s\n", hieroglyphic);
  }

}
