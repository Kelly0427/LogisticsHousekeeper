
$(function(){
	$("#saveForm").on('submit',function(){
		var formData = new FormData($("#saveForm" )[0]); 
		console.log(formData);
		$.ajax({
			type:"post",
			url:"/addInfo",
			data:formData,
			async:false,  
	        cache: false,  
	        contentType: false,  
	        processData: false,	   
			success:function(res){	
				window.location.href='/records';
			},
			error:function(res){
				window.location.href='/index';
			}
		});
		return false;
	});

});
