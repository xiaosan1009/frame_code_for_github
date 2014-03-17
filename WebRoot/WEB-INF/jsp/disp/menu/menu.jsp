<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>OA管理系统</title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<link href="css/disp/menu/menu.css" rel="stylesheet" type="text/css" />
		<link href="css/disp/menu/menu_left.css" rel="stylesheet" type="text/css"/>
		<link href="css/disp/menu/main.css" rel="stylesheet" type="text/css" />
		<link href="css/disp/menu/style.css" rel="stylesheet" type="text/css" />
		<link href="css/frame/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/frame/jquery/jquery.ztree.core-3.5.js" ></script>
		<script type="text/javascript" src="js/disp/menu/menu.js"></script>
	</head>

	<body class="contentBody" style="overflow-y:hidden;">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<!-- 开始菜单浮动层 -->
			<div id="startmenudiv">
				<div id="startmenuleftborder"></div>
				<div id="startmenumiddle">
					<div id="startleft">
<!--						<div id="programsleftborder">-->
<!--						</div>-->
						<div id="programsmiddle">
							<div class="middletop">
								<div class="programssmall"
									onMouseOver="this.className='programssmallhover'"
									onMouseOut="this.className='programssmall'"
									onClick="showQuickNewMenu()" id="quicknewmenulink">
									<div class="navicon"><img src="img/disp/menu/icons/navicon.png">
									</div>
									<div class="menuslinkbutton">
										快捷新建
									</div>
								</div>
								<div class="divline"></div>
								<!--快捷新建div-->
								<div id="quicknew" style="overflow-y: auto;">
									<div class="programsbig"
										onClick="clickMenu('middleFrame','/general/sms/new','207')">
										<a href="#"><img src="img/disp/menu/icons/32/207.png" alt="" />撰写消息</a>
									</div>
									<div class="programsbig"
										onClick="clickMenu('middleFrame','/general/news/manage','93')">
										<a href="#"><img src="img/disp/menu/icons/32/93.png" alt="" />新闻发布</a>
									</div>
									<div class="programsbig"
										onClick="clickMenu('middleFrame','/general/file_folder/file_new/neworedit','311')">
										<a href="#"><img src="img/disp/menu/icons/32/311.png" alt="" />新建个人文档</a>
									</div>
									<div class="programsbig"
										onClick="clickMenu('middleFrame','/general/file_folder/file_new/neworedit/index1.php','316')">
										<a href="#"><img src="img/disp/menu/icons/32/316.png" alt="" />新建公共文档</a>
									</div>
									<div class="programsbig"
										onClick="clickMenu('middleFrame','/general//system/workflow/flow_type','57')">
										<a href="#"><img src="img/disp/menu/icons/32/57.png" alt="" />定义流程</a>
									</div>
									<div class="programsbig"
										onClick="clickMenu('middleFrame','/general//system/workflow/flow_form','201')">
										<a href="#"><img src="img/disp/menu/icons/32/201.png" alt="" />定义表单</a>
									</div>
									<div class="programsbig"
										onClick="clickMenu('middleFrame','/general/workflow/new/','2')">
										<a href="#"><img src="img/disp/menu/icons/32/2.png" alt="" />新建流程</a>
									</div>
								</div>
								<!--所有菜单div-->
								<div id="allmenus">
									<ul id="tree" class="ztree"></ul>
								</div>
								<!--默认菜单div-->
								<div id="defaultprogram">
									<div class="programsbig"
										onMouseOver="this.className='programsbighover'"
										onMouseOut="this.className='programsbig'"
										onClick="clickMenu('middleFrame','/general/news/show/newslist.php?func_id=130','130')">
										<a href="#"><img src="img/disp/menu/icons/32/130.png" alt="" />查看新闻</a>
									</div>
									<div class="programsbig"
									onMouseOver="this.className='programsbighover'"
										onMouseOut="this.className='programsbig'"
										onClick="clickMenu('middleFrame','/general/file_folder/index1.php?func_id=315','315')">
										<a href="#"><img src="img/disp/menu/icons/32/315.png" alt="" />查看公共文档</a>
									</div>
									<div class="programsbig"
									onMouseOver="this.className='programsbighover'"
										onMouseOut="this.className='programsbig'"
										onClick="clickMenu('middleFrame','/general/file_folder/file_new/neworedit/index1.php?func_id=316','316')">
										<a href="#"><img src="img/disp/menu/icons/32/316.png" alt="" />新建公共文档</a>
									</div>
									<div class="programsbig"
									onMouseOver="this.className='programsbighover'"
										onMouseOut="this.className='programsbig'"
										onClick="clickMenu('middleFrame','/general/workflow/overtime/index.php?func_id=376','376')">
										<a href="#"><img src="img/disp/menu/icons/32/376.png" alt="" />超时查询</a>
									</div>
									<div class="programsbig"
									onMouseOver="this.className='programsbighover'"
										onMouseOut="this.className='programsbig'"
										onClick="clickMenu('middleFrame','/general/system/user?func_id=98','98')">
										<a href="#"><img src="img/disp/menu/icons/32/98.png" alt="" />用户管理</a>
									</div>
									<div class="programsbig"
									onMouseOver="this.className='programsbighover'"
										onMouseOut="this.className='programsbig'"
										onClick="clickMenu('middleFrame','/general/system/user_priv?func_id=99','99')">
										<a href="#"><img src="img/disp/menu/icons/32/99.png" alt="" />角色管理</a>
									</div>
									<div class="programsbig"
									onMouseOver="this.className='programsbighover'"
										onMouseOut="this.className='programsbig'"
										onClick="clickMenu('middleFrame','/general/sms/new?func_id=207','207')">
										<a href="#"><img src="img/disp/menu/icons/32/207.png" alt="" />撰写消息</a>
									</div>
									<div class="programsbig"
									onMouseOver="this.className='programsbighover'"
										onMouseOut="this.className='programsbig'"
										onClick="clickMenu('middleFrame','/general/notify/show/index.php?func_id=131','131')">
										<a href="#"><img src="img/disp/menu/icons/32/131.png" alt="" />查看公告</a>
									</div>
								</div>
								<!--所有菜单上面的分割线-->
								<div class="divline"></div>
								<div class="programssmall"
									onMouseOver="this.className='programssmallhover'"
									onMouseOut="this.className='programssmall'"
									onClick="showAllMenu()" id="allmenuslink">
									<div class="navicon" id="allmenuslinkdiv">
										<img src="img/disp/menu/icons/navicon.png">
									</div>
									<div class="menuslinkbutton">
										所有菜单
									</div>
								</div>
							</div>
							<!--搜索菜单div-->
							<div id="searchmenus"></div>
							<!--菜单搜索上面的背景-->
							<div id="searchmenubg"></div>
							<!--菜单搜索div-->
							<div id="searchmenudiv">
								<div id="searchmenuleftbg"></div>
								<div id="searchmenumiddletbg">
									<input type="text" id="searchMenuKeyWord" size="25">
								</div>
								<div id="searchmenurightbg"></div>
							</div>
						</div>
