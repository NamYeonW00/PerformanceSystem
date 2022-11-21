<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*, model.*" %>
<%@page import="java.util.*, model.dao.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<%
	@SuppressWarnings("unchecked")
	Member member = (Member)session.getArribute("member");
	int perId = request.getAttribute("performance_id");
	PerformanceDAO perDao = new PerformanceDAO();
	Recommend recommend = (Recommend)perDao.findRecommend(perId, member);
%>
</head>
<body>
	<div>
		<h1>�¼� ��õ</h1>
		<h3><%=member.getName()%>�Բ� ��õ�帮�� �¼��Դϴ�.</h3>
		<div>
			<img src="<%=recommend.getRecommend_img()%>" width="220" height="430"/>
			<h2><%=recommend.getRecommend_seat()%></h2>
		</div>
	</div>
	<div>
			<button type="button" onclick="history.back();">�ݱ�</button>
	</div>
</body>
</html>