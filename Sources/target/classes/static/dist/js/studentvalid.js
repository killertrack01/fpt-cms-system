const Fullname = document.querySelector('#fullname');
const Email = document.querySelector('#email');
const CollegeEmail = document.querySelector('#collegeemail');
const DoB = document.querySelector('#dob');
const Gender = document.querySelector('#gender');
const Address = document.querySelector('#address');
const MobilePhone = document.querySelector('#mobilephone');
const Avatar = document.querySelector('#avatar');

const ParentName = document.querySelector('#parentname');
const ParentJob = document.querySelector('#parentjob');
const ParentPhone = document.querySelector('#parentphone');
const ParentEmail = document.querySelector('#parentemail');
const ParentAddress = document.querySelector('#parentaddress');
const PlaceOfWork = document.querySelector('#placeofwork');

const IdCard = document.querySelector('#idcard');
const DateIssue = document.querySelector('#dateissue');
const PlaceIssue = document.querySelector('#placeissue');
const Major = document.querySelector('#major');


const form = document.querySelector('#createStudentForm');

const required = value => value === '' ? false : true;
const between = (length, min, max) => length < min || length > max ? false : true;
const idcardvalid = (idcard) => {
    const regex = /^[0-9]+$/;
    return regex.test(idcard);
};
const EmailValid = (email) => {
    const regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,3}))$/;
    return regex.test(email);
};
const NameValid = (name) => {
    const regex = /^(([a-zA-Z]+[ ]{1}|[a-zA-Z]+|[])+)$/;
    return regex.test(name);
};
const PhoneValid = (phone) => {
    const regex = /^(0)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]+$/;
    return regex.test(phone);
};
const PlaceValid = (place) => {
    const regex = /^(([a-zA-Z]{1,}[a-zA-Z0-9-&,]{0,}([ ]|[a-zA-Z0-9-&,]{0,}|[])[a-zA-Z0-9-&,]{0,})+)$/;
    return regex.test(place);
};
const DateValid = (date) => {
    const currentDate = new Date();
    const inputDate = new Date(date.value);
    if (inputDate.getFullYear() == currentDate.getFullYear()) {
        if (inputDate.getMonth() == currentDate.getMonth()) {
            if (inputDate.getDate() < currentDate.getDate()) {
                return true;
            } else {
                return false;
            }
        } else if (inputDate.getMonth() < currentDate.getMonth()) {
            return true;
        } else {
            return false;
        }
    } else if (inputDate.getFullYear() < currentDate.getFullYear()) {
        return true;
    } else {
        return false;
    }
};
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

function checkAgeValidDOB(date1){
    const startDate = new Date(date1.value);
    const currentDate = new Date();
    if(DateDiff.inDays(startDate, currentDate) >= 6570){
        return true;
    }else if(DateDiff.inMonths(startDate, currentDate) >= 216){
        return true;
    }else if(DateDiff.inYears(startDate, currentDate) >= 18){
        return true;
    }else{
        return false;
    }
}
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

// Validate Last name
const checkFullname = (full_name) => {
    let valid = false;
    const min = 2,
        max = 50;
    const fullname = full_name.value;

    if (!required(fullname)) {
        showError(full_name, 'Full Name cannot be blank !');
    } else if (!between(fullname.length, min, max)) {
        showError(full_name, 'Full Name must be between ' + min + ' and ' + max + ' characters');
    } else if (!NameValid(fullname)) {
        showError(full_name, 'Full Name must not have number or special character');
    } else {
        showSuccess(full_name);
        valid = true;
    }
    return valid;
}


