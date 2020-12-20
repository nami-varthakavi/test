package com;

import static spark.Spark.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import io.swagger.codegen.languages.features.SwaggerUIFeatures;

@SwaggerDefinition(host = "localhost:4567",
info = @Info(description = "SPARK JAVA IPGEOLOCATION API",
version = "V1.0",
title = "SPARK JAVA IPGEOLOCATION",
contact = @Contact(name = "Nami") ) ,
schemes = { SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS },
consumes = { "application/json" },
produces = { "application/json" },
tags = { @Tag(name = "swagger") })
public class Main {

  public static final String APP_PACKAGE = "com";

  public static void main(String[] args) throws IllegalAccessException, InstantiationException, JsonProcessingException {
    RouteBuilder.setupRoutes(APP_PACKAGE);

    // Build swagger json description
    final String swaggerJson = SwaggerParser.getSwaggerJson(APP_PACKAGE);
    get("/swagger", (req, res) -> {
      return swaggerJson;
    });
  }
}