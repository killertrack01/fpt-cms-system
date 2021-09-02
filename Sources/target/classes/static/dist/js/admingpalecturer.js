const monthOfYear = [
    {name:'JANUARY', value: '1'},
    {name:'FEBRUARY', value: '2'},
    {name:'MARCH', value: '3'},
    {name:'APRIL', value: '4'},
    {name:'MAY', value: '5'},
    {name:'JUNE', value: '6'},
    {name:'JULY', value: '7'},
    {name:'AUGUST', value: '8'},
    {name:'SEPTEMBER', value: '9'},
    {name:'OCTOBER', value: '10'},
    {name:'NOVEMBER', value: '11'},
    {name:'DECEMBER', value: '12'}
];
function findGpaOfYear(){
    var year = $("#gpayear").children("option:selected").val();
    $.ajax({
        url: '/api/admin/teacher/gpalecturerforyear',
        type: "GET",
        data: {
            gpayear: $("#gpayear").children("option:selected").val(),
            lecturid: $("#lecturid").children("option:selected").val()
        },
        success: function (result) {
            console.log(result);
            if(result.length > 0){
                var arrayCopy = monthOfYear.slice();
                var month = '';
                for(i = 0; i < result.length; i++){
                    for(j = 0; j < arrayCopy.length; j++){
                        if(String(result[i].gpamonth) === arrayCopy[j].value){
                            month += String(result[i].gpamonth);
                        }
                    }
                }
                var str = '';
                str += `<table class="table table-vcenter card-table table-bordered">`;
                str += `<thead>
                            <tr>
                                <th style="width:350px;  font-size: 20px">`+year+`</th>
                                <th>LECTURER</th>
                                <th>SUBJECT</th>
                                <th>GPA</th>
                                <th>STUDENTGROUP</th>
                                <th>DATE</th>
                            </tr>
                        </thead>`;
                str += `<tbody>`;
                for(let i = 0; i < arrayCopy.length; i++){
                    for(let j = 0; j < result.length; j++){
                        if(arrayCopy[i].value === String(result[j].gpamonth)){
                            if(j === 0 || result[j].gpamonth !== result[j-1].gpamonth){
                                str += `<tr>
                                    <th style="background: #009f75; color: #f1f1f2; vertical-align:center" rowspan="`+result[j].maxmonth+`">`+arrayCopy[i].name+`</th>
                                    <td>&nbsp;&nbsp;`+result[j].fullnm+`</td>
                                    <td>&nbsp;&nbsp;`+result[j].subjnm+`</td>
                                    <td style="font-size: 15px" class="text-danger font-weight-bold">`+result[j].gpascore+`</td>
                                    <td>`+result[j].stugronm+`</td>
                                    <td>`+result[j].datefeedback+`</td>
                                </tr>`;
                            }else{
                                str += `<tr>
                                    <td>`+result[j].fullnm+`</td>
                                    <td>&nbsp;&nbsp;`+result[j].subjnm+`</td>
                                    <td style="font-size: 15px" class="text-danger font-weight-bold">`+result[j].gpascore+`</td>
                                    <td>`+result[j].stugronm+`</td>
                                    <td>`+result[j].datefeedback+`</td>
                                </tr>`;
                            }
                        }
                    }
                    if(month.indexOf(arrayCopy[i].value) == (-1)){
                        str += `<tr>
                                <th scope="rowgroup">`+arrayCopy[i].name+`</th>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                            </tr>`;
                    }
                }
                str += `</tbody>`;
                str += `</table>`;
                $("#tablegpa").html(str);
                console.log(month);
            }else{
                var str = '';
                str += `<table>`;
                str += `<table class="table table-vcenter card-table table-bordered">`;
                str += `<thead>
                            <tr>
                                <th style="width:350px;  font-size: 20px">`+year+`</th>
                                <th>LECTURER</th>
                                <th>SUBJECT</th>
                                <th>STUDENTGROUP</th>
                                <th>GPA</th>
                                <th>DATE</th>
                            </tr>
                        </thead>`;
                str += `<tbody>`;
                str += `<tr><td colspan="6" style="background: #75b1a9; color: #f1f1f2" class="text-center font-weight-bold">No Data</td></tr>`
                str += `</tbody>`;
                str += `</table>`;
                $("#tablegpa").html(str);

            }
        }
    });
}

