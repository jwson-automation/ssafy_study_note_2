<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import="dto.Product, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<%
List<Product> list = new ArrayList<Product>();
list = (ArrayList<Product>) request.getAttribute("_product");
if(list == null) {
%>
<script>
alert("제품 정보가 없습니다.");
</script>
<%    
} else {
	for (Product _prod : list){
%>

<meta charset="UTF-8">
<title>제품정보</title>
</head>
<body>
<div>
<div>제품번호 : <%=_prod.getIndex()%></div>
<div>이름 : <%=_prod.getName()%></div>
<div>가격 : <%=_prod.getPrice()%></div>
<div>설명 : <%=_prod.getDescription()%></div>
</div>
<%
	}
}
%>
</body>
</html>