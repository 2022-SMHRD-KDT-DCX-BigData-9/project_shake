$("#commentDetail").on("click", comment)

function comment() {
	$.ajax({
		url: "../recipeCmtList",
		type: "get",
		data: {
			"rcp_idx": $("#rcp_idx").val(),
		},
		success: recipeCmtList,
		error: function(error) {
			console.log("서버 응답 실패 : " + error);
		}
	});
}

function recipeCmtList(res) {
	if (res == 0) {
		console.log(res);
		const commentDiv = $("#commentDiv");
		commentDiv.html("");
		for (let i = 0; i < res.length; i++) {
			let comment = res[i];
			let content = `
						<img class="d-flex mr-3 rounded-circle"
							src="http://placehold.it/50x50" alt="">
						<div class="media-body">
							<!--"작성자 이름  날짜" 및 내용 출력-->
							<h5 class="mt-0">${comment.user_nick}<small class="text-muted">${comment.created_at}</small>
							</h5>
							<p>${comment.cmt_content}</p>
						</div>
				`;
			like.append(content);
		}
	} else {
		console.log("서버오류")
	}

}