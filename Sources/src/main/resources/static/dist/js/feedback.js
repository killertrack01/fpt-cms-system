//-------------------------
//validate
//-------------------------
const Course = document.querySelector('#Courses');
const StudentGroup = document.querySelector('#stugroid');
const Semester = document.querySelector('#Semester');
const Subject = document.querySelector('#subjdetailsid');
const Lecturer = document.querySelector('#Lecturer');
var DateFeedback = document.querySelector('#datefeedback');
var EndDate = document.querySelector('#enddatefeedback');

const form = document.querySelector('#formCreate');
var namefeedback = document.querySelector('#namefeedback');
var subject;

const required = value => value === '' ? false : true;

var DateDiff = {

    inDays: function(d1, d2) {
        var t2 = d2.getTime();
        var t1 = d1.getTime();

        return parseInt((t2-t1)/(24*3600*1000));
    },

    inWeeks: function(d1, d2) {
        var t2 = d2.getTime();
        var t1 = d1.getTime();

        return parseInt((t2-t1)/(24*3600*1000*7));
    },

    inMonths: function(d1, d2) {
        var d1Y = d1.getFullYear();
        var d2Y = d2.getFullYear();
        var d1M = d1.getMonth();
        var d2M = d2.getMonth();

        return (d2M+12*d2Y)-(d1M+12*d1Y);
    },

    inYears: function(d1, d2) {
        return d2.getFullYear()-d1.getFullYear();
    }
}

const DateValid = (date, end_date) => {
    const startDate = new Date(date.value);
    const endDate = new Date(end_date.value);
    if(DateDiff.inDays(startDate, endDate) > 0){
        return true;
    }else{
        return false;
    }
}

const StartDateValid = (date, end_date) => {
    const startDate = new Date(date.value);
    const endDate = new Date(end_date.value);
    if(DateDiff.inDays(startDate, endDate) < 0){
        return true;
    }else{
        return false;
    }
}


// const DateValid = (date, end_date) => {
//     // const currentDate = new Date();
//     // const inputDate = new Date(date.value);
//     const date = new Date(date.value);
//     const endDate = new Date(end_date.value);
//     if (inputDate.getFullYear() == currentDate.getFullYear()) {
//         if (inputDate.getMonth() == currentDate.getMonth()) {
//             if (inputDate.getDate() < currentDate.getDate()) {
//                 return true;
//             } else {
//                 return false;
//             }
//         } else if (inputDate.getMonth() < currentDate.getMonth()) {
//             return true;
//         } else {
//             return false;
//         }
//     } else if (inputDate.getFullYear() < currentDate.getFullYear()) {
//         return true;
//     } else {
//         return false;
//     }
// };

const showError = (input, message) => {
    const formField = input.parentElement;

    input.classList.remove('is-valid');
    input.classList.add('is-invalid');

    input.focus();

    formField.classList.remove('success');
    formField.classList.add('error');

    // show message error with wrong input
    const error = formField.querySelector('small');
    error.classList.add('text-danger');
    error.textContent = message;
};

const showSuccess = (input) => {
    const formField = input.parentElement;

    input.classList.remove('is-invalid');
    input.classList.add('is-valid');

    formField.classList.remove('error');
    formField.classList.add('success');

    // hide message error
    const error = formField.querySelector('small');
    error.textContent = ''; // assign null value to hide error message
};
const showNormal = (input) => {

    const formField = input.parentElement;

    input.classList.remove('is-invalid');
    input.classList.remove('is-valid');

    formField.classList.remove('error');
    formField.classList.remove('success');

    // hide message error
    const error = formField.querySelector('small');
    error.textContent = ''; // assign null value to hide error message
};
// Validate course
const checkCourse = (exam_course) => {
    let valid = false;
    const examcourse = exam_course.value;

    if (!required(examcourse)) {
        showError(exam_course, 'Please select a course !');
    } else {
        showSuccess(exam_course);
        valid = true;
    }
    return valid;
}
// Validate Student Group
const checkStudentGroup = (student_group) => {
    let valid = false;
    const studentgroup = student_group.value;

    if (!required(studentgroup)) {
        showError(student_group, 'Please select Student group !');
    } else {
        showSuccess(student_group);
        valid = true;
    }
    return valid;
}
// Validate semester
const checkSemester = (exam_semester) => {
    let valid = false;
    const semester = exam_semester.value;

    if (!required(semester)) {
        showError(exam_semester, 'Please select Semester !');
    } else {
        showSuccess(exam_semester);
        valid = true;
    }
    return valid;
}
// Validate subject
const checkSubject = (exam_subject) => {
    let valid = false;
    const subject = exam_subject.value;

    if (!required(subject)) {
        showError(exam_subject, 'Please select Subject !');
    } else {
        showSuccess(exam_subject);
        valid = true;
    }
    return valid;
}
// Validate subject
const checkLecturer = (lecturer) => {
    let valid = false;
    const teacher = lecturer.value;

    if (!required(teacher)) {
        showError(lecturer, 'Please select Lecturer !');
    } else {
        showSuccess(lecturer);
        valid = true;
    }
    return valid;
}