// Validate Email
const checkEmail = (student_email) => {
    let valid = false;
    const email = student_email.value.trim();

    if (!required(email)) {
        showError(student_email, 'Email cannot be blank !');
    } else if (!EmailValid(email)) {
        showError(student_email, 'Email is invalid !');
    } else {
        showSuccess(student_email);
        valid = true;
    }
    return valid;
};
// Validate College Email
const checkCollegeEmail = (college_email) => {
    let valid = false;
    const collegeemail = college_email.value.trim();

    if (!required(collegeemail)) {
        showError(college_email, 'College email cannot be blank !');
    } else if (!EmailValid(collegeemail)) {
        showError(college_email, 'College email is invalid !');
    } else {
        showSuccess(college_email);
        valid = true;
    }
    return valid;
};
// Validate Day of birth
const checkDoB = (student_dob) => {
    let valid = false;
    const dob = student_dob.value;

    if (!required(dob)) {
        showError(student_dob, 'Day Of Birth cannot be blank !');
    } else if (!DateValid(student_dob)) {
        showError(student_dob, 'Day Of Birth cannot greater than today !');
    }else if (!checkAgeValidDOB(student_dob)) {
        showError(student_dob, 'Student must 18 year old or older !');
    } else {
        showSuccess(student_dob);
        valid = true;
    }
    return valid;
};
// Validate gender
const checkGender = (student_gender) => {
    let valid = false;
    const gender = student_gender.value;

    if (!required(gender)) {
        showError(student_gender, 'gender cannot be blank !');
    } else {
        showSuccess(student_gender);
        valid = true;
    }
    return valid;
};
// Validate Address
const checkAddress = (student_address) => {
    let valid = false;
    min = 0;
    max = 200;
    const address = student_address.value;

    if (!between(address.length, min, max)) {
        showError(student_address, 'Address can only contain ' + max + ' character');
    } else if (required(address) && between(address.length, min, max)) {
        showSuccess(student_address);
        valid = true;
    } else if (!required(address)) {
        showNormal(student_address);
        valid = true;
    }
    return valid;
};
// Validate Home Phone
const checkHomePhone = (student_homephone) => {
    let valid = false;
    const homephone = student_homephone.value;
    min = 10;
    max = 10;
    if (!required(homephone)) {
        showError(student_homephone, 'Home Phone cannot be blank !');
    } else if (!PhoneValid(homephone)) {
        showError(student_homephone, 'Home Phone is invalid !');
    } else if (!between(homephone.length, min, max)) {
        showError(student_homephone, 'Home Phone must be 10 number !');
    } else {
        showSuccess(student_homephone);
        valid = true;
    }
    return valid;
};
// Validate Mobile Phone
const checkMobilePhone = (student_mobilephone) => {
    let valid = false;
    const mobilephone = student_mobilephone.value;
    min = 10;
    max = 10;
    if (!required(mobilephone)) {
        showError(student_mobilephone, 'Mobile Phone cannot be blank !');
    } else if (!PhoneValid(mobilephone)) {
        showError(student_mobilephone, 'Mobile Phone is invalid !');
    } else if (!between(mobilephone.length, min, max)) {
        showError(student_mobilephone, 'Mobile Phone must be 10 number !');
    } else {
        showSuccess(student_mobilephone);
        valid = true;
    }
    return valid;
};


//validate Avatar
const checkAvatar = (student_avatar) => {
    let valid = false;
    const avatar = student_avatar.value;
    var allowedExtensions = /(\.jpg|\.jpeg|\.png)$/i;

    if (!allowedExtensions.exec(avatar) && !required(avatar)) {
        valid = true;
    } else if (!allowedExtensions.exec(avatar)) {
        student_avatar.value = '';
        showError(student_avatar, 'Avatar must be a .jpg, .png or .jpeg ');
    } else if (student_avatar.files[0].size > (1024 * 1000)) {
        showError(student_avatar, 'Avatar too large. Size cannot greater 1MB !');
    } else {
        showSuccess(student_avatar);
        valid = true;
    }
    return valid;
};

