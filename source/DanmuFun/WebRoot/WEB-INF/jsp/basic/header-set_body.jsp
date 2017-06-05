<%@  page import="com.yida.entity.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>

<link href="css/header/plus.css" rel="stylesheet">
<link href="css/header/imgareaselect-default.css" rel="stylesheet">
<script type="text/javascript" src="js/space/header-set.js"></script>
<script type="text/javascript"
	src="js/space/jquery.imgareaselect.pack.js"></script>
<body>
	
	<div class="panel-heading">
		<div class="panel-heading">
			<span class="glyphicon glyphicon-picture" aria-hidden="true"
				style="color:#3175b0;padding:5px;"></span><span class="text-primary">头像设置</span>
		</div>
	</div>
	
	<div class="panel-body safe-body" style="padding:0px;">
		<!--图片区-->
		<div id="pictures" class="col-md-12">
			<!-- 原图 -->
			<center class="col-md-6">
			<span class="holder"> 
			<img class='pre_img' id="aimPic" src="">
			</span>
			</center>
			<center class="col-md-6">
			<span class='pic_holder center-block' id='add_pic'> 
				<img class='btn_holder'	id='add_btn' src='img/plus.png'>
				<span id="upfile_holder">
					<input type='file' title="点击上传图片" id='pic_file' name='image[]'
					accept='image/*' multiple>
				</span>
			</span>
			</span>
		</div>
	</div>
	
	<div class="panel-body" style="border:none;">
		<div style="margin-top:10px">
			<span class="label label-primary col-md-2 col-md-offset-1">目标头像</span>
			<span class="label label-primary col-md-2 col-md-offset-4">点击‘+’选择图片</span>
		</div>
	</div>
	
	<div class="panel-footer">
	<button type="button" class="btn btn-primary" id="re-select" >重新选择</button>
	<button type="button" class="btn btn-primary" id="confirm-upload" >确认上传</button></div>
	
	
	<!-- Modal -->
	<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					</button>
					<h4 class="modal-title" id="myModalLabel">裁剪图片 - 拖动光标以选择裁剪区域</h4>
				</div>

				<div class="modal-body" id="modalBody">
					<div class="container-fluid" id="modalBodyContainer"
						style="height:650px">
						<div class="row">

							<center class="col-md-8" id="raw_im_wrapper">
								<span id="raw_size"></span> <img id="raw_img" /> <span
									id="select_size"></span>
							</center>
							<canvas id="img_preview" width="180" height="180"></canvas>
						</div>
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="cancel"
						>取消</button>
					<button type="button" class="btn btn-primary" id="confirm"
						>确认</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
