<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
  <c:set var="list" value="${requestScope.list}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>		<!-- 합쳐질때 주석처리 해야함 -->
<script>
	function init(){
		//날짜구하기
		var date = new Date();
		date = new Date('2018-09-16');
		console.log(date);
		// var strDate = date.toString();
		//$("#dailymeals").append('<p>'+(date.getMonth()+1)+'</p>'); 
		$.ajax({
			url:"meals/daily.do"
			,method: 'post'
			,data : {"date": date}
			,success: function(result){
				var jsonObj = JSON.parse(result);
			//	if(jsonObj.status == 'ok'){					
				
			//	$("#dailymeals").html(result);
					
			//	}//if
			}//success
		}); //ajax 
	}	

	$(init);	
</script>
</head>
<body>


!!!!!!!!!!!!!!!!!!!!!!!!!!!! 의미 없는 파일 입니다.!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 

</body>
</html>