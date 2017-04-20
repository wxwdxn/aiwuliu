var removeRouterNodeDetailArr = [];
var removeRouterArr = [];
var line_id;// 线路ID
$(function(){
	$(".leftMenu").load("/iwuliu/welcomeManager/home", function () {
		$(".routerManager").parents(".collapse").addClass("in");
		$(".routerManager").addClass("menuActive")
		roleManager();
	});
	$(".edit").bind("click",function(){
		removeRouterNodeDetailArr = [];
		removeRouterArr = [];
		$("#myModal").find(".hidden").removeClass("hidden");
		$(".sub_line_info_name").removeAttr("disabled")
		var _router_infoDetail;
		if($(".router_infoDetail").length == 1){
			$(".removeRouterInfoBtn").addClass("hidden")
		}
	});
	//详情页面模态框消失后
	$("#myModal").on("hidden.bs.modal",function(){
		$("#myModal").find(".edit").attr("isClick", "true");
		$("#myModal").find(".edit").removeAttr("disabled");
		$("#myModal").find(".edit").css("background", "#69a2b5");
		$("#myModal").find(".form-group").addClass("uneditable");
		$("#myModal").find("input").attr("disabled", "true");
		$("#myModal").find(".glyphicon").addClass("hidden")
		$("#myModal").find(".form-group").find("button").attr("disabled", "true");
		$("#myModal").find(".router_infoDetail").remove()
	})
	//新增页面消失后
	$("#myModalNewAdd").on("hidden.bs.modal",function(){
		$("#myModalNewAdd").find(".newAddRouter").remove();
		$("#myModalNewAdd").find(".newAddNode").remove();
		$("#myModalNewAdd").find(".glyphicon-minus").addClass("hidden");
		$("#myModalNewAdd").find(".form-control").html("");
		$("#myModalNewAdd").find(".form-control").val("");
		var nodeNumbers = $("#myModalNewAdd").find(".nodeNumber");
		for(var i = 0;i<nodeNumbers.length;i++){
			nodeNumbers[i].innerHTML = i
		}
	})
	var accountType = new customDropDown($(".start_provice"));
	var accountType = new customDropDown($(".start_city"));
	var accountType = new customDropDown($(".end_provice"));
	var accountType = new customDropDown($(".end_city"));

	var table = $('#routerManagerTable'),
			router_page = $('#router_page'),
			router_goBtn = $('#router_goBtn');
// 跳转到某页
	router_goBtn.click(function () {
		table.bootstrapTable('selectPage', +router_page.val());
	});
	$(".th-inner").eq(0).append('<span>全选</span>');

	// 获取路线下拉框
	$("#routerManager_finishCity").blur(function () {
		// 获取线路
		$.ajax({
			type: 'post',
			url: "/iwuliu/routerManager/lineDetailList",
			data: {
				"routerManager_startCity": $("#routerManager_startCity").val(),
				"routerManager_finishCity": $("#routerManager_finishCity").val()
			},
			dataType: 'json',
			cache: false,
			success: function (data) {
				var str = "<li value=''><a href='javascript:;'>全部</a></li>";
				for (var i = 0; i < data.length; i++) {
					str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
				}
				$(".routerManager_lineId").find(".dropdown-menu").html(str);
				var routerManagerLineId = new customDropDown($(".routerManager_lineId"));
			},
		});
	});
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
});

// 二层模态框小提示框消失后使其一层模态框上下滑动
$(function(){
	$("#smallModalInfo").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open");
	});
});

//配置参数
function routerQueryParams(params) {
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageSize: params.limit,   //页面大小
		pageNumber: params.pageNumber,  //页码
		start_city: $("#routerManager_startCity").val(),
		finish_city: $("#routerManager_finishCity").val(),
		line_no: $("#routerManager_lineNo").val(),
		line_id: $(".routerManager_lineId").find(".placeHolder").attr("value"),
		sort: params.sort,  //排序列名
		sortOrder: params.order//排位命令（desc，asc）
	};
	return temp;
}

// 查询线下加盟服务商管理列表
function routerManagerQuery() {
	$('#routerManagerTable').bootstrapTable('refresh', {'url': '/iwuliu/routerManager/routerList'});
}

// 查询重置
function routerManagerQueryReset() {
	$('#routerManager_startCity').val("");
	$('#routerManager_finishCity').val("");
	$('#routerManager_lineNo').val("");
	$('#routerManager_lineId').val("");
	$('.placeHolder').html("线路备注");
	$('.placeHolder').attr("value", "");
	$(".sr-only").removeClass("sr-only");
	$('#town_street').val("");
	$('#routerManagerTable').bootstrapTable('removeAll');
}

//点击新增按钮

