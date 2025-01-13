function listAll(){
	
	$.ajax({
		
		type:"GET",
		url:"/api/books",
		contentType : "application/json, charset=UTF-8",
		success: function(res){
			console.log(res);
		},
		error: function(err){
			
		}
	})
}

function selectBook() {
   const selectBook = $("#select-book").val();
   
   $.ajax({
      type: "GET",
      url: "/api/books/" + selectBook,
      contentType: "application/json, charset=UTF-8",
      success: function(res) {
         console.log(res);
      },
      error: function(err) {
         
      }
      
   })
   
}