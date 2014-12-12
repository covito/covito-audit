package org.covito.audit.demo.action;


import org.covito.audit.api.annotation.Audit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class RestController {

    @RequestMapping(value = "/json/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Audit(action="REST", actResolver="REST_RESOLVER", resResolver="REST_RESOURCE_RESOLVER")
    public String getJsonRestObject(@PathVariable Integer id) {
        return id+"";
    }

}
