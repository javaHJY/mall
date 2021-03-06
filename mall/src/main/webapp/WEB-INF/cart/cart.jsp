<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<title>我的购物车-云购物商城</title>
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath }/img/icon/favicon.ico">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/base.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/home.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/index.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/modernizr-custom-v2.7.1.min.js"></script>
	<script type="text/javascript">
		$().ready(function(){
			//初始化总金额
			var sum=0;
			var cartSum=0;
			$(".checkbox").each(function(index,element){
				if($(element).prop("checked")==true){
					var index=$(element).data("index");
					var l_amount=parseFloat($("#l_amount"+index).text());
					sum+=l_amount;
					cartSum++;
				}
				$("#cartSum").text(cartSum);
			})
			$("#t_amount").text(sum);
			$(".allSelect").click(function(){
				if($(this).prop("checked")==true){
					//全选
					$(".checkbox").prop("checked",true);
					var sum=0;
					$(".checkbox").each(function(index,element){
						if($(element).prop("checked")==true){
							var index=$(element).data("index");
							var l_amount=parseFloat($("#l_amount"+index).text());
							sum+=l_amount;
						}
					})
					$("#t_amount").text(sum);
					$(".allSelect").prop("checked",true);
				}else{
					//取消全选
					$("#t_amount").text(0);
					$(".checkbox").prop("checked",false);
					$(".allSelect").prop("checked",false);
				}
			})
			$(".decrease").click(function(){
				var index=$(this).data("index");
				var obj=$("#count"+index);
				var count=obj.val();
				var stock=obj.data("goodsstock");
				var gId=obj.data("goodsid");
				if(count>1){
					obj.val(--count);
					updateCart(index,gId,count);
				}
			})
			var timer=0;
			$(".count").keyup(function() {
				clearTimeout(timer);
				var index=$(this).data("index");
				timer=setTimeout(function(){
					var obj=$("#count"+index);
					var count=obj.val();
					var stock=obj.data("goodsstock");
					var gId=obj.data("goodsid");
					if (count< 1) {
						obj.val(1);
						updateCart(index,gId,1);
					}else if(count>stock){
						obj.val(stock);
						updateCart(index,gId,stock);
					}else{
						obj.val(count);
						updateCart(index,gId,count);
					}
				},1000)
			})
			$(".increase").click(function(){
				var index=$(this).data("index");
				var obj=$("#count"+index);
				var count=obj.val();
				var stock=obj.data("goodsstock");
				var gId=obj.data("goodsid");
				if(count<stock){
					obj.val(++count);
					updateCart(index,gId,count);
				}
			})
			function updateCart(index,gId,count){
				$.ajax({
					type: "post",
	                url: "updateCart.do",
	                data: {"goods.id":gId,"count":count},
	                dataType: "json",
	              	success: function(data){
	              		var prevL_amount=$("#l_amount"+index).html();
	              		$("#l_amount"+index).html(data.l_amount);
						if($("#tr"+index+" .checkbox").prop("checked")==true){
							var sum=$("#t_amount").text()-prevL_amount+data.l_amount;
							$("#t_amount").text(sum);
						}
	                }
				})
			}
			$(".deleteOne").click(function(){
				var id=$(this).data("id");
				var index=$(this).data("index");
				$.ajax({
					type:"post",
					url:"delete.do",
					data:{id:id},
					dataType:"json",
					success:function(data){
						if(data==true){
							var l_amount=$("#l_amount"+index).html();
							$("#tr"+index).remove();
							$("#t_amount").text($("#t_amount").text()-l_amount);
							$("#cartSum").text(--cartSum);
						}
					}
				})
			})
			$(".checkbox").click(function(){
				if($(this).prop("checked")==false){
					$(".allSelect").prop("checked",false);
				}else{
					var flag=true;
					$(".checkbox").each(function(index,element){
						if($(element).prop("checked")==false){
							flag=false;
							return false;
						}
					})
					if(flag){
						$(".allSelect").prop("checked",true);
					}
				}
				var sum=0;
				$(".checkbox").each(function(index,element){
					if($(element).prop("checked")==true){
						var index=$(element).data("index");
						var l_amount=parseFloat($("#l_amount"+index).text());
						sum+=l_amount;
					}
				})
				$("#t_amount").text(sum);
			})
			$("#generateOrder").click(function(){
				var array=new Array();
				$(".checkbox").each(function(index,element){
					if($(element).prop("checked")==true){
						var id=$(element).data("id");
						array.push(id);
					}
				})
				if(array.length!=0){
					location.href="../order/generateOrder.do?cartIds="+array;
				}
			})
			$("#searchButton").click(function(){
	        	if($("#searchName").val()==""){
	           		return false;
	           	}else{
	           		return true;
	           	}
	        })
		})
	</script>
</head>
<body>

