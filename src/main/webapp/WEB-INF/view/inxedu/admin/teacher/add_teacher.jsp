<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>添加讲师</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/static/common/ztree/css/zTreeStyle.css" />
<script type="text/javascript" src="${ctx}/static/common/ztree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctximg}/static/admin/teacher/teacher.js"></script>


<link rel="stylesheet" href="${ctx}/static/common/nice-validator/jquery.validator.css"></link>
<script type="text/javascript" src="${ctx}/static/common/nice-validator/jquery.validator.js"></script>
<script type="text/javascript" src="${ctx}/static/common/nice-validator/local/zh-CN.js"></script>

<script type="text/javascript">
	subjectList='${subjectList}'
	</script>
</head>
<body>
<fieldset>
	<legend>
		<span>讲师管理</span>
		&gt;
		<span>添加讲师</span>
	</legend>
	<div class="">
		<form action="${ctx}/admin/teacher/add" method="post" id="saveTeacherForm" data-validator-option="{stopOnError:false, timely:false}">
			<input type="hidden" name="teacher.picPath" id="imagesUrl" />
			<table width="100%"  style="line-height: 35px;" >
				<tr>
					<td width="10%" align="center">
						<font color="red">*</font>讲师名称:
					</td>
					<td style="text-align: left;">
						<input name="teacher.name" type="text" style="width: 580px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<font color="red">*</font>讲师资历：
					</td>
					<td style="text-align: left;">
						<textarea name="teacher.education" style="width: 580px; height: 30px;" data-rule="required;"></textarea>
					</td>
				</tr>
				<tr>
					<td align="center">
						<font color="red">*</font>讲师专业：
					</td>
					<td style="text-align: left; position: relative;">
						<input type="hidden" name="teacher.subjectId" value="0" />
						<input readonly="readonly" id="subjectId" onclick="showSubjectList()" />
						<div id="ztreedemo" class="ztree" style="display: none; position: absolute; top: 30px; left: 0; background: #f8f8f8; width: 150px;"></div>
					</td>
				</tr>
				<tr>
					<td align="center">讲师等级：</td>
					<td style="text-align: left;">
						<select class="dropdown" name="teacher.isStar">
							<option value="1">高级讲师</option>
							<option value="2">首席讲师</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">讲师排序：</td>
					<td style="text-align: left;">
						<input name="teacher.sort" value="0" data-rule="required;integer[+0]"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<font color="red">*</font>讲师简介：
					</td>
					<td style="text-align: left;">
						<textarea style="width: 580px; height: 80px;" id="career" data-rule="required;" name="teacher.career"></textarea>
					</td>
				</tr>
				<tr>
					<td align="center">讲师头像：</td>
					<td style="text-align: left;">
						<img src="${ctx }/static/common/admin/images/default_head.jpg" alt="" id="subjcetpic" width="288px" height="288px" />
						<input type="button" value="上传" id="fileuploadButton" />
						<font color="red">(请上传 288*288(长X宽)像素 的图片)</font>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<a class="button tooltip" title="提 交" href="javascript:void(0)" onclick="teacherFormSubmit()">提 交</a>
						<a class="button tooltip" title="返 回" href="javascript:history.go(-1);">返 回</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</fieldset>
</body>
</html>
