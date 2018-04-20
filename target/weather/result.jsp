<%@ page import="ru.bellintegrator.weatherbroker.server.weather.view.WeatherView" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>Result</title>
    <script src="js/jQuery3.0.1.js"></script>
  </head>
  <body>
  <form>
    <p>City: <%= request.getParameter("city")%></p>
  </form>
  <p>

  <script>
    $(document).ready(function () {

        $('#submitCity').click(function () {
            var cityName = $('#inputCity').val();

            $.ajax({
                url: '/weather/weather/' + cityName,
                success: function (data) {
                    var weatherView = "Date: " + data.date + "\n" +
                            "Temperature: " + data.temp + "\n" +
                            "Text: " + data.text;
                    alert(weatherView);
                }
            })
        });
    });
  </script>

  </body>
</html>
