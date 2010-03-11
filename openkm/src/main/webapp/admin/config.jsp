<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.openkm.core.Config" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <link rel="Shortcut icon" href="favicon.ico" />
  <link rel="stylesheet" href="css/style.css" type="text/css" />
  <title>Configuration</title>
</head>
<body>
<%!
	public String escape(String str) {
		return str.replace("<", "&lt;").replace(">", "&gt;");
	}
%>
<%
	if (request.isUserInRole(Config.DEFAULT_ADMIN_ROLE)) {
%>
	<h1>Configuration</h1>
	<h2><%=Config.CONFIG_FILE %></h2>
	<table class="results" width="100%">
		<tr><th>Parameter</th><th>Value</th></tr>
		<tr class="odd"><td><b><%=Config.PROPERTY_REPOSITORY_CONFIG %></b></td><td><%=Config.REPOSITORY_CONFIG %></td></tr>
		<tr class="even"><td><b><%=Config.PROPERTY_REPOSITORY_HOME %></b></td><td><%=Config.REPOSITORY_HOME %></td></tr>

		<tr class="odd"><td><b><%=Config.PROPERTY_PRINCIPAL_ADAPTER %></b></td><td><%=Config.PRINCIPAL_ADAPTER %></td></tr>
		<tr class="even"><td><b><%=Config.PROPERTY_PRINCIPAL_DATABASE_FILTER_INACTIVE_USERS %></b></td><td><%=Config.PRINCIPAL_DATABASE_FILTER_INACTIVE_USERS %></td></tr>

		<tr class="odd"><td><b><%=Config.PROPERTY_PRINCIPAL_LDAP_SERVER %></b></td><td><%=Config.PRINCIPAL_LDAP_SERVER %></td></tr>
		<tr class="even"><td><b><%=Config.PROPERTY_PRINCIPAL_LDAP_SECURITY_PRINCIPAL %></b></td><td><%=Config.PRINCIPAL_LDAP_SECURITY_PRINCIPAL %></td></tr>
		<tr class="odd"><td><b><%=Config.PROPERTY_PRINCIPAL_LDAP_SECURITY_CREDENTIALS %></b></td><td><%=Config.PRINCIPAL_LDAP_SECURITY_CREDENTIALS %></td></tr>
		
		<tr class="even"><td><b><%=Config.PROPERTY_PRINCIPAL_LDAP_USER_SEARCH_BASE %></b></td><td><%=Config.PRINCIPAL_LDAP_USER_SEARCH_BASE %></td></tr>
		<tr class="odd"><td><b><%=Config.PROPERTY_PRINCIPAL_LDAP_USER_SEARCH_FILTER %></b></td><td><%=Config.PRINCIPAL_LDAP_USER_SEARCH_FILTER %></td></tr>
		<tr class="even"><td><b><%=Config.PROPERTY_PRINCIPAL_LDAP_USER_ATTRIBUTE %></b></td><td><%=Config.PRINCIPAL_LDAP_USER_ATTRIBUTE %></td></tr>

		<tr class="odd"><td><b><%=Config.PROPERTY_PRINCIPAL_LDAP_ROLE_SEARCH_BASE %></b></td><td><%=Config.PRINCIPAL_LDAP_ROLE_SEARCH_BASE %></td></tr>
		<tr class="even"><td><b><%=Config.PROPERTY_PRINCIPAL_LDAP_ROLE_SEARCH_FILTER %></b></td><td><%=Config.PRINCIPAL_LDAP_ROLE_SEARCH_FILTER %></td></tr>
		<tr class="odd"><td><b><%=Config.PROPERTY_PRINCIPAL_LDAP_ROLE_ATTRIBUTE %></b></td><td><%=Config.PRINCIPAL_LDAP_ROLE_ATTRIBUTE %></td></tr>

		<tr class="even"><td><b><%=Config.PROPERTY_PRINCIPAL_LDAP_MAIL_SEARCH_BASE %></b></td><td><%=Config.PRINCIPAL_LDAP_MAIL_SEARCH_BASE %></td></tr>
		<tr class="odd"><td><b><%=Config.PROPERTY_PRINCIPAL_LDAP_MAIL_SEARCH_FILTER %></b></td><td><%=Config.PRINCIPAL_LDAP_MAIL_SEARCH_FILTER %></td></tr>
		<tr class="even"><td><b><%=Config.PROPERTY_PRINCIPAL_LDAP_MAIL_ATTRIBUTE %></b></td><td><%=Config.PRINCIPAL_LDAP_MAIL_ATTRIBUTE %></td></tr>
		
		<tr class="odd"><td><b><%=Config.PROPERTY_MAX_FILE_SIZE %></b></td><td><%=(Config.MAX_FILE_SIZE / 1024 / 1024) %></td></tr>
		<tr class="even"><td><b><%=Config.PROPERTY_MAX_SEARCH_RESULTS %></b></td><td><%=Config.MAX_SEARCH_RESULTS %></td></tr>

		<tr class="odd"><td><b><%=Config.PROPERTY_RESTRICT_FILE_MIME %></b></td><td><%=Config.RESTRICT_FILE_MIME %></td></tr>
		<tr class="even"><td><b><%=Config.PROPERTY_RESTRICT_FILE_EXTENSION %></b></td><td><%=Config.RESTRICT_FILE_EXTENSION %></td></tr>
		
		<tr class="odd"><td><b><%=Config.PROPERTY_NOTIFICATION_MESSAGE_SUBJECT %></b></td><td><%=escape(Config.NOTIFICATION_MESSAGE_SUBJECT) %></td></tr>
		<tr class="even"><td><b><%=Config.PROPERTY_NOTIFICATION_MESSAGE_BODY %></b></td><td><%=escape(Config.NOTIFICATION_MESSAGE_BODY) %></td></tr>

		<tr class="odd"><td><b><%=Config.PROPERTY_SUBSCRIPTION_MESSAGE_SUBJECT %></b></td><td><%=escape(Config.SUBSCRIPTION_MESSAGE_SUBJECT) %></td></tr>
		<tr class="even"><td><b><%=Config.PROPERTY_SUBSCRIPTION_MESSAGE_BODY %></b></td><td><%=escape(Config.SUBSCRIPTION_MESSAGE_BODY) %></td></tr>

		<tr class="odd"><td><b><%=Config.PROPERTY_SUBSCRIPTION_TWITTER_USER %></b></td><td><%=Config.SUBSCRIPTION_TWITTER_USER %></td></tr>
		<tr class="even"><td><b><%=Config.PROPERTY_SUBSCRIPTION_TWITTER_PASSWORD %></b></td><td><%=Config.SUBSCRIPTION_TWITTER_PASSWORD %></td></tr>
		<tr class="odd"><td><b><%=Config.PROPERTY_SUBSCRIPTION_TWITTER_STATUS %></b></td><td><%=Config.SUBSCRIPTION_TWITTER_STATUS %></td></tr>

		<tr class="even"><td><b><%=Config.PROPERTY_SYSTEM_DEMO %></b></td><td><%=Config.SYSTEM_DEMO %></td></tr>
		<tr class="odd"><td><b><%=Config.PROPERTY_SYSTEM_READONLY %></b></td><td><%=Config.SYSTEM_READONLY %></td></tr>
		<tr class="even"><td><b><%=Config.PROPERTY_SYSTEM_OCR %></b></td><td><%=Config.SYSTEM_OCR %></td></tr>
		<tr class="odd"><td><b><%=Config.PROPERTY_SYSTEM_OPENOFFICE %></b></td><td><%=Config.SYSTEM_OPENOFFICE %></td></tr>
		<tr class="even"><td><b><%=Config.PROPERTY_SYSTEM_PDF2SWF %></b></td><td><%=Config.SYSTEM_PDF2SWF %></td></tr>
		<tr class="odd"><td><b><%=Config.PROPERTY_SYSTEM_ANTIVIR %></b></td><td><%=Config.SYSTEM_ANTIVIR %></td></tr>
		<tr class="even"><td><b><%=Config.PROPERTY_SYSTEM_LOGIN_LOWERCASE %></b></td><td><%=Config.SYSTEM_LOGIN_LOWERCASE %></td></tr>
		
		<tr class="odd"><td><b><%=Config.PROPERTY_APPLICATION_URL %></b></td><td><%=Config.APPLICATION_URL %></td></tr>
		<tr class="even"><td><b><%=Config.PROPERTY_DEFAULT_LANG %></b></td><td><%=Config.DEFAULT_LANG %></td></tr>
		<tr class="odd"><td><b><%=Config.PROPERTY_USER_KEYWORDS_CACHE %></b></td><td><%=Config.USER_KEYWORDS_CACHE %></td></tr>
		<tr class="even"><td><b><%=Config.PROPERTY_USER_SIZE_CACHE %></b></td><td><%=Config.USER_SIZE_CACHE %></td></tr>
	</table>
	
	<h2>MIME types</h2>
<%
		out.println("<table class=\"results\" width=\"100%\">");
		out.println("<tr><th>Icon</th><th>MIME</th></tr>");
		int i = 0;
		for (Iterator<String> it = Config.mimeAccept.iterator(); it.hasNext(); ) {
			String mime = it.next();
			out.println("<tr class=\""+(i++%2==0?"odd":"even")+"\"><td align=\"center\"><img src=\"../com.openkm.frontend.Main/img/icon/mime/"+mime+".gif\"></td><td>"+mime+"</td>");
		}
		out.println("</table>");
	} else {
		out.println("<div class=\"error\"><h3>Only admin users allowed</h3></div>");
	}
%>
</body>
</html>