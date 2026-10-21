# 📝 Console Text Editor with Undo (Stack-Based)

A lightweight, interactive console-based text editor built in Java demonstrating the **Stack** data structure (`java.util.Stack`) and the **Last-In, First-Out (LIFO)** principle to implement a complete state history and multi-level **Undo** system.

---

## 🚀 Features

- **✍️ Type Text**:
  - Appends new characters or phrases to the current document string.
  - Automatically preserves the preceding text state by pushing it onto the history stack before applying changes.
- **↩️ Multi-Level Undo**:
  - Reverts the most recent text modification by popping the previous snapshot from the stack.
  - Supports continuous sequential undo operations back to the initial blank state.
  - Gracefully detects and handles an empty history stack when no actions remain to undo.
- **📄 Display Current Text**:
  - Shows the real-time buffer content currently stored in the editor.
- **🚪 Graceful Exit**:
  - Safely closes standard I/O resources (`Scanner`) and terminates execution cleanly.

---

## 🛠️ Technologies & Concepts

- **Language**: Java (JDK 8 or higher)
- **Data Structure**: `java.util.Stack<String>` (LIFO history stack)
- **I/O Handling**: `java.util.Scanner` for reading console input lines and numbers
- **Design Pattern**: Memento / Snapshot-based state management using an in-memory stack

---

## 📂 Project Structure

```text
├── TextEditor.java    # Core application source code with CLI loop and Stack undo logic
├── README.md          # Project documentation and usage guide
└── .gitignore         # Ignores compiled Java bytecode (*.class)
```

---

## 🏗️ Architecture & Component Overview

The application logic resides in [`TextEditor.java`](file:///c:/Users/sivas/OneDrive/Desktop/java%20intership/TextEditor.java):

| Component | Type | Description |
| :--- | :--- | :--- |
| [`TextEditor`](file:///c:/Users/sivas/OneDrive/Desktop/java%20intership/TextEditor.java#L4-L52) | Class | Main program class containing the text buffer, history stack, and user interface. |
| [`main(String[] args)`](file:///c:/Users/sivas/OneDrive/Desktop/java%20intership/TextEditor.java#L5-L51) | Method | Entry point driving the menu loop, user input collection, and state transitions. |
| `history` (`Stack<String>`) | Data Member | Stores past document snapshots for LIFO retrieval on undo requests. |
| `text` (`String`) | Working Buffer | Maintains the current active string state displayed to the user. |

---

## 🔄 How the Undo Mechanism Works

The editor uses a **Snapshot State Pattern** powered by `Stack<E>`:

```text
 [ Initial: "" ]
       │
       ▼  User types "Hello" -> history.push("")
 [ Stack: [""] ]               -> text = "Hello"
       │
       ▼  User types " World" -> history.push("Hello")
 [ Stack: ["", "Hello"] ]      -> text = "Hello World"
       │
       ▼  User chooses Undo -> text = history.pop()
 [ Stack: [""] ]               -> text = "Hello"
       │
       ▼  User chooses Undo -> text = history.pop()
 [ Stack: [] ]                 -> text = ""
       │
       ▼  User chooses Undo -> history.isEmpty() == true ("Nothing to undo!")
```

### Stack Operations Summary

| Action | Stack Method | Time Complexity | State Impact |
| :--- | :--- | :--- | :--- |
| **Type text** | `history.push(text)` | $O(1)$ amortized | Pushes prior document snapshot onto top of stack before appending input. |
| **Undo** | `history.pop()` | $O(1)$ | Removes and returns the top snapshot, restoring `text` to its previous state. |
| **Check Undoable** | `history.isEmpty()` | $O(1)$ | Checks if any past states exist before attempting pop. |

---

## ⚙️ Getting Started

### Prerequisites

Verify that the Java Development Kit (JDK) is installed and available in your environment:

```powershell
javac -version
java -version
```

### Compilation

Compile [`TextEditor.java`](file:///c:/Users/sivas/OneDrive/Desktop/java%20intership/TextEditor.java) using `javac`:

```powershell
javac TextEditor.java
```

This will produce the compiled bytecode file `TextEditor.class`.

### Execution

Run the compiled class:

```powershell
java TextEditor
```

---

## 🎮 Interactive Menu & Usage

Upon launch, the application displays an interactive terminal menu:

```text
--- Text Editor ---
1. Type text
2. Undo
3. Display text
4. Exit
Enter choice: 
```

### Step-by-Step Sample Session

```text
--- Text Editor ---
1. Type text
2. Undo
3. Display text
4. Exit
Enter choice: 1
Enter text to add: Hello 
Text added successfully.

--- Text Editor ---
1. Type text
2. Undo
3. Display text
4. Exit
Enter choice: 1
Enter text to add: World!
Text added successfully.

--- Text Editor ---
1. Type text
2. Undo
3. Display text
4. Exit
Enter choice: 3
Current text: Hello World!

--- Text Editor ---
1. Type text
2. Undo
3. Display text
4. Exit
Enter choice: 2
Last change undone.

--- Text Editor ---
1. Type text
2. Undo
3. Display text
4. Exit
Enter choice: 3
Current text: Hello 

--- Text Editor ---
1. Type text
2. Undo
3. Display text
4. Exit
Enter choice: 2
Last change undone.

--- Text Editor ---
1. Type text
2. Undo
3. Display text
4. Exit
Enter choice: 2
Nothing to undo!

--- Text Editor ---
1. Type text
2. Undo
3. Display text
4. Exit
Enter choice: 4
Exiting editor...
```

---

## 🛡️ Edge Cases Handled

- **Empty History Stack**: If option `2` (Undo) is selected when no edits have been made (or all edits were already undone), `history.isEmpty()` triggers `"Nothing to undo!"` instead of throwing an `EmptyStackException`.
- **Spacing and Blank Entries**: `sc.nextLine()` preserves full strings, including spaces and punctuation.
- **Unrecognized Menu Choices**: The `default` branch in the `switch` statement alerts the user (`"Invalid choice!"`) on invalid numerical selections without terminating the application.

---

## 📄 License & Notes

Developed as part of the Java Internship practical curriculum.
