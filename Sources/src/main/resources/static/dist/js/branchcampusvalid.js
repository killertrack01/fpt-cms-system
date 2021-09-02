// Default function for check validation
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
// Check not null function 
const required = value => value === '' ? false : true;
// check input only string allow
const stringonly = /^(([a-zA-Z]+[ ]{1}|[a-zA-Z]+|[])+)$/;
//Check combo box different from 1
const check_option = (value) => {
    if (value == 0) {
        return false;
    } else {
        return true;
    }
}

// Get data for check validate for create
const create_bracnhcampusnm = document.querySelector("#create_BranchCampusName");
const create_branch = document.querySelector("#create_Branch");
const create_address = document.querySelector("#create_Address");
const create_location = document.querySelector("#create_Location");
// get Form id
const formcreate = document.querySelector("#newbranchcampus");
const formedit = document.querySelector("#editbranchcampus");
// check data input when create 
const check_create_branchcampusnm = () => {
    var rs = false;
    const branchcampusnm = create_bracnhcampusnm.value;
    if (!required(branchcampusnm)) {
        showError(create_bracnhcampusnm, "Campus Name can not blank!!!");
    } else {
        showSuccess(create_bracnhcampusnm);
        rs = true;
    }
    return true;
};
const check_create_branch = () => {
    var rs = false;
    const branch = create_branch.value;
    if (check_option(branch) == false) {
        showError(create_branch, "Must select branch name");
    } else {
        showSuccess(create_branch);
        rs = true;
    }
    return rs;
};
const check_create_address = () => {
    var rs = false;
    const address = create_address.value;
    if (!required(address)) {
        showError(create_address, "Address can not blank");
    } else {
        showSuccess(create_address);
        rs = true;
    }
    return rs;
};
const check_create_location = () => {
    var rs = false;
    const location = create_location.value;
    if (check_option(location) == false) {
        showError(create_location, "Must select location");
    } else {
        showSuccess(create_location);
        rs = true;
    }
    return rs;
};
// check when submit for create
formcreate.addEventListener('input', function(e) {
    switch (e.target.id) {
        // check campus name
        case ('create_BranchCampusName'):
            check_create_branchcampusnm();
            break;
        case ('create_Branch'):
            check_create_branch();
            break;
        case ('create_Address'):
            check_create_address();
            break;
        case ('create_Location'):
            check_create_location();
            break;
    }
});

formcreate.addEventListener('submit', function(e) {
    e.preventDefault();
    let is_branchcampusvalid = check_create_branchcampusnm();
    let is_branchvalid = check_create_branch();
    let is_addressvalid = check_create_address();
    let is_locationvalid = check_create_location();

    let checkvalid = is_branchcampusvalid && is_branchvalid && is_addressvalid && is_locationvalid;
    if (checkvalid == true) {
        var formdata = {
            branchcamnm: create_bracnhcampusnm.value.trim(),
            address: create_address.value.trim(),
            branchid: +create_branch.value,
            campusid: +create_location.value,
            removeat: 'No'
        }
        $.ajax({
            url: "/api/admin/branchcampus/Create",
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

//--------------//
//-----Edit-----//
//--------------//
// Get data for check validate when edit
const edit_bracnhcampusnm = document.querySelector("#edit_BranchCampusName");
const edit_branch = document.querySelector("#edit_Branch");
const edit_address = document.querySelector("#edit_Address");
const edit_location = document.querySelector("#edit_Location");


// Check data input when edit 
const check_edit_branchcampusnm = () => {
    var rs = false;
    const branchcampusnm = edit_bracnhcampusnm.value;
    if (!required(branchcampusnm)) {
        showError(edit_bracnhcampusnm, "Campus Name can not blank!!!");
    } else {
        showSuccess(edit_bracnhcampusnm);
        rs = true;
    }
    return true;
}

const check_edit_branch = () => {
    var rs = false;
    const branch = edit_branch.value;
    if (check_option(branch) == false) {
        showError(edit_branch, "Must select branch name");
    } else {
        showSuccess(edit_branch);
        rs = true;
    }
    return rs;
}

const check_edit_address = () => {
    var rs = false;
    const address = edit_address.value;
    if (!required(address)) {
        showError(edit_address, "Address can not blank");
    } else {
        showSuccess(edit_address);
        rs = true;
    }
    return rs;
}

const check_edit_location = () => {
    var rs = false;
    const location = edit_location.value;
    if (check_option(location) == false) {
        showError(edit_location, "Must select location");
    } else {
        showSuccess(edit_location);
        rs = true;
    }
    return rs;
}

// Function get id
function doUpdateBranchcampus(elem) {
    var branchcampus = elem.querySelector('input').value;
    $.ajax({
        type: 'GET',
        url: '/api/admin/branchcampus/Find/' + branchcampus,
        success: function(result) {
            $('#editbranchcampus #edit_idBranchCampus').val(result.branchcamid);
            $('#editbranchcampus #edit_BranchCampusName').val(result.branchcamnm);
            $('#editbranchcampus #edit_Branch').val(result.branchid);
            $('#editbranchcampus #edit_Address').val(result.address);
            $('#editbranchcampus #edit_Location').val(result.campusid);
        }
    });
}
// check submit for edit 
formedit.addEventListener('input', function(e) {
    switch (e.target.id) {
        // check campus name
        case ('edit_BranchCampusName'):
            check_edit_branchcampusnm();
            break;
        case ('edit_Branch'):
            check_edit_branch();
            break;
        case ('edit_Address'):
            check_edit_address();
            break;
        case ('edit_Location'):
            check_edit_location();
            break;
    }
});

formedit.addEventListener('submit', function(e) {
    e.preventDefault();
    let is_branchcampusvalid = check_edit_branchcampusnm();
    let is_branchvalid = check_edit_branch();
    let is_addressvalid = check_edit_address();
    let is_locationvalid = check_edit_location();

    let checkvalid = is_branchcampusvalid && is_branchvalid && is_addressvalid && is_locationvalid;
    if (checkvalid == true) {
        var formdata = {
            branchcamid: +document.querySelector("#edit_idBranchCampus").value,
            branchcamnm: edit_bracnhcampusnm.value.trim(),
            address: edit_address.value.trim(),
            branchid: +edit_branch.value,
            campusid: +edit_location.value,
            removeat: 'No'
        }
        $.ajax({
            url: "/api/admin/branchcampus/edit",
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

//delete function
function deletebranchcampus(elem) {
    var branchcampusid = elem.querySelector('input').value;
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
                type: 'PUT',
                url: '/api/admin/branchcampus/Delete/' + branchcampusid,
                success: function() {
                    Swal.fire('Saved!', '', 'success');
                    setTimeout("location.reload(true);", 1000);
                }
            })
        } else if (result.isDenied) {
            Swal.fire('Changes are not saved', '', 'info');
        }
    });
}