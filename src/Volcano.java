/**********************************************************************
 * @file Volcano.java
 * @brief This program implements the Volcano class. This class is used
 * to create each Volcano object. Each volcano in the file has specific
 * attributes.
 * @author Wynne Greene
 * @date: September 25, 2024
 ***********************************************************************/
public class Volcano implements Comparable<Volcano> {
    private String region; //Region of volcano
    private String number; //Number given by scientists
    private String name; //Name of volcano
    private String country; //Where it is present
    private String location; //Location, continent wise
    private Double latitude; //Location in latitude
    private Double longitude; //Location in longitude
    private Integer elevation; //Elevation in meters
    private String type; //Type of volcano
    private String status; //Current status of the volcano

    /*
C:\Users\wynne\IdeaProjects\project-1-part-2-greewa23\src\input.txt C:\Users\wynne\IdeaProjects\project-1-part-2-greewa23\src\volcanoes_around_the_world_in_2021.csv
     */

    //Default constructor
    public Volcano() {
        this.region = "";
        this.number = "";
        this.name = "";
        this.country = "";
        this.location = "";
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.elevation = 0;
        this.type = "";
        this.status = "";
    }

    //Parametrized constructor
    public Volcano(String region, String number, String name, String country, String location, Double latitude, Double longitude,
                   Integer elevation, String type, String status) {
        this.region = region;
        this.number = number;
        this.name = name;
        this.country = country;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.type = type;
        this.status = status;
    }

    //Copy constructor
    public Volcano(Volcano v) {
        this.region = v.region;
        this.number = v.number;
        this.name = v.name;
        this.country = v.country;
        this.location = v.location;
        this.latitude = v.latitude;
        this.longitude = v.longitude;
        this.elevation = v.elevation;
        this.type = v.type;
        this.status = v.status;
    }

    //This method prints out the string representation of the object.
    public String toString() {
        String data = "";
        data += "(" + number + "/ "+country+") " +  name + " [region: " + region + ", location: "
                + location + ", latitude: " + latitude + ", longitude: " + longitude + ", elevation: "
                + elevation + ", type: " + type + ", status: " + status;
        return data;
    }

    //This method returns whether or not two objects are equal, based off their
    //elevation variable.
    public boolean equals(Volcano v) {
        //Compare each variable.
        if(!this.region.equals(v.region))
            return false;
        if(!this.number.equals(v.number))
            return false;
        if(!this.name.equals(v.name))
            return false;
        if(!this.country.equals(v.country))
            return false;
        if(!this.location.equals(location))
            return false;
        if(Double.compare(this.latitude, v.latitude)!=0)
            return false;
        if(Double.compare(this.longitude, v.longitude)!=0)
            return false;
        if(Integer.compare(this.elevation, v.elevation)!=0)
            return false;
        if(!this.type.equals(v.type))
            return false;
        if(!this.status.equals(v.status))
            return false;
        return true;
    }

    /*This method compares two objects. This method returns a negative number if the object
    is less than the object passed as a parameter, a positive number if the object is
    greater than the object passed as a parameter, and 0 if the objects are equal.*/
    @Override
    public int compareTo(Volcano obj) {
        int x = this.getElevation().compareTo(obj.getElevation());
        //If the objects are equal, check their other fields.
        if(x==0) {
            if(!this.equals(obj))
                return 1;
            return 0;
        }
        else {
            return x;
        }
        //Integer.compare(this.getElevation(), obj.getElevation());
    }

    //These are the getter methods for each variable.
    public Integer getElevation() {
        return elevation;
    }

    public String getRegion() {
        return region;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getLocation() {
        return location;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    //The following are the setter methods for each variable.
    public void setRegion(String region) {
        this.region = region;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
