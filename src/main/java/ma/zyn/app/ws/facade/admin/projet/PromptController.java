package ma.zyn.app.ws.facade.admin.projet;/*
package ma.zyn.app.ws.facade.admin.projet;


import ma.zyn.app.service.impl.admin.projet.PromptServiceImpl;
import ma.zyn.app.ws.dto.projet.ProjectFileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/prompt/")
public class PromptController {

    @Autowired
    private PromptServiceImpl promptService;

    @PostMapping("/")
    public String isDocumentIlligibleForExigence(@RequestBody ProjectFileDto projectFileDto) {
        String documentContent = projectFileDto.getDocumentContent();
        String exigence = projectFileDto.getExigence();
        return promptService.isDocumentIlligibleForExigence(documentContent, exigence);
    }







}
*/
