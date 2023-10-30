// 세션의 아이디와 board의 idx가져오기.
// 정보를 보내고 확인하기.
$(function() {
	$.ajax({
		url: "../checkLike",
		type: "get",
		data: {
			"rcp_idx": $("#rcp_idx").val(),
			"user_id": $("#rcp_user_id").val(),
			"rcp_likes": 0
		},
		success: (res) => {
			const like = $("#likeBtn");
			like.html("");
			if (res == 0) {
				let content = `
					<div id = "likeBtn">
						<input type="hidden" name = "rcp_likes" value = "0">
						<button type ="submit" class="btn btn-info">좋아요</button>
					</div>				`;
				like.append(content);
			} else {
				let content = `
					<div id = "likeBtn">
						<input type="hidden" name = "rcp_likes" value = "1">
						<button type ="submit" class="btn btn-info">좋아요 취소</button>
					</div>
				`;
				like.append(content);
			}
		},
		error: function(error) {
			console.log("서버 응답 실패 : " + error);
		}
	});
});