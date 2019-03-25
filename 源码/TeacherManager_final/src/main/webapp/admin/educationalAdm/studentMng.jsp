<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生信息管理</title>
    
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
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript">
	    function check() {
	        var excel_file = $("#excel_file").val();
	        if (excel_file == "" || excel_file.length == 0) {
	            alert("请选择文件路径！");
	            return false;
	        } else {
	            return true;
	        }
	    }
	
	    $(document).ready(function() {
	        var msg = "";
	        if ($("#importMsg").text() != null) {
	            msg = $("#importMsg").text();
	        }
	        if (msg != "") {
	            alert(msg);
	        }
	    });
	</script>
  </head>
  
  <body>
    <div class="container-scroller">
    	<jsp:include page="../partials/navbar.jsp" flush="true"/>
    	<div class="container-fluid page-body-wrapper">
    		<jsp:include page="../partials/sidebar.jsp" flush="true"/>
    		<div class="main-panel">
    			<!-- 页面代码在此放置 -->
    			
		        <div class="content-wrapper">
		          <div class="row">
		          
		          
		         
		          
		          
		          
		          

		

		              
		              
			<div class="col-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">导入学生初始信息</h4>
                  <p class="card-description">
                    请选择Excel格式文件
                  </p>
                  <form class="forms-sample" action="<%=basePath %>admin/ImportStuInfoCtrl" method="post" enctype="multipart/form-data" onsubmit="return check();">
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
		        </div>
    			
    			
    			
  			    
    			<jsp:include page="../partials/footer.jsp" flush="true"/>
    		</div>
    	</div>
    </div>
  </body>
</html>
