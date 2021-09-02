$(document).ready(function () {
    //show all class
    getStudentGroupForSearch();
});
$("#searchstugroid").on("change", function () {
    //remove and show new Semester
    getAllSemesterForSearch();
});
$("#searchSemester").on("change", function () {
    getStudentListForSearch();
});
//get Student Group by Course id
function getStudentGroupForSearch() {
    $.ajax({
        url: '/api/user/student/timetable/findStudentgroup',
        type: "GET",
        datatype: "JSON",
        data: '',
        success: function (result) {
            var option = '<option value="" disabled selected>--Select Student Group--</option>';
            for (var i in result) {
                option += '<option class="stuGroupOption" value="' + result[i].stugroid + '">' + result[i].stugronm + '</option>';
            }
            $("#searchstugroid").html(option);
        }
    });
}
//get All Semester
function getAllSemesterForSearch() {
    $.ajax({
        url: '/api/user/student/timetable/findAllSemester',
        type: "GET",
        datatype: "JSON",
        data: '',
        success: function (result) {
            var option = '<option value="" disabled selected>---select Semester---</option>';
            for (var i in result) {
                option += '<option class="semOption" value="' + result[i].semid + '">' + result[i].semnm + '</option>';
            }
            $("#searchSemester").html(option);
        }
    });
}
//get Student list by Student Group id
function getStudentListForSearch() {
    $.ajax({
        url: '/api/user/students/alltimetable',
        type: "GET",
        datatype: "JSON",
        data: {
            stugroid: $('#searchstugroid').val(),
            semid: $('#searchSemester').val()
        },
        success: function (list) {
            var option = '';
            for (var i in list) {

                if(i == 0 || (list[i].subjectname != list[i-1].subjectname)){
                    if(list[i].subjectname != 'Day Off'){
                        option += '<thead><th>Subject name</th> <th>Teacher name</th> <th>Date<br><small>(yyyy-mm-dd)</small></th> <th>Day of week</th> <th>Location</th> <th>session</th> <th style="width: 15%">Attendance<br><small>( >=70% is pass & < 70% is fail)</small></th></thead>';
                        option += '<thead style="background-color: beige;" ><td colspan="6" class="attendSum h3" id="att'+list[i].subjdetailsid+'">'+list[i].subjectname+'</td> <td class="attendTotalValue"></td></thead>';
                    }
                }
                if(list[i].subjectname != 'Day Off'){
                    option += '<tbody><tr class="att'+list[i].subjdetailsid+'">';
                    option += '<td class="subjnm">' + list[i].subjnm + '</td>';
                    option += '<td class="teanm">' + list[i].teanm + '</td>';
                    option += '<td class="subjdate">' + '<b>' + list[i].subjdate + '</b>' + '</td>';
                    option += '<td class="subjdateofmonth">' + '<b>' + list[i].subjdateofmonth + '</b>' + '</td>';
                    option += '<td class="branchcamnm">' + list[i].branchcamnm + '</td>';
                    var slotraw =list[i].slotofsubjdate;
                    var timeraw =list[i].slotofsubjdate;
                    var slot = slotraw.slice(slotraw.indexOf('?')+1,slotraw.indexOf('?') + 3);
                    var time = "("+timeraw.slice(timeraw.indexOf('?')+4,timeraw.indexOf('?') + 15)+")";
                    option += '<td class="session">' + slot + ' '+ time + '</td>';
                    option += '<td class="attendance" >' + list[i].timetableid + '</td>';
                    option += '</tr></tbody>';
                }else{
                    option += '<tbody><tr class="att'+list[i].subjdetailsid+'">';
                    option += '<td style="background-color: azure; " class="subjnm">' + list[i].subjnm + '</td>';
                    option += '<td style="background-color: azure; " class="teanm text-secondary"><b>NONE</b></td>';
                    option += '<td style="background-color: azure; " class="subjdate">' + '<b>' + list[i].subjdate + '</b>' + '</td>';
                    option += '<td style="background-color: azure; " class="subjdateofmonth">' + '<b>' + list[i].subjdateofmonth + '</b>' + '</td>';
                    option += '<td style="background-color: azure; " class="branchcamnm text-secondary"><b>NONE</b></td>';
                    option += '<td style="background-color: azure; " class="session text-secondary"><b>NONE</b></td>';
                    option += '<td style="background-color: azure; " class="dayoff text-secondary"><b>NONE</b></td>';
                    option += '</tr></tbody>';
                }

            }
            $("#tabledb1").html(option);

            getAttend();
        }
    });

}

//add attendance to each day
async function getAttend(){
    $("#tabledb1 tbody").each(function(i, el) {

        $.ajax({
            url: '/api/user/students/getattendance',
            type: "GET",
            datatype: "JSON",
            data: {
                timetableid: $(el).find('.attendance').text(),
            },
            success: function (result) {
                var attOption='';
                if (result.present === "true") {
                    $(el).find('.attendance').html('<b>PRESENT</b>');
                    $(el).find('.attendance').addClass('text-primary');
                    $(el).find('.attendance').removeClass('text-warning');
                    $(el).find('.attendance').removeClass('text-secondary');
                } else if (result.present === "false"){
                    $(el).find('.attendance').html('<b>ABSENT</b>');
                    $(el).find('.attendance').addClass('text-warning');
                    $(el).find('.attendance').removeClass('text-primary');
                    $(el).find('.attendance').removeClass('text-secondary');
                } else if (result.present === null){
                    $(el).find('.attendance').html('<b>NOT YET</b>');
                    $(el).find('.attendance').addClass('text-secondary');
                    $(el).find('.attendance').removeClass('text-warning');
                    $(el).find('.attendance').removeClass('text-primary');
                }
                countAttend();
            }
        });
    });
}

//get Total attendance for each subject
function countAttend(){
    $("#tabledb1 thead").each(function(i, el) {
        var count = 0;
        var totalCount=0;
        var totalAttendance=0;
        var id = $(el).find('.attendSum').attr('id');
        console.log(id);

        $("#tabledb1 ." + id).each(function(j, elem) {
            var attend=$(elem).find('.attendance').text();
            console.log("attend:"+attend)
            if(attend === 'PRESENT'){
                count++ ;
                console.log("count:"+count);
            }
            totalCount++ ;
            console.log("total:"+totalCount);
        });

        totalAttendance=Math.floor((count/totalCount)*100);
        $(el).find('.attendTotalValue').html('<small class="text-success">'+totalAttendance+'% PRESENT </small><br><small class="text-danger">'+(100 - totalAttendance)+'% ABSENT</small>');

    });
}