package utils;

import items.Utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

import static items.Utils.getUserLogin;

public class ICBM {
  public static void launchICBM() {
    if (Objects.equals(getUserLogin(), "root")) {
      try {
        Utils.print("Enter target lat coordinates: ");
        Scanner scanner = new Scanner(System.in);
        String lat = scanner.nextLine();
        Utils.print("Enter target lon coordinates: ");
        String lon = scanner.nextLine();
        Utils.print("Calculating distance...");
        String target = getLFC(lat, lon);
        double distance = getDistance(Double.parseDouble(lat), Double.parseDouble(lon));
        if (distance == 0) {
          return;
        }
        Utils.print("Target set to " + target + " Confirm launch? (y/n)");
        String confirm = scanner.nextLine();
        if (!confirm.equalsIgnoreCase("y")) {
          Utils.print("Aborting launch");
          return;
        }
        Utils.print("Distance to target: " + distance + " km");
        Utils.print("ETA: " + getTravelTime(String.valueOf(distance)));
        Utils.print("Launching ICBM in T-10");
        for (int i = 10; i > 0; i--) {
          Utils.print("T-" + i);
          Thread.sleep(1000);
        }
        Utils.print("Launch: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\nTarget: " + target + "\nICBM hit at: " + LocalDateTime.now().plusHours(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " hours");
        scanner.nextLine();
        Utils.print("Thank you for using our services :)");
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      Utils.print("You are not authorized to launch an ICBM");
    }
  }

  public static String getLFC(String lat, String lon) {
    String latLonRegex = "^-?\\d{1,2}\\.\\d{1,15}$"; // Matches latitude/longitude format

    if (!lat.matches(latLonRegex) || !lon.matches(latLonRegex)) {
      return "Invalid latitude or longitude format!";
    }

    try {
      String url = "https://nominatim.openstreetmap.org/reverse?lat=" + lat + "&lon=" + lon + "&format=json&addressdetails=1";
      String response = HttpClient.newHttpClient().send(HttpRequest.newBuilder()
              .uri(URI.create(url))
              .header("User-Agent", "Java-HttpClient")
              .build(), HttpResponse.BodyHandlers.ofString()).body();

      // Extract "display_name" field manually
      if (response.contains("\"display_name\":\"")) {
        return response.split("\"display_name\":\"")[1].split("\",")[0];
      }
      return "Address not found!";

    } catch (Exception e) {
      e.printStackTrace();
      return "Error fetching location!";
    }
  }

  public static String getTravelTime(String distance) {
    double speed = 24000; // km/h
    double travelTimeInHours = Double.parseDouble(distance) / speed;

    // Convert travel time into hours, minutes, and seconds
    int hours = (int) travelTimeInHours;
    int minutes = (int) ((travelTimeInHours - hours) * 60);
    int seconds = (int) Math.round(((travelTimeInHours - hours) * 60 - minutes) * 60);

    // Format the result as hh:mm:ss
    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
  }


  public static double getDistance(double lat1, double lon1) {
    // Radius of Earth in kilometers
    final double R = 6371;

    // Convert degrees to radians
    double lat1Rad = Math.toRadians(lat1);
    double lon1Rad = Math.toRadians(lon1);
    double lat2Rad = Math.toRadians(34.74521918903878);
    double lon2Rad = Math.toRadians(-120.52007608400261);

    // Differences between latitudes and longitudes
    double dLat = lat2Rad - lat1Rad;
    double dLon = lon2Rad - lon1Rad;

    // Haversine formula
    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
            + Math.cos(lat1Rad) * Math.cos(lat2Rad)
            * Math.sin(dLon / 2) * Math.sin(dLon / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    // Distance in kilometers
    double distance = R * c;
    if (distance > 13000) {
      Utils.print("Distance is too far for ICBM launch");

    } else {
      return distance;
    }
    return 0;
  }
}