<!--						<div id="programsrightborder"></div>-->
					</div>
					<div id="startright">
<!--						<div id="linksleftborder"></div>-->
						<div id="linksmiddle">
							<div id="avatardiv">
								<img src="img/disp/menu/icons/engine_contact_head.png"
									onerror="this.src='/attachment/personal/noimage_80.png'"
									align="absbottom" width="80" id="avatorimgid"
									onClick="changeAvator()">
							</div>
							<div class="links" onMouseOver="this.className='linkshover'"
								onMouseOut="this.className='links'"
								onClick="clickMenu('middleFrame','/general/person_info/pass/')">
								密码
							</div>
							<div class="links" onMouseOver="this.className='linkshover'"
								onMouseOut="this.className='links'"
								onClick="clickMenu('middleFrame','/general/person_info/info/')">
								个人资料
							</div>
							<div class="links" onMouseOver="this.className='linkshover'"
								onMouseOut="this.className='links'"
								onClick="clickMenu('middleFrame','/general/person_info/set/')">
								其它设置
							</div>
							<div class="links" onMouseOver="this.className='linkshover'"
								onMouseOut="this.className='links'"
								onClick="clickMenu('blank||width=300,height=300,top=200,left=300,status=0,toolbar=no,resizable=yes,scrollbars=1',' /general/memopen/index.php')">
								便签
							</div>
							<div class="links" onMouseOver="this.className='linkshover'"
								onMouseOut="this.className='links'"
								onClick="clickMenu('blank||width=456,height=380,top=200,left=300,status=0,toolbar=no,resizable=yes','/general/version.php')">
								版本信息
							</div>
							<div class="links" onMouseOver="this.className='linkshover'"
								onMouseOut="this.className='links'"
								onClick="clickMenu('blank||width=430,height=405,location=no,resizable=0,scrollbars=0,status=no,toolbar=no,menu=no,top=100,left=200','/general/down.php')">
								插件下载
							</div>
							<div class="links" onMouseOver="this.className='linkshover'"
								onMouseOut="this.className='links'"
								onClick="clickMenu('blank||width=610,height=500,top=200,left=300,status=0,toolbar=no,resizable=yes','http://webchat.tq.cn/sendmain.jsp?uin=8038959&agentid=0&transferpage=1&tq_bottom_ad_url=http://qtt.tq.cn/post/sendmain.html&tq_right_infocard_url=http://qtt.tq.cn/showcard.do&page=&ispaymoney=1&buttonsflag=&iscallback=0&rand=8323811151122')">
								泛微客服
							</div>
							<div class="links" onMouseOver="this.className='linkshover'"
								onMouseOut="this.className='links'"
								onClick="clickMenu('blank||width=590,height=300,location=no,resizable=0,scrollbars=0,status=no,toolbar=no,menu=no,top=100,left=200','/helppage/help.php')">
								帮助中心
							</div>
						</div>
