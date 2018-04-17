<%--
  Created by IntelliJ IDEA.
  User: pnz-admin
  Date: 11.04.2018
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form>
    <input type="text" id="inputCity" placeholder="Enter the city">
    <input type="button" id="submitCity" placeholder="Submit" onclick="getWeatherDemo()">

    <input type="button" id="testButton" value="TEST SUBMIT" onclick="testSubmit()">
  </form>

  <script>
      function getWeatherDemo() {
          var xhr = new XMLHttpRequest();
          var city = document.getElementById("inputCity").textContent;
          var url = 'https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast ' +
              'where woeid in (select woeid from geo.places(1) where text="' + city +'")&format=json';

          xhr.open('GET', url, false);
          xhr.send();

          if (xhr.status !== 200) {
              alert(xhr.status + ": " + xhr.statusText)
          } else {
              alert(xhr.responseText)
          }
      }

      function testSubmit() {
          var cityName = document.getElementById('inputCity').textContent;
          alert(cityName);
      }

      document.getElementById('testButton').click = function () { testSubmit() }
  </script>

  </body>
</html>
