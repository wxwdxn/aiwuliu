//手机号正则
var regExpMob = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/; //手机号码正则验证准确版

var numbers= /^\+?[1-9][0-9]*$/;//数字验证
// 点击“编号”
var contractId;
$(function(){
	// 加载当前页面菜单
	$(".leftMenu").load("/iwuliu/welcomeManager/home",function(){
		$(".operationContractManager").parents(".collapse").addClass("in");
		$(".operationContractManager").addClass("menuActive");
		roleManager();
	});
	$("#smallModalInfo").on("hidden.bs.modal",function(e){
		$("body").addClass("modal-open");
	});
	//详情编辑
	$(".edit").bind("click",function(){
		$("#myModalContractDetail").find(".hidden").removeClass("hidden");
	})
	//详情模态框消失
	$("#myModalContractDetail").on("hidden.bs.modal",function(){
		$("#myModalContractDetail").find(".edit").attr("isClick","true");
		$("#myModalContractDetail").find(".edit").removeAttr("disabled");
		$("#myModalContractDetail").find(".edit").css("background","#69a2b5");
		$("#myModalContractDetail").find(".hidden").removeClass("hidden");
		$(".routerInfo").remove();
		$(".newAdd_router").addClass("hidden");
	})
	//新增模态框消失
	$("#ModalContract").on("hidden.bs.modal",function(){
		$("#ModalContract").find("input").val("");
		$("#ModalContract").find("p").html("");
		$("#ModalContract").find(".placeHolder").html("请选择");
		$('.newAdd_routerInfo').remove();
		$(".glyphicon-minus").addClass("hidden");
	})
	var table = $('#transportationContractTable'),
			page = $('#transportationContractTablepage'),
			goBtn = $('#transportationContractTablegoBtn');
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
	var table2 = $('#transportationPlanTable'),
			page2 = $('#transportationPlanTablepage'),
			goBtn2 = $('#transportationPlanTablegoBtn');
	// 导出
	$('#toolbar').find('select').change(function() {
		table.bootstrapTable('refreshOptions', {
			exportDataType: $(this).val()
		});
	});
	// 跳转到某页
	goBtn2.click(function () {
		table2.bootstrapTable('selectPage', +page2.val());
	});

	//获取货物类型
	$.ajax({
		type:'post',
		url:'/iwuliu/goodTypeManager/cargoTypeList',
		dataType:'json',
		cache:'false',
		success:function(data){
			var str = '';
			for (var i= 0;i<data.length;i++){
				str+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
			}
			$(".cargoType").find(".dropdown-menu").html(str);
			var goodType = new customDropDown($(".cargoType"));
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


	//获取公司个人货主类型
	$.ajax({
		type:'post',
		url:"/iwuliu/dictionaryManager/firstPartyTypeJson",
		dataType:'json',
		cache:false,
		success:function(data){
			var strr = '';
			for (var i= 0;i<data.length;i++){
				strr+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
			}
			$(".ownerType").find(".dropdown-menu").html(strr);
			var ownerType = new customDropDown($(".ownerType"));
			$(".ownerType").find(".dropdown-menu li").bind("click",function(){
				var ownerName=$(this).attr("value");
				if (ownerName!=''||ownerName!=undefined){
					if (ownerName=="0"){
						//获取个人货主
						$.ajax({
							type:'post',
							url:"/iwuliu/personManager/personJsonList",
							dataType:'json',
							cache:false,
							success:function(data){
								var str = '';
								for (var i= 0;i<data.length;i++){
									str+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
								}
								$(".owner").find(".dropdown-menu").html(str);
								var owner = new customDropDown($(".owner"));
							},
							error:function(){
								$("#smallModalInfo").modal();
								$("#smallModalInfo").find(".titleInfo").html("查询个人货主失败！")
								$("#smallModalInfo").on("hidden.bs.modal",function(e){
									$("body").addClass("modal-open");
								});
							}
						});
					}else if (ownerName=="1") {//获取公司货主
						$.ajax({
							type: 'post',
							url: "/iwuliu/companyManager/CompanyShippers",
							dataType: 'json',
							cache: false,
							success: function (data) {
								var str = '';
								for (var i= 0;i<data.length;i++){
									str+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
								}
								$(".owner").find(".dropdown-menu").html(str);
								var owner = new customDropDown($(".owner"));
							},
							error: function () {
								$("#smallModalInfo").modal();
								$("#smallModalInfo").find(".titleInfo").html("查询公司货主失败！")
								$("#smallModalInfo").on("hidden.bs.modal",function(e){
									$("body").addClass("modal-open");
								});
							}
						});
					}
				}else {
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("请选择货主类型！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
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


//结算方式
	$.ajax({
		type:'post',
		url:"/iwuliu/dictionaryManager/balanceType",
		dataType:'json',
		cache:false,
		success:function(data){
			var _str = '';
			for (var i= 0;i<data.length;i++){
				_str+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
			}
			$(".payType").find(".dropdown-menu").html(_str);
			var payType = new customDropDown($(".payType"));
		},
		error:function(){
			$("#smallModalInfo").modal();
			$("#smallModalInfo").find(".titleInfo").html("查询结算失败！")
			$("#smallModalInfo").on("hidden.bs.modal",function(e){
				$("body").addClass("modal-open");
			});
		}
	});

	//获取干线
	$.ajax({
		type:'post',
		url:"/iwuliu/operateMainLineManager/operateMainLines",
		dataType:'json',
		cache:false,
		success:function(data){
			var _strValue = '';
			for (var i= 0;i<data.length;i++){
				_strValue+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
			}
			$(".mainLine").find(".dropdown-menu").html(_strValue);
			var mainLine = new customDropDown($(".mainLine"));

			$(".mainLine").find(".dropdown-menu li").bind("click",function(){
				var mainLineName = $(this).attr("value");
				var loadFreightYard = $(this).parents(".routerInfo").find(".loadFreightYard");
				var unloadFreightYard = $(this).parents(".routerInfo").find(".unloadFreightYard");
				if (mainLineName!=''||mainLineName!='undefined'){
					//获取装货货场
					$.ajax({
						type: "POST",
						url: "/iwuliu/freightYardManager/loadCargoByLineId",
						cache: false,
						data:{"operate_main_line_id":mainLineName},
						dataType: "json",
						success: function (data) {
							if (data.length!=0){
								var _strd = '';
								for (var i= 0;i<data.length;i++){
									_strd+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
								}
								loadFreightYard.find(".dropdown-menu").html(_strd);
								var loadFreightYardSelect = new customDropDown(loadFreightYard);
								loadFreightYard.find(".placeHolder").html('请选择');
								unloadFreightYard.find(".placeHolder").html('请选择');
								//获取卸货货场
								$.ajax({
									type: "POST",
									url: "/iwuliu/freightYardManager/unloadCargoByLineId" ,
									cache: false,
									data:{"operate_main_line_id":mainLineName},
									dataType: "json",
									success: function (data) {
										if (data.length!=0){
											var _strs = '';
											for (var i= 0;i<data.length;i++){
												_strs+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
											}
											unloadFreightYard.find(".dropdown-menu").html(_strs);
											var unloadFreightYardSelect = new customDropDown(unloadFreightYard);
											loadFreightYard.find(".placeHolder").html('请选择');
											unloadFreightYard.find(".placeHolder").html('请选择');
										}else {
											unloadFreightYard.find(".placeHolder").removeAttr("value")
											loadFreightYard.find(".placeHolder").removeAttr("value")
											loadFreightYard.find(".placeHolder").html('无');
											unloadFreightYard.find(".placeHolder").html('无');
											//当前干线没有卸货货场
											$("#smallModalInfo").modal();
											$("#smallModalInfo").find(".titleInfo").html("当前干线没有卸货货场！")
											$("#smallModalInfo").on("hidden.bs.modal",function(e){
												$("body").addClass("modal-open");
											});
										}
									}
								});
							}else {
								unloadFreightYard.find(".placeHolder").removeAttr("value")
								loadFreightYard.find(".placeHolder").removeAttr("value")
								loadFreightYard.find(".placeHolder").html('无');
								unloadFreightYard.find(".placeHolder").html('无');
								//当前干线没有装货货场
								$("#smallModalInfo").modal();
								$("#smallModalInfo").find(".titleInfo").html("当前干线没有装货货场！")
								$("#smallModalInfo").on("hidden.bs.modal",function(e){
									$("body").addClass("modal-open");
								});
							}
						}
					})
				}
				})

		},
		error:function(){
			$("#smallModalInfo").modal();
			$("#smallModalInfo").find(".titleInfo").html("获取干线出错！")
			$("#smallModalInfo").on("hidden.bs.modal",function(e){
				$("body").addClass("modal-open");
			});
		}
	});


	//合同时间
	timeChoose($('.startDate'),$('.endDate'),$('.start_date'),$('.end_date'));
//时间函数 end_Date
	function timeChoose(startTime,endTime,start_date,end_date){
		startTime.datetimepicker({
			format: 'yyyy-mm-dd hh:ii:00',
			language: 'zh-CN',
			autoclose: true,//选中之后自动隐藏日期选择框
			hourStep: 1,
			minuteStep: 30,
			todayBtn:true,
			//format: 'hh:ii',
			//startView:1,
			//minView:'hour',
			//keyboardNavigation:false,
		});
		endTime.datetimepicker({
			format: 'yyyy-mm-dd hh:ii:00',
			autoclose: true,//选中之后自动隐藏日期选择框
			language: 'zh-CN',
			hourStep: 1,
			minuteStep: 30,
			todayBtn:true,
		});
		start_date.datetimepicker({
			format: 'yyyy-mm-dd hh:ii:00',
			language: 'zh-CN',
			autoclose: true,//选中之后自动隐藏日期选择框
			hourStep: 1,
			minuteStep: 30,
			todayBtn:true
		});
		end_date.datetimepicker({
			format: 'yyyy-mm-dd hh:ii:00',
			autoclose: true,//选中之后自动隐藏日期选择框
			language: 'zh-CN',
			hourStep: 1,
			minuteStep: 30,
			todayBtn:true
		});
	}


//保存新合同信息
	$(".hold").click(function(){
		var code=$("#code").val();
		var cargoType=$(".cargoType").find(".placeHolder").attr("value");
		var ownerType=$(".ownerType").find(".placeHolder").attr("value");
		var first_party_relevance_info_id=$(".owner").find(".placeHolder").attr("value");
		var payType=$(".payType").find(".placeHolder").attr("value");
		var contact_name=$("#contact_name").val();
		var contact_phone=$("#contact_phone").val();
		var start_date=$("#start_date").val();
		var end_date=$("#end_date").val();
		var mainLine=$(".newAdd_routerInfo ").find(".mainLine").find(".placeHolder").attr("value");
		if (checkNotNull($("#code"))&checkSelectNotNull($(".cargoType"))&checkSelectNotNull($(".owner"))&checkSelectNotNull($(".ownerType"))
				&checkSelectNotNull($(".payType"))&checkNotNull($("#contact_name"))&checkNotNull($("#contact_phone"))&checkNotNull($("#start_date"))
				&checkNotNull($("#end_date"))){
			//获取干线的个数
			var _length = $(".newAdd_routerInfo").length;
			var _newAdd_routerInfo = $(".newAdd_routerInfo");
			var _obj = {};
			var _ganxian = [];
			var _line = {};
			var _contractArr = {};
			_contractArr["code"] = code;
			_contractArr["cargo_type_id"] = cargoType;
			_contractArr["first_party_type"] = ownerType;
			_contractArr["first_party_relevance_info_id"] = first_party_relevance_info_id;
			_contractArr["balance_type"] = payType;
			_contractArr["contact_name"] = contact_name;
			_contractArr["contact_phone"] = contact_phone;
			_contractArr["start_date"] = start_date;
			_contractArr["end_Date"] = end_date;
			//无干线只保留合同
			_obj["htbh"] = _contractArr;
			if ( mainLine=="" ||mainLine==undefined){
				_line["ganxian"] = _ganxian;
				_obj["line"] = _line;
				var stringObj = JSON.stringify(_obj);
				$.ajax({
					type:"post",
					url:"/iwuliu/operationContractManager/saveContractPlan",
					data:{"list":stringObj},
					async:true,
					success:function(data){
						var obj = eval(data);
						if(obj.result==1){
							$("#smallModalInfo").modal();
							$("#smallModalInfo").find(".titleInfo").html("保存成功！")
							$("#smallModalInfo").on("hidden.bs.modal",function(e){
								$("body").addClass("modal-open");
								$("#ModalContract").modal('hide')
								$('#transportationContractTable').bootstrapTable('refresh');
							});
						}else if (obj.result==0){
							$("#smallModalInfo").modal();
							$("#smallModalInfo").find(".titleInfo").html("重复添加！")
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
					error:function(){
						$("#smallModalInfo").modal();
						$("#smallModalInfo").find(".titleInfo").html("系统错误！")
						$("#smallModalInfo").on("hidden.bs.modal",function(e){
							$("body").addClass("modal-open");
						});
					}
				});
			}else {
				//保存合同和干线
				if(checkNotNull($(".newAdd_routerInfo ").find(".startDate"))&checkNotNull($(".newAdd_routerInfo ").find(".loading_contact_name"))&loadPhone($(".newAdd_routerInfo ").find(".loading_contact_phone"))
						&checkNotNull($(".newAdd_routerInfo ").find(".endDate"))&checkNotNull($(".newAdd_routerInfo ").find(".unloading_contact_name"))&loadPhone($(".newAdd_routerInfo ").find(".unloading_contact_phone"))
						&checkNotNull($(".newAdd_routerInfo ").find(".cargo_total"))&checkNotNull($(".newAdd_routerInfo ").find(".transport_unit_price"))&checkSelectNotNull($(".newAdd_routerInfo ").find(".mainLine"))&checkSelectNotNull($(".newAdd_routerInfo ").find(".loadFreightYard"))
						&checkSelectNotNull($(".newAdd_routerInfo ").find(".unloadFreightYard"))){
					for(var i = 0;i<_length;i++){
						var _inputs = $(_newAdd_routerInfo[i]).find("input");
						var _selects = $(_newAdd_routerInfo[i]).find(".placeHolder");
						var _inputLength = $(_newAdd_routerInfo[i]).find("input").length;
						var _selectLength = $(_newAdd_routerInfo[i]).find(".placeHolder").length;
						var _arr = {};
						for(var j = 0;j<_selectLength;j++){
							var _addAttr = _selects[j].getAttribute("addAttr");
							var _value = _selects[j].getAttribute("value");
							_arr[_addAttr] = _value;
						}
						for(var j = 0;j<_inputLength;j++){
							var _addAttr = _inputs[j].getAttribute("addAttr");
							var _value = _inputs[j].value;
							_arr[_addAttr] = _value;
						}
						_ganxian.push(_arr)
					}
					_line["ganxian"] = _ganxian;
					_obj["line"] = _line;
					var stringObj = JSON.stringify(_obj);
					$.ajax({
						type:"post",
						url:"/iwuliu/operationContractManager/saveContractPlan",
						data:{"list":stringObj},
						async:true,
						success:function(data){
							var obj = eval(data);
							if(obj.result==1){
								$("#smallModalInfo").modal();
								$("#smallModalInfo").find(".titleInfo").html("保存成功！")
								$("#smallModalInfo").on("hidden.bs.modal",function(e){
									$("body").addClass("modal-open");
									$("#ModalContract").modal('hide')
									$('#transportationContractTable').bootstrapTable('refresh');
								});
							}else if (obj.result==0){
								$("#smallModalInfo").modal();
								$("#smallModalInfo").find(".titleInfo").html("重复添加！")
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
						error:function(){
							$("#smallModalInfo").modal();
							$("#smallModalInfo").find(".titleInfo").html("系统错误！")
							$("#smallModalInfo").on("hidden.bs.modal",function(e){
								$("body").addClass("modal-open");
							});
						}
					});
				}else {//验证输入不正确
					$("#smallModalInfo").modal();
					$("#smallModalInfo").find(".titleInfo").html("输入不正确！")
					$("#smallModalInfo").on("hidden.bs.modal",function(e){
						$("body").addClass("modal-open");
					});
				}

			}
		}else {
			$("#smallModalInfo").modal();
			$("#smallModalInfo").find(".titleInfo").html("输入不正确！")
			$("#smallModalInfo").on("hidden.bs.modal",function(e){
				$("body").addClass("modal-open");
			});
		}

	});
	//更新合同信息保存
	$(".updateHold").click(function(){
		//获取干线的个数
		var _length = $(".newAdd_routerInfo").length;
		var _newAdd_routerInfo = $(".newAdd_routerInfo");
		var list = {};
		var newAddArr = [];
		var _price = $(".contractDetail").find(".routerInfo").find(".everyPrice");
		var priceArr = [];
		//获取价格
		for(var i = 0;i<_price.length;i++){
			var priceList = {};
			var attr = $(_price[i]).attr("addattr");
			priceList["schedulePlanNumber"] = attr;
			priceList["price"] = $(_price[i]).val();
			priceArr.push(priceList)
		}
		list["price"] = priceArr;
		//查询是否有新的干线生成
		var newMainLine =$(".newAdd_routerInfo ").find(".mainLine").find(".placeHolder").attr("value");
		if (newMainLine!=undefined){
			if(checkNotNull($(".newAdd_routerInfo ").find(".startDate"))&checkNotNull($(".newAdd_routerInfo ").find(".loading_contact_name"))&loadPhone($(".newAdd_routerInfo ").find(".loading_contact_phone"))
					&checkNotNull($(".newAdd_routerInfo ").find(".endDate"))&checkNotNull($(".newAdd_routerInfo ").find(".unloading_contact_name"))&loadPhone($(".newAdd_routerInfo ").find(".unloading_contact_phone"))
					&checkNotNull($(".newAdd_routerInfo ").find(".cargo_total"))&checkNotNull($(".newAdd_routerInfo ").find(".transport_unit_price"))&checkSelectNotNull($(".newAdd_routerInfo ").find(".mainLine"))&checkSelectNotNull($(".newAdd_routerInfo ").find(".loadFreightYard"))
					&checkSelectNotNull($(".newAdd_routerInfo ").find(".unloadFreightYard"))){
				newAddArr.length=0;
				for(var i = 0;i<_length;i++){
					var _inputs = $(_newAdd_routerInfo[i]).find("input");
					var _selects = $(_newAdd_routerInfo[i]).find(".placeHolder");
					var _inputLength = $(_newAdd_routerInfo[i]).find("input").length;
					var _selectLength = $(_newAdd_routerInfo[i]).find(".placeHolder").length;
					var _arr = {};
					for(var j = 0;j<_selectLength;j++){
						var _addAttr = _selects[j].getAttribute("addAttr");
						var _value = _selects[j].getAttribute("value");
						_arr[_addAttr] = _value;
					}
					for(var j = 0;j<_inputLength;j++){
						var _addAttr = _inputs[j].getAttribute("addAttr");
						var _value = _inputs[j].value;
						_arr[_addAttr] = _value;
					}
					newAddArr.push(_arr);
				}
				list["newAdd"] = newAddArr;
				list["contractId"] = contractId;
				var info = {};
				info["info"]=list;
				var info2=JSON.stringify(info);
				$.ajax({
					type:"post",
					url:"/iwuliu/operationContractManager/updateContractOrAddLineInfo",
					data:{"list":info2},
					async:true,
					success:function(data){
						var obj = eval(data);
						if(obj.result==1){
							$("#smallModalInfo").modal();
							$("#smallModalInfo").find(".titleInfo").html("更新成功！")
							$("#smallModalInfo").on("hidden.bs.modal",function(e){
								$("body").addClass("modal-open");
								$("#myModalContractDetail").modal('hide')
								$('#transportationContractTable').bootstrapTable('refresh');
							});
						}else if (obj.result==0){
							$("#smallModalInfo").modal();
							$("#smallModalInfo").find(".titleInfo").html("重复添加！")
							$("#smallModalInfo").on("hidden.bs.modal",function(e){
								$("body").addClass("modal-open");
							});
						}else{
							$("#smallModalInfo").modal();
							$("#smallModalInfo").find(".titleInfo").html("更新失败！")
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
			}
		}else {
			newAddArr.length=0;
			$(".routerInfo").find(".everyPrice").val()
			//没有新干线 验证单价
			if (checkNumber($(".routerInfo").find(".everyPrice"))) {
				list["newAdd"] = newAddArr;
				list["contractId"] = contractId;
				var info = {};
				info["info"]=list;
				var info2=JSON.stringify(info);
				$.ajax({
					type:"post",
					url:"/iwuliu/operationContractManager/updateContractOrAddLineInfo",
					data:{"list":info2},
					async:true,
					success:function(data){
						var obj = eval(data);
						if(obj.result==1){
							$("#smallModalInfo").modal();
							$("#smallModalInfo").find(".titleInfo").html("更新成功！")
							$("#smallModalInfo").on("hidden.bs.modal",function(e){
								$("body").addClass("modal-open");
								$("#myModalContractDetail").modal('hide')
								$('#transportationContractTable').bootstrapTable('refresh');
							});
						}else if (obj.result==0){
							$("#smallModalInfo").modal();
							$("#smallModalInfo").find(".titleInfo").html("重复添加！")
							$("#smallModalInfo").on("hidden.bs.modal",function(e){
								$("body").addClass("modal-open");
							});
						}else{
							$("#smallModalInfo").modal();
							$("#smallModalInfo").find(".titleInfo").html("更新失败！")
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
			}else{
				$("#smallModalInfo").modal();
				$("#smallModalInfo").find(".titleInfo").html("输入错误！")
				$("#smallModalInfo").on("hidden.bs.modal",function(e){
					$("body").addClass("modal-open");
				});
			}
		}
	});

})
//新增合同时点击新增干线按钮
function newAddBtn_router(ele){
	var _str = '<div class="routerInfo newAdd_routerInfo ">'+ $(".first_routerInfo").html() +'</div>';
	$(ele).parent().before(_str);
	for(var i = 0;i<$(".mainLine").length;i++){
		new customDropDown($($(".mainLine")[i]));
		//合同时间
		timeChoose($('.startDate'),$('.endDate'),$('.start_date'),$('.end_date'));
//时间函数 end_Date
		function timeChoose(startTime,endTime,start_date,end_date){
			startTime.datetimepicker({
				format: 'yyyy-mm-dd hh:ii:00',
				language: 'zh-CN',
				autoclose: true,//选中之后自动隐藏日期选择框
				hourStep: 1,
				minuteStep: 30,
				todayBtn:true,

			});
			endTime.datetimepicker({
				format: 'yyyy-mm-dd hh:ii:00',
				autoclose: true,//选中之后自动隐藏日期选择框
				language: 'zh-CN',
				hourStep: 1,
				minuteStep: 30,
				todayBtn:true,
			});
			start_date.datetimepicker({
				format: 'yyyy-mm-dd hh:ii:00',
				language: 'zh-CN',
				autoclose: true,//选中之后自动隐藏日期选择框
				hourStep: 1,
				minuteStep: 30,
				todayBtn:true
			});
			end_date.datetimepicker({
				format: 'yyyy-mm-dd hh:ii:00',
				autoclose: true,//选中之后自动隐藏日期选择框
				language: 'zh-CN',
				hourStep: 1,
				minuteStep: 30,
				todayBtn:true
			});
		}
	}
	$(".mainLine").find(".dropdown-menu li").bind("click",function(){
		var mainLineName = $(this).attr("value");
		var loadFreightYard = $(this).parents(".routerInfo").find(".loadFreightYard");
		var unloadFreightYard = $(this).parents(".routerInfo").find(".unloadFreightYard");
		if (mainLineName!=''||mainLineName!='undefined'){
			//获取装货货场
			$.ajax({
				type: "POST",
				url: "/iwuliu/freightYardManager/loadCargoByLineId",
				cache: false,
				data:{"operate_main_line_id":mainLineName},
				dataType: "json",
				success: function (data) {
					if (data.length!=0){
						var _strd = '';
						for (var i= 0;i<data.length;i++){
							_strd+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
						}
						loadFreightYard.find(".dropdown-menu").html(_strd);
						var loadFreightYardSelect = new customDropDown(loadFreightYard);
						console.log(111)
						loadFreightYard.find(".placeHolder").html('请选择');
						unloadFreightYard.find(".placeHolder").html('请选择');
						//获取卸货货场
						$.ajax({
							type: "POST",
							url: "/iwuliu/freightYardManager/unloadCargoByLineId" ,
							cache: false,
							data:{"operate_main_line_id":mainLineName},
							dataType: "json",
							success: function (data) {
								if (data.length!=0){
									var _strs = '';
									for (var i= 0;i<data.length;i++){
										_strs+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
									}
									unloadFreightYard.find(".dropdown-menu").html(_strs);
									var unloadFreightYardSelect = new customDropDown(unloadFreightYard);
									loadFreightYard.find(".placeHolder").html('请选择');
									unloadFreightYard.find(".placeHolder").html('请选择');
								}else {
									unloadFreightYard.find(".placeHolder").removeAttr("value")
									loadFreightYard.find(".placeHolder").removeAttr("value")
									loadFreightYard.find(".placeHolder").html('无');
									unloadFreightYard.find(".placeHolder").html('无');
									//当前干线没有卸货货场
									$("#smallModalInfo").modal();
									$("#smallModalInfo").find(".titleInfo").html("当前干线没有卸货货场！")
									$("#smallModalInfo").on("hidden.bs.modal",function(e){
										$("body").addClass("modal-open");
									});
								}
							}
						});
					}else {
						unloadFreightYard.find(".placeHolder").removeAttr("value")
						loadFreightYard.find(".placeHolder").removeAttr("value")
						loadFreightYard.find(".placeHolder").html('无');
						unloadFreightYard.find(".placeHolder").html('无');
						//当前干线没有装货货场
						$("#smallModalInfo").modal();
						$("#smallModalInfo").find(".titleInfo").html("当前干线没有装货货场！")
						$("#smallModalInfo").on("hidden.bs.modal",function(e){
							$("body").addClass("modal-open");
						});
					}
				}
			})
		}
	})

}

//比较合同起点判断起止时间
function checkContractStarDate(ele){
	var starTime=$(ele).val();
	var endTime=$(ele).parents(".form-inline").find("#end_date").val();
	if (endTime!=""){
		var star=starTime.split("-");
		var ss=star[2].split(" ");
		var startime=new Date(star[0],star[1],ss[0]);
		var stars=startime.getTime();
		var end=endTime.split("-");
		var ends=end[2].split(" ");
		var endtime=new Date(end[0],end[1],ends[0]);
		var ends=endtime.getTime();
		if (ends>stars){
			//开始时间p标签
			$(ele).parent().find("p").html("");
			//终止时间p标签不显示文字
			$(ele).parent().find("p").html("");
		}else {
			//p标签显示文字并且变红
			$(ele).parent().find("p").html("开始时间小于终止时间！");
			$(ele).parent().find("p").css("color","red");
		}
	}else {
		//红色
		$(ele).parents(".form-inline").find(".end_date").parent().find("p").html("终止时间不能为空！");
		$(ele).parents(".form-inline").find(".end_date").parent().find("p").css("color","red");
	}

}
//比较合同终点判断起止时间
function checkContractEndndDate(ele){
	var starTime=$("#start_date").val();
	var endTime=$("#end_date").val();
	if (starTime!=""){
		var star=starTime.split("-");
		var ss=star[2].split(" ");
		var startime=new Date(star[0],star[1],ss[0]);
		var stars=startime.getTime();
		var end=endTime.split("-");
		var ends=end[2].split(" ");
		var endtime=new Date(end[0],end[1],ends[0]);
		var ends=endtime.getTime();
		if (ends>stars){
			//开始时间p标签
			$(ele).parent().find("p").html("");
			//终止时间p标签不显示文字
			$(ele).parent().find("p").html("");
		}else {
			//边框变红色
			//$(ele).parents(".newAdd_infoConditions").find(".end_date").css("border","1px solid red");
			//p标签显示文字并且变红
			$(ele).parent().find("p").html("终止时间大于开始时间！");
			$(ele).parent().find("p").css("color","red");
		}
	}else {
		//边框变红色
		//$(ele).css("border","1px solid red");
		//p标签显示文字并且变红
		$(ele).parent().find("p").html("开始时间不能为空！");
		$(ele).parent().find("p").css("color","red");
	}
}

//比较起点判断起止时间
function checkStarDate(ele){
	var starTime = $(ele).val();
	var endTime = $(ele).parents(".newAdd_routerInfo").find(".endDate").val();
	var start_date=$(ele).parents(".modalDialog").find(".start_date").val();
	var end_date= $(ele).parents(".modalDialog").find(".end_date").val();
	if (start_date != "" ||end_date != ""){
		//合同起止时间
		var contractStar=start_date.split("-");
		var contractss=contractStar[2].split(" ");
		var contractStartime=new Date(contractStar[0],contractStar[1],contractss[0]);
		var contractStars=contractStartime.getTime();
		var contractEnd=end_date.split("-");
		var contractEndss=contractEnd[2].split(" ");
		var contractEndime=new Date(contractEnd[0],contractEnd[1],contractEndss[0]);
		var contractEnds=contractEndime.getTime();
		//计划起止时间
		var star=starTime.split("-");
		var ss=star[2].split(" ");
		var startime=new Date(star[0],star[1],ss[0]);
		var stars=startime.getTime();
		if(endTime==""){//卸货时间为空
			if ( stars>=contractStars&&stars<=contractEnds){
				//开始时间p标签
				$(ele).parents(".newAdd_routerInfo").find(".startDate").parent().find("p").html("");
				//终止时间p标签不显示文字
				$(ele).parents(".newAdd_routerInfo").find(".endDate").parent().find("p").html("");
			}else {
				//p标签显示文字并且变红
				$(ele).parent().find("p").html("请选择正确的起止时间！");
				$(ele).parent().find("p").css("color","red");
			}
		}else {
			var end=endTime.split("-");
			var ends=end[2].split(" ");
			var endtime=new Date(end[0],end[1],ends[0]);
			var ends=endtime.getTime();
			if (ends>=contractStars && ends<=contractEnds && ends > stars && stars>=contractStars && stars<=contractEnds){
				//开始时间p标签
				$(ele).parents(".newAdd_routerInfo").find(".startDate").parent().find("p").html("");
				//终止时间p标签不显示文字
				$(ele).parents(".newAdd_routerInfo").find(".endDate").parent().find("p").html("");
			}else {
					//p标签显示文字并且变红
				$(ele).parents(".newAdd_routerInfo").find(".endDate").parent().find("p").html("请选择正确的起止时间！");
				$(ele).parents(".newAdd_routerInfo").find(".endDate").parent().find("p").css("color","red");
				$(ele).parents(".newAdd_routerInfo").find(".startDate").parent().find("p").html("请选择正确的起止时间！");
				$(ele).parents(".newAdd_routerInfo").find(".startDate").parent().find("p").css("color","red");
			}

		}

	}else {
		$("#smallModalInfo").modal();
		$("#smallModalInfo").find(".titleInfo").html("合同起止时间不能为空！")
		$("#smallModalInfo").on("hidden.bs.modal",function(e){
			$("body").addClass("modal-open");
		});
	}

}
//比较终点判断起止时间
function checkEndndDate(ele){
	var endTime = $(ele).val();
	var starTime = $(ele).parents(".newAdd_routerInfo").find(".startDate").val();
	var start_date= $(ele).parents(".modalDialog").find(".start_date").val();
	var end_date= $(ele).parents(".modalDialog").find(".end_date").val();
	if (starTime!=""){
		//合同起止时间
		var contractStar=start_date.split("-");
		var contractss=contractStar[2].split(" ");
		var contractStartime=new Date(contractStar[0],contractStar[1],contractss[0]);
		var contractStars=contractStartime.getTime();
		var contractEnd=end_date.split("-");
		var contractEndss=contractEnd[2].split(" ");
		var contractEndime=new Date(contractEnd[0],contractEnd[1],contractEndss[0]);
		var contractEnds=contractEndime.getTime();
		//计划起止时间
		var star=starTime.split("-");
		var ss=star[2].split(" ");
		var startime=new Date(star[0],star[1],ss[0]);
		var stars=startime.getTime();
		var end=endTime.split("-");
		var ends=end[2].split(" ");
		var endtime=new Date(end[0],end[1],ends[0]);
		var ends=endtime.getTime();
		if (ends>stars&& ends>=contractStars&&ends<=contractEnds && stars>=contractStars&&stars<=contractEnds){

			//开始时间p标签
			$(ele).parents(".newAdd_routerInfo").find(".startDate").parent().find("p").html("");
			//终止时间p标签不显示文字
			$(ele).parents(".newAdd_routerInfo").find(".endDate").parent().find("p").html("");
		}else {
			//p标签显示文字并且变红
			$(ele).parent().find("p").html("请选择正确的起止时间！");
			$(ele).parent().find("p").css("color","red");
		}
	}else {
		//p标签显示文字并且变红
		$(ele).parents(".newAdd_routerInfo").find(".startDate").parent().find("p").html("开始时间不能为空！");
		$(ele).parents(".newAdd_routerInfo").find(".startDate").parent().find("p").css("color","red");
		//p标签显示文字并且变红
		$(ele).parents(".newAdd_routerInfo").find(".endDate").parent().find("p").html("开始时间不能为空！");
		$(ele).parents(".newAdd_routerInfo").find(".endDate").parent().find("p").css("color","red");
	}
}


//验证卸货货联系人电话
function unloadPhone(ele){
	var total=$(ele).val();
	if(total != ""){
		if (regExpMob.test(total)){
			$(ele).parent().find("p").html("");
			return true;
		}else {
			$(ele).parent().find("p").html("请输入正确的手机号码！");
			$(ele).parent().find("p").css("color","red");
			return false;
		}
	}else{
		$(ele).parent().find("p").html("请输入手机号码");
		$(ele).parent().find("p").css("color","red");
		return false;
	}

}


//验证装货联系人电话
function loadPhone(ele){
	if($(ele).length == 1){
		var total=$(ele).val();
		if(total != ""){
			if (regExpMob.test(total)){
				$(ele).parent().find("p").html("");
				return true;
			}else {
				$(ele).parent().find("p").html("请输入正确的手机号码！");
				$(ele).parent().find("p").css("color","red");
				return false;
			}
		}else{
			$(ele).parent().find("p").html("请输入手机号码！");
			$(ele).parent().find("p").css("color","red");
			return false;
		}
	}else{
		for(var i = 0;i<$(ele).length;i++){
			var total=$(ele)[i].value;
			//循环到最后一个节点元素
			if(total != ""){
				if (regExpMob.test(total)){
					$($(ele)[i]).parent().find("p").html("");
					if(i == $(ele).length - 1){
						return true;
					}
				}else {
					$($(ele)[i]).parent().find("p").html("请输入正确的手机号码！");
					$($(ele)[i]).parent().find("p").css("color","red");
					return false;
				}
			}else{
				$($(ele)[i]).parent().find("p").html("请输入手机号码！");
				$($(ele)[i]).parent().find("p").css("color","red");
				return false;
			}
		}
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
//验证单价数字
function checkPrice(ele){
	var total=$(ele).val();
	if (numbers.test(total)){
		$(ele).parent().find("p").html("");
		return true;
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

//新增单一合同
function newAddContract(){
	$("#ModalContract").modal();
}
//新增单一合同下新增干线
function addContractRouter(ele){
	var _routerInfo = $(ele).parents(".routerInfo");
	var _formInline = $(".form-inline");
	_routerInfo.after('<div class="routerInfo newAdd_routerInfo">'+ $(".first_routerInfo").html() +'</div>');
	var _glyphiconPlus = $(".glyphicon-plus");
	if(_glyphiconPlus.length >= 2){
		$(".glyphicon-minus").removeClass("hidden");
	}
	for(var i = 0;i<$(".mainLine").length;i++){
		new customDropDown($($(".mainLine")[i]));
		//合同时间
		timeChoose($('.startDate'),$('.endDate'),$('.start_date'),$('.end_date'));
		//时间函数 end_Date
		function timeChoose(startTime,endTime,start_date,end_date){
			startTime.datetimepicker({
				format: 'yyyy-mm-dd hh:ii:00',
				language: 'zh-CN',
				autoclose: true,//选中之后自动隐藏日期选择框
				hourStep: 1,
				minuteStep: 30,
				todayBtn:true,
			});
			endTime.datetimepicker({
				format: 'yyyy-mm-dd hh:ii:00',
				autoclose: true,//选中之后自动隐藏日期选择框
				language: 'zh-CN',
				hourStep: 1,
				minuteStep: 30,
				todayBtn:true,
			});
			start_date.datetimepicker({
				format: 'yyyy-mm-dd hh:ii:00',
				language: 'zh-CN',
				autoclose: true,//选中之后自动隐藏日期选择框
				hourStep: 1,
				minuteStep: 30,
				todayBtn:true
			});
			end_date.datetimepicker({
				format: 'yyyy-mm-dd hh:ii:00',
				autoclose: true,//选中之后自动隐藏日期选择框
				language: 'zh-CN',
				hourStep: 1,
				minuteStep: 30,
				todayBtn:true
			});
		}
	}
	$(".mainLine").find(".dropdown-menu li").bind("click",function(){
		var mainLineName = $(this).attr("value");
		var loadFreightYard = $(this).parents(".routerInfo").find(".loadFreightYard");
		var unloadFreightYard = $(this).parents(".routerInfo").find(".unloadFreightYard");
		if (mainLineName!=''||mainLineName!='undefined'){
			//获取装货货场
			$.ajax({
				type: "POST",
				url: "/iwuliu/freightYardManager/loadCargoByLineId",
				cache: false,
				data:{"operate_main_line_id":mainLineName},
				dataType: "json",
				success: function (data) {
					if (data.length!=0){
						var _strd = '';
						for (var i= 0;i<data.length;i++){
							_strd+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
						}
						loadFreightYard.find(".dropdown-menu").html(_strd);
						var loadFreightYardSelect = new customDropDown(loadFreightYard);
						console.log(111)
						loadFreightYard.find(".placeHolder").html('请选择');
						unloadFreightYard.find(".placeHolder").html('请选择');
						//获取卸货货场
						$.ajax({
							type: "POST",
							url: "/iwuliu/freightYardManager/unloadCargoByLineId" ,
							cache: false,
							data:{"operate_main_line_id":mainLineName},
							dataType: "json",
							success: function (data) {
								if (data.length!=0){
									var _strs = '';
									for (var i= 0;i<data.length;i++){
										_strs+="<li value="+ data[i].id +"><a href='javascript:;'>"+ data[i].name +"</a></li>"
									}
									unloadFreightYard.find(".dropdown-menu").html(_strs);
									var unloadFreightYardSelect = new customDropDown(unloadFreightYard);
									loadFreightYard.find(".placeHolder").html('请选择');
									unloadFreightYard.find(".placeHolder").html('请选择');
								}else {
									unloadFreightYard.find(".placeHolder").removeAttr("value")
									loadFreightYard.find(".placeHolder").removeAttr("value")
									loadFreightYard.find(".placeHolder").html('无');
									unloadFreightYard.find(".placeHolder").html('无');
									//当前干线没有卸货货场
									$("#smallModalInfo").modal();
									$("#smallModalInfo").find(".titleInfo").html("当前干线没有卸货货场！")
									$("#").on("hidden.bs.modal",function(e){
										$("body").addClass("modal-open");
									});
								}
							}
						});
					}else {
						unloadFreightYard.find(".placeHolder").removeAttr("value")
						loadFreightYard.find(".placeHolder").removeAttr("value")
						loadFreightYard.find(".placeHolder").html('无');
						unloadFreightYard.find(".placeHolder").html('无');
						//当前干线没有装货货场
						$("#smallModalInfo").modal();
						$("#smallModalInfo").find(".titleInfo").html("当前干线没有装货货场！")
						$("#smallModalInfo").on("hidden.bs.modal",function(e){
							$("body").addClass("modal-open");
						});
					}
				}
			})
		}
	})
}
//新增单一合同下移除干线
function removeContractRouter(ele){
	var _routerInfos = $(".routerInfo");
	var _routerInfo = $(ele).parents(".routerInfo");
	_routerInfo.remove();
//	$(".glyphiconMinus").addClass("hidden");
	//if(_routerInfos.length > 2){
	//
	//}else if(_routerInfos.length == 2){
	//	_routerInfo.remove();
	//
	//}
}
//goBtn按钮无数据时隐藏
var webservice = $('#transportationContractTable').attr("data-url");
$.ajax({
	url:webservice,
	success:function(data){
		if(data.length == 0){
			$(".form-inline").css("display","none");
		}
	}
});
var webservice2 = $('#transportationPlanTable').attr("data-url");
$.ajax({
	url:webservice2,
	success:function(data){
		if(data.length == 0){
			$(".form-inline").css("display","none");
		}
	}
});

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
		pageNumber: params.pageNumber,  //页码
		beginDate: $("#use_beginTime").val(),
		endDate: $("#use_endTime").val(),
		sort: params.sort,  //排序列名
		sortOrder: params.order//排位命令（desc，asc）
	};
	return temp;
}
// 格式化“操作”列
function operationFormatter(){
	return '<a class="NewDetail btn" onclick="queryContractDetail(this.parentElement.parentElement)">' +
			'详情</a>';
}
 //点击“详情”
function queryContractDetail(value){
	var _value = $(value).find(".hidden").html();
	contractId = _value
	$.ajax({
		type:"post",
		url:"/iwuliu/transportationOrderManager/transportationOrderListBelongNullDetail",
		data:{"contract_id":_value},
		success:function(data){
			var JsonData =JSON.parse(data);
			$("#codes").val(JsonData.code);
			$("#goodType").val(JsonData.cargo_type_id);
			$("#shipperType").val(JsonData.personType);
			$("#shipperName").val(JsonData.name);
			$("#name").val(JsonData.contactName);
			$("#namePhone").val(JsonData.contactPhone);
			$("#costType").val(JsonData.balanceType);
			$("#contractStart").val(JsonData.beginDate);
			$("#contractEnd").val(JsonData.end_date);
			var _listLength = JsonData.list.length;
			for(var i = 0;i<_listLength ;i++){
				var num = i;
				num++;
				var _append ='<div class="routerInfo"><h4>&nbsp;&nbsp;'+
			'<span onclick="addContractRouter(this)" class="glyphicon glyphicon-plus hidden"></span>'+
						'干线信息'+
						'</h4>'+
						'<div class="form-group">'+
						'<label>干线信息：</label>'+
				'<div class="display_inlineBlcok docubleUneditable">'+
						'<input disabled  type="text" class="form-control lineInfo" value="'+JsonData.list[i].lineName+'"/>'+
						'<p></p>'+
						'</div>'+
						'</div>'+
						'<div class="form-group">'+
						'<label>装货货场：</label>'+
				'<div class="display_inlineBlcok docubleUneditable">'+
						'<input  disabled type="text" class="form-control lineLoad" value="'+JsonData.list[i].loadCargoName+'"/>'+
						'<p></p>'+
						'</div>'+
						'</div>'+
						'<div class="form-group">'+
						'<label>装货开始时间：</label>'+
				'<div class="display_inlineBlcok docubleUneditable">'+
						'<input disabled type="text" class="form-control lineLoadTime" value="'+JsonData.list[i].loading_begin_date+'"/>'+
						'<p></p>'+
						'</div>'+
						'</div>'+
						'<div class="form-group">'+
						'<label>装货联系人：</label>'+
				'<div class="display_inlineBlcok docubleUneditable">'+
						'<input disabled type="text" class="form-control lineLoadName" value="'+JsonData.list[i].loading_contact_name+'"/>'+
						'<p></p>'+
						'</div>'+
						'</div>'+
						'<div class="form-group">'+
						'<label>装货人电话：</label>'+
				'<div class="display_inlineBlcok docubleUneditable">'+
						'<input disabled type="text" class="form-control lineLoadNamePhone" value="'+JsonData.list[i].loading_contact_phone+'"/>'+
						'<p></p>'+
						'</div>'+
						'</div>'+
						'<div class="form-group">'+
						'<label>卸货货场：</label>'+
				'<div class="display_inlineBlcok docubleUneditable">'+
						'<input disabled type="text" class="form-control lineUnload"value="'+JsonData.list[i].unloadCargoName+'"/>'+
						'<p></p>'+
						'</div>'+
						'</div>'+
						'<div class="form-group">'+
						'<label>卸货终止时间：</label>'+
				'<div class="display_inlineBlcok docubleUneditable">'+
						'<input disabled type="text" class="form-control lineUnloadTime" value="'+JsonData.list[i].unloading_finish_date+'"/>'+
						'<p></p>'+
						'</div>'+
						'</div>'+
						'<div class="form-group">'+
						'<label>卸货联系人：</label>'+
				'<div class="display_inlineBlcok docubleUneditable">'+
						'<input disabled type="text" class="form-control  lineUnloadName" value="'+JsonData.list[i].unloading_contact_name+'"/>'+
						'<p></p>'+
						'</div>'+
						'</div>'+
						'<div class="form-group">'+
						'<label>卸货人电话：</label>'+
				'<div class="display_inlineBlcok docubleUneditable">'+
						'<input disabled type="text" class="form-control lineUnloadNamePhone" value="'+JsonData.list[i].unloading_contact_phone+'"/>'+
						'<p></p>'+
						'</div>'+
						'</div>'+
						'<div class="form-group">'+
						'<label>单价（元/吨）：</label>'+
				'<div class="display_inlineBlcok uneditable">'+
						'<input disabled type="text" class="form-control  everyPrice" addattr="'+ JsonData.list[i].schedulePlanNumber +'"  />'+
						'<p></p>'+
						'</div>'+
						'</div>'+
						'<div class="form-group">'+
						'<label>货物数量（吨）：</label>'+
				'<div class="display_inlineBlcok docubleUneditable">'+
						'<input disabled type="text" class="form-control lineTotal " value="'+JsonData.list[i].cargo_total+'"/>'+
						'<p></p>'+
						'</div>'+
						'</div>'+
						'</div>';
				$(".newAdd_router").parent().before(_append);
				$(".routerInfo").find(".everyPrice").val(JsonData.list[i].transport_unit_price)
			}
		}
	})
	$("#myModalContractDetail").modal();
}
// 格式化“订单号”列
function codeFormatter(value, row){
	return '<a  onclick="queryCode(this.parentElement.parentElement)">' +
			'<span style="color: #FF0000" >'+value+'</span></a>';
}

function queryCode(value){
	var contractId = $(value).find(".hidden").html();
	//table设置刷新
	$('#transportationPlanTable').bootstrapTable('refresh',{'url':'/iwuliu/transportationOrderManager/transportationOrderListBelongNull?contract_id='+contractId});
}