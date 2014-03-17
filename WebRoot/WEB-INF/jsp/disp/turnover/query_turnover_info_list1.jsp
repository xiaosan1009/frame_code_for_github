<fmt:setBundle basename="cn.smeltery.resource.turnover.020900" scope="page" />
<table width="1113px" border="1" cellspacing="0" cellpadding="0" style="border-bottom-width:0;" class="font14 tableStyle">
  <tr>
	<td style="border-bottom-width:0;" class="headerColor noStyle"><fmt:message key="FS_0009" /><!-- No. --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0017" /><!-- 库单号 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0020" /><!-- 供货商 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0021" /><!-- 出库单号 --></td>
	<td width="80" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0001" /><!-- 大分类 --></td>
	<td width="110" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0002" /><!-- 小分类 --></td>
	<td width="80" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0003" /><!-- 数量 --></td>
	<td width="80" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0004" /><!-- 单价 --></td>
	<td width="80" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0022" /><!-- 总价 --></td>
	<td width="100" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0011" /><!-- 有效期 --></td>
	<td width="100" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0005" /><!-- 操作日期 --></td>
	<td width="50" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0016" /><!-- 状态 --></td>
	<td width="130" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0010" /><!-- 分厂 --></td>
  </tr>
</table>
<div style="height:auto;width:1130px;overflow:auto;margin: 0;">
	<table width="1113px" border="1" cellspacing="0" cellpadding="0" class="font14 tableStyle">
	<af:forEachList>
		<tr %{line.alternateColor} %{tr_id.style} onclick="showDetail('%{turnover_id.value}', '%{turnover_type.value}', '%{type.value}', '%{parentTypeId.value}')">
			<td class="headerColor noStyle">
				<span>%{line.index.2 + 1}</span>
			</td>
			<td>
				<span>%{orderNo.value}</span>
				<input type="hidden" name="batch" value="%{batch.value}">
			</td>
			<td>
				<span>%{supplier.value}</span>
			</td>
			<td>
				<span>%{deliverednoteno.value}</span>
			</td>
			<td width="80">
				<span>%{parentTypeName.value}</span>
				<!-- 父类型 -->
				<input type="hidden" name="parentTypeId" value="%{parentTypeId.value}">
			</td>
			<td width="110">
				<span>%{typeName.value}</span>
				<!-- 子类型 -->
				<input type="hidden" name="typeId" value="%{typeId.value}">
				<input type="hidden" name="price" value="%{price.value}">
			</td>
			<td width="80">
				<span>%{numberLabel.value}</span>
				<input type="hidden" name="number" value="%{numberLabel.value}">
			</td>
			<td width="80">
				<span>%{price.value}</span>
			</td>
			<td width="80">
				<span>%{sum.value}</span>
			</td>
			<td width="100">
				<span>%{effectiveDate.value}</span>
				<input type="hidden" name="effectiveDate" value="%{effectiveDate.value}">
			</td>
			<td width="100">
				<span>%{turnoverDate.value}</span>
			</td>
			<td width="50" align="center">
				<span %{turnoverType.style}>%{turnoverType.value}</span>
				<!-- 出入库 -->
				<input type="hidden" name="turnover_type" value="%{turnover_type.value}">
				<!-- id -->
				<input type="hidden" name="turnover_id" value="%{turnover_id.value}">
				<!-- 原辅材料背辅材料类型 -->
				<input type="hidden" name="type" value="%{type.value}">
			</td>
			<td width="130">
				<span>%{filialeName.value}</span>
			</td>
		</tr>
	</af:forEachList>
	</table>
</div>
