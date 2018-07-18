function goToMemberDisplay(id, me){
	if(id == me) {
		location.href = "home.go";				
	} else {
		location.href = "follow.wave?wm_id="+id;		
	}
}

function checkRepleCnt(postNum) {
	$.ajax({
		url: "reple.count",
		data: {wr_rno: postNum},
		success: function(xml){
			$(".commentCnt").empty();
			var cnt = 0;
			$(xml).find("reple").each(function(e){
				cnt = e + 1;
			});
			$(".commentCnt").text(cnt);
		}
	});
}

function postDeleteDo(no) {
	var ok = confirm("�Խù��� �����մϱ�?");
	
	if(ok) {
		// �Խù� ����
		$.ajax({
			url: "post.delete",
			type:"post",
			data: {wp_no: no}, 
			success: function(result){
				if(result == "OK") {
					//$(".postATag"+ no).remove();
					location.href = "home.go";
				} else {
					alert("�Խù� ���� ����!!");
				}
			}
		});
	}
}

function repleDeleteDo(no, pNo) {
	var ok = confirm("����� �����մϱ�?");
	
	if(ok) {
		// ���� ����
		$.ajax({
			url: "reple.delete",
			type:"post",
			data: {wr_no: no}, 
			success: function(result){
				if(result == "OK") {
					//alert("����� ���� �Ǿ����ϴ�!");
					checkRepleCnt(pNo);
					$(".eachReples"+ no).remove();
				} else {
					alert("��� ���� ����!!");
				}
			}
		});
	}
	
}


function postCheck() { 
	var titleField = document.postForm.wp_title;
	var imgField = document.postForm.wp_img;
	
	if(isEmpty(titleField)) {
		alert("���� �ٽ�");
		titleField.value = "";
		titleField.focus();
		return false;
	} else if(isEmpty(imgField) || isNotType(imgField, "jpg")
				&& isNotType(imgField, "png")
				&& isNotType(imgField, "gif")
				&& isNotType(imgField, "jpeg")) {
		alert("�̹��� �ٽ�");
		imgField.value = "";
		imgField.focus();
		return false;
	} 
	
	return true;
}

// �ȷο� ���ο� ���� �Խù� ���
function followOrNot2(id, me){
	
	$.ajax({
		url: "follow.ornot",
		data: {wf_id: id, wf_follower: me},
		success: function(xml){
			var cnt = $(xml).find('follow').length;
			//alert(cnt);	
			if(cnt > 0) {
				$('#noPostDiv2').remove(); // �ش��±� ����
				$('#postTb').show(); // �Խù� ��� ǥ��
				$('#noPostDiv').show(); // �Խù� ���� ǥ��
			} else {
				$('#postTb').hide(); // �Խù� ��� �����
				$('#noPostDiv').hide(); // �Խù� ���� ǥ�� �����
				//nonOpenDiv.show();
				
				var nonOpenDiv = $('<div></div>').attr('id', 'noPostDiv2');
				var nonOpenImg = $('<img></img>').attr('src', 'resources/img/wonsta.jpg');
				var nonOpenSpan = $('<span></span>').text('�����!');
				nonOpenDiv.append(nonOpenImg, '<p>', nonOpenSpan);
				$('.mainCttTd').append(nonOpenDiv);
			}
		}		
	});	
}