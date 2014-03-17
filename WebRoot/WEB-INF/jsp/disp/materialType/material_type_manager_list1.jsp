<div style="width:auto;overflow:auto;">
<af:forEachList>
	<div id="parent_%{line.index}" class="styleType" style="text-align: left;padding-left: 10px;padding-top: 3">
		<span style="height: 22;font-size: 14;width: 100px;cursor: pointer;" onclick="toggle(%{typeId.value}, this.parentNode)">%{typeName.value}</span>
		<img src="img/frame/common/add.gif" style="cursor: pointer;" onclick="addType(false, this.parentNode, %{typeId.value})">
		<img src="img/frame/common/edit.gif" style="margin-left: 5;cursor: pointer;" onclick="editType(true, this.parentNode, %{typeId.value})">
		<img src="img/frame/common/delete.gif" style="margin-left: 5;cursor: pointer;" onclick="deleteType(true, this.parentNode, %{typeId.value})">
	</div>
</af:forEachList>
</div>