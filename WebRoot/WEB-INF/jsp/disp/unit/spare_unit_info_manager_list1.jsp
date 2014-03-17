<fmt:setBundle basename="cn.smeltery.resource.unit.050400" scope="page" />
<table width="513px" border="1" cellspacing="0" cellpadding="0" style="border-bottom-width:0;" class="font14 tableStyle">
  <tr>
	<td style="border-bottom-width:0;" class="headerColor noStyle"><fmt:message key="FS_0003" /><!-- No. --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0001" /><!-- 大分类 --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0002" /><!-- 小分类 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0004" /><!-- 单位 --></td>
  </tr>
</table>
<div style="height: auto;width:530px;overflow:auto;margin: 0;">
	<table width="513px" border="1" cellspacing="0" cellpadding="0" class="font14 tableStyle">
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
				<span style="margin-right: 10px;">%{unit.value}</span>
				<input type="text" %{unit.id} value="%{unit.value}" style="display: none;margin-right: 10px;" onblur="unitChange(%{line.index})">
				<img src="img/frame/common/edit.gif" style="cursor: pointer;" onclick="unitEdit()">
			</td>
		</tr>
	</af:forEachList>
	</table>
</div>
