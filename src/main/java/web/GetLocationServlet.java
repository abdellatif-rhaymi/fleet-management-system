
  package web;
  
  import jakarta.servlet.ServletException; import
  jakarta.servlet.annotation.WebServlet; import
  jakarta.servlet.http.HttpServlet; import
  jakarta.servlet.http.HttpServletRequest; import
  jakarta.servlet.http.HttpServletResponse; import java.io.IOException; import
  java.lang.reflect.AccessFlag.Location; import java.io.*; import
  com.google.gson.*;
  
  public class GetLocationServlet extends HttpServlet { protected void
  doGet(HttpServletRequest request, HttpServletResponse response) throws
  IOException { String path = request.getServletPath(); if
  (path.equals("/trajet.locate")) {
  
  int driverId = Integer.parseInt(request.getParameter("id"));
  entities.Location location =
  UpdateLocationServlet.getDriverLocation(driverId);
  
  response.setContentType("application/json"); PrintWriter out =
  response.getWriter();
  
  if (location != null) { Gson gson = new Gson();
  out.write(gson.toJson(location)); } else { out.write("{}"); } } }}
 