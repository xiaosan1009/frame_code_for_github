<div style="width:auto;overflow:auto;">
<af:forEachList>
	<div %{id_0001.id} class="styleType">
		<h5 onclick="toggle(this.parentNode)">
			<span>%{id_0001.value}</span>
		</h5>
		<af:forDimension>
			<div class="styleItem" onclick="jumpFunction('%{id_0012.value}', this)">
				<span %{id_0011.id}>%{id_0011.value}</span>
			</div>
		</af:forDimension>
	</div>
</af:forEachList>
</div>