package swcho.mini.mvc.web.util;

import org.springframework.ui.Model;

public class ViewFragment {

    public static void setParameters(Model model, String fragmentPath, String fragmentName, String dmlType) {
        model.addAttribute("fragmentPath", fragmentPath);
        model.addAttribute("fragmentName", fragmentName);
        model.addAttribute("dmlType", dmlType);
    }
}