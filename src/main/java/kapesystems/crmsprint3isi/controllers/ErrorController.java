package kapesystems.crmsprint3isi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model, HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                //return "error-404";
                model.addAttribute("errorTitle", "Error 404");
                model.addAttribute("errorMsg", "No se ha encontrado el recurso en el servidor.");
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                //return "error-500";
                model.addAttribute("errorTitle", "Error 500");
                model.addAttribute("errorMsg", "Ha habido un problema en el servidor.");
            }
        }

        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
