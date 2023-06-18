# **8. Data**

The **'data'** package contains 2 Java classes, which include **GameFileDataManager** and **GameFolderInitializer**. The use of the classes in this package is to manage the game files that stores the progress of the user in respective games.

<br>

## **GameFileDataManager Java Class**

This Java class is 186 lines long and feature many methods. To simplify the process of explanation, let us pick out a few important methods to highlight on. Mainly, there are 8 important methods in this Java class.

<br>

### **1. saveGame() method**

```java
public void saveGame(BoardGameRunner runner, int gameMode) {
        String parentFolderPath = getParentFolderPath(getGameModeSubfolder(gameMode));
        createFolderIfNotExists(parentFolderPath);
        String filename = getSaveFileName(parentFolderPath);
        saveGame(parentFolderPath, filename, runner);
    }

```

- This method is responsible for saving a game.

- It takes a BoardGameRunner object and a gameMode parameter as inputs.

- It constructs the parent folder path and filename based on the game mode.

- It calls the saveGame() method to serialize the runner object and save it to the specified file

<br>

### **2. loadGame() method**

```java
private BoardGameRunner loadGame(String parentFolderPath, String filename) {
        BoardGameRunner runner = null;
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(parentFolderPath + File.separator + filename))) {
            runner = (BoardGameRunner) in.readObject();
            System.out.println("Game loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return runner;
    }
```

- This method is responsible for loading a saved game.
- It takes a gameMode parameter as input.
- It constructs the parent folder path and filename based on the game mode.
- It calls the loadGame() method to deserialize and retrieve the BoardGameRunner object from the specified file.

<br>

### **3. saveGameReplay() method**

```java
private void saveGameReplay(String parentFolderPath, String filename, GamingBoard board) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(parentFolderPath + File.separator + filename))) {
            out.writeObject(board);
            System.out.println("Game Replay saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```

- This method is responsible for saving a game replay.
- It takes a GamingBoard object as input.
- It constructs the parent folder path and filename for the replay.
- It calls the saveGameReplay() method to serialize the board object and save it to the specified file.

<br>

### **4. loadGameReplay() method**

```java
private GamingBoard loadGameReplay(String parentFolderPath, String filename) {
        GamingBoard board = null;
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(parentFolderPath + File.separator + filename))) {
            board = (GamingBoard) in.readObject();
            System.out.println("Game loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return board;
    }
```

- This method is responsible for loading a saved game replay.
- It constructs the parent folder path and filename for the replay.
- It calls the loadGameReplay() method to deserialize and retrieve the GamingBoard object from the specified file.

<br>

### **5. getSaveFileName() method**

```java
public String getSaveFileName(String parentFolderPath) {
        String fileName = null;

        while (true) {
            System.out.print("Enter the filename: ");
            fileName = InputHandler.getStringInput();

            if (isFileExist(parentFolderPath, fileName)) {
                System.out.println("File already exists. Do you want to overwrite it? (y/n)");
                if (!InputHandler.getStringInput().equalsIgnoreCase("y")) {
                    continue;
                }
            }

            if (fileName.isBlank() || !isFileNameValid(fileName)) {
                System.out.println("Invalid filename.");
                continue;
            }

            break;
        }

        return fileName;
    }
```

- This method prompts the user to enter a filename for saving a game or replay.
- It checks if the file already exists and asks the user if they want to overwrite it.
- It validates the entered filename and ensures it is not blank and follows a specific pattern.
- It returns the validated and chosen filename.

<br>

### **6. getLoadFileName() method**

```java
public String getLoadFileName(String parentFolderPath) {
        String fileName = null;
        while (true) {
            System.out.print("Enter the filename: ");
            fileName = InputHandler.getStringInput();

            if (!isFileExist(parentFolderPath, fileName)) {
                System.out.println("File does not exist. Enter another file name. ");
                continue;
            }

            if (fileName.isBlank() && !isFileNameValid(fileName)) {
                System.out.println("Invalid filename.");
                continue;
            }

            break;
        }
        return fileName;
    }
```

- This method prompts the user to enter a filename for loading a game or replay.
- It checks if the file exists and prompts the user to enter another filename if it doesn't.
- It validates the entered filename and ensures it is not blank and follows a specific pattern.
- It returns the validated and chosen filename.

<br>

### **7. etParentFolderPath() method**

