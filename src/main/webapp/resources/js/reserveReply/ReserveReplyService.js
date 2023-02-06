

let replyService = {
	
	reserveList : function(mno) {
		console.log('댓글목록');
		$.ajax({
			type : 'get',
			url : `${contextPath}/reserveReply/list`,
			data : {mno : mno},
			success : function(replyList) {
				replyListRender(replyList);		
			},
			error : function() {
				alert('댓글 목록 조회 실패');
			}
		}); // ajax end
	},

	detail : function(ReserveReplyVO) {
		console.log('댓글조회');
		$.ajax({
			type : 'get',
			url : `${contextPath}/reserveReply/datail`,
			data : ReserveReplyVO,
			success : function(detail) {
				alert(ReserveReplyVO.rno);		
			},
			error : function() {
				alert('댓글 상세목록 조회 실패');
			}
		}); // ajax end
	},
	
	write : function(ReserveReplyVO) {
		$.ajax({
			type : 'post',
			url : `${contextPath}/reserveReply/write`,	
			data : ReserveReplyVO,
			success : function(result) {
				$('.reply_content').val('');
				$('#feedback').find('modal-body').html(result);
				$('#feedback').modal('show');
				replyService.reserveList(ReserveReplyVO.mno);
			},
			error : function() {
				alert('댓글 등록 에러');
			}
			
		}); // ajax end
		
	},
	
	modify : function() {
		$.ajax({
			type : 'post',
			url : `${contextPath}/reserveReply/modify`,
			data : ReserveReplyVO,
			success : function(result){
				alert(result);
				replyService.reserveList(ReserveReplyVO.mno);
			},
			error : function() {
				alert('댓글 수정 에러');
			}
		})
	},
	
	remove : function(ReserveReplyVO) {
		$.ajax({
			type : 'post',
			url : `${contextPath}/reserveReply/remove`,
			data : ReserveReplyVO,
			success : function(result){
				alert(result);
				replyService.reserveList(ReserveReplyVO.mno);
			},
			error : function() {
				alert('댓글 삭제 에러');
			}
			
		})
	}
}

// 댓글화면 랜더링
function replyListRender(replyList) {
	let output = '';
	for(let r of replyList) {
		output += 
			`<li class="list-group-item d-flex justify-content-between">
			<div>
				<p>${r.reply}</p>	
				<span class="badge badge-success">${r.writer}</span>
				<span class="badge badge-info">${r.replyDate}</span>
			</div>`
			
			if(auth.grade == 'ROLE_ADMIN'){
				output+= `
				<div class="align-self-content" data-rno="${r.rno}">
					<button class="btn btn-sm btn-danger reply_delBtn">삭제</button>				
				</div>
				`;
			}
	}
	output += `</li>`;
	$('.replyList').html(output);
	
	
	
}