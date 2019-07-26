loadInfoList(1);
var nowNum; 
function loadInfoList(num){
	nowNum = num;
	$.ajax({
		type:"GET",
		url:"/searchInfo",
		data:{
			'page':num-1,                 
            'size':10, 
		},
		dataType:'json',
		success:function(body){
			console.log(body);
			var contents='';
			$.each(body.content,function(index,con){
				contents+='<tr><th scope="row">'+index+'</th><td class="hid" style="display:none;">'+con.id+'</td><td>'+con.fullName+'</td><td><img src="'+con.imgUrl+'" /></td>'+
				          '<td>'+con.description+'</td><td>'+con.address+'</td><td>'+con.status+'</td><td class="sta">'+
				          (con.status=='已完成'?'<a class="cls" href="#">'+"评价"+'</a>':'待评价')
				          +'</td></tr>';
			});	
			$('.tbody1').html(contents); 
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
var thisParent=null;
var hiddenId=null;
$(document).on('click','.cls',function(){
	$(".boxPop1").show(); 
    thisParent= $(this).parent().parent();	
    hiddenId=thisParent.find('.hid').text();
});
$("#submitBx").click(function(){
	saveData();
	$(".boxPop1").hide(); 
	$(".sta").text("已评价");
});
$(".boxPop1_close").click(function(){ 
	$(".boxPop1").hide(); 
});
function saveData(){
	console.log(hiddenId);
	var evaluation=$("#evaluation").val();
	console.log(evaluation);
	var aOptions=$(".pingStar-a option:selected").val();
	console.log(aOptions);
	var sOptions=$(".pingStar-s option:selected").val();
	console.log(sOptions);
	var qOptions=$(".pingStar-q option:selected").val();
	console.log(qOptions);
	var totle=Number(aOptions)+Number(sOptions)+Number(qOptions);
	console.log(totle);
	$.ajax({
		type:"POST",
		url:"/addFeedback",
		data:{
			'idf':hiddenId,                 
            'evaluation':evaluation, 
            'attitude':aOptions,
            'speed':sOptions,	
            'quality':qOptions,
            'totle':totle
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