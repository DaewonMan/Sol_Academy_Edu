function addPostGo() {
	var ok = confirm("�Խù� �߰� �Ͻðڽ��ϱ�?");
	if (ok) {
		location.href = "post.go";
	}
}
function deleteDo() {
	var ok = confirm("ȸ��Ż�� �Ͻðڽ��ϱ�?");
	if (ok) {
		location.href = "delete.member.go";
	}
}
function updateDo() {
	var ok = confirm("�������� �����Ͻðڽ��ϱ�?");
	if (ok) {
		location.href = "update.member.do";
	}
}
function logoutDo() {
	var ok = confirm("�α׾ƿ��Ͻðڽ��ϱ�?");
	if (ok) {
		location.href = "logout.do";
	}
}

function searchIdEvent(loginId) {
	var loginId2 = loginId;
	
	// id ��ȸ
	$("#searchInfo").keyup(function(e) {

		var id = $("#searchInfo").val();

		$.ajax({
			url : "member.id.search",
			data : {
				wm_id : id
			},
			success : function(xml) {
				var ok = $(xml).find("member").length;
				
				$('#searchIdDiv').remove();
				$('#triDiv').remove();
				var displayDiv = $('<div></div>');
				var triDiv = $('<div></div>');
				
				if (id == "") {
					// id�� �Ƚ��� ��
				} else if (ok >= 1) {
					displayDiv.attr("id","searchIdDiv");
					triDiv.attr("id","triDiv");
					
					var idSpan;
					var idNameSpan;
					var idImg;
					$(xml).find("member").each(function(i, e){
						var loginId1 = $(e).find('wm_id').text();
		
						// �α���id�� �˻� ��Ͽ� �ȶ߰� �Ѵ�
						if(loginId1 == loginId2){
							if(ok == 1){
								var noIdDiv = $("<div></div>").attr('class','noIdDiv').text("�˻� ����� �����ϴ�.");
								
								displayDiv.append(noIdDiv);
							}
							return true; // continue�� ����
						}
							
						var aTag = $('<a></a>').attr('href','follow.wave?wm_id='+loginId1).attr('class', 'dvsATag');
						var divisionTb = $('<table></table>').attr("class", "dvsTb");
						if(i+1 < ok) {
							divisionTb.css("border-bottom", "1px #E0E0E0 solid");
						}
						
						var tr = $('<tr></tr>');
						var td1 = $('<td></td>').css('width', '45px');
						var td2 = $('<td></td>');
						
						idImg = $('<img></img>').attr('class', 'searchIdImg').attr('src', 'resources/img/' + $(e).find("wm_img").text());
						td1.append(idImg);
						
						idSpan = $('<span></span>').attr("class", "searchIdSpan").text(loginId1);
						idNameSpan = $('<span></span>').attr("class", "searchIdNameSpan").text($(e).find("wm_name").text());
						td2.append(idSpan, '<br>', idNameSpan);
						
						tr.append(td1, td2);
						divisionTb.append(tr);
						aTag.append(divisionTb)
						
						displayDiv.append(aTag);
					
					});
					
				} else if (ok == 0) {
					displayDiv.attr("id","searchIdDiv");
					triDiv.attr("id","triDiv");
					
					var noIdDiv = $("<div></div>").attr('class','noIdDiv').text("�˻� ����� �����ϴ�.");
					
					displayDiv.append(noIdDiv);
				}
				
				$('#loBd').append(triDiv, displayDiv);
			}
		});
	});
}

function connectIdCheckEvent() {
	// id �ߺ��˻�
	$("#joinID").keyup(function(e) {

		var id = $("#joinID").val();

		$.ajax({
			url : "member.id.check",
			data : {
				wm_id : id
			},
			success : function(xml) {
				var ok = $(xml).find("member").length;

				if (id == "") {
					// id�� �Ƚ��� ��
					$("#joinIDResult").text("ID �Է�");
				} else if (ok == 1) {
					$("#joinIDResult").text("ID �ߺ�");
				} else if (ok == 0) {
					$("#joinIDResult").text("OK");
				}
			}
		});
	});
}

