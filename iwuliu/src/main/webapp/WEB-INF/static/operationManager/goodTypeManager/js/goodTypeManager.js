$(function(){
	// 加载当前页面菜单
	$(".leftMenu").load("/iwuliu/welcomeManager/home",function(){
		$(".goodTypeManager").parents(".collapse").addClass("in");
		$(".goodTypeManager").addClass("menuActive");
		roleManager();
	});
	$("#smallModalInfo").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open");
	});
	//点击编辑按钮
	$(".edit").bind("click",function(){
		var _matchGoodType = $(".matchGoodType");
		if(_matchGoodType.length == 1){
			$(".glyphicon-plus").removeClass("hidden");
		}else{
			$(".glyphicon").removeClass("hidden");
		}
		//编辑时获取货物类型
		$.ajax({
			type: 'post',
			url: '/iwuliu/truckCarriageTypeManager/truck_carriage_type_idJson',
			dataType: 'json',
			cache: 'false',
			success: function (data) {
				var carTypeValue = '';
				for (var i= 0;i<data.length;i++){
					carTypeValue+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
				}
				for (var k = 0;k<$(".matchGoodType").length;k++){
					$($(".matchGoodType")[k]).find(".placeHolder").val(carTypeNameTions[k]);
					$($(".matchGoodType")[k]).find(".placeHolder").attr("value",carTypeIdTions[k]);
					//cargoName变成下拉
					$($(".matchGoodType")[k]).find(".dropdown-menu").html(carTypeValue);
					var carTypeList=new customDropDown($($(".matchGoodType")[k]));
					$($(".matchGoodType")[k]).find(".dropdown-menu>li>a").bind("click",function(){
						$($(".matchGoodType")[k]).find(".placeHolder").val($(this).text())
					})
				}
			},
			error: function () {
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("系统错误！")
				$("#smallModalInfo").on("hidden.bs.modal",function(e){
					$("body").addClass("modal-open");
				});
				return false;
			}
		});

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
		$("#myModal").find(".modalFooter").addClass("elementDisplayNone")
	});
	//新增模态框消失
	$("#myNewAddModal").on("hidden.bs.modal",function(){
		$("#myNewAddModal").find("input").val("");
		$("#myNewAddModal").find("p").html("");
		$("#myNewAddModal").find(".placeHolder").html("请选择");
	});
	//新增匹配模态框消失
	$("#ModalMatch").on("hidden.bs.modal",function(){
		$("#ModalMatch").find("input").val("");
		$("#ModalMatch").find("p").html("");
		$("#ModalMatch").find(".placeHolder").html("请选择");
		$(".newMatchCarType").remove()
	});

});

