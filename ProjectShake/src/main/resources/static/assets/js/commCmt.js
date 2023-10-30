// 커뮤니티의 게시글의 댓글목록을 가져오는 JS파일

$(function() {
	// CommunityRestController로 게시글 idx 값 보내기.
	$.ajax({
		url: "../commCmtList",
		type: "get",
		data: {
			"comm_idx": $("#comm_idx").val(),
		},
		success: (res) => {
			console.log(res);
			const commentDiv = $("#commentDiv");
			commentDiv.html("");
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
