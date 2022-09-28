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

  // print pyramid
  public void print() {
    System.out.printf("%s Pyramid\n", name);
    System.out.printf("\tid:\t\t%d\n", id);
    System.out.printf("\tcontributors:\t%s\n", contributors[0]);
    for (int i = 1; i < contributors.length; i++){
      System.out.printf("\t\t\t%s\n", contributors[i]);
    }
  }
}