//管理页面获取车厢类型
var CarType;//获取车厢类型下拉
var str="";//拼接下拉
$.ajax({
	type:'post',
	url:'/iwuliu/truckCarriageTypeManager/truck_carriage_type_idJson',
	dataType:'json',
	cache:'false',
	success:function(data){
		for (var i= 0;i<data.length;i++){
			str+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
		}
		$(".goodTypeList").find(".dropdown-menu").html(str);
		//
		CarType = new customDropDown($(".goodTypeList"));
	},
	error:function(){
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("系统错误！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
		return false;
	}
});

//重置
function resetGoodType(){
	$(".goodTypeList").find(".placeHolder").html("请选择")
	$(".goodTypeList").find(".placeHolder").removeAttr("value")
	$(".goodTypeList").find(".dropdown-menu").html(str);
	goodType = new customDropDown($(".goodTypeList"));
	$('#cargoTypeTable').bootstrapTable('removeAll');
}
//点击匹配货物类型
function matchGood(){
	$('#cargoTypeTable').bootstrapTable('refresh',{'url':'/iwuliu/goodAndCarMatchManager/MatchList'});
}

var carTypeIdTions=[];//车厢类型Id
var carTypeNameTions=[];//货物类型Id
//点击详情按钮
var _listLength=0;//货物的匹配个数
var commonCargoTypeId;//详情时的货物类型id
function btn_ModalDetail(){
	var selects = $('#cargoTypeTable').bootstrapTable('getSelections'); //获取表选择的行
	if (selects.length == 1) {
		var newSelects = $.parseJSON(JSON.stringify(selects));//将获取的行转化为json对象，注意selects，而不是selects[0]
		commonCargoTypeId= newSelects[0].cargoTypeId;
		carTypeIdTions.length=0;
		carTypeNameTions.length=0;
		$.ajax({
			type:"post",
			url:"/iwuliu/goodAndCarMatchManager/getTruckTypeByCargoId",
			data:{"cargoId":commonCargoTypeId},
			success:function(data){
				var JsonData =JSON.parse(data);
				var _append='';
				$("#cargoTypeUnitC").val(JsonData.cargoTypeUnitC);
				$("#cargoDesc").val(JsonData.cargoTypeDesc);
				$("#cargoName").val(JsonData.cargoTypeName);
				$("#cargoTypeUnitE").val(JsonData.cargoTypeUnitE);
				_listLength = JsonData.list.length;
				for(var i = 0;i<_listLength ;i++){
					carTypeIdTions.push(JsonData.list[i].truckTypeId);
					carTypeNameTions.push(JsonData.list[i].typeName)
					var num = i;
					num++;
					_append  +='<div class="form-group uneditable matchGoodType">'+
							'<label>匹配货物类型'+num+':'+'</label>'+
							'<div class="display_inlineBlcok">'+
							'<button disabled class="btn form-control btn-default dropdown-toggle cars" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">'+
							'<span class="placeHolder">'+JsonData.list[i].typeName+'</span>'+
							'<span class="caret"></span>'+
							'</button>'+
							'<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">'+
							'</ul>'+
							'<p></p>'+
							'</div>'+
							'<span onclick="addcarType(this)" class="glyphicon glyphicon-plus hidden margin-right"></span>'+
							'<span onclick="removecarType(this)" class="glyphicon glyphicon-minus hidden"></span>'+
							'</div>'
				}
				$("#myModal").find("form").append(_append)
			}
		});
		$("#myModal").modal();
	} else {
		//bootstrap提示框
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请选择一条信息！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		})
		return false;
	}
}
//编辑详情时保存
function saveEdit(){
	var cargoTypeInfo = {};
	var _goodType_matchCar = [];
	cargoTypeInfo["cargoType"]=commonCargoTypeId;
	var goodTypeLength=$(".matchGoodType").find(".cars").length;
	for (var i=0;i<goodTypeLength;i++){
		_goodType_matchCar.push($($(".matchGoodType").find(".cars").find(".placeHolder")[i]).attr("value"));
	}
	cargoTypeInfo["cargoTypeDesc"]=$("#cargoDesc").val();
	cargoTypeInfo["cargoTypeE"]=$("#cargoTypeUnitE").val();
	cargoTypeInfo["cargoTypeC"]=$("#cargoTypeUnitC").val();
	//flag 为1时匹配新增操作 为0时更新操作方便后台共用方法
	cargoTypeInfo["flag"]="0";
	cargoTypeInfo["_goodType_matchCar"] = _goodType_matchCar;
	var  stringObj=JSON.stringify(cargoTypeInfo);
	$.ajax({
		type:"post",
		url:"/iwuliu/goodAndCarMatchManager/updateMatch",
		data:{"list":stringObj},
		async:true,
		success:function(data){
			var obj = eval(data);
			if(obj.result==1){
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("保存成功！")
				$("#smallModalInfo").on("hidden.bs.modal",function(e){
					$("body").addClass("modal-open");
					$('#myModal').modal('hide')
				});
				$('#cargoTypeTable').bootstrapTable('refresh');
			}else if (obj.result==0){
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("保存重复！")
				$("#smallModalInfo").on("hidden.bs.modal",function(e){
					$("body").addClass("modal-open");
				});
			}else{
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("保存失败！")
				$("#smallModalInfo").on("hidden.bs.modal",function(e){
					$("body").addClass("modal-open");
				});
			}
		},
		error: function () {
			$("#smallModalInfo").modal();
			$("#smallModalInfo").find(".titleInfo").html("保存失败！")
			$("#smallModalInfo").on("hidden.bs.modal",function(e){
				$("body").addClass("modal-open");
			});
		}
	});
	
}

//点击+号添加匹配货物类型
function addcarType(ele){
	var _parent = $(ele).parents(".matchGoodType");
	_parent.after('<div class="form-group matchGoodType">'+ _parent.html() +'</div>');
	var _matchGoodType = $(".matchGoodType");
	if(_matchGoodType.length>=1){
		$(".glyphicon").removeClass("hidden");
	}
	for(var i = 0;i<_matchGoodType.length;i++){
		new customDropDown($($(".matchGoodType")[i]));
	}
}
//点击—号删除匹配货物类型
function removecarType(ele){
	var _matchGoodType = $(".matchGoodType");
	var _parent = $(ele).parents(".matchGoodType");
	if(_matchGoodType.length == 2){
		_parent.remove();
		$(".glyphicon-minus").addClass("hidden");
	}else{
		_parent.remove();
	}
}

