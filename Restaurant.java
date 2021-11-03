public class Restaurant{
  /*

private Point (for calculating distance??) or Google Map coordinates
int x
int y
^^^ figure out how to do this


  */

  private String name; //of restaurant
  private String [] menu; //menu items
  private String hours; //business hours
  private String cuisineType; //type of cuisine
  private boolean gWorld; //does it accept gWorld?
  //private Point;

  //constructor
  Restaurant (String name, String hours, String cuisineType, boolean gWorld){
    this.name = name;
    this.hours = hours;
    this.cuisineType = cuisineType;
    this.gWorld = gWorld;
  }

  public void setMenu (String [] setMenu){

    //set menu so it's ready to be displayed
    menu = new String [setMenu.length];
    System.arraycopy(setMenu, 0, menu, 0, setMenu.length);
  }


}