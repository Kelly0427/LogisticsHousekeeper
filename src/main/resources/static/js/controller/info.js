loadInfoList(1);
showMsg();
var nowNum; 
function loadInfoList(num){
	nowNum = num;
	$.ajax({
		type:"GET",
		url:"/loadInfoList",
		data:{
			'page':num-1,                 
            'size':5, 
            'status':$(".stt").val()
		},
		dataType:'json',
		success:function(body){
			console.log(body);
			var contents='<ul class="theUl">';
			$.each(body.content,function(index,con){
				contents+='<li>';
				contents+='<a class="cell cell_access" href="#">'+
				'<div class="hid" style="display:none">'+con.id+'</div>'+
                '<div class="media-box__hd media-box__hd_img">'+
                '<img class="media-box__thumb media-box__thumb_img" src="../images/icon_user1.png" alt="">'+
                '<p style="margin-top: -25px">'+con.fullName+'</p></div>'+
                '<div class="media-box__bd"><h4 class="media-box__title media-box__title_gg">'+con.description+'</h4>'+   
                '<div class="cell__hd"><img src="../images/icon_add.png" alt="" class="icon-add"><span class="media-box__desc_add">'+con.area+'</span>'+   
                '<button id="btn" class="media-box_btn btn_blue" style="outline:none">'+con.status+'</button></div>'+
                '<div class="cell__hd"><img src="../images/icon_time.png" alt="" class="icon-add" style="width: 16px;">'+
                '<span class="media-box__desc_add">'+con.recordTime+'</span></div></div></a>';    
				contents+='</li>';		
			});
			contents+='</ul>';
			$('.content').html(contents); 
			//分页
			$("#page").paging({
				pageNo: num,
				totalPage: body.totalPages,
				totalSize: body.totalElements,
				callback: function(num) {
					loadInfoList(num);
				}
			})
		},
		error:function(body){
			console.log(body);
		}
	});	 
}
$("#searchBtn").click(function(){
	loadInfoList(1);
});
var hiddenId=null;
$(document).on('click','#btn',function(){
	thisParent= $(this).parent().parent().parent();	
	hiddenId=thisParent.find('.hid').text();
    console.log(hiddenId);
    $(".boxPop2").show();  
});
$("#submBtn").click(function(){
	manage();
	$(".boxPop2").hide(); 
});
function manage(){
	$.ajax({
		type:"post",
		url:"/editInfo",
		data:{
			'id':hiddenId,                 
            'status':$("#repairCon").val(), 
		},
		dataType:'json',
		success:function(res){
			console.log(res);
		},
		error:function(res){
			console.log(res);
		}
	});
}
function showRole(){
	$.ajax({
		type:"GET",
		url:"/operatorRole",
		success:function(res){
			if(res!="学生"){
				$(".boxPop1").show(); 
			}
			else{
				alert("您目前没有此权限!");
			}
		},
		error:function(res){
			console.log(res);
			
		}
	})
}
function saveMsg(){
	$.ajax({
		type:"POST",
		url:"/addMsg",
		data:{
			"msg":$("#repairContent").val()
		},
		dataType:'json',
		success:function(res){
			console.log(res);
		},
		error:function(res){
			console.log(res);		
		}
	});
}
function showMsg(){
	$.ajax({
		type:"GET",
		url:"/searchMsg",
		success:function(res){
			$(".message").html(res.msg);
		},
		error:function(res){
			console.log(res);
			
		}
	})
}
$(".boxPop_close").click(function(){ 
 	$(".boxPop").hide(); 
}); 
$("#msg").click(function(){ 
	showRole();
}); 
$(".boxPop1_close").click(function(){ 
 	$(".boxPop1").hide(); 
}); 
$(".boxPop2_close").click(function(){ 
 	$(".boxPop2").hide(); 
}); 
$("#submitBx").click(function(){
	saveMsg();
	$(".boxPop1").hide(); 
	$(".boxPop").show(); 
	$("#repairContent").load(location.href+"#repairContent");
});
