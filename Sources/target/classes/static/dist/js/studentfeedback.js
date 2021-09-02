$(document).ready(function () {
    //show all feedback
    getAllFeedbackForTable();
});

//get All course for table
function getAllFeedbackForTable() {
    $.ajax({
        url: '/api/students/feedback',
        type: "GET",
        datatype: "JSON",
        data: '',
        success: function (result) {
            var option = '';
            if(result.length > 0){
                for(var i in result){
                    option += `<tr>`;

                    option += `<td>`+ result[i].stugroupnm+`</td>`;
                    option += `<td>`+ result[i].dateoffeedback+`</td>`;
                    option += `<td>`+ result[i].enddatefeedback+`</td>`;
                    option += `<td>`+ result[i].lecturernm+`</td>`;
                    option += `<td>`+ result[i].subjectnm+`</td>`;

                    if(result[i].check){
                        option += `<td class="text-danger" style="vertical-align: middle;">`;

                        option += `<input type="text" class="my-2 form-control" id="note`+result[i].feedbackid+`" placeholder="Change Note...">`;
                        option += `<div class="input-group-append mt-2"><button class="btn btn-primary" type="button" onclick="updatenote(this)"><input type="hidden" value="`+result[i].feedbackid+`""/>
                            SAVE
                        </button></div>`;

                        option += `</div>`;

                        option +=`</td>`;
                    }else{
                        option += `<td style="vertical-align: middle;">`;
                        option += `	<a href="/students/dashboard/feedback/`+result[i].feedbackid+`"
                            class="btn btn-vk btn-icon">Take</a>`;
                        option +=`</td>`;
                    }

                    option += `</tr>`;
                }
            }else{
                option += `<tr><td colspan="6" class="text-center font-weight-light"><i>No Feedback</i></td></tr>`
            }
            $("#tabledb tbody").html(option);
        }
    });
}



//STUDENT FEEDBACK//
const form = document.querySelector('#submitform');
//submit form
form.addEventListener('submit', function (e) {
    e.preventDefault();

    var formData = {
        ans1 : $('input[name="ans1"]:checked').val(),
        ans2 : $('input[name="ans2"]:checked').val(),
        ans3 : $('input[name="ans3"]:checked').val(),
        ans4 : $('input[name="ans4"]:checked').val(),
        ans5 : $('input[name="ans5"]:checked').val(),
        note: $('#note').val(),
        feedbackid: $('#feedbackid').val(),
        studentroll: $('#studentroll').val()
    }
    console.log(formData);
    $.ajax({
        type: 'POST',
        url: '/api/students/feedback/post',
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
                setTimeout('window.location.replace("http://localhost:8888/students/dashboard/feedback");', 1000);
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
});

function updatenote(elem){
    var id = elem.querySelector('input').value;
    const note = document.querySelector('#note'+id);
    var formData = {
        note: note.value,
        feedbackid: id,
        studentrollnum: $('#studentroll').val()
    }
    console.log(formData);
    $.ajax({
        type: 'POST',
        url: '/api/students/feedback/updatenote',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function (result) {
            if(result.title === 'success'){
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Update Success',
                    showConfirmButton: false,
                    timer: 1000
                });
            }else{
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: result.message,
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
    });
}