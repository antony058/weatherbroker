<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Weather</title>
    <script src="js/jQuery3.0.1.js"></script>
</head>
<body>

<form action="weather" method="get">
    <p>City:
        <input type="text" id="city" name="city" placeholder="Enter the city">
    </p>
    <input type="submit" id="submitBtn" value="Submit">
</form>

<script>
    document.getElementById('city').onkeyup = function () {
        var cityName = $('#city').val();
        var isValid = checkUserInput(cityName);

        if (isValid)
            getCitiesByName(cityName);
    };

    function checkUserInput(cityName) {
        var cityNameLength = cityName.length;

        if (cityNameLength > 1) {
            return true;
        }

        return false;
    }

    function getCitiesByName(cityName) {
        $.ajax({
            url: "/weather/city/list/" + cityName,
            dataType: "application/json",
            success: function (data) {
                alert(data);
            }
        });
    }
</script>

</body>
</html>
