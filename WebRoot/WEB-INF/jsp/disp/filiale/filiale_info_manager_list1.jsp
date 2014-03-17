<fmt:setBundle basename="cn.smeltery.resource.filiale.020700" scope="page" />
<table width="913px" border="1" cellspacing="0" cellpadding="0" style="border-bottom-width:0;" class="font14 tableStyle">
  <tr>
	<td style="border-bottom-width:0;" class="headerColor noStyle"><fmt:message key="FS_0001" /><!-- No --></td>
	<td style="border-bottom-width:0;width: 150" class="headerColor"><fmt:message key="FS_0002" /><!-- 下设名称 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0003" /><!-- 下设简介 --></td>
	<td style="border-bottom-width:0;width: 100px;" class="headerColor"><fmt:message key="FS_0008" /><!-- 修改 --></td>
	<td style="border-bottom-widthd:0;width: 100px;" class="headerColor"><fmt:message key="FS_0009" /><!-- 删除 --></td>
  </tr>
</table>
<div style="height:auto;width:930px;overflow:auto;margin: 0;">
	<table width="913px" border="1" cellspacing="0" cellpadding="0" class="font14 tableStyle">
	<af:forEachList>
		<tr %{line.alternateColor} %{tr_id.style}>
			<td class="headerColor noStyle">
				<span>%{line.index.2 + 1}</span>
			</td>
			<td width="150" style="padding-left: 5">
				<span>%{filialeName.value}</span>
			</td>
			<td style="padding-left: 5">
				<span>%{filialeDescribe.value}</span>
			</td>
			<td align="center" valign="middle" width="100">
				<input type="button" class="btn btn-submit" value="<fmt:message key="FS_0008" />" onclick="updateFilialeInfo(%{filialeId.value})"/>
			</td>
			<td align="center" valign="middle" width="100">
				<input type="button" class="btn btn-submit" value="<fmt:message key="FS_0009" />" onclick="deleteFilialeInfo(%{filialeId.value})"/>
			</td>
		</tr>
	</af:forEachList>
	</table>
</div>