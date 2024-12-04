package ma.zyn.app.service.impl.admin.projet;/*
package ma.zyn.app.service.impl.admin.projet;

import ma.zyn.app.service.facade.admin.projet.PromptService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


@Service
public class PromptServiceImpl implements PromptService {


    private final ChatClient chatClient;
    @Value("classpath:/prompts/prompt.st")
    private Resource exigencePromptResource;

    public PromptServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }


    public String isDocumentIlligibleForExigence(String myDocument, String exigence) {
        return chatClient.prompt()
                .user(u -> u.text(exigencePromptResource).param("exigence",exigence).param("texte",myDocument))
                .call()
                .content();


    }


}

*/
