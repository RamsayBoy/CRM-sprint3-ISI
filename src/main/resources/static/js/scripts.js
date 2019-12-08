(function () {
  'use strict';
  document.addEventListener('DOMContentLoaded', function () {
    var editForm = document.getElementById("editForm");
    editForm.addEventListener('submit', validateForm);

    function validateForm(event) {
        var clientName = editForm[0].value;
        var clientDate = editForm[2].value;

        var datePattern = /^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$/i;

        if(clientName == null || clientName === "") {
            alert("El nombre del cliente está vacío");
            event.preventDefault();
        }
        else if(clientDate == null || clientDate === "" || !datePattern.test(clientDate)) {
            alert("Formato de fecha no válido (dd/mm/yyyy)");
            event.preventDefault();
        }
    }
  });
})();