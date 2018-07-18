function waiterAccept(me, you) {
	//alert(me);
	// �ȷο��ϱ�
	$.ajax({
		url: "insert.follower",
		type:"post",
		data: {wf_id: me, wf_follower: you},
		success: function(result){
			if(result == "OK") {
				followerCnt(me); // �ȷο� �� ����
			}
		}
	});
	// waiter ��� �����
	$.ajax({
		url: "delete.waiter",
		type:"post",
		data: {wfw_id: me, wfw_follower: you},
		success: function(result){
			if(result == "OK") {
				$("#waiterTb_"+you).remove(); // ���� ����� waiter ����
			}
		}
	});
}

function waiterAcceptCancel(me, you) {
	//alert(me);
	// waiter ��� �����
	$.ajax({
		url: "delete.waiter",
		type:"post",
		data: {wfw_id: me, wfw_follower: you},
		success: function(result){
			if(result == "OK") {
				$("#waiterTb_"+you).remove(); // ���� ����� waiter ����
			}
		}
	});
}

function waitersForFollow(me) {
	// follower ��ȸ
	$("#alarmWaiter").click(function() {
		$.ajax({
			url: "count.waitforme",
			data: {wfw_id: me},
			success: function(xml){
				var cnt = $(xml).find("waiter").length;
				
				$('#searchWaiterDiv').remove();
				$('#waiterDiv').remove();
				$('#closeDiv').remove();
				
				var displayDiv = $('<div></div>');
				var waiterDiv = $('<div></div>');
				var closeDiv = $('<div></div>').attr("id","closeDiv");
	
				if (cnt >= 1) {
					displayDiv.attr("id","searchWaiterDiv");
					waiterDiv.attr("id","waiterDiv");
					
					var idSpan;
					var nameSpan;
					var imgSpan;
					var okBnt;
					var delBnt;
					
					$(xml).find("waiter").each(function(i, e){
						var followerId = $(e).find('wfw_follower').text();
						var followerImg = "";
						var followerName = "";
						
						// id�� ȸ�������� �������� ���۽�
						$.ajax({
							url : "member.id.check",
							data : {
								wm_id : followerId
							},
							success : function(xml2) {
								var fm = $(xml2).find('member');
								followerImg = fm.find('wm_img').text();
								followerName = fm.find('wm_name').text();									
									
								//var aTag = $('<a></a>').attr('class', 'dvsATag');
								
								//aTag.attr('href','follow.wave?wm_id='+followerId);
								
								var waiterTb = $('<table></table>').attr("class", "waiterTb").attr("id", "waiterTb_"+followerId);
								
								waiterTb.css("border-bottom", "1px #E0E0E0 solid");
					
								var tr = $('<tr></tr>');
								var td1 = $('<td></td>').css('width', '45px');
								var td2 = $('<td></td>');
								var td3 = $('<td></td>').attr('align', 'right');
									
								idImg = $('<img></img>').attr('class', 'searchIdImg').attr('src', 'resources/img/' + followerImg);
								td1.append(idImg);
									
								idSpan = $('<span></span>').attr("class", "searchIdSpan").text(followerId);
								nameSpan = $('<span></span>').attr("class", "searchIdNameSpan").text(followerName);
								td2.append(idSpan, '<br>', nameSpan);
								
								okBnt = $('<button></button>').attr("class", "followOkBnt").attr("onclick", "waiterAccept(\'" + me + "\',\'" + followerId + "\');").text("Ȯ��");
								delBnt = $('<button></button>').attr("class", "followDelBnt").attr("onclick", "waiterAcceptCancel(\'" + me + "\',\'" + followerId + "\');").text("���");
								td3.append(okBnt, delBnt);
								
								tr.append(td1, td2, td3);
								waiterTb.append(tr);
								//aTag.append(waiterTb)
									
								waiterDiv.append(waiterTb);
							}
						});
							
						displayDiv.append(waiterDiv)
					});
					
				} else {
					displayDiv.attr("id","searchWaiterDiv");
					
					var noIdDiv = $("<div></div>").attr('class','noWaiterDiv').text("�ƹ��� �����ϴ�.");
					
					displayDiv.append(noIdDiv);
				}
				closeDiv.text('x');
				displayDiv.append(closeDiv);
				
				$('#loBd').append(displayDiv);
				
				// �ݱ� ��ư ���� ��
				$('#closeDiv').click(function(){
					//$('#closeDiv').remove();
					//$('#followDiv').remove();
					$('#searchWaiterDiv').hide();
					countWaitForMe(me); // �ȷο� ��û�� ��� ������ ��Ÿ���� ��Ƽ ���� ����
				});
			}
		});
		
	});
}

function countWaitForMe(me) {
	$.ajax({
		url: "count.waitforme",
		data: {wfw_id: me},
		success: function(xml){
			var cnt = $(xml).find("waiter").length;
			
			if(cnt > 0) {
				$("#alarmWaiter").css("visibility", "visible");
			} else {
				$("#alarmWaiter").css("visibility", "hidden");
			} 
		}
	});
}


