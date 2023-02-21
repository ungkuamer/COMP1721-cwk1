import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;
import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Ungku Amer sc22uaib
 */
public class Track {

    ArrayList<Point> trackPoints;

    // TODO: Create a stub for the constructor
    public Track() {
        trackPoints = new ArrayList<>();
    }

    // TODO: Create a stub for readFile()
    public void readFile(String fileName) throws IOException {
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

    public Point lowestPoint() {

        if (trackPoints.size() == 0) {
            throw new GPSException("Track is empty");
        }
        Collections.sort(trackPoints, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Double.compare(o1.getElevation(), o2.getElevation());
            }
        });

        return trackPoints.get(0);
    }

    // TODO: Create a stub for highestPoint()

    public Point highestPoint () {

        if (trackPoints.size() == 0) {
            throw new GPSException("Track is empty");
        }
        Collections.sort(trackPoints, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Double.compare(o1.getElevation(), o2.getElevation());
            }
        });

        int getFrom = trackPoints.size()-1;
        return trackPoints.get(getFrom);
    }
    // TODO: Create a stub for totalDistance()
    public Double totalDistance() {

        if (trackPoints.size() < 2) {
            throw new GPSException("Not enough tracks");
        }

        double totalD = 0;

        for (int j = 0; j < trackPoints.size()-1; j++) {
            Point currentPoint = trackPoints.get(j);
            Point nextPoint = trackPoints.get(j+1);

            double distance = Point.greatCircleDistance(currentPoint, nextPoint);
            totalD += distance;
        }
        return totalD;
    }

    // TODO: Create a stub for averageSpeed()
    public Double averageSpeed() {

        if (trackPoints.size() < 2) {
            throw new GPSException("Not enough tracks");
        }

        double totalTime = 0;

        for (int k = 0; k < trackPoints.size()-1; k++) {
            ZonedDateTime currentPoint = trackPoints.get(k).getTime();
            ZonedDateTime nextPoint = trackPoints.get(k+1).getTime();

            double currentTime = SECONDS.between(currentPoint, nextPoint);
            totalTime += currentTime;
        }

        double avgSpeed = totalDistance()/totalTime;
        return avgSpeed;
    }
}