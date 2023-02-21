import java.io.File;
import java.io.IOException;

/**
 * Program to provide information on a GPS track stored in a file.
 *
 * @author Ungku Amer sc22uaib
 */
public class TrackInfo {
  public static void main(String[] args) throws IOException {
    // TODO: Implement TrackInfo application here
    if (args.length == 0) {
      System.out.print("No file name given");
      System.exit(0);
    }

    Track newTrack = new Track();
    newTrack.readFile(args[0]);
    double distKM = newTrack.totalDistance()/1000;
    Point highest = newTrack.highestPoint();
    Point lowest = newTrack.lowestPoint();

    System.out.printf("%d points in track\n", newTrack.size());
    System.out.printf("(%.5f, %.5f), %.1f\n", lowest.getLongitude(), lowest.getLatitude(), lowest.getElevation());
    System.out.printf("(%.5f, %.5f), %.1f\n", highest.getLongitude(), highest.getLatitude(), highest.getElevation());
    System.out.printf("Total distance = %.3f km\n", distKM);
    System.out.printf("Average speed = %.3f m/s\n", newTrack.averageSpeed());

  }
}