function findGpaOfMonth(){
    var month = $("#gpamonth").children("option:selected").val();
    $.ajax({
        url: '/api/admin/teacher/gpalecturer',
        type: "GET",
        datatype: "JSON",
        data: {
            gpamonth: $("#gpamonth").children("option:selected").val(),
            gpayear: $("#gpayear").children("option:selected").val(),
            lecturid: $("#lecturid").children("option:selected").val()
        },
        success: function (result) {
            if(result.length > 0){
                var str = '';
                str += `<table class="table table-vcenter card-table table-bordered">`;
                str += `<thead>
                             <tr>
                                 <th>SUBJECT</th>
                                 <th>GPA</th>
                                 <th>STUDENTGROUP</th>                              
                                 <th>DATE</th>
                             </tr>
                         </thead>`;
                str += `<tbody>`;
                for(var i = 0; i < result.length; i++){
                    str += `<tr>
                                 <td>`+result[i].subjectnm+`</td>
                                 <td style="font-size: 18px" class="font-weight-bold text-danger">`+result[i].gpascore+`</td>
                                 <td>`+result[i].studentgroupnm+`</td>                            
                                 <td>`+result[i].gpadate+`</td>
                             </tr>`;
                }
                str += `</tbody>`;
                str += `</table>`;
                $("#tablegpa").html(str);
            }else{
                var str = '';
                str += `<table>`;
                str += `<table class="table table-vcenter card-table table-bordered">`;
                str += `<thead>
                            <tr>
                                <th style="width:350px; font-size: 20px">#</th>
                                <th>SUBJECT</th>
                                <th>STUDENTGROUP</th>
                                <th>GPA</th>
                                <th>DATE</th>
                            </tr>
                        </thead>`;
                str += `<tbody>`;
                str += `<tr><td colspan="6" style="background: #75b1a9; color: #f1f1f2" class="text-center font-weight-bold">No Data</td></tr>`
                str += `</tbody>`;
                str += `</table>`;
                $("#tablegpa").html(str);
            }
        }
    });
}

function findGpa(){
    var gpamonth = $("#gpamonth").children("option:selected").val();
    var gpayear = $("#gpayear").children("option:selected").val();
    var lecturid = $("#lecturid").children("option:selected").val();
    if(gpamonth !== '0' && gpayear !== '0' && lecturid !== '0'){
        findGpaOfMonth();
    }else if(gpamonth === '0' && gpayear !== '0' && lecturid !== '0'){
        findGpaOfYear();
    }else if(gpamonth === '0' && gpayear !== '0' && lecturid === '0'){
        findAllGpaLecturer();
    }else if(gpamonth === '0' && gpayear === '0' && lecturid === '0'){
        getDefaultGpa();
    }else{
        var str = '';
        str += `<table>`;
        str += `<table class="table table-vcenter card-table table-bordered">`;
        str += `<thead>
                    <tr>
                        <th style="width:350px; font-size: 20px">#</th>
                        <th>SUBJECT</th>
                        <th>STUDENTGROUP</th>
                        <th>GPA</th>
                        <th>DATE</th>
                    </tr>
                </thead>`;
        str += `<tbody>`;
        str += `<tr><td colspan="6" style="background: #75b1a9; color: #f1f1f2" class="text-center font-weight-bold">No Data</td></tr>`
        str += `</tbody>`;
        str += `</table>`;
        $("#tablegpa").html(str);
    }
}

function findAllGpaLecturer(){
    var year = $("#gpayear").children("option:selected").val();
    $.ajax({
        url: '/api/admin/teacher/allgpalecturerforyear',
        type: "GET",
        data: {
            gpayear: year
        },
        success: function (result) {
            console.log(result);
            if(result.length > 0){
                var arrayCopy = monthOfYear.slice();
                var month = '';
                for(i = 0; i < result.length; i++){
                    for(j = 0; j < arrayCopy.length; j++){
                        if(String(result[i].gpamonth) === arrayCopy[j].value){
                            month += String(result[i].gpamonth);
                        }
                    }
                }
                var str = '';
                str += `<table class="table table-vcenter card-table table-bordered">`;
                str += `<thead>
                            <tr>
                                <th style="width:350px;  font-size: 20px">`+year+`</th>
                                <th>LECTURER</th>
                                <th>SUBJECT</th>
                                <th>GPA</th>
                                <th>STUDENTGROUP</th>
                                <th>DATE</th>
                            </tr>
                        </thead>`;
                str += `<tbody>`;
                for(let i = 0; i < arrayCopy.length; i++){
                    for(let j = 0; j < result.length; j++){
                        if(arrayCopy[i].value === String(result[j].gpamonth)){
                            if(j === 0 || result[j].gpamonth !== result[j-1].gpamonth){
                                str += `<tr>
                                    <th style="background: #009f75; color: #f1f1f2; vertical-align:center" rowspan="`+result[j].maxmonth+`">`+arrayCopy[i].name+`</th>
                                    <td>&nbsp;&nbsp;`+result[j].fullnm+`</td>
                                    <td>&nbsp;&nbsp;`+result[j].subjnm+`</td>
                                    <td style="font-size: 15px" class="text-danger font-weight-bold">`+result[j].gpascore+`</td>
                                    <td>`+result[j].stugronm+`</td>
                                    <td>`+result[j].datefeedback+`</td>
                                </tr>`;
                            }else{
                                str += `<tr>
                                    <td>`+result[j].fullnm+`</td>
                                    <td>&nbsp;&nbsp;`+result[j].subjnm+`</td>
                                    <td style="font-size: 15px" class="text-danger font-weight-bold">`+result[j].gpascore+`</td>
                                    <td>`+result[j].stugronm+`</td>
                                    <td>`+result[j].datefeedback+`</td>
                                </tr>`;
                            }
                        }
                    }
                    if(month.indexOf(arrayCopy[i].value) == (-1)){
                        str += `<tr>
                                <th scope="rowgroup">`+arrayCopy[i].name+`</th>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                            </tr>`;
                    }
                }
                str += `</tbody>`;
                str += `</table>`;
                $("#tablegpa").html(str);
                console.log(month);
            }else{
                var str = '';
                str += `<table>`;
                str += `<table class="table table-vcenter card-table table-bordered">`;
                str += `<thead>
                            <tr>
                                <th style="width:350px; font-size: 50px">`+year+`</th>
                                <th>LECTURER</th>
                                <th>SUBJECT</th>
                                <th>STUDENTGROUP</th>
                                <th>GPA</th>
                                <th>DATE</th>
                            </tr>
                        </thead>`;
                str += `<tbody>`;
                str += `<tr><td colspan="6" style="background: #75b1a9; color: #f1f1f2" class="text-center font-weight-bold">No Data</td></tr>`
                str += `</tbody>`;
                str += `</table>`;
                $("#tablegpa").html(str);

            }
        }
    });
}