```java
private String getParentFolderPath(String subfolder) {
        String username = User.getInstance().getName();
        String parentFolderPath = String.format(parentFolderPathFormat, Configuration.getGameFolderURL(), username, subfolder);
        return parentFolderPath;
    }
```

- This method constructs the parent folder path for saving or loading a game or replay.
- It uses the Configuration.getGameFolderURL() method to retrieve the base game folder URL.
- It formats the parent folder path using the username and subfolder provided.

<br>

### **8. createFolderIfNotExists(String folderPath) method**

```java
private void createFolderIfNotExists(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
```

- This method creates a folder at the specified folder path if it doesn't already exist.

<br>
<br>

## **GameFileInputHandler Java Class**

This Java class named GameFileInputHandler handles the saving and loading of game data for a Tic-Tac-Toe game. Here are some important methods of this Java class.

<br>

### **1. handleSaveGame()() method**

```java
public void handleSaveGame(BoardGameRunner runner, int gameMode) {
        handleSaveProcess(runner, gameMode, "Save Game");
    }
```

- Handles the saving of the game data.
- It takes a **BoardGameRunner** instance representing the current game state and the game mode as parameters.

<br>

### **2. handleSaveReplay() method**

```java
    public void handleSaveReplay(GamingBoard board) {
        handleSaveProcess(board, null, "Save Game Replay");
    }
```

- Handles the saving of the game replay data.
- It takes a GamingBoard instance representing the game board as a parameter.

<br>

### **3. handleLoadGame() method**

```java
    public Optional<BoardGameRunner> handleLoadGame(int gameMode) {
        Optional<Object> obj = handleLoadProcess(gameMode, "Load Game");
        return obj.isPresent() ? Optional.of((BoardGameRunner) obj.get()) : Optional.empty();
    }
```

- Handles the loading of the game data.
- It takes the game mode as a parameter and returns an **Optional<BoardGameRunner>** containing the loaded **BoardGameRunner** instance if successful, or an empty **Optional** otherwise.

<br>

### **4. handleLoadReplay() method**

```java
    public Optional<GamingBoard> handleLoadReplay() {
        Optional<Object> obj = handleLoadProcess(null, "Load Game Replay");
        return obj.isPresent() ? Optional.of((GamingBoard) obj.get()) : Optional.empty();
    }
```

- Handles the loading of the game replay data.
- It returns an **Optional<GamingBoard>** containing the loaded **GamingBoard** instance if successful, or an empty **Optional** otherwise.

<br>

### **5. computeIfFileExists() method**

```java
    private boolean computeIfFileExists(String parentFolder) {
        return computeIfFileExists(parentFolder, 5);
    }
```

- Checks if files exist in the specified parent folder and returns true if files exist, false otherwise.

<br>

### **6. performLoad() method**

```java
    private Optional<Object> performLoad(Integer gameMode, String parentFolderPath, String filename) {
        if (gameMode != null) {
            Object obj = manager.loadGame(parentFolderPath, filename);
            System.out.println("Game loaded successfully.");
            return Optional.ofNullable(obj);
        } else {
            Object obj = manager.loadGameReplay(parentFolderPath, filename);
            System.out.println("Game Replay loaded successfully.");
            return Optional.ofNullable(obj);
        }
    }
```

- Performs the load process for game data.
- It takes the game mode, parent folder path, and filename as parameters and returns an **Optional<Object>** containing the loaded object if successful, or an empty **Optional** otherwise.

<br>

### **7. performSave() method**

```java
    private Optional<Object> performSave(Object object, Integer gameMode, String parentFolderPath, String filename) {
        if (gameMode != null) {
            manager.saveGame(parentFolderPath, filename, (BoardGameRunner) object);
            System.out.println("Game saved successfully.");
        } else {
            manager.saveGameReplay(parentFolderPath, filename, (GamingBoard) object);
            System.out.println("Game Replay saved successfully.");
        }
        return Optional.empty();
    }
```

- Performs the save process for game data.
- It takes the object to be saved, game mode, parent folder path, and filename as parameters and returns an **Optional<Object>** containing the saved object if successful, or an empty **Optional** otherwise.

<br>
<br>

## **GameFolderInitializer Java Class**

This class is responsible for initializing and managing the game folder and user-specific folders in a game application. There is a total of 7 methods that are important in this Java class.

<br>

