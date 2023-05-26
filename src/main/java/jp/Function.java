package jp;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;
import io.github.cdimascio.dotenv.Dotenv;


/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    private static final Dotenv dotenv;
    private static final String cognitiveSearchQueryKey;
    static{
        dotenv = Dotenv
        .configure()
        .ignoreIfMalformed()
        .ignoreIfMissing()
        .load();
        cognitiveSearchQueryKey = dotenv.get("cognitiveSearchQueryKey");
    }
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("HttpExample")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) throws Exception{
        context.getLogger().info("Java HTTP trigger processed a request.");
        return request.createResponseBuilder(HttpStatus.OK).header("Access-Control-Allow-Origin", "*").body(String.format("{\"key\":\"%s\"}", cognitiveSearchQueryKey )).build();
    }
}
 