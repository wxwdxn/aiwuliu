//@ sourceURL=vehicleMonitoringManagers.js


//车牌号码验证
var regTicNum=/^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
var longitude=[];
var latitude=[];
var points=[];//经纬度数组
var plateNumberes=[];//车辆牌照数组
var directions=[];//方向数组
var objMap;//地图
var marker;//点图标
var flagList=[];//行驶中1  熄火0
var speedList=[];//速度列表
var phoneList=[];//手机号码列表
var personNameList=[];//管理者姓名

var brandNameList=[];//车辆品牌
var carriageTypeNameList=[];//车厢类型
var alarmList=[];//警情列表
var truckPlates=[];//车牌
var truckList=[];//页面加载时公司行驶中车辆
var alarmTruckList=[];//页面加载时公司报警车辆
var fuelConsumptions=[];//实时油耗
var tirePressures=[];//当前胎压
var r;//车辆实时定时器
var t;//导航栏公司车辆定时器
var zNodes=[];
var zNodesNew;//勾选车辆行驶状态时与获取的数据做对比
$(function(){
	// 加载当前页面菜单
	$(".leftMenu").load("/iwuliu/welcomeManager/home",function(){
		$(".vehicleMonitoring").parents(".collapse").addClass("in");
		$(".vehicleMonitoring").addClass("menuActive");
		roleManager();
	})
	$(".amap-info-close").css("display","none")
	objMap = new AMap.Map('container', {
		center: [116.397428, 39.90923],
		zoom:10
	});
	var infoWindow = new AMap.InfoWindow();
	//加载地图基本控件
	objMap.plugin(["AMap.ToolBar", "AMap.OverView", "AMap.Scale","AMap.TileLayer","AMap.MapType","AMap.Driving","AMap.Geocoder"],function() {
		//添加工具条
		objMap.addControl(new AMap.ToolBar());

		//比例尺
		objMap.addControl(new AMap.Scale());
		//添加鹰眼
		objMap.addControl(new AMap.OverView({isOpen:true}));
		//实时路况
		objMap.addControl(new AMap.TileLayer.Traffic({
			zIndex: 10
		}));
		//实现默认图层与卫星图、实施交通图层之间切换的控
		objMap.addControl(new AMap.MapType());
		objMap.addControl(new AMap.Driving());
		objMap.addControl(new AMap.Geocoder());
	});
	//获取公司车牌号  tree赋值 注意先后顺序
	companyTruck();
	t = setInterval("runTruckCompany()",5000);
	sessionStorage.setItem("clearTimer",t);


	var table = $('#transportationPlanTable'),
			page = $('#transportationPlanTablepage'),
			goBtn = $('#transportationPlanTablegoBtn');
	// 导出
	$('#toolbar').find('select').change(function() {
		table.bootstrapTable('refreshOptions', {
			exportDataType: $(this).val()
		});
	});
	// 跳转到某页
	goBtn.click(function () {
		table.bootstrapTable('selectPage', +page.val());
	});
});


//全屏
function changeSize(){
	$(".changeSize").css("display","none");
	$(".cancelFullSize").css("display","block");
	var _width = document.documentElement.clientWidth;
	var _height= document.documentElement.clientHeight;
	$(".main").css({"margin-left":"0","width":"100%"});
	$(".NavDisplay").css({"display":"none"});
	$(".vehicleMonitoringHeader").css({"display":"none"});
	$(".vehicleMonitoringManager").css({"position":"absolute","left":"0","top":"0","width":_width + "px","height":_height+"px"});
	$(".footerDisplay").css({"display":"none"})
}
//退出全屏
function cancelFullSize(){
	$(".changeSize").css("display","block");
	$(".cancelFullSize").css("display","none")
	$(".main").css({"margin-left":"17%","width":"83%"});
	$(".NavDisplay").css({"display":"block"});
	$(".vehicleMonitoringHeader").css({"display":"block"});
	$(".vehicleMonitoringManager").css({"position":"relative","width":"80%","height":"70rem","float":"left"});
	$(".footerDisplay").css({"display":"block"})
}
var setting = {
	check: {
		enable: true
	},
	data: {
		simpleData: {
			enable: true
		},
		key: {
			title: "t"
		}
	},
	callback: {
		onCheck: zTreeOnCheck
	}
};

