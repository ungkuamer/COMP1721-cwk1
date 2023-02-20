import java.io.*;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Ungku Amer Iskandar
 */
public class Track {

    ArrayList<Point> trackPoints;

    // TODO: Create a stub for the constructor
    public Track() {
        trackPoints = new ArrayList<>();

    }

    // TODO: Create a stub for readFile()
    public void readFile(String fileName) throws IOException { // change to GPSException
        trackPoints.removeAll(trackPoints);
        try {
            Scanner scanner = new Scanner(new File(fileName));
            String line = scanner.nextLine();

            if (line != null) {
                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    String[] dataFinal = data.split(",");

                    if (dataFinal.length < 4) {
                        throw new GPSException("Not enough data");
                    } else {
                        ZonedDateTime time = ZonedDateTime.parse(dataFinal[0]);
                        double longitude = Double.parseDouble(dataFinal[1]);
                        double latitude = Double.parseDouble(dataFinal[2]);
                        double elevation = Double.parseDouble(dataFinal[3]);

                        trackPoints.add(new Point(time, longitude, latitude, elevation));
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File does not exists");
        }

    }

    // TODO: Create a stub for add()
    public void add(Point points) {
        trackPoints.add(points);
    }

    // TODO: Create a stub for get()
    public Point get(int points) throws GPSException{
        if (points < 0) {
            throw new GPSException("Points to low");
        }
        else if (points > trackPoints.size()-1) {
            throw new GPSException("Points out of bound");
        }
        else {
            return trackPoints.get(points);
        }

    }

    // TODO: Create a stub for size()
    public int size() {
        return trackPoints.size();
    }
    // TODO: Create a stub for lowestPoint()


    // TODO: Create a stub for highestPoint()

    // TODO: Create a stub for totalDistance()

    // TODO: Create a stub for averageSpeed()

}