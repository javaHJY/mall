<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="Generator" content="EditPlus®">
	<meta name="Author" content="">
	<meta name="Keywords" content="">
	<meta name="Description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
	<meta name="renderer" content="webkit">
	<title>登录.云购物商城</title>
	<link rel="shortcut icon" type="image/x-icon" href="../img/icon/favicon.ico">
	<link rel="stylesheet" type="text/css" href="../css/base.css">
	<link rel="stylesheet" type="text/css" href="../css/home.css">
</head>
<body>
	<header id="pc-header">
		<div class="center">
			<div class="pc-fl-logo">
				<h1>
					<a href="index.html"></a>
				</h1>
			</div>
		</div>
	</header>
	<section>
		<div class="pc-login-bj">
			<div class="center clearfix">
				<div class="fl"></div>
				<div class="fr pc-login-box">
					<div class="pc-login-title"><h2>用户登录</h2></div>
					<form action="login.do" method="post">
						<div style="color:red;">${error }</div>
						<div class="pc-sign">
							<input type="text" name="username" placeholder="用户名/邮箱/手机号">${user.username }
						</div>
						<div class="pc-sign">
							<input type="password" name="password" placeholder="请输入您的密码">${user.password }
						</div>
						<div class="pc-submit-ss">
							<input type="submit" value="登录">
						</div>
						<div class="pc-item-san clearfix">
							<a href="#"><img src="../img/icon/weixin.png" alt="">微信登录</a>
							<a href="#"><img src="../img/icon/weibo.png" alt="">微博登录</a>
							<a href="#" style="margin-right:0"><img src="../img/icon/tengxun.png" alt="">QQ登录</a>
						</div>
						<div class="pc-reg">
							<a href="forgetPassword.do">忘记密码</a>
							<a href="goRegist.do" class="red">免费注册</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<footer>
		<div class="center">
			<div class="pc-footer-login">
				<p>关于我们 招聘信息 联系我们 商家入驻 商家后台 商家社区 ©2017 Yungouwu.com 北京云购物网络有限公司</p>
				<p style="color:#999">营业执照注册号：990106000129004 | 网络文化经营许可证：北网文（2016）0349-219号 | 增值电信业务经营许可证：京2-20110349 | 安全责任书 | 京公网安备 99010602002329号 </p>
			</div>
		</div>
	</footer>
</body>
</html>