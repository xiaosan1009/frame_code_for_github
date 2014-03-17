<fmt:setBundle basename="com.richClientFrame.resource.common.commonPageList" scope="page" />
<table width="300px" border="0" cellspacing="0" cellpadding="0" class="font12">
	<tr>
		<td align="center">
			<input onclick="pagePrevParam()" name="page_prev1"
				id="page_prev1" class="commonBtnMinimum"
				type="button" value="<fmt:message key="page_prev1" />" />
		</td>
		<td align="center">
			<input type="hidden" name="currentpage" id="currentpage" value="1">
			<span id="now_page_num1" style="width: 30px" class="font12">1</span>
			<span id="page_separator1" style="">/</span>
			<input type="hidden" name="totalPages" id="totalPages">
			<span id="all_page_num1" style="width: 30px" class="font12"></span>
		</td>
		<td width="40" align="center">
			<input onclick="pageNextParam()" id="page_next1" name="page_next1" type="button"
				value="<fmt:message key="page_next1" />" class="commonBtnMinimum " />
		</td>
		<td align="center">
			<input type="text" name="page_num1" id="page_num1" class="pageNobox" />
		</td>
		<td width="70" align="right">
			<input id="page_move1" onclick="pageGotoParam()" type="button"
				name="page_move1" value="<fmt:message key="page_move1" />"
				class="pageMoveBtn" />
		</td>
	</tr>
</table>