//点击新增按钮
function btn_ModalNewAdd(){
	$("#myNewAddModal").modal();
}
//新增保存货物类型
function  saveNewCargoType(){
	if (checkNotNull($("#cargoTypeName"))&checkNotNull($("#cargoTypeE"))&checkNotNull($("#cargoTypeC"))){
		var cargoTypeName= $("#cargoTypeName").val();
		var cargoTypeDesc= $("#cargoDescs").val();
		var cargoTypeE= $("#cargoE").val();
		var cargoTypeC= $("#cargoC").val();
		var _goodTypeInfo = {};
		_goodTypeInfo["cargoType"] = cargoTypeName;
		_goodTypeInfo["cargoDesc"] = cargoTypeDesc;
		_goodTypeInfo["UnitC"] = cargoTypeC;
		_goodTypeInfo["UnitE"] = cargoTypeE;
		var  stringObj=JSON.stringify(_goodTypeInfo);
		$.ajax({
			type:"post",
			url:"/iwuliu/goodTypeManager/saveGoodType",
			data:{"list":stringObj},
			async:true,
			success:function(data){
				var obj = eval(data);
				if(obj.result==1){
					//bootstrap提示框
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("保存成功！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
						$('#myNewAddModal').modal('hide');
					})
					$('#cargoTypeTable').bootstrapTable('refresh');
				}else if (obj.result==0){
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("重复添加！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});
					return false;
				}else{
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("保存失败！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});
					return false;
				}
			},
			error:function(){
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("系统错误！")
				$("#smallModalInfo").on("hidden.bs.modal",function(e){
					$("body").addClass("modal-open");
				});
				return false;
			}
		});
	}else {
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请填写带*字段！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		})
	}

}
//打开新增匹配页面
function btn_ModalMatch(){
	$("#ModalMatch").modal();
	var matchCarType = new customDropDown($(".matchCarType"));
	//新增货物和车厢类型匹配获取下拉
	$.ajax({
		type:'post',
		url:'/iwuliu/goodTypeManager/cargoTypeList',
		dataType:'json',
		cache:'false',
		success:function(data){
			var cargoTypeList="";
			for (var i= 0;i<data.length;i++){
				cargoTypeList+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
			}
			$('.cargoTypeList').find(".dropdown-menu").html(cargoTypeList);
			//赋值后生成下拉菜单
			var cargoTypeSelect = new customDropDown($(".cargoTypeList"));
			$(".cargoTypeList").find(".dropdown-menu li").bind("click",function(){
				var cargoTypeName=$(this).attr("value");
				if (cargoTypeName!=""&&cargoTypeName!='undefined'){
					//获取货物类型信息
					$.ajax({
						type: "POST",
						url: "/iwuliu/goodTypeManager/findCargoById",
						data:{"cargoId":cargoTypeName},
						cache: false,
						dataType: "json",
						success: function (data) {
							$("#cargoTypeDesc").val(data.cargoType.cargoTypeDesc);
							$("#cargoTypeC").val(data.cargoType.cargoTypeUnitC);
							$("#cargoTypeE").val(data.cargoType.cargoTypeUnitE);
						},
						error: function () {
							$("#smallModalInfo").modal();
							$("#smallModalInfo").find(".titleInfo").html("系统错误！")
							$("#smallModalInfo").on("hidden.bs.modal",function(e){
								$("body").addClass("modal-open");
							});
							return false;
						}
					});
					//根据货物获取车厢类型
					$.ajax({
						type:'post',
						url:"/iwuliu/goodAndCarMatchManager/getCarriageTypeByCargoId",
						data:{"CargoId":cargoTypeName},
						dataType:'json',
						cache:false,
						success: function (data) {
							var truckTypeValue = '';
							for (var i= 0;i<data.length;i++){
								truckTypeValue+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
							}
							$(".matchCarType").find(".dropdown-menu").html(truckTypeValue);
							var matchCarType=new customDropDown($(".matchCarType"))
							$(".matchCarType").find(".dropdown-menu>li>a").bind("click",function(){
								$(".matchCarType").find(".placeHolder").val($(this).text())
							})
						},
						error: function () {
							$("#smallModalInfo").modal();
							$("#smallModalInfo").find(".titleInfo").html("系统错误！")
							$("#smallModalInfo").on("hidden.bs.modal",function(e){
								$("body").addClass("modal-open");
							});
							return false;
						}
					});
				}
			})

		},
		error:function(){
			$("#smallModalInfo").modal();
			$("#smallModalInfo").find(".titleInfo").html("系统错误！")
			$("#smallModalInfo").on("hidden.bs.modal",function(e){
				$("body").addClass("modal-open");
			});
			return false;
		}
	});

}
//新增匹配
function newAddMatch(ele){
	var _matchCarType = $(ele).parents(".matchCarType");
	_matchCarType.after('<div class="form-group matchCarType">'+ _matchCarType.html() +'</div>');
	var matchCarType = $(".matchCarType")
	for(var i = 0;i<matchCarType.length;i++){
		new customDropDown($($(".matchCarType")[i]));
	}
}

