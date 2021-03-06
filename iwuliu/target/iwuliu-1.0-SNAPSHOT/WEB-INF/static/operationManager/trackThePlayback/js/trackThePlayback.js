
//@ sourceURL=truckReplayManager.js
var truckNumber=null;
$(function(){
	truckNumber=sessionStorage.getItem("carNumber");
	if (truckNumber!=undefined ||truckNumber!=null){
		$("#truckNumber").val(truckNumber);
		sessionStorage.clear();
	}
	// 加载当前页面菜单
	$(".leftMenu").load("/iwuliu/welcomeManager/home",function(){
		$(".trackThePlayback").parents(".collapse").addClass("in");
		$(".trackThePlayback").addClass("menuActive");
		roleManager();
	})
	$("#smallModalInfo").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open");
	});
	//获取公司
	$.ajax({
		type:'post',
		url:'/iwuliu/companyManager/companys',
		dataType:'json',
		cache:'false',
		success:function(data){
			var str='';
			for (var i= 0;i<data.length;i++){
				str+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
			}
			$(".logisticsCompany").find(".dropdown-menu").html(str);
			//公司名称点击下拉
			var logisticsCompany = new customDropDown($(".logisticsCompany"));
			$(".logisticsCompany").find(".dropdown-menu li").bind("click",function(){
				var companyName = $(this).attr("value");
				//获取公司下的车辆
				if (companyName!=''&&companyName!='undefined'){
					$.ajax({
						type: "POST",
						url: "/iwuliu/companyManager/oneCompanyTruckList",
						data:{"companyId":companyName},
						cache: false,
						dataType: "json",
						success: function (data) {
							var _str = "";
							for (var i= 0;i<data.length;i++){
								_str+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
							}

							if (_str!=""){
								$(".license").find("input").val(data[0].name);
								$(".license").find(".dropdown-menu").html(_str);
								//车辆牌照点击下拉
								var license = new customDropDown($(".license"));
								$(".license").find(".dropdown-menu>li>a").bind("click",function(){
									$(".license").find(".placeHolder").val($(this).text())
								});
							}else {
								$(".license").find("input").val("");
								$(".license").find(".dropdown-menu").html("");
							}

						}
					});
				}else {
					$("#name").html("<li value><a href='javascript:;'></a></li>");
				}
			})
		},
		error:function(){
			$("#smallModalInfo").modal();
			$("#smallModalInfo").find(".titleInfo").html("获取公司车辆失败！")
			$("#smallModalInfo").on("hidden.bs.modal",function(e){
				$("body").addClass("modal-open");
			});

		}
	});

})

