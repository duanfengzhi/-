<%@ page import="java.util.*,com.hfut.domain.Teacher" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看校内教师</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- plugins:css -->
    <link rel="stylesheet" href="<%=basePath%>vendors/iconfonts/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="<%=basePath%>vendors/css/vendor.bundle.base.css">
    <!-- endinject -->
    <!-- inject:css -->
    <link rel="stylesheet" href="<%=basePath%>css/style.css">
    <!-- endinject -->
    <link rel="shortcut icon" href="<%=basePath%>images/favicon.png" />

  </head>
  
  <body>
    <div class="container-scroller">
    	<jsp:include page="../partials/navbar.jsp" flush="true"/>
    	<div class="container-fluid page-body-wrapper">
    		<jsp:include page="../partials/sidebar.jsp" flush="true"/>
    		<div class="main-panel">
    			<!-- 页面代码在此放置 -->
    			
<div class="content-wrapper">   
	<div class="page-header">
		<h3 class="page-title">
			<span class="page-title-icon bg-gradient-primary text-white mr-2">
			<i class="mdi mdi-border-color"></i>                 
			</span>
			查看校内教师信息
		</h3>
	    <!-- <nav aria-label="breadcrumb">
	      <ul class="breadcrumb">
	        <li class="breadcrumb-item active" aria-current="page">
	          <span></span>发送反馈
              <i class="mdi mdi-alert-circle-outline icon-sm text-primary align-middle"></i>
            </li>
          </ul>
        </nav> -->
	</div>
	
	
	
    			
		
	<form action='admin/ListTeacherInfoCtrl' method='POST' class="forms-sample">
		<table>
			<tr>
			<td >姓名:</td>
			<td><input type=text class="form-control" name='TeaName' 
			value='${requestScope.TeaName}'/></td>
			
	        <td >所属学院: </td>
	        <td><input type=text class="form-control" name='TeaBelong' 
	        value='${requestScope.TeaBelong}'/></td>
	        
	        <td><input type=submit value='查询' class="btn btn-outline-primary" /></td>
	       <font color="red"> ${conErrMap['condition'] == null? '' : conErrMap['condition'] }</font>
	        </tr>
		</table>
	</form>
  
  
<div class="row">

  <div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">所有教师</h4>

		<table class="table table-hover">
				<thead align="center">
						<tr>
				  	 		<th>姓名</th>
				  	 		<th>工号</th>
				  	 		<th>类型</th>
				  	 		<th>学院</th>
				  	 		<th></th>
				  	 	</tr>
				<thead>
			    <c:forEach var="t" items="${requestScope.AllTeacherInfo}">
				    <tbody align="center">
			        <tr>
			             <td>${t.teaName}</td>
			             <td>${t.teaNo}</td>
			             <td>${t.teaKind}</td>
			             <td>${t.teaBelong}</td>
			             <td><a href='admin/ListTeaInfoCtrl?tid=${t.teaNo}' class="btn btn-outline-danger btn-sm">查看详情</a></td>
					</tr>
					</tbody>
				</c:forEach>
		</table>
      </div>
	<nav aria-label="breadcrumb" style="margin-left:30%">
		<ul class="breadcrumb">
			<li class="breadcrumb-item"><a href='admin/ListTeacherInfoCtrl?page=0'><button type="button" class="btn btn-outline-primary btn-sm">首页</button></a></li>
			<li class="breadcrumb-item"><a href='admin/ListTeacherInfoCtrl?page=prev'><button type="button" class="btn btn-outline-primary btn-sm">上一页</button></a></li>
			<li class="breadcrumb-item"><a href='admin/ListTeacherInfoCtrl?page=next'><button type="button" class="btn btn-outline-primary btn-sm">下一页</button></a></li>
			<li class="breadcrumb-item"><a href='admin/ListTeacherInfoCtrl?page=${requestScope.lastPage}'><button type="button" class="btn btn-outline-primary btn-sm">末页</button></a></li>
		</ul>
	</nav>
    </div>
    
    
  </div>
		
    
  
    <div class="col-6 grid-margin stretch-card" style="margin-top:50px">
       <div class="card">
         <div class="card-body">
           <h4 class="card-title">导入校内教师基本信息</h4>
           <p class="card-description">
             	请选择Excel格式文件
           </p>
           <form class="forms-sample" action="<%=basePath %>admin/ImportTeaInfoCtrl" method="post" enctype="multipart/form-data" onsubmit="return check();">
             <div class="form-group">
               <label>文件名</label>
               <input type="file" id="excel_file" name="filename" class="file-upload-default" accept="xlsx" size="80">
               <div class="input-group col-xs-12">
                 <input type="text" class="form-control file-upload-info" >
                 <span class="input-group-append">
                   <button class="file-upload-browse btn btn-outline-primary" type="button">选择文件</button>
                 </span>
               </div>
             </div>
             <button type="submit" id="excel_button" class="btn btn-gradient-primary mr-2">导入Excel</button>
             <font id="importMsg" color="red">${msg }</font><input type="hidden" />
           </form>
         </div>
       </div>
     </div>
    
    
    
    
    
    
    
</div>
    
  			    
  			    <!-- 插入footer之前的代码 -->
    			<jsp:include page="../partials/footer.jsp" flush="true"/>
    		</div>
    	</div>
    </div>
  </body>
</html>