### **1. checkGameFolder() method**

```java
public void checkGameFolder() {
        if (!isGameFolderURLSet()) {
            configureGameFolderURL();
            saveConfiguration();
            Configuration.setGameFolderURL(gameFolderURL);
        }

        if (gameFolderURL == null) {
            this.gameFolderURL = properties.getProperty("game_folder_url");
        }

        createGameFolder();
    }
```

- This method checks if the game folder URL is set.
- If not set, it configures the game folder URL, saves the configuration, and sets the game folder URL in the Configuration class.
- It then calls the createGameFolder() method to create the game folder.

<br>

### **2. configureGameFolderURL() method**

```java
private void configureGameFolderURL() {
        // Set the Material Design look and feel
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame parentFrame = new JFrame();
        File defaultDirectory = getDefaultDirectory();

        JFileChooser fileChooser = new JFileChooser(defaultDirectory);
        fileChooser.updateUI();
        fileChooser.setDialogTitle("Choose a folder to save your game data");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Customize the appearance of the file chooser
        UIManager.put("FileChooser.background", MaterialColors.CYAN_50);
        UIManager.put("FileChooser.foreground", MaterialColors.BLACK);
        UIManager.put("FileChooser.selectionBackground", MaterialColors.CYAN_100);
        UIManager.put("FileChooser.selectionForeground", MaterialColors.WHITE);
        UIManager.put("FileChooser.acceptButtonToolTipText", "Select");
        UIManager.put("FileChooser.cancelButtonToolTipText", "Cancel");

        int result = fileChooser.showOpenDialog(parentFrame);

        File selectedFolder = (result == JFileChooser.APPROVE_OPTION)
                ? fileChooser.getSelectedFile()
                : defaultDirectory;

        this.gameFolderURL = selectedFolder.getAbsolutePath() + File.separator + "suzume" + File.separator;
    }
```

- This method allows the user to choose a folder to save their game data.
- It opens a JFileChooser dialog for the user to select a directory.
- It sets the selected folder as the game folder URL.

<br>

### **3. saveConfiguration() method**

```java
private boolean saveConfiguration() {
        // Edit the game_folder_url property
        properties.setProperty("game_folder_url", gameFolderURL);

        // Save the modified properties back to a file if needed
        try (FileOutputStream fileOutputStream = new FileOutputStream(Configuration.CONFIG_FILE)) {
            properties.store(fileOutputStream, "Modified properties");
            return true;
        } catch (IOException e) {
            return false;
        }
    }
```

- This method saves the modified properties, including the game folder URL, back to a file.
- It writes the properties to the configuration file using a FileOutputStream and the store() method.

<br>

### **4. createGameFolder() method**

```java
private void createGameFolder() {
        new File(gameFolderURL).mkdirs();
    }
```

- This method creates the game folder if it doesn't already exist.
- It uses the mkdirs() method to create the folder and any necessary parent directories.

<br>

### **5. createUserFolder() method**

```java
public void createUserFolder(String username) {
        final File userFolder = new File(gameFolderURL, username);
        final String[] subfolders = { "save_game", "replay" };
        final String[] saveSubfolders = { "pvp", "pve", "eve" };

        userFolder.mkdirs();

        for (String subfolder : subfolders) {
            File folder = new File(userFolder, subfolder);
            folder.mkdirs();
        }

        for (String saveSubfolder : saveSubfolders) {
            File folder = new File(userFolder + File.separator + "save_game", saveSubfolder);
            folder.mkdirs();
        }
    }
```

- This method creates a user-specific folder within the game folder.
- It creates subfolders for saving games and replays.
- It uses the mkdirs() method to create the necessary directories.

<br>

### \*\*6. getDefaultDirectory method()

```java
private File getDefaultDirectory() {
        String userHome = System.getProperty("user.home");
        File programFileDir = new File(userHome);
        return programFileDir;
    }
```

- This method creates a user-specific folder within the game folder.
- It creates subfolders for saving games and replays.
- It uses the mkdirs() method to create the necessary directories.

### **7. getGameFolderURL() method**

```java
public String getGameFolderURL() {
        if (this.gameFolderURL == null) {
            properties.getProperty("game_folder_url");
        }
        return gameFolderURL;
    }
```

- This method retrieves the game folder URL.
- If the gameFolderURL instance variable is null, it retrieves the value from the properties file.
