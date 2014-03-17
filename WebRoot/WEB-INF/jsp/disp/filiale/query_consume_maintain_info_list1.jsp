<fmt:setBundle basename="cn.smeltery.resource.filiale.030500" scope="page" />
<table width="713px" border="1" cellspacing="0" cellpadding="0" style="border-bottom-width:0;" class="font14 tableStyle">
  <tr>
	<td style="border-bottom-width:0;" class="headerColor noStyle"><fmt:message key="FS_0006" /><!-- No. --></td>
	<td width="120" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0004" /><!-- 类型 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0001" /><!-- 分厂 --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0003" /><!-- 消耗日期 --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0002" /><!-- 总金额 --></td>
  </tr>
</table>
<div style="height: auto;width:730px;overflow:auto;margin: 0;">
	<table width="713px" border="1" cellspacing="0" cellpadding="0" class="font14 tableStyle">
	<af:forEachList>
		<tr %{line.alternateColor} %{tr_id.style}>
			<td class="headerColor noStyle">
				<span>%{line.index.2 + 1}</span>
			</td>
			<td width="120" align="center">
				<span>%{type.value}</span>
			</td>
			<td>
				<span>%{filialeName.value}</span>
			</td>
			<td width="110">
				<span>%{turnoverDate.value}</span>
			</td>
			<td width="110">
				<span>%{priceSum.value}</span>
			</td>
		</tr>
	</af:forEachList>
	</table>
</div>
