$(function() {
	let listForm = $('#listForm');
	$('.title').on('click', function(e){
		e.preventDefault();
		// let mnoData = "<input type='hidden' name='bno' value='"+$(this).data('bno')+"'/>";
		let mnoData = "<input type='hidden' name='mno' value='"+$(this).attr('href')+"'/>";
		listForm.append(mnoData)
				.attr('action', `${contextPath}/reserve/detail`)
				.submit();
	});
});