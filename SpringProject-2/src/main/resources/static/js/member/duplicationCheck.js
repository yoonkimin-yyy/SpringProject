function checkId(){
	const getId = $("#new-userid").val();
	
	$.ajax({
		type:"POST",
		url:"/member/checkId",
		data:{memberId:getId},
		success:function(res){
			
			if(res === "true"){
				$("#id-msg").css("color","red");
				$("#id-msg").text("사용 불가능한 아이디 입니다.");
			
			}
			else{
				$("#id-msg").css("color","green");
				$("#id-msg").text("사용 가능한 아이디입니다.");
				
			}
		},
		error:function(err){
			
		}
	})
}