function followerCnt(yId){
	$.ajax({
		url: "follower.cnt",
		data: {wf_id: yId},
		success: function(xml){
			var cnt = $(xml).find("follow").length;
			$('#followerCntSpan').text(cnt);
		}
	});
}

function followCnt(yId){
	$.ajax({
		url: "follow.cnt",
		data: {wf_follower: yId},
		success: function(xml){
			var cnt = $(xml).find("follow").length;
			$('#followCntSpan').text(cnt);
		}
	});
}

function followDo(id, me) {
var ok = confirm(id +"���� �ȷο� �մϱ�?");
	
	if(ok) {
		$.ajax({
			url: "insert.follower",
			type:"post",
			data: {wf_id: id, wf_follower: me},
			success: function(result){
				if(result == "OK") {
					alert(id + "���� �ȷο� �߽��ϴ�!!!");
					$("#followBnt").attr("id", "").attr("id", "followingBnt").text("�ȷ���");
					$("#followingBnt").text("�ȷ���");
					followerCnt(id); // �ȷο� �� ����
				}
			}
		});
	}
}

function unfollowDo(id, me) {
	var ok = confirm(id +"���� ���ȷο� �մϱ�?");
	
	if(ok) {
		//href.location = "insert.follower";
		$.ajax({
			url: "delete.follower",
			type:"post",
			data: {wf_id: id, wf_follower: me},
			success: function(result){
				if(result == "OK") {
					alert(id + "���� ���ȷο� �߽��ϴ�!!!");
					$("#followBnt").text("�ȷο�");
					$("#followingBnt").attr("id", "").attr("id", "followBnt").text("�ȷο�");
					followerCnt(id); // �ȷο� �� ����
				}
			}
		});
	}
}

// �ȷο� �ߴ��� ����
function followOrNot(id, me){
	$.ajax({
		url: "follow.ornot",
		data: {wf_id: id, wf_follower: me},
		success: function(xml){
			var cnt = $(xml).find("follow").length;
			if(cnt > 0) {
				$("#followBnt").attr("id", "").attr("id", "followingBnt").text("�ȷ���");
			}
		}
	});
}

function followWait(id, me) {
	var ok = confirm(id +"���� �ȷο� �մϱ�?");
	
	if(ok) {
		$.ajax({
			url: "insert.waiter",
			type:"post",
			data: {wfw_id: id, wfw_follower: me},
			success: function(result){
				if(result == "OK") {
					alert(id + "���� ������ ������ ��ٸ�����...");
					$("#followBnt").attr("id", "").attr("id", "followWaitBnt").text("������");
					$("#followingBnt").attr("id", "").attr("id", "followWaitBnt").text("������");
				}
			}
		});
	}
}

function unFollowWaitDo(id, me) {
	var ok = confirm("���� ��� �մϱ�?");
	
	if(ok) {
		$.ajax({
			url: "delete.waiter",
			type:"post",
			data: {wfw_id: id, wfw_follower: me},
			success: function(result){
				if(result == "OK") {
					alert("���� ��� �߽��ϴ�!!");
					$("#followWaitBnt").attr("id", "").attr("id", "followBnt").text("�ȷο�");
					$("#followBnt").text("�ȷο�");
				}
			}
		});
	}
}

//���������� ����
function followWaitOrNot(id, me){
	$.ajax({
		url: "followWait.ornot",
		data: {wfw_id: id, wfw_follower: me},
		success: function(xml){
			var cnt = $(xml).find("waiter").length;
			if(cnt > 0) {
				$("#followBnt").attr("id", "").attr("id", "followWaitBnt").text("������");
			}
			
			return cnt;
		}
	});
}