//点击轨迹回放按钮
function playback(){
	obMap.clearInfoWindow();
	var arr = [];
	name=$(".license").find("input").val().replace(/\s+/g + "");
	var beginTime = $("#beginTime").val() ;
	var endTime = $("#endTime").val() ;
	if (beginTime != "" && endTime != "" && name!="") {
		obMap.clearMap();
		arr.push(name);
		arr.push(beginTime);
		arr.push(endTime);
		$.ajax({
			type: "POST",   // post提交方式
			url: '/iwuliu/truckHawkeyeFunctionManager/getPositiones',
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{arr:encodeURI(arr)},
			dataType: "text",
			cache: false,
			error: function (request) {      //  设置表单提交出错
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("系统出错！")
				$("#smallModalInfo").on("hidden.bs.modal",function(e){
					$("body").addClass("modal-open");
				});
			},
			success: function (value) {
				// json 类型的专为对象
				var pp = eval('('+value+')');
				if (pp.list[0].flag=="0"){
					if (pp.list[0].LatLon.length>=20){
						//数组长度为0 清空
						pointes.length = 0;
						plateNumberes.length = 0;
						for (var key in pp) { //第一层循环取到各个list
							var List = pp[key];
							for (var i = 0; i < List[0].LatLon.length; i++) { //第二层循环取list中的对象
								//push 把对象放到了栈底
								pointes.push(new AMap.LngLat(List[0].LatLon[i].longitude,List[0].LatLon[i].latitude));
							}

						}
						plateNumber=pp.list[0].plateNumber;
						personName=pp.list[0].personName;
						phone=	pp.list[0].phone;
						brandName=	pp.list[0].brandName;
						carriageTypeName=	pp.list[0].carriageTypeName;
						polyline = new AMap.Polyline({
							path:pointes,
							strokeColor:"#3366CC",
							strokeOpacity:0.8,
							strokeWeight:6,
							strokeStyle: 'solid',     // 线样式
							strokeDasharray: [10, 5], // 补充线样式
							geodesic: true            // 绘制大地线
						});
						polyline.setMap(obMap);
						//行驶中地图覆盖物
						var   icon = new AMap.Icon({
							image: "/iwuliu/static/common/images/ce_ju.png",
							size: new AMap.Size(52, 26)
						});

						marker = new AMap.Marker({
							map: obMap,
							position: [pointes[pointes.length-1].lng, pointes[pointes.length-1].lat],
							icon: icon,
							offset: new AMap.Pixel(-26, -13),
							autoRotation: true
						});
						//终点标注
						var   icon2 = new AMap.Icon({
							image: "/iwuliu/static/common/images/zhong.png",
							size: new AMap.Size(52, 35)
						});
						var marker2 = new AMap.Marker({
							map: obMap,
							position: [pointes[0].lng, pointes[0].lat],
							icon: icon2,
							offset: new AMap.Pixel(-20, -10),
							autoRotation: true
						});
						marker2.content = "车牌号：" + plateNumber + "<br/>" + "车辆管理者："
								+personName + "<br/>" + "手机号：" + phone + "<br/>"
								+ "车辆品牌：" + brandName + "<br/>" + "车厢类型：" + carriageTypeName;
						marker2.on('click', markerClick);
						obMap.setFitView();
						$(".Start").removeClass("elementDisplayNone");
						$(".stop").addClass("elementDisplayNone");
						$(".continue").addClass("elementDisplayNone");
					}else if (pp.list[0].LatLon.length<20){
						$("#smallModalInfo").modal();
						$("#smallModalInfo").find(".titleInfo").html("当前车辆运行距离比较短！")
						$("#smallModalInfo").on("hidden.bs.modal",function(e){
							$("body").addClass("modal-open");
						});
					}
				}else if(pp.list[0].flag=="2") {
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("车辆不存在！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});

				}else if(pp.list[0].flag=="1") {
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("查询时间段内车辆无数据！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});

				}else if(pp.list[0].flag=="3") {
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("当前车辆没有位置信息！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});

				}
			}
		});
	}else if (beginTime == "" || endTime == "" || name =="" ){
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请填写必要字段！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
		return false;
	}

}


//经纬度数组
var pointes=[];
//车牌号数组
var plateNumberes=[];
//绘制线路
var polyline;
//搜索值
var name;
var plateNumber;
var personName;
var phone;
var brandName;
var carriageTypeName;

//播放
var marker3;
var lineArr;
var speed=6000;
var index=0;
//点击开始动画按钮
function Start(){
	$(".Start").addClass("elementDisplayNone");
	$(".stop").removeClass("elementDisplayNone");
	$(".continue").addClass("elementDisplayNone");
	obMap.clearMap();
	//终点标记
	var   icon2 = new AMap.Icon({
		image: "/iwuliu/static/common/images/zhong.png",
		size: new AMap.Size(52, 35)
	});
	var marker2 = new AMap.Marker({
		map: obMap,
		position: [pointes[0].lng, pointes[0].lat],
		icon: icon2,
		offset: new AMap.Pixel(-20, -10),
		autoRotation: true
	});
	marker2.content = "车牌号：" + plateNumber + "<br/>" + "车辆管理者："
			+personName + "<br/>" + "手机号：" + phone + "<br/>"
			+ "车辆品牌：" + brandName + "<br/>" + "车厢类型：" + carriageTypeName;
	marker2.on('click', markerClick);
	//起点标记
	var   icon5 = new AMap.Icon({
		image: "/iwuliu/static/common/images/qi.png",
		size: new AMap.Size(52, 35)
	});
	var marker5 = new AMap.Marker({
		map: obMap,
		position: [pointes[pointes.length-1].lng, pointes[pointes.length-1].lat],
		icon: icon5,
		offset: new AMap.Pixel(-20, -10),
		autoRotation: true
	});
	marker5.content = "车牌号：" + plateNumber + "<br/>" + "车辆管理者："
			+personName + "<br/>" + "手机号：" + phone + "<br/>"
			+ "车辆品牌：" + brandName + "<br/>" + "车厢类型：" + carriageTypeName;
	marker5.on('click', markerClick);

	x=pointes[0].lng;
	y=pointes[0].lat;
	completeEventHandler(x,y);
	marker3.moveAlong(lineArr,speed);     //开始轨迹回放
	var passedPolyline = new AMap.Polyline({
		map: obMap,
		strokeColor: "#F00",  //线颜色
		strokeWeight:3,
	});

	//移动中
	marker3.on('moving',function(e){
		marker3.on('click', markerTruckClick);
		passedPolyline.setPath(e.passedPath);
		if (flags!=1){
			var ss=marker3.getPosition();
			var geocoder = new AMap.Geocoder({
				radius: 1000,
				extensions: "all"
			});
			var title="车辆信息：";
			var contents = "车牌号：" + plateNumber + "<br/>" + "车辆管理者："
					+personName + "<br/>" + "手机号：" + phone + "<br/>"
					+ "车辆品牌：" + brandName + "<br/>" + "车厢类型：" + carriageTypeName;
			var gg =ss.getLat();
			var ii =ss.getLng();
			var lngslats= [ii,gg];
			//根据经纬度查询地址
			geocoder.getAddress(lngslats, function(status, result) {
				if (status === 'complete' && result.info === 'OK') {
					var address = result.regeocode.formattedAddress; //返回地址描述
					var infoWindow = new AMap.InfoWindow({
						isCustom: true,  //使用自定义窗体
						content: createInfoWindow(title,contents+"<br/>"+address ),
						offset: new AMap.Pixel(16, -45)
					});
					infoWindow.open(obMap, lngslats);
				}
			});
		}

	});
	obMap.setFitView();
}

//点击暂停动画按钮
function stop(){
	$(".Start").addClass("elementDisplayNone");
	$(".stop").addClass("elementDisplayNone");
	$(".continue").removeClass("elementDisplayNone");
	marker3.pauseMove();

}
//点击按钮继续播放
function continueMove(){
	marker3.resumeMove();
	$(".Start").addClass("elementDisplayNone");
	$(".stop").removeClass("elementDisplayNone");
	$(".continue").addClass("elementDisplayNone");
}

//地图
var obMap;
// 全局变量
$(document).ready(function () {
	//时间
	$('#beginTime').datetimepicker({
		format: 'yyyy-mm-dd hh:ii:00',
		language: 'zh-CN',
		autoclose: true,//选中之后自动隐藏日期选择框
		hourStep: 1,
		minuteStep: 30,
		todayBtn:true,
	});
	$('#endTime').datetimepicker({
		format: 'yyyy-mm-dd hh:ii:00',
		autoclose: true,//选中之后自动隐藏日期选择框
		language: 'zh-CN',
		hourStep: 1,
		minuteStep: 30,
		todayBtn:true,
	});
	obMap = new AMap.Map('container', {
		center: [116.397428, 39.90923],
		zoom: 10,
	});
	//加载地图基本控件
	obMap.plugin(["AMap.ToolBar", "AMap.OverView", "AMap.Scale","AMap.TileLayer","AMap.MapType","AMap.Driving","AMap.Geocoder","AMap.convertFrom"],function() {
		//添加工具条
		obMap.addControl(new AMap.ToolBar());

		//比例尺
		obMap.addControl(new AMap.Scale());
		//添加鹰眼
		obMap.addControl(new AMap.OverView({isOpen:true}));
		//实时路况
		obMap.addControl(new AMap.TileLayer.Traffic({
			zIndex: 10
		}));
		//实现默认图层与卫星图、实施交通图层之间切换的控
		obMap.addControl(new AMap.MapType());
		obMap.addControl(new AMap.Driving());
		obMap.addControl(new AMap.Geocoder());
		obMap.addControl(new AMap.convertFrom());
	});

});

//回调函数
function completeEventHandler(x,y){
	marker3 = new AMap.Marker({
		map:obMap,
		draggable:true, //是否可拖动
		position:new AMap.LngLat(x,y),//基点位置
		icon:'http://webapi.amap.com/images/car.png', //marker图标，直接传递地址url
		offset:new AMap.Pixel(-26,-13), //相对于基点的位置
		autoRotation:true,
	});
	var lngX ;
	var latY ;
	lineArr = [];
	for(var i = 0;i<pointes.length;i++){
		lngX = pointes[pointes.length-i-1].lng;
		latY = pointes[pointes.length-i-1].lat;
		lineArr.push(new AMap.LngLat(lngX,latY));
	}
	//绘制轨迹
	var polyline2 = new AMap.Polyline({
		map:obMap,
		path:lineArr,
		strokeColor:"#00A",//线颜色
		strokeOpacity:1,//线透明度
		strokeWeight:3,//线宽
		strokeStyle:"solid",//线样式
	});
}
//起点终点 点击事件获取车辆位置
function markerClick(e){
	flags=1;
	var geocoder = new AMap.Geocoder({
		radius: 1000,
		extensions: "all"
	});
	var title="位置信息：";
	var tt=e;
	//获取当前标注的经纬度
	var ln=tt.target.getPosition().lng;
	var la=tt.target.getPosition().lat;
	var lnglatXY= [ln,la];
	//根据经纬度查询地址
	geocoder.getAddress(lnglatXY, function(status, result) {
		if (status === 'complete' && result.info === 'OK') {
			var address = result.regeocode.formattedAddress; //返回地址描述
			var infoWindow = new AMap.InfoWindow({
				isCustom: true,  //使用自定义窗体
				content: createInfoWindow(title, "地点位置：" +"<br/>"+address ),
				offset: new AMap.Pixel(16, -30)
			});
			infoWindow.open(obMap, tt.target.getPosition());
		}
	});

}


// 点击事件点击事件获取车辆位置
function markerTruckClick(e){
	flags=0;
	//marker3.content = "车牌号：" + plateNumber + "<br/>" + "车辆管理者："
	//		+personName + "<br/>" + "手机号：" + phone + "<br/>"
	//		+ "车辆品牌：" + brandName + "<br/>" + "车厢类型：" + carriageTypeName;
	//var geocoder = new AMap.Geocoder({
	//	radius: 1000,
	//	extensions: "all"
	//});
	//var title="车辆信息：";
	//var tt=e;
	////获取当前标注的经纬度
	//var ln=tt.target.getPosition().lng;
	//var la=tt.target.getPosition().lat;
	//var lnglatXY= [ln,la];
	////根据经纬度查询地址
	//geocoder.getAddress(lnglatXY, function(status, result) {
	//	if (status === 'complete' && result.info === 'OK') {
	//		var address = result.regeocode.formattedAddress; //返回地址描述
	//		var infoWindow = new AMap.InfoWindow({
	//			isCustom: true,  //使用自定义窗体
	//			content: createInfoWindow(title,tt.target.content+"<br/>"+address ),
	//			offset: new AMap.Pixel(16, -30)
	//		});
	//		infoWindow.open(obMap, tt.target.getPosition());
	//	}
	//});
//var geocoder = new AMap.Geocoder({
	//	radius: 1000,
	//	extensions: "all"
	//});
	//var title="车辆信息：";
	//var contents = "车牌号：" + plateNumber + "<br/>" + "车辆管理者："
	//		+personName + "<br/>" + "手机号：" + phone + "<br/>"
	//		+ "车辆品牌：" + brandName + "<br/>" + "车厢类型：" + carriageTypeName;
	//var ss=marker3.getPosition();
	//var gg =ss.getLat();
	//var ii =ss.getLng();
	//var lngslats= [ii,gg];
	////根据经纬度查询地址
	//	geocoder.getAddress(lngslats, function(status, result) {
	//		if (status === 'complete' && result.info === 'OK') {
	//			var address = result.regeocode.formattedAddress; //返回地址描述
	//			var infoWindow = new AMap.InfoWindow({
	//				isCustom: true,  //使用自定义窗体
	//				content: createInfoWindow(title,contents+"<br/>"+address ),
	//				offset: new AMap.Pixel(16, -45)
	//			});
	//			infoWindow.open(obMap, lngslats);
	//		}
	//	});


}
//构建自定义信息窗体
function createInfoWindow(title, content) {
	var info = document.createElement("div");
	info.className = "info";

	//可以通过下面的方式修改自定义窗体的宽高
	// 定义顶部标题
	var top = document.createElement("div");
	var titleD = document.createElement("div");
	var closeX = document.createElement("img");
	var sharp = document.createElement("img");
	closeX.src = "/iwuliu/static/common/images/close2.png";
	closeX.onclick = closeInfoWindow;
	top.className = "info-top";
	titleD.innerHTML =  title;

	top.appendChild(titleD);
	top.appendChild(closeX);
	info.appendChild(top);

	// 定义中部内容
	var middle = document.createElement("div");
	middle.className = "info-middle";
	middle.style.backgroundColor = 'white';
	middle.innerHTML = content;
	info.appendChild(middle);

	// 定义底部内容
	var bottom = document.createElement("div");
	bottom.className = "info-bottom";
	bottom.style.position = 'relative';
	bottom.style.top = '0px';
	bottom.style.margin = '0 auto';
	sharp.src = "/iwuliu/static/common/images/sharp111.png";
	bottom.appendChild(sharp);
	info.appendChild(bottom);
	return info;
}
//关闭信息窗体
var flags=5;//开启车辆信息的标志
function closeInfoWindow() {
	flags=1;
	obMap.clearInfoWindow();
}

