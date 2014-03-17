<af:forEachList>
	<div style="width: 200px;float: left;margin: 5px;text-align: left;padding-left: 10;padding-top: 3" class="styleType1">
		<span style="height: 22;font-size: 14;width: 100px;">%{typeName.value}</span>
		<img src="img/frame/common/edit.gif" style="margin-left: 5;cursor: pointer;" onclick="editType(false, this.parentNode, %{typeId.value})">
		<img src="img/frame/common/delete.gif" style="margin-left: 5;cursor: pointer;" onclick="deleteType(false, this.parentNode, %{typeId.value})">
	</div>
</af:forEachList>
