<fmt:setBundle basename="cn.smeltery.resource.materialLimit.040100" scope="page" />
<table width="713px" border="1" cellspacing="0" cellpadding="0" style="border-bottom-width:0;" class="font14 tableStyle">
  <tr>
	<td style="border-bottom-width:0;" class="headerColor noStyle"><fmt:message key="FS_0003" /><!-- No. --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0001" /><!-- 大分类 --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0002" /><!-- 小分类 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0004" /><!-- 上限 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0005" /><!-- 下限 --></td>
  </tr>
</table>
<div style="height: auto;width:730px;overflow:auto;margin: 0;">
	<table width="713px" border="1" cellspacing="0" cellpadding="0" class="font14 tableStyle">
	<af:forEachList>
		<tr %{line.alternateColor} %{tr_id.style}>
			<td class="headerColor noStyle">
				<span>%{line.index.2 + 1}</span>
			</td>
			<td width="110">
				<span>%{parentName.value}</span>
				<input type="hidden" %{typeId.id} value="%{typeId.value}">
			</td>
			<td width="110">
				<span>%{typeName.value}</span>
			</td>
			<td align="center">
				<span style="margin-right: 10px;">%{topLimit.value}</span>
				<input type="text" %{topLimit.id} value="%{topLimit.value}" style="display: none;margin-right: 10px;" onblur="limitChange(%{line.index})">
				<img src="img/frame/common/edit.gif" style="cursor: pointer;" onclick="limitEdit()">
			</td>
			<td align="center">
				<span style="margin-right: 10px;">%{floorLimit.value}</span>
				<input type="text" %{floorLimit.id} value="%{floorLimit.value}" style="display: none;margin-right: 10px;" onblur="limitChange(%{line.index})">
				<img src="img/frame/common/edit.gif" style="cursor: pointer;" onclick="limitEdit()">
			</td>
		</tr>
	</af:forEachList>
	</table>
</div>
