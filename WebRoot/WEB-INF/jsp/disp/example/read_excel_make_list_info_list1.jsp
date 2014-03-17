<ul>
<af:forEachList>
	<li style="width: 100;background-color: aqua;border: 1px solid #000000;cursor: pointer"
	onclick="showDataList('%{sheetName.value}')">
		<span>%{sheetName.value}</span>
	</li>
</af:forEachList>
</ul>