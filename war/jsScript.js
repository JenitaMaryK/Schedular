$(document).ready(function() {
	$("#mydate").datepicker().datepicker("setDate", new Date());

	$('#addBtn').click(function() {
		var toAdd = $('input[name=task]').val();
		ajax("/DataStoreCreation", toAdd, "addBtn");
		$('#todo').append('<li>' + toAdd + '</li>');
	});
	$('input').focus(function() {
		$(this).val('');
	});
	$('#delBtn').click(function() {
		$('#todo .selected').remove();
	});
	$('#moveLeft').click(function() {
		$('#doing').append($('#todo .selected').removeClass('selected'));
	});
	$('#moveDone').click(function() {
		$('#done').append($('#todo .selected').removeClass('selected'));
	});

	$('#addBtn1').click(function() {
		var toAdd1 = $('input[name=doing]').val();
		ajax("/DataStoreCreation", toAdd1, "addBtn1");
		$('#doing').append('<li>' + toAdd1 + '</li>');
	});
	$('input').focus(function() {
		$(this).val('');
	});
	$(document).on('click', 'li', function() {
		$(this).toggleClass('selected');
	});
	$('#delBtn1').click(function() {
		$('#doing .selected').remove();
	});
	$('#moveLeft1').click(function() {

		$('#done').append($('#doing .selected').removeClass('selected'));

	});
	$('#moveRight1').click(function() {

		$('#todo').append($('#doing .selected').removeClass('selected'));

	});

	$('#addBtn2').click(function() {
		var toAdd2 = $('input[name=done]').val();
		ajax("/DataStoreCreation", toAdd2, "addBtn2");
		$('#done').append('<li>' + toAdd2 + '</li>');
	});
	$('input').focus(function() {
		$(this).val('');
	});
	/*
	 * $(document).on('dblclick', 'li', function() { $('#done
	 * .selected').remove(); });
	 */
	$('#delBtn2').click(function() {
		$('#done .selected').remove();
	});
	$('#moveLeft2').click(function() {

		$('#doing').append($('#done .selected').removeClass('selected'));

	});
	$('#moveTodo').click(function() {
		$('#todo').append($('#done .selected').removeClass('selected'));
	});

	function ajax(urlValue, dataValue, identifier) {
		var x = dataValue;
		var y = urlValue;
		var z = identifier;
		var date = $("#mydate").val();
		debugger;
		$.ajax({
			url : y,
			type : "Get",
			headers : {
				'Accept' : 'Text/Plain',
				'Content-Type' : 'Text/Plain'
			},
			data : {
				value : x,
				id : z,
				todayDate : date
			},
			contentType : "Text/plain",
			success : function() {
				alert("success");
			},
			error : function() {
				alert("error");
			}
		});
	}
});