// Validate Parent name
const checkParentName = (parent_name) => {
    let valid = false;
    const parentname = parent_name.value;
    if (!required(parentname)) {
        showError(parent_name,'Parent name can not be blank');
    } else if (!NameValid(parentname)) {
        showError(parent_name, 'Parent name cannot containt number or special character !');
    }else if (!between(parentname.length,0,50)) {
        showError(parent_name, 'Parent name can only containt 50 character !');
    } else{
        showSuccess(parent_name);
        valid = true;
    }
    return valid;
};
// Validate Parent Job
const checkParentJob = (parent_job) => {
    let valid = false;
    const parentjob = parent_job.value;

    if (!required(parentjob)) {
        showNormal(parent_job);
        return true;
    }else if (!NameValid(parentjob)) {
        showError(parent_job, 'Parent job cannot containt number or special character !');
    }else if (!between(parentjob.length,0,50)) {
        showError(parent_job, 'Parent job can only containt 50 character !');
    } else if (required(parentjob) && NameValid(parentjob) && between(parentjob.length,0,50)) {
        showSuccess(parent_job);
        valid = true;
    }
    return valid;
};
// Validate Parent Phone
const checkParentPhone = (parent_phone) => {
    let valid = false;
    const parentphone = parent_phone.value;
    min = 10;
    max = 10;
    if (!required(parentphone)) {
        showError(parent_phone,"Parent phone can not be blank !");
    } else if (!PhoneValid(parentphone)) {
        showError(parent_phone, 'Parent Phone is invalid !');
    } else if (!between(parentphone.length, min, max)) {
        showError(parent_phone, 'Parent Phone must be 10 number !');
    } else {
        showSuccess(parent_phone);
        valid = true;
    }
    return valid;
};
// Validate Parent Email
const checkParentEmail = (parent_email) => {
    let valid = false;
    const parentemail = parent_email.value;

    if (!required(parentemail)) {
        showError(parent_email,'Parent email can not be blank !');
    } else if (!EmailValid(parentemail)) {
        showError(parent_email, 'Parent Email is invalid !');
    } else{
        showSuccess(parent_email);
        valid = true;
    }
    return valid;
};
// Validate Parent Address
const checkParentAddress = (parent_address) => {
    let valid = false;
    const parentaddress = parent_address.value;
    min = 0;
    max = 100;
    if (!required(parentaddress)) {
        showNormal(parent_address);
        valid = true;
    } else if (required(parentaddress) && between(parentaddress.length, min, max)) {
        showSuccess(parent_address);
        valid = true;
    } else if (!between(parentaddress.length, min, max)) {
        showError(parent_address, 'Parent Address can only contain ' + max + ' character');
    }
    return valid;
};
// Validate PlaceOfWork
const checkPlaceOfWork = (placeof_work) => {
    let valid = false;
    const placeofwork = placeof_work.value;

    min = 0;
    max = 100;
    if (!required(placeofwork)) {
        showNormal(placeof_work);
        valid = true;
    } else if (required(placeofwork) && between(placeofwork.length, min, max)) {
        showSuccess(placeof_work);
        valid = true;
    } else if (!between(placeofwork.length, min, max)) {
        showError(placeof_work, 'Place of work can only contain ' + max + ' character');
    }
    return valid;
};
// Validate IdCard
const checkIdCard = (id_card) => {
    let valid = false;
    const idcard = id_card.value;

    if (!required(idcard)) {
        showError(id_card, 'Idcard cannot be blank !');
    }else if (!idcardvalid(idcard)) {
        showError(id_card, 'Id card number can only contain number !');
    }else if (!between(idcard.length,9,9)) {
        showError(id_card, 'Id card number must have 9 character !');
    }else {
        showSuccess(id_card);
        valid = true;
    }
    return valid;
};
// Validate DateIssue
const checkDateIssue = (date_issue) => {
    let valid = false;
    const dateissue = date_issue.value;

    if (!required(dateissue)) {
        showError(date_issue, 'Date issue cannot be blank !');
    } else if (!DateValid(date_issue)) {
        showError(date_issue, 'Date issue cannot greater than today !');
    } else {
        showSuccess(date_issue);
        valid = true;
    }
    return valid;
};
// Validate PlaceIssue
const checkPlaceIssue = (place_issue) => {
    let valid = false;
    const placeissue = place_issue.value;

    if (!required(placeissue)) {
        showError(place_issue, 'Place issue cannot be blank !');
    }else if (!PlaceValid(placeissue)) {
        showError(place_issue, 'Place issue can not have special character !');
    } else {
        showSuccess(place_issue);
        valid = true;
    }
    return valid;
};
// Validate Major
const checkMajor = (student_major) => {
    let valid = false;
    const major = student_major.value;
    min = 0;
    max = 100;
    if (!required(major)) {
        showError(student_major, 'Major cannot be blank !');
    } else if (!between(major.length, min, max)) {
        showError(student_major, 'Major only containt ' + max + ' character');
    } else{
        showSuccess(student_major);
        valid = true;
    }
    return valid;
};

