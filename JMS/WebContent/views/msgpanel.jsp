<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script>
	window.setInterval("reloadIFrame();", 7000);
	function reloadIFrame() {
		document.getElementById('chathistoryframe').contentWindow.location
				.reload();
	}
</script>

<div align="center">
	<h2 style="color: teal;">Secure Instant Point to Point Messaging</h2>
	User Name - ${userName} - &nbsp;&nbsp; (<a href="logout.html">Logout</a>)
	<br> <br> <font color="maroon;">${info}</font> <br>
	<hr>
	<form:form action="sendMessage.html" method="post">
	Receiver :<form:select path="toUser">

			<c:forEach var="u" items="${users}">
				<c:if test="${u ne userName}">
					<option value="${u}">${u}</option>
				</c:if>
			</c:forEach>
		</form:select>
		<br>
		<br>
	Message: <input type="text" size="80" name="message" />
		<input type="submit" value="Send" />
	</form:form>
	<hr>
	<h3 style="color: teal;">Chat History</h3>
	<iframe marginwidth="0" marginheight="0" width="500" height="300"
		style="border-radius: 12px; border: 1px solid black; padding-left: 5px; padding-right: 5px; padding-bottom: 5px; padding-top: 5px; overflow: auto;"
		id="chathistoryframe" name="chathistoryframe" scrolling="yes"
		frameborder=0 src="getMessage.html"></iframe>
	<hr>

	<c:if test="${null ne receivedMessage}">
		<c:out value="${receivedMessage}"></c:out>
	</c:if>

	<br /> <br /> <br />
</div>