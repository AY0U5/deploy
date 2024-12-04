package ma.zyn.app.service.impl.admin.projet;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AIService {

    private static final String OPENAI_API_KEY = "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";
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/completions";

    public String getAIResponse(String prompt) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(OPENAI_API_URL);
            post.setHeader("Authorization", "Bearer " + OPENAI_API_KEY);
            post.setHeader("Content-Type", "application/json");

            String json = "{"
                    + "\"model\":\"text-davinci-003\","
                    + "\"prompt\":\"" + prompt + "\","
                    + "\"max_tokens\":100"
                    + "}";

            StringEntity entity = new StringEntity(json);
            post.setEntity(entity);

            CloseableHttpResponse response = client.execute(post);
            String responseBody = EntityUtils.toString(response.getEntity());
            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred while processing the request.";
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
