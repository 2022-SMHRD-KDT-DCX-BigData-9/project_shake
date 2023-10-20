$(function () {
	$.ajax({
		url : "count",
		type : "get",
		data : {
			// 속성값을 이용해서 태그 선택.
			"comm_idx" : $("input[type=hidden]").val()
		},
		success : function(res){
			if (res > 0){
				console.log("조회수 올리기 성공");
			}
		},
		error : function(e){
			if (e > 0){
				console.log("실패");
			}
		}
	})
	
});