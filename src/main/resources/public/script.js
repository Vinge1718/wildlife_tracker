$(function(){
    var forms = $('form');
    forms.hide();

    $('#category').on('change', function(){
        forms.hide();
        var formId = $(this).val();
        $(formId).show();

        // function adjust_textarea(h) {
        //     h.style.height = "12px";
        //     h.style.height = (h.scrollHeight)+"px";
        // }
    });


});
