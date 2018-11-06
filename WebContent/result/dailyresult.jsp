<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <c:set var="list" value="${requestScope.list}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>		<!-- 합쳐질때 주석처리 해야함 -->
<script>
	function init(){
		 $.ajax({
			url:"meals/daily.do"
			,method: 'post'
			,success: function(result){
				console.log("ok");
			}//success
		});//ajax 
	}	

	$(init);
</script>


</head>
<body>

<div id="dailymeals">
 <c:set var = "orgdate" value = "${list[1].meals_date}" />
<fmt:formatDate value="${orgdate}" pattern="MM월 dd일"/>
<c:forEach var="p" items="${list}">  
  <li>${p.food.food_name} </li>  
</c:forEach>

</div>

</body>
</html>