// Validate DateIssue
const checkDate = (enddate_feedback, date_feedback) => {
    let valid = false;
    const FeedbackDate = date_feedback.value;
    var enddate = $('#enddatefeedback').val();
    if (!required(FeedbackDate)) {
        showError(date_feedback, 'Date Feedback cannot be blank !');
    }else if(enddate != ''){
        if(DateValid(date_feedback, enddate_feedback) == false){
            showError(date_feedback, 'Date Feedback must be lowerthan End Date');
        }else{
            showNormal(date_feedback);
            // show message error with wrong input
            $('#smalldatefeedback').text('Date Selected');
            valid = true;
        }
    }else {
        showNormal(date_feedback);
        // show message error with wrong input
        $('#smalldatefeedback').text('Date Selected');
        valid = true;
    }
    return valid;
};
// Validate DateIssue
const checkEndDate = (enddate_feedback, date_feedback) => {
    let valid = false;
    const enddate = enddate_feedback.value;
    var dateoffeedback = $('#datefeedback').val();

    if (!required(enddate)) {
        showError(enddate_feedback, 'End Date cannot be blank !');
    }else if(dateoffeedback != ''){
        if(DateValid(date_feedback, enddate_feedback) == false){
            showError(enddate_feedback, 'End Date must greater than Start Date');
        }else{
            showNormal(enddate_feedback);
            // show message error with wrong input
            $('#smallenddatefeedback').text('Date Selected');
            valid = true;
        }
    }else {
        showNormal(enddate_feedback);
        // show message error with wrong input
        $('#smallenddatefeedback').text('Date Selected');
        valid = true;
    }
    return valid;
};


//-------------------------
// Validate create form
//Trigger validation check
form.addEventListener('input', function (e) {
    switch (e.target.id) {
        case 'Courses':
            checkCourse(Course);
            break;
        case 'stugroid':
            checkStudentGroup(StudentGroup);
            break;
        case 'Semester':
            checkSemester(Semester);
            break;
        case 'subjdetailsid':
            checkSubject(Subject);
            break;
        case 'Lecturer':
            checkLecturer(Lecturer);
            break;
        case 'datefeedback':
            checkDate(EndDate, DateFeedback);
            break;
        case 'enddatefeedback':
            checkEndDate(EndDate, DateFeedback);
            break;
    }
});
//----------------------
//end validate
//----------------------
$(document).ready(function () {
    //show all course
    getAllCourses();
    //show all feedback
    //getAllFeedbackForTable();
});
$("#Courses").on("change", function () {
    //remove and show new student group
    getStudentGroupByCourse($(this).val());
    //remove semester
    $("#Semester").html('');
    //remove subject
    $("#subjdetailsid").html('');
    //remove lecturer
    $("#Lecturer").html('');
});
$("#stugroid").on("change", function () {
    var selectedStugroup = $(this).children("option:selected").val();
    //remove and show new Semester
    getAllSemester(selectedStugroup);
    //remove subject
    $("#subjdetailsid").html('');
    //remove lecturer
    $("#Lecturer").html('');
});
$("#Semester").on("change", function () {
    var selectedSemid = $(this).children("option:selected").val();
    var stugroid = $('#stugroid').children("option:selected").val();
    //remove and show new subject
    getSubjectDetail();
    getLecturer(stugroid, selectedSemid);
});
$("#subjdetailsid").on("change", function () {
    namefeedback.value = '';
    subject = '';
    var selectedText;
    selectedText = $(this).find("option:selected").text();
    console.log(selectedText);
    selectedText += '_';
    namefeedback.value = selectedText;
    subject = selectedText;
    //getLecturer();
    var Lecturername = $('#Lecturer').find("option:selected").text();
    namefeedback.value = subject + Lecturername;

    //insert date value
    $.ajax({
        type: 'GET',
        url: '/api/admin/feedback/findFeedbackMaxDate',
        data: {
            stugroid: $('#stugroid').val(),
            subjdetailsid:  $('#subjdetailsid').children("option:selected").val()
        },
        datatype: "JSON",
        success: function (result) {
            console.log(result);
            if(result === ''){
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: "Timetable Not Exist",
                    showConfirmButton: false,
                    timer: 1000
                });
            }else{
                $('#datefeedback').val(result.dateoffeedback);
                $('#enddatefeedback').val(result.enddatefeedback);
            }
        }
    });
});