<!--						<div id="linksrightborder"></div>-->
					</div>
				</div>
				<div id="startmenurightborder"></div>
			</div>
			<div id="main">
				<div id="header">
					<div id="userBar">
						<div style="text-align: right;padding: 10px;">
							你好，<span id="userName"></span>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" onclick="userLogout()">退出</a>
						</div>
					</div>
				</div>
				<div id="content">
<!--					<div id="left_menu">-->
<!--						<div id="list1"></div>-->
<!--						<div id="toggle" class="toggle">-->
<!--							<img style="position: absolute;top: 50%;" src="img/disp/menu/handleV.gif" width="3px" height="39px"/>-->
<!--						</div>-->
<!--					</div>-->
					<div id="container">
						<div id="operate">
							<iframe style="width:100%;height:100%;" src="main?dispcode=000003&cmdcode=0&clientType=1" name="operateFrame" id="operateFrame" frameborder="0" scrolling="auto"></iframe>
						</div>
					</div>
				</div>
				<div id="footer" style="position: absolute;bottom: -5;left: 0;">
					<table width="100%" height="48" cellpadding="0" cellspacing="0">
						<tr>
							<td width="50" height="48" id="startmenutd" class="bottom">
								<div class="startmenu"></div>
							</td>
							<td id="bottom" class="bottom">
								&nbsp;
							</td>
						</tr>
					</table>
				</div>
			</div>
			
			<iframe src="<%=request.getContextPath() %>/html/glayer.html" class="loadingOnClass" name="glayoutFrame" id="glayoutFrame" scrolling="no" frameborder="0" marginheight="0" marginwidth="0" width="0"></iframe>
		</form>
	</body>
</html>