// �ȷο� �� ������ �� ���
function searchFollowerEvent(yId, meId) {
	
	// follower ��ȸ
	$("#followerTd").click(function() {
		$.ajax({
			url : "follower.cnt",
			data : {
				wf_id : yId
			},
			success : function(xml) {
				var ok = $(xml).find("follow").length;
				
				$('#searchFollowerDiv').remove();
				$('#followDiv').remove();
				$('#closeDiv').remove();
				var displayDiv = $('<div></div>');
				var followDiv = $('<div></div>');
				var closeDiv = $('<div></div>').attr("id","closeDiv");
	
				if (ok >= 1) {
					displayDiv.attr("id","searchFollowerDiv");
					followDiv.attr("id","followDiv");
					
					var idSpan;
					var nameSpan;
					var imgSpan;
					
					$(xml).find("follow").each(function(i, e){
						var followerId = $(e).find('wf_follower').text();
						var followerImg = "";
						var followerName = "";
						
						// id�� ȸ�������� �������� ���۽�
						$.ajax({
							url : "member.id.check",
							data : {
								wm_id : followerId
							},
							success : function(xml2) {
								var fm = $(xml2).find('member');
								followerImg = fm.find('wm_img').text();
								followerName = fm.find('wm_name').text();									
									
								var aTag = $('<a></a>').attr('class', 'dvsATag');
								if(followerId == meId) {
									aTag.attr('href','home.go');
								} else {
									aTag.attr('href','follow.wave?wm_id='+followerId);
								}
								
								var followTb = $('<table></table>').attr("class", "followTb");
								
								followTb.css("border-bottom", "1px #E0E0E0 solid");
					
								var tr = $('<tr></tr>');
								var td1 = $('<td></td>').css('width', '45px');
								var td2 = $('<td></td>');				
									
								idImg = $('<img></img>').attr('class', 'searchIdImg').attr('src', 'resources/img/' + followerImg);
								td1.append(idImg);
									
								idSpan = $('<span></span>').attr("class", "searchIdSpan").text(followerId);
								nameSpan = $('<span></span>').attr("class", "searchIdNameSpan").text(followerName);
								td2.append(idSpan, '<br>', nameSpan);
									
								tr.append(td1, td2);
								followTb.append(tr);
								aTag.append(followTb)
									
								followDiv.append(aTag);
							}
						});
							
						displayDiv.append(followDiv)
					});
					
				} else {
					displayDiv.attr("id","searchFollowerDiv");
					
					var noIdDiv = $("<div></div>").attr('class','noFollowDiv').text("�ƹ��� �����ϴ�.");
					
					displayDiv.append(noIdDiv);
				}
				closeDiv.text('x');
				displayDiv.append(closeDiv);
				
				$('#loBd').append(displayDiv);
				
				// �ݱ� ��ư ���� ��
				$('#closeDiv').click(function(){
					//$('#closeDiv').remove();
					//$('#followDiv').remove();
					$('#searchFollowerDiv').hide();
				});
			}
		});
		
	});
}

//�ȷο� �� ������ �� ���
function searchFollowEvent(yId, meId) {
	
	// follower ��ȸ
	$("#followTd").click(function() {
		$.ajax({
			url : "follow.cnt",
			data : {
				wf_follower : yId
			},
			success : function(xml) {
				var ok = $(xml).find("follow").length;
				
				$('#searchFollowerDiv').remove();
				$('#followDiv').remove();
				$('#closeDiv').remove();
				var displayDiv = $('<div></div>');
				var followDiv = $('<div></div>');
				var closeDiv = $('<div></div>').attr("id","closeDiv");
	
				if (ok >= 1) {
					displayDiv.attr("id","searchFollowerDiv");
					followDiv.attr("id","followDiv");
					
					var idSpan;
					var nameSpan;
					var imgSpan;
					
					$(xml).find("follow").each(function(i, e){
						var followId = $(e).find('wf_id').text();
						var followImg = "";
						var followName = "";
						
						// id�� ȸ�������� �������� ���۽�
						$.ajax({
							url : "member.id.check",
							data : {
								wm_id : followId
							},
							success : function(xml2) {
								var fm = $(xml2).find('member');
								followImg = fm.find('wm_img').text();
								followName = fm.find('wm_name').text();									
									
								var aTag = $('<a></a>').attr('class', 'dvsATag');
								if(followId == meId) {
									aTag.attr('href','home.go');
								} else {
									aTag.attr('href','follow.wave?wm_id='+followId);
								}
								var followTb = $('<table></table>').attr("class", "followTb");
								
								followTb.css("border-bottom", "1px #E0E0E0 solid");
					
								var tr = $('<tr></tr>');
								var td1 = $('<td></td>').css('width', '45px');
								var td2 = $('<td></td>');				
									
								idImg = $('<img></img>').attr('class', 'searchIdImg').attr('src', 'resources/img/' + followImg);
								td1.append(idImg);
									
								idSpan = $('<span></span>').attr("class", "searchIdSpan").text(followId);
								nameSpan = $('<span></span>').attr("class", "searchIdNameSpan").text(followName);
								td2.append(idSpan, '<br>', nameSpan);
									
								tr.append(td1, td2);
								followTb.append(tr);
								aTag.append(followTb)
									
								followDiv.append(aTag);
							}
						});
							
						displayDiv.append(followDiv)
					});
					
				} else {
					displayDiv.attr("id","searchFollowerDiv");
					
					var noIdDiv = $("<div></div>").attr('class','noFollowDiv').text("�ƹ��� �����ϴ�.");
					
					displayDiv.append(noIdDiv);
				}
				closeDiv.text('x');
				displayDiv.append(closeDiv);
				
				$('#loBd').append(displayDiv);
				
				// �ݱ� ��ư ���� ��
				$('#closeDiv').click(function(){
					//$('#closeDiv').remove();
					//$('#followDiv').remove();
					$('#searchFollowerDiv').hide();
				});
			}
		});
		
	});
}
