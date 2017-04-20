//数字验证
var numbers= /^\+?[1-9][0-9]*$/;
var objMap;//地图获取经纬度
//获取省市县
var _strValue='';//省级下拉
var _strd='';//市级下拉
var _strsarea = '';//县级下拉
$(function(){
	$(".leftMenu").load("/iwuliu/welcomeManager/home",function(){
		$(".freightYardManager").parents(".collapse").addClass("in");
		$(".freightYardManager").addClass("menuActive");
		roleManager();
	})
	$("#smallModalInfo").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open");
	});
	//时间
	$('.clockpicker').clockpicker();
	//翻页
	var table = $('#freightYard'),
			page = $('#freightYardpage'),
			goBtn = $('#freightYardgoBtn');
	//go按钮加载
	goBtn.click(function () {
		table.bootstrapTable('selectPage', +page.val());
	});
	//合同时间
	//timeChoose($('.startDate'),$('.endtDate'),$('.start_end'),$('.start_date'));
//时间函数 end_Date
//	function timeChoose(startTime,endTime,start_date,end_date){
//		startTime.datetimepicker({
//			language: 'zh-CN',
//			autoclose: true,//选中之后自动隐藏日期选择框
//			hourStep: 1,
//			minuteStep: 30,
//			todayBtn:true,
//			format: 'hh:ii',
//			startView:1,
//			minView:'hour',
//			keyboardNavigation:false,
//		});
//		endTime.datetimepicker({
//			language: 'zh-CN',
//			autoclose: true,//选中之后自动隐藏日期选择框
//			hourStep: 1,
//			minuteStep: 30,
//			todayBtn:true,
//			format: 'hh:ii',
//			startView:1,
//			minView:'hour',
//			keyboardNavigation:false,
//		});
//		start_date.datetimepicker({
//			language: 'zh-CN',
//			autoclose: true,//选中之后自动隐藏日期选择框
//			hourStep: 1,
//			minuteStep: 30,
//			todayBtn:true,
//			format: 'hh:ii',
//			startView:1,
//			minView:'hour',
//			keyboardNavigation:false,
//		});
//		end_date.datetimepicker({
//			language: 'zh-CN',
//			autoclose: true,//选中之后自动隐藏日期选择框
//			hourStep: 1,
//			minuteStep: 30,
//			todayBtn:true,
//			format: 'hh:ii',
//			startView:1,
//			minView:'hour',
//			keyboardNavigation:false,
//		});
//	}


	//新增时获取地图
	objMap= new AMap.Map('container', {
		resizeEnable: true,
		zoom:11,
		center: [116.397428, 39.90923]

	});
	//加载地图基本控件
	objMap.plugin(["AMap.ToolBar", "AMap.OverView", "AMap.Scale", "AMap.TileLayer", "AMap.MapType", "AMap.Driving", "AMap.Geocoder"], function () {
		//添加工具条
		objMap.addControl(new AMap.ToolBar());
		//比例尺
		objMap.addControl(new AMap.Scale());
		//添加鹰眼
		objMap.addControl(new AMap.OverView({isOpen: true}));
		//实时路况
		objMap.addControl(new AMap.TileLayer.Traffic({
			zIndex: 10
		}));
		//实现默认图层与卫星图、实施交通图层之间切换的控
		objMap.addControl(new AMap.MapType());
		objMap.addControl(new AMap.Driving());
		objMap.addControl(new AMap.Geocoder());
	});
	//	查询时获取省市县
	$.ajax({
		type:'post',
		url:"/iwuliu/dictionaryManager/provinceJson",
		dataType:'json',
		cache:false,
		success:function(data){
			for (var i= 0;i<data.length;i++){
				_strValue+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
			}
			$(".provinceJson").find(".dropdown-menu").html(_strValue);
			var provinceJsonList = new customDropDown($(".provinceJson"));
			$(".provinceJson").find(".placeHolder").html('请选择');
			$(".areaJson").find(".placeHolder").html('请选择');
			$(".cityJson").find(".placeHolder").html('请选择');

			$(".provinceJson").find(".dropdown-menu li").bind("click",function(){
				var province = $(this).attr("value");
				$(".cityJson").find(".placeHolder").html('请选择');
				$(".areaJson").find(".placeHolder").html('请选择');
				if (province!=''||province!='undefined'){
					//获取城市
					$.ajax({
						type: "POST",
						url: "/iwuliu/dictionaryManager/cityJson",
						cache: false,
						data:{"dicdata_code":province},
						dataType: "json",
						success: function (data) {
							_strd='';
							for (var i= 0;i<data.length;i++){
								_strd+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
							}
							$(".cityJson").find(".dropdown-menu").html(_strd);
							var cityJsonList = new customDropDown($(".cityJson"));
							$(".cityJson").find(".dropdown-menu li").bind("click",function(){
								var cityJson = $(this).attr("value");
								//获取卸货货场
								$.ajax({
									type: "POST",
									url: "/iwuliu/dictionaryManager/areaJson" ,
									cache: false,
									data:{"dicdata_code":cityJson},
									dataType: "json",
									success: function (data) {
										_strsarea='';
										for (var i= 0;i<data.length;i++){
											_strsarea+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
										}
										$(".areaJson").find(".dropdown-menu").html(_strsarea);
										var areaJsonList = new customDropDown($(".areaJson"));

									},
									error:function(){
										$("#smallModalInfo").modal();
										$("#smallModalInfo").find(".titleInfo").html("系统错误！")
										$("#smallModalInfo").on("hidden.bs.modal",function(e){
											$("body").addClass("modal-open");
										});
									}
								});
							})
						}
					})
				}
			})
		},
		error:function(){
			$("#smallModalInfo").modal();
			$("#smallModalInfo").find(".titleInfo").html("系统错误！")
			$("#smallModalInfo").on("hidden.bs.modal",function(e){
				$("body").addClass("modal-open");
			});
		}
	});
	//新增 时获取省市县
	$.ajax({
		type:'post',
		url:"/iwuliu/dictionaryManager/provinceJson",
		dataType:'json',
		cache:false,
		success:function(data){
			var	_strValues='';
			for (var i= 0;i<data.length;i++){
				_strValues+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
			}
			$(".provinceJsons").find(".dropdown-menu").html(_strValues);
			var provinceJsonList = new customDropDown($(".provinceJsons"));
			$(".provinceJsons").find(".placeHolder").html('请选择');
			$(".areaJsons").find(".placeHolder").html('请选择');
			$(".cityJsons").find(".placeHolder").html('请选择');

			$(".provinceJsons").find(".dropdown-menu li").bind("click",function(){
				var province = $(this).attr("value");
				$(".cityJsons").find(".placeHolder").html('请选择');
				$(".areaJsons").find(".placeHolder").html('请选择');
				if (province!=''||province!='undefined'){
					//获取城市
					$.ajax({
						type: "POST",
						url: "/iwuliu/dictionaryManager/cityJson",
						cache: false,
						data:{"dicdata_code":province},
						dataType: "json",
						success: function (data) {
							_strd='';
							for (var i= 0;i<data.length;i++){
								_strd+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
							}
							$(".cityJsons").find(".dropdown-menu").html(_strd);
							var cityJsonList = new customDropDown($(".cityJsons"));
							$(".cityJsons").find(".dropdown-menu li").bind("click",function(){
								var cityJson = $(this).attr("value");
								//获取卸货货场
								$.ajax({
									type: "POST",
									url: "/iwuliu/dictionaryManager/areaJson" ,
									cache: false,
									data:{"dicdata_code":cityJson},
									dataType: "json",
									success: function (data) {
										_strsarea='';
										for (var i= 0;i<data.length;i++){
											_strsarea+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
										}
										$(".areaJsons").find(".dropdown-menu").html(_strsarea);
										var areaJsonList = new customDropDown($(".areaJsons"));

									},
									error:function(){
										$("#smallModalInfo").modal();
										$("#smallModalInfo").find(".titleInfo").html("系统错误！")
										$("#smallModalInfo").on("hidden.bs.modal",function(e){
											$("body").addClass("modal-open");
										});
									}
								});
							})
						}
					})
				}
			})
		},
		error:function(){
			$("#smallModalInfo").modal();
			$("#smallModalInfo").find(".titleInfo").html("系统错误！")
			$("#smallModalInfo").on("hidden.bs.modal",function(e){
				$("body").addClass("modal-open");
			});
		}
	});

	//详情模态框消失
	$("#myModal").on("hidden.bs.modal",function(){
		$("#myModal").find(".form-group").addClass("uneditable");
		$("#myModal").find("input").attr("disabled","true");
		$("#myModal").find(".form-group").find("button").attr("disabled","true");
		$("#myModal").find(".edit").attr("isClick","true");
		$("#myModal").find(".edit").removeAttr("disabled");
		$("#myModal").find(".edit").css("background","#69a2b5");
		$(".matchGoodType").remove();
		$("#myModal").find(".modalFooter").addClass("elementDisplayNone");
		$("#detailBeginTime").find("span").addClass("hidden");
		$("#detailEndTime").find("span").addClass("hidden");
		$("#detailUnloadendTime").find("span").addClass("hidden");
		$("#detailUnloadBeginTime").find("span").addClass("hidden");

	})
	//点击编辑按钮
	$(".edit").bind("click",function(){
		$("#myModal").find(".hidden").removeClass("hidden")
	})
	//新增模态框消失
	$("#myModalNewAdd").on("hidden.bs.modal",function(){
		$("#myModalNewAdd").find("input").val("");
		$("#myModalNewAdd").find("p").html("");
		$("#myModalNewAdd").find(".placeHolder").html("请选择");
	})


})
//保存 更新
function ipdateFreightYard(){
	var freightYardById=$('#freightYard').bootstrapTable('getSelections')[0].freightYardId;
	var _inputs = $(".update").find("input");
	var _inputLength = $(".update").find("input").length;
	var _goodTypeInfo = {};
	for(var i = 0;i<_inputLength;i++){
		var _addAttr =_inputs[i].getAttribute("addAttr");
		_goodTypeInfo[_addAttr] = _inputs[i].value;
	}
	_goodTypeInfo["freightYardId"]=freightYardById;
	var  stringObj=JSON.stringify(_goodTypeInfo);
	//验证非空字段
	if(checkNotNull($("#CargoName"))&checkNotNull($("#town"))&checkNotNull($("#beginTime"))&checkNotNull($("#endTime"))&checkNumber($("#loadCosts"))&checkNumber($("#loadPumpCosts"))
			&checkNotNull($("#unloadBeginTime"))&checkNotNull($("#unloadendTime"))&checkNumber($("#loadPerDay"))&checkNumber($("#unloadCosts"))&checkNumber($("#unloadPumpCosts"))&checkNumber($("#unloadPerDay")))
	{
		$.ajax({
			type:"post",
			url:"/iwuliu/freightYardManager/updateFreightYard",
			data:{"list":stringObj},
			async:true,
			success:function(data){
				var obj = eval(data);
				if(obj.result==1){
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("保存成功！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
						$("#myModal").modal('hide')
						$('#freightYard').bootstrapTable('refresh');
					});
				}else{
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("保存失败！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});
				}
			},
			error:function(){
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("系统错误！")
				$("#smallModalInfo").on("hidden.bs.modal",function(e){
					$("body").addClass("modal-open");
				});
			}
		});

	}else {
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请填写必要数据！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
	}



}


