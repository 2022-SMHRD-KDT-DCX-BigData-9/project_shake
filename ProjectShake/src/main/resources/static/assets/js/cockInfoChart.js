document.addEventListener("DOMContentLoaded", function() {
	const crc = document.getElementById('cockInfoChart');

	new Chart(crc, {
		type: 'radar',
		data: {
			labels: ['단맛', '짠맛', '쓴맛', '신맛', '매운맛', '떫은맛'],
			datasets: [{
				label: '칵테일 명',
				data: [10, 10, 4, 3, 2, 1],
				backgroundColor: 'black',
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
		},
	});
});