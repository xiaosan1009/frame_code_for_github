<fmt:setBundle basename="cn.smeltery.resource.filiale.030200" scope="page" />
<table width="1073px" border="1" cellspacing="0" cellpadding="0" style="border-bottom-width:0;" class="font14 tableStyle">
  <tr>
	<td style="border-bottom-width:0;" class="headerColor noStyle"><input type="checkbox" name="checkAll" id="checkAll" /></td>
	<td width="90" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0016" /><!--库单号 --></td>
	<td width="90" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0019" /><!--供货商 --></td>
	<td width="90" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0020" /><!--出库单号 --></td>
	<td width="100" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0010" /><!-- 分厂 --></td>
	<td width="100" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0001" /><!-- 大分类 --></td>
	<td width="100" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0002" /><!-- 小分类 --></td>
	<td width="80" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0003" /><!-- 数量 --></td>
	<td width="90" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0004" /><!-- 单价 --></td>
	<td width="90" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0021" /><!-- 总价 --></td>
	<td width="90" style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0005" /><!-- 入库时间 --></td>
	<td style="border-bottom-width:0;" class="headerColor"><fmt:message key="FS_0014" /><!-- 消耗量 --></td>
  </tr>
</table>
<div style="max-height: 322px;_height: expression(this.scrollHeight>322?'322px':'auto');width:1090px;overflow:auto;margin: 0;">
	<table width="1073px" border="1" cellspacing="0" cellpadding="0" class="font14 tableStyle">
	<af:forEachList>
		<tr %{line.alternateColor} %{tr_id.style}>
			<td class="headerColor noStyle">
				<input type="checkbox" name="indexs" value="%{line.index}" />
			</td>
			<td width="90">
				<span>%{orderNo.value}</span>
				<input type="hidden" name="batch" value="%{batch.value}">
				<input type="hidden" name="orderNo" value="%{orderNo.value}">
			</td>
			<td width="90">
				<span>%{supplier.value}</span>
			</td>
			<td width="90">
				<span>%{deliverednoteno.value}</span>
			</td>
			<td width="100">
				<span>%{filialeName.value}</span>
				<input type="hidden" name="filialeId" value="%{filialeId.value}">
			</td>
			<td width="100">
				<span>%{parentTypeName.value}</span>
				<input type="hidden" name="parentTypeId" value="%{parentTypeId.value}">
			</td>
			<td width="100">
				<span>%{typeName.value}</span>
				<input type="hidden" name="typeId" value="%{typeId.value}">
				<input type="hidden" name="price" value="%{price.value}">
			</td>
			<td width="80">
				<span>%{numLabel.value}</span>
				<input type="hidden" name="number" value="%{number.value}">
			</td>
			<td width="90">
				<span>%{price.value}</span>
			</td>
			<td width="90">
				<span>%{sum.value}</span>
			</td>
			<td width="90">
				<span>%{turnoverDate.value}</span>
			</td>
			<td align="center">
				<input id="outNumber_%{line.index}" style="width:80px" name="outNumber" type="text" />
			</td>
		</tr>
	</af:forEachList>
	</table>
</div>
