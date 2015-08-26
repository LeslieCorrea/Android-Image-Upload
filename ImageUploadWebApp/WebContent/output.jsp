<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@page import="com.span.webapp.ManipulateImage"%>
<%@page import ="java.net.URL"%>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.ArrayList" %><%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    String[] k=ManipulateImage.display();
    int l=k.length;
    
    
    
    out.print("<table border='1'>");
    out.print("<tr>");
    out.print("<td>Name</td>");
    out.print("</tr>");
    for(int i=0;i<k.length;i++){
        
        
            out.print("<tr>");
           
            out.print("<td>"+k[i]+"</td>");
            out.print("</tr>");
        
    }
    out.print("</table>");
  out.print(  "</br>");
  out.print(  "</br>");
  out.print(  "</br>");
    
    out.print("<table border='1'>");
    out.print("<tr>");
    out.print("<td>Without File</td>");
    out.print("</tr>");
    int l1=k.length;
    for(int i=0;i<k.length;i++){
        
        
            out.print("<tr>");
           
            out.print("<td>"+k[i].substring(5)+"</td>");
            out.print("</tr>");
        
    }
    out.print("</table>");
    
    out.print(  "</br>");
    out.print(  "</br>");
    out.print(  "</br>");
    
    String [] j = new String[k.length];
	 Arrays.fill(j,"none");
	 String [] link= new String[k.length];
	 Arrays.fill(link,"none");
	 //===Edited
	 out.print("<table border='1'>");
	    out.print("<tr>");
	    out.print("<td>Image Url</td>");
	    out.print("</tr>");
    for(int i=0;i<k.length;i++){
    	  j[i]=k[i].substring(5);
    	  link[i]="http://localhost:8080/ImageUploadWebApp/DisplayImageServlet/images/"+j[i];
    }
    for(int i=0;i<k.length;i++){
    	 out.print("<tr>");
         
         out.print("<td>");
         out.print("<a href='#'>"+link[i]+"</a>");
         out.print("</td>");
         out.print("</tr>");
    }
    out.print("</table>");
    URL[] url= new URL[k.length]; //
    for(int i=0;i<k.length;i++){
    url[i]= new URL(link[i]);
    }
    /* if(k.length !=0){
    out.print("<table>");
    out.print("<tr>");
    for(int i=0;i<k.length;i++){
    	out.print("<td>");
    	
    	out.print("<img  style='width:304px;height:228px; ' id='abcd'>");
    	URL h=url[i];
    	out.print("<script language='text/javascript'");
    	
    	// abcd.src=src1 > ");
    	out.print("</script>");
    	out.print("</td>");
    	
    }
    out.print("</tr>");
    out.print("</table>");
    } */
    	
   // }
    
    //String asd="http://localhost:8080/ImageUploadWebApp/DisplayImageServlet/images/attur-church-karkala-karnataka.jpg";
   
  //  for(int i=0;i<k.length;i++){
    	
   // }
    
    %>
    
<!DOCTYPE html>
<html>
<head>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <title>My First HTML</title>
  <meta charset="UTF-8">
</head>
<body>

<p>The HTML head element contains meta data.</p>
<p>Meta data is data about the HTML document.</p>
<div id="textDiv"></div>
<!-- <img id="copyimg1"  alt="img" style="width:304px;height:228px;">
<img id="copyimg2"  alt="img" style="width:304px;height:228px;">
<img id="copyimg3"  alt="img" style="width:304px;height:228px;">
<img id="copyimg4"  alt="img" style="width:304px;height:228px;"> -->
<%

	for(int i=0; i< k.length ;i++){
		out.println(" <img id='copyimg"+i+"' alt='img' style='width:304px;height:228px;' src="+url[i]+">");
		
	 } 
%>
<script type="text/javascript">
//var default_image = document.getElementById('copyimg');
//var $div = $("#copyimg");

		
	

<%-- $("#copyimg1").attr("src", "<%=url[0]%>")
$("#copyimg2").attr("src", "<%=url[1]%>")
$("#copyimg3").attr("src", "<%=url[2]%>")
$("#copyimg4").attr("src", "<%=url[3]%>") --%>

<%-- for(var i=0;i< <%=l1%>;i++){
    var default_img_src = "<%=url[0]%>" ;
    default_image.src = default_img_src;
} --%>
    //var asd = document.getElementById("copyimg");
   
   
    

</script>


<!-- <img 
src="http://localhost:8080/ImageUploadWebApp/DisplayImageServlet/images/attur-church-karkala-karnataka.jpg" style="width:304px;height:228px;" alt="Leslie"> -->


</body>
</html> 