$("#Lecturer").on("change", function () {
    var selectedText = $(this).find("option:selected").text();
    namefeedback.value = '';
    data = subject + selectedText;
    namefeedback.value = data;
});

//get All course
function getAllCourses() {
    $.ajax({
        url: '/api/admin/feedback/findAllCourse',
        type: "GET",
        datatype: "JSON",
        data: '',
        success: function (result) {
            var option = '<option value="" disabled selected>Select Course</option>';
            for (var i in result) {
                option += '<option class="courseOption" value="' + result[i].courid + '">' + result[i].cournm + '</option>';
            }
            $("#Courses").html(option);
        }
    });
}
//get All Semester
function getAllSemester(selectedStugroup) {
    $.ajax({
        url: '/api/admin/feedback/findAllSemester/' + selectedStugroup,
        type: "GET",
        datatype: "JSON",
        success: function (result) {
            console.log(result);
            var option = '<option value="" disabled selected>Select Semester</option>';
            for (var i in result) {
                option += '<option class="courseOption" value="' + result[i].semid + '">' + result[i].semnm + '</option>';
            }
            $("#Semester").html(option);
        }
    });
}

//get Student Group by Course id
function getStudentGroupByCourse(courseId) {
    $.ajax({
        url: '/api/admin/feedback/findStudentgroup/' + courseId,
        type: "GET",
        datatype: "JSON",
        data: '',
        success: function (result) {
            var option = '<option value="" disabled selected>Select Student Group</option>';
            for (var i in result) {
                option += '<option class="stuGroupOption" value="' + result[i].stugroid + '">' + result[i].stugronm + '</option>';
            }
            $("#stugroid").html(option);
        }
    });
}

//get subject details
function getSubjectDetail() {
    var semid = $('#Semester').val();
    var courid = $('#Courses').val();
    $.ajax({
        url: '/api/admin/feedback/findSubjectDetails/' + semid + '/' + courid,
        type: "GET",
        datatype: "JSON",
        data: '',
        success: function (result) {
            var option = '<option value="" disabled selected>Select Subject</option>';
            for (var i in result) {
                option += '<option class="subjectOption" value="' + result[i].subjdetailsid + '">' + result[i].subjnm + '</option>';
            }
            $("#subjdetailsid").html(option);
        }
    });
}
//get lecturer
function getLecturer(stugroid, semid) {
    $.ajax({
        url: '/api/admin/feedback/findAllLecturer',
        type: "GET",
        datatype: "JSON",
        data: {
            'stugroid': stugroid,
            'semid': semid
        },
        success: function (result) {
            var option = '<option value="' + result.lecturid + '" disabled selected>' + result.fullnm + '</option>';
            // option += '<option class="subjectOption" value="' + result.lecturid + '">' + result.fullnm + '</option>';
            $("#Lecturer").html(option);
        }
    });
}

