# **6. Configuration**

This package contains 4 items, which include a Java class file, a properties file, and 2 txt files. The Java class is named Configuration, the properties file is named config.properties, and the txt files are named banner.txt and welcome_message respectively;

<br>

## **1. Configuration Java class**

The purpose of this Java class is to load and provide access to configuration properties from the previously mentioned properties file named config.properties.

<br>

### **Import Statements**

```java
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
```

The above import statements are declared to read data from a file in a byte-oriented manner, to throw an exception class **'IOException'** that is thrown when an I/O operation encounters an error or fails, and to import a **Properties** class that represents a persistent set of properties (key-value pairs).

<br>

### **Constants Declaration**

The class contains the following constants :

```java
public static final String PARENT_PATH = "src/main/java/com/assignment/suzume/configuration/";
public static final String CONFIG_FILE = PARENT_PATH + "config.properties";
```

1. **PARENT_PATH** specifies the parent path of the configuration file.
2. **CONFIG_FILE** specifies the full path of the configuration file, which is derived by appending "config.properties" to the parent path.

<br>

### **Static Variable Declaration**

The class also declares the following static variables :

```java
public static final Properties properties = loadProperties();
public static final String BANNER_URL = properties.getProperty("banner_url");
public static final String WELCOME_MESSAGE_URL = properties.getProperty("welcome_message_url");
private static String gameFolderURL = properties.getProperty("game_folder_url");
private static String decorator = properties.getProperty("input_prefix");
```

1. **properties** an instance of the Properties class, which will be used to store the loaded properties from the configuration file.

2. **BANNER_URL** holds the value of the "banner_url" property from the configuration file.

3. **WELCOME_MESSAGE_URL** holds the value of the "welcome_message_url" property from the configuration file.

4. **gameFolderURL** holds the value of the "game_folder_url" property from the configuration file. This value can be accessed and modified through getter and setter methods.

5. **decorator** holds the value of the "input_prefix" property from the configuration file. This value can also be accessed and modified through getter and setter methods.

<br>

### **loadProperties() method**

This code defines a static method named loadProperties() that returns an instance of the Properties class.

```java
public static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            // Handle the exception accordingly
            e.printStackTrace();
        }
        return properties;
    }
```

This method reads the contents of the configuration file specified by CONFIG_FILE using a FileInputStream, loads the properties into a Properties object, and returns that object. The caller of this method can then access the loaded properties and use them in the application as needed.

 <br>

### **Getter and Setter classes**

```java
    public static String getGameFolderURL() {
        return gameFolderURL;
    }

    public static void setGameFolderURL(String gameFolderURL) {
        Configuration.gameFolderURL = gameFolderURL;
    }

    public static String getDecorator() {
        return decorator;
    }

    public static void setDecorator(String decorator) {
        Configuration.decorator = decorator;
    }
```

The above snippet of codes are written as getter and setter classes to get and set the values for **getGameFolderURL** and also **Decorator**

<br>
<br>

## **2. config.properties Properties File**

<br>

The purpose of the content of this file is to define and configure various properties for an application.

The contents of the file are as follows :

```bash
#Modified properties
#Wed Jun 14 18:35:25 MYT 2023
input_prefix=>>>
game_folder_url=C\:\\Users\\User\\Desktop\\suzume\\
welcome_message_url=src/main/java/com/assignment/suzume/configuration/welcome_message.txt
banner_url=src/main/java/com/assignment/suzume/configuration/banner.txt
```

1. **input_prefix** is a property that specifies the input prefix. Its value is set to >>>.

2. **game_folder_url** is a property that specifies the URL or path to the game folder. In this case, the value is set to C:\Users\User\Desktop\suzume\.

3. **welcome_message_url** is a property that specifies the URL or path to the welcome message file. In this case, the value is set to src/main/java/com/assignment/suzume/configuration/welcome_message.txt.

4. **banner_url** is a property that specifies the URL or path to the banner file. In this case, the value is set to src/main/java/com/assignment/suzume/configuration/banner.txt.

<br>

These properties can be loaded and accessed in Java code using the **Properties** class, as shown in the previous code snippet. The values can be retrieved using the **getProperty()** method, and modified using the appropriate setter methods defined in the **Configuration** class.

<br>
<br>

## **3. txt files**

There is a total of 2 txt files in this package, which are **banner.txt** and **welcome.txt**. Both of these txt files serve the same purpose, which is to provide a template to be sampled from when printing into the CLI of the application.

<br>

### **banner.txt :**

```

    _____                                _        ___      _                 _
   /  ___|                              ( )      / _ \    | |               | |
   \ `--. _   _ _____   _ _ __ ___   ___|/ ___  / /_\ \ __| |_   _____ _ __ | |_ _   _ _ __ ___
    `--. \ | | |_  / | | | '_ ` _ \ / _ \ / __| |  _  |/ _` \ \ / / _ \ '_ \| __| | | | '__/ _ \
   /\__/ / |_| |/ /| |_| | | | | | |  __/ \__ \ | | | | (_| |\ V /  __/ | | | |_| |_| | | |  __/
   \____/ \__,_/___|\__,_|_| |_| |_|\___| |___/ \_| |_/\__,_| \_/ \___|_| |_|\__|\__,_|_|  \___|

 ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ ______
|______|______|______|______|______|______|______|______|______|______|______|______|______|______|
```

**banner.txt** is a reference point to print out the banner **"Suzume's Adventure"** when the user launches the application for the first time.

<br>

### **welcome_message.txt** :

```
Welcome, adventurer, to "Suzume's Adventure"!
Embark on an exhilarating quest,
testing your wit and strategic prowess.
Navigate a labyrinthine maze,
uncovering hidden secrets.
Engage in thrilling rounds of Tic Tac Toe
to unlock rewards and boost your score.
Can you conquer the maze and outsmart opponents?
Get ready for the
adrenaline-fueled excitement of "Suzume's Adventure"
and become the ultimate adventurer!
```

**welcome_message.txt** is a file to print out the welcome message for the players as an introduction