//Check reset password btn
const CheckResetPass=document.querySelector("#CheckResetPass");
CheckResetPass.addEventListener('click',function (e){

    if(CheckResetPass.checked==true){
        $("#ResetPass").val("123");
    }
    else if(CheckResetPass.checked==false){
        $("#ResetPass").val("");
    }
});


// Prevent the form submit
form.addEventListener('submit', function (e) {
    e.preventDefault();

    // validate form
    let isFullnameValid = checkFullname(Fullname),
        isEmailValid = checkEmail(Email),
        isCollegeEmailValid = checkCollegeEmail(CollegeEmail),
        isDoBValid = checkDoB(DoB),
        isGenderValid = checkGender(Gender),
        isAddressValid = checkAddress(Address),
        isMobilePhoneValid = checkMobilePhone(MobilePhone),
        isAvatarValid = checkAvatar(Avatar),
        isParentNameValid = checkParentName(ParentName),
        isParentJobValid = checkParentJob(ParentJob),
        isParentPhoneValid = checkParentPhone(ParentPhone),
        isParentEmailValid = checkParentEmail(ParentEmail),
        isParentAddressValid = checkParentAddress(ParentAddress),
        isPlaceOfWorkValid = checkPlaceOfWork(PlaceOfWork),
        isICardValid = checkIdCard(IdCard),
        isDateIssueValid = checkDateIssue(DateIssue),
        isPlaceIssueValid = checkPlaceIssue(PlaceIssue),
        isMajorValid = checkMajor(Major);

    let isFormValid =isFullnameValid && isEmailValid && isCollegeEmailValid && isDoBValid && isGenderValid  && isMobilePhoneValid && isAddressValid && isAvatarValid && isParentNameValid && isParentJobValid && isParentPhoneValid && isParentEmailValid && isParentAddressValid && isPlaceOfWorkValid && isICardValid && isDateIssueValid && isPlaceIssueValid && isMajorValid;

    // submit to the server if the form is valid
    if (isFormValid) {

        var formdata = new FormData(this);
        $.ajax({
            url: "/api/admin/students/postCreate",
            type: 'POST',
            enctype: 'multipart/form-data',
            dataType: 'json',
            data: formdata,
            cache: false,
            contentType: false,
            processData: false,
            success: function (result) {
                if (result.title === 'error') {
                    Swal.fire({
                        postition: 'top-end',
                        icon: result.title,
                        title: result.message,
                        showConfirmButton: true
                    });
                }
                else{
                    Swal.fire({
                        postition: 'top-end',
                        icon: result.title,
                        title: result.message,
                        showConfirmButton: false,
                        timer: 1500
                    });
                    setTimeout("location.reload(true);", 1500);
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
                //setTimeout("location.reload(true);", 1000);
            }
        });

    }
});
//validate Avatar for edit form
const checkEditAvatar = (student_avatar) => {
    let valid = false;
    const min = 3,
        max = 100;
    const avatar = student_avatar.value;
    var allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif)$/i;
    if (!required(avatar)) {
        valid = true;
    } else if (!allowedExtensions.exec(avatar)) {
        student_avatar.value = '';
        showError(student_avatar, 'Avatar must be a .jpg, .png or .jpeg ');
    } else if (student_avatar.files[0].size > (1024 * 1000)) {
        showError(student_avatar, 'Avatar too large. Size cannot greater 1MB !');
    } else {
        showSuccess(student_avatar);
        valid = true;
    }
    return valid;
};
//Trigger validation check
form.addEventListener('input', function (e) {
    switch (e.target.id) {
        case 'fullname':
            checkFullname(Fullname);
            break;
        case 'email':
            checkEmail(Email);
            break;
        case 'collegeemail':
            checkCollegeEmail(CollegeEmail);
            break;
        case 'dob':
            checkDoB(DoB);
            break;
        case 'gender':
            checkGender(Gender);
            break;
        case 'address':
            checkAddress(Address);
            break;
        case 'homephone':
            checkHomePhone(HomePhone);
            break;
        case 'mobilephone':
            checkMobilePhone(MobilePhone);
            break;
        case 'avatar':
            checkAvatar(Avatar);
            break;
        case 'parentname':
            checkParentName(ParentName);
            break;
        case 'parentjob':
            checkParentJob(ParentJob);
            break;
        case 'parentphone':
            checkParentPhone(ParentPhone);
            break;
        case 'parentemail':
            checkParentEmail(ParentEmail);
            break;
        case 'parentaddress':
            checkParentAddress(ParentAddress);
            break;
        case 'placeofwork':
            checkPlaceOfWork(PlaceOfWork);
            break;
        case 'idcard':
            checkIdCard(IdCard);
            break;
        case 'dateissue':
            checkDateIssue(DateIssue);
            break;
        case 'placeissue':
            checkPlaceIssue(PlaceIssue);
            break;
        case 'major':
            checkMajor(Major);
            break;
    }
});
//Show student information
function findStudent(elem) {
    var id = elem.querySelector('input').value;
    $.ajax({
        type: 'GET',
        url: '/api/admin/students/findStudentById/' + id,
        success: function (result) {
            $('#editStudentForm #editstudentid').val(id);
            $('#editStudentForm #editfullname').val(result.fullnm);
            $('#editStudentForm #editemail').val(result.email);
            $('#editStudentForm #editcollegeemail').val(result.collegeemail);
            $('#editStudentForm #editdob').val(result.dob);
            $('#editStudentForm #editgender').val(result.gender);
            $('#editStudentForm #editaddress').val(result.address);
            $('#editStudentForm #editmobilephone').val(result.mobphone);
            $('#editStudentForm #studentavatar').attr("src", "/dist/img/students/" + result.studentavatarname);


            $('#editStudentForm #editparentname').val(result.parnm);
            $('#editStudentForm #editparentjob').val(result.parjob);
            $('#editStudentForm #editparentphone').val(result.parphone);
            $('#editStudentForm #editparentemail').val(result.paremail);
            $('#editStudentForm #editparentaddress').val(result.paddress);
            $('#editStudentForm #editplaceofwork').val(result.pow);

            $('#editStudentForm #editidcard').val(result.idcard);
            $('#editStudentForm #editdateissue').val(result.doi);
            $('#editStudentForm #editplaceissue').val(result.poi);

            $('#editStudentForm #editmajor').val(result.major);
            $('#editStudentForm #ResetPass').val(result.studentPass);
        },
        error: function () {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Error',
                showConfirmButton: false,
                timer: 1000
            });
            //setTimeout("location.reload(true);", 1000);
        }
    });
}

