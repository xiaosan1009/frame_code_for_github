<fmt:setBundle basename="cn.smeltery.resource.client.010004" scope="page" />
<table width="1013px" border="1" cellspacing="0" cellpadding="0" style="border-bottom-width:0;" class="font14 tableStyle">
  <tr>
	<td style="border-bottom-width:0;" class="headerColor noStyle">No.</td>
	<td style="border-bottom-width:0;" class="headerColor" width="200">部门</td>
	<td style="border-bottom-width:0;" class="headerColor">员工姓名</td>
	<td style="border-bottom-width:0;" class="headerColor">考勤日</td>
	<td style="border-bottom-width:0;" class="headerColor">入室时间</td>
	<td style="border-bottom-width:0;" class="headerColor">出室时间</td>
	<td style="border-bottom-width:0;" class="headerColor">理由</td>
	<td style="border-bottom-width:0;" class="headerColor">状态</td>
	<td style="border-bottom-width:0;" class="headerColor">加班时间</td>
  </tr>
<af:forEachList>
	<tr %{line.alternateColor}>
		<td class="headerColor noStyle">
			<span>%{unit.value}</span>
		</td>
		<td align="center">
			<span>%{inner.value}</span>
		</td>
		<td align="center">
			<span>%{batteryNo.value}</span>
		</td>
		<td align="center">
			<span>%{createDate.value}</span>
		</td>
		<td align="center">
			<span>%{checkDate.value}</span>
		</td>
		<td align="center">
			<span>%{volume.value}</span>
		</td>
		<td align="center">
			<span>%{weight.value}</span>
		</td>
		<td align="center">
			<span>%{wallThickness.value}</span>
		</td>
		<td align="center">
			<span>%{medium.value}</span>
		</td>
	</tr>
</af:forEachList>
</table>
