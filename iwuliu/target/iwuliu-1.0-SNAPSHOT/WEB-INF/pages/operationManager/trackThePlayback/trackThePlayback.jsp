<%--
  Created by IntelliJ IDEA.
  User: WXW
  Date: 2017/02/15
  Time: 09:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<title></title>
	</head>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/common/css/common.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-table.min.css">
	<link rel="stylesheet"
		  href="<%=request.getContextPath()%>/static/common/bootstrap/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/operationManager/trackThePlayback/css/trackThePlayback.css" />

	<%--<script src="<%=request.getContextPath()%>/static/common/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>--%>
	<script src="<%=request.getContextPath()%>/static/common/js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>

	<%--Bootstrap时间控件  JavaScript 文件以及汉化文件--%>

	<script src="<%=request.getContextPath()%>/static/common/js/jquery.min.js" type="text/javascript"
	charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/common/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/commonNav.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/operationManager/trackThePlayback/js/trackThePlayback.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/static/common/js/common.js" type="text/javascript" charset="utf-8"></script>

	<!--[if lt IE 9]>
	  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->

	<%--高德地图--%>
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
	<script src="http://webapi.amap.com/maps?v=1.3&key=a0faf6f34da0f9e9fa333547de2a4a9e"></script>

	<style type="text/css">
		.info {
			border: solid 1px silver;
		}
		div.info-top {
			position: relative;
			background: none repeat scroll 0 0 #F9F9F9;
			border-bottom: 1px solid #CCC;
			border-radius: 5px 5px 0 0;
		}
		div.info-top div {
			display: inline-block;
			color: #333333;
			font-size: 14px;
			font-weight: bold;
			line-height: 31px;
			padding: 0 10px;
		}
		div.info-top img {
			position: absolute;
			top: 10px;
			right: 10px;
			transition-duration: 0.25s;
		}
		div.info-top img:hover {
			box-shadow: 0px 0px 5px #000;
		}
		div.info-middle {
			font-size: 12px;
			padding: 6px;
			line-height: 20px;
		}
		div.info-bottom {
			height: 0px;
			width: 100%;
			clear: both;
			text-align: center;
		}
		div.info-bottom img {
			position: relative;
			z-index: 104;
		}
		span {
			margin-left: 5px;
			font-size: 11px;
		}
		.info-middle img {
			float: left;
			margin-right: 6px;
		}
	</style>
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
						轨迹回放
					</h4>
					<div class="infoConditions">
						<div class="trackThePlayback form-inline">
							<div class="form-group">
								<label>
									公司名称:
								</label>
								<div class="display_inlineBlcok logisticsCompany">
									<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
										<span class="placeHolder " >请选择</span>
									    <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">

									</ul>
								</div>
							</div>
							<div class="form-group">
								<label class="licenseTitle">
									车辆牌照:
								</label>
								<div class="input-group display_inlineBlcok license">
									<input type="text" id="truckNumber" class="form-control placeHolder">
									<div class="input-group-btn">
										<button type="button" class="btn inputAndSelect btn-default dropdown-toggle" data-toggle="dropdown" tabindex="-1">
											<span class="caret "></span>
											<span class="sr-only">切换下拉菜单</span>
										</button>
										<ul class="dropdown-menu pull-right">

										</ul>
									</div><!-- /btn-group -->
								</div>
							</div>
							<div class="form-group">
								<label>
									时间:
								</label>
								<div class="display_inlineBlcok">
									<input type="text" class="form-control " id="beginTime" />至
									<input type="text" class="form-control " id="endTime" />
								</div>
							</div>
							<div class="form-group">
								<label class="sr-only">按钮</label>
								<div class="display_inlineBlcok">

									<a tabindex="0" onclick="playback(this)"  role="button" class="btn playback playbackBtn">
										轨迹回放
									</a>
										<%--<a tabindex="0"  onclick="playback(this)" class="btn btn-lg btn-danger" role="button"  >可消失的弹出框</a>--%>
									<a onclick="Start()" type="button" class="btn elementDisplayNone playbackBtn Start">
										开始动画
									</a>
									<a onclick="stop()" type="button" class="btn elementDisplayNone playbackBtn stop">
										暂停动画
									</a>
									<a type="button" onclick="continueMove()" class="btn elementDisplayNone playbackBtn continue">
										继续动画
									</a>
								</div>
							</div>
						</div>
						<div class="col-md-12" id="container"></div>
					</div>
				</div>
			</div>
		</div>

		<!--Modal 小提示框-->
		<div class="modal fade bs-example-modal-sm" id="smallModalInfo" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">提示信息</h4>
					</div>
					<div class="modal-body text-center">
						<h4 class="titleInfo">

						</h4>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>

	</body>

</html>