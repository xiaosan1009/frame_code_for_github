<af:forEachList targetId="${param.targetId}">
	<option value="%{${param.targetId}.value}" %{${param.targetId}.selected}>%{${param.targetId}.text}</option>
</af:forEachList>