//保存货场
function  saveFreightYard(){
	//验证非空字段
	if(checkSelectNotNull($(".provinceJsons"))&checkSelectNotNull($(".cityJsons"))&checkSelectNotNull($(".areaJsons"))&
			checkNotNull($("#freightYardName"))&checkNotNull($("#averageUnload"))&checkNotNull($("#averageLoad"))&checkNotNull($("#townStreet"))&
			checkNotNull($("#latitudeValue"))&checkNotNull($("#longitudeValue"))){
		var _inputs = $(".myModalNewAdd").find("input");
		var _inputLength = $(".myModalNewAdd").find("input").length;
		var _selects = $(".myModalNewAdd").find(".placeHolder");
		var _selectLength = $(".myModalNewAdd").find(".placeHolder").length;
		var _goodTypeInfo = {};
		for(var i = 0;i<_inputLength;i++){
			var _addAttr = _inputs[i].getAttribute("addAttr");
			_goodTypeInfo[_addAttr] = _inputs[i].value;
		}
		for(var i = 0;i<_selectLength;i++){
			var _addAttr = _selects[i].getAttribute("addAttr");
			_goodTypeInfo[_addAttr] = _selects[i].getAttribute("value");
		}
		var  stringObj=JSON.stringify(_goodTypeInfo);
		$.ajax({
			type:"post",
			url:"/iwuliu/freightYardManager/saveFreightYard",
			data:{"list":stringObj},
			async:true,
			success:function(data){
				var obj = eval(data);
				if(obj.result==1){
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("保存成功！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
						$("#myModalNewAdd").modal('hide')
						$('#freightYard').bootstrapTable('refresh');
					});
				}else if(obj.result==0){
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("保存重复！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});
				}else {
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("保存失败！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});
				}
			},
			error:function(){
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("系统错误！")
				$("#smallModalInfo").on("hidden.bs.modal",function(e){
					$("body").addClass("modal-open");
				});
			}
		});
	}else {
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请填写数据！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
	}
}
//删除勾选的货场
function deletefreightYard(){
	var selectTions = $('#freightYard').bootstrapTable('getSelections'); //获取表选择的行
	if (selectTions.length !=0) {
		var freightYardID = "";//存储多个删除的id
		for (var i = 0; i < selectTions.length; i++) {
			if (i == 0) {
				freightYardID = selectTions[i].freightYardId;
			} else {
				freightYardID += "," + selectTions[i].freightYardId;
			}
		}
		$("#smallModalInfoDelete").modal();
		$("#smallModalInfoDelete").find(".titleInfoDelete").html("确定删除货场信息？")
		//点击确定删除
		$(".deletefreightYard").click(function () {
			//发送请求
			$.ajax({
				type: "post",
				url: "/iwuliu/freightYardManager/cargoDel",
				dataType:"json",
				data:{"freightYardID":freightYardID},
				cache:'false',
				success: function (data) {
					// json 类型的专为对象
					var obj = eval(data);
					if (obj.result == 0) {
						$("#smallModalInfo").modal();
						$("#smallModalInfo").find(".titleInfo").html("删除成功！")
						$("#smallModalInfo").on("hidden.bs.modal",function(e){
							$("body").addClass("modal-open");
						});
						$('#freightYard').bootstrapTable('refresh');
					} else {
						$("#smallModalInfo").modal();
						$("#smallModalInfo").find(".titleInfo").html("删除失败！")
						$("#smallModalInfo").on("hidden.bs.modal",function(e){
							$("body").addClass("modal-open");
						});
					}
				},
				error: function () {
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("系统错误！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});
				}
			})
		});
	}else {
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请选择删除的数据！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
	}
}

var webservice = $('#freightYard').attr("data-url");
$.ajax({
	url:webservice,
	success:function(data){
		if(data.length == 0){
			$(".form-inline").css("display","none");
		}
	}
});
//配置参数
function freightYardParams(params) {
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageSize: params.limit,   //页面大小
		pageNumber: params.pageNumber,  //页码
		province: $(".provinceJson").find(".placeHolder").attr("value"),
		city:$(".cityJson").find(".placeHolder").attr("value"),
		area:$(".areaJson").find(".placeHolder").attr("value"),
		street:$("#street").val(),
		sort: params.sort,  //排序列名
		sortOrder: params.order//排位命令（desc，asc）
	};
	return temp;
}
//查询
function searchYard() {
	$('#freightYard').bootstrapTable('refresh', {'url': '/iwuliu/freightYardManager/getFrightYardByPosition'});
}
//重置
function resetYard(){
	//重置
	$(".provinceJson").find(".placeHolder").html("请选择");
	$(".cityJson").find(".placeHolder").html("请选择");
	$(".areaJson").find(".placeHolder").html("请选择");
	$(".infoConditions").find(".placeHolder").removeAttr("value");
	$("#street").val("");
//	$('#freightYard').bootstrapTable('removeAll');
}

//点击详情按钮弹出模态框
function btn_ModalDetail(){
	var selects = $('#freightYard').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length==1){
		var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
		var freightYardId=newSelects[0].freightYardId;
		$.ajax({
			type:"post",
			url:"/iwuliu/freightYardManager/getFreightYardById",
			data:{"freightYardById":freightYardId},
			success:function(data) {
				var JsonData =JSON.parse(data);
				$("#CargoName").val(JsonData.CargoName);
				$("#position").val(JsonData.position);
				$("#town").val(JsonData.townStreet);
				$("#beginTime").val(JsonData.beginTime);
				$("#endTime").val(JsonData.endTime);
				$("#loadCosts").val(JsonData.loadCost);
				$("#loadPumpCosts").val(JsonData.loadPumpCost);
				$("#loadPerDay").val(JsonData.loadPerDay);
				$("#loadAverage").val(JsonData.loadAverage);
				$("#unloadBeginTime").val(JsonData.unloadBeginTime);
				$("#unloadendTime").val(JsonData.unloadendTime);
				$("#unloadCosts").val(JsonData.unloadCost);
				$("#unloadPumpCosts").val(JsonData.unloadPumpCost);
				$("#unloadAverage").val(JsonData.unloadAverage);
				$("#unloadPerDay").val(JsonData.unloadPerDay);
				$("#longitude").val(JsonData.longitude);
				$("#latitude").val(JsonData.latitude);
				$("#myModal").modal();
			}
		})

	}else {
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请选择一行数据！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
	}
}
//点击新增按钮弹出模态框
function btn_NewAdd(){
	$("#myModalNewAdd").modal();
}
//新增时点击弹出地图页面
function getMap(ele){
	//获取地址
	var province=$(".myModalNewAdd").find(".provinceJsons").find(".placeHolder").html();
	var area=$(".myModalNewAdd").find(".areaJsons").find(".placeHolder").html();
	var city=$(".myModalNewAdd").find(".cityJsons").find(".placeHolder").html();
	var street=$(".newAdd_address").val();
	$(".map_address").val(province+city+area+street);
	$("#ModalMap").modal();
	//图片放大模态框隐藏后触发的事件
	$("#ModalMap").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open")
	})
}

