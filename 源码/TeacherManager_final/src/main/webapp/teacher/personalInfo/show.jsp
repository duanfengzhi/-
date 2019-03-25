<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>教师首页</title>
    
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
                 <i class="mdi mdi-account-box"></i>                 
               </span>
                详细信息
               </h3>
               <nav aria-label="breadcrumb">
                 <ul class="breadcrumb">
                   <li class="breadcrumb-item active" aria-current="page">
                     <span></span>个人信息
                     <i class="mdi mdi-alert-circle-outline icon-sm text-primary align-middle"></i>
                   </li>
                 </ul>
               </nav>
		     </div>
			 <div class="row">
			    <div class="col-md-12 grid-margin stretch-card">
			     <div class="card">
                   <div class="card-body">
                    <h4 class="card-title">合肥工业大学软件学院教师信息</h4>
					 <div class="row">
					 <div class="col-md-6">
                      <ul class="list-star">
                       <li><font class="text-primary">姓名：</font>${sessionScope.teacher.teaName }</li>
					   </br>
					   <li><font class="text-primary">工号：</font>${sessionScope.teacher.teaNo}</li>
					   </br>
                       
					   <li><font class="text-primary">类型：</font>${sessionScope.teacher.teaKind }</li>
					   </br>
					   <li><font class="text-primary">所属：</font>${sessionScope.teacher.teaBelong }</li>
					   </br>
                       <li><font class="text-primary">电话：</font>${sessionScope.teacher.teaTel }</li>
					   </br>
                       <li><font class="text-primary">邮箱：</font>${sessionScope.teacher.teaEmail }</li>
					   </br>
                       
					   
                      </ul>
					 </div>
					 <div class="col-md-6">
                      <ul class="list-star">
                      <li><font class="text-primary">教授课程：</font>${sessionScope.teacher.teaCourse }</li>
					   </br>
					   <li><font class="text-primary">职位：</font>${sessionScope.teacher.teaTitle }</li>
					   </br>
                       <li><font class="text-primary">学位：</font>${sessionScope.teacher.teadegree }</li>
					   </br>
                       <li><font class="text-primary">毕业院校：</font>${sessionScope.teacher.teagraduate }</li>
					   </br>
                       <li><font class="text-primary">海外经历：</font>${sessionScope.teacher.overSeaExp }</li>
					   </br>
                       
                      </ul>
					 </div>
					 <a href="<%=basePath %>teacher/personalInfo/change.jsp"><button class="btn btn-gradient-primary mr-2">修改个人信息</button>
                    </div>
				   </div>
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
