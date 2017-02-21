$(function() {

	//添加的js
	$('#addButton').unbind('click').bind('click',function(){
		console.log("我要添加人员了");
		var addInfo = $('#addForm').serialize();
//		alert("我要弹出数据了");
//		alert(JSON.stringify(addInfo));
		console.log(addInfo);
		$.ajax({
			type:"post",
			url:"http://localhost:8080/addPerson",
			data:addInfo,
			async:false,
			success:function(msg){
				
				alert(msg);
				window.location.href="person";
			},
			error:function(data){
				alert("添加失败"+data)
			}
		})
	})
	
	//添加的js
	$('#delButton').unbind('click').bind('click',function(){
//		alert("我要弹出数据了");
//		alert(JSON.stringify(addInfo));
		console.log(addInfo);
		$.ajax({
			type:"post",
			url:"http://localhost:8080/delPerson",
			data:addInfo,
			async:false,
			success:function(msg){
				alert('删除成功');
			},
			error:function(data){
				alert("删除失败"+data)
			}
		})
	})
	
});




