<fmt:setBundle basename="cn.smeltery.resource.user.010002" scope="page" />
<table width="813px" border="1" cellspacing="0" cellpadding="0" style="border-bottom-width:0;" class="font14 tableStyle">
  <tr>
	<td style="border-bottom-width:0;" class="headerColor noStyle"><fmt:message key="FS_0003" /><!-- No --></td>
	<td style="border-bottom-width:0;" class="headerColor boxsmallTd"><fmt:message key="FS_0001" /></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0002" /></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0004" /></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0005" /></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0008" /></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0009" /></td>
  </tr>
</table>
<div style="height:auto;width:830px;overflow:auto;margin: 0;">
	<table width="813px" border="1" cellspacing="0" cellpadding="0" class="font14 tableStyle">
	<af:forEachList targetId="list1">
		<tr %{line.alternateColor} %{tr_id.style}>
			<td class="headerColor noStyle">
				<span>%{line.index.2 + 1}</span>
			</td>
			<td class="boxsmallTd">
				<span %{userName.id}>%{userName.value}</span>
			</td>
			<td>
				<span %{password.id}>%{password.value}</span>
			</td>
			<td>
				<span %{userLevel.id}>%{userLevel.value}</span>
			</td>
			<td>
				<span %{createDate.id}>%{createDate.value}</span>
			</td>
			<td align="center" valign="middle">
				<input type="button" class="btn btn-submit" tabindex="5" value="<fmt:message key="FS_0008" />" onclick="updateUserInfo(%{userId.value})"/>
			</td>
			<td align="center" valign="middle">
				<input type="button" class="btn btn-submit" tabindex="6" value="<fmt:message key="FS_0009" />" onclick="deleteUserInfo(%{userId.value})"/>
			</td>
		</tr>
	</af:forEachList>
	</table>
</div>