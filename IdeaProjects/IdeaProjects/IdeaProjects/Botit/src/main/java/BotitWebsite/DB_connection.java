package BotitWebsite;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.openqa.selenium.WebDriver;

public class DB_connection {
    WebDriver driver;
    public void DB_connection(){
        MongoClient mongoClient = MongoClients.create("mongodb+srv://transmission_dev:K1IPfykYMq6FAUv6@botit-dev.jwtve.mongodb.net/botitdev?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("botitdev");
    }
}
