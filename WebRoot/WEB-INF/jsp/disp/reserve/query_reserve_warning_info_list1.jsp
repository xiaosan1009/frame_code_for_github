<fmt:setBundle basename="cn.smeltery.resource.reserve.040300" scope="page" />
<table width="1013px" border="1" cellspacing="0" cellpadding="0" style="border-bottom-width:0;" class="font14 tableStyle">
  <tr>
	<td style="border-bottom-width:0;" class="headerColor noStyle"><fmt:message key="FS_0009" /><!-- No. --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0013" /><!-- 类型 --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0001" /><!-- 大分类 --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0002" /><!-- 小分类 --></td>
	<td width="120" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0003" /><!-- 数量 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0014" /><!-- 上限 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0015" /><!-- 下限--></td>
  </tr>
</table>
<div style="height: auto;width:1030px;overflow:auto;margin: 0;">
	<table width="1013px" border="1" cellspacing="0" cellpadding="0" class="font14 tableStyle">
	<af:forEachList>
		<tr %{line.alternateColor} %{tr_id.style}>
			<td class="headerColor noStyle">
				<span>%{line.index.2 + 1}</span>
			</td>
			<td width="110">
				<span>%{type.value}</span>
			</td>
			<td width="110">
				<span>%{parentTypeName.value}</span>
			</td>
			<td width="110">
				<span>%{typeName.value}</span>
			</td>
			<td width="120">
				<span>%{number.value}</span>
			</td>
			<td>
				<span>%{topLimit.value}</span>
			</td>
			<td>
				<span>%{floorLimit.value}</span>
			</td>
		</tr>
	</af:forEachList>
	</table>
</div>