//地图上的点击按钮
function getClicks() {
	objMap.clearMap();
	var geocoder = new AMap.Geocoder({
		radius: 500 //范围，默认：500
	});
	var key =  $(".map_address").val();
	//地理编码,返回地理编码结果
	geocoder.getLocation(key, function(status, result) {
		if (status === 'complete' && result.info === 'OK') {
			geocoder_CallBack(result);
		}
	});
}

function addMarker(i, d) {
	var marker = new AMap.Marker({
		map: objMap,
		position: [ d.location.getLng(),  d.location.getLat()]
	});
	var infoWindow = new AMap.InfoWindow({
		content: d.formattedAddress,
		offset: {x: 0, y: -30}
	});
	marker.on("mouseover", function(e) {
		infoWindow.open(objMap, marker.getPosition());
	});
}
//地理编码返回结果展示
function geocoder_CallBack(data) {
	//地理编码结果数组
	var geocode = data.geocodes;
	addMarker(0, geocode[0]);
	objMap.setFitView();
	$(".map_longitude").val(geocode[0].location.getLng());
	$(".map_latitude").val(geocode[0].location.getLat());
}
/*
 * 地图点击确定按钮传值给新增页面的经纬度
 */
function determine(){
	$(".newAdd_longitude").val($(".map_longitude").val());
	$(".newAdd_latitude").val($(".map_latitude").val());
}
//验证装货费用
function checkLoadCost(ele){
	var total=$("#loadCost").val();
	if (numbers.test(total)){
		$(ele).parent().find("p").html("");
	}else {
		$(ele).parent().find("p").html("请输入数字！");
		$(ele).parent().find("p").css("color","red");
	}
}
//验证装货过磅费用
function checkPumpCost(ele){
	var total=$("#loadPumpCost").val();
	if (numbers.test(total)){
		$(ele).parent().find("p").html("");
	}else {
		$(ele).parent().find("p").html("请输入数字！");
		$(ele).parent().find("p").css("color","red");
	}
}
//验证装货车数
function checkLoadCount(ele){
	var total=$("#loadCount").val();
	if (numbers.test(total)){
		$(ele).parent().find("p").html("");
	}else {
		$(ele).parent().find("p").html("请输入数字！");
		$(ele).parent().find("p").css("color","red");
	}
}

