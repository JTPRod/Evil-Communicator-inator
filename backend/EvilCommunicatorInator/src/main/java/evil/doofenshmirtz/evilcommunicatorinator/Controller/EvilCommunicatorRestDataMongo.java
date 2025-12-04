package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Filters.*;
import evil.doofenshmirtz.evilcommunicatorinator.Models.Message;
import evil.doofenshmirtz.evilcommunicatorinator.Models.User;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class EvilCommunicatorRestDataMongo {
    private static final CodecRegistry pojoRegistry = fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build())
    );
    private static final MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(Settings.dbURL))
            .codecRegistry(pojoRegistry).build();

    // Messages
    public static String addMessage(Message message){
        try (MongoClient mongo = MongoClients.create(settings)) {
            MongoDatabase database = mongo.getDatabase("EvilCommunicatorInator");
            MongoCollection<Message> collection = database.getCollection("Messages", Message.class);
            collection.insertOne(message);
            return "Message Added Successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Message Failed to Add";
        }

    }

    // find all hardcoded limit: max_messages
    public static ArrayList<Message> getAllMessages(){
        int max_messages = 100;
        try (MongoClient mongo = MongoClients.create(settings)) {
            MongoDatabase database = mongo.getDatabase("EvilCommunicatorInator");
            MongoCollection<Message> collection = database.getCollection("Messages", Message.class);
            return collection.find().limit(max_messages).into(new ArrayList<Message>());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Message getMessageById(ObjectId id){
        try (MongoClient mongo = MongoClients.create(settings)) {
            MongoDatabase database = mongo.getDatabase("EvilCommunicatorInator");
            MongoCollection<Message> collection = database.getCollection("Messages", Message.class);
            Bson filter = Filters.eq("_id", id);
            return collection.find(filter).first();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // implementation not required
    public static Message updateMessage(Message message){
        return null;
    }

    public static ArrayList<Message> getAllMessagesByUserId(ObjectId userId){
        try (MongoClient mongo = MongoClients.create(settings)) {
            MongoDatabase database = mongo.getDatabase("EvilCommunicatorInator");
            MongoCollection<Message> collection = database.getCollection("Messages", Message.class);
            Bson filter = Filters.eq("user_id", userId);
            return collection.find(filter).into(new ArrayList<Message>());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String deleteMessageById(ObjectId id){
        try (MongoClient mongo = MongoClients.create(settings)) {
            MongoDatabase database = mongo.getDatabase("EvilCommunicatorInator");
            MongoCollection<Message> collection = database.getCollection("Messages", Message.class);
            Bson filter = Filters.eq("_id", id);
            collection.deleteOne(filter);
            return "Message Deleted Successfully !";
        } catch (Exception e) {
            e.printStackTrace();
            return "Message Deletion Failed !";
        }
    }

    // TODO: return http response instead of string
    public static Map<String, Object> addUser(User user) {
        //check for duplicate usernames/username criteria
            Map<String, Object> userValidity = User.checkUsername(user.getUsername());
        if ("error".equals(userValidity.get("status"))) {
            return userValidity;
        } else {
            getUserByUsername(user.getUsername());
            if (getUserByUsername(user.getUsername()) != null) {
                return Map.of(
                        "status", "error",
                        "message", "Username taken, Please try again!"
                );
            } else {
                user.setUsername(user.getUsername());
            }
        }
        //check password
            Map<String, Object> strength = User.checkPasswordStrength(user.getPassword());

            if ("error".equals(strength.get("status"))) {
                return strength;
        } else {
            user.setPassword(User.hashPassword(user.getPassword()));
            try (MongoClient mongo = MongoClients.create(settings)) {
                MongoDatabase database = mongo.getDatabase("EvilCommunicatorInator");
                MongoCollection<User> collection = database.getCollection("Users", User.class);
                collection.insertOne(user);
                return Map.of(
                        "status", "success",
                        "message", "User successfully added"
                );
            } catch (Exception e) {
                e.printStackTrace();
                return Map.of(
                        "status", "error",
                        "message", "User failed to add"
                );
            }
        }
    }

    // MARKED AS PRIVATE FOR ACCESS CONTROL REASONS
    private static ArrayList<User> getAllUsers(){
        return null;
    }

    // MARKED AS PRIVATE FOR ACCESS CONTROL REASONS
    private static ArrayList<User> getAllUsersById(ObjectId userId){
        return null;
    }

    public static User getUserByUsername(String username){
        try (MongoClient mongo = MongoClients.create(settings)) {
            MongoDatabase database = mongo.getDatabase("EvilCommunicatorInator");
            MongoCollection<User> collection = database.getCollection("Users", User.class);
            Bson filter = Filters.eq("username", username);
            return collection.find(filter).first();
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User getUserById(ObjectId id){
        try (MongoClient mongo = MongoClients.create(settings)) {
            MongoDatabase database = mongo.getDatabase("EvilCommunicatorInator");
            MongoCollection<User> collection = database.getCollection("Users", User.class);
            Bson filter = Filters.eq("_id", id);
            return collection.find(filter).first();
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, Object> authenticate(String username, String rawPassword){
        User  user = getUserByUsername(username);
        if (user == null){
            return Map.of(
                    "status", "error",
                    "message", "Invalid username or password."
            );
        } else if (!User.validatePassword(rawPassword, user.getPassword())) {
            return Map.of(
                    "status", "error",
                    "message", "Invalid username or password."
            );
        }

        return Map.of(
                "status", "success",
                "user_id", user.getUser_id().toHexString(),
                "message", "Login successful. Welcome, " + user.getUsername()
        );
    }

    public static String deleteUserById(ObjectId id){
        try (MongoClient mongo = MongoClients.create(settings)) {
            MongoDatabase database = mongo.getDatabase("EvilCommunicatorInator");
            MongoCollection<User> collection = database.getCollection("Users", User.class);
            Bson filter = Filters.eq("_id", id);
            collection.deleteOne(filter);
            return "User Deleted Successfully !";
        } catch (Exception e) {
            e.printStackTrace();
            return "User Deletion Failed !";
        }
    }

}
