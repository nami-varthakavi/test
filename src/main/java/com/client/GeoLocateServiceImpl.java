package com.client;

import com.exceptions.GeoLocateException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeoLocateServiceImpl implements GeoLocateService{

  final Logger LOG = LoggerFactory.getLogger(GeoLocateServiceImpl.class);

  private static final Pattern IPV4_PATTERN = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$");
  private static final Pattern IPV6_STD_PATTERN = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
  private static final Pattern IPV6_HEX_COMPRESSED_PATTERN = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");

  private static final String API_KEY = "16a9461d4f1c4597a357df4241d53ae4";

  /**
   * Check whether the provide ip is a valid ipv4/ipv6
   * @param ipAddress ipAddress to be checked
   * @return true if valid, otherwise false
   */
  public static boolean isValidIpAddress(final String ipAddress) {
    Matcher matcherIPV4 = IPV4_PATTERN.matcher(ipAddress);
    Matcher matcherIPV6_STD = IPV6_STD_PATTERN.matcher(ipAddress);
    Matcher matcherIPV6_HEX = IPV6_HEX_COMPRESSED_PATTERN.matcher(ipAddress);
    return matcherIPV4.matches() || matcherIPV6_STD.matches() || matcherIPV6_HEX.matches();
  }

  @Override
  public String getIPGeoLocation(String ipAddress) throws IOException, InterruptedException, GeoLocateException {

    LOG.info("Requesting Geo Location for " + ipAddress);

    // Check for ipAddress
    if(isValidIpAddress(ipAddress)) {
      String ipGeoLocationIOURL = "https://api.ipgeolocation.io/ipgeo?apiKey=" + API_KEY
                                  + "&ip=" + ipAddress;

      HttpClient client = HttpClient.newHttpClient();

      HttpRequest checkRequest = HttpRequest.newBuilder()
          .uri(URI.create(ipGeoLocationIOURL))
          .header("Content-Type", "application/json")
          .GET()
          .build();

      HttpResponse<String> response = client.send(checkRequest, HttpResponse.BodyHandlers.ofString());

      return response.body();
    }
    throw new GeoLocateException("Invalid Input");
  }
}
