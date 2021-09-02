const RollnumEL = document.querySelector('#rollnum');
const funmEL = document.querySelector('#fullnm');
const dobEL = document.querySelector('#dob');
const idcardEL = document.querySelector('#idcard');
const genderEL = document.querySelector('#gender');
const emailEL = document.querySelector('#email');
const companyemailEl = document.querySelector('#companyemail');
const poiEL = document.querySelector('#poi');
const doiEL = document.querySelector('#doi');
const phoneEL = document.querySelector('#phone');
const imageEL = document.querySelector('#file');
const contractEL = document.querySelector('#contract');
const majorEL = document.querySelector('#major');
const typeEL = document.querySelector('#lecturertype');
const addrnmEL = document.querySelector('#address');
const Pass = document.querySelector('#lecturerPass');
const RePass = document.querySelector('#reLecturerPass');

const form = document.querySelector('#update_lecturer');

const required = value => value === '' ? false : true;
const between = (length, min, max) => length < min || length > max ? false : true;
const EmailValid = (email) => {
    const regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return regex.test(email);
};
const NameValid = (name) => {
    const regex = /^(([a-zA-Z]+[ ]{1}|[a-zA-Z]+|[])+)$/;
    return regex.test(name);
};

const NoSpecialCharacter = (name) =>{
    const regex = /^(([a-zA-Z0-9]+[ ]|[a-zA-Z0-9]+|[])+)$/;
    return regex.test(name);
};
const NumberValid = (number) =>{
    const regex = /^[0-9]+$/;
    return regex.test(number);
}
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

