/*--------adding validation error messages ------------------*/
function addError(id, errorParam) {
    $('span[for='+id+']').addClass('error');
	$('label[for='+id+']').addClass('error');
    $('input#'+id).addClass('error');
    $('select#'+id).addClass('error');
	$('button#'+id).addClass('error');
	$('input[name*="'+id+'"]').each(function (data){
		var radioVal = $(this).attr('id');
            $(this).addClass('error');
            $('span[for="'+radioVal+'"]').addClass('error');
           
	});
};

/*------------removing validation error messages -----------------*/
function removeError(id) {
    $('span[for='+id+']').removeClass('error');
	$('label[for='+id+']').removeClass('error');
    $('input#'+id).removeClass('error');
    $('select#'+id).removeClass('error');
    $('span.error.'+id).remove();
	$('input[name*="'+id+'"]').each(function (data){
		var radioVal = $(this).attr('id');
            $(this).removeClass('error');
            $('span[for="'+radioVal+'"]').removeClass('error');
          
	});
};

$(document).ready(function(){	
	
	 $('[data-toggle="tooltip"]').tooltip();  
	
	$('#MainMenu .has-submenu').click(function() {
		var $this = $(this).find("i");
		var $siblings= $(this).siblings('.has-submenu').find('i');
		var $child = $(this).find(".has-submenu i");
	    $this.toggleClass('glyphicon-plus-sign glyphicon-minus-sign');
	    $siblings.removeClass("glyphicon-minus-sign").addClass("glyphicon-plus-sign");
		//  $(this).children("i").removeClass("glyphicon-minus-sign").addClass("glyphicon-plus-sign");
 });
});