<%@page import="com.span.webapp.ManipulateImage"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
    String imgEncodedStr = request.getParameter("image");
String fileName = request.getParameter("filename");
System.out.println("Filename: "+ fileName);
if(imgEncodedStr != null){
	 response.setIntHeader("Refresh", 1);
	 byte[] imageByteArray=( ManipulateImage.convertStringtoImage(imgEncodedStr, fileName));//edited
   
    System.out.println("Inside if");
    out.print("Image upload complete, Please check your directory");
} else{
    System.out.println("Inside else");
    out.print("Image is empty");    
}
%>
<%-- <!DOCTYPE html>
<html>
<head>
  <title>My First HTML</title>
  <meta charset="UTF-8">
</head>
<body>

<p>The HTML head element contains meta data.</p>
<img  src='data:image/jpeg;base64,<%= imgEncodedStr%>' alt="image" style="width:304px;height:228px;">


</body>
</html> --%>