function checkAgeValidDOI(date1, date2){
    const startDate = new Date(date1.value);
    const currentDate = new Date(date2.value);
    if(DateDiff.inDays(startDate, currentDate) >= 5840){
        return true;
    }else if(DateDiff.inMonths(startDate, currentDate) >= 192){
        return true;
    }else if(DateDiff.inYears(startDate, currentDate) >= 16){
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


// Validate Major
const checkPass = () => {
    let valid = false;
    const pass = $('#lecturerPass').val();
    const repass = $('#reLecturerPass').val();
    if (!required(pass)) {
        showNormal(Pass);
        showNormal(RePass);
        valid = true;
    } else if (repass != pass) {
        showError(RePass, "Please enter the same password");
        showNormal(Pass);
        valid = false;
    } else if (repass == pass) {
        showSuccess(Pass);
        showSuccess(RePass);
        valid = true;
    } else if (required(pass)) {
        showSuccess(Pass);
        showSuccess(RePass);
        valid = true;
    }
    return valid;
};

// Validate Rollnum
const checkRollNum = () => {
    let valid = false;
    const min = 3, max = 30;
    const rollnum = RollnumEL.value.trim();

    if (!required(rollnum)) {
        showError(RollnumEL, 'RollNumber cannot be blank !');
    } else if (!between(rollnum.length, min, max)) {
        showError(RollnumEL, 'RollNumber must be between ' + min + ' and ' + max + 'characters');
    }else if(!NoSpecialCharacter(rollnum)){
        showError(RollnumEL, 'RollNumber cannot include special character');
    }else {
        showSuccess(RollnumEL);
        valid = true;
    }
    return valid;
};

// Validate Fullname
const checkFullname = () => {
    let valid = false;
    const min = 3, max = 30;
    const fullname = funmEL.value.trim();

    if (!required(fullname)) {
        showError(funmEL, 'Fullname cannot be blank !');
    } else if (!between(fullname.length, min, max)) {
        showError(funmEL, 'Fullname must be between ' + min + ' and ' + max + 'characters');
    } else if (!NameValid(fullname)) {
        showError(funmEL, 'Fullname must be a character and non digit !');
    } else {
        showSuccess(funmEL);
        valid = true;
    }
    return valid;
};

//Validate Dob
const checkDOB = () => {
    let valid = false;
    const dob = dobEL.value;
    if (!required(dob)) {
        showError(dobEL, "Date of Birth cannot be blank !");
    }else if(!checkAgeValidDOB(dobEL)){
        showError(dobEL, "Age must equal 18");
    }
    else {
        showSuccess(dobEL);
        valid = true;
    }
    return valid;
};

//Validate idCard
const checkIdcard = () => {
    let valid = false;
    const idcard = idcardEL.value.trim();
    const min = 6, max = 10;

    if (!required(idcard)) {
        showError(idcardEL, 'IdCard cannot be blank !');
    } else if (!between(idcard.length, min, max)) {
        showError(idcardEL, 'IdCard must be between ' + min + ' and ' + max + ' characters');
    }else if(!NumberValid(idcard)){
        showError(idcardEL, "IDCard must be number");
    } else {
        showSuccess(idcardEL);
        valid = true;
    }
    return valid;
};

// Validate Gender
const checkGender = () => {
    let valid = false;
    const gender = genderEL.value;
    if (!required(gender)) {
        showError(genderEL, 'Gender cannot be blank !');
    } else {
        showSuccess(genderEL);
        valid = true;
    }
    return valid;
};

// Validate Lecturer type
const checkLecturerType = () => {
    let valid = false;
    const type = typeEL.value.trim();
    if (!required(type)) {
        showError(typeEL, 'Gender cannot be blank !');
    } else {
        showSuccess(typeEL);
        valid = true;
    }
    return valid;
};

// Validate Email
const checkEmail = () => {
    let valid = false;
    const email = emailEL.value.trim();

    if (!required(email)) {
        showError(emailEL, 'Email cannot be blank !');
    } else if (!EmailValid(email)) {
        showError(emailEL, 'Email is invalid !');
    } else {
        showSuccess(emailEL);
        valid = true;
    }
    return valid;
};

// Validate CompanyEmail
const checkCompanyEmail = () => {
    let valid = false;
    const email = companyemailEl.value.trim();

    if (!required(email)) {
        showError(companyemailEl, 'CompanyEmail cannot be blank !');
    } else if (!EmailValid(email)) {
        showError(companyemailEl, 'CompanyEmail is invalid !');
    } else {
        showSuccess(companyemailEl);
        valid = true;
    }
    return valid;
};

//validate DateofIssue
const checkDoi = () => {
    let valid = false;
    const doi = doiEL.value.trim();
    if (!required(doi)) {
        showError(doiEL, "DateOfIssue cannot be blank !");
    }else if(!checkAgeValidDOI(dobEL, doiEL)){
        showError(doiEL, "Age must equal 16");
    }
    else {
        showSuccess(doiEL);
        valid = true;
    }
    return valid;
};

// Validate PlaceOfIssue
const checkPoi = () => {
    let valid = false;
    const poi = poiEL.value.trim();
    const min = 6, max = 30;

    if (!required(poi)) {
        showError(poiEL, 'Place Of Issue cannot be blank !');
    } else if (!between(poi.length, min, max)) {
        showError(poiEL, 'Place Of Issue must be between ' + min + ' and ' + max + ' characters');
    }else if(!NoSpecialCharacter(poi)){
        showError(poiEL, 'Place Of Isse cannot include special character');
    } else {
        showSuccess(poiEL);
        valid = true;
    }
    return valid;
};

// Validate Phone
const checkPhone = () => {
    let valid = false;
    const min = 10, max = 11;
    const phone = phoneEL.value;
    const isVNPhoneMobile = /^(0|\+84)(\s|\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\d)(\s|\.)?(\d{3})(\s|\.)?(\d{3})$/;

    if (!required(phone)) {
        showError(phoneEL, 'Phonenumber cannot be blank !');
    } else if (!between(phone.length, min, max)) {
        showError(phoneEL, 'Phonenumber must be between ' + min + ' and ' + max + 'characters');
    } else if (!isVNPhoneMobile.test(phone)) {
        showError(phoneEL, 'Phonenumber invalid. Pls try-again !');
    } else {
        showSuccess(phoneEL);
        valid = true;
    }
    return valid;
};

//validate address
const checkAddrnm = () => {
    let valid = false;
    const min = 10, max = 100;
    const addrnm = addrnmEL.value.trim();

    if (!required(addrnm)) {
        showError(addrnmEL, 'Address cannot be blank !');
    } else if (!between(addrnm.length, min, max)) {
        showError(addrnmEL, 'Address must be between ' + min + ' and ' + max + 'characters');
    } else {
        showSuccess(addrnmEL);
        valid = true;
    }
    return valid;
};

//validate major
const checkMajor = () => {
    let valid = false;
    const min = 6, max = 100;
    const major = majorEL.value.trim();

    if (!required(major)) {
        showError(majorEL, 'Contract cannot be blank !');
    } else if (!between(major.length, min, max)) {
        showError(majorEL, 'Contract must be between ' + min + ' and ' + max + 'characters');
    } else {
        showSuccess(majorEL);
        valid = true;
    }
    return valid;
};

// Validate Contract
const checkContract = () => {
    let valid = false;
    const min = 3, max = 30;
    const contract = contractEL.value.trim();

    if (!required(contract)) {
        showError(contractEL, 'Contract cannot be blank !');
    } else if (!between(contract.length, min, max)) {
        showError(contractEL, 'Contract must be between ' + min + ' and ' + max + 'characters');
    }else if(!NoSpecialCharacter(contract)){
        showError(contractEL, 'Contract cannot include special character');
    }
    else {
        showSuccess(contractEL);
        valid = true;
    }
    return valid;
};

// Validate Img
const checkImage = () => {
    let valid = false;
    const min = 3, max = 100;
    const image = imageEL.value;
    var allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif)$/i;

    if (!required(image)) {
        valid = true;
    } else if (!allowedExtensions.exec(image)) {
        imageEL.value = '';
        showError(imageEL, 'Image must be a .jpg, .png, .jpeg or .gif');
    } else if (imageEL.files[0].size > (1024 * 10000)) {
        showError(imageEL, 'Image too large. Size cannot greater 100KB !');
    } else {
        showSuccess(imageEL);
        valid = true;
    }
    return valid;
};
// Prevent the form submit
form.addEventListener('submit', function (e) {
    e.preventDefault();

    // validate form
    let isFunmValid = checkFullname(),
        isRollnumValid = checkRollNum(),
        isPoiValid = checkPoi(),
        isDobValid = checkDOB(),
        isIdcardValid = checkIdcard(),
        isGenderValid = checkGender(),
        isEmailValid = checkEmail(),
        isCompanyEmailValid = checkCompanyEmail(),
        isDoiValid = checkDoi(),
        isPhoneValid = checkPhone(),
        isTypeValid = checkLecturerType(),
        isAddrnmValid = checkAddrnm(),
        isContractValid = checkContract(),
        isMajorValid = checkMajor(),
        isImageValid = checkImage();
    isResetPassValid = checkPass();

    let isFormValid = isRollnumValid && isResetPassValid && isTypeValid && isPoiValid && isFunmValid && isDobValid && isIdcardValid && isEmailValid && isCompanyEmailValid && isDoiValid
        && isPhoneValid && isAddrnmValid && isContractValid && isMajorValid && isImageValid && isGenderValid;

    // submit to the server if the form is valid
    if (isFormValid) {
        var formData = new FormData(this);
        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            url: '/api/admin/teacher/postupdate',
            data: formData,
            dataType: 'json',
            cache: false,
            processData: false,
            contentType: false,
            success: function (result) {
                if (result.title === 'error') {
                    Swal.fire({
                        postition: 'top-end',
                        icon: result.title,
                        title: result.message,
                        showConfirmButton: true
                    });
                } else {
                    Swal.fire({
                        postition: 'top-end',
                        icon: result.title,
                        title: result.message,
                        showConfirmButton: false,
                        timer: 1000
                    });
                    setTimeout("location.reload(true);", 1000);
                }
            }
        });
    }
});

