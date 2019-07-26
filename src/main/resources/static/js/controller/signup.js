var flag=true;
function checkPwd(){
	if($(".password").val()!=$(".pwd").val()){
		flag=false;
		alert("两次密码不一致，请重新输入！");
	}
	return flag;
}
$("#saveForm").on('submit',function(){
		checkPwd();
		var formData = new FormData($("#saveForm" )[0]); 
		console.log(formData);
		var name=$(".username").val();
		var passWord=$(".password").val();
		var classCode=$(".classCode").val();
		var phone=$(".phone").val();
		var realName=$(".realName").val();
		var role=$(".role").val();
		if(name!=null && passWord!=null && classCode!=null && phone!=null
				&& realName!=null && role!=null && flag==true){
			$.ajax({
				type:"POST",
				url:"/system/addOperator",
				data:formData,
				async:false,  
		        cache: false,  
		        contentType: false,  
		        processData: false,	   
				success:function(res){	
					window.location.href='/login';
				},
				error:function(res){
					window.location.href='/signup';
				}
			});
			return false;
		}
	});
	
