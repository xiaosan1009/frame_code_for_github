<fmt:setBundle basename="cn.smeltery.resource.filiale.030100" scope="page" />
<table width="1113px" border="1" cellspacing="0" cellpadding="0" style="border-bottom-width:0;" class="font14 tableStyle">
  <tr>
	<td style="border-bottom-width:0;" class="headerColor noStyle"><fmt:message key="FS_0009" /><!-- No. --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0010" /><!-- 分厂 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0014" /><!-- 库单号 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0017" /><!-- 供货商 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0018" /><!-- 出库单号 --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0001" /><!-- 大分类 --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0002" /><!-- 小分类 --></td>
	<td width="90" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0003" /><!-- 数量 --></td>
	<td width="90" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0004" /><!-- 单价 --></td>
	<td width="90" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0019" /><!-- 总价 --></td>
	<td width="100" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0011" /><!-- 有效期 --></td>
	<td width="100" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0005" /><!-- 入库时间 --></td>
  </tr>
<af:forEachList>
	<tr %{line.alternateColor} %{tr_id.style}>
		<td class="headerColor noStyle">
			<span>%{line.index.2 + 1}</span>
		</td>
		<td align="center">
			<span>%{filialeName.value}</span>
		</td>
		<td>
			<span>%{orderNo.value}</span>
		</td>
		<td>
			<span>%{supplier.value}</span>
		</td>
		<td>
			<span>%{deliveredNoteNo.value}</span>
		</td>
		<td align="center">
			<span>%{parentTypeName.value}</span>
		</td>
		<td align="center">
			<span>%{typeName.value}</span>
		</td>
		<td>
			<span>%{number.value}</span>
		</td>
		<td>
			<span>%{price.value}</span>
		</td>
		<td>
			<span>%{sum.value}</span>
		</td>
		<td>
			<span>%{effectiveDate.value}</span>
		</td>
		<td>
			<span>%{turnoverDate.value}</span>
		</td>
	</tr>
</af:forEachList>
</table>
