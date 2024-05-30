import 'remixicon/fonts/remixicon.css'
document.addEventListener('DOMContentLoaded', function () {
    var profileLink = document.getElementById('profileLink');
    var offcanvasProfile = new bootstrap.Offcanvas(document.getElementById('offcanvasProfile'));

    profileLink.addEventListener('mouseenter', function () {
        offcanvasProfile.show();
    });

    profileLink.addEventListener('mouseleave', function () {
        setTimeout(function () {
            if (!document.querySelector('.offcanvas.show')) {
                offcanvasProfile.hide();
            }
        }, 200);
    });

    document.getElementById('offcanvasProfile').addEventListener('mouseenter', function () {
        clearTimeout();
    });

    document.getElementById('offcanvasProfile').addEventListener('mouseleave', function () {
        offcanvasProfile.hide();
    });
});