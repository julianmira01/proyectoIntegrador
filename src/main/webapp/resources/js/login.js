function handleLoginRequest(xhr, status, args) {
    if (args.validationFailed || !args.loggedIn) {
        jQuery('#frmLogin').effect("shake", {times: 5}, 100);
    }
    else {
        location.href = args.ruta;
    }
}


