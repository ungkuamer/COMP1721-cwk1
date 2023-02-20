import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;


/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Nick Efford & Ungku Amer sc22uaib
 */
public class Point {
  // Constants useful for bounds checking, etc

  private static final double MIN_LONGITUDE = -180.0;
  private static final double MAX_LONGITUDE = 180.0;
  private static final double MIN_LATITUDE = -90.0;
  private static final double MAX_LATITUDE = 90.0;
  private static final double MEAN_EARTH_RADIUS = 6.371009e+6;

  // attributes declaration
  ZonedDateTime time = ZonedDateTime.now();
  double longitude;
  double latitude;
  double elevation;

  // constructor
  public Point(double longitude, double latitude, double elevation) {
    this.longitude = longitude;
    this.latitude = latitude;
    this.elevation = elevation;

    if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
      throw new GPSException("Invalid Coordinates");
    }

    if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
      throw new GPSException("Invalid Coordinates");
    }
  }

  // getter methods
  public ZonedDateTime getTime() {
    return time;
  }

  public double getLongitude() {
    return longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getElevation() {
    return elevation;
  }



  // TODO: Create a stub for the constructor

  // TODO: Create a stub for getTime()

  // TODO: Create a stub for getLatitude()

  // TODO: Create a stub for getLongitude()

  // TODO: Create a stub for getElevation()

  // TODO: Create a stub for toString()

  // IMPORTANT: Do not alter anything beneath this comment!

  /**
   * Computes the great-circle distance or orthodromic distance between
   * two points on a spherical surface, using Vincenty's formula.
   *
   * @param p First point
   * @param q Second point
   * @return Distance between the points, in metres
   */
  public static double greatCircleDistance(Point p, Point q) {
    double phi1 = toRadians(p.getLatitude());
    double phi2 = toRadians(q.getLatitude());

    double lambda1 = toRadians(p.getLongitude());
    double lambda2 = toRadians(q.getLongitude());
    double delta = abs(lambda1 - lambda2);

    double firstTerm = cos(phi2)*sin(delta);
    double secondTerm = cos(phi1)*sin(phi2) - sin(phi1)*cos(phi2)*cos(delta);
    double top = sqrt(firstTerm*firstTerm + secondTerm*secondTerm);

    double bottom = sin(phi1)*sin(phi2) + cos(phi1)*cos(phi2)*cos(delta);

    return MEAN_EARTH_RADIUS * atan2(top, bottom);
  }
}