const deleteLecturer = (elem) => {
    var id = elem.querySelector('input').value;
    Swal.fire({
        title: 'Do you want to delete ?',
        showDenyButton: true,
        confirmButtonText: "Yes",
        denyButtonText: "No"
    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            $.ajax({
                type: 'POST',
                url: '/api/admin/teacher/delete/' + id,
                success: function () {
                    Swal.fire('Saved!', '', 'success');
                    setTimeout("location.reload(true);", 1000);
                }
            });

        } else if (result.isDenied) {
            Swal.fire('Changes are not saved', '', 'info');
        }
    });
};

function getEdit() {
    var staffid = $('#lecturer_id').val();

    //up data to modal
    $.ajax({
        type: 'get',
        url: '/api/teacher/getupdate/' + staffid,
        success: function (data) {
            $('#staff_id').val(data.staffid);
            $('#login').val(data.login);
            $('#full_name').val(data.fullname);
            $('#email').val(data.email);
            $('#phone').val(data.phone);
            $('#department').val(data.department);
            $('#contract').val(data.contract);
            $('#manager').val(data.manager);
            $('#image').val('/dist/img/tachers/' + data.image);
        }
    });
}


form.addEventListener('input', function (e) {
    switch (e.target.id) {
        case 'rollnum':
            checkRollNum();
            break;
        case 'fullnm':
            checkFullname();
            break;
        case 'dob':
            checkDOB();
            break;
        case 'idcard':
            checkIdcard();
            break;
        case 'gender':
            checkGender();
            break;
        case 'email':
            checkEmail();
            break;
        case 'companyemail':
            checkCompanyEmail();
            break;
        case 'poi':
            checkPoi();
            break;
        case 'doi':
            checkDoi();
            break;
        case 'phone':
            checkPhone();
            break;
        case 'lecturertype':
            checkLecturerType();
            break;
        case 'address':
            checkAddrnm();
            break;
        case 'contract':
            checkContract();
            break;
        case 'major':
            checkMajor();
            break;
        case 'file':
            checkImage();
            break;
        case 'reLecturerPass':
            checkPass();
            break;
    }
});

