

let replyService = {
	
	list : function(bno) {
		$.ajax({
			type : 'get',
			url : `${contextPath}/reply/list`,
			data : {bno : bno},
			success : function(replyList) {
				replyListRender(replyList);		
			},
			error : function() {
				alert('댓글 목록 조회 실패');
			}
		}); // ajax end
	},

	detail : function(replyVO) {
		console.log('댓글조회');
		$.ajax({
			type : 'get',
			url : `${contextPath}/reply/datail`,
			data : replyVO,
			success : function(detail) {
				alert(replyVO.rno);		
			},
			error : function() {
				alert('댓글 상세목록 조회 실패');
			}
		}); // ajax end
	},
	
	write : function(replyVO) {
		$.ajax({
			type : 'post',
			url : `${contextPath}/reply/write`,	
			data : replyVO,
			success : function(result) {
				$('.reply_content').val('');
				$('#feedback').find('modal-body').html(result);
				$('#feedback').modal('show');
				replyService.list(replyVO.bno);
			},
			error : function() {
				alert('댓글 등록 에러');
			}
			
		}); // ajax end
		
	},
	
	modify : function() {
		$.ajax({
			type : 'post',
			url : `${contextPath}/reply/modify`,
			data : replyVO,
			success : function(result){
				alert(result);
				replyService.list(replyVO.bno);
			},
			error : function() {
				alert('댓글 수정 에러');
			}
		})
	},
	
	remove : function(replyVO) {
		$.ajax({
			type : 'post',
			url : `${contextPath}/reply/remove`,
			data : replyVO,
			success : function(result){
				alert(result);
				replyService.list(replyVO.bno);
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
			
			if(r.writer==auth.id){
				output+= `
				<div class="align-self-content" data-rno="${r.rno}">
					<button class="btn btn-sm btn-info reply_modBtn">수정</button>
					<button class="btn btn-sm btn-danger reply_delBtn">삭제</button>				
				</div>
				`;
			}
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