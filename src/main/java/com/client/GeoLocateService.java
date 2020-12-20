package com.client;

import com.exceptions.GeoLocateException;
import java.io.IOException;

public interface GeoLocateService {

  public String getIPGeoLocation(String ipAddress) throws GeoLocateException, IOException, InterruptedException;
}