//get All course for table
function getAllFeedbackForTable() {
    $.ajax({
        url: '/api/admin/feedback/findAllFeedback',
        type: "GET",
        datatype: "JSON",
        data: '',
        success: function (result) {
            var option = '';
            for(var i in result){
                option += `<tr>`;

                option += `<td>`+ result[i].feedbackid+`</td>`;
                option += `<td>`+ result[i].namefeedback+`</td>`;
                option += `<td>`+ result[i].stugroupnm+`</td>`;
                option += `<td>`+ result[i].subjectnm+`</td>`;
                // option += `<td>`+ result[i].lecturernm+`</td>`;
                option += `<td>`+ result[i].dateoffeedback+`</td>`;
                option += `<td>`+ result[i].enddatefeedback+`</td>`;

                option+= `<td>`;
                option += `<button class="btn btn-vk btn-icon mx-1" onclick="findStudentFeedback(this)">
                <svg xmlns="http://www.w3.org/2000/svg" class="icon"
                width="24" height="24" viewBox="0 0 24 24" stroke-width="2"
                stroke="currentColor" fill="none" stroke-linecap="round"
                stroke-linejoin="round">
                <path stroke="none" d="M0 0h24v24H0z" fill="none" />
                <circle cx="12" cy="12" r="2" />
                <path
                d="M22 12c-2.667 4.667 -6 7 -10 7s-7.333 -2.333 -10 -7c2.667 -4.667 6 -7 10 -7s7.333 2.333 10 7" />
                </svg>
                <input type="hidden" value="`+result[i].feedbackid+`">
                <input type="hidden" id="counter`+result[i].feedbackid+`" value="0"/>
                 </button>`;

                if(result[i].count == '0'){
                    option += `<button class="btn btn-vk btn-icon" onclick="findFeedback(this)"
                data-bs-toggle="modal" data-bs-target="#modal-edit-student">
                <svg xmlns="http://www.w3.org/2000/svg" class="icon" width="24" height="24"
                    viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none"
                    stroke-linecap="round" stroke-linejoin="round">
                    <path stroke="none" d="M0 0h24v24H0z" fill="none" />
                    <path d="M9 7h-3a2 2 0 0 0 -2 2v9a2 2 0 0 0 2 2h9a2 2 0 0 0 2 -2v-3" />
                    <path d="M9 15h3l8.5 -8.5a1.5 1.5 0 0 0 -3 -3l-8.5 8.5v3" />
                    <line x1="16" y1="5" x2="19" y2="8" />
                </svg>
                <input type="hidden" value="`+result[i].feedbackid+`">
                 </button>`;

                    option += ` <button class="btn btn-google btn-icon" onclick="deleteFeedback(this)">
                <svg xmlns="http://www.w3.org/2000/svg" class="icon" width="24" height="24"
                     viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none"
                     stroke-linecap="round" stroke-linejoin="round">
                   <path stroke="none" d="M0 0h24v24H0z" fill="none" />
                   <path d="M15 3v4a1 1 0 0 0 1 1h4" />
                   <path
                           d="M17 17h-6a2 2 0 0 1 -2 -2v-6m0 -4a2 2 0 0 1 2 -2h4l5 5v7c0 .294 -.063 .572 -.177 .823" />
                   <path d="M16 17v2a2 2 0 0 1 -2 2h-7a2 2 0 0 1 -2 -2v-10a2 2 0 0 1 2 -2" />
                   <line x1="3" y1="3" x2="21" y2="21" />
                </svg>
                <input type="hidden" value="`+result[i].feedbackid+`">
             </button>`;
                }

                option += `</td>`;

                option += `</tr>`;
            }
            $("#tabledb tbody").html(option);
        }
    });
}
//select dom
$("select.country").change(function(){
    var selectedCountry = $(this).children("option:selected").val();
    //get feedback id in table
    var feedid = $(this).parent('td').find('input').val();

    var formData = {
        feedbackid: feedid,
        feedbackstatus: selectedCountry
    }
    $.ajax({
        type: 'POST',
        url: '/api/admin/feedback/updateStatus',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function (result) {
            if(result.title === 'success'){
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: result.message,
                    showConfirmButton: false,
                    timer: 1000
                });
            }else{
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: result.error,
                    showConfirmButton: false,
                    timer: 1000
                });
            }
            setTimeout("location.reload(true);", 1000);
        },
        error: function () {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Error',
                showConfirmButton: false,
                timer: 1000
            });
        }
    })
});