<header id="pc-header">
	<div class="pc-header-nav">
		<div class="pc-header-con">
			<div class="fl pc-header-link" >您好！，欢迎来云购物 
				<c:if test="${not empty user }">
					<a>${user.username }</a>
					<a href="${pageContext.request.contextPath }/user/loginOut.do">退出</a>
				</c:if>
				<c:if test="${empty user }">
					<a href="${pageContext.request.contextPath }/user/goLogin.do">请登录</a>
				</c:if>
				<a href="${pageContext.request.contextPath }/user/goRegist.do"> 免费注册</a>
			</div>
			<div class="fr pc-header-list top-nav">
				<ul>
					<li>
						<div class="nav">
							<i class="pc-top-icon"></i>
							<a href="${pageContext.request.contextPath }/order/showOrder.do">我的订单</a>
						</div>
						<div class="con">
							<dl>
								<dt><a href="">批发进货</a></dt>
								<dd><a href="">已买到货品</a></dd>
								<dd><a href="">优惠券</a></dd>
								<dd><a href="">店铺动态</a></dd>
							</dl>
						</div>
					</li>
					<li>
						<div class="nav"><i class="pc-top-icon"></i><a href="#">我的商城</a></div>
						<div class="con">
							<dl>
								<dt><a href="">批发进货</a></dt>
								<dd><a href="">已买到货品</a></dd>
								<dd><a href="">优惠券</a></dd>
								<dd><a href="">店铺动态</a></dd>
							</dl>
						</div>
					</li>
					<li><a href="#">我的云购</a></li>
					<li><a href="#">我的收藏</a></li>
					<li><a href="${pageContext.request.contextPath }/user/goUser.do">会员中心</a></li>
					<li><a href="#">客户服务</a></li>
					<li><a href="#">帮助中心</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="pc-header-logo clearfix">
		<div class="pc-fl-logo fl">
			<h1>
				<a href="index.html"></a>
			</h1>
		</div>
		<div class="head-form fl">
			<form class="clearfix" action="${pageContext.request.contextPath }/goods/searchByName.do" method="post">
				<input id="searchName" name="goodsName" class="search-text" accesskey="" id="key" autocomplete="off" type="text">
				<button id="searchButton" type="submit" class="button">搜索</button>
			</form>
			<div class="words-text clearfix">
				<a href="#" class="red">1元秒爆</a>
				<a href="#">低至五折</a>
				<a href="#">农用物资</a>
				<a href="#">佳能相机</a>
				<a href="#">服装城</a>
				<a href="#">买4免1</a>
				<a href="#">家电秒杀</a>
				<a href="#">农耕机械</a>
				<a href="#">手机新品季</a>
			</div>
		</div>
		<div class="fr pc-head-car">
			<i class="icon-car"></i>
			<a href="${pageContext.request.contextPath }/cart/list.do">我的购物车</a>
		</div>
	</div>
	<!--  顶部    start-->
	<div class="yHeader">
		<!-- 导航   start  -->
		<div class="yNavIndex">
			<ul class="yMenuIndex" style="margin-left:0">
				<li style="background:#d1201e">
					<a href="${pageContext.request.contextPath }/index/index.do">云购首页</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath }/goods/list.do">电脑配件 </a>
				</li>
				<li><a href="" target="_blank">男士护肤</a></li>
				<li><a href="" target="_blank">洗护染发</a></li>
				<li><a href="" target="_blank">染发</a></li>
				<li><a href="" target="_blank">彩妆</a></li>
				<li><a href="" target="_blank">品牌故事</a></li>
			</ul>
		</div>
		<!-- 导航   end  -->
	</div>

</header>

