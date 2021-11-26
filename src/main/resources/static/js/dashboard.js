$(document).ready(function() {
    $('#filebutton').change(function () {
        showImageThumbnail(this);
    });
});

function showImageThumbnail(fileInput) {
    file = fileInput.file[0];
    reader = new FileReader();

    reader.onload = function (e) {
        $('#thumbnail').attr('src', e.target.result);
    };

    reader.readAsDataURL(file);
}