function findStudentFeedback(elem){
    var feedbackid = elem.querySelector('input').value;
    var idcounter = '#counter'+feedbackid;
    var counter = Number($(idcounter).val());
    $.ajax({
        url: '/api/admin/feedback/findStudentFeedback/'+feedbackid,
        type: "GET",
        datatype: "JSON",
        data: '',
        success: function (result) {
            var option = '';
            option += `<div class="row row-cards d-flex justify-content-center">
                            <div class="col-12">
                            <h2>Feedback `+result[0].namefeedback+`</h2>
                            <br/>
                                <div class="card">     
                                    <div class="table-responsive">
                                        <table class="table table-vcenter card-table table-bordered" id="studentFeedbackList">
                                            <colgroup>
                                                <col span="1" style="width: 10%;">
                                                <col span="1" style="width: 10%;">
                                                <col span="1" style="width: 10%;">
                                                <col span="1" style="width: 10%;">
                                                <col span="1" style="width: 10%;">
                                                <col span="1" style="width: 10%;">
                                                <col span="1" style="width: 10%;">
                                                <col span="1" style="width: 30%;">
                                            </colgroup>

                                            <thead>
                                            <tr>
                                                <th scope="col">No.</th>
                                                <th scope="col">Answer 1</th>
                                                <th scope="col">Answer 2</th>
                                                <th scope="col">Answer 3</th>
                                                <th scope="col">Answer 4</th>
                                                <th scope="col">Answer 5</th>
                                                <th scope="col">GPA</th>
                                                <th scope="col">Note</th>
                                            </tr>
                                            </thead>
                                            <tbody>`;

            if(result.length === 1){
                option += `<tr><td colspan="8" class="text-center"><i>No Student Feedback</i></td></tr>`;
            }else{
                var totalGpa = 0;
                var count = result.length-1;
                for(let i = 1; i < result.length; i++){
                    option += `<tr>`;
                    option += `<td>`+i+`</td>`;
                    option += `<td>`+result[i].ans1+`</td>`;
                    option += `<td>`+result[i].ans2+`</td>`;
                    option += `<td>`+result[i].ans3+`</td>`;
                    option += `<td>`+result[i].ans4+`</td>`;
                    option += `<td>`+result[i].ans5+`</td>`;
                    option += `<td>`+result[i].gpa+`</td>`;
                    totalGpa += Number(result[i].gpa);
                    var note = result[i].note === null ? '' : result[i].note;
                    option += `<td>`+note+`</td>`;
                    option +=`</tr>`;
                }
                option += `<tr><td colspan="8" class="text-center font-weight-bold text-uppercase">Total GPA: `+totalGpa/count+`</td></tr>`
            }
            option += `</tbody>`;
            option += `</table>
                        </div>
                    </div>
                </div>
            </div>`;
            if(counter % 2 === 0){
                $('#studentFeedbackList').html(option);
            }else{
                $('#studentFeedbackList').html('');
            }
            counter++;
            $(idcounter).val(counter);
        }
    });
}


//submit form
form.addEventListener('submit', function (e) {
    e.preventDefault();
    let isexamcoursevalid=checkCourse(Course),
        isstudentgroupvalid=checkStudentGroup(StudentGroup),
        issemestervalid=checkSemester(Semester),
        issubjectvalid=checkSubject(Subject),
        islecturervalid = checkLecturer(Lecturer);
    isDateValid =  checkDate(EndDate, DateFeedback);
    isEndDateValid = checkEndDate(EndDate, DateFeedback);

    let isformvalid=isexamcoursevalid && isEndDateValid && isstudentgroupvalid && issemestervalid && issubjectvalid&&islecturervalid&&isDateValid;

    if(isformvalid){
        var id = $('#Lecturer').children("option:selected").val();
        var formData = {
            dateoffeedback: $('#datefeedback').val(),
            stugroid: $('#stugroid').val(),
            lecturid: id,
            subjdetailsid: $('#subjdetailsid').val(),
            namefeedback: $('#namefeedback').val(),
            enddatefeedback: $('#enddatefeedback').val()
        }
        console.log(formData);
        $.ajax({
            type: 'POST',
            url: '/api/admin/feedback/saveFeedback',
            data: JSON.stringify(formData),
            contentType: 'application/json',
            success: function (result) {
                if(result.title === 'success'){
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Success',
                        showConfirmButton: false,
                        timer: 1000
                    });
                    setTimeout("location.reload(true);", 1000);
                }else{
                    Swal.fire({
                        position: 'center',
                        icon: 'error',
                        title: result.message,
                        showConfirmButton: false,
                        timer: 1000
                    });
                }
            },
            error: function () {
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: 'Error',
                    showConfirmButton: false,
                    timer: 1000
                });
            }
        });
    }
});


