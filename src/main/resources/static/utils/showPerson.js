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
		height : $(window).height() - 50,
		dataType : "json",
		toolbar : '#toolbar',
		search : true,
		showRefresh : true,
		showToggle : true,
		showColumns : true,
		showExport : true,
		// detailView : true, //显示卡片
		detailFormatter : 'detailFormatter',
		mininumCountColumns : 2,
		showPaginationSwitch : true,
		pagination : true,
		idField : 'id',
		pageList : '[5,10,25,50,All]',
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
			title : '操作',
			formatter : 'actionFormatter',
			events : 'actionEvents',
			align : 'center'
		} ]

	});
}

function actionFormatter() {
	return '<a class="option">查看</a>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <a class="delete">删除</a>';
}

window.actionEvents = {
	'click .option' : function(e, value, row, index) {
		// alert(row.编号);
		var id = row.编号;
		window.location.href = "personShow?id=" + id;
	},
	'click .delete' : function(e, value, row, index) {
//		alert("你确定要删除么？" + row.编号)
		var id = row.编号
		if(confirm("您是否确认删除该条信息？")){			
			$.ajax({
				type : "post",
				url : "delete",
				data : "id=" + id,
				async : false,
				success : function(msg) {
					alert('删除成功');
					window.location.href = "person";
				},
				error : function(data) {
					alert("删除失败" + data)
				}
			})
		}
	}
}

$(function() {

	initTable();

	$.get('http://localhost:8080/study/web/queryPerson', null, function(msg) {
		// alert("我获取到信息了么");
		// alert(msg);
		console.log("msg==>" + msg)
		$('#table_id').bootstrapTable('load', eval('(' + msg + ')'));
	});

	$('#personAdd').unbind('click').bind('click', function() {
		// alert("我要增加人员了");
		window.location.href = "personAdd";
	})

});

/*
 * 
 * 江涛写的jS $(function() {
 *  // initTable();
 * 
 * $.get('http://localhost:8080/study/web/queryPerson', null, function(msg) { //
 * alert("我获取到信息了么"); // alert(msg); console.log("msg==>"+msg) //
 * $('#table_id').bootstrapTable('load', eval('(' + msg + ')')); var
 * jstr=eval('('+msg+')'); var ts=[]; ts[ts.length]='<thead><tr><td>编号</td><td>姓名</td><td>年龄</td><td>操作</td></tr></thead><tbody>';
 * $.each(jstr,function(){ ts[ts.length]='<tr><td>'+this.编号+'</td><td>'+this.姓名+'</td><td>'+this.年龄+'</td><td><a
 * class="option">查看</td></tr>'; }); ts[ts.length]='</tbody>';
 * $('#table_id').html(ts.join("")); }); });
 */