<span style="font-size:12px;">
<span style="font-size:14px;">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*,java.io.*,java.util.*"%>
<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<title>批量反地址解析+位置</title>
	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#l-map{height:600px;width:100%;}
		#r-result{width:100%; font-size:14px;line-height:20px;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=cQcGi1ReQPYWsLhVITcQsRUIKKTbxojc"></script>
</head>
<body>
<%
		String driverName = "com.mysql.jdbc.Driver";
		//数据库用户名 
		String userName = "root";
		//密码 
		String userPasswd = "";
		//数据库名 
		String dbName = "user_address";
		//表名 
		String tableName = "locatetable";
		//联结字符串 
		String url = "jdbc:mysql://localhost:3306/"+dbName+"?"
		+ "useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(url, userName, userPasswd);
		Statement statement = connection.createStatement();
		String sql = "SELECT longtitude,latitude FROM " + tableName;
		ResultSet rs = statement.executeQuery(sql);//连接数据库

		rs.last();  //直接执行跳到结果集的最后一行
		int h=rs.getRow();//获取数据库行数
		//System.out.println(h);
		rs.beforeFirst();//重新执行到第一行的前一行
		//System.out.println(rs.getMetaData().getColumnCount());//获取列数
		
		 ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等   
         int columnCount = rs.getMetaData().getColumnCount(); //返回此 ResultSet 对象中的列数   
         List list = new ArrayList(); 
         List longtitudes = new ArrayList();
         List latitudes = new ArrayList();
         Map rowData = new HashMap();   
         while (rs.next()) {   
          rowData = new HashMap(columnCount);   
          for (int i = 1; i <= columnCount; i++) {   
                  rowData.put(md.getColumnName(i), rs.getObject(i));//hashmap    
                  //System.out.println("rpwdata:"+rowData);
          }   
          list.add(rowData);
          longtitudes.add(rowData.get("longtitude"));
          latitudes.add(rowData.get("latitude"));
         }   
         List list1 = new ArrayList();
         /*
         System.out.println("list:" + list.toString());   
         System.out.println(list.get(0)); 
         System.out.println(list.get(1)); 
         System.out.println("longtitude"+longtitudes); 
         System.out.println("longtitude"+longtitudes.get(0).getClass());
         System.out.println("latitude"+latitudes); 
         */
         HttpSession sessionlocate1=request.getSession();
         sessionlocate1.setAttribute("longtitude", longtitudes);
         HttpSession sessionlocate2=request.getSession();
         sessionlocate2.setAttribute("latitude", latitudes);
         HttpSession sessioncount=request.getSession();
         sessioncount.setAttribute("count", h);
               	
	%>
	
	<div id="l-map"></div>
	<div id="r-result">
		<input type="button" value="按序显示所有位置" onclick="bdGEO(0)" />
		<div id="result"></div>
	</div>
	
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var hcount=new Array();
	hcount="<%=session.getAttribute("count")%>";
	//alert(hcount[0]);检验数据库行数是否正确	
 	var datalong=new Array();
 
 	datalong=<%=longtitudes%>
 	var datala=new Array();
 	
 	datala=<%=latitudes%>
 	
 	//alert(datalong);检验经度是否正确	
 	//alert(datala);检验纬度是否正确	
	var map = new BMap.Map("l-map");
	map.centerAndZoom(new BMap.Point(datalong[0],datala[0]), 13);//longtitude,latitude
	map.enableScrollWheelZoom(true);
	var index = 0;
	var myGeo = new BMap.Geocoder();

	
	var adds = [];
	for (var i=0;i<hcount[0];i++)
		{adds.push(new BMap.Point(datalong[i],datala[i]))}
	
	for(var i = 0; i<adds.length; i++){
		var marker = new BMap.Marker(adds[i]);
		map.addOverlay(marker);
		marker.setLabel(new BMap.Label("位置信息:"+(i+1),{offset:new BMap.Size(20,-10)}));
	}
	function bdGEO(){	
		var pt = adds[index];
		geocodeSearch(pt);
		index++;
	}
	function geocodeSearch(pt){
		if(index < adds.length-1){
			setTimeout(window.bdGEO,400);
		} 
		myGeo.getLocation(pt, function(rs){
			var addComp = rs.addressComponents;
			document.getElementById("result").innerHTML += index + ". " +adds[index-1].lng + "," + adds[index-1].lat + "："  + "位置(" + rs.business + ")  结构化数据(" + addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber + ")<br/><br/>";
		});
	}
	
	
</script>
