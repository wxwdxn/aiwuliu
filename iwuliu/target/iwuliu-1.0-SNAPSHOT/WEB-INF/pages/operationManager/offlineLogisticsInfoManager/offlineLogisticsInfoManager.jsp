<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String username = request.getSession().getAttribute("username").toString();%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<title></title>
</head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/css/common.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/operationManager/offlineLogisticsInfoManager/css/offlineLogisticsInfoManager.css"/>
<script src="<%=request.getContextPath()%>/static/common/js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/operationManager/offlineLogisticsInfoManager/js/offlineLogisticsInfoManager.js" type="text/javascript" charset="utf-8"></script>
<%--common.js的引用放在自己单独引用的js下面--%>
<script src="<%=request.getContextPath()%>/static/common/js/commonNav.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/common/js/common.js" type="text/javascript" charset="utf-8"></script>
<%--生成二维码--%>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/common/js/jquery.qrcode.min.js"></script>
<!--[if lt IE 9]>
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<body>
<!--*********导航条开始***********-->
<nav class="navbar navbar-default" role="navigation">
</nav>
<!--*********导航条结束***********-->
<div class="container-fluid eleHeight">
	<div class="row eleHeight">
		<div class="col-md-2 leftMenu eleHeight">
		</div>
		<div class="iframeMain">
			<h4>
				公司信息管理
			</h4>
			<div class="infoConditions">
				<form class="form-inline">
					<h3 class="text-center">
						物流公司详情
					</h3>
					<div class="text-right btnIffline">
						<a href="javascript:;" class="btn" onclick="btn_ModalPwd()">
							修改密码
						</a>
					</div>
					<div class="form-group">
						<label>物流公司名称：</label>
						<div class="display_inlineBlcok uneditable">
							<input disabled type="text" class="form-control company_name" />
						</div>
					</div>
					<div class="form-group">
						<label>所在省：</label>
						<div class="display_inlineBlcok uneditable detail_province">
							<button disabled class="btn form-control btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="placeHolder province"></span>
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							</ul>
						</div>
					</div>
					<div class="form-group">
						<label>所在市：</label>
						<div class="display_inlineBlcok uneditable detail_city">
							<button disabled class="btn form-control btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="placeHolder city"></span>
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							</ul>
						</div>
					</div>
					<div class="form-group">
						<label>所在区县：</label>
						<div class="display_inlineBlcok uneditable detail_area">
							<button disabled class="btn form-control btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="placeHolder area"></span>
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							</ul>
						</div>
					</div>
					<div class="form-group">
						<label>所在村镇街道：</label>
						<div class="display_inlineBlcok uneditable">
							<input disabled type="text" class="form-control townStreet" />
						</div>
					</div>
					<div class="form-group latitudeAndlongitude">
						<label>节点经度：</label>
						<div class="display_inlineBlcok uneditable">
							<input disabled type="text" class="form-control longitude" />
						</div>
						<label>节点纬度：</label>
						<div class="display_inlineBlcok uneditable">
							<input disabled type="text" class="form-control latitude" />
						</div>
					</div>
					<div class="form-group">
						<label>工作时间起点：</label>
						<div class="display_inlineBlcok uneditable">
							<input disabled type="text" class="form-control start_work_time" />
						</div>
					</div>
					<div class="form-group">
						<label>工作时间终点：</label>
						<div class="display_inlineBlcok uneditable">
							<input disabled type="text" class="form-control finish_work_time" />
						</div>
					</div>
					<div class="form-group">
						<label>营业执照：</label>
						<div class="display_inlineBlcok uneditable">
							<input disabled type="text" class="form-control business_licence"
								   onkeyup="checkLicence(this)"/>
						</div>
					</div>
					<div class="form-group">
						<label>联络人：</label>
						<div class="display_inlineBlcok uneditable">
							<input disabled type="text" class="form-control contact_name" />
						</div>
					</div>
					<div class="form-group">
						<label>联络人员手机：</label>
						<div class="display_inlineBlcok uneditable">
							<input disabled type="text" class="form-control contact_mobile_phone" />
						</div>
					</div>
					<div class="form-group uneditable">
						<label>性别：</label>
						<div class="display_inlineBlcok detail_sex">
							<button disabled class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="placeHolder contact_sex"></span>
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							</ul>
							<p></p>
						</div>
					</div>
					<div class="form-group uneditable">
						<label>公司固定电话：</label>
						<div class="display_inlineBlcok">
							<input disabled type="text" class="form-control company_fixed_phone"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>开户银行：</label>
						<div class="display_inlineBlcok uneditable detail_bank_id">
							<button disabled class="btn form-control btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="placeHolder bank_id"></span>
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							</ul>
						</div>
					</div>
					<div class="form-group">
						<label>银行账户：</label>
						<div class="display_inlineBlcok uneditable">
							<input disabled type="text" class="form-control bank_account" />
						</div>
					</div>
					<div class="form-group login_pwd">
						<label>登录密码：</label>
						<div class="display_inlineBlcok uneditable">
							<input disabled type="text" class="form-control member_password" />
						</div>
					</div>
					<div class="form-group pay_pwd">
						<label>支付密码：</label>
						<div class="display_inlineBlcok uneditable">
							<input disabled type="text" class="form-control payment_password" />
						</div>
					</div>
					<div class="form-group formgroupImg">
						<label>营业执照照片：</label>
						<div class="display_inlineBlcok uneditable" id="business_licence_pic">
						</div>
					</div>
					<div class="form-group formgroupImg">
						<label>店面照片：</label>
						<div class="display_inlineBlcok uneditable" id="store_pic">
						</div>
					</div><br/>
					<div class="form-group formgroupImg_erweima">
						<label>二维码图片：</label>
						<div class="display_inlineBlcok Detail_ModalQRCode">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!--Modal 修改密码-->