$(document).ready(function() {
    getDefaultGpa();
});

function getDefaultGpa(){
    var date = new Date();

    $.ajax({
        url: '/api/admin/teacher/allgpalecturerforyear',
        type: "GET",
        data: {
            gpayear: date.getFullYear()
        },
        success: function (result) {
            console.log(result);
            if(result.length > 0){
                var arrayCopy = monthOfYear.slice();
                var month = '';
                for(i = 0; i < result.length; i++){
                    for(j = 0; j < arrayCopy.length; j++){
                        if(String(result[i].gpamonth) === arrayCopy[j].value){
                            month += String(result[i].gpamonth);
                        }
                    }
                }
                var str = '';
                str += `<table class="table table-vcenter card-table table-bordered">`;
                str += `<thead>
                            <tr>
                                <th style="width:350px;  font-size: 20px">`+date.getFullYear()+`</th>
                                <th>LECTURER</th>
                                <th>SUBJECT</th>
                                <th>GPA</th>
                                <th>STUDENTGROUP</th>
                                <th>DATE</th>
                            </tr>
                        </thead>`;
                str += `<tbody>`;
                for(let i = 0; i < arrayCopy.length; i++){
                    for(let j = 0; j < result.length; j++){
                        if(arrayCopy[i].value === String(result[j].gpamonth)){
                            if(j === 0 || result[j].gpamonth !== result[j-1].gpamonth){
                                str += `<tr>
                                    <th style="background: #009f75; color: #f1f1f2; vertical-align:center" rowspan="`+result[j].maxmonth+`">`+arrayCopy[i].name+`</th>
                                    <td>&nbsp;&nbsp;`+result[j].fullnm+`</td>
                                    <td>&nbsp;&nbsp;`+result[j].subjnm+`</td>
                                    <td style="font-size: 15px" class="text-danger font-weight-bold">`+result[j].gpascore+`</td>
                                    <td>`+result[j].stugronm+`</td>
                                    <td>`+result[j].datefeedback+`</td>
                                </tr>`;
                            }else{
                                str += `<tr>
                                    <td>`+result[j].fullnm+`</td>
                                    <td>&nbsp;&nbsp;`+result[j].subjnm+`</td>
                                    <td style="font-size: 15px" class="text-danger font-weight-bold">`+result[j].gpascore+`</td>
                                    <td>`+result[j].stugronm+`</td>
                                    <td>`+result[j].datefeedback+`</td>
                                </tr>`;
                            }
                        }
                    }
                    if(month.indexOf(arrayCopy[i].value) == (-1)){
                        str += `<tr>
                                <th scope="rowgroup">`+arrayCopy[i].name+`</th>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                            </tr>`;
                    }
                }
                str += `</tbody>`;
                str += `</table>`;
                $("#tablegpa").html(str);
                console.log(month);
            }else{
                var str = '';
                str += `<table>`;
                str += `<table class="table table-vcenter card-table table-bordered">`;
                str += `<thead>
                            <tr>
                                <th style="width:350px; font-size: 20px">`+date.getFullYear()+`</th>
                                <th>LECTURER</th>
                                <th>SUBJECT</th>
                                <th>STUDENTGROUP</th>
                                <th>GPA</th>
                                <th>DATE</th>
                            </tr>
                        </thead>`;
                str += `<tbody>`;
                str += `<tr><td colspan="6" style="background: #75b1a9; color: #f1f1f2" class="text-center font-weight-bold">No Data</td></tr>`
                str += `</tbody>`;
                str += `</table>`;
                $("#tablegpa").html(str);

            }
        }
    });
}