function joinCheck() {
	var idField = document.joinForm.wm_id;
	var pwField = document.joinForm.wm_pw;
	var pwChkField = document.joinForm.wm_pwChk;
	var nameField = document.joinForm.wm_name;
	var add1Field = document.joinForm.wm_add1;
	var add2Field = document.joinForm.wm_add2;
	var add3Field = document.joinForm.wm_add3;

	var imgField = document.joinForm.wm_img;
	
	if ($("#joinIDResult").text() == "ID �ߺ�" || isEmpty(idField)
			|| containsHangul(idField)) {
		alert("ID �ٽ�");
		idField.value = "";
		idField.focus();
		return false;
	} else if (isEmpty(pwField) || notContains(pwField, "1234567890")
			|| notContains(pwField, "qwertyuiopasdfghjklzxcvbnm")
			|| notContains(pwField, "QWERTYUIOPASDFGHJKLZXCVBNM")) {
		alert("PW �ٽ�");
		pwField.value = "";
		pwField.focus();
		return false;
	} else if (notEquals(pwChkField, pwField) || isEmpty(pwChkField)) {
		alert("��й�ȣ �ٸ�");
		pwChkField.value = "";
		pwField.value = "";
		pwChkField.focus();
		return false;
	} else if (isEmpty(nameField)) {
		alert("�̸� �ٽ�");
		nameField.value = "";
		nameField.focus();
		return false;
	} else if (isEmpty(add1Field) || isEmpty(add2Field) || isEmpty(add3Field)) {
		alert("�ּ� �ٽ�");
		add1Field.value = "";
		add2Field.value = "";
		add3Field.value = "";
		add1Field.focus();
		return false;
	} else if (isEmpty(imgField) || isNotType(imgField, "jpg")
			&& isNotType(imgField, "png") && isNotType(imgField, "gif")
			&& isNotType(imgField, "jpeg")) {
		alert("�̹��� �ٽ�");
		imgField.value = "";
		imgField.focus();
		return false;
	} else {
		alert(idField.value + "�� ȯ���մϴ�^^!!");		
	}
	
	return true;
}
function updateCheck() {
	var pwField = document.joinForm.wm_pw;
	var pwChkField = document.joinForm.wm_pwChk;
	var nameField = document.joinForm.wm_name;
	var add1Field = document.joinForm.wm_add1;
	var add2Field = document.joinForm.wm_add2;
	var add3Field = document.joinForm.wm_add3;

	var imgField = document.joinForm.wm_img;

	if (isEmpty(pwField) || notContains(pwField, "1234567890")
			|| notContains(pwField, "qwertyuiopasdfghjklzxcvbnm")
			|| notContains(pwField, "QWERTYUIOPASDFGHJKLZXCVBNM")) {
		alert("PW �ٽ�");
		pwField.value = "";
		pwField.focus();
		return false;
	} else if (notEquals(pwChkField, pwField) || isEmpty(pwChkField)) {
		alert("��й�ȣ �ٸ�");
		pwField.value = "";
		pwChkField.value = "";
		pwField.focus();
		return false;
	} else if (isEmpty(nameField)) {
		alert("�̸� �ٽ�");
		nameField.value = "";
		nameField.focus();
		return false;
	} else if (isEmpty(add1Field) || isEmpty(add2Field) || isEmpty(add2Field)) {
		alert("�ּ� �ٽ�");
		add1Field.value = "";
		add2Field.value = "";
		add3Field.value = "";
		add1Field.focus();
		return false;
	} else if (isEmpty(imgField)) {
		return true;
	} else if (isNotType(imgField, "jpg") && isNotType(imgField, "png")
			&& isNotType(imgField, "gif") && isNotType(imgField, "jpeg")) {
		alert("�̹��� �ٽ�");
		imgField.value = "";
		imgField.focus();
		return false;
	}

	return true;
}

function loginCheck() {
	var idField = document.loginForm.wm_id;
	var pwField = document.loginForm.wm_pw;

	if (isEmpty(idField)) {
		alert("���̵� �ٽ�");
		idField.value = "";
		idField.focus();
		return false;
	} else if (isEmpty(pwField) || notContains(pwField, "1234567890")
			|| notContains(pwField, "qwertyuiopasdfghjklzxcvbnm")
			|| notContains(pwField, "QWERTYUIOPASDFGHJKLZXCVBNM")) {
		alert("��� �ٽ�");
		pwField.value = "";
		pwField.focus();
		return false;
	}
	
	return true;
}