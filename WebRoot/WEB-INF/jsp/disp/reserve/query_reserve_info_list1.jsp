<fmt:setBundle basename="cn.smeltery.resource.reserve.020800" scope="page" />
<table width="1113px" border="1" cellspacing="0" cellpadding="0" style="border-bottom-width:0;" class="font14 tableStyle">
  <tr>
	<td style="border-bottom-width:0;" class="headerColor noStyle"><fmt:message key="FS_0009" /><!-- No. --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0014" /><!-- 库单号 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0018" /><!-- 供货商 --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0013" /><!-- 类型 --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0001" /><!-- 大分类 --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0002" /><!-- 小分类 --></td>
	<td width="80" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0003" /><!-- 数量 --></td>
	<td width="120" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0004" /><!-- 单价 --></td>
	<td width="120" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0019" /><!-- 总价 --></td>
	<td width="100" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0011" /><!-- 有效期 --></td>
	<td width="100" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0005" /><!-- 入库时间 --></td>
  </tr>
</table>
<div style="height: auto;width:1130px;overflow:auto;margin: 0;">
	<table width="1113px" border="1" cellspacing="0" cellpadding="0" class="font14 tableStyle">
	<af:forEachList>
		<tr %{line.alternateColor} %{tr_id.style}>
			<td class="headerColor noStyle">
				<span>%{line.index.2 + 1}</span>
			</td>
			<td>
				<span>%{orderNo.value}</span>
				<input type="hidden" name="batch" value="%{batch.value}">
			</td>
			<td>
				<span>%{supplier.value}</span>
				<input type="hidden" name="supplier" value="%{supplier.value}">
			</td>
			<td width="110">
				<span>%{type.value}</span>
			</td>
			<td width="110">
				<span>%{parentTypeName.value}</span>
				<input type="hidden" name="parentTypeId" value="%{parentTypeId.value}">
			</td>
			<td width="110">
				<span>%{typeName.value}</span>
				<input type="hidden" name="typeId" value="%{typeId.value}">
				<input type="hidden" name="price" value="%{price.value}">
			</td>
			<td width="80">
				<span>%{number.value}</span>
				<input type="hidden" name="number" value="%{number.value}">
			</td>
			<td width="120">
				<span>%{price.value}</span>
			</td>
			<td width="120">
				<span>%{sum.value}</span>
			</td>
			<td width="100">
				<span>%{effectiveDate.value}</span>
				<input type="hidden" name="effectiveDate" value="%{effectiveDate.value}">
			</td>
			<td width="100">
				<span>%{turnoverDate.value}</span>
			</td>
		</tr>
	</af:forEachList>
	</table>
</div>
