<%-- 画面迁移 --%>
<input type="hidden" name="dispcode" id="dispcode">			              <%-- 迁移画面ID --%>
<input type="hidden" name="screencode" id="screencode">		              <%-- 表示画面ID --%>
<input type="hidden" name="cmdcode" id="cmdcode">			              <%-- 方法ID --%>
<input type="hidden" name="targetid" id="targetid">			              <%-- 控件ID --%>
<input type="hidden" name="targetid_idx" id="targetid_idx">	              <%-- 控件INDEX --%>
<input type="hidden" name="tagId" id="tagId" value="">	                  <%-- tag ID --%>
<input type="hidden" name="clientType" id="clientType" value="1">	      <%-- client type --%>
<input type="hidden" name="dialogFlg" id="dialogFlg" value="${param.dialogFlg}">	      <%-- 是否为dialog画面 --%>

<%-- 列表 --%>
<input type="hidden" name="listindex" id="listindex"/>					  <%-- 列表INDEX --%>
<input type="hidden" name="setopenerindex" id="setopenerindex" value=""/> <%--  --%>
<input type="hidden" name="key" id="key" value="">					      <%--  --%>

<input type="hidden" name="initializeCmd" id="initializeCmd" value="${initializeCmd}">		  <%-- 分页数据总数 --%>
<input type="hidden" name="initializeTargetId" id="initializeTargetId" value="${initializeTargetId}">		  <%-- 分页数据总数 --%>
<input type="hidden" name="pagingTotalRows" id="pagingTotalRows" value="${pagingTotalRowsInit}">		  <%-- 分页数据总数 --%>
<input type="hidden" name="pagingPageSize" id="pagingPageSize" value="${pagingPageSizeInit}">		      <%-- 分页每页数据数 --%>

<%--  --%>
<input type="hidden" name="value" id="value" value="${param.value}">	  <%-- 对应js中settings中的data属性 --%>
<input type="hidden" name="autoValue" id="autoValue" value="">			  <%-- 自动补全功能共通存储值域 --%>

<div id="result" class="commonResult"></div>
