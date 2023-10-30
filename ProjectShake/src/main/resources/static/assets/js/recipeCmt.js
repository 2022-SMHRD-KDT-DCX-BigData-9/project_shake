// 레시피 게시글의 댓글 정보 가져오는 JS파일
console.log($("#rcp_idx").val())
$(function() {
	// MyrecipeRestController로 이동
	$.ajax({
		url: "../recipeCmtList",
		type: "get",
		data: {
			"rcp_idx": $("#rcp_idx").val(),
		},
		success: (res) => {
			console.log(res);
			const commentDiv = $("#commentDiv");
			for (let i = 0; i < res.length; i++) {
				let comment = res[i];
				let content = `
					<div class="media-body">
						<h5 class="mt-0">${comment.user_nick}<small class="text-muted"> ${comment.created_at}</small></h5>
						<h5>ㄴ  ${comment.cmt_content}</h5>
					</div>
					<br>
				`;
				commentDiv.append(content);
			}
		},
		error: function(error) {
			console.log("서버 응답 실패 : " + error);
		}
	});
});