//获取经纬度 moveTo 方法
function getLonLat (){
	var checkedName = [];
	tirePressures.length=0;
	fuelConsumptions.length=0;
	plateNumberes.length=0;
	phoneList.length=0;
	personNameList.length=0;
	speedList.length=0;
	carriageTypeNameList.length=0;
	brandNameList.length=0;
	flagList.length=0;
	directions.length=0;
	alarmList.length=0
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var	nodes = treeObj.getCheckedNodes(true);
	for(var i in nodes){
		if(nodes[i].isParent == false){
			checkedName.push(nodes[i].name);
		}
	}
	var plateNumberList=checkedName;
	if (checkedName.length!=0){
		$.ajax({
			type: "POST",   // post提交方式
			url: '/iwuliu/truckHawkeyeFunctionManager/getPositionList',
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{plateNumberList:encodeURI(plateNumberList)},
			cache: false,
			error: function (XMLHttpRequest) {      //  设置表单提交出错
				//判断响应码发送请求
				if (XMLHttpRequest.readyState==0){
					r = setInterval("getLonLat()",5000);
				}
			},
			success: function (data) {
				var obj = eval("(" + data + ")");
				points.length=0;
				for (var key in obj) { //第一层循环取到各个list
					var List = obj[key];
					for (var i=0;i<List.length;i++ ) { //第二层循环取list中的对象
						points.push(new AMap.LngLat(obj.list[i].longitude,obj.list[i].latitude));
						speedList.push(obj.list[i].speed);
						plateNumberes.push(obj.list[i].plateNumber);
						phoneList.push(obj.list[i].phone);
						personNameList.push(obj.list[i].personName);
						brandNameList.push(obj.list[i].brandName);
						flagList.push(obj.list[i].flag);
						carriageTypeNameList.push(obj.list[i].carriageTypeName);
						directions.push(obj.list[i].direction);
						alarmList.push(obj.list[i].alarm);
						tirePressures.push(obj.list[i].tirePressure);
						fuelConsumptions.push(obj.list[i].fuelConsumption);
					}
				}
				objMap.clearMap();//清除覆盖物

				//获取点击时的经纬度 再次打开信息窗口
				var plateNum;
				if (ee!=null&&ee!=undefined){
					//获取显示内容中的车牌
					plateNum= ee.target.content.toString().substring(4,11);
				}
				//多个车辆的定位
				for (var i = 0; i < points.length; i++) {
					if(flagList[i]==2){
						//熄火
						var  icon = new AMap.Icon({
							image: "/iwuliu/static/common/images/down.png",
							size: new AMap.Size(52, 26)
						});
					}else if(flagList[i]==1) {
						//行驶中地图覆盖物
						var icon = new AMap.Icon({
							image: "/iwuliu/static/common/images/normal.png",
							size: new AMap.Size(52, 26)
						});
					}else  {
						//车辆报警
						var icon = new AMap.Icon({
							image: "/iwuliu/static/common/images/cass.png",
							size: new AMap.Size(52, 26)
						});
					}

					marker = new AMap.Marker({
						icon: icon,
						position: [points[i].lng, points[i].lat],
						offset: new AMap.Pixel(-12, -12),
						zIndex: 101,
						map: objMap,
						angle:directions[i]
					});
					marker.content = "车牌号：" + plateNumberes[i] + "<br/>" + "车辆管理者："
							+ personNameList[i] + "<br/>" + "手机号：" + phoneList[i] + "<br/>"
							+ "速度：" + speedList[i] + "<br/>" + "车辆品牌：" + brandNameList[i] + "<br/>" + "车厢类型：" + carriageTypeNameList[i]
							+"<br/>"+"车辆报警："+alarmList[i]+"<br/>"+"实时油耗："+fuelConsumptions[i]+"<br/>"+"当前胎压："+tirePressures[i];

					//获取每个车辆的经纬度对象
					marker.on('click', markerClick);
					if (plateNum==plateNumberes[i]){
						//自动赋值显示内容
						var text="车牌号：" + plateNumberes[i] + "<br/>" + "车辆管理者："
								+ personNameList[i] + "<br/>" + "手机号：" + phoneList[i] + "<br/>"
								+ "速度：" + speedList[i] + "<br/>" + "车辆品牌：" + brandNameList[i] + "<br/>" + "车厢类型：" + carriageTypeNameList[i]
								+"<br/>"+"车辆报警："+alarmList[i]+"<br/>"+"实时油耗："+fuelConsumptions[i]+"<br/>"+"当前胎压："+tirePressures[i];

						var geocoder = new AMap.Geocoder({
							radius: 1000,
							extensions: "all"
						});
						var title="车辆信息：";

						//获取当前标注的经纬度
						ln=points[i].lng;
						la=points[i].lat;
						lnglatXY= [ln,la];
						//根据经纬度查询地址
						geocoder.getAddress(lnglatXY, function(status, result) {
							if (status === 'complete' && result.info === 'OK') {
								var address = result.regeocode.formattedAddress; //返回地址描述
								var infoWindow = new AMap.InfoWindow({
									isCustom: true,  //使用自定义窗体
									content: createInfoWindow(title,text+"<br/>"+address+"<br/>"+"<a class='btn detailClass' onclick='truckDetails()'>查看详情&gt;&gt;</a>" ),
									offset: new AMap.Pixel(16, -45)
								});
								infoWindow.open(objMap, lnglatXY);
							}
						});
					}
				}
			}
		})
	}else{
		objMap.clearMap();
		clearInterval(r);
	}

	//--以下 公司所有车辆图片刷新
	runTruckCompany();

}
//动态获取树形结构的值
function zTreeOnCheck() {
	var checkedName = [];
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var	nodes = treeObj.getCheckedNodes(true);
	for(var i in nodes){
		if(nodes[i].isParent == false){
			checkedName.push(nodes[i].name)
		}
	}
	var plateNumberList=checkedName;
	ee=null;
	//清除地图覆盖物
	objMap.clearMap();
	clearInterval(r);
	clearInterval(t);
	if (checkedName.length!=0){
		$.ajax({
			type: "POST",   // post提交方式
			url: '/iwuliu/truckHawkeyeFunctionManager/getPositionList',
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{plateNumberList:encodeURI(plateNumberList)},
			cache: false,
			error: function (request) {      //  设置表单提交出错
				//判断响应码发送请求
				if (XMLHttpRequest.readyState==0){
					t = setInterval("runTruckCompany()",5000);
					sessionStorage.setItem("clearTimer",t);
				}
			},
			success: function (data) {
				// json 类型的专为对象
				var obj = eval("(" + data + ")");
				points.length=0;
				tirePressures.length=0;
				fuelConsumptions.length=0;
				plateNumberes.length=0;
				phoneList.length=0;
				personNameList.length=0;
				speedList.length=0;
				carriageTypeNameList.length=0;
				brandNameList.length=0;
				flagList.length=0;
				directions.length=0;
				alarmList.length=0;
				//多个车辆
				if (obj.list.length > 1){
					for (var key in obj) { //第一层循环取到各个list
						var List = obj[key];
						for (var i=0;i<List.length;i++ ) { //第二层循环取list中的对象
							points.push(new AMap.LngLat(obj.list[i].longitude,obj.list[i].latitude));
							speedList.push(obj.list[i].speed);
							plateNumberes.push(obj.list[i].plateNumber);
							phoneList.push(obj.list[i].phone);
							personNameList.push(obj.list[i].personName);
							brandNameList.push(obj.list[i].brandName);
							flagList.push(obj.list[i].flag);
							carriageTypeNameList.push(obj.list[i].carriageTypeName);
							directions.push(obj.list[i].direction);
							alarmList.push(obj.list[i].alarm);
							tirePressures.push(obj.list[i].tirePressure);
							fuelConsumptions.push(obj.list[i].fuelConsumption);
						}
					}
					//多个车辆的定位
					for (var i = 0; i < points.length; i++) {
						//地图覆盖物
						if(flagList[i]==2){
							//熄火
							var  icon = new AMap.Icon({
								image: "/iwuliu/static/common/images/down.png",
								size: new AMap.Size(52, 26)
							});
						}else if(flagList[i]==1) {
							//行驶中地图覆盖物
							var icon = new AMap.Icon({
								image: "/iwuliu/static/common/images/normal.png",
								size: new AMap.Size(52, 26)
							});
						}else  {
							//车辆报警
							var icon = new AMap.Icon({
								image: "/iwuliu/static/common/images/cass.png",
								size: new AMap.Size(52, 26)
							});
						}
						var marker = new AMap.Marker({
							icon: icon,
							position: [points[i].lng,points[i].lat],
							offset: new AMap.Pixel(-12,-12),
							zIndex: 101,
							map: objMap,
							angle:directions[i]
						});
						marker.content="车牌号："+obj.list[i].plateNumber+"<br/>"+"车辆管理者："
								+obj.list[i].personName+"<br/>"+"手机号："+obj.list[i].phone+"<br/>"
								+"速度：" +obj.list[i].speed+"<br/>"+"车辆品牌："+obj.list[i].brandName+"<br/>"+"车厢类型："+obj.list[i].carriageTypeName
								+"<br/>"+"车辆报警："+obj.list[i].alarm+"<br/>"+"实时油耗："+obj.list[i].fuelConsumption+"<br/>"+"当前胎压："+obj.list[i].tirePressure;
						//视图适中
						objMap.setFitView();
						//获取每个车辆的经纬度对象

						marker.on('click', markerClick);

					}
					//一个车辆
				}else if (obj.list.length == 1) {
					//在指定的容器内创建地图实例
					objMap = new AMap.Map('container', {
						center: [obj.list[0].longitude, obj.list[0].latitude],
						zoom: 10
					});
					//初始化地图，设置中心点坐标和地图级别
					//地图覆盖物
					if(obj.list[0].flag==2){
						//熄火
						var   icon = new AMap.Icon({
							image: "/iwuliu/static/common/images/down.png",
							size: new AMap.Size(52, 26)
						});
					}else if(obj.list[0].flag==1) {
						//行驶中地图覆盖物
						var icon = new AMap.Icon({
							image: "/iwuliu/static/common/images/normal.png",
							size: new AMap.Size(52, 26)
						});
					}else  {
						//车辆报警
						var icon = new AMap.Icon({
							image: "/iwuliu/static/common/images/cass.png",
							size: new AMap.Size(52, 26)
						});
					}
					marker = new AMap.Marker({
						icon:icon,
						position: [obj.list[0].longitude, obj.list[0].latitude],
						offset: new AMap.Pixel(-12,-12),
						zIndex: 101,
						map: objMap,
						angle:obj.list[0].direction
					});
					marker.content="车牌号："+obj.list[0].plateNumber+"<br/>"+"车辆管理者："
							+obj.list[0].personName+"<br/>"+"手机号："+obj.list[0].phone+"<br/>"
							+"速度：" +obj.list[0].speed+"<br/>"+"车辆品牌："+obj.list[0].brandName+"<br/>"+"车厢类型："+obj.list[0].carriageTypeName
							+"<br/>"+"车辆报警："+obj.list[0].alarm+"<br/>"+"实时油耗："+obj.list[0].fuelConsumption+"<br/>"+"当前胎压："+'正常';

					marker.on('click', markerClick);
				}else{
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("当前车辆没有数据！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});
				}

				r = setInterval("getLonLat()",5000);
				sessionStorage.setItem("getLonLat",r)
			}
		});
	}else{
		objMap.clearMap();
		t = setInterval("runTruckCompany()",5000);
		sessionStorage.setItem("clearTimer",t);
	}
}
var ee;
var carNumber;
function markerClick(e){
	var geocoder = new AMap.Geocoder({
		radius: 1000,
		extensions: "all"
	});
	var title="车辆信息：";
	ee=e;
	carNumber=ee.target.content.substring(4,11);
	//获取当前标注的经纬度
	ln=ee.target.getPosition().lng;
	la=ee.target.getPosition().lat;
	lnglatXY= [ln,la];
	//根据经纬度查询地址
	geocoder.getAddress(lnglatXY, function(status, result) {
		if (status === 'complete' && result.info === 'OK') {
			var address = result.regeocode.formattedAddress; //返回地址描述
			var infoWindow = new AMap.InfoWindow({
				isCustom: true,  //使用自定义窗体
				content: createInfoWindow(title,ee.target.content+"<br/>"+address+"<br/>"+"<a class='btn detailClass' onclick='truckDetails()'>查看详情&gt;&gt;</a>" ),
				offset: new AMap.Pixel(16, -45)
			});
			infoWindow.open(objMap, ee.target.getPosition());
		}
	});
}
//模糊查询
function findEach(){
	var sFind = document.getElementById("name").value;
	if(sFind!=""){
		var nPos;
		var vResult = [];
		for(var i in zNodes){
			var sTxt=zNodes[i].name||'';
			nPos = find(sFind, sTxt);
			if(nPos>=0){
				vResult[vResult.length] = sTxt;
			}
		}
		if(vResult.length != 0){
			var z = [];
			for (var i in vResult) {
				for (var j in zNodes) {
					if(zNodes[j].name == vResult[i]){
						z.push(zNodes[j]);
						for (var n in zNodes) {
							if(zNodes[j].pId == zNodes[n].id){
								z.push(zNodes[n]);
							}
						}
					}
				}
			}
			var hash = {};
			z = z.reduce(function(item, next) {
				hash[next.id] ? '' : hash[next.id] = true && item.push(next);
				return item
			}, []);
			$.fn.zTree.init($("#treeDemo"), setting, z);
		}
	}else{
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	}
	var _as = $("a");
	for(var i =0;i<_as.length;i++){
		if($(_as[i]).attr("title")){
			if($(_as[i]).attr("title") ==2){//不在线车辆
				$(_as[i]).find(".button").css({"background":"url('/iwuliu/static/common/css/img/hui.png') no-repeat center"});
				$(_as[i]).find(".button").css({"background-position":"0 0"})
			}else if($(_as[i]).attr("title") == 1){//行驶车辆
				$(_as[i]).find(".button").css({"background":"url('/iwuliu/static/common/css/img/xiaolv.png') no-repeat center"});
				$(_as[i]).find(".button").css({"background-position":"0 0"});
			}else if($(_as[i]).attr("title") == 3){
				$(_as[i]).find(".button").css({"background":"url('iwuliu/static/common/images/case.png') no-repeat center"});
				$(_as[i]).find(".button").css({"background-position":"0 0"});
			}
		}
	}
}
function find(sFind, sObj) {
	var nSize = sFind.length;
	var nLen = sObj.length;
	var sCompare;
	if(nSize <= nLen ){
		for(var i = 0; i <= nLen - nSize + 1; i++){
			sCompare = sObj.substring(i, i + nSize);
			if(sCompare == sFind){
				return i;
			}
		}
	}
	return -1;
}


function myFunction() {
	objMap.clearMap();
	//去空后的车牌
	var name=$('#name').val().replace(/\s+/g + "");
	var zNodes=[];
	$.ajax({
		type: "POST",   // post提交方式
		url: '/iwuliu/truckHawkeyeFunctionManager/getPosition',
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		data:{plate_number:encodeURI(name)},
		cache: false,
		error: function (request) {      //  设置表单提交出错
			return false;
		},
		success: function (data) {
			var obj = eval("(" + data + ")");

			for (var i=0; i < obj.list.length; i++) {
				var zNodeIn = "";
				//拼接公司 和车辆
				zNodeIn ='{ "id":"'+i+ '","pId":"'+i +'","name":'+'"'+obj.list[i].name+'"'+', "open":"true"}';
				zNodeIn = JSON.parse( zNodeIn);
				zNodes.push(zNodeIn);
				//公司有车辆
				if (obj.list[i].listNumber!=null){
					for (var k=0; k < obj.list[i].listNumber.length; k++){
						zNodeIn = '{ "id":"'+i+k+ '","pId":"'+i+'","name":'+'"'+obj.list[i].listNumber[k].plateNumber+'"'+'}';
						zNodeIn = JSON.parse( zNodeIn);
						zNodes.push(zNodeIn);
					}
				}
			}

			//异步设置tree的名称
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}
	});
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
function closeInfoWindow() {
	objMap.clearInfoWindow();
	ee=null;
}

//当不勾选时只显示公司导航栏 拼接公司 和车辆
function companyTruck(){
	//获取公司车牌号  tree赋值
	$.ajax({
		type:"post",
		url:"/iwuliu/companyManager/companyTruckList",
		dateType:"json",
		cache: false,
		success:function(data){
			var obj = eval("(" + data + ")");
			zNodesNew = obj;
			for (var i=0; i < obj.list.length; i++) {
				var zNodeIn = "";
				//拼接公司 和车辆
				zNodeIn ='{ "id":"'+i+ '","pId":"'+i +'","name":'+'"'+obj.list[i].name+'"'+', "open":"true"}';
				zNodeIn = JSON.parse( zNodeIn);
				zNodes.push(zNodeIn);
				//公司有车辆
				if (obj.list[i].listNumber!=null){
					for (var k=0; k < obj.list[i].listNumber.length; k++){
						zNodeIn = '{ "id":"'+i+k+ '","pId":"'+i+'","name":'+'"'+obj.list[i].listNumber[k].plateNumber+'","t":"'+ obj.list[i].listNumber[k].flag +'"}';
						zNodeIn = JSON.parse( zNodeIn);
						zNodes.push(zNodeIn);
						truckPlates.push(obj.list[i].listNumber[k].plateNumber);
						if(obj.list[i].listNumber[k].flag==1){
							truckList.push(obj.list[i].listNumber[k].plateNumber);//加载页面时公司行驶中的车辆
						}else if(obj.list[i].listNumber[k].flag==3){
							alarmTruckList.push(obj.list[i].listNumber[k].plateNumber)//加载页面时公司报警的车辆
						}
					}
				}

			}
			//异步设置tree的名称
			var treeObj =  $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			var _as = $("a");
			for(var i =0;i<_as.length;i++){
				if($(_as[i]).attr("title")){
					if($(_as[i]).attr("title") == 2){//不在线车辆
						$(_as[i]).find(".button").css({"background":"url('/iwuliu/static/common/css/img/hui.png') no-repeat center"});
						$(_as[i]).find(".button").css({"background-position":"0 0"})
					}else if($(_as[i]).attr("title") == 1){//行驶车辆
						$(_as[i]).find(".button").css({"background":"url('/iwuliu/static/common/css/img/xiaolv.png') no-repeat center"});
						$(_as[i]).find(".button").css({"background-position":"0 0"});
					}else if($(_as[i]).attr("title") == 3){//报警车辆
						$(_as[i]).find(".button").css({"background":"url('/iwuliu/static/common/images/xxx.png') no-repeat center"});
						$(_as[i]).find(".button").css({"background-position":"0 0"});
					}
				}
			}
			var carStatics = [];
			$(".checkbox").bind("click",function(){
				carStatics.length=0;
				var _isCheck = $(this).attr("isCheck");
				if(_isCheck == "false"){
					$(this).attr("isCheck","true");
					if($(this).attr("value") == "0"){
						$(".checkbox").attr("isCheck","true");
						$(".checkbox").removeClass("glyphicon-unchecked");
						$(".checkbox").addClass("glyphicon-check");
						carStatics = []
						for(var i = 1;i<$(".checkbox").length;i++){
							carStatics.push($($(".checkbox")[i]).attr("value"));
						}
					}else{
						$(this).removeClass("glyphicon-unchecked");
						$(this).addClass("glyphicon-check");
						carStatics.push($(this).attr("value"));
					}
				}else{
					$(this).attr("isCheck","false");
					if($(this).attr("value") == "0"){
						$(".checkbox").attr("isCheck","false");
						$(".checkbox").addClass("glyphicon-unchecked");
						$(".checkbox").removeClass("glyphicon-check");
						carStatics = [];
					}else{
						$(".allVehicles").attr("isCheck","false");
						$(".allVehicles").addClass("glyphicon-unchecked");
						$(".allVehicles").removeClass("glyphicon-check");
						$(this).addClass("glyphicon-unchecked");
						$(this).removeClass("glyphicon-check");
						carStatics.remove($(this).attr("value"));
					}
				}
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
				if(carStatics.length != 0){
					if(carStatics.length == 1){
						function filter(node) {
							return (node.t == carStatics[0]);
						}
					}else{
						for(var i = 0;i<carStatics.length;i++){
							var filterReason = ''
							if(i != carStatics.length-1){
								filterReason += "node.t =="+carStatics[i]+"||"
							}else{
								filterReason += "node.t =="+carStatics[i];
							}
						}
						function filter(node) {
							return (filterReason);
						}
					}
					//取消全部选中的节点
					treeObj.checkAllNodes(false);
					var nodes = treeObj.getNodesByFilter(filter); // 查找节点集合
					for (var i=0, l=nodes.length; i < l; i++) {
						treeObj.checkNode(nodes[i], true, true);
					}
					zTreeOnCheck();
				}else{
					//取消全部选中的节点
					treeObj.checkAllNodes(false);
					zTreeOnCheck();
				}
			});
		},
		error:function(){
			alert('There are some errors happened');
		}

	});

}

var runTruck=[];//运行车辆
var alarmCars=[];//报警车辆
var stopCars=[];//熄火车辆
//当行驶车辆发生变化时回调
function runTruckCompany(){
	//-----------以下 公司所有车辆图片刷新
	$.ajax({
		type: "POST",   // post提交方式
		url: '/iwuliu/truckHawkeyeFunctionManager/getPositionList',
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		data:{plateNumberList:encodeURI(truckPlates)},
		cache: false,
		dataType : 'json',
		error: function (XMLHttpRequest) {      //  设置表单提交出错
			//判断响应码发送请求
			if (XMLHttpRequest.readyState==0){
				t = setInterval("runTruckCompany()",5000);
				sessionStorage.setItem("clearTimer",t);
			}
		},
		success: function (data) {
			var obj = eval(data);
			runTruck.length=0;
			alarmCars.length=0;
			stopCars.length=0;
			for (var key in obj) { //第一层循环取到各个list
				var List = obj[key];
				for (var i = 0; i < List.length; i++) { //第二层循环取list中的对象
					var flag= obj.list[i].flag;
					if (flag==1){
						//实时车辆
						runTruck.push(obj.list[i].plateNumber);
					}else if (flag==3){//报警车辆
						alarmCars.push(obj.list[i].plateNumber);
					}else if (flag==2){//熄火车辆
						stopCars.push(obj.list[i].plateNumber);
					}
				}
			}

			var nowlength=runTruck.length+alarmCars.length;
			var oldLength=truckList.length+ alarmTruckList.length;
			//根据实时车辆和旧的车辆对比 注意顺序
			if(oldLength!= nowlength|| alarmCars.length!=alarmTruckList.length){
				var _as = $("a");
				for(var i =0;i<_as.length;i++){
					if($(_as[i]).attr("title")){
						for(var j=0;j<obj.list.length;j++){
							if($(_as[i]).context.innerText==runTruck[j]&& regTicNum.test($(_as[i]).context.innerText)){//行驶车辆
								$(_as[i]).attr("title","1")
								$(_as[i]).find(".button").css({"background":"url('/iwuliu/static/common/css/img/xiaolv.png') no-repeat center"});
								$(_as[i]).find(".button").css({"background-position":"0 0"});
								break;
							}else if($(_as[i]).context.innerText==alarmCars[j] && regTicNum.test($(_as[i]).context.innerText)) {//报警车辆
								$(_as[i]).attr("title","3")
								$(_as[i]).find(".button").css({"background":"url('/iwuliu/static/common/images/xxx.png') no-repeat center"});
								$(_as[i]).find(".button").css({"background-position":"0 0"});
								break;
							}else if($(_as[i]).context.innerText==stopCars[j] && regTicNum.test($(_as[i]).context.innerText)) {//熄火车辆
								$(_as[i]).attr("title","2")
								$(_as[i]).find(".button").css({"background":"url('/iwuliu/static/common/css/img/hui.png') no-repeat center"});
								$(_as[i]).find(".button").css({"background-position":"0 0"});
								break;
							}
						}
					}
				}
				truckList.length=runTruck.length;
				alarmTruckList.length=alarmCars.length;

			}
		}
	});
}


//图片放大
function ImgBig(ele){
	var _src = $(ele).attr("src");
	$(".modalImg").html("<img src="+ _src +"/>");
	$('#ModalImg').modal();
	//图片放大模态框隐藏后触发的事件
	$("#ModalImg").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open")
	})
}
//点击详情获取车辆详情模态框
function truckDetails(){
	//获取车牌号
	if (regTicNum.test(carNumber)){
		$.ajax({
			url:'/iwuliu/truckHawkeyeFunctionManager/getTruckInfo',
			type:"post",
			dataType:'json',
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{carNumber:encodeURI(carNumber)},
			cache:false,
			success:function(data){
				var obj = eval(data);
				var carNumbers= obj.list.plateNumber;
				$("#carNumber").val(carNumbers);
				var brandName= obj.list.brandName;
				$("#brandName").val(brandName);
				var carriageTypeName= obj.list.carriageTypeName;
				$("#carriageTypeName").val(carriageTypeName);
				var typeName= obj.list.typeName;
				$("#typeName").val(typeName);
				var fuleName= obj.list.fuleName;
				$("#fuleName").val(fuleName);
				var lengthName= obj.list.lengthName;
				$("#lengthName").val(lengthName);
				var load_weight= obj.list.load_weight;
				$("#load_weight").val(load_weight);
				var vehicle_identify_number= obj.list.vehicle_identify_number;
				$("#vehicle_identify_number").val(vehicle_identify_number);
				var engine_number= obj.list.engine_number;
				$("#engine_number").val(engine_number);
				var firstPageSavePath= obj.list.firstPageSavePath;
				var secondPageSavePath= obj.list.secondPageSavePath;
				var truckFirstPicSavePath= obj.list.truckFirstPicSavePath;
				var truckSecondPicSavePath= obj.list.truckSecondPicSavePath;
				var truckThirdPicSavePath= obj.list.truckThirdPicSavePath;
				$("#firstPageSavePath").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl="+ firstPageSavePath +">");
				$("#secondPageSavePath").html("<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl="+ secondPageSavePath +">");
				$("#truckPic").html("<span><img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl="+ truckFirstPicSavePath +">"+
						"<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl="+ truckSecondPicSavePath +">"+
						"<img onclick='ImgBig(this)' src=/iwuliu/contentManager/contentDownload?fileUrl="+ truckThirdPicSavePath +"></span>");

				var  lineLength= obj.list.list.length;
				if (lineLength!=0){
					var str='';
					for(var i = 0;i<lineLength ;i++){
						var line=obj.list.list[i].lineName;
						str+=line+"<span></span><span></span>";
					}
				}
				$("#lineName").html(str);
				//table设置刷新
				$('#transportationPlanTable').bootstrapTable('refresh',{'url':'/iwuliu/truckHawkeyeFunctionManager/getTruckSheetInfo?carNumber='+carNumber});
			},

			error:function(){
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("查询失败！")
				$("#smallModalInfo").on("hidden.bs.modal",function(e){
					$("body").addClass("modal-open");
				});
			}

		})
	}
	$("#myModal").modal();
}


// table表格隔行变色
function rowStyle(row, index) {
	if (index % 2 === 0) {
		return{};
	}
	return {
		css: {"background-color": "#eefff9"}
	};
}

//合同配置参数
function transportationContractParams(params) {
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageSize: params.limit,   //页面大小
		pageNumber: 5,  //页码
		sort: params.sort,  //排序列名
		sortOrder: params.order//排位命令（desc，asc）
	};
	return temp;
}

//跳转车辆轨迹回放页面
function truckReplay(){
	window.location.href ="/iwuliu/operationWelcome/truckReplay_home";
	sessionStorage.setItem("carNumber",carNumber);
}