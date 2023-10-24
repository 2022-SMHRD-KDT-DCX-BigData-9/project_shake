const crt = document.getElementById('recipeChart');

$.ajax({
	url: "../recipeChart",
	type: "get",
	data: {
		"rcp_idx": $("#rcp_idx").val(),
	},
	success: makeChart,
	error: function(error) {
		console.log("서버 응답 실패 : " + error);
	}
});


function makeChart(res) {
	console.log(res)
	new Chart(crt, {
		type: 'radar',
		data: {
			labels: ['단맛', '신맛', '쓴맛', '짠맛', '매운맛', '떫은맛'],
			datasets: [{
				data: [res[0].sweet, res[0].sour, res[0].bitter, res[0].salty, res[0].spicy, res[0].puckery],
				backgroundColor: 'pink',
				borderColor: 'black',
				borderWidth: 1
			}]
		},
		options: {
			scale: {
				r: {
					ticks: {
						beginAtZero: true,
						max: 10
					}
				}
			}
		}
	});
};