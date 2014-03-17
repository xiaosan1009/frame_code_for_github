<fmt:setBundle basename="cn.smeltery.resource.turnover.020400" scope="page" />
<table width="1113px" border="1" cellspacing="0" cellpadding="0" style="border-bottom-width:0;" class="font14 tableStyle">
  <tr>
	<td style="border-bottom-width:0;" class="headerColor noStyle"><input type="checkbox" name="checkAll" id="checkAll" /></td>
	<td width="120" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0014" /><!-- 库单号 --></td>
	<td width="100" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0015" /><!-- 供货商 --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0001" /><!-- 大分类 --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0002" /><!-- 小分类 --></td>
	<td width="100" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0003" /><!-- 数量 --></td>
	<td width="100" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0004" /><!-- 单价 --></td>
	<td width="100" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0013" /><!-- 总价 --></td>
	<td width="100" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0011" /><!-- 有效期 --></td>
	<td width="100" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0005" /><!-- 入库时间 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0009" /><!-- 出货量 --></td>
  </tr>
</table>
<div style="max-height: 322px;_height: expression(this.scrollHeight>322?'322px':'auto');width:1130px;overflow:auto;margin: 0;">
	<table width="1113px" border="1" cellspacing="0" cellpadding="0" class="font14 tableStyle">
	<af:forEachList>
		<tr %{line.alternateColor} %{tr_id.style}>
			<td class="headerColor noStyle">
				<input type="checkbox" name="indexs" value="%{line.index}" />
			</td>
			<td width="120">
				<span>%{orderNo.value}</span>
				<input type="hidden" name="batch" value="%{batch.value}">
				<input type="hidden" name="orderNo" value="%{orderNo.value}">
			</td>
			<td width="100">
				<span>%{supplier.value}</span>
				<input type="hidden" name="supplierValue" id="supplierValue" value="%{supplier.value}">
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
			<td width="100">
				<span>%{numLabel.value}</span>
				<input type="hidden" name="number" value="%{number.value}">
			</td>
			<td width="100">
				<span>%{price.value}</span>
			</td>
			<td width="100">
				<span>%{sum.value}</span>
			</td>
			<td width="100">
				<span>%{effectiveDate.value}</span>
				<input type="hidden" name="effectiveDate" value="%{effectiveDate.value}">
			</td>
			<td width="100">
				<span>%{turnoverDate.value}</span>
			</td>
			<td align="center">
				<input id="outNumber_%{line.index}" style="width:120px" name="outNumber" type="text" />
			</td>
		</tr>
	</af:forEachList>
	</table>
</div>
