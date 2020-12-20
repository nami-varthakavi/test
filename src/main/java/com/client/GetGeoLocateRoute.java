package com.client;

import com.Main;
import com.exceptions.GeoLocateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

@Api
@Path("/geoLocate/{ipAddress}")
@Produces("application/json")
public class GetGeoLocateRoute implements Route {

  public static final Logger LOG = LoggerFactory.getLogger(Main.class);

  @GET
  @ApiOperation(value = "Gets the IP Geo Location", nickname="GetGeoLocate")
  @ApiImplicitParams({ @ApiImplicitParam(required = true, dataType = "string", name = "ipAddress", paramType = "path")}) //
  @ApiResponses(value = { //
                          @ApiResponse(code = HttpStatus.OK_200, message = "Success"), //
                          @ApiResponse(code = HttpStatus.BAD_REQUEST_400, message = "Invalid IP provided", response = GeoLocateException.class),
                          @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "Internal Server Error")
  })
  public String handle(@ApiParam(hidden=true) Request request, @ApiParam(hidden=true) Response response) {
    try {
      String ipAddress = request.params("ipAddress");
      response.type("application/json");
      final GeoLocateService geoLocateService = new GeoLocateServiceImpl();

      LOG.info("Requesting Geo Location for " + ipAddress);

      return geoLocateService.getIPGeoLocation(ipAddress);
    } catch (GeoLocateException ex) {
      response.status(HttpStatus.BAD_REQUEST_400);
      return "Invalid IP provided";
    } catch (Exception e) {
      response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
      return "Internal Server Error";
    }
  }

}