// EDIT FEEDBACK//
const EditSubject = document.querySelector('#editsubjdetailsid');
const EditLecturer = document.querySelector('#editLecturer');
const EditFeedbackID = document.querySelector('#editfeedbackid');
const editdatefeedback = document.querySelector('#editdatefeedback');
const editnamefeedback = document.querySelector('#editnamefeedback');
const editEnddatefeedback = document.querySelector('#editenddatefeedback');
const formEdit = document.querySelector('#formEdit');
var optionSubject = '';
var optionLecturer = '';
var position;
var editsubject = '';
var editlecturer = '';
var subjdetailsid = '';
var lecturerid = '';

// Validate DateIssue
const checkEditDate = (enddate_feedback, date_feedback) => {
    let valid = false;
    const date = date_feedback.value;
    var enddate = $('#editenddatefeedback').val();
    if (!required(date)) {
        showError(date_feedback, 'Date Feedback cannot be blank !');
    }else if(enddate != ''){
        if(DateValid(date_feedback, enddate_feedback) == false){
            showError(date_feedback, 'Date Feedback must be lowerthan End Date');
        }else{
            showNormal(date_feedback);
            // show message error with wrong input
            $('#editsamlldatefeedback').text('Date Selected');
            valid = true;
        }
    }else {
        showNormal(date_feedback);
        // show message error with wrong input
        $('#editsamlldatefeedback').text('Date Selected');
        valid = true;
    }
    return valid;
};
// Validate DateIssue
const checkEditEndDate = (enddate_feedback, date_feedback) => {
    let valid = false;
    const enddate = enddate_feedback.value;
    var dateoffeedback = $('#editdatefeedback').val();

    if (!required(enddate)) {
        showError(enddate_feedback, 'End Date cannot be blank !');
    }else if(dateoffeedback != ''){
        if(DateValid(date_feedback, enddate_feedback) == false){
            showError(enddate_feedback, 'End Date must greater than Start Date');
        }else{
            showNormal(enddate_feedback);
            // show message error with wrong input
            $('#smalleditenddatefeedback').text('Date Selected');
            valid = true;
        }
    }else {
        showNormal(enddate_feedback);
        // show message error with wrong input
        $('#smalleditenddatefeedback').text('Date Selected');
        valid = true;
    }
    return valid;
};

function findFeedback(elem){
    var id = elem.querySelector('input').value;
    $.ajax({
        url: '/api/admin/feedback/find/'+id,
        type: "GET",
        datatype: "JSON",
        data: '',
        success: function (result) {
            optionSubject += '<option selected value="' + result.subjdetailsid + '">' + result.subjectnm + '</option>';
            $("#editsubjdetailsid").html(optionSubject);
            optionLecturer += '<option selected value="' + result.lecturid + '">' + result.lecturernm + '</option>';
            $("#editLecturer").html(optionLecturer);

            $('#editfeedbackid').val(result.feedbackid);
            $('#editdatefeedback').val(result.dateoffeedback);
            $('#editnamefeedback').val(result.namefeedback);
            $('#editCourses').val(result.courseid);
            $('#editSemester').val(result.semesterid);
            $('#editstugroid').val(result.stugroid);
            $('#editenddatefeedback').val(result.enddatefeedback);

            position = $('#editnamefeedback').val().lastIndexOf('_');
            editsubject = $('#editnamefeedback').val().slice(0, position);
            editlecturer = $('#editnamefeedback').val().slice(position+1);

            subjdetailsid = result.subjdetailsid;
            lecturerid = result.lecturid;

            getEditSubjectDetail();
            getEditLecturer();
        }
    });
}