const DateValid = (date) => {
    const currentDate = new Date();
    const inputDate = new Date(date.value);
    if (inputDate.getFullYear() === currentDate.getFullYear()) {
        if (inputDate.getMonth() === currentDate.getMonth()) {
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

const findLecturer = (elem) => {
    var id = elem.querySelector('input').value;
    //up data to modal
    $.ajax({
        type: 'get',
        url: '/api/admin/teacher/findlecturer/' + id,
        success: function (result) {
            $('#idsendmailprofile').val(result.id);
        }
    });
};

const formSendmail = document.querySelector('#sendEmail');
formSendmail.addEventListener('submit', function (e) {
    e.preventDefault();

    var formData = new FormData(this);
    $.ajax({
        type: 'POST',
        enctype: 'multipart/form-data',
        url: '/api/admin/teacher/sendmailLecturer',
        data: formData,
        dataType: 'json',
        cache: false,
        processData: false,
        contentType: false,
        beforeSend: function () {
            $("#loading").show();
        },
        complete: function () {
            $("#loading").hide();
        },
        success: function (result) {
            if (result.title === 'error') {
                Swal.fire({
                    postition: 'top-end',
                    icon: result.title,
                    title: result.message,
                    showConfirmButton: true
                });
            } else {
                Swal.fire({
                    postition: 'top-end',
                    icon: result.title,
                    title: result.message,
                    showConfirmButton: false,
                    timer: 1000
                });
                setTimeout("location.reload(true);", 1000);
            }
        }
    });
});