var newRouterStartCity='',newRouterEndCity='';
function btn_newAdd(){
	$("#myModalNewAdd").find(".placeHolder").attr("value","");
	$("#myModalNewAdd").find(".placeHolder").html("请选择")
	//获取起点省、市
	$.ajax({
		type: 'post',
		url: '/iwuliu/dictionaryManager/provinceJson',
		dataType: 'json',
		cache: 'false',
		success: function (data) {
			var str = "";
			for (var i = 0; i < data.length; i++) {
				str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
			}
			$(".add_start_provice").find(".placeHolder").html("请选择")
			$(".add_start_provice").find(".dropdown-menu").html(str);
			//省
			var add_province = new customDropDown($(".add_start_provice"));
			$("#myModalNewAdd").modal()
			//根据省获取市
			$(".add_start_provice").find(".dropdown-menu li").bind("click", function () {
				var add_start_provice = $(this).attr("value");
				if (add_start_provice != '' && add_start_provice != 'undefined') {
					$.ajax({
						type: "POST",
						url: "/iwuliu/dictionaryManager/cityJson?dicdata_code=" + add_start_provice,
						cache: false,
						dataType: "json",
						success: function (data) {
							var str = "";
							for (var i = 0; i < data.length; i++) {
								str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
							}
							//市
							$(".add_start_city").find(".dropdown-menu").html("");
							$(".add_start_city").find(".placeHolder").html("请选择");
							$(".add_start_city").find(".dropdown-menu").html(str);
							//市
							var add_start_city = new customDropDown($(".add_start_city"));
							$(".add_start_city").find("li").bind("click",function(){
								var _text = $(this).find("a").text();
								$(".nodeStartCity").html(_text);
								$(".firstStartCity").val(_text)
								newRouterStartCity = _text
							})
						}
					});
				}
			})
		}
	});

	//获取终点省、市
	$.ajax({
		type: 'post',
		url: '/iwuliu/dictionaryManager/provinceJson',
		dataType: 'json',
		cache: 'false',
		success: function (data) {
			var str = "";
			for (var i = 0; i < data.length; i++) {
				str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
			}
			$(".add_end_provice").find(".placeHolder").html("请选择");

			$(".add_end_provice").find(".dropdown-menu").html(str);
			//省
			var add_province = new customDropDown($(".add_end_provice"));
			//根据省获取市
			$(".add_end_provice").find(".dropdown-menu li").bind("click", function () {
				var add_end_provice = $(this).attr("value");
				if (add_end_provice != '' && add_end_provice != 'undefined') {
					$.ajax({
						type: "POST",
						url: "/iwuliu/dictionaryManager/cityJson?dicdata_code=" + add_end_provice,
						cache: false,
						dataType: "json",
						success: function (data) {
							var str = "";
							for (var i = 0; i < data.length; i++) {
								str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
							}
							//市
							$(".add_end_city").find(".dropdown-menu").html("");
							$(".add_end_city").find(".placeHolder").html("请选择");
							$(".add_end_city").find(".dropdown-menu").html(str);
							//市
							var add_end_city = new customDropDown($(".add_end_city"));
							$(".add_end_city").find("li").bind("click",function(){
								var _text = $(this).find("a").text();
								$(".nodeEndCity").html(_text);
								$(".lastEndCity").val(_text)
								newRouterEndCity = _text
							})
						}
					});
				}
			})
		}
	});
}

