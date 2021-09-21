package swcho.mini.mvc.web.util;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ViewFragment {

    public static void setModelParameters(Model model, String fragmentPath, String fragmentName, String dmlType) {
        model.addAttribute("fragmentPath", fragmentPath);
        model.addAttribute("fragmentName", fragmentName);
        model.addAttribute("dmlType", dmlType);
    }

    public static void setRedirectParameters(RedirectAttributes redirectAttributes, String fragmentPath, String fragmentName, String dmlType) {
        redirectAttributes.addAttribute("fragmentPath", fragmentPath);
        redirectAttributes.addAttribute("fragmentName", fragmentName);
        redirectAttributes.addAttribute("dmlType", dmlType);
    }
}