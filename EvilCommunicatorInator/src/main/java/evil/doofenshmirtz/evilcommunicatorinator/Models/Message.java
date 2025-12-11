package evil.doofenshmirtz.evilcommunicatorinator.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Message implements Serializable {
    @BsonId
    @JsonProperty("message_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId message_id;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId user_id;
    private String content;
    private static final Set<String> swears = new HashSet<>();
    private static final Set<String> allowlist = new HashSet<>();

    public Message() {}

    public Message(String content, ObjectId user_id) {
        this.content = content;
        this.user_id = user_id;
    }

    public static void initialiseProfanity() {
        try {
            URI url = new URI("https://raw.githubusercontent.com/censor-text/profanity-list/refs/heads/main/list/en.txt");
            Scanner scanner = new Scanner(url.toURL().openStream());
            while (scanner.hasNextLine()) {
                swears.add(scanner.nextLine());
            }

            URI allowUrl = new URI("https://raw.githubusercontent.com/duckian-code/Profanity-Exceptions-Allowlist/refs/heads/main/en-allowlist.txt");
            scanner = new Scanner(allowUrl.toURL().openStream());
            while (scanner.hasNextLine()) {
                allowlist.add(scanner.nextLine());
            }
        } catch (IOException io){
            System.out.println("Scanner failed. Skipping profanity check...");
            System.out.println(io.getMessage());
        } catch (URISyntaxException e) {
            System.out.println("Problem opening URL. Skipping profanity check...");
            System.out.println(e.getMessage());
        }
    }

    private static String undoLeet(String word) {
        return word.replace('0', 'o')
                .replace('1', 'i')
                .replace('3', 'e')
                .replace('4', 'a')
                .replace('5', 's')
                .replace('7', 't')
                .replace('@', 'a')
                .replace('$', 's')
                .replace('(', 'c');
    }

    // will censor word even if it is conjoined without obfuscating the entire string
    // note that filtering conjoined words increases processing time by a factor of 1.5-2
    // current processing time is 10k words in ~570 ms
    private static String censor(String cleaned, String original) {
        boolean[] mask = new boolean[cleaned.length()];

        for (String swear : swears) {
            int idx = cleaned.indexOf(swear);
            while (idx != -1) {
                for (int i = 0; i < swear.length(); i++) {
                    mask[idx + i] = true;
                }
                idx = cleaned.indexOf(swear, idx + 1);
            }
        }

        StringBuilder out = new StringBuilder(original.length());
        for (int i = 0; i < original.length(); i++) {
            if (mask[i]) out.append("♥");
            else out.append(original.charAt(i));
        }

        return out.toString();
    }

    /**
     * Takes in message content, tokenizes it into words and punctuation, censors detected swears
     * Filter fully supports leet speak and conjoined words
     * @param content Raw message content to be censored
     * @return Censored message content
     */
    public static String filterProfanity(String content) {
        long start = System.currentTimeMillis();
        Pattern regex = Pattern.compile("([a-zA-Z0-9@$(]+)|([^a-zA-Z0-9@$(])");
        Matcher matcher = regex.matcher(content);
        StringBuilder sb = new StringBuilder();

        while  (matcher.find()) {
            if (matcher.group(1) != null) {
                String original =  matcher.group(1);
                String cleaned = undoLeet(original.toLowerCase());

                // Append original immediately if in list of allowed words
                if (allowlist.contains(cleaned)) {
                    sb.append(original);
                } else {
                    sb.append(censor(cleaned, original));
                }
            } else if (matcher.group(2) != null) {
                sb.append(matcher.group(2));
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Message filtered in " +  (end - start) + "ms !");
        return sb.toString();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ObjectId getUser_id() {
        return user_id;
    }

    public void setUser_id(ObjectId user_id) {
        this.user_id = user_id;
    }

    public ObjectId getMessage_id() {
        return message_id;
    }

    public void setMessage_id(ObjectId message_id) {
        this.message_id = message_id;
    }
}
