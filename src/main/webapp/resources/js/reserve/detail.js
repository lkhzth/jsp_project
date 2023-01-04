$(function() {
	// ====== 게시물 관련 ======
	
	$('.viewMode').hide();
	
	let viewForm = $('#viewForm');
	let titleObj = $('input[name="title"]');
	let contentObj = $('textarea[name="content"]');
	let pTag = $('.preview p').html; 
	
	let titleVal = titleObj.val();
	let contentVal = contentObj.val();
	
	// 수정모드
	$('.toModForm').on('click', function() {
		$('input[name="title"], textarea[name="content"]').attr("readonly", false);
		$('.viewMode').show();
		$(this).closest('tr').hide();
	});
	
	// 수정처리
	$('.modify').on('click', function() {
		viewForm.attr({
			'action' : `${contextPath}/reserve/modBoard`,
			'method' : 'post'
		}).submit();
	});
	
	// 삭제
	$('.remove').on('click', function() {
		viewForm.attr({
			'action' : `${contextPath}/reserve/removeBoard`,
			'method' : 'post' 
		}).submit();
	});
	
	// 뷰모드
	$('.backViewMode').on('click', function() {
		$('input[name="title"], textarea[name="content"]').attr("readonly", true);
		$('.viewMode').hide ();
		$(this).closest('tr').prev().show();
		titleObj.val(titleVal);
		contentObj.val(contentVal);
	});
	
	// 목록
	$('.toList').on('click', function() {
		viewForm.attr({
			'action' : `${contextPath}/reserve`,
			'method' : 'get'
		}).empty().submit();
	});
	
	
	// ====== 댓글 관련 ======
	
	let mno = $('input[name="mno"]').val();

	// 댓글 목록
	replyService.reserveList(mno);	

	// 댓글 쓰기	
	$('.reply_write').on('click', function() {
		let writer = $('.reply_writer').val();
		let reply = $('.reply_content').val();
	
		let ReserveReplyVO = {
			mno : mno,
			reply : reply,
			writer : writer
		}
		replyService.write(ReserveReplyVO);		
	});
	
	// 수정 버튼 이벤트
	$('.replyList').on('click', '.reply_modBtn', function(){
		let rno = $(this).closest('div').data('rno');
		let reply = $('.reply_content').val();
		let ReserveReplyVO = {
			mno : mno,
			rno : rno,
			reply : reply,
		}
		replyService.detail(ReserveReplyVO);
		alert('수정입니다' + rno + '번')
	});

	// 삭제 버튼 이벤트
	$('.replyList').on('click', '.reply_delBtn', function(){
		let rno = $(this).closest('div').data('rno');
		let ReserveReplyVO = {
			mno : mno,
			rno : rno
		}
		replyService.remove(ReserveReplyVO);
	});
});