<section id="pc-jie">
	<div class="center ">
		<ul class="pc-shopping-title clearfix">
			<li><a href="#" class="cu">全部商品</a></li>
			<li><a href="#">限时优惠</a></li>
			<li><a href="#">库存紧张</a></li>
		</ul>
	</div>
	<div class="pc-shopping-cart center">
		<div class="pc-shopping-tab">
			<table>
				<thead>
					<tr class="tab-0">
						<th class="tab-1">
							<input type="checkbox" name="s_all" checked="checked" class="s_all tr_checkmr allSelect" id="s_all_h">
							<label for=""> 全选</label>
						</th>
						<th class="tab-2">商品</th>
						<th class="tab-3">商品信息</th>
						<th class="tab-4">单价</th>
						<th class="tab-5">数量</th>
						<th class="tab-6">小计</th>
						<th class="tab-7">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="7" style="padding-left:10px; background:#eee">
							<input type="checkbox" checked >
							<label for="">云购物自营</label>
							<a href="#" style="position:relative;padding-left:50px"><i class="icon-kefu"></i>联系客服</a>
							<ul class="clearfix fr" style="padding-right:20px">
								<li><i class="pc-shop-car-yun"></i>满109元减10</li>
								<li><i class="pc-shop-car-yun"></i>领取3种优惠券, 最高省30元</li>
							</ul>
						</td>
					</tr>
					<c:forEach items="${cartList }" var="cart" varStatus="status">
						<tr id="tr${status.index }">
							<th><input type="checkbox" checked="checked" data-id="${cart.id }" data-index="${status.index }" class="checkbox" style="margin-left:10px; float:left"></th>
							<th class="tab-th-1">
								<a href="../goods/detail.do?id=${cart.goods.id }">
									<c:forEach items="${cart.goods.photoList }" var="p">
										<c:if test="${p.cover==1 }">
											<img src="../goods/${p.path }" width="100%" alt="">
										</c:if>
									</c:forEach>
								</a>
								<a href="../goods/detail.do?id=${cart.goods.id }" class="tab-title">${cart.goods.name }</a>
							</th>
							<th>
								<p>${cart.goods.detail }</p>
							</th>
							<th>
								<p>${cart.goods.price }</p>
								<p class="red">${cart.goods.price }</p>
							</th>
							<th class="tab-th-2">
								<span data-index="${status.index }" class="decrease">-</span>
								<input id="count${status.index }" class="count shul" data-index="${status.index }" data-goodsid="${cart.goods.id }" data-goodsstock="${cart.goods.stock}" type="text" value="${cart.count }" maxlength="3" placeholder="">
								<span class="increase" data-index="${status.index }">+</span>
							</th>
							<th id="l_amount${status.index }" class="red">${cart.l_amount }</th>
							<th><a class="deleteOne" data-id="${cart.id }" data-index="${status.index }" href="javascript:return false">删除</a></th>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
	<div style="height:10px"></div>
	<div class="center">
		<div class="clearfix pc-shop-go">
			<div class="fl pc-shop-fl">
				<input class="allSelect" checked="checked" type="checkbox" placeholder="">
				<label for="">全选</label>
				<a href="#">删除</a>
				<a href="#">清楚失效商品</a>
			</div>
			<div class="fr pc-shop-fr">
				<p>共有 <em class="red pc-shop-shu" id="cartSum"></em> 款商品，总计（不含运费）</p>
				<span>¥</span><span id="t_amount"></span>
				<a id="generateOrder" href="javascript:return false">去付款</a>
			</div>
		</div>
	</div>
</section>



<div style="height:100px"></div>

<footer>
	<div class="pc-footer-top">
		<div class="center">
			<ul class="clearfix">
				<li>
					<span>关于我们</span>
					<a href="#">关于我们</a>
					<a href="#">诚聘英才</a>
					<a href="#">用户服务协议</a>
					<a href="#">网站服务条款</a>
					<a href="#">联系我们</a>
				</li>
				<li class="lw">
					<span>购物指南</span>
					<a href="#">新手上路</a>
					<a href="#">订单查询</a>
					<a href="#">会员介绍</a>
					<a href="#">网站服务条款</a>
					<a href="#">帮助中心</a>
				</li>
				<li class="lw">
					<span>消费者保障</span>
					<a href="#">人工验货</a>
					<a href="#">退货退款政策</a>
					<a href="#">运费补贴卡</a>
					<a href="#">无忧售后</a>
					<a href="#">先行赔付</a>
				</li>
				<li class="lw">
					<span>商务合作</span>
					<a href="#">人工验货</a>
					<a href="#">退货退款政策</a>
					<a href="#">运费补贴卡</a>
					<a href="#">无忧售后</a>
					<a href="#">先行赔付</a>
				</li>
				<li class="lss">
					<span>下载手机版</span>
					<div class="clearfix lss-pa">
						<div class="fl lss-img"><img src="../img/icon/code.png" alt=""></div>
						<div class="fl" style="padding-left:20px">
							<h4>扫描下载云购APP</h4>
							<p>把优惠握在手心</p>
							<P>把潮流带在身边</P>
							<P></P>
						</div>
					</div>
				</li>
			</ul>
		</div>
		<div class="pc-footer-lin">
			<div class="center">
				<p>友情链接：
					卡宝宝信用卡
					梦芭莎网上购物
					手游交易平台
					法律咨询
					深圳地图
					P2P网贷导航
					名鞋库
					万表网
					叮当音乐网
					114票务网
					儿歌视频大全
				</p>
				<p>
					京ICP证1900075号  京ICP备20051110号-5  京公网安备110104734773474323  统一社会信用代码 91113443434371298269B  食品流通许可证SP1101435445645645640352397
				</p>
				<p style="padding-bottom:30px">版物经营许可证 新出发京零字第朝160018号  Copyright©2011-2015 版权所有 ZHE800.COM </p>
			</div>
		</div>
	</div>
</footer>
<script type="text/javascript">
    //hover 触发两个事件，鼠标移上去和移走
    //mousehover 只触发移上去事件
    $(".top-nav ul li").hover(function(){
        $(this).addClass("hover").siblings().removeClass("hover");
        $(this).find("li .nav a").addClass("hover");
        $(this).find(".con").show();
    },function(){
        //$(this).css("background-color","#f5f5f5");
        $(this).find(".con").hide();
        //$(this).find(".nav a").removeClass("hover");
        $(this).removeClass("hover");
        $(this).find(".nav a").removeClass("hover");
    })
</script>
</body>
</html>