//保存货物类型匹配
function  saveGoodTruckMatch(){
	//获取车厢类型的value
	var cargo=$(".cargoTypeList").find(".placeHolder").attr("value");
	if (cargo!="" && cargo!=undefined){
		var _goodTypeInfo = {};
		var _goodType_matchCar = [];
		_goodTypeInfo["cargoType"]=cargo;
		_goodTypeInfo["cargoTypeDesc"]=$("#cargoTypeDesc").val();
		_goodTypeInfo["cargoTypeC"]=$("#cargoTypeC").val();
		_goodTypeInfo["cargoTypeE"]=$("#cargoTypeE").val();
		//获取货物类型的长度
		var goodTypeLength=$(".matchCarType").find(".CarType").length;
		for (var i=0;i<goodTypeLength;i++){
			_goodType_matchCar.push($($(".matchCarType").find(".CarType").find(".placeHolder")[i]).attr("value"));
		}
		//后台判断flag 为1时匹配新增操作 为0时更新操作方便后台共用方法
		_goodTypeInfo["flag"]="1";
		_goodTypeInfo["_goodType_matchCar"] = _goodType_matchCar;
		var stringObj = JSON.stringify(_goodTypeInfo);
		$.ajax({
			type:"post",
			url:"/iwuliu/goodAndCarMatchManager/saveMatch",
			data:{"list":stringObj},
			async:true,
			success:function(data){
				var obj = eval(data);
				if(obj.result==1){
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("保存成功！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
						$('#ModalMatch').modal('hide')
					});
					$('#cargoTypeTable').bootstrapTable('refresh');
				}else if (obj.result==0){
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("保存重复！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});
				}else{
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("保存失败！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});
				}
			},
			error: function () {
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("保存失败！")
				$("#smallModalInfo").on("hidden.bs.modal",function(e){
					$("body").addClass("modal-open");
				});
			}
		});
	}else {
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("请填写货物类型！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
		return false;
	}


}

//删除勾选的货物类型
function deleteCargoType(){
	var selectTions = $('#cargoTypeTable').bootstrapTable('getSelections'); //获取表选择的行
	if (selectTions.length !=0) {
		var cargoTypeID = "";//存储多个删除的id
		for (var i = 0; i < selectTions.length; i++) {
			if (i == 0) {
				cargoTypeID = selectTions[i].cargoTypeId;
			} else {
				cargoTypeID += "," + selectTions[i].cargoTypeId;
			}
		}
		$("#smallModalInfoDelete").modal();
		$("#smallModalInfoDelete").find(".titleInfoDelete").html("确定删除货物类型及匹配关系？")
		//点击确定删除
		$(".deleteCargoType").click(function () {
			//发送请求
			$.ajax({
				type: "post",
				url: "/iwuliu/goodTypeManager/cargoTypeDel",
				data:{"cargoTypeID":cargoTypeID},
				dataType:"json",
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
						$('#cargoTypeTable').bootstrapTable('refresh');
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
		$("#smallModalInfo").find(".titleInfo").html("请选择数据！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
	}
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
//goBtn按钮无数据时隐藏
var webservice = $('#cargoTypeTable').attr("data-url");
$.ajax({
	url:webservice,
	success:function(data){
		if(data.length == 0){
			$(".form-inline").css("display","none");
		}
	}
});
//配置参数
function transportationGoodParams(params) {
	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageSize: params.limit,   //页面大小
		pageNumber: params.pageNumber,  //页码
		truckTypeId: $(".goodTypeList").find(".placeHolder").attr("value"),
		sort: params.sort,  //排序列名
		sortOrder: params.order//排位命令（desc，asc）
	};
	return temp;
}
// 不能为空的校验
function  checkNotNull(inp){
	var _value = inp.val();
	if (_value == ''||_value == undefined){
		inp.css("border","1px solid red");
		return false;
	}else{
		inp.css("border","none");
		return true;
	}
}