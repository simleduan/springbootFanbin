
function formate_num(num) {
	var str = "";
	if (num < 10) {
		str = "0" + num;
	} else {
		str = num;
	}
	return str;
}

function transacte_number_to_date(num) {
	if (num == null) {
		return "";
	} else {
		var date = new Date(num);
		var str = date.getFullYear() + "-" + formate_num(date.getMonth() + 1)
				+ "-" + formate_num(date.getDate());
		return str;
	}
}

// 日期格式化
function birthday(value, row, index) {
	return transacte_number_to_date(formate_num(row.生日));
}

// 初始化表格
function initTable() {
	$('#table_id').bootstrapTable('destroy').bootstrapTable({
		height : $(window).height() - 200,
		dataType : "json",
		toolbar : '#toolbar',
		search : true,
		showRefresh : true,
		showToggle : true,
		showColumns : true,
		showExport : true,
//		detailView : true, //显示卡片
		detailFormatter : 'detailFormatter',
		mininumCountColumns : 2,
		showPaginationSwitch : true,
		pagination : true,
		idField : 'id',
		pageList : '[10,25,50,All]',
		showFooter : false,
		sidePagination : 'client',
		pageNumber : 1,
		columns : [ {
			field : '编号',
			title : '编号',
			align : 'center'
		}, {
			field : '姓名',
			title : '姓名',
			align : 'center'
		}, {
			field : '年龄',
			title : '年龄',
			align : 'center'
		}, {
			field : '生日',
			title : '生日',
			formatter : 'birthday',
			align : 'center'
		}, {
			field : '证件类型',
			title : '证件类型',
			align : 'center'
		}, {
			field : '证件号码',
			title : '证件号码',
			align : 'center'
		}, {
			field : '血型',
			title : '血型',
			align : 'center'
		}, {
			field : '兴趣爱好',
			title : '兴趣爱好',
			align : 'center'
		}, {
			field : '国籍',
			title : '国籍',
			align : 'center'
		}, {
			field : '微信',
			title : '微信',
			align : 'center'
		}, {
			field : '性别',
			title : '性别',
			align : 'center'
		}, {
			field : '技能专长',
			title : '技能专长',
			align : 'center'
		}, {
			field : '政治面貌',
			title : '政治面貌',
			align : 'center'
		}, {
			field : '紧急联系人',
			title : '紧急联系人',
			align : 'center'
		}, {
			field : '联系人关系',
			title : '联系人关系',
			align : 'center'
		}, {
			field : '邮箱',
			title : '邮箱',
			align : 'center'
		}, {
			title : '操作',
			formatter : 'actionFormatter',
			events : 'actionEvents',
			align : 'center'
		}]

	});
}

function actionFormatter(){
	return '<a class="option">查看</a>';
}

window.actionEvents={
	'click .option' : function(e,value,row,index){
		window.location.href="http://localhost:8080/study/web/login";
	}
}


$(function() {

	initTable();

	$.get('http://localhost:8080/study/web/query', null, function(msg) {
		// alert("我获取到信息了么");
		// alert(msg);
		$('#table_id').bootstrapTable('load', eval('(' + msg + ')'));
	})
});
