<fmt:setBundle basename="cn.smeltery.resource.client.010004" scope="page" />
<table width="1013px" border="1" cellspacing="0" cellpadding="0" style="border-bottom-width:0;" class="font14 tableStyle">
  <tr>
	<td style="border-bottom-width:0;" class="headerColor noStyle"><fmt:message key="FS_0003" /><!-- No --></td>
	<td style="border-bottom-width:0;" class="headerColor boxsmallTd"><fmt:message key="FS_0001" /><!-- 姓 名 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0002" /><!-- 电 话 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0004" /><!-- 传 真 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0005" /><!-- 邮 箱 --></td>
	<td style="border-bottom-width:0;width: 40px;" class="headerColor"><fmt:message key="FS_0006" /><!-- 性 别 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0008" /><!-- 修改 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0009" /><!-- 删除 --></td>
  </tr>
</table>
<div style="height:auto;width:1030px;overflow:auto;margin: 0;">
	<table width="1013px" border="1" cellspacing="0" cellpadding="0" class="font14 tableStyle">
	<af:forEachList>
		<tr %{line.alternateColor} %{tr_id.style}>
			<td class="headerColor noStyle">
				<span>%{line.index.2 + 1}</span>
			</td>
			<td class="boxsmallTd" align="center">
<!--				<span>%{clientName.value}</span>-->
				<select class="searchBarWidgetMagin">
					<af:cmb targetId="clientSex">
						<option value="%{clientSex.value}" %{clientSex.selected}>%{clientSex.text}</option>
					</af:cmb>
				</select>
			</td>
			<td align="center">
				<span>%{clientTel.value}</span>
			</td>
			<td align="center">
				<span>%{clientFax.value}</span>
			</td>
			<td align="center">
				<span>%{clientMail.value}</span>
			</td>
			<td width="40" align="center">
				<span>%{clientSex.value}</span>
			</td>
			<td align="center" valign="middle">
				<input type="button" class="btn btn-submit" tabindex="5" value="<fmt:message key="FS_0008" />" onclick="updateUserInfo(%{clientId.value})"/>
			</td>
			<td align="center" valign="middle">
				<input type="button" class="btn btn-submit" tabindex="6" value="<fmt:message key="FS_0009" />" onclick="deleteUserInfo(%{clientId.value})"/>
			</td>
		</tr>
	</af:forEachList>
	</table>
</div>