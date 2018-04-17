<%@ page import="ru.bellintegrator.weatherbroker.weather.view.Weather" %>
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
                    var weather = "Date: " + data.date + "\n" +
                            "Temperature: " + data.temp + "\n" +
                            "Text: " + data.text;
                    alert(weather);
                }
            })
        });
    });
  </script>

  </body>
</html>