//验证卸货费用
function checkunLoadCosts(ele){
	var total=$("#unloadCost").val();
	if (numbers.test(total)){
		$(ele).parent().find("p").html("");
	}else {
		$(ele).parent().find("p").html("请输入数字！");
		$(ele).parent().find("p").css("color","red");
	}
}
//验证卸货过磅费用
function checkunPumpCosts(ele){
	var total=$("#unloadPumpCost").val();
	if (numbers.test(total)){
		$(ele).parent().find("p").html("");
	}else {
		$(ele).parent().find("p").html("请输入数字！");
		$(ele).parent().find("p").css("color","red");
	}
}
//验证卸货车数
function checkunLoadCount(ele){
	var total=$("#unloadCount").val();
	if (numbers.test(total)){
		$(ele).parent().find("p").html("");
	}else {
		$(ele).parent().find("p").html("请输入数字！");
		$(ele).parent().find("p").css("color","red");
	}
}
//前10趟平均装卸货时间
function averageLoader(ele){
	var total=$("#averageLoad").val();
	if (numbers.test(total)){
		$(ele).parent().find("p").html("");
	}else {
		$(ele).parent().find("p").html("请输入数字！");
		$(ele).parent().find("p").css("color","red");
	}
}

//验证卸货车数
function averageUnloader(ele){
	var total=$("#averageUnload").val();
	if (numbers.test(total)){
		$(ele).parent().find("p").html("");
	}else {
		$(ele).parent().find("p").html("请输入数字！");
		$(ele).parent().find("p").css("color","red");
	}
}
// 不能为空的正则校验(input输入框使用)
function checkNotNull(inp) {
	var _value = inp.val();
	var _p = $(inp).parents(".form-group").find("p");
	if (_value != '') {
		_p.html("");
		return true;
	} else {
		_p.css("color", "red");
		_p.html("不能为空！");
		return false;
	}
}
// 不能为空的正则校验(下拉框使用)
function checkSelectNotNull(inp) {
	var _value = inp.find(".placeHolder").attr("value");
	var _p = $(inp).parents(".form-group").find("p");
	if (_value != '' && _value != undefined) {
		_p.html("");
		return true;
	} else {
		_p.css("color", "red");
		_p.html("不能为空！");
		return false;
	}
}
//验证货物数量数字
function checkNumber(ele){
	var total=$(ele).val();
	if (numbers.test(total)){
		$(ele).parent().find("p").html("");
		return true;
	}else {
		$(ele).parent().find("p").html("请输入数字！");
		$(ele).parent().find("p").css("color","red");
		return false;
	}
}