import java.io.File;
import java.io.IOException;

/**
 * Program to provide information on a GPS track stored in a file.
 *
 * @author Ungku Amer Iskandar sc22uaib
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

    System.out.printf("%d points in track\n", newTrack.size());
    // implement lowest point code
    // implement highest point code

    System.out.printf("Total distance = %.3f km\n", distKM);
    System.out.printf("Average speed = %.3f m/s\n", newTrack.averageSpeed());

  }
}
