<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Reservation Details</title>
</head>
<body>
<h2>Flight Details</h2>
Airline:${reservation.flight.operatingAirlines}</br>
Flight Number:${reservation.flight.flightNumber}</br>
Departure City:${reservation.flight.departureCity}</br>
Arrival City:${reservation.flight.arrivalCity}</br>
Date Of Departure:${reservation.flight.dateOfDeparture}</br>
Estimated Departure Time:${reservation.flight.estimatedDepartureTime}</br>

<h2>Passenger Details:</h2>
First Name:${reservation.passenger.firstName}</br>
Last Name:${reservation.passenger.lastName}</br>
Email:${reservation.passenger.email}</br>
Phone:${reservation.passenger.phone}</br>

<pre>
<form action="completeCheckIn" method="post">
Enter the number of bags :<input type="text" name="numberOfBags"/>
<input type="hidden" value="${reservation.id}" name="reservationId"/>
<input type="submit" value="Check IN"/>
 </form>
</pre>

</body>
</html>