const editFullname = document.querySelector('#editfullname');
const editEmail = document.querySelector('#editemail');
const editCollegeEmail = document.querySelector('#editcollegeemail');
const editDoB = document.querySelector('#editdob');
const editGender = document.querySelector('#editgender');
const editAddress = document.querySelector('#editaddress');
const editMobilePhone = document.querySelector('#editmobilephone');
const editAvatar = document.querySelector('#editavatar');

const editParentName = document.querySelector('#editparentname');
const editParentJob = document.querySelector('#editparentjob');
const editParentPhone = document.querySelector('#editparentphone');
const editParentEmail = document.querySelector('#editparentemail');
const editParentAddress = document.querySelector('#editparentaddress');
const editPlaceOfWork = document.querySelector('#editplaceofwork');

const editIdCard = document.querySelector('#editidcard');
const editDateIssue = document.querySelector('#editdateissue');
const editPlaceIssue = document.querySelector('#editplaceissue');
const editMajor = document.querySelector('#editmajor');

const editForm = document.querySelector('#editStudentForm');

editForm.addEventListener('submit', function (e) {
    e.preventDefault();
    // validate form
    let isFullnameValid = checkFullname(editFullname),
        isEmailValid = checkEmail(editEmail),
        isCollegeEmailValid = checkCollegeEmail(editCollegeEmail),
        isDoBValid = checkDoB(editDoB),
        isGenderValid = checkGender(editGender),
        isAddressValid = checkAddress(editAddress),
        isMobilePhoneValid = checkMobilePhone(editMobilePhone),
        isAvatarValid = checkAvatar(editAvatar),
        isParentNameValid = checkParentName(editParentName),
        isParentJobValid = checkParentJob(editParentJob),
        isParentPhoneValid = checkParentPhone(editParentPhone),
        isParentEmailValid = checkParentEmail(editParentEmail),
        isParentAddressValid = checkParentAddress(editParentAddress),
        isPlaceOfWorkValid = checkPlaceOfWork(editPlaceOfWork),
        isICardValid = checkIdCard(editIdCard),
        isDateIssueValid = checkDateIssue(editDateIssue),
        isPlaceIssueValid = checkPlaceIssue(editPlaceIssue),
        isMajorValid = checkMajor(editMajor);

    let isFormValid =isFullnameValid && isEmailValid && isCollegeEmailValid && isDoBValid && isGenderValid  && isMobilePhoneValid && isAddressValid && isAvatarValid && isParentNameValid && isParentJobValid && isParentPhoneValid && isParentEmailValid && isParentAddressValid && isPlaceOfWorkValid && isICardValid && isDateIssueValid && isPlaceIssueValid  && isMajorValid;

    // submit to the server if the form is valid
    if (isFormValid) {

        var formdata = new FormData(this);

        $.ajax({
            url: "/api/admin/students/postEdit",
            type: 'PUT',
            enctype: 'multipart/form-data',
            dataType: 'json',
            data: formdata,
            cache: false,
            contentType: false,
            processData: false,
            success: function (result) {
                if (result.title === 'error') {
                    Swal.fire({
                        postition: 'top-end',
                        icon: result.title,
                        title: result.message,
                        showConfirmButton: true
                    });
                }
                else{
                    Swal.fire({
                        postition: 'top-end',
                        icon: result.title,
                        title: result.message,
                        showConfirmButton: false,
                        timer: 1500
                    });
                    setTimeout("location.reload(true);", 1500);
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
                // setTimeout("location.reload(true);", 1000);
            }
        });

    }
});
editForm.addEventListener('input', function (e) {
    switch (e.target.id) {
        case 'editfullname':
            checkFullname(editFullname);
            break;
        case 'editemail':
            checkEmail(editEmail);
            break;
        case 'editcollegeemail':
            checkCollegeEmail(editCollegeEmail);
            break;
        case 'editdob':
            checkDoB(editDoB);
            break;
        case 'editgender':
            checkGender(editGender);
            break;
        case 'editaddress':
            checkAddress(editAddress);
            break;
        case 'edithomephone':
            checkHomePhone(editHomePhone);
            break;
        case 'editmobilephone':
            checkMobilePhone(editMobilePhone);
            break;
        case 'editavatar':
            checkAvatar(editAvatar);
            break;
        case 'editparentname':
            checkParentName(editParentName);
            break;
        case 'editparentjob':
            checkParentJob(editParentJob);
            break;
        case 'editparentphone':
            checkParentPhone(editParentPhone);
            break;
        case 'editparentemail':
            checkParentEmail(editParentEmail);
            break;
        case 'editparentaddress':
            checkParentAddress(editParentAddress);
            break;
        case 'editplaceofwork':
            checkPlaceOfWork(editPlaceOfWork);
            break;
        case 'editidcard':
            checkIdCard(editIdCard);
            break;
        case 'editdateissue':
            checkDateIssue(editDateIssue);
            break;
        case 'editplaceissue':
            checkPlaceIssue(editPlaceIssue);
            break;
        case 'editmajor':
            checkMajor(editMajor);
            break;
    }
});

function deleteStudent(elem) {
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
                type: 'PUT',
                url: '/api/admin/students/deletestudent/' + id,
                success: function () {
                    Swal.fire('Saved!', '', 'success');
                    setTimeout("location.reload(true);", 1000);
                },
                error: function () {
                    setTimeout("location.reload(true);", 1000);
                }

            })

        } else if (result.isDenied) {
            Swal.fire('Changes are not saved', '', 'info');
        }
    })
}