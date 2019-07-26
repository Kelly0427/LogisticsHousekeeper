$("#login").click(function(){
		$.ajax({
			type:"GET",
			url:"/enter",
			data:{
				"name":$(".username").val(),
			},
			async:false,
			dataType:'json',
			success:function(res){
				if(res != "用户不存在，请重试！" && res != null){
					var value=$('.password').val();
					if(value==res){
						return res;
					}	
				}else{
					console.log(res)
				}
			},
			error:function(res){
				console.log(res);
			}
		});
})