var detailRouterStartCity='',detailRouterEndCity=''
//点击详情按钮
function btn_detail(){
	var selects = $('#routerManagerTable').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length==1){
		var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
		line_id =newSelects[0].line_id;
		$.ajax({
			type:"get",
			url:"/iwuliu/routerManager/routerDetail?line_id=" + line_id,
			success:function(data){
				var data = JSON.parse(data);
				console.log(data);
				$(".routerManagerDetail").attr("operate_main_line_id", data[0].operate_main_line_id);
				$(".start_provice").find(".placeHolder").html(data[0].start_province_name);
				$(".start_provice").find(".placeHolder").attr("value",data[0].start_province_id);
				$(".start_city").find(".placeHolder").html(data[0].start_city_name);
				detailRouterStartCity = data[0].start_city_name;
				$(".start_city").find(".placeHolder").attr("value",data[0].start_city_id);
				$(".end_provice").find(".placeHolder").html(data[0].finish_province_name);
				$(".end_provice").find(".placeHolder").attr("value",data[0].finish_province_id);
				$(".end_city").find(".placeHolder").html(data[0].finish_city_name);
				detailRouterEndCity = data[0].finish_city_name
				$(".end_city").find(".placeHolder").attr("value",data[0].finish_city_id);
				if(data[0].subLineInfo){
					var subLineInfo = data[0].subLineInfo
					for(var i = 0;i<subLineInfo.length;i++){
						var _routerInfo = '<div class="form-inline routerTypeName docubleUneditable padding-left">'+
								'<div class="form-group margin-right addRouterBtn">'+
								'<label class="sr-only">添加线路</label>'+
								'<div class="display_inlineBlcok">'+
								'<span class="glyphicon glyphicon-plus addRouterInfoBtn hidden" onclick="addRouterDetailInfo(this)">'+

								'</span>'+
								'<span class="glyphicon glyphicon-minus removeRouterInfoBtn hidden" onclick="removeRouterInfo(this)">'+

								'</span>'+
								'</div>'+
								'</div>'+
								'<div class="form-group margin-right">'+
								'<label>线路：</label>'+
								'<div class="display_inlineBlcok">'+
								'<span class="form-control nodeStartCity">'+ data[0].start_city_name +'</span>'+
								'<span class="form-control nodeEndCity">'+ data[0].finish_city_name +'</span>'+
								'</div>'+
								'</div>'+
								'<div class="form-group uneditable">'+
								'<label>线路备注：</label>'+
								'<div class="display_inlineBlcok">'+
								'<input type="text" disabled  class="form-control text-center sub_line_info_name" value="'+ subLineInfo[i].sub_line_info_name +'"/>'+
								'<p></p>'+
								'</div>'+
								'</div>'+
								'</div>'
						//线路下节点信息
						var subLineDetail = subLineInfo[i].subLineDetail;
						var _str = '';
						for(var j = 0;j<subLineDetail.length;j++){
							if( j == 0){
								_str += '<div class="form-inline routerManagerNodeType" sub_line_detail_id="'+ subLineDetail[j].sub_line_detail_id +'">'+
										'<div class="form-group docubleUneditable">'+
										'<label for="">节点编号：</label>'+
										'<div class="display_inlineBlcok">'+
										'<span addAttr="nodeNumber" class="form-control nodeNumber">'+ subLineDetail[j].node_no +'</span>'+
										'<p></p>'+
										'</div>'+
										'</div>'+
										'<div class="form-group docubleUneditable">'+
										'<label for="">节点名称：</label>'+
										'<div class="display_inlineBlcok">'+
										'<input disabled addAttr="nodeName" type="text" class="form-control text-center nodeName firstStartCity" value="'+ subLineDetail[j].node_name +'"/>'+
										'<p></p>'+
										'</div>'+
										'</div>'+
										'<div class="form-group docubleUneditable">'+
										'<label for="">节点经度：</label>'+
										'<div class="display_inlineBlcok">'+
										'<span class="form-control nodeLongitude" addAttr="nodeLongitude">'+ subLineDetail[j].longitude +'</span>'+
										'<p></p>'+
										'</div>'+
										'</div>'+
										'<div class="form-group docubleUneditable">'+
										'<label for="">节点纬度：</label>'+
										'<div class="display_inlineBlcok">'+
										'<span class="form-control nodeLatitude" addAttr="nodeLatitude">'+ subLineDetail[j].latitude +'</span>'+
										'<p></p>'+
										'</div>'+
										'</div>'+
										'<div class="form-group hidden">'+
										'<label class="sr-only">获取经纬度</label>'+
										'<div class="display_inlineBlcok">'+
										'<span onclick="getMap(this)" class="latitudeAndlongitude margin-right">点击获取经纬度</span>'+
										'<span class="glyphicon glyphicon-plus addNodeBtn" onclick="addNodeDetailBtn(this)">'+

										'</span>'+
										'</div>'+
										'</div>'+
										'</div>'
							}else if(j == subLineDetail.length-1){
								_str += '<div class="form-inline routerManagerNodeType" sub_line_detail_id="'+ subLineDetail[j].sub_line_detail_id +'">'+
										'<div class="form-group docubleUneditable">'+
										'<label for="">节点编号：</label>'+
										'<div class="display_inlineBlcok">'+
										'<span addAttr="nodeNumber" class="form-control nodeNumber">'+ subLineDetail[j].node_no +'</span>'+
										'<p></p>'+
										'</div>'+
										'</div>'+
										'<div class="form-group docubleUneditable">'+
										'<label for="">节点名称：</label>'+
										'<div class="display_inlineBlcok">'+
										'<input disabled addAttr="nodeName" type="text" class="form-control text-center nodeName lastEndCity" value="'+ subLineDetail[j].node_name +'" />'+
										'<p></p>'+
										'</div>'+
										'</div>'+
										'<div class="form-group docubleUneditable">'+
										'<label for="">节点经度：</label>'+
										'<div class="display_inlineBlcok">'+
										'<span class="form-control nodeLongitude" addAttr="nodeLongitude">'+ subLineDetail[j].longitude +'</span>'+
										'<p></p>'+
										'</div>'+
										'</div>'+
										'<div class="form-group docubleUneditable">'+
										'<label for="">节点纬度：</label>'+
										'<div class="display_inlineBlcok">'+
										'<span class="form-control nodeLatitude" addAttr="nodeLatitude">'+ subLineDetail[j].latitude +'</span>'+
										'<p></p>'+
										'</div>'+
										'</div>'+
										'<div class="form-group hidden">'+
										'<label class="sr-only">获取经纬度</label>'+
										'<div class="display_inlineBlcok">'+
										'<span onclick="getMap(this)" class="latitudeAndlongitude margin-right">点击获取经纬度</span>'+

										'</div>'+
										'</div>'+
										'</div>'
							}else{
								_str += '<div class="form-inline routerManagerNodeType" sub_line_detail_id="'+ subLineDetail[j].sub_line_detail_id +'">'+
										'<div class="form-group docubleUneditable">'+
										'<label for="">节点编号：</label>'+
										'<div class="display_inlineBlcok">'+
										'<span addAttr="nodeNumber" class="form-control nodeNumber">'+ subLineDetail[j].node_no +'</span>'+
										'<p></p>'+
										'</div>'+
										'</div>'+
										'<div class="form-group uneditable">'+
										'<label for="">节点名称：</label>'+
										'<div class="display_inlineBlcok">'+
										'<input disabled addAttr="nodeName" type="text" class="form-control text-center nodeName" value="'+ subLineDetail[j].node_name +'" />'+
										'<p></p>'+
										'</div>'+
										'</div>'+
										'<div class="form-group docubleUneditable">'+
										'<label for="">节点经度：</label>'+
										'<div class="display_inlineBlcok">'+
										'<span class="form-control nodeLongitude" addAttr="nodeLongitude">'+ subLineDetail[j].longitude +'</span>'+
										'<p></p>'+
										'</div>'+
										'</div>'+
										'<div class="form-group docubleUneditable">'+
										'<label for="">节点纬度：</label>'+
										'<div class="display_inlineBlcok">'+
										'<span class="form-control nodeLatitude" addAttr="nodeLatitude">'+ subLineDetail[j].latitude +'</span>'+
										'<p></p>'+
										'</div>'+
										'</div>'+
										'<div class="form-group hidden">'+
										'<label class="sr-only">获取经纬度</label>'+
										'<div class="display_inlineBlcok">'+
										'<span onclick="getMap(this)" class="latitudeAndlongitude margin-right">点击获取经纬度</span>'+
										'<span class="glyphicon glyphicon-plus addNodeBtn" onclick="addNodeDetailBtn(this)">'+

										'</span>'+
										'<span class="glyphicon glyphicon-minus addNodeBtn" onclick="removeNodeDetailBtn(this)">'+

										'</span>'+
										'</div>'+
										'</div>'+
										'</div>'
							}
						}
						$("#myModal").find(".modal-body").append('<div class="router_infoDetail" sub_line_info_id = "'+ subLineInfo[i].sub_line_info_id +'">'+ _routerInfo + _str +'</div>')
					}
				}
				$("#myModal").modal();
			}
		})
	}else {
		alert("请选择一行数据")
	}

	//获取起点省、市
	$.ajax({
		type: 'post',
		url: '/iwuliu/dictionaryManager/provinceJson',
		dataType: 'json',
		cache: 'false',
		success: function (data) {
			var str = "";
			for (var i = 0; i < data.length; i++) {
				str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
			}
			$(".start_provice").find(".placeHolder").html("请选择")
			$(".start_provice").find(".dropdown-menu").html(str);
			//省
			var add_province = new customDropDown($(".start_provice"));
			//根据省获取市
			$(".start_provice").find(".dropdown-menu li").bind("click", function () {
				var add_start_provice = $(this).attr("value");
				if (add_start_provice != '' && add_start_provice != 'undefined') {
					$.ajax({
						type: "POST",
						url: "/iwuliu/dictionaryManager/cityJson?dicdata_code=" + add_start_provice,
						cache: false,
						dataType: "json",
						success: function (data) {
							var str = "";
							for (var i = 0; i < data.length; i++) {
								str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
							}
							//市
							$(".start_city").find(".dropdown-menu").html("");
							$(".start_city").find(".placeHolder").html("请选择");
							$(".start_city").find(".dropdown-menu").html(str);
							//市
							var add_start_city = new customDropDown($(".start_city"));
							$(".start_city").find("li").bind("click",function(){
								var _text = $(this).find("a").text();
								$(".nodeStartCity").html(_text);
								$(".firstStartCity").val(_text)
								detailRouterStartCity = _text
							})
						}
					});
				}
			})
		}
	});

	//获取终点省、市
	$.ajax({
		type: 'post',
		url: '/iwuliu/dictionaryManager/provinceJson',
		dataType: 'json',
		cache: 'false',
		success: function (data) {
			var str = "";
			for (var i = 0; i < data.length; i++) {
				str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
			}
			//$(".end_provice").find(".placeHolder").html("请选择");

			$(".end_provice").find(".dropdown-menu").html(str);
			//省
			var add_province = new customDropDown($(".end_provice"));
			//根据省获取市
			$(".end_provice").find(".dropdown-menu li").bind("click", function () {
				var add_end_provice = $(this).attr("value");
				if (add_end_provice != '' && add_end_provice != 'undefined') {
					$.ajax({
						type: "POST",
						url: "/iwuliu/dictionaryManager/cityJson?dicdata_code=" + add_end_provice,
						cache: false,
						dataType: "json",
						success: function (data) {
							var str = "";
							for (var i = 0; i < data.length; i++) {
								str += "<li value=" + data[i].id + "><a href='javascript:;'>" + data[i].name + "</a></li>"
							}
							//市
							$(".end_city").find(".dropdown-menu").html("");
							$(".end_city").find(".placeHolder").html("请选择");
							$(".end_city").find(".dropdown-menu").html(str);
							//市
							var end_city111 = new customDropDown($(".end_city"));
							$(".end_city").find("li").bind("click",function(){
								var _text = $(this).find("a").text();
								$(".nodeEndCity").html(_text);
								$(".lastEndCity").val(_text)
								detailRouterEndCity = _text
							})
						}
					});
				}
			})
		}
	});
}
//点击添加线路按钮
function addRouterInfo(ele){
	var _routerInfoDetail = $(ele).parents(".router_infoDetail");
	var _append = '<div class="form-inline docubleUneditable padding-left">'+
			'<div class="form-group margin-right addRouterBtn">'+
			'<label class="sr-only">添加线路</label>'+
			'<div class="display_inlineBlcok">'+
			'<span class="glyphicon glyphicon-plus addRouterInfoBtn" onclick="addRouterInfo(this)">'+

			'</span>&nbsp;&nbsp;'+
			'<span class="glyphicon glyphicon-minus removeRouterInfoBtn" onclick="removeRouterInfo(this)">'+

			'</span>'+
			'</div>'+
			'</div>'+
			'<div class="form-group margin-right">'+
			'<label>线路：</label>'+
			'<div class="display_inlineBlcok">'+
			'<span class="form-control nodeStartCity">'+ newRouterStartCity +'</span>&nbsp;&nbsp;'+
			'<span class="form-control nodeEndCity">'+ newRouterEndCity +'</span>&nbsp;&nbsp;'+
			'</div>'+
			'</div>'+
			'<div class="form-group">'+
			'<label>线路备注：</label>'+
			'<div class="display_inlineBlcok">'+
			'<input type="text" class="form-control text-center routerNodeName" value=""/>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'</div>'+
			'<div class="form-inline routerManagerNewAddNodeInfoDetail">'+
			'<div class="form-group docubleUneditable">'+
			'<label for="">节点编号：</label>'+
			'<div class="display_inlineBlcok">'+
			'&nbsp;&nbsp;<span class="form-control nodeNumber" addAttr="nodeNumber">0</span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group docubleUneditable">'+
			'<label for="">节点名称：</label>'+
			'<div class="display_inlineBlcok">'+
			'<input disabled type="text" addAttr="nodeName" class="form-control text-center firstStartCity nodeName" value="'+ newRouterStartCity+'"/>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group docubleUneditable">'+
			'<label for="">&nbsp;&nbsp;&nbsp;节点经度：</label>'+
			'<div class="display_inlineBlcok">'+
			'<span class="form-control nodeLongitude" addAttr="nodeLongitude"></span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group docubleUneditable">'+
			'<label for="">&nbsp;&nbsp;&nbsp;节点纬度：</label>'+
			'<div class="display_inlineBlcok">'+
			'<span class="form-control nodeLatitude" addAttr="nodeLatitude"></span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group">'+
			'<label class="sr-only">获取经纬度</label>'+
			'<div class="display_inlineBlcok">'+
			'<span onclick="getMap(this)" class="latitudeAndlongitude margin-right">点击获取经纬度</span>'+
			'&nbsp;&nbsp;<span class="glyphicon glyphicon-plus  " onclick="addNodeBtn(this)">'+

			'</span>'+
			'</div>'+
			'</div>'+
			'</div>'+
			'<div class="form-inline routerManagerNewAddNodeInfoDetail">'+
			'<div class="form-group docubleUneditable">'+
			'<label>节点编号：</label>'+
			'<div class="display_inlineBlcok">'+
			'&nbsp;&nbsp;<span class="form-control nodeNumber" addAttr="nodeNumber">1</span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group docubleUneditable">'+
			'<label for="">节点名称：</label>'+
			'<div class="display_inlineBlcok">'+
			'<input disabled type="text" addAttr="nodeName" class="form-control text-center lastEndCity nodeName" value="'+ newRouterEndCity +'"/>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group docubleUneditable">'+
			'<label for="">&nbsp;&nbsp;&nbsp;节点经度：</label>'+
			'<div class="display_inlineBlcok">'+
			'<span class="form-control nodeLongitude" addAttr="nodeLongitude"></span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group docubleUneditable">'+
			'<label for="">&nbsp;&nbsp;&nbsp;节点纬度：</label>'+
			'<div class="display_inlineBlcok">'+
			'<span class="form-control nodeLatitude" addAttr="nodeLatitude"></span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group">'+
			'<label class="sr-only">获取经纬度</label>'+
			'<div class="display_inlineBlcok">'+
			'<span onclick="getMap(this)" class="latitudeAndlongitude margin-right">点击获取经纬度</span>'+
			'</span>'+
			'</div>'+
			'</div>'+
			'</div>';

	_routerInfoDetail.after('<div class="router_infoDetail newAddRouter">'+ _append +'</div>');
	$(".removeRouterInfoBtn").removeClass("hidden")
}
//点击添加线路详情
function addRouterDetailInfo(ele){
	var _routerInfoDetail = $(ele).parents(".router_infoDetail");
	var _append = '<div class="form-inline docubleUneditable padding-left">'+
			'<div class="form-group margin-right addRouterBtn">'+
			'<label class="sr-only">添加线路</label>'+
			'<div class="display_inlineBlcok">'+
			'<span class="glyphicon glyphicon-plus addRouterInfoBtn" onclick="addRouterDetailInfo(this)">'+

			'</span>&nbsp;&nbsp;'+
			'<span class="glyphicon glyphicon-minus removeRouterInfoBtn" onclick="removeRouterInfo(this)">'+

			'</span>'+
			'</div>'+
			'</div>'+
			'<div class="form-group margin-right">'+
			'<label>线路：</label>'+
			'<div class="display_inlineBlcok">'+
			'<span class="form-control nodeStartCity">'+ detailRouterStartCity +'</span>&nbsp;&nbsp;'+
			'<span class="form-control nodeEndCity">'+ detailRouterEndCity +'</span>&nbsp;&nbsp;'+
			'</div>'+
			'</div>'+
			'<div class="form-group">'+
			'<label>线路备注：</label>'+
			'<div class="display_inlineBlcok">'+
			'<input type="text" class="form-control text-center sub_line_info_name" value=""/>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'</div>'+
			'<div class="form-inline routerManagerNodeType">'+
			'<div class="form-group docubleUneditable">'+
			'<label for="">节点编号：</label>'+
			'<div class="display_inlineBlcok">'+
			'&nbsp;&nbsp;<span addattr="nodeNumber" class="form-control nodeNumber">0</span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group docubleUneditable">'+
			'<label for="">节点名称：</label>'+
			'<div class="display_inlineBlcok">'+
			'<input disabled type="text" addattr="nodeName" class="form-control text-center nodeName firstStartCity" value="'+ detailRouterStartCity +'"/>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group docubleUneditable">'+
			'<label for="">节点经度：</label>'+
			'<div class="display_inlineBlcok">'+
			'<span class="form-control nodeLongitude" addattr="nodeLongitude"></span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group docubleUneditable">'+
			'<label for="">节点纬度：</label>'+
			'<div class="display_inlineBlcok">'+
			'<span class="form-control nodeLatitude" addattr="nodeLatitude"></span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group">'+
			'<label class="sr-only">获取经纬度</label>'+
			'<div class="display_inlineBlcok">'+
			'<span onclick="getMap(this)" class="latitudeAndlongitude margin-right">点击获取经纬度</span>'+
			'&nbsp;&nbsp;<span class="glyphicon glyphicon-plus  " onclick="addNodeDetailBtn(this)">'+

			'</span>'+
			'</div>'+
			'</div>'+
			'</div>'+
			'<div class="form-inline routerManagerNodeType">'+
			'<div class="form-group docubleUneditable">'+
			'<label>节点编号：</label>'+
			'<div class="display_inlineBlcok">'+
			'&nbsp;&nbsp;<span addattr="nodeNumber" class="form-control nodeNumber">1</span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group docubleUneditable">'+
			'<label for="">节点名称：</label>'+
			'<div class="display_inlineBlcok">'+
			'<input disabled addattr="nodeName" type="text" class="form-control text-center nodeName lastEndCity" value="'+ detailRouterEndCity +'"/>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group docubleUneditable">'+
			'<label for="">节点经度：</label>'+
			'<div class="display_inlineBlcok">'+
			'<span class="form-control nodeLongitude" addattr="nodeLongitude"></span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group docubleUneditable">'+
			'<label for="">节点纬度：</label>'+
			'<div class="display_inlineBlcok">'+
			'<span class="form-control nodeLatitude" addattr="nodeLatitude"></span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group">'+
			'<label class="sr-only">获取经纬度</label>'+
			'<div class="display_inlineBlcok">'+
			'<span onclick="getMap(this)" class="latitudeAndlongitude margin-right">点击获取经纬度</span>'+

			'</span>'+
			'</div>'+
			'</div>'+
			'</div>';

	_routerInfoDetail.after('<div class="router_infoDetail" sub_line_info_id="null">'+ _append +'</div>');
	$(".removeRouterInfoBtn").removeClass("hidden")
}
//点击删除线路按钮
function removeRouterInfo(ele){
	var _router_infoDetail = $(ele).parents(".router_infoDetail");
	var _routers = $(ele).parents(".modal-body").find(".router_infoDetail");
	if(_routers.length > 2){
		var sub_line_info_id = _router_infoDetail.attr("sub_line_info_id");
		if(sub_line_info_id != "null"){
			removeRouterArr.push(sub_line_info_id);
		}
		console.log(removeRouterArr)
		_router_infoDetail.remove()
	}else if(_routers.length == 2){
		var sub_line_info_id = _router_infoDetail.attr("sub_line_info_id");
		if(sub_line_info_id != "null"){
			removeRouterArr.push(sub_line_info_id);
		}
		console.log(removeRouterArr)
		_router_infoDetail.remove()
		$(".removeRouterInfoBtn").addClass("hidden")
	}
}
//点击添加节点
function addNodeBtn(ele){
	var _appendString = '<div class="form-group docubleUneditable margin-right">'+
			'<label for="">节点编号：</label>'+
			'<div class="display_inlineBlcok">'+
			'&nbsp;<span addAttr="nodeNumber" class="form-control nodeNumber">1</span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group">'+
			'<label for="">节点名称：</label>'+
			'<div class="display_inlineBlcok">'+
			'<input addAttr="nodeName" type="text" class="form-control text-center nodeName" />'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group docubleUneditable margin-right">'+
			'<label for="">&nbsp;&nbsp;&nbsp;节点经度：</label>'+
			'<div class="display_inlineBlcok">'+
			'<span class="form-control nodeLongitude" addAttr="nodeLongitude"></span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group docubleUneditable margin-right">'+
			'<label for="">&nbsp;&nbsp;&nbsp;节点纬度：</label>'+
			'<div class="display_inlineBlcok">'+
			'<span class="form-control nodeLatitude" addAttr="nodeLatitude"></span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group">'+
			'<label class="sr-only">获取经纬度</label>'+
			'<div class="display_inlineBlcok">'+
			'<span onclick="getMap(this)" class="latitudeAndlongitude margin-right">点击获取经纬度</span>'+
			'&nbsp;<span class="glyphicon glyphicon-plus addNodeBtn" onclick="addNodeBtn(this)">'+

			'</span>'+
			'&nbsp;&nbsp;<span class="glyphicon glyphicon-minus removeNodeBtn" onclick="removeNodeBtn(this)">'+

			'</span>'+
			'</div>'+
			'</div>'

	$(ele).parents(".form-inline").after('<div class="form-inline newAddNode routerManagerNewAddNodeInfoDetail">'+ _appendString +'</div>');
	var _nodeNumbers =  $(ele).parents(".router_infoDetail").find(".nodeNumber");
	for(var i = 0;i<_nodeNumbers.length;i++){
		_nodeNumbers[i].innerHTML = i
	}
}
//点击详情添加节点
function addNodeDetailBtn(ele){
	var _appendString = '<div class="form-group docubleUneditable margin-right">'+
			'<label for="">节点编号：</label>'+
			'<div class="display_inlineBlcok">'+
			'&nbsp;<span addattr="nodeNumber" class="form-control nodeNumber"></span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group">'+
			'<label for="">节点名称：</label>'+
			'<div class="display_inlineBlcok">'+
			'<input type="text" addattr="nodeName" class="form-control text-center nodeName" />'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group docubleUneditable margin-right">'+
			'<label for="">节点经度：</label>'+
			'<div class="display_inlineBlcok">'+
			'<span class="form-control nodeLongitude" addattr="nodeLongitude"></span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group docubleUneditable margin-right">'+
			'<label for="">节点纬度：</label>'+
			'<div class="display_inlineBlcok">'+
			'<span class="form-control nodeLatitude" addattr="nodeLatitude"></span>'+
			'<p></p>'+
			'</div>'+
			'</div>'+
			'<div class="form-group">'+
			'<label class="sr-only">获取经纬度</label>'+
			'<div class="display_inlineBlcok">'+
			'<span onclick="getMap(this)" class="latitudeAndlongitude margin-right">点击获取经纬度</span>'+
			'&nbsp;<span class="glyphicon glyphicon-plus addNodeBtn" onclick="addNodeDetailBtn(this)">'+

			'</span>'+
			'&nbsp;&nbsp;<span class="glyphicon glyphicon-minus removeNodeBtn" onclick="removeNodeDetailBtn(this)">'+

			'</span>'+
			'</div>'+
			'</div>'

	$(ele).parents(".form-inline").after('<div class="form-inline routerManagerNodeType" sub_line_detail_id="null">'+ _appendString +'</div>');
	var _nodeNumbers =  $(ele).parents(".router_infoDetail").find(".nodeNumber");
	for(var i = 0;i<_nodeNumbers.length;i++){
		_nodeNumbers[i].innerHTML = i
	}
}
//点击删除节点
function removeNodeBtn(ele){
	$(ele).parents(".form-inline").remove();

	var routers = $("#myModalNewAdd").find(".router_infoDetail");
	for(var i = 0;i<routers.length;i++){
		var nodes = $(routers[i]).find(".nodeNumber");
		for(var j = 0;j<nodes.length;j++){
			nodes[j].innerHTML = j
		}
	}
}
//点击详情删除节点
function removeNodeDetailBtn(ele){
	$(ele).parents(".form-inline").remove();
	var sub_line_detail_id = $(ele).parents(".form-inline").attr("sub_line_detail_id")
	if(sub_line_detail_id != "null"){
		removeRouterNodeDetailArr.push(sub_line_detail_id);
	}
	console.log(removeRouterNodeDetailArr)
	var routers = $("#myModal").find(".router_infoDetail");
	for(var i = 0;i<routers.length;i++){
		var nodes = $(routers[i]).find(".nodeNumber");
		for(var j = 0;j<nodes.length;j++){
			nodes[j].innerHTML = j
		}
	}
}


