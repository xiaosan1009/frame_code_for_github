<fmt:setBundle basename="cn.smeltery.resource.client.010004"
	scope="page" />
<table width="1013px" border="1" cellspacing="0" cellpadding="0"
	style="border-bottom-width: 0;" class="font14 tableStyle">
	<tr>
		<td style="border-bottom-width: 0;" class="headerColor noStyle">
			<fmt:message key="FS_0003" />
			<!-- No -->
		</td>
		<td style="border-bottom-width: 0;width: 100" class="headerColor">
			<fmt:message key="FS_0002" />
			<!-- 电 话 -->
		</td>
		<td style="border-bottom-width: 0;width: 100" class="headerColor">
			<fmt:message key="FS_0004" />
			<!-- 传 真 -->
		</td>
		<td style="border-bottom-width: 0;width: 100" class="headerColor">
			<fmt:message key="FS_0005" />
			<!-- 邮 箱 -->
		</td>
		<td style="border-bottom-width: 0;width: 100" class="headerColor">
			<fmt:message key="FS_0006" />
			<!-- 性 别 -->
		</td>
		<td style="border-bottom-width: 0;" class="headerColor">
			<fmt:message key="FS_0006" />
			<!-- 性 别 -->
		</td>
	</tr>
	<af:forEach var="loop1">
		<tr%{line.alternateColor} style="height: auto">
			<td class="headerColor">
				<span>%{line.index.2 + 1}</span>
			</td>
			<td align="center" style="loop1">
				<span>%{loop1_key1.value}</span>
			</td>
			<td align="center" style="loop1">
				<span>%{loop1_key2.value}</span>
			</td>
			<td align="center" style="loop1">
				<span>%{loop1_key3.value}</span>
			</td>
			<td align="center" style="loop1">
				<span>%{loop1_key4.value}</span>
			</td>
			<td align="center">
				<table width="100%" border="1" cellspacing="0" cellpadding="0"
					style="border-bottom-width: 0;" class="font14 tableStyle">
					<af:forEach items="${loop1}" var="loop2" loop="1">
						<tr%{line.alternateColor} style="height: auto">
							<td class="headerColor">
								<span>%{line.index.2 + 1}</span>
							</td>
							<td align="center" style="loop2">
								<span>%{loop2_key1.value}</span>
							</td>
							<td align="center" style="loop2">
								<span>%{loop2_key2.value}</span>
							</td>
							<td align="center">
								<table width="100%" border="1" cellspacing="0" cellpadding="0"
									style="border-bottom-width: 0;" class="font14 tableStyle">
									<af:forEach items="${loop2}" loop="2">
										<tr%{line.alternateColor}>
											<td align="center" style="loop3">
												<span>%{loop3_key1.value}</span>
											</td>
											<td align="center" style="loop3">
												<span>%{loop3_key2.value}</span>
											</td>
										</tr>
									</af:forEach>
								</table>
							</td>
						</tr>
					</af:forEach>
				</table>
			</td>
		</tr>
	</af:forEach>
</table>