<div class="modal fade" id="ModalPwd" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modalDialog_pwd" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<a type="button" class="close modalImgClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></a>
				<h4 class="modal-title">修改密码</h4>
			</div>
			<div class="modal-body">
				<div class="text-center btn_pwdType">
					<a href="javascript:;" class="btn clickActive">修改登录密码</a>
					<a href="javascript:;" class="btn">修改支付密码</a>
				</div>
				<div class="text-center form-horizontal loginPwd lPwd">
					<div class="form-group">
						<label>&nbsp;&nbsp;旧的登录密码：</label>
						<div class="display_inlineBlcok">
							<input type="password" class="form-control old_member_password" onkeyup="checkPsd(this)"
								   oncopy="return false" onpaste="return false"  ondragstart="return false" onselectstart="return false"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>&nbsp;&nbsp;新的登录密码：</label>
						<div class="display_inlineBlcok">
							<input type="password" class="form-control new_member_password" onkeyup="checkPsd(this)"
								   oncopy="return false" onpaste="return false"  ondragstart="return false" onselectstart="return false"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>再次确认新密码：</label>
						<div class="display_inlineBlcok">
							<input type="password" class="form-control new_member_password_again" onkeyup="checkPsd(this)"
								   oncopy="return false" onpaste="return false"  ondragstart="return false" onselectstart="return false"/>
							<p></p>
						</div>
					</div>
				</div>
				<div class="text-center form-horizontal payPwd pPwd hidden">
					<div class="form-group">
						<label>&nbsp;&nbsp;旧的支付密码：</label>
						<div class="display_inlineBlcok">
							<input type="password" class="form-control old_payment_password" onkeyup="checkPsd(this)"
								   oncopy="return false" onpaste="return false"  ondragstart="return false" onselectstart="return false"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>&nbsp;&nbsp;新的支付密码：</label>
						<div class="display_inlineBlcok">
							<input type="password" class="form-control new_payment_password"onkeyup="checkPsd(this)"
								   oncopy="return false" onpaste="return false"  ondragstart="return false" onselectstart="return false"/>
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label>再次确认新密码：</label>
						<div class="display_inlineBlcok">
							<input type="password" class="form-control new_payment_password_again" onkeyup="checkPsd(this)"
								   oncopy="return false" onpaste="return false"  ondragstart="return false" onselectstart="return false"/>
							<p></p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer modalFooter form-inline lPwd">
				<button type="button" class="btn btn-default" onclick="editLoginPassword()">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
			<div class="modal-footer modalFooter form-inline pPwd hidden">
				<button type="button" class="btn btn-default" onclick="editPaymentPassword()">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
<!--Modal 小信息提示框-->
<div class="modal fade bs-example-modal-sm" id="smallModalInfo" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">信息提示</h4>
			</div>
			<div class="modal-body text-center">
				<h2 class="titleInfo">

				</h2>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default confirm" data-dismiss="modal">确定</button>
				<button type="button" class="btn btn-default cancel" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
<!--Modal imgBig-->
<div class="modal fade" id="ModalImg" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<a type="button" class="close modalImgClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></a>
				<h4 class="modal-title QRCodeCompanyName" >图片信息</h4>
			</div>
			<div class="modal-body modalImg">
			</div>
		</div>
	</div>
</div>
</body>
</html>