var longitudeMap,latitudeMap;

//点击获取经纬度
function getMap(ele){
	//获取地址
	var formInline = $(ele).parents(".form-inline");
	//经度
	var longitude = formInline.find(".nodeLongitude");
	longitudeMap = longitude
	//纬度
	var latitude = formInline.find(".nodeLatitude");
	latitudeMap = latitude
	//节点名称
	var nodeName = formInline.find(".nodeName").val();
	$("#ModalMap").modal();
	$(".map_address").val(nodeName)
	//图片放大模态框隐藏后触发的事件
	$("#ModalMap").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open")
	})
}

//地图上的点击按钮
function getClick() {
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

//点击地图确定按钮
function determine(){
	longitudeMap.html($(".map_longitude").val());
	latitudeMap.html($(".map_latitude").val());
}

// 格式化“详情”列
function operationFormatter() {
	return '<a class="NewDetail" onclick="queryContractDetail(this.parentElement.parentElement)">' +
			'<button style="color: #FF0000" >地图展示</button></a>';
}

//经纬度数组
var pointes = [];
//绘制线路
var polyline;
// 点击“地图展示”
function queryContractDetail(value) {
	$("#ModalMap1").modal();
	$(value).attr("isclick", "false");

	var _value = $(value).find(".hidden").html();
	if (_value != null) {
		$.ajax({
			type: "POST",   // post提交方式
			url: '/iwuliu/routerManager/routerDetail?line_id=' + _value,
			success: function (data) {
				data = JSON.parse(data)
				var lines = data[0].subLineInfo[0].subLineDetail;
				var linesArr = [];
				for (var i = 0; i < lines.length; i++) {
					var m = String(i);
					for (var j = 0; j < lines.length; j++) {
						if (m == lines[j].node_no) {
							linesArr.push(lines[j])
						}
					}
				}
				var LatLon = {};
				var list = {};
				var LatLonArr = [];
				var listArr = [];
				for(var i = 0;i<linesArr.length;i++){
					var tude = {};
					tude["longitude"] = linesArr[i].longitude;
					tude["latitude"] = linesArr[i].latitude;
					LatLonArr.push(tude);
				}
				LatLon["LatLon"] = LatLonArr;
				listArr.push(LatLon);
				list["list"] = listArr;
				list = JSON.stringify(list)
				// json 类型的专为对象
				//var pp = eval('(' + list + ')');
				var pp = JSON.parse(list)
				if (pp.list.length != 0) {
					var p = pp.list[0].LatLon;
					for (var i = 0; i < p.length; i++) { //第二层循环取list中的对象
						//push 把对象放到了栈底
						pointes.push(new AMap.LngLat(p[i].longitude, p[i].latitude));
					}
					objMap = new AMap.Map('container1', {
						center: [116.397428, 39.90923],
						zoom: 10
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
					});
					polyline = new AMap.Polyline({
						path: pointes,
						strokeColor: "#3366CC",
						strokeOpacity: 0.8,
						strokeWeight: 6,
						strokeStyle: 'solid',     // 线样式
						strokeDasharray: [10, 5], // 补充线样式
						geodesic: true            // 绘制大地线
					});
					polyline.setMap(objMap);
					marker = new AMap.Marker({
						map: objMap,
						position: [pointes[pointes.length - 1].lng, pointes[pointes.length - 1].lat],
						offset: new AMap.Pixel(-26, -13),
						autoRotation: true
					});
					objMap.setFitView();
				} else {
					alert("当前线路无节点");
				}
			}
		});
	}
}

// 新增保存
function routerNewAddSaveClick() {
	if (checkSelectNotNull($(".add_start_provice").find(".placeHolder")) &
			checkSelectNotNull($(".add_start_city").find(".placeHolder")) &
			checkSelectNotNull($(".add_end_provice").find(".placeHolder")) &
			checkSelectNotNull($(".add_end_city").find(".placeHolder")) &
			checkNotNull($(".routerNodeName")) &
			checkNotNull($(".nodeName")) &
			checkSpanNotNull($(".nodeLongitude")) &
			checkSpanNotNull($(".nodeLatitude"))
	) {

		var routerAll = [];
		var Obj = {};
		var _routerManagerNewAddNodeInfo = $(".router_infoDetail");
		//获取干线起点和终点
		var _startProvinceValue = $("#startProvince").text();
		var _startCityValue = $("#startCity").text();
		var _endProviceValue = $("#endProvince").text();
		var _endCityValue = $("#endCity").text();
		var _startProvinceID = $(".add_start_provice").find(".placeHolder").attr("value");
		var _startCityID = $(".add_start_city").find(".placeHolder").attr("value");
		var _endProvinceID = $(".add_end_provice").find(".placeHolder").attr("value");
		var _endCityID = $(".add_end_city").find(".placeHolder").attr("value");

		for(var i = 0;i<_routerManagerNewAddNodeInfo.length;i++){
			var _routerNodeName = $(_routerManagerNewAddNodeInfo[i]).find(".routerNodeName");
			var _routerNodeNameStr = '';
			var _routerNodeObj = {};
			//线路备注
			_routerNodeObj["routerNodeName"] = _routerNodeName.val();
			var _routerManagerNewAddNodeInfoDetail = $(_routerManagerNewAddNodeInfo[i]).find(".routerManagerNewAddNodeInfoDetail");
			var _routerNodeArr = [];
			for(var n = 0;n<_routerManagerNewAddNodeInfoDetail.length;n++){
				var _inputs = $(_routerManagerNewAddNodeInfoDetail[n]).find("span.form-control");
				var nodeName = $(_routerManagerNewAddNodeInfoDetail[n]).find(".nodeName");
				var _routerNodeInfo = {};

				for(var m = 0;m<_inputs.length;m++){
					var _addAttr = $(_inputs[m]).attr("addAttr");
					_routerNodeInfo[_addAttr] = $(_inputs[m]).text();
				}
				_routerNodeInfo[nodeName.attr("addAttr")] = nodeName.val();
				_routerNodeArr.push(_routerNodeInfo);
			}
			_routerNodeObj["lines"] = _routerNodeArr;
			routerAll.push(_routerNodeObj);
		}

		Obj["mainRouter"] = _startCityValue+'-'+_endCityValue;
		Obj["startProvince"] = _startProvinceID;
		Obj["startCity"] = _startCityID;
		Obj["endProvince"] = _endProvinceID;
		Obj["endCity"] = _endCityID;
		Obj["lineAll"] = routerAll;
		//开始ajax操作，data中传的是表单中的参数
		$("#routerNewAddForm").ajaxSubmit({
			type: "POST",//提交类型
			dataType: "json",//返回结果格式
			url: "/iwuliu/routerManager/saveRouterInfo",//请求地址
			data: {"list": JSON.stringify(Obj)},
			async: true,
			success: function (data) {
				var obj = eval(data);
				if (obj.result == 1) {
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("保存成功！")
					$("#smallModalInfo").on("hidden.bs.modal", function (e) {
						$("body").addClass("modal-open");
						$('#myModalNewAdd').modal('hide');
						routerManagerQuery();
					})
				} else if (obj.result == 2) {
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("干线重复！");
					$("#smallModalInfo").on("hidden.bs.modal", function (e) {
						$("body").addClass("modal-open");
					})
					return false;
				} else {
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("保存失败！");
					$("#smallModalInfo").on("hidden.bs.modal", function (e) {
						$("body").addClass("modal-open");
					})
					return false;
				}
			},
			error: function () {
				//bootstrap提示框
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("系统异常！");
				$("#smallModalInfo").on("hidden.bs.modal", function (e) {
					$("body").addClass("modal-open");
				})
				return false;
			}
		});
	} else {
		//bootstrap提示框
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请填写完整信息！")
		$("#smallModalInfo").on("hidden.bs.modal", function (e) {
			$("body").addClass("modal-open");
		})
		return false;
	}
}

// 点击编辑保存按钮
function routerEditSave() {
	if (checkSelectNotNull($(".start_provice").find(".placeHolder")) &
			checkSelectNotNull($(".start_city").find(".placeHolder")) &
			checkSelectNotNull($(".end_provice").find(".placeHolder")) &
			checkSelectNotNull($(".end_city").find(".placeHolder")) &
			checkNotNull($("#myModal").find(".sub_line_info_name")) &
			checkNotNull($("#myModal").find(".nodeName")) &
			checkSpanNotNull($("#myModal").find(".nodeLongitude")) &
			checkSpanNotNull($("#myModal").find(".nodeLatitude"))
	) {

		var obj = {};
		//总干线信息
		var _startProvince = $("#detail_startProvince").text();
		var _startCity = $("#detail_startCity").text();
		var _endProvince = $("#detail_endProvince").text();
		var _endCity = $("#detail_endCity").text();
		var _startProvinceValue = $(".start_provice").find(".placeHolder").attr("value");
		var _startCityValue = $(".start_city").find(".placeHolder").attr("value");
		var _endProvinceValue = $(".end_provice").find(".placeHolder").attr("value");
		var _endCityValue = $(".end_city").find(".placeHolder").attr("value");
		//获取多条线路
		var _routerManagerNodeDetail = $("#myModal").find(".router_infoDetail");

		// 运营干线名称
		var mainRouter = _startCity + '-' + _endCity;
		obj["mainRouter"] = mainRouter;
		var allLines = [];
		for(var i = 0;i<_routerManagerNodeDetail.length;i++){
			var line = {};
			//var _routerTypeName = $(_routerManagerNodeDetail[i]).find(".routerTypeName");
			//线路备注
			var _sub_line_info_name = $(_routerManagerNodeDetail[i]).find(".sub_line_info_name");
			//线路ID
			line["sub_line_info_id"] = $(_routerManagerNodeDetail[i]).attr("sub_line_info_id");
			var _textAlignLeftWithRedText = "";
			//单个线路备注内容
			line["sub_line_info_name"] = _sub_line_info_name.val();
			//_routerTypeName.append('<div class="col-md-2 routerLine">'+ _textAlignLeftWithRedText +'</div>');
			//$(_routerManagerNodeDetail[i]).find(".addRouterNodeDisplay").css("display","none");
			//单个线路下面的节点信息
			var _routerManagerNodeTypes = $(_routerManagerNodeDetail[i]).find(".routerManagerNodeType");
			var nodesArr = [];
			for(var m = 0;m<_routerManagerNodeTypes.length;m++){
				var nodeobj = {};
				var _nodes = $(_routerManagerNodeTypes[m]).find("span.form-control");
				nodeobj["node_name"] = $(_routerManagerNodeTypes[m]).find(".nodeName").val();
				nodeobj["sub_line_detail_id"] = $(_routerManagerNodeTypes[m]).attr("sub_line_detail_id");
				for(var n = 0;n<_nodes.length;n++){
					var _addAttr = $(_nodes[n]).attr("addAttr");
					nodeobj[_addAttr] = $(_nodes[n]).text()
				}
				nodesArr.push(nodeobj);
			}
			line["routerNode"] = nodesArr;
			allLines.push(line);
		}
		obj["operate_main_line_id"] = $(".routerManagerDetail").attr("operate_main_line_id");
		obj["startProvinceValue"] = _startProvinceValue;
		obj["startCityValue"] = _startCityValue;
		obj["endProvinceValue"] = _endProvinceValue;
		obj["endCityValue"] = _endCityValue;
		obj["lineAll"] = allLines;
		obj["removeRouterNodeDetailArr"] = removeRouterNodeDetailArr;
		obj["removeRouterArr"] = removeRouterArr;

		console.log(JSON.stringify(obj));
		//开始ajax操作，data中传的是表单中的参数
		$("#routerEditForm").ajaxSubmit({
			type: "POST",//提交类型
			dataType: "json",//返回结果格式
			url: "/iwuliu/routerManager/upDateRouterInfo",//请求地址
			data: {"list": JSON.stringify(obj)},
			async: true,
			success: function (data) {
				var obj = eval(data);
				if (obj.result == 0) {
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("保存成功！")
					$("#smallModalInfo").on("hidden.bs.modal", function (e) {
						$("body").addClass("modal-open");
						$('#myModal').modal('hide');
						routerManagerQuery();
					})
				} else if (obj.result == 2) {
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("信息不存在！");
					$("#smallModalInfo").on("hidden.bs.modal", function (e) {
						$("body").addClass("modal-open");
					})
					return false;
				} else if (obj.result == 3) {
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("干线重复！");
					$("#smallModalInfo").on("hidden.bs.modal", function (e) {
						$("body").addClass("modal-open");
					})
					return false;
				} else {
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("保存失败！");
					$("#smallModalInfo").on("hidden.bs.modal", function (e) {
						$("body").addClass("modal-open");
					})
					return false;
				}
			},
			error: function () {
				//bootstrap提示框
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("系统异常！");
				$("#smallModalInfo").on("hidden.bs.modal", function (e) {
					$("body").addClass("modal-open");
				})
				return false;
			}
		});
	} else {
		//bootstrap提示框
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请填写完整信息！")
		$("#smallModalInfo").on("hidden.bs.modal", function (e) {
			$("body").addClass("modal-open");
		})
		return false;
	}
}

// 点击删除按钮
function routerDelete() {
	$(".titleInfoDelet").html("确定是否删除整条干线？")
	$("#smallModalInfoDelet").modal();
	$(".confirmDelet").click(function () {
		console.log("joe-line_id:"+ line_id);
		$.ajax({
			type: "post",
			url: "/iwuliu/routerManager/delRouterInfo?line_id=" + line_id,
			data: line_id,
			dataType: "json",
			async: true,
			success: function (object) {
				// json 类型的专为对象
				var obj = eval(object);
				//  reload the user data
				if (obj.result == 0) {
					routerManagerQuery
					$(".titleInfo").html("删除成功！");
					$("#smallModalInfo").modal();
					$("body").addClass("modal-open");
					$('#myModal').modal('hide');
				} else {
					$(".titleInfo").html("删除失败！");
					$("#smallModalInfo").modal();
				}
			},
			error: function () {
				$(".titleInfo").html("系统错误！")
				$("#smallModalInfo").modal();
			}
		});
	})
}



// 不能为空的正则校验(input输入框使用)
function checkNotNull(inp) {
	var _value = inp.val();
	var _p = $(inp).parents(".form-group").find("p");
	if (_value != '') {
		console.log(_value)
		_p.html("");
		return true;
	} else {
		_p.css("color", "red");
		_p.html("不能为空！");
		return false;
	}
}
// 不能为空的正则校验(span输入框使用)
function checkSpanNotNull(inp) {
	var _value = inp.text();
	var _p = $(inp).parents(".form-group").find("p");
	if (_value != '') {
		console.log(_value)
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
	var _value = inp.attr("value");
	var _p = $(inp).parents(".display_inlineBlcok").find("p");
	if (_value != '' && _value != undefined) {
		console.log(_value)
		_p.html("");
		return true;
	} else {
		_p.css("color", "red");
		_p.html("不能为空！");
		return false;
	}
}