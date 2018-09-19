<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:forEach items="${messageMapList}" var="m">
	<c:if test="${m.fromUser eq userName || m.toUser eq userName}">
		<c:if test="${m.fromUser eq userName}">
			<font color="blue"><c:out value="${m.fromUser}"></c:out> : <c:out
					value="${m.message}"></c:out> </font>
		</c:if>
		<c:if test="${m.fromUser ne userName}">
			<font color="green"> <c:out value="${m.fromUser}"></c:out> : <c:out
					value="${m.message}"></c:out>
			</font>
		</c:if>
	</c:if>
	<br />
</c:forEach>
