package run.mone.mcp.chat.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Flux;

import org.springframework.stereotype.Component;
import run.mone.hive.mcp.spec.McpSchema;
import run.mone.mcp.chat.service.ChatService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ChatFunction implements Function<Map<String, Object>, Flux<McpSchema.CallToolResult>> {

    private final ChatService chatService;

    private static final String TOOL_SCHEMA = """
            {
                "type": "object",
                "properties": {
                    "message": {
                        "type": "string",
                        "description": "The message content from user"
                    },
                    "context": {
                        "type": "string",
                        "description": "Previous chat history"
                    }
                },
                "required": ["message"]
            }
            """;

    @Override
    public Flux<McpSchema.CallToolResult> apply(Map<String, Object> arguments) {
        String message = (String) arguments.get("message");
        String context = (String) arguments.get("context");
        if (StringUtils.isEmpty(context)) {
            context = "";
        }
        try {
            Flux<String> result = chatService.chat(message, context);
            return result.map(res -> new McpSchema.CallToolResult(List.of(new McpSchema.TextContent(res)), false));
        } catch (Exception e) {
            return Flux.just(new McpSchema.CallToolResult(List.of(new McpSchema.TextContent("Error: " + e.getMessage())), true));
        }
    }

    public String getName() {
        return "stream_minzai_chat";
    }

    public String getDesc() {
        return "和minzai聊天，问问minzai问题。支持各种形式如：'minzai'、'请minzai告诉我'、'让minzai帮我看看'、'minzai你知道吗'等。支持上下文连续对话。";
    }

    public String getToolScheme() {
        return TOOL_SCHEMA;
    }
}