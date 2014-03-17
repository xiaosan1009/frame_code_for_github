<div style="width:auto;overflow:auto;">
	<table width="1013px" border="1" cellspacing="0" cellpadding="0" style="border-bottom-width:0;" class="font14 tableStyle">
	<af:forEachList>
		<tr %{line.alternateColor}>
			<td class="headerColor noStyle" height="80">
				<span>%{line.index.2 + 1}</span>
			</td>
			<td align="center" width="200">
				<input type="checkbox" name="first" value="%{id_0002.value}">
				<span>%{id_0001.value}</span>
			</td>
			<td align="center">
				<div style="white-space: nowrap;">
					<af:forDimension>
						<div style="float: left;margin:5px;">
							<input type="checkbox" name="second" value="%{id_0012.value}">
							<span>%{id_0011.value}</span>
						</div>
					</af:forDimension>
				</div>
			</td>
		</tr>
	</af:forEachList>
	</table>
</div>