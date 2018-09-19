<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<br>
<br>
<br>
<div align="center">
	<form:form method="post" name="loginPage" action="doLogin.html">
		<p
			style="font-weight: bold; font-size: 25px; color: teal; font-variant: small-caps;">Login
			Page</p>

		<b>${info}</b>
		<br />
		<table cellpadding="5">
			<tr>
				<td align="right">Username</td>
				<td align="left"><form:input path="userName" /></td>
			</tr>
			<tr>
				<td align="right">Password</td>
				<td align="left"><form:password path="password" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Login"></td>
			</tr>

		</table>
	</form:form>
</div>