//get subject details
function getEditSubjectDetail() {
    var semid = $('#editCourses').val();
    var courid = $('#editSemester').val();
    $.ajax({
        url: '/api/admin/feedback/findSubjectDetails/' + semid + '/' + courid,
        type: "GET",
        datatype: "JSON",
        data: '',
        success: function (result) {
            for (var i in result) {
                if(result[i].subjdetailsid != subjdetailsid){
                    optionSubject += '<option class="subjectOption" value="' + result[i].subjdetailsid + '">' + result[i].subjnm + '</option>';
                }
            }
            $("#editsubjdetailsid").html(optionSubject);
        }
    });
}
//get lecturer
function getEditLecturer() {
    $.ajax({
        url: '/api/admin/feedback/findAllLecturer',
        type: "GET",
        datatype: "JSON",
        data: '',
        success: function (result) {
            for (var i in result) {
                if(result[i].lecturid != lecturerid){
                    optionLecturer += '<option class="subjectOption" value="' + result[i].lecturid + '">' + result[i].fullnm + '</option>';
                }
            }
            $("#editLecturer").html(optionLecturer);
        }
    });
}
$("#formEdit #editsubjdetailsid").on("change", function () {
    position = $('#editnamefeedback').val().lastIndexOf('_');
    editsubject = '';
    editsubject = $('#editnamefeedback').val().slice(0, position);
    var selectedText = $(this).find("option:selected").text();
    var text = $('#editnamefeedback').val().replace(editsubject, selectedText);
    $("#editnamefeedback").val(text);
});

$("#formEdit #editLecturer").on("change", function () {
    position = $('#editnamefeedback').val().lastIndexOf('_');
    editlecturer = '';
    editlecturer = $('#editnamefeedback').val().slice(position+1);
    var selectedText = $(this).find("option:selected").text();
    var text = $('#editnamefeedback').val().replace(editlecturer, selectedText);
    $("#editnamefeedback").val(text);
});

//submit form
formEdit.addEventListener('submit', function (e) {
    e.preventDefault();

    var formData = {
        dateoffeedback: $('#editdatefeedback').val(),
        stugroid: $('#editstugroid').val(),
        lecturid: $('#editLecturer').val(),
        subjdetailsid: $('#editsubjdetailsid').val(),
        namefeedback: $('#editnamefeedback').val(),
        feedbackid: $('#editfeedbackid').val(),
        enddatefeedback: $('#editenddatefeedback').val()
    }
    $.ajax({
        type: 'POST',
        url: '/api/admin/feedback/editFeedback',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function (result) {
            if(result.title === 'success'){
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Success',
                    showConfirmButton: false,
                    timer: 1000
                });
                setTimeout("location.reload(true);", 1000);
            }else{
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: 'Error',
                    showConfirmButton: false,
                    timer: 1000
                });
            }
        },
        error: function () {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Error',
                showConfirmButton: false,
                timer: 1000
            });
        }
    });
});



function deleteFeedback(elem) {
    var id = elem.querySelector('input').value;
    Swal.fire({
        title: 'Do you want to delete ?',
        showDenyButton: true,
        confirmButtonText: `Yes`,
        denyButtonText: `No`,
    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            $.ajax({
                type: 'POST',
                url: '/api/admin/feedback/deleteFeedback/' + id,
                success: function () {
                    Swal.fire('Saved!', '', 'success');
                    setTimeout("location.reload(true);", 1000);
                }
            })

        } else if (result.isDenied) {
            Swal.fire('Changes are not saved', '', 'info');
        }
    })
}

formEdit.addEventListener('input', function (e) {
    switch (e.target.id) {
        case 'editdatefeedback':
            checkEditDate(editEnddatefeedback, editdatefeedback);
            break;
        case 'editenddatefeedback':
            checkEditEndDate(editEnddatefeedback, editdatefeedback);
            break;
    }
});