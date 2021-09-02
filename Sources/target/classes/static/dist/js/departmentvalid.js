const showError = (input, message) => {
    const formField = input.parentElement;
    input.classList.remove('is-valid');
    input.classList.add('is-invalid');
    input.focus();
    formField.classList.remove('success');
    formField.classList.add('error');

    // show message error with wrong input
    const error = formField.querySelector('span');
    error.textContent = message;
};

const showSuccess = (input) => {

    const formField = input.parentElement;
    input.classList.remove('is-invalid');
    input.classList.add('is-valid');
    formField.classList.remove('error');
    formField.classList.add('success');

    // hide message error
    const error = formField.querySelector('span');
    error.textContent = ''; // gán rỗng để ẩn message lỗi
};
// show normal
const showNormal = (input) => {

    const formField = input.parentElement;

    input.classList.remove('is-invalid');
    input.classList.remove('is-valid');

    formField.classList.remove('error');
    formField.classList.remove('success');

    // hide message error
    const error = formField.querySelector('span');
    error.textContent = ''; // assign null value to hide error message
};
//Check not null
const check_input = (value) => {
        if (value == '') {
            return false;
        } else {
            return true;
        }
    }
    //Check combo box different from 1 1
const check_option = (value) => {
    if (value == 1) {
        return false;
    } else {
        return true;
    }
}

//check with regex
const stringvalid = /^[a-zA-Z0-9\-]+$/;
const stringonly = /^(([a-zA-Z]+[ ]{1}|[a-zA-Z]+|[])+)$/;

//get object from input form create + update department
const create_departname = document.querySelector('#create_DepartName');
const create_description = document.querySelector('#create_Description');

const edit_departname = document.querySelector('#edit_DepartName');
const edit_description = document.querySelector('#edit_Description');

// CRUD depart form
const formdepart = document.querySelector('#newdepartment');
const editdepartbtn = document.querySelector('#editdepartbtn');
const formdepartedit = document.querySelector('#updateDepartment');
//------check field in form new department-----------
//check_name department
const check_create_departname = () => {
    var rs = false;
    const departname = create_departname.value;
    if (check_input(departname) == false) {
        showError(create_departname, "Department name can not blank");
    } else if (!(stringonly.test(departname))) {
        showError(create_departname, "Department name is invalid");
    } else {
        showSuccess(create_departname);
        rs = true;
    }
    return rs;
}

//check description name
const check_create_description = () => {
    var rs = false;
    const description = create_description.value;
    if (check_input(description) == false) {
        showNormal(create_description);
        rs = true;
    } else {
        showSuccess(create_description);
        rs = true;
    }
    return rs;
}

const check_edit_departname = () => {
    var rs = false;
    const departname = edit_departname.value;
    if (check_input(departname) == false) {
        showError(edit_departname, "Department name can not blank");
    } else if (!(stringvalid.test(departname))) {
        showError(edit_departname, "Department name is invalid");
    } else {
        showSuccess(edit_departname);
        rs = true;
    }
    return rs;
}

//check description name
const check_edit_description = () => {
    var rs = false;
    const description = edit_description.value;
    if (check_input(description) == false) {
        showNormal(edit_description);
        rs = true;
    } else {
        showSuccess(edit_description);
        rs = true;
    }
    return rs;
}

// Check input department
formdepart.addEventListener('input', function(e) {
    switch (e.target.id) {
        //check department name
        case ('create_DepartName'):
            check_create_departname();
            break;
        case ('create_Description'):
            check_create_description();
            break;
    }
});

// Create new department
formdepart.addEventListener('submit', function(e) {
    e.preventDefault();
    let is_departnamevalid = check_create_departname();
    let is_descriptionvalid = check_create_description();
    let check_valid = is_departnamevalid && is_descriptionvalid;
    if (check_valid == true) {
        var formdata = {
            depnm: create_departname.value,
            descriptions: create_description.value == '' ? 'No descriptions' : create_description.value,
            removeat: 'No',
        }
        $.ajax({
            url: "/api/admin/department/departmentCreate",
            type: 'POST',
            data: JSON.stringify(formdata),
            cache: false,
            contentType: 'application/json; charset=UTF-8',
            processData: false,
            success: function() {
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Your work has been saved',
                    showConfirmButton: false,
                    timer: 1500
                });
                setTimeout("location.reload(true);", 2000);
            },
            error: function() {
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Your work has been saved',
                    showConfirmButton: false,
                    timer: 1500
                });
                setTimeout("location.reload(true);", 2000);
            }
        });
    }
});

// Edit department
function doUpdateDepart(elem) {
    var departtid = elem.querySelector('input').value;
    $.ajax({
        type: 'get',
        url: '/api/admin/department/DepartmentFind/' + departtid,
        success: function(result) {
            $('#updateDepartment #edit_id').val(result.depid);
            $('#updateDepartment #edit_DepartName').val(result.depnm);
            $('#updateDepartment #edit_Description').val(result.descriptions);
        }
    });
}

formdepartedit.addEventListener('input', function(e) {
    switch (e.target.id) {
        //check department name
        case ('edit_DepartName'):
            check_edit_departname();
            break;
        case ('edit_Description'):
            check_edit_description();
            break;
    }
});

formdepartedit.addEventListener('submit', function(e) {
    e.preventDefault();
    const editid = document.querySelector('#edit_id');
    var formdata = {
        depid: editid.value,
        depnm: edit_departname.value,
        descriptions: edit_description.value == '' ? 'No descriptions' : edit_description.value,
        removeat: 'No',
    }
    $.ajax({
        url: '/api/admin/department/DepartmentEdit',
        type: 'PUT',
        data: JSON.stringify(formdata),
        cache: false,
        contentType: 'application/json; charset=UTF-8',
        processData: false,
        success: function() {
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Your work has been saved',
                showConfirmButton: false,
                timer: 1000
            });
            setTimeout("location.reload(true);", 1000);


        },
        error: function() {
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Your work has been saved',
                showConfirmButton: false,
                timer: 1500
            });
            setTimeout("location.reload(true);", 2000);
        }
    });
});

// delete department
function deletedepart(elem) {
    var departmentid = elem.querySelector('input').value;
    Swal.fire({
        title: 'Do you want to delete ?',
        showDenyButton: true,
        showCancelButton: false,
        confirmButtonText: `Yes`,
        denyButtonText: `No`,
    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            $.ajax({
                type: 'DELETE',
                url: '/api/admin/department/DepartmentDelete/' + departmentid,
                success: function() {
                    Swal.fire('Deleted!', '', 'success');
                    setTimeout("location.reload(true);", 1000);
                }
            })

        } else if (result.isDenied) {
            Swal.fire('Nothing have been deleted', '', 'info');
        }
    });

}