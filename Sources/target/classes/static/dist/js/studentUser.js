$(document).ready(function () {
    getExamstatus();

});

function getExamstatus(){
    $("#tabledb1 tbody tr").each(function(i, el) {
        var result=$(el).find('.examresult').text();
        result = result.replace('%','');
        if(result>=40){
            $(el).find('.examstatus').text("Pass");
            $(el).find('.examstatus').addClass('text-success');
        }else {
            $(el).find('.examstatus').text("Fail");
            $(el).find('.examstatus').addClass('text-danger');
        }
    });
};

$("#tabledb1 tbody tr").each(function () {
    if($(this).find('.Subjnm').text() === $(this).next().find('.Subjnm').text() && $(this).find('.Examtype').text() === $(this).next().find('.Examtype').text()){

        $(this).next().find('.Stugronm').remove();
        $(this).next().find('.Subjnm').remove();
        $(this).next().find('.Examtype').remove();

        $(this).find('.Stugronm').prop('rowspan',2);
        $(this).find('.Subjnm').prop('rowspan',2);
        $(this).find('.Examtype').prop('rowspan',2);
    }
});